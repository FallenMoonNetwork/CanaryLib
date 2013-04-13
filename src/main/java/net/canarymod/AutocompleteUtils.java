package net.canarymod;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;

/**
 * Auto-Complete Utility class
 * @author Brian (WWOL)
 * @author Chris (damagefilter)
 *
 */
public class AutocompleteUtils {

    /**
     * Autocompletes a word with passed options, optionally ignoring case.
     * @param word The word to be autocompleted
     * @param caseSensitive Whether the match should be case sensitive.
     * @param options The options <tt>word</tt> can be completed to
     * @return A list containing the matching options
     */
    public static StringBuilder autoCompleteFromOptions(String word, boolean caseSensitive, String... options) {
        StringBuilder matches = new StringBuilder(); // Reasonable default size?

        word = caseSensitive ? word : word.toLowerCase();

        for (String option : options) {
            if ((caseSensitive ? option : option.toLowerCase()).startsWith(word)) {
                matches.append(option).append("\u0000");
            }
        }

        return matches;
    }

    /**
     * Autocompletes a word with passed options, ignoring case.
     * @param word The word to be autocompleted.
     * @param options The options <tt>word</tt> can be completed to.
     * @return A list containing the matching options.
     */
    public static StringBuilder autoCompleteFromOptions(String word, String... options) {
        return autoCompleteFromOptions(word, false, options);
    }

    /**
     * Autocompletes a partial player name, optionally ignoring case.
     * @param partialName The partial player name.
     * @param caseSensitive Whether the match should be case sensitive.
     * @return A list containing matching player names.
     */
    public static StringBuilder autoCompleteNames(String partialName, boolean caseSensitive) {
        return autoCompleteFromOptions(partialName, caseSensitive, Canary.getServer().getPlayerNameList());
    }

    /**
     * Autocompletes a partial player name, ignoring case.
     * @param partialName The partial player name.
     * @return A list containing matching player names.
     */
    public static StringBuilder autoCompleteNames(String partialName) {
        return autoCompleteNames(partialName, false);
    }

    /**
     * Returns a <tt>String</tt> containing possible completions for
     * <tt>currentText</tt>.
     * @param currentText The text to be autocompleted.
     * @param player The player to autocomplete for.
     * @return List of available options
     */
    public static StringBuilder autoComplete(String currentText, MessageReceiver player) {
        StringBuilder matches = new StringBuilder();

        if (currentText.length() == 0 || currentText.charAt(0) != '/' && currentText.indexOf(' ') == -1) {
            // Not a command and in first word of text, check player names.
            // Start of line, add a colon to completed names for convenience
            for (Player p : Canary.getServer().getPlayerList()) {
                if (p.getName().toLowerCase().startsWith(currentText.toLowerCase())) {
                    matches.append(p.getName()).append(":"); // Note: no space here, because client splits on space.
                }
            }
        }
        else if (currentText.charAt(0) == '/') {
            // Is a command.
            if (currentText.indexOf(' ') > 0) {
                //Match commands and player names
                String subject = currentText.substring(currentText.lastIndexOf(' ') + 1);
                matches.append(Canary.commands().matchCommand(player, subject));
                matches.append(autoCompleteNames(subject));
            } else {
                // Haven't completed the command, get some matches if has permission for command.
                matches.append(Canary.commands().matchCommand(player, currentText.replace("/", "")));
            }
        }
        else {
         // Not a command and not in first word, get player names without adding the colon.
            String[] splitText = currentText.split("\\s+");
            String toComplete = splitText[splitText.length - 1];
            StringBuilder strb = autoCompleteNames(toComplete);
            if(strb != null && strb.length() > 0) {
                matches = strb;
            }
        }
        return matches;
        //return Canary.glueString(options.toArray(new String[options.size()]), 0, "\u0000");
    }

}
