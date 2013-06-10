package net.canarymod.api;

import java.util.ArrayList;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.gui.GUIControl;
import net.canarymod.api.inventory.recipes.CraftingRecipe;
import net.canarymod.api.inventory.recipes.SmeltRecipe;
import net.canarymod.api.world.World;
import net.canarymod.api.world.WorldManager;
import net.canarymod.api.world.blocks.CommandBlock;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CommandOwner;
import net.canarymod.tasks.ServerTask;

/**
 * CanaryMod Server.<br />
 * This handles communication to the server and provides a couple of useful
 * information
 * 
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 * @author Jos Kuijpers
 */
public interface Server extends MessageReceiver, CommandOwner {

    /**
     * Get the current host name for this server
     * 
     * @return the host name
     */
    public String getHostname();

    /**
     * Get the amount of players that are online
     * 
     * @return the number of {@link Player}s online
     */
    public int getNumPlayersOnline();

    /**
     * Get the number of maximum allowed {@link Player}s online
     * 
     * @return the mximum
     */
    public int getMaxPlayers();

    /**
     * Get a list of names from {@link Player}s that are currently online
     * 
     * @return string array of {@link Player} names
     */
    public String[] getPlayerNameList();

    /**
     * Get a list of online {@link Player}s
     * 
     * @return an {@link ArrayList} of {@link Player}s
     */
    public ArrayList<Player> getPlayerList();

    /**
     * Get the default world name
     * 
     * @return the name of the default world
     */
    public String getDefaultWorldName();

    /**
     * Get the world container
     * 
     * @return the {@link WorldManager}
     */
    public WorldManager getWorldManager();

    /**
     * Use a MC vanilla console command
     * 
     * @param command
     *            the command
     * @return {@code true} if command has been parsed by CanaryMod; {@code false} otherwise
     */
    public boolean consoleCommand(String command);

    /**
     * Use a MC vanilla console command as the given player (Ingame vanilla
     * commands)
     * 
     * @param command
     *            the command
     * @param player
     *            the {@link Player} to act as
     * @return {@code true} if command has been parsed by CanaryMod; {@code false} otherwise
     */
    public boolean consoleCommand(String command, Player player);

    /**
     * Use a MC vanilla console command as the given player (Ingame vanilla
     * commands)
     * 
     * @param command
     *            the command
     * @param cmdBlock
     *            the {@link CommandBlock} to act as
     * @return {@code true} if command has been parsed by CanaryMod; {@code false} otherwise
     */
    public boolean consoleCommand(String command, CommandBlock cmdBlock);

    /**
     * Set a timer in the built-in vanilla Timer System.
     * 
     * @param uniqueName
     *            Name to identify your timer
     * @param time
     *            Time in milliseconds.
     */
    public void setTimer(String uniqueName, int time);

    /**
     * Check if a registered Timer has expired already
     * 
     * @param uniqueName
     *            The unique name of the timer you're looking for
     * @return {@code true} if expired; {@code false} if not
     */
    public boolean isTimerExpired(String uniqueName);

    /**
     * Matches a {@link Player} name or a partial name of a {@link Player} name to an existing online {@link Player}
     * 
     * @param player
     *            the partial name of the {@link Player} to find a match for
     * @return a reference to an online {@link Player} or {@code null} if no match was found
     */
    public Player matchPlayer(String player);

    /**
     * Get an OfflinePlayer. This will always return an OfflinePlayer instance.
     * if you try to get a player that never joined it will return an OfflinePlayer
     * with the given name and default values
     * 
     * @param player
     * @return
     */
    public OfflinePlayer getOfflinePlayer(String player);

    /**
     * Like matchPlayer, this returns a player according to a name String.
     * However, no matching is performed here so you need to pass the exact
     * player name
     * 
     * @param player
     *            the name of the {@link Player} to get
     * @return a reference to an online {@link Player}; {@code null} if there is no {@link Player} with the given name
     */
    public Player getPlayer(String player);

    /**
     * Send (broadcast) the given messsage to ALL players on the server,
     * in all worlds.
     * 
     * @param message
     *            the message to be broadcasted
     */
    public void broadcastMessage(String message);

    /**
     * Load a {@link World} with the given name from file.
     * 
     * @param name
     *            the name of the {@link World}
     * @param seed
     *            the {@code long} seed
     * @return true on success, false if the world didn't exist
     */
    public boolean loadWorld(String name, long seed);

    /**
     * Get a {@link World} that already is loaded.
     * If the {@link World} isn't loaded, it'll be loaded,
     * if it didn't exist, this will return null
     * 
     * @param name
     *            the name of the {@link World}
     * @return {@link World} if found; {@code null} if not
     */
    public World getWorld(String name);

    /**
     * Get the {@link World} that is defined as default per config
     * 
     * @return the default {@link World}
     */
    public World getDefaultWorld();

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName();

    /**
     * {@inheritDoc}
     */
    @Override
    public void notice(String message);

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasPermission(String node);

    /**
     * Get the servers configuration manager.
     * 
     * @return the {@link ConfigurationManager}
     */
    public ConfigurationManager getConfigurationManager();

    /**
     * Signal the server to shut down and exit
     */
    public void initiateShutdown();

    /**
     * Restart the server by shutting it down and
     * creating a new server instance, without actually killing the running JVM
     * 
     * @param reloadCanary
     *            Set true if CanaryMod should reload its data too
     */
    public void restart(boolean reloadCanary);

    /**
     * Check if the server is running. This is rarely useful
     * and you should probably not bother using it.
     * 
     * @return {@code true} if running; {@code false} if not
     */
    public boolean isRunning();

    /**
     * Adds a CraftingRecipe to the Server
     * 
     * @param recipe
     *            the {@link CraftingRecipe} to add
     * @see CraftingRecipe
     */
    public void addRecipe(CraftingRecipe recipe);

    /**
     * Adds a Smelting recipe to the Server
     * 
     * @param recipe
     *            the {@link SmeltRecipe} to add
     * @see SmeltRecipe
     */
    public void addSmeltingRecipe(SmeltRecipe recipe);

    /**
     * Changes the current GUI to the given GUI
     * 
     * @param gui
     */
    public void addGUI(GUIControl gui);

    /**
     * Gets the array of the amount of sent packets
     * 
     * @return
     */
    public long[] getSentPacketCountArray();

    /**
     * Gets the array of the size of sent packets
     * 
     * @return
     */
    public long[] getSentPacketSizeArray();

    /**
     * Gets the array of the amount of received packets
     * 
     * @return
     */
    public long[] getReceivedPacketCountArray();

    /**
     * Gets the array of the size of received packets
     * 
     * @return
     */
    public long[] getReceivedPacketSizeArray();

    /**
     * Gets the array of Time Ticks
     * 
     * @return
     */
    public long[] getTickTimeArray();

    /**
     * Gets the number of TCP reading threads
     * 
     * @return
     */
    public int getTcpReaderThreadCount();

    /**
     * Gets the number of TCP writing threads
     * 
     * @return
     */
    public int getTcpWriterThreadCount();

    /**
     * Get the version of CanaryMod that is currently running
     * 
     * @return
     */
    public String getCanaryModVersion();

    /**
     * Get the version of the implemented Minecraft Server<br>
     * 
     * @return
     */
    public String getServerVersion();

    /**
     * Gets the log up to this point if the ServerGUI is running.
     * 
     * @return
     */
    public String getServerGUILog();

    /**
     * Gets the current GUI's control interface
     * Will return null if no GUI is started
     * 
     * @return
     */
    public GUIControl getCurrentGUI();

    /**
     * Gets if the Server is headless or not
     * Will return true if nogui is passed to the server on start
     * 
     * @return
     */
    public boolean isHeadless();

    /**
     * Add a task to the servers synchronous task queue.
     * This will be executed within the main server thread.
     * 
     * @param task
     * @return true if task was added successfully, false otherwise
     */
    public boolean addSynchronousTask(ServerTask task);

    /**
     * Remove the given task from the task queue.
     * 
     * @param task
     * @return true if task was removed successfully, false otherwise
     */
    public boolean removeSynchronousTask(ServerTask task);

    /**
     * Sends a {@link PlayerListEntry} to all {@link Player}s on the server.
     * <p>
     * NOTE: The server needs to have PlayerList enabled in the configuration
     * 
     * @param entry
     *            the {@link PlayerListEntry} to be sent
     */
    public void sendPlayerListEntry(PlayerListEntry entry);
}
