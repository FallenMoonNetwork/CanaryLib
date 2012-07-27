package net.canarymod;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import net.canarymod.api.Server;
import net.canarymod.api.entity.Player;
import net.canarymod.bansystem.BanManager;
import net.canarymod.commandsys.CommandManager;
import net.canarymod.config.Configuration;
import net.canarymod.database.Database;
import net.canarymod.database.flatfile.DatabaseFlatfile;
import net.canarymod.help.HelpManager;
import net.canarymod.hook.HookExecutor;
import net.canarymod.kit.KitProvider;
import net.canarymod.permissionsystem.PermissionManager;
import net.canarymod.plugin.PluginLoader;
import net.canarymod.serialize.Serializer;
import net.canarymod.user.Group;
import net.canarymod.user.UserAndGroupsProvider;
import net.canarymod.warp.WarpProvider;

/**
 * The interface to the brains of the bird! AKA Utils
 * 
 * @author Chris Ksoll
 * @author Jos Kuijpers
 * 
 */
public abstract class Canary {

    protected Server server;

    protected BanManager banManager;
    protected UserAndGroupsProvider userAndGroupsProvider;
    protected PermissionManager permissionLoader;
    protected WarpProvider warpProvider;
    protected KitProvider kitProvider;
    protected HookExecutor hookExecutor;
    protected Database database;
    protected PluginLoader loader;
    protected Configuration config;
    protected HelpManager helpManager; // TODO: phase out in favor of CommandManager
    protected CommandManager commandManager;
    
    //Serializer Cache
    HashMap<String, Serializer<?>> serializers = new HashMap<String, Serializer<?>>();
    
    protected static Canary instance;
    
    /**
     * Get the ban System to manage bans
     * @return
     */
    public static BanManager bans() {
        return instance.banManager;
    }
    
    /**
     * Get the Groups provider to manage groups
     * @return
     */
    public static UserAndGroupsProvider usersAndGroups() {
        return instance.userAndGroupsProvider;
    }
    
    /**
     * Get the Warps provider to manage warps and homes
     * @return
     */
    public static WarpProvider warps() {
        return instance.warpProvider;
    }
    
    /**
     * Get the Kit Provider to manage kits
     * @return
     */
    public static KitProvider kits() {
        return instance.kitProvider;
    }
    
    /**
     * Get the Hook executor to fire hooks
     * @return
     */
    public static HookExecutor hooks() {
        return instance.hookExecutor;
    }
    
    /**
     * Get the database interface for managing system data and custom plugin data
     * @return
     */
    public static Database db() {
        return instance.database;
    }
    
    /**
     * Get the Plugin Loader to load, enable or disable plugins and manage 
     * plugin dependencies
     * @return
     */
    public static PluginLoader loader() {
        return instance.loader;
    }
    
    /**
     * Get the permission loader.
     * Note: As plugin author will rarely need to use this.
     * Use the PermissionProviders with Groups and players instead!
     * @return
     */
    public static PermissionManager permissionManager() {
        return instance.permissionLoader;
    }
    
    /**
     * Get the help manager, used to register and unregister help commands, and creating help visualizations
     * @return
     */
    public static HelpManager help() {
        return instance.helpManager;
    }
    
    /**
     * Get the command manager, used to register and unregister commands.
     * @return The current{@link CommandManager} instance.
     */
    public static CommandManager commands() {
        return instance.commandManager;
    }
    
    /**
     * Get the canary instance
     * @return
     */
    public static Canary instance() {
        return instance;
    }
    
    /**
     * Set the canary instance
     * @param canary
     */
    public static void setCanary(Canary canary) {
        instance = canary;
    }

    /**
     * Set the server instance for this Canary
     * @param server
     */
    public static void setServer(Server server) {
        instance.server = server;
    }
    
    /**
     * Get the Server for managing server related stuff
     * @return
     */
    public static Server getServer() {
        return instance.server;
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
    
    /**
     * Glue together a String array to a normal string
     * @param toGlue
     * @param start
     * @param divider The glue between the elements of the array
     * @return
     */
    public static String glueString(String[] toGlue, int start, String divider) {
        if(toGlue == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();

        for (int i = start; i < toGlue.length; i++) {
            if (i != start)
                builder.append(divider);
            builder.append(toGlue[i]);
        }
        return builder.toString();
    }
    
    /**
     * Serialize an object of the given Type T into a String.
     * @param <T>
     * @param object
     * @param type
     * @return serialized String of the object or null if there is no suitable serializer registered
     */
    @SuppressWarnings("unchecked")
    public static <T> String serialize(Object object, String type) {
        Serializer<T> ser = (Serializer<T>) instance.serializers.get(type);
        if(ser != null) {
            return ser.serialize(object);
        }
        return null;
    }
    
    /**
     * Accepts a String with data and the type it should
     * deserialize into.
     * @param data
     * @param Deserialized object of given type or null if there is no suitable serializer registered
     */
    public static Object deserialize(String data, String type) {
        Serializer<?> ser =  instance.serializers.get(type);
        if(ser != null) {
            try {
                return (Object) ser.deserialize(data);
            }
            catch(CanaryDeserializeException e) {
                Logman.logStackTrace("Deserialization failure.", e);
            }
        }
        return null;
    }
    
    /**
     * Add a serializer to the system
     * @param serializer
     * @param type The type this serializer can process
     */
    public static void addSerializer(Serializer<?> serializer, String type) {
        Logman.logInfo("Adding a new Serializer: "+type);
        instance.serializers.put(type, serializer);
    }
    
    /**
     * Reload all subsystems and the whole of canary.
     * 
     * Don't over-use this method, it slows down the server.
     * It is used by the reload command and should not be used by anything else!
     */
    public void reload() {

        // Reload configurations
        Configuration.reload();
        
        // Reload the database if flatfile
        if(instance.database instanceof DatabaseFlatfile) {
            ((DatabaseFlatfile)instance.database).reload();
        }
        
        // Reload all subsystems with a cache
        instance.banManager.reload();
        instance.kitProvider.reload();
        instance.userAndGroupsProvider.reloadAll();
        instance.warpProvider.reload();
        
        //Reload Player permissions
        for(Player p : getServer().getPlayerList()) {
            p.getPermissionProvider().reload();
        }
        
        //Reload group permissiond
        for(Group g : instance.userAndGroupsProvider.getGroups()) {
            g.permissions.reload();
        }
    }
}
