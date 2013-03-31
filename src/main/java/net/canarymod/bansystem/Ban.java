package net.canarymod.bansystem;


import net.canarymod.api.entity.living.humanoid.Player;


/**
 * Contains information regarding a ban
 *
 * @author Chris Ksoll
 *
 */
public class Ban {
    private String subject, ip, reason, banningPlayer;
    private boolean isIpBan = false;

    /**
     * When this ban will expire as unix timestamp. Note: It's probably
     * unnecessary all the way but we use a long here to dodge the 2038
     * problem. Just to be cool :P
     */
    private long timestamp;

    /**
     * Create a default ban object.
     * It's highly recommended to override the values before saving to db
     */
    public Ban() {
        setSubject("John Doe");
        setIp("xxx.xxx.xxx");
        setReason("Impersonating fictive characters");
        setTimestamp(-1);
        setIsIpBan(false);
    }

    public Ban(Player player, String reason, boolean ipBan) {
        setSubject(player.getName());
        if (ipBan) {
            setIp(player.getIP());
            setIsIpBan(true);
        } else {
            setIp("xxx.xxx.xxx");
            setIsIpBan(false);
        }
        setReason(reason);
        setTimestamp(-1);
    }

    public Ban(Player player, String reason, long timestamp, boolean ipBan) {
        setSubject(player.getName());
        if (ipBan) {
            setIp(player.getIP());
            setIsIpBan(true);
        } else {
            setIp("xxx.xxx.xxx");
            setIsIpBan(false);
        }
        setReason(reason);
        setTimestamp(timestamp);
    }

    /**
     * Get the banned subject (usually a player name)
     *
     * @return
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Set the banned Subject (usually a player name)
     *
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * If this is an IP ban, you can get the IP address here
     *
     * @return the ip address or null if this is not an ip ban
     */
    public String getIp() {
        return ip;
    }

    /**
     * If this is an IP Ban, set the IP address
     *
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * Get the banning reasons. It defaults to the funny reason of
     * "Impersonating fictive characters"
     *
     * @return
     */
    public String getReason() {
        return reason;
    }

    /**
     * Set the ban reason
     *
     * @param reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * Get the UNIX timestamp of when this ban will expire. You can use that
     * with a date formatter if you need to.
     *
     * @return
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Set the UNIX timestamp of when this ban will expire
     *
     * @param timestamp
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Check if this ban has expired
     *
     * @return
     */
    public boolean isExpired() {

        /*
         * System.currentTimeMillis() returns milliseconds since Jan 1, 1970.
         * Unixtimestamp is seconds since Jan 1, 1970. So we just do some simple
         * 3rd class math :3
         */
        return ((System.currentTimeMillis() / 1000L) >= timestamp) || (timestamp == -1);
    }

    public boolean isIpBan() {
        return isIpBan;
    }

    public void setIsIpBan(boolean isIpBan) {
        this.isIpBan = isIpBan;
    }

    public String getBanningPlayer() {
        return banningPlayer;
    }

    public void setBanningPlayer(String banningPlayer) {
        this.banningPlayer = banningPlayer;
    }
}
