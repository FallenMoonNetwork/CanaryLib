package net.canarymod;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.factory.Factory;
import net.canarymod.bansystem.BanManager;
import net.canarymod.channels.ChannelManager;
import net.canarymod.commandsys.CommandManager;
import net.canarymod.config.Configuration;
import net.canarymod.database.Database;
import net.canarymod.help.HelpManager;
import net.canarymod.hook.HookExecutor;
import net.canarymod.kit.KitProvider;
import net.canarymod.logger.CanaryLevel;
import net.canarymod.logger.Logman;
import net.canarymod.permissionsystem.PermissionManager;
import net.canarymod.plugin.PluginLoader;
import net.canarymod.serialize.Serializer;
import net.canarymod.tasks.TaskOwner;
import net.canarymod.user.OperatorsProvider;
import net.canarymod.user.ReservelistProvider;
import net.canarymod.user.UserAndGroupsProvider;
import net.canarymod.user.WhitelistProvider;
import net.canarymod.warp.WarpProvider;

/**
 * The interface to the brains of the bird! AKA Utils
 * 
 * @author Chris (damagefilter)
 * @author Jos Kuijpers
 * @author Brian (WWOL)
 */
public abstract class Canary implements TaskOwner {
    final private static Logman logger;
    private static boolean pluginsUp;
    protected Server server;

    protected BanManager banManager;
    protected UserAndGroupsProvider userAndGroupsProvider;
    protected PermissionManager permissionLoader;
    protected WarpProvider warpProvider;
    protected KitProvider kitProvider;
    protected WhitelistProvider whitelist;
    protected OperatorsProvider ops;
    protected ReservelistProvider reservelist;
    protected HookExecutor hookExecutor;
    protected Database database;
    protected PluginLoader loader;
    protected Configuration config;
    protected HelpManager helpManager; // TODO: phase out in favor of CommandManager
    protected CommandManager commandManager;
    protected Factory factory;
    protected ChannelManager channelManager;

    // Serializer Cache
    HashMap<Class<?>, Serializer<?>> serializers = new HashMap<Class<?>, Serializer<?>>();

    protected static Canary instance;

    static {
        logger = Logman.getLogman("CanaryMod");
    }

    /**
     * Get the ban System to manage bans
     * 
     * @return {@link BanManager}
     */
    public static BanManager bans() {
        return instance.banManager;
    }

    /**
     * Get the Groups provider to manage groups
     * 
     * @return {@link UserAndGroupsProvider}
     */
    public static UserAndGroupsProvider usersAndGroups() {
        return instance.userAndGroupsProvider;
    }

    /**
     * Get the Warps provider to manage warps and homes
     * 
     * @return {@link WarpProvider}
     */
    public static WarpProvider warps() {
        return instance.warpProvider;
    }

    /**
     * Get the Kit Provider to manage kits
     * 
     * @return {@link KitProvider}
     */
    public static KitProvider kits() {
        return instance.kitProvider;
    }

    /**
     * Get the whitelist provider for managing the whitelist
     * 
     * @return {@link WhitelistProvider}
     */
    public static WhitelistProvider whitelist() {
        return instance.whitelist;
    }

    /**
     * Get the operators provider for managing the ops
     * 
     * @return {@link OperatorsProvider}
     */
    public static OperatorsProvider ops() {
        return instance.ops;
    }

    /**
     * Get the reservelist provider for managing the reservelist
     * 
     * @return {@link ReservelistProvider}
     */
    public static ReservelistProvider reservelist() {
        return instance.reservelist;
    }

    /**
     * Get the Hook executor to fire hooks
     * 
     * @return {@link HookExecutor}
     */
    public static HookExecutor hooks() {
        return instance.hookExecutor;
    }

    /**
     * Get the database interface for managing system data and custom plugin data
     * 
     * @return {@link Database}
     */
    public static Database db() {
        return instance.database;
    }

    /**
     * Get the Plugin Loader to load, enable or disable plugins and manage
     * plugin dependencies
     * 
     * @return {@link PluginLoader}
     */
    public static PluginLoader loader() {
        return instance.loader;
    }

    /**
     * Get the permission loader.
     * Note: As plugin author will rarely need to use this.
     * Use the PermissionProviders with Groups and players instead!
     * 
     * @return {@link PermissionManager}
     */
    public static PermissionManager permissionManager() {
        return instance.permissionLoader;
    }

    /**
     * Get the help manager, used to register and unregister help commands, and creating help visualizations
     * 
     * @return {@link HelpManager}
     */
    public static HelpManager help() {
        return instance.helpManager;
    }

    /**
     * Get the command manager, used to register and unregister commands.
     * 
     * @return The current {@link CommandManager} instance.
     */
    public static CommandManager commands() {
        return instance.commandManager;
    }

    /**
     * Gets the {@link Factory} manager
     * 
     * @return {@link Factory}
     */
    public static Factory factory() {
        return instance.factory;
    }

    /**
     * Gets the {@link ChannelManager}
     * 
     * @return {@link ChannelManager}
     */
    public static ChannelManager channels() {
        return instance.channelManager;
    }

    /**
     * Get the canary instance
     * 
     * @return {@link Canary}
     */
    public static Canary instance() {
        return instance;
    }

    /**
     * Set the canary instance
     * 
     * @param canary
     *            the {@link Canary} instance
     */
    public static void setCanary(Canary canary) {
        if (instance == null) {
            instance = canary;
        }
    }

    /**
     * Set the server instance for this Canary
     * 
     * @param server
     *            the {@link Server} instance
     */
    public static void setServer(Server server) {
        instance.server = server;
    }

    /**
     * Enables all plugins
     */
    public static void enablePlugins() {
        if (!pluginsUp && instance.server != null) {
            logInfo("Enabling Plugins...");
            loader().enableAllPlugins();
            pluginsUp = true;
        }
    }

    /**
     * Get the Server for managing server related stuff
     * 
     * @return {@link Server}
     */
    public static Server getServer() {
        return instance.server;
    }

    /**
     * Get the Unix timestamp for the current time
     * 
     * @return {@code long} timestamp
     */
    public static long getUnixTimestamp() {
        return (System.currentTimeMillis() / 1000L);
    }

    /**
     * Parse number of seconds for the given time and TimeUnit String<br>
     * Example: long 1 String HOUR will give you number of seconds in 1 hour.<br>
     * This is used to work with Unix timestamps.
     * 
     * @param time
     *            the {@code long} time
     * @param timeUnit
     *            MINUTES, HOURS, DAYS, WEEKS, MONTHS
     * @return {@code long} parsed time
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
        else {
            throw new NumberFormatException(timeUnit + " is not a valid time unit!");
        }
        return time;
    }

    /**
     * Formats a Unix timestamp into the date format defined in server.cfg
     * 
     * @param timestamp
     *            the {@code long} time
     * @return {@link String} formatted TimeStamp
     */
    public static String formatTimestamp(long timestamp) {
        return new SimpleDateFormat(Configuration.getServerConfig().getDateFormat()).format(timestamp);
    }

    /**
     * Parse number of seconds for the given time and TimeUnit<br>
     * Example: long 1 String {@link TimeUnit#HOURS} will give you number of
     * seconds in 1 hour.<br>
     * This is used to work with unix timestamps.
     * 
     * @param time
     *            the {@code long} time
     * @param unit
     *            the {@link TimeUnit} to use for conversion
     * @return {@code long} parsed time
     */
    public static long parseTime(long time, TimeUnit unit) {
        return unit.convert(time, TimeUnit.SECONDS);
    }

    /**
     * Glue together a String array to a normal string
     * 
     * @param toGlue
     *            the {@code String[]}
     * @param start
     *            this starting index
     * @param divider
     *            The glue between the elements of the array
     * @return the combined {@link String}
     */
    public static String glueString(String[] toGlue, int start, String divider) {
        if (toGlue == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();

        for (int i = start; i < toGlue.length; i++) {
            if (i != start) {
                builder.append(divider);
            }
            builder.append(toGlue[i]);
        }
        return builder.toString();
    }

    /**
     * Serialize an object of the given Type T into a String.
     * 
     * @param object
     *            the {@link Object} to serialize
     * @return serialized {@link String} of the object or null if there is no suitable serializer registered
     */
    @SuppressWarnings("unchecked")
    public static <T> String serialize(T object) {
        Serializer<T> ser = (Serializer<T>) instance.serializers.get(object.getClass());

        if (ser != null) {
            return ser.serialize(object);
        }
        return null;
    }

    /**
     * Accepts a String with data and the type it should deserialize into.
     * 
     * @param data
     *            the data to have deserialized
     * @param shell
     *            object of given type or null if there is no suitable serializer registered
     */
    @SuppressWarnings("unchecked")
    public static <T> T deserialize(String data, Class<T> shell) {
        Serializer<T> ser = (Serializer<T>) instance.serializers.get(shell);

        if (ser != null) {
            try {
                return ser.deserialize(data);
            } catch (CanaryDeserializeException e) {
                Canary.logStackTrace("Deserialization failure.", e);
            }
        }
        return null;
    }

    /**
     * Add a serializer to the system
     * 
     * @param serializer
     *            the {@link Serializer} to add
     * @param type
     *            The type this serializer can process
     */
    public static void addSerializer(Serializer<?> serializer, Class<?> type) {
        Canary.logInfo("Adding a new Serializer: " + type);
        instance.serializers.put(type, serializer);
    }

    /**
     * Reload all subsystems and the whole of canary.
     * Don't over-use this method, it slows down the server.
     * It is used by the reload command and should not be used by anything else!
     */
    public void reload() {

        // Reload configurations
        Configuration.reload();

        // Reload all subsystems with a cache
        instance.banManager.reload();
        instance.kitProvider.reload();
        instance.userAndGroupsProvider.reloadAll();
        instance.warpProvider.reload();
        instance.whitelist.reload();

        // Reload Player permissions and groups data
        for (Player p : getServer().getPlayerList()) {
            p.initPlayerData();
        }
    }

    /**
     * Use the standard CanaryMod logger to dump a StackTrace with WARNING level
     * 
     * @param message
     *            the message to be logged
     * @param thrown
     *            the {@link Throwable} thrown
     */
    public static void logStackTrace(String message, Throwable thrown) {
        logger.log(Level.WARNING, message, thrown);
    }

    /**
     * Use the standard CanaryMod logger to log with SEVERE level
     * 
     * @param message
     *            the message to be logged
     */
    public static void logSevere(String message) {
        logger.log(Level.SEVERE, message);
    }

    /**
     * Use the standard CanaryMod logger to log with WARNING level
     * 
     * @param message
     *            the message to be logged
     */
    public static void logWarning(String message) {
        logger.log(Level.WARNING, message);
    }

    /**
     * Use the standard CanaryMod logger to log with INFO level
     * 
     * @param message
     *            the message to be logged
     */
    public static void logInfo(String message) {
        logger.log(Level.INFO, message);
    }

    /**
     * Use the standard CanaryMod logger to log messages in debug mode as DEBUG
     * 
     * @param message
     *            the message to be logged
     */
    public static void logDebug(String message) {
        if (Configuration.getServerConfig().isDebugMode()) {
            logger.log(CanaryLevel.DEBUG, message);
        }
    }

    /**
     * Use the standard CanaryMod logger to log messages with NOTICE level
     * 
     * @param message
     *            the message to be logged
     */
    public static void logNotice(String message) {
        logger.log(CanaryLevel.NOTICE, message);
    }

    /**
     * Use the standard CanaryMod logger to log messages with DERP level
     * 
     * @param message
     *            the message to be logged
     */
    public static void logDerp(String message) {
        logger.log(CanaryLevel.DERP, message);
    }

    /**
     * Use the standard CanaryMod logger to log messages with SERVERMESSAGE level
     * 
     * @param message
     *            the message to be logged
     */
    public static void logServerMessage(String message) {
        logger.log(CanaryLevel.SERVERMESSAGE, message);
    }

    /**
     * Prints a line to the console
     * 
     * @param line
     *            the line to be printed
     */
    public static void println(String line) {
        System.out.println(line);
    }
}
