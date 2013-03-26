package net.canarymod.api.entity.living.humanoid;


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
     * Tells the NPC to look at given player
     * 
     * @param player
     *            the player to look at
     */
    public void lookAt(Player player);
    
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
     * Repeatedly called to allow updating things for this NPC, override and add your own code for your NPC instances
     */
    public void update();
}
