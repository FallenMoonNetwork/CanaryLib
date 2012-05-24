package net.canarymod.bansystem;

import java.util.ArrayList;

import net.canarymod.Canary;
import net.canarymod.Logman;
import net.canarymod.api.entity.Player;
import net.canarymod.backbone.Backbone;
import net.canarymod.backbone.BackboneBans;
import net.canarymod.database.Database;

/**
 * Used to issue bans
 * 
 * @author Chris
 * 
 */
public class BanManager {
    private BackboneBans backbone;
    private ArrayList<Ban> bans = new ArrayList<Ban>();

    public BanManager(Backbone bone, Database.Type type) {
        backbone = (BackboneBans) bone.getBackbone(Backbone.System.BANS, type);
        bans = backbone.loadBans();
    }

    /**
     * Issue a permanent ban for this player with a given reason
     * 
     * @param player
     * @param reason
     */
    public void issueBan(Player player, String reason) {
        Ban ban = new Ban(player, reason, false);
        bans.add(ban);
        backbone.addBan(ban);
    }

    /**
     * Issue a temporary ban.
     * 
     * @param player
     * @param reason
     * @param time
     *            The time component must be NUMBER HOUR/DAY/WEEK/MONTH. <br>
     *            Example: /ban player Being incredibly stupid 5 HOURS If you
     *            put nothing as time unit, it will evaluate as HOURS!
     */
    public void issueBan(Player player, String reason, String time) {
        long timeToAdd = 0L;
        try {
            timeToAdd = parseTimeSpec(time);
        } catch (NumberFormatException e) {
        	Logman.logWarning("Invalid time for temp ban specified(" + time + "). Skipping!");
        }
        Ban ban = new Ban(player, reason, Canary.getUnixTimestamp() + timeToAdd, false);
        bans.add(ban);
        backbone.addBan(ban);
    }

    /**
     * Ban player by ID
     * 
     * @param player
     * @param reason
     */
    public void issueIpBan(Player player, String reason) {
        Ban ban = new Ban(player, reason, true);
        bans.add(ban);
        backbone.addBan(ban);
    }

    /**
     * Issue an IP Ban with the given amount of time
     * 
     * @param player
     * @param reason
     * @param time
     */
    public void issueIpBan(Player player, String reason, String time) {
        long timeToAdd = 0L;
        try {
            timeToAdd = parseTimeSpec(time);
        } catch (NumberFormatException e) {
        	Logman.logWarning("Invalid time for temp ban specified. Skipping!");
        }
        Ban ban = new Ban(player, reason, Canary.getUnixTimestamp() + timeToAdd, true);
        bans.add(ban);
        backbone.addBan(ban);
    }

    /**
     * Check if banned and unban if ban has expired. Returns true if still
     * banned, false otherwise. THIS WILL ALSO WORK FOR IP!
     * 
     * @param subject
     * @return
     */
    public boolean isBanned(String subject) {
        Ban test = null;
        for (Ban b : bans) {
            if (b.getSubject().equals(subject)) {
                test = b;
                break;
            } else if (b.getIp().equals(subject)) {
                test = b;
                break;
            }
        }
        if (test == null) {
            return false;
        } else if (test.isExpired()) {
            if (test.isIpBan()) {
                backbone.liftIpBan(test.getIp());
            } else {
                backbone.liftBan(test.getSubject());
            }
            bans.remove(test);
            return false;
        } else {
            return true;
        }
    }

    /**
     * Unban a subject, player or ip
     * 
     * @param subject
     */
    public void unban(String subject) {
        Ban test = null;
        for (Ban b : bans) {
            if (b.getSubject().equals(subject)) {
                test = b;
                break;
            } else if (b.getIp().equals(subject)) {
                test = b;
                break;
            }
        }
        if (test == null) {
            return;
        }
        if (test.isIpBan()) {
            backbone.liftIpBan(test.getIp());
        } else {
            backbone.liftBan(test.getSubject());
        }
        bans.remove(test);
    }

    /**
     * Unban this player (this will NOT work with IPBans!)
     * 
     * @param player
     */
    public void unban(Player player) {
        Ban test = null;
        for (Ban b : bans) {
            if (!b.isIpBan()) {
                if (b.getSubject().equals(player.getName())) {
                    test = b;
                    break;
                }
            }
        }
        if (test == null) {
            return;
        }
        backbone.liftBan(test.getSubject());
        bans.remove(test);
    }

    //TODO: Update Ban method!
    /**
     * Take a string and parse an amount of seconds. A String should be
     * formatted like this: number hours|days|months Ex: 1 month and it will
     * return the amount of seconds that contain one month
     * 
     * @param ts
     * @return long amount of seconds
     */
    private long parseTimeSpec(String ts) throws NumberFormatException {
        String[] split = ts.split(" ");

        if (split.length < 2) return -1;
        long seconds;
        try {
            seconds = Integer.parseInt(split[0]);
        } catch (NumberFormatException ex) {
            throw ex;
        }

        if (split[1].toLowerCase().startsWith("hour")) {
            seconds *= 3600;
        } else if (split[1].toLowerCase().startsWith("day")) {
            seconds *= 86400;
        } else if (split[1].toLowerCase().startsWith("week")) {
            seconds *= 604800;
        } else if (split[1].toLowerCase().startsWith("month")) {
            seconds *= 2629743;
        }
        return seconds;
    }
}
