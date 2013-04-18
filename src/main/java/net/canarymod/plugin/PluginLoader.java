package net.canarymod.plugin;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import net.canarymod.Canary;
import net.canarymod.chat.Colors;
import net.visualillusionsent.utils.PropertiesFile;


/**
 * This class loads, reload, enables and disables plugins.
 *
 * @author Jos Kuijpers
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public class PluginLoader {

    private static final Object lock = new Object();

    // Loaded plugins
    private HashMap<Plugin, Boolean> plugins;

    // Plugins that will be loaded before the world
    private HashMap<String, CanaryClassLoader> loaderList;

    // Dependency storage for the pre-load plugins
    private HashMap<String, HashMap<String, Boolean>> dependencies;

    // Solved order to load preload plugins
    private ArrayList<String> loadOrder;

    // Plugin names that won't be loaded
    private ArrayList<String> noLoad;

    // Those are used to determine the main-class of a plugin, if it has none defined.
    // It's highly unreliable but might catch some cases in default-package plugins
    private HashMap<String, String> realJarNames;

    private PropertiesFile pluginPriorities;

    public PluginLoader() {
        this.plugins = new HashMap<Plugin, Boolean>();
        this.loaderList = new HashMap<String, CanaryClassLoader>();
        this.noLoad = new ArrayList<String>();
        this.dependencies = new HashMap<String, HashMap<String, Boolean>>();
        this.realJarNames = new HashMap<String, String>();
        this.pluginPriorities = new PropertiesFile("config/plugin_priorities.cfg");
    }

    /**
     * Scan for plugins: find the plugins and examine them. Then solve the
     * dependency lists
     *
     * @return
     */
    public boolean scanPlugins() {
        File dir = new File("plugins/");
        plugins.clear();
        if (!dir.isDirectory()) {
            Canary.logSevere("Failed to scan for plugins. 'plugins/' is not a directory. Creating...");
            dir.mkdir();
            return false;
        }

        for (String jarfile : dir.list()) {
            if (!jarfile.endsWith(".jar")) {
                continue;
            }
            if (!this.scan(jarfile)) {
                continue;
            }
            this.realJarNames.put(jarfile.substring(0, jarfile.lastIndexOf(".")), jarfile);
        }

        // Solve the dependency tree
        loadOrder = this.solveDependencies(this.dependencies);
        if (loadOrder == null) {
            Canary.logSevere("Failed to solve plugin dependency list.");
            return false;
        }
        return loadPlugins();
    }

    /**
     * Loads the plugins
     */
    private boolean loadPlugins() {
        Canary.logInfo("Finding plugins ...");

        for (String name : this.loadOrder) {
            String rname = this.realJarNames.get(name);
            CanaryClassLoader jar = this.loaderList.get(name);
            this.load(rname, jar);
        }
        this.loaderList.clear();

        Canary.logInfo("Found " + plugins.size() + " plugins.");
        return true;
    }

    private ArrayList<String> fetchDependency(String pluginName, CanaryClassLoader jar) {
        ArrayList<String> dependencies = new ArrayList<String>(1);

        try {
            URLConnection manifestConnection = jar.getResource("Canary.inf").openConnection();

            manifestConnection.setUseCaches(false);
            PropertiesFile manifesto;
            if(pluginName.endsWith(".jar")) {
                manifesto = new PropertiesFile(new File("plugins/" + pluginName).getAbsolutePath(), "Canary.inf");
            }
            else {
                manifesto = new PropertiesFile(new File("plugins/" + pluginName + ".jar").getAbsolutePath(), "Canary.inf");
            }
            String[] deps = manifesto.getString("dependencies", "").split("[ \t]*[,;][ \t]*");

            for (String dependency : deps) {
                dependency = dependency.trim();

                // Remove empty entries
                if (dependency.length() == 0) {
                    continue;
                }
                dependencies.add(dependency);
            }
            return dependencies;

        } catch (Throwable ex) {
            Canary.logStackTrace("Exception while loading plugin", ex);
            return null;
        }
    }

    /**
     * Extract information from the given Jar
     *
     * This information includes the dependencies, mount point and custom priorities.
     *
     * @param filename
     * @return
     */
    private boolean scan(String filename) {
        try {
            File file = new File("plugins/" + filename);
            String jarName = filename.substring(0, filename.lastIndexOf("."));
            PropertiesFile manifesto = new PropertiesFile(file.getAbsolutePath(), "Canary.inf");

            if (!file.isFile()) {
                return false;
            }

            // Load the jar file
            CanaryClassLoader jar = null;

            try {
                jar = new CanaryClassLoader(new URL[] { file.toURI().toURL() }, Thread.currentThread().getContextClassLoader());
            } catch (MalformedURLException ex) {
                Canary.logStackTrace("Exception while loading plugin jar", ex);
                return false;
            }

            // Check if this plugin should be loaded or if it's just a library sort of thing (no-load)
            boolean isLib = manifesto.getBoolean("isLibrary", false);

            if (!isLib) {
                this.loaderList.put(jarName, jar);
            } else {
                this.noLoad.add(jarName);
                return true;
            }

            // Find dependencies and put them in the dependency order-list
            HashMap<String, Boolean> depends = new HashMap<String, Boolean>();

            String[] dependencies = manifesto.getString("dependencies", "").split("[ \t]*[,;][ \t]*");

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

            String[] softDependencies = manifesto.getString("optional-dependencies", "").split("[ \t]*[,;][ \t]*");

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
            this.dependencies.put(jarName, depends);
        } catch (Throwable ex) {
            Canary.logStackTrace("Exception while scanning plugin", ex);
            return false;
        }

        return true;
    }

    /**
     * Loads the plugin but without a class loader. Used for reloading and enabling
     * @param pluginName the case-sensitive name of the plugin
     * @return
     */
    private boolean load(String pluginName) {
        try {
            File file = new File("plugins/" + pluginName);

            if (!file.isFile()) {
                return false;
            }

            // Load the jar file
            CanaryClassLoader jar = null;

            try {
                jar = new CanaryClassLoader(new URL[] { file.toURI().toURL() }, Thread.currentThread().getContextClassLoader());
            } catch (MalformedURLException ex) {
                Canary.logStackTrace("Exception while loading Plugin jar", ex);
                return false;
            }
            ArrayList<String> deps = fetchDependency(pluginName, jar);

            if (deps == null) {
                Canary.logSevere("There was a problem while fetching" + pluginName + "'s dependency list.");
                return false;
            }

            if (deps.isEmpty()) {
                boolean result = load(pluginName, jar);
                return result;
            } else {
                ArrayList<String> missingDeps = new ArrayList<String>(1);

                for (String dep : deps) {
                    if (getPlugin(dep) == null) {
                        missingDeps.add(dep);
                    }
                }
                if (!missingDeps.isEmpty()) {
                    Canary.logSevere("To reload " + pluginName + " you need to enable the following plugins first: " + missingDeps.toString());
                    return false;
                }
                return load(pluginName, jar);
            }
        } catch (Throwable ex) {
            Canary.logStackTrace("Exception while loading plugin", ex);
            return false;
        }
    }

    /**
     * The class loader
     *The pluginName should come as full file name with file extension
     * @param pluginName
     * @param jar
     * @return
     */
    private boolean load(String pluginName, CanaryClassLoader jar) {
        try {
            String mainClass = "";
            PropertiesFile manifesto = new PropertiesFile(new File("plugins/" + pluginName).getAbsolutePath(), "Canary.inf");

            // Get the main class, or use the plugin name as class
            if (!manifesto.containsKey("main-class")) {
                Canary.logSevere("Failed to read main-class for '" + pluginName + "' in Canary.inf Please specify a main-class entry in Canary.inf");
                return false;
            }
            mainClass = manifesto.getString("main-class");

            Class<?> c = jar.loadClass(mainClass);
            Plugin plugin = (Plugin) c.newInstance();

            plugin.setLoader(jar, manifesto, pluginName);
            String fileName = pluginName.substring(0, pluginName.lastIndexOf("."));

            if(!pluginPriorities.containsKey(fileName)) {
                pluginPriorities.setInt(fileName, plugins.size());
                pluginPriorities.save();
            }
            plugin.setPriority(pluginPriorities.getInt(fileName));
            synchronized (lock) {
                this.plugins.put(plugin, false);
            }
        } catch (Throwable ex) {
            Canary.logStackTrace("Exception while loading plugin '" + pluginName + "' (Canary.inf missing?)", ex);
            return false;
        }

        return true;
    }

    /**
     * Start solving the dependency list given.
     *
     * @param pluginDependencies
     * @return
     */
    private ArrayList<String> solveDependencies(HashMap<String, HashMap<String, Boolean>> pluginDependencies) {
        // http://www.electricmonk.nl/log/2008/08/07/dependency-resolving-algorithm/

        if (pluginDependencies.size() == 0) {
            return new ArrayList<String>();
        }

        ArrayList<String> retOrder = new ArrayList<String>();
        HashMap<String, DependencyNode> graph = new HashMap<String, DependencyNode>();

        // Create the node list
        for (String name : pluginDependencies.keySet()) {
            graph.put(name, new DependencyNode(name));
        }

        // Add dependency nodes to the nodes
        ArrayList<String> isDependency = new ArrayList<String>();

        for (String pluginName : pluginDependencies.keySet()) {
            DependencyNode node = graph.get(pluginName);

            for (String depName : pluginDependencies.get(pluginName).keySet()) {
                if (!graph.containsKey(depName)) {
                    // If the dependency is already loaded omit error
                    if (loaderList.containsKey(depName)) {
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

        ArrayList<DependencyNode> resolved = new ArrayList<DependencyNode>();

        for (String n : graph.keySet()) {
            this.depResolve(graph.get(n), resolved);
        }

        for (DependencyNode x : resolved) {
            retOrder.add(x.getName());
        }

        return retOrder;
    }

    /**
     * This recursive method actually solves the dependency lists
     *
     * @param node
     * @param resolved
     */
    private void depResolve(DependencyNode node, ArrayList<DependencyNode> resolved) {
        for (DependencyNode edge : node.edges) {
            if (!resolved.contains(edge)) {
                this.depResolve(edge, resolved);
            }
        }
        resolved.add(node);
    }

    /**
     * Get the Plugin with specified name.
     * @param name
     * @return The plugin for the given name, or null on failure.
     */
    public Plugin getPlugin(String name) {
        synchronized (lock) {
            for (Plugin plugin : plugins.keySet()) {
                if (plugin.getName().equalsIgnoreCase(name)) {
                    return plugin;
                }
            }
        }
        return null;
    }

    /**
     * Get a list of plugin-names
     * @return
     */
    public String[] getPluginList() {
        ArrayList<String> list = new ArrayList<String>();
        String[] ret = {};

        synchronized (lock) {
            for (Plugin plugin : this.plugins.keySet()) {
                list.add(plugin.getName());
            }
        }

        return list.toArray(ret);
    }

    /**
     * Get a list of plugins for shoeing to the player
     *
     * The format is: pluginname (X) where X is E(nabled) or D(isabled)
     * @return
     */
    public String getReadablePluginList() {
        StringBuilder sb = new StringBuilder();

        synchronized (lock) {
            for (Plugin plugin : plugins.keySet()) {
                if (plugins.get(plugin)) {
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
    public String getReadablePluginListForConsole() {
        StringBuilder sb = new StringBuilder();

        synchronized (lock) {
            for (Plugin plugin : plugins.keySet()) {
                if (plugins.get(plugin)) {
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
     * Enables the given plugin. Loads the plugin if not loaded (and available)
     *
     * @param name
     *            the name of the {@link Plugin}
     * @return {@code true} on success, {@code false} on failure
     */
    public boolean enablePlugin(String name) {
        return enablePlugin(this.getPlugin(name));
    }

    /* Same as public boolean enablePlugin(String name) */
    private boolean enablePlugin(Plugin plugin) {
        if (plugin == null) {
            return false;
        }

        // The plugin must be disabled to enable
        if (plugins.get(plugin) == true) {
            return true; // already enabled
        }

        // Set the plugin as enabled and send enable message
        boolean enabled = false;
        try {
            enabled = plugin.enable();
        } catch (Throwable t) {
            // If the plugin is in development, they may need to know where something failed.
            Canary.logStackTrace("Could not enable " + plugin.getName(), t);
        }

        if(!enabled) {
            plugins.put(plugin, enabled);
            disablePlugin(plugin, true);
        }
        plugins.put(plugin, enabled);
        return enabled;
    }

    /**
     * Disables the given plugin
     *
     * @param name
     *            the name of the {@link Plugin}
     * @return {@code true} on success, {@code false} on failure
     */
    public boolean disablePlugin(String name) {
        return disablePlugin(this.getPlugin(name), false);
    }

    /* Same as public boolean disablePlugin(String name) */
    private boolean disablePlugin(Plugin plugin, boolean force) {
        // Plugin must exist before disabling
        if (plugin == null) {
            return false;
        }

        // Plugin must also be enabled to disable
        if (plugins.get(plugin) == false && !force) {
            return true; // already disabled
        }

        // Set the plugin as disabled, and send disable message
        plugins.put(plugin, false);
        try {
            plugin.disable();
        }
        catch (Throwable t){
            Canary.logStackTrace("Error while disabling " + plugin.getName(), t);
        }
        //remove registered listeners
        Canary.hooks().unregisterPluginListeners(plugin);
        // Remove all its help and command content
        Canary.help().unregisterCommands(plugin);
        Canary.commands().unregisterCommands(plugin);
        plugin.getLoader().close();
        return true;
    }

    /**
     * Enables all plugins, used when starting up the server.
     */
    public void enableAllPlugins() {
        int enabled = 0;
        for (Plugin plugin : plugins.keySet()) {
            if (enablePlugin(plugin)) {
                enabled++;
            }
        }
        Canary.logInfo("Enabled " + enabled + " plugins.");
    }

    /**
     * Disables all plugins, used when shutting down the server.
     */
    public void disableAllPlugins() {
        for (Plugin plugin : plugins.keySet()) {
            disablePlugin(plugin, true);
        }
    }

    /**
     * Reload the specified plugin
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
        disablePlugin(plugin, true);

        synchronized (lock) {
            // Remove the plugin and unregister the listeners
            Canary.hooks().unregisterPluginListeners(plugin);
            // Remove all its help and command content
            Canary.commands().unregisterCommands(plugin);
            plugins.remove(plugin);
        }
        plugin.getLoader().close();
        // Reload the plugin by loading its package again
        boolean test = load(plugin.getJarName());
        if (test) {
            test = enablePlugin(plugin.getName()); // We have a name, not the new instance. Don't pass the plugin directly.
        }
        return test;
    }

    /**
     * Moves a plugins jar file to the disabled/ folder
     * so it won't be loaded with the next server-start/restart
     * @param name
     * @return
     */
    public boolean moveToDisabled(String name) {
        Plugin plugin = this.getPlugin(name);
        if(plugin == null) {
            return false;
        }
        plugin.getLoader().close();
        plugins.remove(plugin);
        String jarName = plugin.getJarName();
        plugin = null;
        File f = new File("plugins/" + jarName + ".jar");
        return f.renameTo(new File("plugins/" + jarName + ".jar.disabled"));
    }

    /**
     * A node used in solving the dependency tree.
     *
     * @author Jos Kuijpers
     *
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
