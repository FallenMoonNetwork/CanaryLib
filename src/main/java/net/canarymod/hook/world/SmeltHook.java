package net.canarymod.hook.world;

import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.blocks.Furnace;
import net.canarymod.hook.CancelableHook;

/**
 * SmeltHook
 * <p/>
 * Called when a {@link Furnace} smelts an Item
 *
 * @author Jason (darkdiplomat)
 */
public final class SmeltHook extends CancelableHook {
    private Furnace furnace;
    private Item result;

    /**
     * Constructs a new SmeltHook
     *
     * @param furnace
     *         the {@link Furnace} doing the smelting
     * @param result
     *         the {@link Item} result
     */
    public SmeltHook(Furnace furnace, Item result) {
        this.furnace = furnace;
        this.result = result;
    }

    /**
     * Gets the {@link Furnace} doing the smelting
     *
     * @return the {@link Furnace}
     */
    public Furnace getFurnace() {
        return furnace;
    }

    /**
     * Gets the {@link Item} result
     *
     * @return the {@link Item}
     */
    public Item getResult() {
        return result;
    }

    @Override
    public final String toString() {
        return String.format("%s[Furnace=%s, Item=%s]", getName(), furnace, result);
    }

}
