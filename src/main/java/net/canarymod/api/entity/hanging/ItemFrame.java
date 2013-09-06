package net.canarymod.api.entity.hanging;

import net.canarymod.api.inventory.Item;

/**
 * Wraps an ItemFrame
 *
 * @author Jason (darkdiplomat)
 */
public interface ItemFrame extends HangingEntity {

    /**
     * Returns the item currently on this frame.
     *
     * @return the Item on the frame
     */
    public Item getItemInFrame();

    /**
     * Sets the Item in this frame
     *
     * @param item
     *         the {@link Item} to attach to the frame
     */
    public void setItemInFrame(Item item);

    /**
     * Return the rotation of the item currently on this frame.
     *
     * @return integer between 0 and 3
     */
    public int getItemRotation();

    /**
     * Sets the rotation of the item currently on this frame
     *
     * @param rot
     *         An integer between 0 and 3
     */
    public void setItemRotation(int rot);

    /**
     * Gets the chance for this item frame's item to drop from the frame.
     *
     * @return drop chance
     */
    public float getItemDropChance();

    /**
     * Gets the chance for this item frame's item to drop from the frame.
     *
     * @param chance
     *         The chance for drop, float between 0.0 and 1.0
     */
    public void setItemDropChance(float chance);
}
