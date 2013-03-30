package net.canarymod;


import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;


/**
 * CanaryMod Log manager. All static methods.
 * You can get an appropriate logger for your plugin here.
 * 
 * @author Chris Ksoll
 * @author Jos Kuijpers
 * 
 */
public class Logman extends Logger {
    private String name;
    private final static HashMap<String, Logman> loggers = new HashMap<String, Logman>();

    public Logman(String name) {
        super(name, null);
        this.name = name;
        loggers.put(name, this);
    }

    @Override
    public void log(LogRecord logRecord) {
        //        if(Configuration.getServerConfig() == null || !Configuration.getServerConfig().isLogging()) {
        logRecord.setMessage(new StringBuilder("[").append(name).append("] ").append(logRecord.getMessage()).toString());
        super.log(logRecord);
        //        }
    }

    /**
     * Get a Logman for the name given
     * @param name
     * @return
     */
    public static Logman getLogman(String name) {
        if (!loggers.containsKey(name)) {
            Logman logman = new Logman(name);

            logman.setParent(Logger.getLogger("Minecraft-Server"));
            loggers.put(name, logman);
        }
        return loggers.get(name);
    }

    /**
     * Log a message with INFO level.
     * @param message
     */
    public void logInfo(String message) {
        log(Level.INFO, message);
    }

    /**
     * Log a message with WARNING level
     * @param message
     */
    public void logWarning(String message) {
        log(Level.WARNING, message);
    }

    /**
     * Log a message with SEVERE level
     * @param message
     */
    public void logSevere(String message) {
        log(Level.SEVERE, message);
    }

    /**
     * Dump a stacktrace to the log
     * @param message
     * @param e
     */
    public void logStacktrace(String message, Throwable e) {
        log(Level.WARNING, message, e);
    }

    /**
     * Convenience shortcut to System.out.println().
     * Prints to the output stream on a new line
     * @param message
     */
    public static void println(String message) {
        System.out.println(message);
    }

    /**
     * Convenience shortcut to System.out.print().<br>
     * Prints to the output stream on the same line
     * @param message
     */
    public static void print(String message) {
        System.out.print(message);
    }
}
