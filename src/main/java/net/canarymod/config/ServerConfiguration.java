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
        config.setString("data-source", "flatfile");
        config.setBoolean("logging", false);
        config.setBoolean("playerlist-autoupdate", false);
        config.setBoolean("debug", false);
        config.setString("default-world-name", "default");
        config.setBoolean("show-unknown-command", true);
        config.setBoolean("save-homes", true);
        config.setBoolean("death-message", true);
        config.setString("whitelist-message", "Not on whitelist.");
        config.setString("motd", "CanaryMod Minecraft Server");
        config.setString("language", "en_US");
        config.setInt("playerlist-ticks", 500);
        config.setBoolean("playerlist-usecolors", true);
        config.setBoolean("whitelist", false);
        config.setBoolean("alllow-enchantment-stacking", false);
        config.setString("date-format", "l jS \\of F Y h:i:s A");

        config.save();
    }

    /**
     * Get datasource type
     * @return
     */
    public String getDatasourceType() {
        return cfg.getString("data-source", "xml");
    }

    /**
     * Get the default world name defined in the config
     * @return
     */
    public String getDefaultWorldName() {
        return cfg.getString("default-world-name", "default");
    }

    /**
     * Whether this server is in debug mode.
     *
     * Use debug mode when developing plugins, CanaryAPI or CanaryMod.
     * @return
     */
    public boolean isDebugMode() {
        return cfg.getBoolean("debug-mode", false);
    }

    /**
     * Get whether the death message is enabled
     * @return true when enabled, false otherwise
     */
    public boolean isDeathMessageEnabled() {
        return cfg.getBoolean("death-message", true);
    }

    /**
     * Get the default ban message
     * @return A string containing the default ban message
     */
    public String getDefaultBanMessage() {
        return cfg.getString("default-ban-message", "You are banned from this server!");
    }

    /**
     * Get whether the server must log
     * @return true when enabled, false otherwise
     */
    public boolean isLogging() {
        return cfg.getBoolean("logging", true);
    }

    /**
     * Get whether the player list is auto-updated
     * @return true if auto-updated, false otherwise. Default is false.
     */
    public boolean getPlayerlistAutoUpdate() {
        return cfg.getBoolean("playerlist-autoupdate", false);
    }

    /**
     * Get whether the player list is enabled.
     * @return true when enabled, false otherwise. Default is true.
     */
    public boolean isPlayerListEnabled() {
        return cfg.getBoolean("playerlist-enabled", true);
    }

    /**
     * Get the number of ticks between playerlist updates
     * @return
     */
    public int getPlayerlistTicks() {
        return cfg.getInt("playerlist-ticks", 500);
    }

    /**
     * Get whether playerlist colors are enabled.
     *
     * Note that using colors in the playerlist breaks usage of playername-completion in chat.
     *
     * @return true when enabled, false otherwise. Default is true.
     */
    public boolean isPlayerlistColorsEnabled() {
        return cfg.getBoolean("playerlist-usecolors", true);
    }

    /**
     * Get whether the reservelist is enabled
     * @return true when enabled, false otherwise. Default is false.
     */
    public boolean isReservelistEnabled() {
        return cfg.getBoolean("reservelist", false);
    }

    /**
     * Get the message to be displayed when someone is not on the reserve list.
     * @return A string containing the message.
     */
    public String getReservelistMessage() {
        return cfg.getString("reservelist-message", "Not on reserve list.");
    }

    /**
     * Get whether home-saving is enabled.
     * @return true when enabled, false otherwise. Default is true.
     */
    public boolean isSaveHomesEnabled() {
        return cfg.getBoolean("save-homes", true);
    }

    /**
     * Get whether 'Unkown Command' must be shown when an unkown command is used.
     * @return True when enabled, false otherwise. Default is true.
     */
    public boolean getShowUnkownCommand() {
        return cfg.getBoolean("show-unkown-command", true);
    }

    /**
     * Get the message shown to players who are not whitelisted.
     * @return A string containing the message.
     */
    public String getWhitelistMessage() {
        return cfg.getString("whitelist-message", "Not on whitelist.");
    }

    /**
     * Get whether the whitelist is enabled.
     * @return True when enabled, false otherwise. Default is false.
     */
    public boolean isWhitelistEnabled() {
        return cfg.getBoolean("whitelist", false);
    }

    /**
     * Get the message of the day, the message shown in the server list.
     * @return A string containing the message
     */
    public String getMotd() {
        return cfg.getString("motd", "A Minecraft Server");
    }

    public int getGameMode() {
        return cfg.getInt("gamemode", 0);
    }

    /**
     * Get the port number used to receive player-connections
     * @return
     */
    public int getPort() {
        return cfg.getInt("server-port", 25565);
    }

    /**
     * Get whether server query-ing is enabled
     * @return
     */
    public boolean isQueryEnabled() {
        return cfg.getBoolean("enable-query", false);
    }

    /**
     * Whether Remote CONtrol is enabled.
     * @return
     */
    public boolean isRconEnabled() {
        return cfg.getBoolean("enable-rcon", false);
    }

    /**
     * Whether the server is in online mode.
     *
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
     * @return
     */
    public String getBindIp() {
        return cfg.getString("server-ip", "");
    }

    /**
     * Get maximum amount of player allowed online
     * @return
     */
    public int getMaxPlayers() {
        return cfg.getInt("max-players", 20);
    }

    /**
     * Get the port used for remote control
     * @return
     */
    public int getRconPort() {
        return cfg.getInt("rcon.port", 0);
    }

    /**
     * Get the password used for remote control
     * @return
     */
    public String getRconPassword() {
        return cfg.getString("rcon.password", "");
    }

    /**
     * Get the port used for query
     * @return
     */
    public int getQueryPort() {
        return cfg.getInt("query.port", 0);
    }

    /**
     * Get the view distance of clients: maximum radius of loaded chunks around a player
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
}
