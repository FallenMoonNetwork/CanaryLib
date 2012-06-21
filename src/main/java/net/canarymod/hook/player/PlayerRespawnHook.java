package net.canarymod.hook.player;

import net.canarymod.api.entity.Player;
import net.canarymod.api.world.position.Location;
import net.canarymod.hook.Hook;

/**
 * Player respawn hook. Contains information about a player's respawn location
 * @author Jason Jones
 */
public class PlayerRespawnHook extends Hook{
    
    private Player player;
    private Location respawn;
    
    public PlayerRespawnHook(Player player, Location respawn){
        this.player = player;
        this.respawn = respawn;
        this.type = Hook.Type.PLAYER_RESPAWN;
    }
    
    /**
     * Gets the {@link Player}
     * @return
     */
    public Player getPlayer(){
        return player;
    }
    
    /**
     * Gets the respawn {@link Location}
     * @return respawn
     */
    public Location getRespawnLocation(){
        return respawn;
    }
    
    /**
     * Sets the respawn {@link Location}
     * @param respawn
     */
    public void setRespawnLocation(Location respawn){
        this.respawn = respawn;
    }

    /**
     * Return the set of Data in this order: PLAYER RESPAWN
     */
    @Override
    public Object[] getDataSet() {
        return new Object[]{ player, respawn };
    }

}
