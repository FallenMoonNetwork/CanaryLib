package net.canarymod;


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
     * @param messageKey
     * @return
     */
    public static String translate(String messageKey) {
        return Translator.instance.localeTranslate(messageKey);
    }

    /**
     * Translates the given message key and applies formatting according to standard Java formatting rules
     * @param messageKey
     * @param format
     * @return
     */
    public static String translateAndFormat(String messageKey, Object...format) {
        return Translator.instance.localeTranslateMessage(messageKey, format);
    }

    /**
     * Returns the instance for this Translator.
     * For translation purposes, please use the provided static methods.
     * This here is for working with the command system
     * @return
     */
    public static Translator getInstance() {
        return instance;
    }
}
