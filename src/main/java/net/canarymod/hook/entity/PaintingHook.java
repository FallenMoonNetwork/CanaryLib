package net.canarymod.hook.entity;

import net.canarymod.api.entity.Painting;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.CancelableHook;

/**
 * Painting hook. Contains information about a player destroying a painting.
 * @author Jason Jones
 */
public final class PaintingHook extends CancelableHook{

    private Painting painting;
    private Player player;

    public PaintingHook(Painting painting, Player player){
        this.painting = painting;
        this.player = player;
    }

    /**
     * Gets the {@link Painting}
     * @return painting
     */
    public Painting getPainting(){
        return painting;
    }

    /**
     * Gets the {@link Player}
     * @return player
     */
    public Player getPlayer(){
        return player;
    }

    /**
     * Return the set of Data in this order: PAINTING PLAYER ISCANCELLED
     */
    @Override
    public Object[] getDataSet(){
        return new Object[]{ painting, player, isCanceled };
    }
}
