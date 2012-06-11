package net.canarymod.hook.player;

import net.canarymod.api.entity.Player;
import net.canarymod.api.world.position.Location;
import net.canarymod.hook.Hook;

/**
 * Player move hook. Contains information about a player's movement
 * @author Jason Jones
 */
public class PlayerMoveHook extends Hook{

    private Player player;
    private Location from;
    private Location to;
    
    public PlayerMoveHook(Player player, Location from, Location to){
        this.player = player;
        this.from = from;
        this.to = to;
        this.type = Hook.Type.PLAYER_MOVE;
    }
    
    /**
     * Gets the player
     * @return player
     */
    public Player getPlayer(){
        return player;
    }
    
    /**
     * Gets the from location
     * @return from
     */
    public Location getFrom(){
        return from;
    }
    
    /**
     * Gets the to location (same as player's location)
     * @return to
     */
    public Location getTo(){
        return to;
    }

    /**
     * Return the set of Data in this order: PLAYER FROM TO
     */
    @Override
    public Object[] getDataSet() {
        return new Object[]{ player, from, to };
    }
}
