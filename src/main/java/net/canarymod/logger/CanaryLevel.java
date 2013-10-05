package net.canarymod.logger;

import java.util.logging.Level;

import net.canarymod.chat.MessageReceiver;

/**
 * Canary specific Logger Levels
 *
 * @author Jason (darkdiplomat)
 */
public final class CanaryLevel extends Level {

    private static final long serialVersionUID = 100111001001111L;

    /* Level notes
     *
     * SEVERE: 1000
     * WARNING: 900
     * INFO: 800
     * CONFIG: 700
     * ____: 600 *
     * FINE: 500
     * FINER: 400
     * FINEST: 300
     * DEBUG: 200 **
     *
     *  * No Level Assigned
     *  ** UnOfficial Level Assignment
     */

    /** Canary SERVERMESSAGE Level (801), For use with {@link MessageReceiver#message(String)} */
    public static final CanaryLevel SERVERMESSAGE = new CanaryLevel("SERVERMESSAGE", 801);

    /** Canary CHAT Level (802). For use with loggin chat messages from players */
    public static final CanaryLevel CHAT = new CanaryLevel("CHAT", 802);

    /** Canary Server NOTICE Level (901), For use with {@link MessageReceiver#notice(String)} */
    public static final CanaryLevel NOTICE = new CanaryLevel("NOTICE", 901);

    /** Canary DERP Level (902), For those herp times */
    public static final CanaryLevel DERP = new CanaryLevel("DERP", 902);

    /** Canary DEBUG Level (200), For internal debugging */
    public static final CanaryLevel DEBUG = new CanaryLevel("DEBUG", 200);

    /** Canary PLUGIN_DEBUG Level (201), For plugin debugging */
    public static final CanaryLevel PLUGIN_DEBUG = new CanaryLevel("PLUGIN_DEBUG", 201);

    /**
     * Creates a new Canary Level
     *
     * @param name
     *         name of the level
     * @param lvl
     *         numeric value for the level
     */
    private CanaryLevel(String name, int lvl) {
        super(name, lvl);
    }

}
