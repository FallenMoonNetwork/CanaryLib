package net.canarymod.hook.entity;


import net.canarymod.api.entity.Painting;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.CancelableHook;


/**
 * Painting hook. Contains information about a player hanging a painting.
 * @author Jason Jones
 */
public final class PaintingCreateHook extends CancelableHook {

    private Painting painting;
    private Player player;

    public PaintingCreateHook(Painting painting, Player player) {
        this.painting = painting;
        this.player = player;
    }

    /**
     * Gets the {@link Painting}
     * @return painting
     */
    public Painting getPainting() {
        return painting;
    }

    /**
     * Gets the {@link Player}
     * @return player
     */
    public Player getPlayer() {
        return player;
    }
}
