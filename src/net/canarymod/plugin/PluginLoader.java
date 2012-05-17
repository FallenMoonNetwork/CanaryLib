package net.canarymod.plugin;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.canarymod.config.ConfigurationFile;

public class PluginLoader {

	private static final Logger log = Logger.getLogger("Minecraft");
	private static final Object lock = new Object();
	
	private List<Plugin> plugins;
	private HashMap<String, URLClassLoader> preLoad;
	private HashMap<String, URLClassLoader> postLoad;
//	private HashMap<String, ArrayList<String>> dependencies;
	private List<String> noLoad;
	
	public PluginLoader() {
		this.plugins = new ArrayList<Plugin>();
		this.preLoad = new HashMap<String, URLClassLoader>();
		this.postLoad = new HashMap<String, URLClassLoader>();
		this.noLoad = new ArrayList<String>();
	}
	
	public boolean loadPlugins() {
	
		log.info("CanaryMod: Scanning for plugins...");
		
		File dir = new File("plugins/");
		if(!dir.isDirectory()) {
			log.info("CanaryMod: Failed to scan for plugins. 'plugins/' is not a directory.");
			return false;
		}

		log.info("CanaryMod: Investigating plugins...");
		
		for(String classes : dir.list()) {
			if(!classes.endsWith(".jar"))
				continue;
			this.scan(classes);
		}
		
		log.info("CanaryMod: Loading plugins...");

		for(String name : this.preLoad.keySet()) {
			this.load(name, this.preLoad.get(name));
		}
		
		for(String name : this.postLoad.keySet()) {
			this.load(name, this.postLoad.get(name));
		}
		
		log.info("CanaryMod: Loaded "+this.plugins.size()+" plugins.");
		
		return true;
	}
	
	private boolean scan(String filename) {
		try {
			File file = new File("plugins/"+filename);
			String className = filename.substring(0, filename.indexOf("."));
			URL manifestURL = null;
			ConfigurationFile manifesto;
			
			if(!file.isFile())
				return false;
			
			// Load the jar file
			URLClassLoader jar = null;
			try {
				jar = new CanaryClassLoader(new URL[] {file.toURI().toURL()}, Thread.currentThread().getContextClassLoader());
			}
			catch(MalformedURLException ex) {
				log.log(Level.SEVERE, "Exception while loading jar", ex);
				return false;
			}
			
			// Load file information
			manifestURL = jar.getResource("CANARY.INF");
			if(manifestURL == null) {
				log.severe("CanaryMod: Failed to load plugin '"+className+"': resource CANARY.INF is missing.");
				return false;
			}
			
			// Parse the file
			manifesto = new ConfigurationFile(jar.getResourceAsStream("CANARY.INF"));

			log.info("Found plugin '"+className+"' v"+manifesto.getString("plugin-version","?"));

			// Find the mount-point to determine the load-time
			int mountType = 0; // 0 = no, 1 = pre, 2 = post // reused for dependencies
			String mount = manifesto.getString("mount-point","after");
			if(mount.trim().equalsIgnoreCase("after") || mount.trim().equalsIgnoreCase("post"))
				mountType = 2;
			else if(mount.trim().equalsIgnoreCase("before") || mount.trim().equalsIgnoreCase("pre"))
				mountType = 1;
			else if(mount.trim().equalsIgnoreCase("no-load") || mount.trim().equalsIgnoreCase("none"))
				mountType = 0;
			else {
				log.severe("CanaryMod: Failed to load plugin "+className+": resource CANARY.INF is invalid.");
				return false;
			}
			
			if(mountType == 2)
				this.postLoad.put(className, jar);
			else if(mountType == 1)
				this.preLoad.put(className, jar);
			else if(mountType == 0) { // Do not load, close jar
				this.noLoad.add(className);
				return true;
			}

			// Find dependencies and put them in the dependency order-list
			String sdependencies = manifesto.getString("dependencies","");
			String[] dependencies = sdependencies.split("[,;]");
			for(String dependency : dependencies) {
				dependency = dependency.trim();
				if(dependency == "")
					continue;
				// TODO: implement dependency list building
				log.info("\t depends on "+dependency);
			}
		}
		catch(Throwable ex) {
			log.log(Level.SEVERE, "Exception while scanning plugin", ex);
			return false;
		}
		
		return true;
	}
	
	private boolean load(String pluginName, URLClassLoader jar) {
		try {
			String mainClass = "";
			
			try {
				// Get the path of a known resource
				String infPath = jar.getResource("CANARY.INF").toString();
				// Remove the resource and directly point to the manifest
				String path = infPath.substring(0, infPath.lastIndexOf("!")+1)+"/META-INF/MANIFEST.MF";
				// Get a manifest object
				Manifest manifest = new Manifest(new URL(path).openStream());
				// Get the main Main-Class attribute
				Attributes attr = manifest.getMainAttributes();
				mainClass = attr.getValue("Main-Class");
			}
			catch (IOException e) {
				log.log(Level.SEVERE, "Failed to load manifest of plugin '"+pluginName+"'.",e);
				return false;
			}
			
			if(mainClass == "") {
				log.severe("CanaryMod: Failed to find Manifest in plugin '"+pluginName+"'");
				return false;
			}
			
			Class<?> c = jar.loadClass(mainClass);
            Plugin plugin = (Plugin) c.newInstance();

            synchronized (lock) {
                this.plugins.add(plugin);
                plugin.enable();
            }
		}
		catch(Throwable ex) {
			log.log(Level.SEVERE, "Exception while loading plugin '"+pluginName+"'", ex);
			return false;
		}
		
		return true;
	}

	public Plugin getPlugin(String name) {
        synchronized (lock) {
            for (Plugin plugin : plugins) {
                if (plugin.getName().equalsIgnoreCase(name)) {
                    return plugin;
                }
            }
        }
        
        return null;
	}
	
	public String[] getPluginList() {
		ArrayList<String> list = new ArrayList<String>();
		String[] ret = {};
		
		synchronized(lock) {
			for(Plugin plugin : this.plugins) {
				list.add(plugin.getName());
			}
		}
		
		return list.toArray(ret);
	}
	
	public String getReadablePluginList() {
		StringBuilder sb = new StringBuilder();

		synchronized (lock) {
			for (Plugin plugin : plugins) {
				sb.append(plugin.getName());
				sb.append(" ");
				//sb.append(plugin.isEnabled() ? "(E)" : "(D)");
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
	
	public boolean enablePlugin(String name) {
		Plugin plugin = this.getPlugin(name);
		if(plugin == null)
			return false;
		
		//if(plugin.isEnabled())
		//	return true;
		
		plugin.enable();
		
		return true;
	}

	public boolean disablePlugin(String name) {
		Plugin plugin = this.getPlugin(name);
		if(plugin == null)
			return false;
		
		//if(!plugin.isEnabled())
		//	return true;
		
		plugin.disable();
		
		return true;
	}

}
