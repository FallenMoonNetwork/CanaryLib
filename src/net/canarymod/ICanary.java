package net.canarymod;

import java.util.concurrent.TimeUnit;

/**
 * The interface to the brains of the bird!
 * AKA Utils
 * @author Chris
 *
 */
public abstract class ICanary {
    
    /**
     * Get the unix timestamp for the current time
     * @return
     */
    public static long getUnixTimestamp() {
        return (System.currentTimeMillis() / 1000L);
    }
    
    /**
     * Parse number of seconds for the given time and TimeUnit String<br>
     * Example: long 1 String HOUR will give you number of seconds in 1 hour.<br>
     * This is used to work with unix timestamps.
     * @param time
     * @param timeUnit MINUTES, HOURS, DAYS, WEEKS, MONTHS
     * @return
     */
    public static long parseTime(long time, String timeUnit) {

        if (timeUnit.toLowerCase().startsWith("minute")) {
            time *= 60;
        }
        else if (timeUnit.toLowerCase().startsWith("hour")) {
            time *= 3600;
        }
        else if (timeUnit.toLowerCase().startsWith("day")) {
            time *= 86400;
        } 
        else if (timeUnit.toLowerCase().startsWith("week")) {
            time *= 604800;
        }
        else if(timeUnit.toLowerCase().startsWith("month")) {
            time *= 2629743;
        }
        return time;
    }
    
    /**
     * Parse number of seconds for the given time and TimeUnit<br>
     * Example: long 1 String {@link TimeUnit#HOURS} will give you number of seconds in 1 hour.<br>
     * This is used to work with unix timestamps.
     * @param time
     * @param unit
     * @return
     */
    public static long parseTime(long time, TimeUnit unit) {
        return unit.convert(time, TimeUnit.SECONDS);
    }
}
