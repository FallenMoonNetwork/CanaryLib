package net.canarymod.hook.player;

import net.canarymod.hook.Hook;

/**
 * Login checks hook. Comes with ip, name and a kickReason that is to be returned,
 * and should be null if a player should not be kicked.
 * @author Chris Ksoll
 *
 */
public class LoginChecksHook extends Hook {
    private String ip, name;
    private String kickReason = null;
    
    public LoginChecksHook(String ip, String name) {
        this.ip = ip;
        this.name = name;
        this.type = Type.LOGINCHECKS;
    }
    
    /**
     * Get the IP of the joining player
     * @return
     */
    public String getIp() {
        return ip;
    }
    
    /**
     * Get the name of the joining player
     */
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
     * Set the kick reason. Make it null to not kick the player
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

}
