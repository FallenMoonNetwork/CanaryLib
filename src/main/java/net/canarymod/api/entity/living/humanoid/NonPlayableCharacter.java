package net.canarymod.api.entity.living.humanoid;


import java.util.List;
import net.canarymod.api.PathFinder;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.living.EntityLiving;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Position;


/**
 * Non-Playable Character interface
 *
 * @author Jason (darkdiplomat)
 */
public interface NonPlayableCharacter extends EntityLiving {

    /**
     * Sets the name of the NonPlayableCharacter
     *
     * @param name
     *            the name to set for the NonPlayableCharacter
     */
    public void setName(String name);

    /**
     * Gets the Player's name displayed to others
     *
     * @return Player's display name
     */
    public String getDisplayName();

    /**
     * Sets the Player's name display name
     *
     * @param display
     *            the name to have the Player display
     */
    public void setDisplayName(String display);

    /**
     * Teleport to the given coords within the position
     *
     * @param vec3d
     */
    public void teleportTo(Position position);

    /**
     * Teleport to the given location
     * @param loc
     */
    public void teleportTo(Location loc);

    /**
     * Teleport to the given points
     * @param x
     * @param y
     * @param z
     */
    public void teleportTo(int x, int y, int z);

    /**
     * Returns the item that is currently in the NPC's hands
     *
     * @return item in hand
     */
    public Item getItemHeld();

    /**
     * Sets the slot to use for the item in hand, an integer between 0 and 9
     *
     * @param slot
     *            the Slot to use
     */
    public void setItemInHandSlot(int slot);

    /**
     * Gets the NPC's inventory
     *
     * @return NPC's inventory
     */
    public Inventory getInventory();

    /**
     * Tells the NPC to look at given entity
     *
     * @param entity
     *            the entity to look at
     */
    public void lookAt(Entity entity);

    /**
     * Tell the NPC to look at nearest player
     */
    public void lookAtNearest();

    /**
     * Shows the NPC to the specified Player
     */
    public void haunt(Player player);

    /**
     * Hides the NPC from specified Player
     *
     * @param player
     *            the Player to hide from
     */
    public void ghost(Player player);

    /**
     * De-spawn the NPC and return its reference for further processing.
     * This does not DELETE this entity, it stays there, its just no longer shown
     */
    public NonPlayableCharacter despawn();

    /**
     * Gets the list of NPCBehaviors for this NonPlayableCharacter
     *
     * @return the list of NPCBehaviors
     */
    public List<NPCBehavior> getBehaviors();

    /**
     * Removes a behavior from the NonPlayableCharacter
     *
     * @param behavior
     *            the NPCBehavior to be removed
     * @return the removed NPCBehavior or {@code null} if the NPCBehavior wasn't in the list
     */
    public NPCBehavior removeBehavior(NPCBehavior behavior);

    /**
     * Adds a NPCBehavior to the NonPlayableCharacter
     *
     * @param behavior
     *            the NPCBehavior to be added
     * @return {@code true} of successfully added, {@code false} if not
     */
    public boolean addBehavior(NPCBehavior behavior);

    /**
     * Sends a message as the NonPlayableCharacter
     *
     * @param msg
     *            the Message to send
     */
    public void chat(String msg);

    /**
     * Sends a private message as the NonPlayerCharacter to the specified Player
     *
     * @param player
     *            the {@link Player} to pm
     * @param msg
     *            the message to send
     */
    public void privateMessage(Player player, String msg);

    /**
     * Gets the prefix used by the NonPlayableCharacter for messages
     *
     * @return the prefix
     */
    public String getPrefix();

    /**
     * Sets the prefix used by the NonPlayableCharacter for messages<br>
     * Use %name to include the NonPlayableCharacter's name
     *
     * @param prefix
     *            the prefix to be used by the NonPlayableCharacter
     */
    public void setPrefix(String prefix);

    /**
     * Sets if the NonPlayableCharacter can be damaged and thus killed
     *
     * @param invulnerable
     *            {@code true} for invulnerable; {@code false} for not
     */
    public void setInvulnerable(boolean invulnerable);

    /**
     * Checks if the NonPlayableCharacter can be damaged
     *
     * @return {@code true} for invulnerable; {@code false} for not
     */
    public boolean isInvulnerable();

    /**
     * Attacks for the NonPlayableCharacter the targeted entity with the
     * currently equipped item.
     * @param entity Entity to attack.
     */
    public void attackEntity(Entity entity);

    /**
     * Get the PathFinder class for this NPC.
     * @return the pathfinder
     */
    public PathFinder getPathFinder();

    /**
     * Move this entity to exact location with this speed.
     * @param x x coord
     * @param y y coord
     * @param z z coord
     * @param speed Set the speed of this mob, it should be between 0.0F and 1.0F <br>
     * <b>NOTE:</b> 1.0F is really really fast.<br>
     */
    public void moveEntityWithGravity(double x, double y, double z, float speed);

    /**
     * Can this NPC Fly?
     * @return true if its allowed, false otherwise.
     */
    public boolean canFly();

    /**
     * Set whether or not this NPC can fly.
     * @param bool 
     */
    public void setFly(boolean bool);
}
