package net.canarymod.hook.player;


import net.canarymod.api.entity.EntityItem;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.CancelableHook;


/**
 * Food eat hook. Contains EntityItem and Player
 * @author
 *
 */
public final class EatHook extends CancelableHook {

    private EntityItem item;
    private Player player;
    private float foodSaturation;
    private float foodExhaustion;

    /**
     * Construct a new food eat hook
     * @param player
     * @param item
     * @param saturation
     * @param exhaustion
     */
    public EatHook(Player player, EntityItem item, float saturation, float exhaustion) {
        this.player = player;
        this.item = item;
        this.foodSaturation = saturation;
        this.foodExhaustion = exhaustion;
    }

    /**
     * Gets the {@link Player}
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the {@link EntityItem} that is being consumed
     * @return
     */
    public EntityItem getItem() {
        return item;
    }

    /**
     * Get the amount of food saturation that will be added to the players food saturation level
     * @return the food Saturation level
     */
    public float getFoodSaturation() {
        return foodSaturation;
    }

    /**
     * This will be added to the players food saturation level
     * @param foodSaturation the food Saturation level to set
     */
    public void setFoodSaturation(float foodSaturation) {
        this.foodSaturation = foodSaturation;
    }

    /**
     * Get the amount of exhaustion that will be removed from the player.
     * That is, how fast they will get hungry again. Higher numbers mean longer times
     * until hunger starts to appear again.
     * @return the foodExhaustion
     */
    public float getFoodExhaustion() {
        return foodExhaustion;
    }

    /**
     * Set the exhaustion that will be removed from the player
     * @param foodExhaustion the foodExhaustion to set
     */
    public void setFoodExhaustion(float foodExhaustion) {
        this.foodExhaustion = foodExhaustion;
    }

    /**
     * Return the set of Data in this order: PLAYER ITEM, FOODSATURATION, FOODEXHAUSTION ISCANCELLED
     */
    @Override
    public Object[] getDataSet() {
        return new Object[] { player, item, foodSaturation, foodExhaustion, isCanceled};
    }
}
