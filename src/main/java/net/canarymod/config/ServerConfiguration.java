package net.canarymod.config;

import net.canarymod.Canary;
import net.visualillusionsent.utils.PropertiesFile;

import java.io.File;

/**
 * Server Configuration Container
 *
 * @author Jos Kuijpers
 * @author Jason (darkdiplomat)
 */
public class ServerConfiguration implements ConfigurationContainer {
    private PropertiesFile cfg;

    public ServerConfiguration(String path) {
        File test = new File(path);

        if (!test.exists()) {
            Canary.logInfo("Could not find the server configuration at " + path + ", creating default.");
        }
        this.cfg = new PropertiesFile("config" + File.separatorChar + "server.cfg");
        verifyConfig();
    }

    /** Reloads the configuration file */
    @Override
    public void reload() {
        cfg.reload();
        verifyConfig();
    }

    /** Get the configuration file */
    @Override
    public PropertiesFile getFile() {
        return cfg;
    }

    /** Creates the default configuration */
    private void verifyConfig() {
        cfg.getBoolean("reservelist", false);
        cfg.getString("protect-spam", "default");
        cfg.getString("reservelist-message", "Not on reserve list.");
        cfg.getBoolean("playerlist-enabled", true);
        cfg.getString("default-ban-message", "You are banned from this server!");
        cfg.getString("chat-format", "<%prefix%name&f> %message");
        cfg.setComments("chat-format", "Valid default placeholders are:",
                "%prefix (player prefix), %name (player name), %group (main group)",
                "You can use standard color codes at all times. Use & as identifier if you miss a § key",
                "Plugins may extend the list of available placeholders");
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
        cfg.setComments("language", "Available are: en_EN, en_US, de_DE, no_NO, ne_NE");
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
        cfg.getString("date-format", "yyyy.MM.dd, hh:mm:ss");
        cfg.setComments("date-format", "A formatting to display timestamps");
        cfg.getString("commandblock-group", "default");
        cfg.setComments("commandblock-group", "This groups permissions will determine what commandblock can and can not do!");
        cfg.getBoolean("enable-command-block", false);
        cfg.getBoolean("use-world-cache-timer", true);
        cfg.setComments("use-world-cache-timer", "Enable automatic unloading of unused worlds.");
        cfg.getLong("world-cache-timeout", 60);
        cfg.setComments("world-cache-timeout", "For how long should a world be empty before it will be unloaded (if use-world-cache is enabled)");
        cfg.getString("ban-expiration-date-message", "Your Ban will be lifted at ");
        cfg.getString("ban-default-message", "You are banned from this server.");
        cfg.getString("not-on-whitelist-message", "You are not whitelisted on this server.");
        cfg.getString("server-full-message", "The server is full.");
        cfg.getBoolean("strict-sign-characters", true);
        cfg.setComments("strict-sign-characters", "Sets whether to strictly check characters on signs for invalid chat characters. Set to false to disable (and allow more characters)");
        cfg.getInt("player-idle-timeout", 1);
        cfg.setComments("player-idle-timeout", "Timeout in minutes before kicking an idle player");
        cfg.getBoolean("update-lang-files", true);
        cfg.setComments("Whether to verify and update lang files or not, disable if you intend to make changes to those files");
        cfg.save();
    }

    /**
     * Get datasource type
     *
     * @return datasource type
     */
    public String getDatasourceType() {
        return cfg.getString("data-source", "xml");
    }

    /**
     * Get the default world name defined in the config
     *
     * @return default world name
     */
    public String getDefaultWorldName() {
        return cfg.getString("default-world-name", "default");
    }

    /**
     * Whether this server is in debug mode.
     * Use debug mode when developing plugins, CanaryLib or CanaryMod.
     *
     * @return {@code true} if debug mode enabled; {@code false} if not
     */
    public boolean isDebugMode() {
        return cfg.getBoolean("debug-mode", false);
    }

    /**
     * Get whether the death message is enabled
     *
     * @return true when enabled; false otherwise
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
     * @return playerlist ticks
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
     * Get whether 'Unknown Command' must be shown when an unknown command is used.
     *
     * @return True when enabled, false otherwise. Default is true.
     */
    public boolean getShowUnknownCommand() {
        return cfg.getBoolean("show-unknown-command", true);
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
     * @return port
     */
    public int getPort() {
        return cfg.getInt("server-port", 25565);
    }

    /**
     * Get whether server query-ing is enabled
     *
     * @return {@code true} if enabled; {@code false} if not
     */
    public boolean isQueryEnabled() {
        return cfg.getBoolean("enable-query", false);
    }

    /**
     * Whether Remote Control (RCON) is enabled.
     *
     * @return {@code true} if enabled; {@code false} if not
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
     * @return {@code true} if online mode is enabled; {@code false} if not
     */
    public boolean isOnlineMode() {
        return cfg.getBoolean("online-mode", true);
    }

    /**
     * Get the IP address which to server binds to
     *
     * @return server ip
     */
    public String getBindIp() {
        return cfg.getString("server-ip", "");
    }

    /**
     * Get maximum amount of player allowed online
     *
     * @return max players
     */
    public int getMaxPlayers() {
        return cfg.getInt("max-players", 20);
    }

    /**
     * Get the port used for remote control
     *
     * @return RCON port
     */
    public int getRconPort() {
        return cfg.getInt("rcon.port", 0);
    }

    /**
     * Get the password used for remote control
     *
     * @return RCON password
     */
    public String getRconPassword() {
        return cfg.getString("rcon.password", "");
    }

    /**
     * Get the port used for query
     *
     * @return query port
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
        return cfg.getString("date-format", "yyyy.MM.dd, hh:mm:ss");
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

    public boolean getStrictSignCharacterChecks() {
        return cfg.getBoolean("strict-sign-characters");
    }

    public String getChatFormat() {
        return cfg.getString("chat-format", "<%prefix%name&f> %message");
    }

    public int getPlayerIdleTimeout() {
        return cfg.getInt("player-idle-timeout", 1);
    }

    public void setPlayerIdleTimeout(int timeout) {
        cfg.setInt("player-idle-timeout", timeout);
    }

    public boolean updateLang() {
        return cfg.getBoolean("update-lang-files", true);
    }
}
