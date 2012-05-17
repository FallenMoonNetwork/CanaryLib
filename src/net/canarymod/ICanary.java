package net.canarymod;

import java.util.concurrent.TimeUnit;

import net.canarymod.backbone.IBackbone;
import net.canarymod.bansystem.BanManager;
import net.canarymod.group.GroupsProvider;
import net.canarymod.hook.HookExecutor;
import net.canarymod.kit.KitProvider;
import net.canarymod.plugin.PluginLoader;
import net.canarymod.warp.WarpProvider;
import net.canarymod.config.Configuration;
import net.canarymod.api.IServer;
import net.canarymod.database.IDatabase;

/**
 * The interface to the brains of the bird!
 * AKA Utils
 * @author Chris
 *
 */
public abstract class ICanary {
    
	protected IServer server;
	
    protected BanManager banManager;
    protected GroupsProvider groupsProvider;
    protected WarpProvider warpProvider;
    protected KitProvider kitProvider;
    protected HookExecutor hookExecutor;
    protected Configuration configuration;
    protected IDatabase database;
    protected PluginLoader loader;
    
    /* TODO: Might add .warps() .kits() .hooks() .bans() .conf() .groups() .db()
     * to reduce line size of plugins 
     */
    
    /**
     * Get a backbone
     * @param system
     * @return IBackbone according to system
     */
    public abstract IBackbone getBackbone(IBackbone.System system);
    
    /**
     * Get the Systems BanManager
     * @return
     */
    public BanManager getBanManager() {
        return banManager;
    }
    
    /**
     * Get the groups provider containing all information regarding groups
     * @return
     */
    public GroupsProvider getGroupsProvider() {
        return groupsProvider;
    }
    
    /**
     * Get the warps provider, containing all the information regarding warps
     * @return
     */
    public WarpProvider getWarpProvider() {
        return warpProvider;
    }
    
    /**
     * Get the Kit Provider containing information about kits
     * @return
     */
    public KitProvider getKitProvider() {
        return kitProvider;
    }
    
    /**
     * Get the Hook executor used to e
     * @return
     */
    public HookExecutor getHookExecutor() {
        return hookExecutor;
    }
    
    /**
     * Get the Configuration used to get and set any server and plugin configurations
     * @return
     */
    public Configuration getConfiguration() {
    	return this.configuration;
    }
    
    /**
     * Get the Database used by the backbones and available to plugins
     * @return
     */
    public IDatabase getDatabase() {
    	return this.database;
    }
    
    /**
     * Get the Canary server object
     * @return
     */
    public IServer getServer() {
    	return this.server;
    }
    
    /**
     * Get the plugin loader mechanism
     * @return
     */
    public PluginLoader getLoader() {
    	return this.loader;
    }
    
    /**
     * Get the unix timestamp for the current time
     * @return
     */
    public static long getUnixTimestamp() {
        return (System.currentTimeMillis() / 1000L);
    }
    
    /**
     * Parse number of seconds for the given time and TimeUnit String<br>
     * Example: long 1 String HOUR will give you number of seconds in 1 hour.<br>
     * This is used to work with unix timestamps.
     * @param time
     * @param timeUnit MINUTES, HOURS, DAYS, WEEKS, MONTHS
     * @return
     */
    public static long parseTime(long time, String timeUnit) {

        if (timeUnit.toLowerCase().startsWith("minute")) {
            time *= 60;
        }
        else if (timeUnit.toLowerCase().startsWith("hour")) {
            time *= 3600;
        }
        else if (timeUnit.toLowerCase().startsWith("day")) {
            time *= 86400;
        } 
        else if (timeUnit.toLowerCase().startsWith("week")) {
            time *= 604800;
        }
        else if(timeUnit.toLowerCase().startsWith("month")) {
            time *= 2629743;
        }
        return time;
    }
    
    /**
     * Parse number of seconds for the given time and TimeUnit<br>
     * Example: long 1 String {@link TimeUnit#HOURS} will give you number of seconds in 1 hour.<br>
     * This is used to work with unix timestamps.
     * @param time
     * @param unit
     * @return
     */
    public static long parseTime(long time, TimeUnit unit) {
        return unit.convert(time, TimeUnit.SECONDS);
    }
}
