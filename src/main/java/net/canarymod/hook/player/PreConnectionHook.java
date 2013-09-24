package net.canarymod.hook.player;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.DimensionType;
import net.canarymod.hook.Hook;

/**
 * Login checks hook. Comes with ip, name and a kickReason that is to be returned,
 * and should be null if a player should not be kicked.
 *
 * @author Chris (damagefilter)
 */
public final class PreConnectionHook extends Hook {
    private String ip, name, world;
    private String kickReason = null;
    private DimensionType dimensionType;

    public PreConnectionHook(String ip, String name, DimensionType dimType, String world) {
        this.ip = ip;
        this.name = name;
        this.setWorld(world);
        this.setWorldType(dimType);

    }

    /**
     * Get the IP of the joining {@link Player}
     *
     * @return
     */
    public String getIp() {
        return ip;
    }

    /** Get the name of the joining {@link Player} */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Get the currently set kick reason
     *
     * @return
     */
    public String getKickReason() {
        return kickReason;
    }

    /**
     * Set the kick reason. Make it null to not kick the {@link Player}
     *
     * @param reason
     */
    public void setKickReason(String reason) {
        kickReason = reason;
    }

    public DimensionType getWorldType() {
        return dimensionType;
    }

    public void setWorldType(DimensionType dimensionType) {
        this.dimensionType = dimensionType;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    @Override
    public final String toString() {
        return String.format("%s[Player Name=%s, IP=%s, World=%s, Kicked Reason=%s, World Type=%s]", getName(), name, ip, world, kickReason, dimensionType);
    }
}
