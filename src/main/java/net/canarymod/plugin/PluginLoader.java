package net.canarymod.plugin;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import net.canarymod.Canary;
import net.canarymod.CanaryClassLoader;
import net.canarymod.chat.Colors;
import net.visualillusionsent.utils.PropertiesFile;

/**
 * Plugin Loading and Management Class
 * <p>
 * Handles loading, unloading, enabling, disabling, and dependency resolving
 *
 * @author Jason (darkdiplomat)
 * @author Chris (damagefilter)
 * @author Jos
 */
public final class PluginLoader {
    private final HashMap<String, Plugin> plugins;
    private final PropertiesFile pluginPriorities;
    private final CanaryClassLoader masterLoader;
    private static final Object lock = new Object();

    public PluginLoader() {
        plugins = new HashMap<String, Plugin>();
        this.pluginPriorities = new PropertiesFile("config" + File.separator + "plugin_priorities.cfg");
        masterLoader = new CanaryClassLoader(new URL[0], this.getClass().getClassLoader());
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
        LinkedList<String> loadOrder = new LinkedList<String>();
        scanDependencies(canLoad, loadOrder);

        Canary.logInfo("Found " + loadOrder.size() + " loadable plugins. Attempting load...");
        for (String name : loadOrder) {
            load(name, canLoad.get(name));
        }
    }

    /**
     * Get the Canary.inf from a jar file
     * @param filename
     * @param priorityBase The base for plugin priority which is used to calculate the priority of new Plugins
     * @return
     */
    private final PropertiesFile scan(String filename, int priorityBase) {
        PropertiesFile inf = null;
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
        } catch (Throwable ex) {
            Canary.logStackTrace("Exception while loading plugin jar '" + filename + "' (Canary.inf missing?)", ex);
            return null;
        }

        return inf;
    }

    /**
     * Start solving the dependency list given.
     *
     * @param pluginDependencies
     * @return
     */
    private LinkedList<String> solveDependencies(HashMap<String, HashMap<String, Boolean>> pluginDependencies, HashMap<String, PropertiesFile> knownJars) {
        // http://www.electricmonk.nl/log/2008/08/07/dependency-resolving-algorithm/

        if (pluginDependencies.size() == 0) {
            return new LinkedList<String>();
        }

        LinkedList<String> retOrder = new LinkedList<String>();
        HashMap<String, DependencyNode> graph = new HashMap<String, DependencyNode>();

        // Create the node list
        for (String name : pluginDependencies.keySet()) {
            graph.put(name, new DependencyNode(name));
        }

        // Add dependency nodes to the nodes
        LinkedList<String> isDependency = new LinkedList<String>();

        for (String pluginName : pluginDependencies.keySet()) {
            DependencyNode node = graph.get(pluginName);

            for (String depName : pluginDependencies.get(pluginName).keySet()) {
                if (!graph.containsKey(depName)) {
                    // If the dependency is already loaded omit error
                    boolean resolved = false;
                    for (PropertiesFile depInf : knownJars.values()) {
                        if (depName.equals(depInf.getString("name"))) {
                            resolved = true;
                            break;
                        }
                    }
                    if (resolved) {
                        continue;
                    }

                    // If the dependency is soft, don't trigger any error
                    if (pluginDependencies.get(pluginName).get(depName) == true) {
                        continue;
                    }

                    // Dependency does not exist, lets happily fail
                    Canary.logSevere("Failed to solve dependency '" + depName + "' for '" + pluginName + "'. The plugin will not be loaded.");
                    graph.remove(pluginName);
                    break;
                }
                node.addEdge(graph.get(depName));
                isDependency.add(depName);
            }
        }

        // Remove nodes in the top-list that are in the graph too
        for (String dep : isDependency) {
            graph.remove(dep);
        }

        // If there are no nodes anymore, there might have been a circular dependency
        if (graph.size() == 0) {
            Canary.logWarning("Failed to solve dependency graph. Is there a circular dependency?");
            return null;
        }

        // The graph now contains elements that either have edges or are lonely

        LinkedList<DependencyNode> resolved = new LinkedList<DependencyNode>();

        for (String n : graph.keySet()) {
            this.depResolve(graph.get(n), resolved);
        }

        for (DependencyNode x : resolved) {
            retOrder.addFirst(x.getName());
        }

        return retOrder;
    }

    /**
     * This recursive method actually solves the dependency lists
     *
     * @param node
     * @param resolved
     */
    private void depResolve(DependencyNode node, LinkedList<DependencyNode> resolved) {
        for (DependencyNode edge : node.edges) {
            if (!resolved.contains(edge)) {
                this.depResolve(edge, resolved);
            }
        }
        resolved.addFirst(node);
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
                inf.setString("short-main", simpleMain(inf.getString("main-class")));
                inf.setString("jarPath", "plugins/".concat(jar.getName()));
                inf.setString("jarName", jar.getName().replace(".jar", ""));

                for (String dep : inf.getStringArray("dependencies", ";")) {
                    if (!plugins.containsKey(dep)) {
                        // Unsatisfied dependency
                        return null;
                    }
                }

                if (load(jar)) {
                    return plugins.get(name);
                }

            } catch (Throwable thrown) {
                // well that broke
            }
        }
        return null;
    }

    private final void scanDependencies(HashMap<String, PropertiesFile> knownJars, LinkedList<String> loadOrder) {
        HashMap<String, HashMap<String, Boolean>> pluginDependencies = new HashMap<String, HashMap<String, Boolean>>();
        for(String jar : knownJars.keySet()){
            PropertiesFile inf = knownJars.get(jar);
            if (!inf.containsKey("dependencies")) {
                loadOrder.addFirst(jar);
                continue;
            }
            else if (!inf.containsKey("optional-dependencies")) {
                loadOrder.addFirst(jar);
                continue;
            }
            else{
                // Find dependencies and put them in the dependency order-list
                HashMap<String, Boolean> depends = new HashMap<String, Boolean>();

                String[] dependencies = inf.getStringArray("dependencies", ";");

                for (String dependency : dependencies) {
                    dependency = dependency.trim();

                    // Remove empty entries
                    if (dependency.length() == 0) {
                        continue;
                    }

                    // Remove duplicates
                    if (depends.keySet().contains(dependency)) {
                        continue;
                    }

                    depends.put(dependency, false);
                }

                String[] softDependencies = inf.getStringArray("optional-dependencies", ";");

                for (String dependency : softDependencies) {
                    dependency = dependency.trim();

                    // Remove empty entries
                    if (dependency.length() == 0) {
                        continue;
                    }

                    // Remove duplicates
                    if (depends.keySet().contains(dependency)) {
                        continue;
                    }
                    depends.put(dependency, true);
                }
                pluginDependencies.put(jar, depends);
            }
        }
        loadOrder.addAll(solveDependencies(pluginDependencies, knownJars));
    }

    /**
     * The class loader
     * The pluginName should come as full file name with file extension
     *
     * @param pluginName
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
            masterLoader.addURL(new File(inf.getString("jarPath")).toURI().toURL());

            Class<?> c = masterLoader.loadClass(mainClass);
            Plugin plugin = (Plugin) c.newInstance();
            plugin.setInf(inf);
            plugin.setPriority(pluginPriorities.getInt(name, 0));
            synchronized (lock) {
                this.plugins.put(name, plugin);
            }
        } catch (Throwable ex) {
            Canary.logStackTrace("Exception while loading plugin '" + pluginJar + "'", ex);
            return false;
        }

        return true;
    }

    private final boolean load(String jarPath) {
        return load(new File(jarPath));
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

            String[] deps = new String[0];
            if (inf.containsKey("dependencies")) {
                deps = inf.getStringArray("dependencies", ";");
            }

            if (deps == null) {
                Canary.logSevere("There was a problem while fetching " + file.getName() + "'s dependency list.");
                return false;
            }

            if (deps.length == 0) {
                boolean result = load(file.getName(), inf);
                return result;
            } else {
                ArrayList<String> missingDeps = new ArrayList<String>(1);

                for (String dep : deps) {
                    if (!plugins.containsKey(dep) || plugins.get(dep).isDisabled()) {
                        missingDeps.add(dep);
                    }
                }
                if (!missingDeps.isEmpty()) {
                    Canary.logSevere("To reload " + file.getName() + " you need to enable the following plugins first: " + missingDeps.toString());
                    return false;
                }
                return load(file.getName(), inf);
            }
        } catch (Throwable ex) {
            Canary.logStackTrace("Exception while loading plugin", ex);
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
     *            the name of the {@link Plugin}
     * @return {@code true} on success, {@code false} on failure
     */
    public final boolean enablePlugin(String name) {
        Plugin plugin = this.getPlugin(name);
        if (plugin == null) {
            // Plugin is NIL - lets see if we have it on disk
            plugin = unsafeScanForPlugin(name);
        }
        return enablePlugin(this.getPlugin(name));
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
                if (plugin.isClosed()) {
                    if (!testDependencies(plugin)) {
                        return false;
                    }
                }
                else {
                    if (!testDependencies(plugin)) {
                        return false;
                    }
                    enabled = plugin.enable();
                    needNewInstance = false;
                }
            } catch (Throwable t) {
                // If the plugin is in development, they may need to know where something failed.
                Canary.logStackTrace("Could not enable " + plugin.getName(), t);
            }
        }
        if (needNewInstance) {
            try {
                File file = new File(plugin.getJarPath());
                PropertiesFile inf = plugin.getCanaryInf();
                if (testDependencies(plugin)) {
                    masterLoader.addURL(file.toURI().toURL());
                    Class<?> cls = masterLoader.loadClass(plugin.getClass().getName());
                    plugin = (Plugin) cls.newInstance();
                    plugin.setInf(inf);
                    enabled = plugin.enable();
                    plugins.put(plugin.getName(), plugin);
                }
            } catch (Throwable t) {
                // If the plugin is in development, they may need to know where something failed.
                Canary.logStackTrace("Could not enable " + plugin.getName(), t);
            }
        }

        if (enabled) {
            plugin.toggleDisabled();
            Canary.logInfo("Enabled " + plugin.getName() + ", Version " + plugin.getVersion());
        }

        return enabled;
    }

    /**
     * Tests if all dependencies for the Plugin are present and running
     *
     * @param plugin
     *            the Plugin to test dependencies for
     * @return {@code true} if passes; {@code false} otherwise
     */
    private final boolean testDependencies(Plugin plugin) {
        if (!plugin.getCanaryInf().containsKey("dependencies")) {
            return true;
        }

        String[] deps = plugin.getCanaryInf().getStringArray("dependencies", ";");

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

    /**
     * Enables all plugins, used when starting up the server.
     */
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
     *            the name of the {@link Plugin}
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
        try {
            plugin.disable();
        } catch (Throwable t) {
            Canary.logStackTrace("Error while disabling " + plugin.getName(), t);
        }

        /* Remove Registered Listeners */
        Canary.hooks().unregisterPluginListeners(plugin);
        /* Remove Help Content */
        Canary.help().unregisterCommands(plugin);
        /* Remove Commands */
        Canary.commands().unregisterCommands(plugin);

        Canary.logInfo("Disabled " + plugin.getName() + ", Version " + plugin.getVersion());
        return true;
    }

    /**
     * Disables all plugins, used when shutting down the server.
     */
    public final void disableAllPlugins() {
        for (Plugin plugin : plugins.values()) {
            disablePlugin(plugin);
        }
    }

    /**
     * Moves a plugins jar file to the disabled/ folder
     * so it won't be loaded with the next server-start/restart
     *
     * @param name
     * @return
     */
    public final boolean moveToDisabled(String name) {
        Plugin plugin = this.getPlugin(name);
        if (plugin == null) {
            return false;
        }
        try {
            masterLoader.removeURL(new File(plugin.getJarPath()).toURI().toURL());
        } catch (MalformedURLException murlex) {
            Canary.logStackTrace("Failed to remove Plugin path URL from masterLoader", murlex);
        }
        plugins.remove(name);
        pluginPriorities.setInt(name, -1);
        plugin = null;
        return true;
    }

    /**
     * Reload the specified plugin
     *
     * @param name
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

        synchronized (lock) {
            plugins.remove(plugin.getName());
        }

        try {
            masterLoader.removeURL(new File(plugin.getJarPath()).toURI().toURL());
        } catch (MalformedURLException murlex) {
            Canary.logStackTrace("Failed to remove Plugin path URL from masterLoader", murlex);
        }

        plugin.markClosed();
        // Reload the plugin by loading its package again
        boolean test = load(plugin.getJarPath());
        if (test) {
            test = enablePlugin(plugin.getName()); // We have a name, not the new instance. Don't pass the plugin directly.
        }
        return test;
    }

    /**
     * Get the Plugin with specified name.
     *
     * @param name
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
     * The format is: pluginname (X) where X is E(nabled) or D(isabled)
     *
     * @return
     */
    public final String getReadablePluginList() {
        StringBuilder sb = new StringBuilder();

        synchronized (lock) {
            for (Plugin plugin : plugins.values()) {
                if (!plugin.isDisabled()) {
                    sb.append(Colors.LIGHT_GREEN).append(plugin.getName()).append(Colors.WHITE).append(", ");
                } else {
                    sb.append(Colors.LIGHT_RED).append(plugin.getName()).append(Colors.WHITE).append(", ");
                }
            }
        }
        String str = sb.toString();

        if (str.length() > 1) {
            return str.substring(0, str.length() - 1);
        } else {
            return null;
        }
    }

    /**
     * Get a list of plugins for shoeing to the player
     * The format is: pluginname (X) where X is E(nabled) or D(isabled)
     *
     * @return
     */
    public final String getReadablePluginListForConsole() {
        StringBuilder sb = new StringBuilder();

        synchronized (lock) {
            for (Plugin plugin : plugins.values()) {
                if (!plugin.isDisabled()) {
                    sb.append("(E)").append(plugin.getName()).append(",");
                } else {
                    sb.append("(D)").append(plugin.getName()).append(",");
                }
            }
        }
        String str = sb.toString();

        if (str.length() > 1) {
            return str.substring(0, str.length() - 1);
        } else {
            return null;
        }
    }

    /**
     * A node used in solving the dependency tree.
     *
     * @author Jos Kuijpers
     */
    class DependencyNode {

        private String name;
        public ArrayList<DependencyNode> edges;

        DependencyNode(String name) {
            this.name = name;
            this.edges = new ArrayList<DependencyNode>();
        }

        String getName() {
            return this.name;
        }

        void addEdge(DependencyNode node) {
            this.edges.add(node);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            sb.append("<" + this.name + ">(");
            for (DependencyNode node : this.edges) {
                sb.append(node.toString());
                sb.append(",");
            }
            int idx = sb.lastIndexOf(",");
            if (idx != -1)
                sb.deleteCharAt(idx);
            sb.append(")");

            return sb.toString();
        }
    }
}
