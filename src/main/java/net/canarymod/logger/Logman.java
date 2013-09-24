package net.canarymod.logger;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import net.canarymod.chat.TextFormat;

/**
 * CanaryMod Log manager.
 * <p/>
 * You can get an appropriate logger for your plugin here.
 *
 * @author Chris (damagefilter)
 * @author Jos Kuijpers
 * @author Jason (darkdiplomat)
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
        logRecord.setMessage("[" + name + "] " + TextFormat.consoleFormat(logRecord.getMessage()));
        super.log(logRecord);
    }

    /**
     * Get a Logman for the name given
     *
     * @param name
     *         the name of the Logger to use
     *
     * @return new Logman
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
     *
     * @param message
     *         the message to be logged
     */
    public void logInfo(String message) {
        log(Level.INFO, message);
    }

    /**
     * Log a message with WARNING level
     *
     * @param message
     *         the message to be logged
     */
    public void logWarning(String message) {
        log(Level.WARNING, message);
    }

    /**
     * Log a message with SEVERE level
     *
     * @param message
     *         the message to be logged
     */
    public void logSevere(String message) {
        log(Level.SEVERE, message);
    }

    /**
     * Logs a debug message.
     *
     * @param message
     *         the message to be logged
     */
    public void logDebug(String message) {
        log(CanaryLevel.DEBUG, message);
    }

    /**
     * Log a derpy message
     *
     * @param message
     *         the message to be logged
     */
    public void logDerp(String message) {
        log(CanaryLevel.DERP, message);
    }

    /**
     * Log a Plugin Debug message
     *
     * @param message
     *         the message to be logged
     */
    public void logPluginDebug(String message) {
        log(CanaryLevel.PLUGIN_DEBUG, message);
    }

    /**
     * Dump a stacktrace to the log
     *
     * @param message
     *         the message to be logged
     * @param thrown
     *         the {@link Throwable} thrown
     */
    public void logStacktrace(String message, Throwable thrown) {
        log(Level.SEVERE, message, thrown);
    }

    /**
     * Convenience shortcut to System.out.println().
     * Prints to the output stream on a new line
     *
     * @param message
     *         the message to be printed to the console
     */
    public static void println(String message) {
        System.out.println(message);
    }

    /**
     * Convenience shortcut to System.out.print().<br>
     * Prints to the output stream on the same line
     *
     * @param message
     *         the message to be printed to the console
     */
    public static void print(String message) {
        System.out.print(message);
    }
}
