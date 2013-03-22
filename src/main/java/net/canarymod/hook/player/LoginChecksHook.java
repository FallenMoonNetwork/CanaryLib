package net.canarymod.hook.player;

import net.canarymod.api.entity.Player;
import net.canarymod.api.world.WorldType;
import net.canarymod.hook.Hook;

/**
 * Login checks hook. Comes with ip, name and a kickReason that is to be returned,
 * and should be null if a player should not be kicked.
 * @author Chris Ksoll
 *
 */
public final class LoginChecksHook extends Hook {
    private String ip, name, world;
    private String kickReason = null;
    private WorldType dimensionType;

    public LoginChecksHook(String ip, String name, WorldType dimType, String world) {
        this.ip = ip;
        this.name = name;
        this.setWorld(world);
        this.setWorldType(dimType);

    }

    /**
     * Get the IP of the joining {@link Player}
     * @return
     */
    public String getIp() {
        return ip;
    }

    /**
     * Get the name of the joining {@link Player}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Get the currently set kick reason
     * @return
     */
    public String getKickReason() {
        return kickReason;
    }

    /**
     * Set the kick reason. Make it null to not kick the {@link Player}
     * @param reason
     */
    public void setKickReason(String reason) {
        kickReason = reason;
    }

    /**
     * Return the set of Data in this order: IP, NAME, KICK REASON (null if not to be kicked)
     */
    @Override
    public Object[] getDataSet() {
        return new Object[]{ip,name,kickReason};
    }

    public WorldType getWorldType() {
        return dimensionType;
    }

    public void setWorldType(WorldType dimensionType) {
        this.dimensionType = dimensionType;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }
}
