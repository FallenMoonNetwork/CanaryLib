package net.canarymod.hook.player;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.position.Location;
import net.canarymod.hook.Hook;

/**
 * Player respawn hook. Contains information about a player's respawn location
 * 
 * @author Jason Jones
 */
public final class PlayerRespawnHook extends Hook {

    private Player player;
    private Location respawn;
    private boolean bedSpawn;

    public PlayerRespawnHook(Player player, Location respawn, boolean bedSpawn) {
        this.player = player;
        this.respawn = respawn;
        this.bedSpawn = bedSpawn;
    }

    /**
     * Gets the {@link Player}
     * 
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the respawn {@link Location}
     * 
     * @return respawn
     */
    public Location getRespawnLocation() {
        return respawn;
    }

    /**
     * Sets the respawn {@link Location}
     * 
     * @param respawn
     */
    public void setRespawnLocation(Location respawn) {
        this.respawn = respawn;
    }

    /**
     * Returns true if the player is supposed to respawn at his bed
     * 
     * @return
     */
    public boolean isBedSpawn() {
        return bedSpawn;
    }

    @Override
    public final String toString() {
        return String.format("%s[Player=%s, Location=%s]", getName(), player, respawn);
    }
}
