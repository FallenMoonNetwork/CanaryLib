package net.canarymod.api;

import java.util.ArrayList;
import java.util.List;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.gui.GUIControl;
import net.canarymod.api.inventory.recipes.CraftingRecipe;
import net.canarymod.api.inventory.recipes.Recipe;
import net.canarymod.api.inventory.recipes.ShapedRecipe;
import net.canarymod.api.inventory.recipes.ShapelessRecipe;
import net.canarymod.api.inventory.recipes.SmeltRecipe;
import net.canarymod.api.world.World;
import net.canarymod.api.world.WorldManager;
import net.canarymod.api.world.blocks.CommandBlock;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CommandOwner;
import net.canarymod.motd.MOTDOwner;
import net.canarymod.tasks.ServerTask;
import net.canarymod.tasks.TaskOwner;

/**
 * CanaryMod Server.<br />
 * This handles communication to the server and provides a couple of useful
 * information
 *
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 * @author Jos Kuijpers
 */
public interface Server extends MessageReceiver, CommandOwner, TaskOwner, MOTDOwner {

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
     *         the command
     *
     * @return {@code true} if command has been parsed by CanaryMod; {@code false} otherwise
     */
    public boolean consoleCommand(String command);

    /**
     * Use a MC vanilla console command as the given player (Ingame vanilla
     * commands)
     *
     * @param command
     *         the command
     * @param player
     *         the {@link Player} to act as
     *
     * @return {@code true} if command has been parsed by CanaryMod; {@code false} otherwise
     */
    public boolean consoleCommand(String command, Player player);

    /**
     * Use a MC vanilla console command as the given player (Ingame vanilla
     * commands)
     *
     * @param command
     *         the command
     * @param cmdBlock
     *         the {@link CommandBlock} to act as
     *
     * @return {@code true} if command has been parsed by CanaryMod; {@code false} otherwise
     */
    public boolean consoleCommand(String command, CommandBlock cmdBlock);

    /**
     * Set a timer in the built-in vanilla Timer System.
     *
     * @param uniqueName
     *         Name to identify your timer
     * @param time
     *         Time in milliseconds.
     */
    public void setTimer(String uniqueName, int time);

    /**
     * Check if a registered Timer has expired already
     *
     * @param uniqueName
     *         The unique name of the timer you're looking for
     *
     * @return {@code true} if expired; {@code false} if not
     */
    public boolean isTimerExpired(String uniqueName);

    /**
     * Matches a {@link Player} name or a partial name of a {@link Player} name to an existing online {@link Player}
     *
     * @param player
     *         the partial name of the {@link Player} to find a match for
     *
     * @return a reference to an online {@link Player} or {@code null} if no match was found
     */
    public Player matchPlayer(String player);

    /**
     * Get an {@link OfflinePlayer}. This will always return an {@link OfflinePlayer} instance.
     * if you try to get a player that never joined it will return an OfflinePlayer
     * with the given name and default values
     *
     * @param player
     *         the name of the player
     *
     * @return the {@link OfflinePlayer} instance
     */
    public OfflinePlayer getOfflinePlayer(String player);

    /**
     * Like matchPlayer, this returns a player according to a name String.
     * However, no matching is performed here so you need to pass the exact
     * player name
     *
     * @param player
     *         the name of the {@link Player} to get
     *
     * @return a reference to an online {@link Player}; {@code null} if there is no {@link Player} with the given name
     */
    public Player getPlayer(String player);

    /**
     * Send (broadcast) the given messsage to ALL players on the server,
     * in all worlds.
     *
     * @param message
     *         the message to be broadcasted
     */
    public void broadcastMessage(String message);

    /**
     * Load a {@link World} with the given name from file.
     *
     * @param name
     *         the name of the {@link World}
     * @param seed
     *         the {@code long} seed
     *
     * @return true on success, false if the world didn't exist
     */
    public boolean loadWorld(String name, long seed);

    /**
     * Get a {@link World} that already is loaded.
     * If the {@link World} isn't loaded, it'll be loaded,
     * if it didn't exist, this will return null
     *
     * @param name
     *         the name of the {@link World}
     *
     * @return {@link World} if found; {@code null} if not
     */
    public World getWorld(String name);

    /**
     * Get the {@link World} that is defined as default per config
     *
     * @return the default {@link World}
     */
    public World getDefaultWorld();

    /** {@inheritDoc} */
    @Override
    public String getName();

    /** {@inheritDoc} */
    @Override
    public void notice(String message);

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(String node);

    /**
     * Get the servers configuration manager.
     *
     * @return the {@link ConfigurationManager}
     */
    public ConfigurationManager getConfigurationManager();

    /** Signal the server to shut down and exit */
    public void initiateShutdown();

    /**
     * Restart the server by shutting it down and
     * creating a new server instance, without actually killing the running JVM
     *
     * @param reloadCanary
     *         Set true if CanaryMod should reload its data too
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
     *         the {@link CraftingRecipe} to add
     *
     * @return a {@link Recipe} object from the created Recipe
     *
     * @see CraftingRecipe
     * @see Recipe
     * @see ShapedRecipe
     * @see ShapelessRecipe
     */
    public Recipe addRecipe(CraftingRecipe recipe);

    /**
     * Gets a list of {@link Recipe} that the Server has
     *
     * @return the {@link Recipe} list
     */
    public List<Recipe> getServerRecipes();

    /**
     * Attempts to remove a {@link Recipe}
     *
     * @param recipe
     *         the {@link Recipe} to be removed
     *
     * @return {@code true} if successful; {@code false} if not
     */
    public boolean removeRecipe(Recipe recipe);

    /**
     * Adds a Smelting recipe to the Server
     *
     * @param recipe
     *         the {@link SmeltRecipe} to add
     *
     * @see SmeltRecipe
     */
    public void addSmeltingRecipe(SmeltRecipe recipe);

    /**
     * Gets a list of {@link SmeltRecipe} that the Server has
     *
     * @return a list of {@link SmeltRecipe}
     */
    public List<SmeltRecipe> getServerSmeltRecipes();

    /**
     * Attempts to remove a {@link SmeltRecipe} from the Server<br>
     * Note: There is no official native objects for smelting recipes so matching the item id from and item result is enough.
     *
     * @param recipe
     *         the {@link SmeltRecipe} to be removed
     *
     * @return {@code true} if successful; {@code false} if not
     */
    public boolean removeSmeltRecipe(SmeltRecipe recipe);

    /**
     * Changes the current GUI to the given GUI
     *
     * @param gui
     *         the {@link GUIControl} to add
     */
    public void addGUI(GUIControl gui);

    /**
     * Gets the array of the amount of sent packets
     *
     * @return a long array of the sent packet count
     */
    public long[] getSentPacketCountArray();

    /**
     * Gets the array of the size of sent packets
     *
     * @return array of the size of sent packets
     */
    public long[] getSentPacketSizeArray();

    /**
     * Gets the array of the amount of received packets
     *
     * @return array of the amount of recieved packets
     */
    public long[] getReceivedPacketCountArray();

    /**
     * Gets the array of the size of received packets
     *
     * @return array of the size of received packets
     */
    public long[] getReceivedPacketSizeArray();

    /**
     * Gets the array of Time Ticks
     *
     * @return array of Time ticks
     */
    public long[] getTickTimeArray();

    /**
     * Gets the number of TCP reading threads
     *
     * @return number of TCP reading threads
     */
    public int getTcpReaderThreadCount();

    /**
     * Gets the number of TCP writing threads
     *
     * @return number of TCP writing threads
     */
    public int getTcpWriterThreadCount();

    /**
     * Get the version of CanaryMod that is currently running
     *
     * @return the internal CanaryMod version
     */
    public String getCanaryModVersion();

    /**
     * Get the version of the implemented Minecraft Server<br>
     *
     * @return the internal Minecraft Server version
     */
    public String getServerVersion();

    /**
     * Gets the log up to this point if the ServerGUI is running.
     *
     * @return log
     */
    public String getServerGUILog();

    /**
     * Gets the current GUI's control interface
     * Will return null if no GUI is started
     *
     * @return current {@link GUIControl} or {@code null} if none set
     */
    public GUIControl getCurrentGUI();

    /**
     * Gets if the Server is headless or not
     * Will return true if nogui is passed to the server on start
     *
     * @return {@code true} if headless (no gui); {@code false} if gui enabled
     */
    public boolean isHeadless();

    /**
     * Add a task to the servers synchronous task queue.
     * This will be executed within the main server thread.
     *
     * @param task
     *         the {@link ServerTask} to add
     *
     * @return {@code true} if task was added successfully, {@code false} otherwise
     */
    public boolean addSynchronousTask(ServerTask task);

    /**
     * Remove the given task from the task queue.
     *
     * @param task
     *         the {@link ServerTask} to remove
     *
     * @return {@code true} if task was removed successfully, {@code false} otherwise
     */
    public boolean removeSynchronousTask(ServerTask task);

    /**
     * Sends a {@link PlayerListEntry} to all {@link Player}s on the server.
     * <p/>
     * NOTE: The server needs to have PlayerList enabled in the configuration
     *
     * @param entry
     *         the {@link PlayerListEntry} to be sent
     */
    public void sendPlayerListEntry(PlayerListEntry entry);

    /**
     * Gets the currently tracked Tick count.<br>
     * You can use this number to later determine a rough Ticks Per Second (TPS) count
     *
     * @return the current tick
     */
    public int getCurrentTick();

    /**
     * Gets the currently read TPS.<br>
     * Update is attempted once per second
     *
     * @return the current TPS
     */
    public float getTicksPerSecond();
}
