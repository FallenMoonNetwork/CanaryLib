package net.canarymod.plugin;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;

import net.canarymod.Canary;
import net.canarymod.Logman;
import net.canarymod.config.ConfigurationFile;

/**
 * This class loads, reload, enables and disables plugins.
 * 
 * @author Jos Kuijpers
 */
public class PluginLoader {

    private static final Object lock = new Object();

    // Loaded plugins
    private HashMap<Plugin, Boolean> plugins;

    // Plugins that will be loaded before the world
    private HashMap<String, URLClassLoader> preLoad;
    // Dependency storage for the pre-load plugins
    private HashMap<String, ArrayList<String>> preLoadDependencies;
    // Solved order to load preload plugins
    private ArrayList<String> preOrder;

    // Plugins that will be loaded after the world
    private HashMap<String, URLClassLoader> postLoad;
    // Dependency storage for the post-load plugins
    private HashMap<String, ArrayList<String>> postLoadDependencies;
    // Solved order to load postload plugins
    private ArrayList<String> postOrder;

    // Plugin names that won't be loaded
    private ArrayList<String> noLoad;

    private HashMap<String, String> casedNames;

    private int stage = 0; // 0 none, 1 scanned, 2 pre, 3 pre+post

    public PluginLoader() {
        this.plugins = new HashMap<Plugin, Boolean>();
        this.preLoad = new HashMap<String, URLClassLoader>();
        this.postLoad = new HashMap<String, URLClassLoader>();
        this.noLoad = new ArrayList<String>();
        this.preLoadDependencies = new HashMap<String, ArrayList<String>>();
        this.postLoadDependencies = new HashMap<String, ArrayList<String>>();
        this.casedNames = new HashMap<String, String>();
        
        File dir = new File("plugins/disabled/");
        if(!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * Scan for plugins: find the plugins and examine them. Then solve the
     * dependency lists
     * 
     * @return
     */
    public boolean scanPlugins() {
        // We can't do a rescan this way because it needs a reload 
        // of the plugins (AFAIK)
        if (stage != 0) return false;

        File dir = new File("plugins/");
        if (!dir.isDirectory()) {
        	Logman.logSevere("Failed to scan for plugins. 'plugins/' is not a directory.");
            return false;
        }

        for (String classes : dir.list()) {
            if (!classes.endsWith(".jar")) continue;
            if (!this.scan(classes)) continue;
            String sname = classes.toLowerCase();
            this.casedNames.put(sname.substring(0, sname.lastIndexOf(".")), classes);
        }

        // Solve the dependency tree

        preOrder = this.solveDependencies(this.preLoadDependencies);
        if (preOrder == null) {
            Logman.logSevere("Failed to solve preload dependency list.");
            return false;
        }

        postOrder = this.solveDependencies(this.postLoadDependencies);
        if (postOrder == null) {
            Logman.logSevere("Failed to solve postload dependency list.");
            return false;
        }

        // Change the stage
        stage = 1;

        return true;
    }

    /**
     * Loads the plugins for pre or post load
     * 
     * @param preLoad
     */
    public boolean loadPlugins(boolean preLoad) {
        if ((preLoad && stage != 1) || stage == 3) return false;
        Logman.logInfo("Loading " + ((preLoad) ? "preloadable " : "") + "plugins...");
        if (preLoad) {
            for (String name : this.preOrder) {
                String rname = this.casedNames.get(name);
                this.load(rname.substring(0, rname.lastIndexOf(".")), this.preLoad.get(name));
            }
            this.preLoad.clear();
        } else {
            for (String name : this.postOrder) {
                String rname = this.casedNames.get(name);
                this.load(rname.substring(0, rname.lastIndexOf(".")), this.postLoad.get(name));
            }
            this.postLoad.clear();
        }

        Logman.logInfo("Loaded all " + ((preLoad) ? "preloadable " : "") + "plugins.");

        // Prevent a double-load (which makes the server crash)
        stage++;

        return true;
    }

    /**
     * Extract information from the given Jar
     * 
     * This information includes the dependencies and mount point
     * 
     * @param filename
     * @return
     */
    private boolean scan(String filename) {
        try {
            File file = new File("plugins/" + filename);
            String jarName = filename.substring(0, filename.indexOf("."));
            URL manifestURL = null;
            ConfigurationFile manifesto;

            if (!file.isFile()) return false;

            // Load the jar file
            URLClassLoader jar = null;
            try {
                jar = new CanaryClassLoader(new URL[] { file.toURI().toURL() }, Thread.currentThread().getContextClassLoader());
            } catch (MalformedURLException ex) {
                Logman.logStackTrace("Exception while loading plugin jar", ex);
                return false;
            }

            // Load file information
            manifestURL = jar.getResource("CANARY.INF");
            if (manifestURL == null) {
                Logman.logSevere("Failed to load plugin '" + jarName + "': resource CANARY.INF is missing.");
                return false;
            }

            // Parse the file
            manifesto = new ConfigurationFile(jar.getResourceAsStream("CANARY.INF"));
            if(!manifesto.exists()) {
                Logman.logSevere("Failed to load CANARY.INF of plugin '" + jarName + "'.");
                return false;
            }

            // Find the mount-point to determine the load-time
            int mountType = 0; // 0 = no, 1 = pre, 2 = post // reused for dependencies
            String mount = manifesto.getString("mount-point", "after");
            if (mount.trim().equalsIgnoreCase("after") || mount.trim().equalsIgnoreCase("post")) mountType = 2;
            else if (mount.trim().equalsIgnoreCase("before") || mount.trim().equalsIgnoreCase("pre")) mountType = 1;
            else if (mount.trim().equalsIgnoreCase("no-load") || mount.trim().equalsIgnoreCase("none")) mountType = 0;
            else {
                Logman.logSevere("Failed to load plugin " + jarName + ": resource CANARY.INF is invalid.");
                return false;
            }

            if (mountType == 2) {
                this.postLoad.put(jarName.toLowerCase(), jar);
            }
            else if (mountType == 1) {
                this.preLoad.put(jarName.toLowerCase(), jar);
            }
            else if (mountType == 0) { // Do not load, close jar
                this.noLoad.add(jarName.toLowerCase());
                return true;
            }

            // Find dependencies and put them in the dependency order-list
            String[] dependencies = manifesto.getString("dependencies", "").split("[ \t]*[,;][ \t]*");
            ArrayList<String> depends = new ArrayList<String>();
            for (String dependency : dependencies) {
                dependency = dependency.trim();

                // Remove empty entries
                if (dependency.length() == 0) continue;

                // Remove duplicates
                if (depends.contains(dependency.toLowerCase())) continue;

                depends.add(dependency.toLowerCase());
            }
            if (mountType == 2) // post
                this.postLoadDependencies.put(jarName.toLowerCase(), depends);
            else if (mountType == 1) // pre
                this.preLoadDependencies.put(jarName.toLowerCase(), depends);
        } catch (Throwable ex) {
            Logman.logStackTrace("Exception while scanning plugin", ex);
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
            File file = new File("plugins/" + pluginName + ".jar");
            if (!file.isFile()) return false;

            // Load the jar file
            URLClassLoader jar = null;
            try {
                jar = new CanaryClassLoader(new URL[] { file.toURI().toURL() }, Thread.currentThread().getContextClassLoader());
            } catch (MalformedURLException ex) {
                Logman.logStackTrace("Exception while loading Plugin jar", ex);
                return false;
            }
            
            return load(pluginName, jar);
        } catch (Throwable ex) {
            Logman.logStackTrace("Exception while loading plugin", ex);
            return false;
        }
    }
    
    /**
     * The class loader
     * 
     * @param pluginName
     * @param jar
     * @return
     */
    private boolean load(String pluginName, URLClassLoader jar) {
        try {
            String mainClass = "";
        	ConfigurationFile manifesto;
        	
        	// TODO: cache the object instead?
        	// Load the configuration file again
        	manifesto = new ConfigurationFile(jar.getResourceAsStream("CANARY.INF"));

            if(!manifesto.exists()) {
                Logman.logSevere("Failed to find CANARY.INF of plugin '" + pluginName + "'.");
                return false;
            }
        	
        	// Get the main class, or use the plugin name as class
            mainClass = manifesto.getString("main-class", pluginName);
            if (mainClass.compareTo("") == 0) {
                Logman.logSevere("Failed to load CANARY.INF in plugin '" + pluginName + "'");
                return false;
            }

            Class<?> c = jar.loadClass(mainClass);
            Plugin plugin = (Plugin) c.newInstance();
            plugin.setName(pluginName);
            
            synchronized (lock) {
                this.plugins.put(plugin,true);
            }
            plugin.enable();
        } catch (Throwable ex) {
            Logman.logStackTrace("Exception while loading plugin '" + pluginName + "'", ex);
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
    private ArrayList<String> solveDependencies(HashMap<String, ArrayList<String>> pluginDependencies) {
        // http://www.electricmonk.nl/log/2008/08/07/dependency-resolving-algorithm/

        if (pluginDependencies.size() == 0) return new ArrayList<String>();

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
            for (String depName : pluginDependencies.get(pluginName)) {
                if (!graph.containsKey(depName)) {
                    // If the dependency is in the preload, it is already loaded. Omit it
                    if(preLoad.containsKey(depName)) {
                        continue;
                    }
                    // Dependency does not exist, lets happily fail
                    Logman.logSevere("Failed to solve dependency '" + depName + "' for '" + pluginName + "'. The plugin will not be loaded.");
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
            Logman.logWarning("Failed to solve dependency graph. Is there a circular dependency?");
            return null;
        }

        // The graph now contains elements that either have edges or are lonely

        ArrayList<DependencyNode> resolved = new ArrayList<DependencyNode>();
        for (String n : graph.keySet()) {

            this.depResolve(graph.get(n), resolved);
        }

        for (DependencyNode x : resolved)
            retOrder.add(x.getName());

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
            if (!resolved.contains(edge)) this.depResolve(edge, resolved);
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
     * Get a list of plugins usable to show a player.
     * 
     * The format is: pluginname (X) where X is E(nabled) or D(isabled)
     * @return
     */
    public String getReadablePluginList() {
        StringBuilder sb = new StringBuilder();

        synchronized (lock) {
            for (Plugin plugin : plugins.keySet()) {
                sb.append(plugin.getName());
                sb.append(" ");
                sb.append((plugins.get(plugin) == true)?"(E)":"(D)");
                sb.append(",");
            }
        }
        String str = sb.toString();

        if (str.length() > 1) {
            return str.substring(0, str.length() - 1);
        } else {
            return "Empty";
        }
    }

    /**
     * Enables the given plugin. Loads the plugin if not loaded (and available)
     * @param name
     * @return true on success, false on failure
     */
    public boolean enablePlugin(String name) {
        Plugin plugin = this.getPlugin(name);
        
        // If the plugin does not exist, try to load it instead
        if (plugin == null) {
            if(!load(name)) {
                return false;
            }
            return true;
        }

        // The plugin must be disabled to enable
        if(plugins.get(plugin) == true) {
        	return true; // already enabled
        }

        // Set the plugin as enabled and send enable message
        plugins.put(plugin, true);
        plugin.enable();

        return true;
    }

    /**
     * Disables the given plugin
     * @param name
     * @return true on success, false on failure
     */
    public boolean disablePlugin(String name) {
        Plugin plugin = this.getPlugin(name);
        
        // Plugin must exist before disabling
        if (plugin == null) {
        	return false;
        }

        // Plugin must also be enabled to disable
        if(plugins.get(plugin) == false) {
        	return true; // already disabled
        }
        
        // Set the plugin as disabled, and send disable message
        plugins.put(plugin, false);
        plugin.disable();

        return true;
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
            return false;
        }
        
        // Disable the plugin
        plugin.disable();
        
        synchronized(lock) {
            // Remove the plugin and unregister the listeners
            Canary.hooks().unregisterPluginListeners(plugin);
            plugins.remove(plugin);
        }
        
        // TODO rescanning for CANARY.INF changes? If dependencies cant be resolved, don't load
        
        // Reload the plugin by loading its package again
        return load(name);
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

        /* Debugging only
        public String toString() {
            StringBuilder sb = new StringBuilder();

            sb.append("<" + this.name + ">(");
            for (DependencyNode node : this.edges) {
                sb.append(node.toString());
                sb.append(",");
            }
            int idx = sb.lastIndexOf(",");
            if (idx != -1) sb.deleteCharAt(idx);
            sb.append(")");

            return sb.toString();
        }*/
    }
    
    /**
     * Class loader used to load classes dynamically. This also closes the jar so we
     * can reload the plugin.
     * 
     * @author James
     * 
     */
    class CanaryClassLoader extends URLClassLoader {

        public CanaryClassLoader(URL[] urls, ClassLoader loader) {
            super(urls, loader);
        }

        @SuppressWarnings("rawtypes")
        public void close() {
            try {
                Class<?> clazz = java.net.URLClassLoader.class;
                java.lang.reflect.Field ucp = clazz.getDeclaredField("ucp");

                ucp.setAccessible(true);
                Object sun_misc_URLClassPath = ucp.get(this);
                java.lang.reflect.Field loaders = sun_misc_URLClassPath.getClass().getDeclaredField("loaders");

                loaders.setAccessible(true);
                Object java_util_Collection = loaders.get(sun_misc_URLClassPath);

                for (Object sun_misc_URLClassPath_JarLoader : ((java.util.Collection) java_util_Collection).toArray()) {
                    try {
                        java.lang.reflect.Field loader = sun_misc_URLClassPath_JarLoader.getClass().getDeclaredField("jar");

                        loader.setAccessible(true);
                        Object java_util_jar_JarFile = loader.get(sun_misc_URLClassPath_JarLoader);

                        ((java.util.jar.JarFile) java_util_jar_JarFile).close();
                    } catch (Throwable t) {
                        // if we got this far, this is probably not a JAR loader so
                        // skip it
                    }
                }
            } catch (Throwable t) {
                // Probably not a Sun (correct: Oracle) VM.
            }
            return;
        }
    }
}
