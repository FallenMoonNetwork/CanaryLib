package net.canarymod.config;

import java.io.File;

import net.canarymod.Canary;
import net.visualillusionsent.utils.PropertiesFile;

/**
 * @author Jos Kuijpers
 * @author Jason (darkdiplomat)
 */
public class ServerConfiguration implements ConfigurationContainer {
    private PropertiesFile cfg;

    public ServerConfiguration(String path) {
        File test = new File(path);

        if (!test.exists()) {
            Canary.logInfo("Could not find the server configuration at " + path + ", creating default.");
            ServerConfiguration.createDefault();
        }
        PropertiesFile file = new PropertiesFile(path);

        init(file);
    }

    public ServerConfiguration(PropertiesFile cfg) {
        init(cfg);
    }

    private void init(PropertiesFile cfg) {
        this.cfg = cfg;
        testConfig();
    }

    /**
     * Reloads the configuration file
     */
    @Override
    public void reload() {
        cfg.reload();
    }

    /**
     * Get the configuration file
     */
    @Override
    public PropertiesFile getFile() {
        return cfg;
    }

    /**
     * Creates the default configuration
     */
    public static void createDefault() {
        PropertiesFile config;

        config = new PropertiesFile("config" + File.separatorChar + "server.cfg");

        config.setBoolean("reservelist", false);
        config.setString("protect-spam", "default");
        config.setString("reservelist-message", "Not on reserve list.");
        config.setBoolean("playerlist-enabled", true);
        config.setString("default-ban-message", "You are banned from this server!");
        config.setString("data-source", "xml");
        config.setBoolean("logging", false);
        config.setBoolean("playerlist-autoupdate", false);
        config.setInt("view-distance", 10);
        config.setBoolean("debug", false);
        config.setString("default-world-name", "default");
        config.setBoolean("show-unknown-command", true);
        config.setBoolean("save-homes", true);
        config.setBoolean("death-message", true);
        config.setString("whitelist-message", "Not on whitelist.");
        config.setString("motd", "CanaryMod Minecraft Server");
        config.setString("language", "en_US");
        config.addComment("language", "Available are: en_EN, en_US, de_DE, no_NO, ne_NE");
        config.setInt("playerlist-ticks", 500);
        config.setBoolean("playerlist-usecolors", true);
        config.setInt("max-players", 20);
        config.setBoolean("whitelist", false);
        config.setBoolean("allow-enchantment-stacking", false);
        config.setBoolean("online-mode", true);
        config.setString("server-ip", "");
        config.setString("texture-pack", "");
        config.setBoolean("snooper-enabled", true);
        config.setInt("server-port", 25565);
        config.setString("date-format", "l jS \\of F Y h:i:s A");
        config.addComment("date-format", "A formatting to display timestamps");

        config.setString("commandblock-group", "default");
        config.addComment("commandblock-group", "This groups permissions will determine what commandblock can and can not do!");
        config.setBoolean("enable-command-block", false);

        config.setBoolean("use-world-cache-timer", true);
        config.addComment("use-world-cache-timer", "Enable automatic unloading of unused worlds.");

        config.setLong("world-cache-timeout", 60);
        config.addComment("world-cache-timeout", "For how long should a world be empty before it will be unloaded (if use-world-cache is enabled)");

        config.setString("ban-expiration-date-message", "Your Ban will be lifted at ");
        config.setString("ban-default-message", "You are banned from this server.");
        config.setString("not-on-whitelist-message", "You are not whitelisted on this server.");
        config.setString("server-full-message", "The server is full.");
        config.save();
    }

    /**
     * Test properties, adding missing properties to the file and save it.
     */
    private final void testConfig() {
        cfg.getBoolean("reservelist", false);
        cfg.getString("protect-spam", "default");
        cfg.getString("reservelist-message", "Not on reserve list.");
        cfg.getBoolean("playerlist-enabled", true);
        cfg.getString("default-ban-message", "You are banned from this server!");
        cfg.getString("data-source", "xml");
        cfg.getBoolean("logging", false);
        cfg.getBoolean("playerlist-autoupdate", false);
        cfg.getInt("view-distance", 10);
        cfg.getBoolean("debug", false);
        cfg.getString("default-world-name", "default");
        cfg.getBoolean("show-unknown-command", true);
        cfg.getBoolean("save-homes", true);
        cfg.getBoolean("death-message", true);
        cfg.getString("whitelist-message", "Not on whitelist.");
        cfg.getString("motd", "CanaryMod Minecraft Server");
        cfg.getString("language", "en_US");
        cfg.getInt("playerlist-ticks", 500);
        cfg.getBoolean("playerlist-usecolors", true);
        cfg.getInt("max-players", 20);
        cfg.getBoolean("whitelist", false);
        cfg.getBoolean("allow-enchantment-stacking", false);
        cfg.getBoolean("online-mode", true);
        cfg.getString("server-ip", "");
        cfg.getString("texture-pack", "");
        cfg.getBoolean("snooper-enabled", true);
        cfg.getInt("server-port", 25565);
        cfg.getString("date-format", "l jS \\of F Y h:i:s A");
        cfg.getString("commandblock-group", "default");
        cfg.getBoolean("enable-command-block", false);
        cfg.getBoolean("use-world-cache-timer", true);
        cfg.getLong("world-cache-timeout", 60);
        cfg.getString("ban-expiration-date-message", "Your Ban will be lifted at ");
        cfg.getString("ban-default-message", "You are banned from this server.");
        cfg.getString("not-on-whitelist-message", "You are not whitelisted on this server.");
        cfg.getString("server-full-message", "The server is full.");
        cfg.save();
    }

    /**
     * Get datasource type
     *
     * @return
     */
    public String getDatasourceType() {
        return cfg.getString("data-source", "xml");
    }

    /**
     * Get the default world name defined in the config
     *
     * @return
     */
    public String getDefaultWorldName() {
        return cfg.getString("default-world-name", "default");
    }

    /**
     * Whether this server is in debug mode.
     * Use debug mode when developing plugins, CanaryAPI or CanaryMod.
     *
     * @return
     */
    public boolean isDebugMode() {
        return cfg.getBoolean("debug-mode", false);
    }

    /**
     * Get whether the death message is enabled
     *
     * @return true when enabled, false otherwise
     */
    public boolean isDeathMessageEnabled() {
        return cfg.getBoolean("death-message", true);
    }

    /**
     * Get the default ban message
     *
     * @return A string containing the default ban message
     */
    public String getDefaultBanMessage() {
        return cfg.getString("default-ban-message", "You are banned from this server!");
    }

    /**
     * Get whether the server must log
     *
     * @return true when enabled, false otherwise
     */
    public boolean isLogging() {
        return cfg.getBoolean("logging", true);
    }

    /**
     * Get whether the player list is auto-updated
     *
     * @return true if auto-updated, false otherwise. Default is false.
     */
    public boolean getPlayerlistAutoUpdate() {
        return cfg.getBoolean("playerlist-autoupdate", false);
    }

    /**
     * Get whether the player list is enabled.
     *
     * @return true when enabled, false otherwise. Default is true.
     */
    public boolean isPlayerListEnabled() {
        return cfg.getBoolean("playerlist-enabled", true);
    }

    /**
     * Get the number of ticks between playerlist updates
     *
     * @return
     */
    public int getPlayerlistTicks() {
        return cfg.getInt("playerlist-ticks", 500);
    }

    /**
     * Get whether playerlist colors are enabled.
     * Note that using colors in the playerlist breaks usage of playername-completion in chat.
     *
     * @return true when enabled, false otherwise. Default is true.
     */
    public boolean isPlayerlistColorsEnabled() {
        return cfg.getBoolean("playerlist-usecolors", true);
    }

    /**
     * Get whether the reservelist is enabled
     *
     * @return true when enabled, false otherwise. Default is false.
     */
    public boolean isReservelistEnabled() {
        return cfg.getBoolean("reservelist", false);
    }

    /**
     * Get the message to be displayed when someone is not on the reserve list.
     *
     * @return A string containing the message.
     */
    public String getReservelistMessage() {
        return cfg.getString("reservelist-message", "Not on reserve list.");
    }

    /**
     * Get whether home-saving is enabled.
     *
     * @return true when enabled, false otherwise. Default is true.
     */
    public boolean isSaveHomesEnabled() {
        return cfg.getBoolean("save-homes", true);
    }

    /**
     * Get whether 'Unkown Command' must be shown when an unkown command is used.
     *
     * @return True when enabled, false otherwise. Default is true.
     */
    public boolean getShowUnkownCommand() {
        return cfg.getBoolean("show-unkown-command", true);
    }

    /**
     * Get the message shown to players who are not whitelisted.
     *
     * @return A string containing the message.
     */
    public String getWhitelistMessage() {
        return cfg.getString("whitelist-message", "Not on whitelist.");
    }

    /**
     * Get whether the whitelist is enabled.
     *
     * @return True when enabled, false otherwise. Default is false.
     */
    public boolean isWhitelistEnabled() {
        return cfg.getBoolean("whitelist", false);
    }

    /**
     * Get the message of the day, the message shown in the server list.
     *
     * @return A string containing the message
     */
    public String getMotd() {
        return cfg.getString("motd", "Canary Minecraft Server");
    }

    public int getGameMode() {
        return cfg.getInt("gamemode", 0);
    }

    /**
     * Get the port number used to receive player-connections
     *
     * @return
     */
    public int getPort() {
        return cfg.getInt("server-port", 25565);
    }

    /**
     * Get whether server query-ing is enabled
     *
     * @return
     */
    public boolean isQueryEnabled() {
        return cfg.getBoolean("enable-query", false);
    }

    /**
     * Whether Remote CONtrol is enabled.
     *
     * @return
     */
    public boolean isRconEnabled() {
        return cfg.getBoolean("enable-rcon", false);
    }

    /**
     * Whether the server is in online mode.
     * When a server is in online mode, all players are verificated
     * against the servers of Mojang. This will ensure all players have paid.
     * When allowing unpaid users, the server is vulnerable to griefing and attacks.
     *
     * @return
     */
    public boolean isOnlineMode() {
        return cfg.getBoolean("online-mode", true);
    }

    /**
     * Get the IP address which to server binds to
     *
     * @return
     */
    public String getBindIp() {
        return cfg.getString("server-ip", "");
    }

    /**
     * Get maximum amount of player allowed online
     *
     * @return
     */
    public int getMaxPlayers() {
        return cfg.getInt("max-players", 20);
    }

    /**
     * Get the port used for remote control
     *
     * @return
     */
    public int getRconPort() {
        return cfg.getInt("rcon.port", 0);
    }

    /**
     * Get the password used for remote control
     *
     * @return
     */
    public String getRconPassword() {
        return cfg.getString("rcon.password", "");
    }

    /**
     * Get the port used for query
     *
     * @return
     */
    public int getQueryPort() {
        return cfg.getInt("query.port", 0);
    }

    /**
     * Get the view distance of clients: maximum radius of loaded chunks around a player
     *
     * @return view distance
     */
    public int getViewDistance() {
        return cfg.getInt("view-distance", 10);
    }

    public String getLanguageCode() {
        return cfg.getString("language", "en_US");
    }

    public boolean allowEnchantmentStacking() {
        return cfg.getBoolean("alllow-enchantment-stacking", false);
    }

    public String getDateFormat() {
        return cfg.getString("date-format", "l jS \\of F Y h:i:s A");
    }

    public String getCommandBlockGroupName() {
        return cfg.getString("commandblock-group", "default");
    }

    public boolean isCommandBlockEnabled() {
        return cfg.getBoolean("enable-command-block", false);
    }

    public String getTexturePack() {
        return cfg.getString("texture-pack", "");
    }

    public boolean isSnooperEnabled() {
        return cfg.getBoolean("snooper-enabled", true);
    }

    public long getWorldCacheTimeout() {
        return cfg.getLong("world-cache-timeout", 60);
    }

    public boolean isWorldCacheTimerEnabled() {
        // config.setBoolean("use-world-cache-timer", true);
        return cfg.getBoolean("use-world-cache-timer", true);
    }

    public String getBanExpireDateMessage() {
        return cfg.getString("ban-expiration-date-message", "Your Ban will be lifted at ");
    }

    public String getDefaultBannedMessage() {
        return cfg.getString("ban-default-message", "You are banned from this server.");
    }

    public String getNotWhitelistedMessage() {
        return cfg.getString("not-on-whitelist-message", "You are not whitelisted on this server.");
    }

    public String getServerFullMessage() {
        return cfg.getString("server-full-message", "The server is full.");
    }

}
