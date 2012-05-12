package net.canarymod;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CanaryMod Log manager Singleton
 * Use get() to get the Logger functionality
 * @author Chris
 *
 */
public class LogManager {
    private final static Logger mclog = Logger.getLogger("Minecraft");
    
    private static LogManager instance = null;
    
    
    private LogManager() {
        //make private to avoid multiple instances
    }
    public static LogManager get() {
        if(instance == null) {
            instance = new LogManager();
        }
        return instance;
    }
    
    /**
     * Log with INFO level
     * @param message
     */
    public void logInfo(String message) {
        mclog.log(Level.INFO, message);
    }
    
    /**
     * Log with warning level
     * @param message
     */
    public void logWarning(String message) {
        mclog.log(Level.WARNING, message);
    }
    
    public void logSevere(String message) {
        mclog.log(Level.SEVERE, message);
    }
    /**
     * Print a stacktrace to the server.log with WARNING level
     * @param e
     */
    public void logStackTrace(Throwable e) {
        mclog.log(Level.WARNING, stackTraceToString(e));
    }
    
    private String stackTraceToString(Throwable e) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : e.getStackTrace()) {
            sb.append(element.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
