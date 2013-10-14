package net.canarymod.plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import net.canarymod.Canary;
import net.canarymod.CanaryClassLoader;
import net.canarymod.motd.MessageOfTheDay;
import net.canarymod.chat.Colors;
import net.canarymod.hook.system.PluginDisableHook;
import net.canarymod.hook.system.PluginEnableHook;
import net.canarymod.tasks.ServerTaskManager;
import net.visualillusionsent.utils.PropertiesFile;

/**
 * Plugin Loading and Management Class
 * <p/>
 * Handles loading, unloading, enabling, disabling, and dependency resolving
 *
 * @author Jason (darkdiplomat)
 * @author Chris (damagefilter)
 * @author Jos
 */
public final class PluginLoader {
    private final LinkedHashMap<String, Plugin> plugins; // This is keyed to set Plugin name
    private final HashMap<String, PropertiesFile> pluginInf; // This is keyed to main class name
    private final PropertiesFile pluginPriorities;
    private static final Object lock = new Object();

    public PluginLoader() {
        plugins = new LinkedHashMap<String, Plugin>();
        pluginInf = new HashMap<String, PropertiesFile>();
        this.pluginPriorities = new PropertiesFile("config" + File.separator + "plugin_priorities.cfg");
    }

    public final void scanPlugins() {
        if (!plugins.isEmpty()) {
            return;
        }
        Canary.logInfo("Scanning for plugins ...");
        File dir = new File("plugins/");
        if (!dir.exists()) {
            Canary.logSevere("Failed to scan for plugins. 'plugins/' is not a directory. Creating...");
            dir.mkdir();
            return;
        }
        else if (!dir.isDirectory()) {
            Canary.logSevere("Failed to scan for plugins. 'plugins/' is not a directory but a file...");
            return;
        }
        ArrayList<String> jars = new ArrayList<String>();
        for (String jarfile : dir.list()) {
            if (!jarfile.endsWith(".jar")) {
                continue;
            }
            jars.add(jarfile);
        }
        HashMap<String, PropertiesFile> canLoad = new HashMap<String, PropertiesFile>();
        int numLoaded = 1;
        for (String jar : jars) {
            PropertiesFile check = scan(jar, numLoaded);
            if (check == null) {
                continue;
            }
            else {
                canLoad.put(jar, check);
            }
            numLoaded++;
        }
        LinkedList<DependencyNode> loadOrder = new LinkedList<DependencyNode>();
        buildDepTree(canLoad, loadOrder);

        Canary.logInfo("Found " + loadOrder.size() + " loadable plugins. Attempting load...");
        for (DependencyNode node : loadOrder) {
            load(node.getJarName(), node.getInf());
        }
    }

    /**
     * Get the Canary.inf from a jar file
     *
     * @param filename
     * @param priorityBase
     *         The base for plugin priority which is used to calculate the priority of new Plugins
     *
     * @return
     */
    private final PropertiesFile scan(String filename, int priorityBase) {
        PropertiesFile inf;
        try {
            File file = new File("plugins/" + filename);
            String jarName = filename.substring(0, filename.lastIndexOf("."));
            inf = new PropertiesFile(file.getAbsolutePath(), "Canary.inf");

            if (!inf.containsKey("main-class")) {
                Canary.logSevere("Failed to read main-class for '" + file.getName() + "' in Canary.inf Please specify a main-class entry in Canary.inf");
                return null;
            }
            inf.setString("jarName", jarName);
            inf.setString("jarPath", "plugins/".concat(filename));
            if (!inf.containsKey("name")) {
                inf.setString("name", simpleMain(inf.getString("main-class")));
            }

            if (!pluginPriorities.containsKey(inf.getString("name"))) {
                pluginPriorities.setInt(inf.getString("name"), priorityBase * 10);
                pluginPriorities.save();
            }
            else if (pluginPriorities.getInt(inf.getString("name")) < 0) {
                return null;
            }
        }
        catch (Throwable ex) {
            Canary.logStacktrace("Exception while loading plugin jar '" + filename + "' (Canary.inf missing?)", ex);
            return null;
        }

        return inf;
    }

    /**
     * This recursive method actually solves the dependency lists
     *
     * @param node
     * @param resolved
     */
    private void depResolve(DependencyNode node, LinkedList<DependencyNode> resolved) {
        if (!node.isInvalid()) {
            for (DependencyNode edge : node.edges) {
                if (!resolved.contains(edge)) {
                    this.depResolve(edge, resolved);
                }
            }
            resolved.add(node);
        }
    }

    private Plugin unsafeScanForPlugin(String name) {
        File dir = new File("plugins/");
        if (!dir.isDirectory()) {
            return null;
        }
        for (File jar : dir.listFiles()) {
            if (!jar.isFile() || !jar.getName().endsWith(".jar")) {
                continue;
            }
            try {
                PropertiesFile inf = new PropertiesFile(jar.getAbsolutePath(), "Canary.inf");
                if (!inf.containsKey("name")) {
                    if (!simpleMain(inf.getString("main-class")).equals(name)) {
                        continue;
                    }
                    inf.setString("name", simpleMain(inf.getString("main-class")));
                }
                else if (!inf.getString("name").equals(name)) {
                    continue;
                }
                inf.setString("jarPath", "plugins/".concat(jar.getName()));
                inf.setString("jarName", jar.getName().replace(".jar", ""));

                if (inf.containsKey("dependencies")) {
                    for (String dep : inf.getStringArray("dependencies", ";")) {
                        if (!plugins.containsKey(dep)) {
                            // Unsatisfied dependency
                            return null;
                        }
                    }
                }

                if (load(jar)) {
                    return plugins.get(name);
                }

            }
            catch (Throwable thrown) {
                Canary.logStacktrace("Something broke. Here's what we know: ", thrown);
            }
        }
        return null;
    }

    /**
     * Builds the tree that is used to topsort the dependencies
     *
     * @param knownJars
     * @param loadOrder
     */
    private final void buildDepTree(HashMap<String, PropertiesFile> knownJars, LinkedList<DependencyNode> loadOrder) {
        HashMap<String, DependencyNode> nodes = new HashMap<String, DependencyNode>();
        // Make the flat dep tree
        for (String jar : knownJars.keySet()) {
            PropertiesFile inf = knownJars.get(jar);
            nodes.put(inf.getString("name"), new DependencyNode(inf.getString("name"), jar, inf));
        }
        // Create the basic dependency list
        Iterator<String> itr = nodes.keySet().iterator();
        while (itr.hasNext()) {
            String jar = itr.next();
            DependencyNode node = nodes.get(jar);
            PropertiesFile inf = node.getInf();

            if (inf.containsKey("dependencies")) {
                String[] dependencies = inf.getStringArray("dependencies", "[,;]+");
                for (String dependency : dependencies) {
                    dependency = dependency.trim();
                    // Remove empty entries
                    if (dependency.length() == 0) {
                        continue;
                    }
                    if (!nodes.containsKey(dependency)) {
                        Canary.logServerMessage("Cannot find dependency " + dependency + " but " + jar + " depends on it. Removing.");
                        itr.remove();
                        continue;
                    }
                    if (!node.edges.contains(nodes.get(dependency))) {
                        node.edges.add(nodes.get(dependency));
                    }
                }
            }

            if (inf.containsKey("optional-dependencies")) {
                String[] softDependencies = inf.getStringArray("optional-dependencies", "[,;]+");
                for (String dependency : softDependencies) {
                    dependency = dependency.trim();
                    // Remove empty entries
                    if (dependency.length() == 0) {
                        continue;
                    }
                    if (!nodes.containsKey(dependency)) {
                        continue; // ignore soft dependencies
                    }
                    if (!node.edges.contains(nodes.get(dependency))) {
                        node.edges.add(nodes.get(dependency));
                    }
                }
            }
            // Detect if we have a circular dependency inside this node
            // then mark them as invalid so they will not be processed
            for (DependencyNode dep : node.edges) {
                if (dep.edges.contains(node)) {
                    Canary.logWarning("Detected circular dependency for " + node.getName() + ": " + dep.getName()
                            + ". These Plugins can not be loaded.");
                    node.setInvalid(true);
                    dep.setInvalid(true);
                }
            }
        }
        for (DependencyNode node : nodes.values()) {
            depResolve(node, loadOrder);
        }
    }

    /**
     * The class loader
     * The pluginName should come as full file name with file extension
     *
     * @param pluginJar
     * @param inf
     *
     * @return
     */
    private final boolean load(String pluginJar, PropertiesFile inf) {
        try {
            String name = inf.getString("name");
            String mainClass = inf.getString("main-class");
            if (plugins.containsKey(name)) {
                Canary.logSevere(name + " is already loaded, skipping");
                return false; // Already loaded
            }

            String[] deps = new String[0];
            if (inf.containsKey("dependencies")) {
                deps = inf.getStringArray("dependencies", "[,;]+");
            }

            if (deps == null) {
                Canary.logSevere("There was a problem while fetching " + name + "'s dependency list.");
                return false;
            }

            if (deps.length > 0) {
                ArrayList<String> missingDeps = new ArrayList<String>(1);

                for (String dep : deps) {
                    if (!plugins.containsKey(dep)) {
                        missingDeps.add(dep);
                    }
                }
                if (!missingDeps.isEmpty()) {
                    Canary.logSevere("To load " + name + " you need to enable the following plugins first: " + missingDeps.toString());
                    return false;
                }
            }
            pluginInf.put(simpleMain(mainClass), inf);
            CanaryClassLoader ploader = new CanaryClassLoader(new File(inf.getString("jarPath")).toURI().toURL(), getClass().getClassLoader());
            Class<?> c = ploader.loadClass(mainClass);
            Plugin plugin = (Plugin) c.newInstance();
            plugin.setPriority(pluginPriorities.getInt(name, 0));
            synchronized (lock) {
                this.plugins.put(name, plugin);
            }
        }
        catch (Throwable ex) {
            Canary.logStacktrace("Exception while loading plugin '" + pluginJar + "'", ex);
            return false;
        }

        return true;
    }

    private final boolean load(File file) {
        try {
            if (!file.isFile()) {
                return false;
            }

            PropertiesFile inf = new PropertiesFile(file.getAbsolutePath(), "Canary.inf");
            // Get the main class, or use the plugin name as class
            if (!inf.containsKey("main-class")) {
                Canary.logSevere("Failed to read main-class for '" + file.getName() + "' in Canary.inf Please specify a main-class entry in Canary.inf");
                return false;
            }

            if (!inf.containsKey("name")) {
                inf.setString("name", simpleMain(inf.getString("main-class")));
            }
            inf.setString("jarPath", "plugins/".concat(file.getName()));
            inf.setString("jarName", file.getName().replace(".jar", ""));

            return load(file.getName(), inf);
        }
        catch (Throwable ex) {
            Canary.logStacktrace("Exception while loading plugin", ex);
            return false;
        }
    }

    private final String simpleMain(String main) {
        int last = main.lastIndexOf('.');
        return main.substring(last != -1 ? last + 1 : 0, main.length());
    }

    /**
     * Enables the given plugin. Loads the plugin if not loaded (and available)
     *
     * @param name
     *         the name of the {@link Plugin}
     *
     * @return {@code true} on success, {@code false} on failure
     */
    public final boolean enablePlugin(String name) {
        Plugin plugin = this.getPlugin(name);
        if (plugin == null) {
            // Plugin is NIL - lets see if we have it on disk
            plugin = unsafeScanForPlugin(name);
        }
        return enablePlugin(plugin);
    }

    /* Same as public boolean enablePlugin(String name) */
    private final boolean enablePlugin(Plugin plugin) {
        if (plugin == null) {
            return false;
        }

        // The plugin must be disabled to enable
        if (!plugin.isDisabled()) {
            return true; // already enabled
        }

        // Set the plugin as enabled and send enable message
        boolean enabled = false;
        boolean needNewInstance = true;
        if (plugins.containsValue(plugin)) {
            try {
                if (!plugin.isClosed()) {
                    if (!testDependencies(plugin)) {
                        return false;
                    }
                    enabled = plugin.enable();
                    needNewInstance = false;
                }
            }
            catch (Throwable t) {
                // If the plugin is in development, they may need to know where something failed.
                Canary.logStacktrace("Could not enable " + plugin.getName(), t);
            }
        }
        if (needNewInstance) {
            try {
                File file = new File(plugin.getJarPath());
                PropertiesFile inf = new PropertiesFile(file.getAbsolutePath(), "Canary.inf");
                pluginInf.put(plugin.getClass().getSimpleName(), inf);
                if (testDependencies(plugin)) { // Test for changes
                    CanaryClassLoader ploader = new CanaryClassLoader(new File(inf.getString("jarPath")).toURI().toURL(), getClass().getClassLoader());
                    Class<?> cls = ploader.loadClass(inf.getString("main-class"));
                    plugin = (Plugin) cls.newInstance();
                    enabled = plugin.enable();
                    plugins.put(plugin.getName(), plugin);
                }
            }
            catch (Throwable t) {
                // If the plugin is in development, they may need to know where something failed.
                Canary.logStacktrace("Could not enable " + plugin.getName(), t);
            }
        }

        if (enabled) {
            plugin.toggleDisabled();
            Canary.hooks().callHook(new PluginEnableHook(plugin));

            // Check and add plugins to their dependents
            if (plugin.getCanaryInf().containsKey("dependencies")) {
                String[] deps = plugin.getCanaryInf().getStringArray("dependencies", "[,;]+");
                for (String dep : deps) {
                    plugins.get(dep).addDependent(plugin.getName());
                }
            }

            // Check for dependents and re-enable them as well, if this is a new instance this will have no effect here and will be handled in the reload method
            if (plugin.hasDependents()) {
                Canary.logInfo(String.format("%s has %d dependents that will now be re-enabled...", plugin.getName(), plugin.getDependents().size()));
                for (String dependent : plugin.getDependents()) {
                    enablePlugin(dependent);
                }
            }

            Canary.logInfo("Enabled " + plugin.getName() + ", Version " + plugin.getVersion());
        }
        else {
            // Clean up anything that may have got registered
            /* Remove Registered Listeners */
            Canary.hooks().unregisterPluginListeners(plugin);
            /* Remove Commands */
            Canary.commands().unregisterCommands(plugin);
            /* Remove Tasks */
            ServerTaskManager.removeTasksForPlugin(plugin);
            /* Remove MOTD Variables */
            Canary.motd().unregisterMOTDListener(plugin);
        }
        return enabled;
    }

    /**
     * Tests if all dependencies for the Plugin are present and running
     *
     * @param plugin
     *         the Plugin to test dependencies for
     *
     * @return {@code true} if passes; {@code false} otherwise
     */
    private final boolean testDependencies(Plugin plugin) {
        if (!plugin.getCanaryInf().containsKey("dependencies")) {
            return true;
        }

        String[] deps = plugin.getCanaryInf().getStringArray("dependencies", "[,;]+");

        if (deps == null) {
            Canary.logSevere("There was a problem while fetching " + plugin.getName() + "'s dependency list.");
            return false;
        }

        if (deps.length != 0) {
            ArrayList<String> missingDeps = new ArrayList<String>(1);

            for (String dep : deps) {
                if (!plugins.containsKey(dep) || plugins.get(dep).isDisabled()) {
                    missingDeps.add(dep);
                }
            }
            if (!missingDeps.isEmpty()) {
                Canary.logSevere("To enable " + plugin.getName() + " you need to enable the following plugins first: " + missingDeps.toString());
                return false;
            }
        }
        return true;
    }

    /** Enables all plugins, used when starting up the server. */
    public final void enableAllPlugins() {
        int enabled = 0;
        for (Plugin plugin : plugins.values()) {
            if (enablePlugin(plugin)) {
                enabled++;
            }
        }
        Canary.logInfo("Enabled " + enabled + " plugins.");
    }

    /**
     * Disables the given plugin
     *
     * @param name
     *         the name of the {@link Plugin}
     *
     * @return {@code true} on success, {@code false} on failure
     */
    public final boolean disablePlugin(String name) {
        return disablePlugin(plugins.get(name));
    }

    /* Same as public boolean disablePlugin(String name) */
    private final boolean disablePlugin(Plugin plugin) {
        /* Plugin must exist before disabling*/
        if (plugin == null) {
            return false;
        }

        /* Plugin must also be enabled to disable */
        if (plugin.isDisabled()) {
            return true;
        }

        /* Set the plugin as disabled, and send disable message */
        plugin.toggleDisabled();
        /* Remove Registered Listeners */
        Canary.hooks().unregisterPluginListeners(plugin);
        /* Remove Commands */
        Canary.commands().unregisterCommands(plugin);
        /* Remove Tasks */
        ServerTaskManager.removeTasksForPlugin(plugin);
        /* Remove MOTD Variables */
        Canary.motd().unregisterMOTDListener(plugin);
        try {
            // Check if the plugin has dependents that need disabled as well
            if (plugin.hasDependents()) {
                Canary.logInfo(String.format("%s has %d dependents that will now be disabled...", plugin.getName(), plugin.getDependents().size()));
                for (String dependent : plugin.getDependents()) {
                    disablePlugin(dependent);
                    // DO NOT REMOVE THEM! We need to know the dependents if this is a reload case
                }
            }

            plugin.disable(); // Now call disable
        }
        catch (Throwable t) {
            Canary.logStacktrace("Error while disabling " + plugin.getName(), t);
        }
        Canary.hooks().callHook(new PluginDisableHook(plugin));
        Canary.logInfo("Disabled " + plugin.getName() + ", Version " + plugin.getVersion());
        return true;
    }

    /** Disables all plugins, used when shutting down the server. */
    public final void disableAllPlugins() {
        for (Plugin plugin : this.getPlugins()) {
            disablePlugin(plugin);
        }
    }

    /**
     * Moves a plugins jar file to the disabled/ folder
     * so it won't be loaded with the next server-start/restart
     *
     * @param name
     *
     * @return
     */
    public final boolean moveToDisabled(String name) {
        Plugin plugin = this.getPlugin(name);
        if (plugin == null) {
            return false;
        }
        ((CanaryClassLoader) plugin.getClass().getClassLoader()).close();
        plugin.markClosed();
        plugins.remove(name);
        pluginPriorities.setInt(name, -1);
        return true;
    }

    /**
     * Reload the specified plugin
     *
     * @param name
     *
     * @return true on success, false on failure which probably means the plugin is now not enabled nor loaded
     */
    public boolean reloadPlugin(String name) {
        Plugin plugin = this.getPlugin(name);

        // Plugin must exist before reloading
        if (plugin == null) {
            Canary.logWarning("Could not reload " + name + ". It doesn't exist.");
            return false;
        }

        // Disable the plugin
        disablePlugin(plugin);
        PropertiesFile orgInf;
        synchronized (lock) {
            plugins.remove(plugin.getName());
            ((CanaryClassLoader) plugin.getClass().getClassLoader()).close(); // close loader
            /* Remove INF reference */
            orgInf = pluginInf.remove(plugin.getClass().getSimpleName());
        }
        plugin.markClosed();
        // Reload the plugin by loading its package again
        boolean test = load(new File(orgInf.getString("jarPath")));
        if (test) {
            test = enablePlugin(plugin.getName()); // We have a name, not the new instance. Don't pass the plugin directly.

            // Check for dependents and reload them as well
            if (plugin.hasDependents()) {
                Canary.logInfo(String.format("%s has %d dependents that will now be reloaded...", plugin.getName(), plugin.getDependents().size()));
                for (String dependent : plugin.getDependents()) {
                    reloadPlugin(dependent); // need to reload plugins so the class references are corrected
                }
            }
        }
        return test;
    }

    /**
     * Get the Plugin with specified name.
     *
     * @param name
     *
     * @return The plugin for the given name, or null on failure.
     */
    public final Plugin getPlugin(String name) {
        synchronized (lock) {
            return plugins.get(name);
        }
    }

    /**
     * Gets an unmodifiable collection of currently loaded Plugins
     *
     * @return unmodifiable collection of Plugins
     */
    public final Collection<Plugin> getPlugins() {
        synchronized (lock) {
            return Collections.unmodifiableCollection(plugins.values());
        }
    }

    /**
     * Get a list of plugin-names
     *
     * @return String array of Plugin names
     */
    public final String[] getPluginList() {
        ArrayList<String> list = new ArrayList<String>();
        String[] ret = new String[list.size()];

        synchronized (lock) {
            list.addAll(plugins.keySet());
        }

        return list.toArray(ret);
    }

    /**
     * Get a list of plugins for shoeing to the player
     * The format is: (color)pluginname where color is light green for Enabled or light red for disabled
     *
     * @return readable list of plugins
     */
    public final String getReadablePluginList() {
        StringBuilder sb = new StringBuilder();

        synchronized (lock) {
            for (Plugin plugin : plugins.values()) {
                if (!plugin.isDisabled()) {
                    sb.append(Colors.LIGHT_GREEN).append(plugin.getName()).append(Colors.WHITE).append(", ");
                }
                else {
                    sb.append(Colors.LIGHT_RED).append(plugin.getName()).append(Colors.WHITE).append(", ");
                }
            }
        }
        String str = sb.toString();

        if (str.length() > 1) {
            return str.substring(0, str.length() - 1);
        }
        else {
            return null;
        }
    }

    /**
     * Get a list of plugins for showing to the player
     * The format is: pluginname (X) where X is E(nabled) or D(isabled)
     *
     * @return console readable list of plugins
     */
    public final String getReadablePluginListForConsole() {
        StringBuilder sb = new StringBuilder();

        synchronized (lock) {
            for (Plugin plugin : plugins.values()) {
                if (!plugin.isDisabled()) {
                    sb.append("(E)").append(plugin.getName()).append(",");
                }
                else {
                    sb.append("(D)").append(plugin.getName()).append(",");
                }
            }
        }
        String str = sb.toString();

        if (str.length() > 1) {
            return str.substring(0, str.length() - 1);
        }
        else {
            return null;
        }
    }

    final PropertiesFile getPluginInf(String main_class_name) {
        return pluginInf.get(main_class_name);
    }

    /**
     * A node used in solving the dependency tree.
     *
     * @author Jos Kuijpers
     * @author Chris (damagefilter)
     */
    class DependencyNode {

        private String name;
        private String jar;
        public ArrayList<DependencyNode> edges;
        private PropertiesFile inf;
        private boolean isInvalid = false;

        DependencyNode(String name, String jar, PropertiesFile inf) {
            this.name = name;
            this.jar = jar;
            this.edges = new ArrayList<DependencyNode>();
            this.inf = inf;
        }

        public String getName() {
            return this.name;
        }

        public String getJarName() {
            return this.jar;
        }

        public PropertiesFile getInf() {
            return this.inf;
        }

        public boolean isInvalid() {
            return isInvalid;
        }

        public void setInvalid(boolean dep) {
            this.isInvalid = dep;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            sb.append("<").append(this.name).append(">(");
            for (DependencyNode node : this.edges) {
                sb.append(node.toString()).append(",");
            }
            int idx = sb.lastIndexOf(",");
            if (idx != -1) {
                sb.deleteCharAt(idx);
            }
            sb.append(")");

            return sb.toString();
        }
    }
}
