package net.canarymod;

import java.util.concurrent.TimeUnit;

import net.canarymod.api.Server;
import net.canarymod.backbone.Backbone;
import net.canarymod.bansystem.BanManager;
import net.canarymod.config.Configuration;
import net.canarymod.database.Database;
import net.canarymod.group.GroupsProvider;
import net.canarymod.hook.HookExecutor;
import net.canarymod.kit.KitProvider;
import net.canarymod.plugin.PluginLoader;
import net.canarymod.warp.WarpProvider;

/**
 * The interface to the brains of the bird! AKA Utils
 * 
 * @author Chris
 * 
 */
public abstract class Canary {

    protected Server server;

    protected BanManager banManager;
    protected GroupsProvider groupsProvider;
    protected WarpProvider warpProvider;
    protected KitProvider kitProvider;
    protected HookExecutor hookExecutor;
    protected Configuration configuration;
    protected Database database;
    protected PluginLoader loader;
    
    protected static Canary instance;

    /*
     * TODO: Might add .warps() .kits() .hooks() .bans() .conf() .groups() .db()
     * to reduce line size of plugins
     */
    
    public static Canary get() {
        return instance;
    }
    
    public static void setCanary(Canary canary) {
        instance = canary;
    }

    /**
     * Set the server instance for this Canary
     * @param server
     */
    public void setServer(Server server) {
        this.server = server;
    }
    
    public Server getServer() {
        return server;
    }
    
    
    /**
     * Get a backbone
     * 
     * @param system
     * @return IBackbone according to system
     */
    public abstract Backbone getBackbone(Backbone.System system);

    /**
     * Get the Systems BanManager
     * 
     * @return
     */
    public BanManager getBanManager() {
        return banManager;
    }

    /**
     * Get the groups provider containing all information regarding groups
     * 
     * @return
     */
    public GroupsProvider getGroupsProvider() {
        return groupsProvider;
    }

    /**
     * Get the warps provider, containing all the information regarding warps
     * 
     * @return
     */
    public WarpProvider getWarpProvider() {
        return warpProvider;
    }

    /**
     * Get the Kit Provider containing information about kits
     * 
     * @return
     */
    public KitProvider getKitProvider() {
        return kitProvider;
    }

    /**
     * Get the Hook executor used to e
     * 
     * @return
     */
    public HookExecutor getHookExecutor() {
        return hookExecutor;
    }

    /**
     * Get the Configuration used to get and set any server and plugin
     * configurations
     * 
     * @return
     */
    public Configuration getConfiguration() {
        return this.configuration;
    }

    /**
     * Get the Database used by the backbones and available to plugins
     * 
     * @return
     */
    public Database getDatabase() {
        return this.database;
    }

    /**
     * Get the plugin loader mechanism
     * 
     * @return
     */
    public PluginLoader getPluginLoader() {
        return this.loader;
    }

    /**
     * Get the unix timestamp for the current time
     * 
     * @return
     */
    public static long getUnixTimestamp() {
        return (System.currentTimeMillis() / 1000L);
    }

    /**
     * Parse number of seconds for the given time and TimeUnit String<br>
     * Example: long 1 String HOUR will give you number of seconds in 1 hour.<br>
     * This is used to work with unix timestamps.
     * 
     * @param time
     * @param timeUnit
     *            MINUTES, HOURS, DAYS, WEEKS, MONTHS
     * @return
     */
    public static long parseTime(long time, String timeUnit) {

        if (timeUnit.toLowerCase().startsWith("minute")) {
            time *= 60;
        } else if (timeUnit.toLowerCase().startsWith("hour")) {
            time *= 3600;
        } else if (timeUnit.toLowerCase().startsWith("day")) {
            time *= 86400;
        } else if (timeUnit.toLowerCase().startsWith("week")) {
            time *= 604800;
        } else if (timeUnit.toLowerCase().startsWith("month")) {
            time *= 2629743;
        }
        return time;
    }

    /**
     * Parse number of seconds for the given time and TimeUnit<br>
     * Example: long 1 String {@link TimeUnit#HOURS} will give you number of
     * seconds in 1 hour.<br>
     * This is used to work with unix timestamps.
     * 
     * @param time
     * @param unit
     * @return
     */
    public static long parseTime(long time, TimeUnit unit) {
        return unit.convert(time, TimeUnit.SECONDS);
    }
}
