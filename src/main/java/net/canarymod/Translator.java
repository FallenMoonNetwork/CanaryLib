package net.canarymod;


import java.text.MessageFormat;
import net.canarymod.config.Configuration;
import net.visualillusionsent.utils.LocaleHelper;

/**
 * This class handles internationalization (aka i18n).
 * It will look up translations from a given key to the language that is currently active.
 * @author chris
 *
 */
public class Translator extends LocaleHelper {

    private static Translator instance = new Translator();

    private Translator() {
        super();
        localeCodeOverride = Configuration.getServerConfig().getLanguageCode();
    }

    /**
     * Translates a message into the current system language if that mapping exists
     * 
     * @param messageKey
     *            the message key to used to get the message from the Language file
     * @return the translated message
     */
    public static String translate(String messageKey) {
        return Translator.instance.localeTranslate(messageKey);
    }

    /**
     * Translates the given message key and applies formatting according to standard Java formatting rules
     * 
     * @param messageKey
     *            the message key to used to get the message from the Language file
     * @param format
     *            the arguments used to format the message
     * @return the translated message
     * @see MessageFormat
     */
    public static String translateAndFormat(String messageKey, Object...format) {
        return Translator.instance.localeTranslateMessage(messageKey, format);
    }

    /**
     * Returns the instance for this Translator.
     * For translation purposes, please use the provided static methods.
     * This here is for working with the command system
     * 
     * @return the Translator instance
     */
    public static Translator getInstance() {
        return instance;
    }
}
