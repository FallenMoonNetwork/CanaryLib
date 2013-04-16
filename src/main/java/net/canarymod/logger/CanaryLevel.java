package net.canarymod.logger;

import java.util.logging.Level;

/**
 * Canary specific Logger Levels<br>
 * Levels defined here are intValue generated between 801 and 899
 * 
 * @author Jason (darkdiplomat)
 */
public final class CanaryLevel extends Level {
    
    private static final long serialVersionUID = 100111001001111L;

    //NOTE: Increment the level number, keeping it between 801 and 899. 800 is INFO and 900 is WARNING
    public static final CanaryLevel SERVERMESSAGE = new CanaryLevel("SERVERMESSAGE", 801);
    public static final CanaryLevel NOTICE = new CanaryLevel("NOTICE", 802);
    public static final CanaryLevel DERP = new CanaryLevel("DERP", 803);
    public static final CanaryLevel DEBUG = new CanaryLevel("DEBUG", 804);
    
    private CanaryLevel(String name, int lvl){
        super(name, lvl);
    }

}
