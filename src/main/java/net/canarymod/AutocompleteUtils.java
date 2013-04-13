package net.canarymod;

import java.util.ArrayList;
import java.util.List;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.commandsys.CanaryCommand;

public class AutocompleteUtils {

    /**
     * Autocompletes a word with passed options, optionally ignoring case.
     * @param word The word to be autocompleted
     * @param caseSensitive Whether the match should be case sensitive.
     * @param options The options <tt>word</tt> can be completed to
     * @return A list containing the matching options
     */
    public static List<String> autoCompleteFromOptions(String word, boolean caseSensitive, String... options) {
        List<String> matches = new ArrayList<String>(options.length / 5); // Reasonable default size?

        word = caseSensitive ? word : word.toLowerCase();

        for (String option : options) {
            if ((caseSensitive ? option : option.toLowerCase()).startsWith(word)) {
                matches.add(option);
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
    public static List<String> autoCompleteFromOptions(String word, String... options) {
        return autoCompleteFromOptions(word, false, options);
    }

    /**
     * Autocompletes a partial player name, optionally ignoring case.
     * @param partialName The partial player name.
     * @param caseSensitive Whether the match should be case sensitive.
     * @return A list containing matching player names.
     */
    public static List<String> autoCompleteNames(String partialName, boolean caseSensitive) {
        return autoCompleteFromOptions(partialName, caseSensitive, Canary.getServer().getPlayerNameList());
    }

    /**
     * Autocompletes a partial player name, ignoring case.
     * @param partialName The partial player name.
     * @return A list containing matching player names.
     */
    public static List<String> autoCompleteNames(String partialName) {
        return autoCompleteNames(partialName, false);
    }

    /**
     * Returns a <tt>String</tt> containing possible completions for
     * <tt>currentText</tt>.
     * @param currentText The text to be autocompleted.
     * @param player The player to autocomplete for.
     * @return A null-char-separated String of options.
     */
    public List<String> autoCompleteForPlayer(String currentText, Player player) {
        List<String> options = new ArrayList<String>();

        if (currentText.length() == 0 || currentText.charAt(0) != '/' && currentText.indexOf(' ') == -1) {
            // Not a command and in first word of text, check player names.
            // Start of line, add a colon to completed names for convenience
            for (Player p : Canary.getServer().getPlayerList()) {
                if (p.getName().toLowerCase().startsWith(currentText.toLowerCase())) {
                    options.add(p.getName() + ":"); // Note: no space here, because client splits on space.
                }
            }
        } else if (currentText.charAt(0) == '/') {
            // Is a command.
            if (currentText.indexOf(' ') > 0) {
                // In parameters of command, get player names to complete command if has permission for command.
                String commandName = currentText.split("\\s+")[0].substring(1);
                CanaryCommand command = /* Get command from string */ null; // FIXME
                if (command.canUse(player)) {
                    List<String> commandOptions = autoCompleteNames(currentText.substring(currentText.lastIndexOf(' ') + 1));
                    if (commandOptions != null) {
                        options.addAll(commandOptions);
                    }
                }
            } else {
                // Haven't completed the command, get some matches if has permission for command.
                for (String commandName : /* Get list of command names */ (List<String>) null) { // FIXME
                    if (commandName.startsWith(currentText)) {
                        CanaryCommand command = /* Get command from string */ null; // FIXME
                        if (command.canUse(player)) {
                            options.add(commandName);
                        }
                    }
                }
            }
        } else {
            // Not a command and not in first word, get player names without adding the colon.
            String[] splitText = currentText.split("\\s+");
            String toComplete = splitText[splitText.length - 1];

            options = autoCompleteNames(toComplete);
        }

        return options;
        //return Canary.glueString(options.toArray(new String[options.size()]), 0, "\u0000");
    }

}
