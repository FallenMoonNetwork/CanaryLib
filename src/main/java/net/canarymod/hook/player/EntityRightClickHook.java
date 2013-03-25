package net.canarymod.hook.player;


import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.CancelableHook;


/**
 * Is called when a player right-clicks an entity, for instance a villager for trading or a vehicle for entering
 * @author chris
 *
 */
public class EntityRightClickHook extends CancelableHook {
    private Entity clickedEntity;
    private Player player;

    /**
     * Construct a new EntityRightClickHook
     * @param block
     * @param player
     */
    public EntityRightClickHook(Entity clicked, Player player) {
        this.clickedEntity = clicked;
        this.player = player;
    }

    /**
     * Get the player who clicked
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns the entity that was clicked
     * @return
     */
    public Entity getEntity() {
        return clickedEntity;
    }
}
