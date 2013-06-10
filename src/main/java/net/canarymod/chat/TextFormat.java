package net.canarymod.chat;

/**
 * This class contains James' color list and additionally formatting options for
 * underlining, bolding, striking text etc etc
 * 
 * @author Chris
 * @author Jason (darkdiplomat)
 * @author James
 */
public class TextFormat extends Colors {

    /**
     * The character sequence to make the following text bold.
     */
    public static final String BOLD = "\u00A7l";

    /**
     * The character seqence to make the following text striked.
     */
    public static final String STRIKE = "\u00A7m";

    /**
     * The characted sequence to make the following text underlined.
     */
    public static final String UNDERLINED = "\u00A7n";

    /**
     * The character sequence to make the following text italic.
     */
    public static final String ITALICS = "\u00A7o";

    /**
     * The character sequence to display everything as completely random
     */
    public static final String RANDOM = "\u00A7k";
    /**
     * The character sequence to create a new line of text
     */
    public static final String NEW_LINE = "\n";

    /**
     * The character sequence to reset all text formatting.
     */
    public static final String RESET = "\u00A7r";

    /**
     * Removes all minecraft-style formatting from <tt>text</tt>.
     * 
     * @param text
     *            The text to be stripped of formatting.
     * @return <tt>text</tt> with all color/style tags stripped.
     */
    public static final String removeFormatting(String text) {
        return text.replaceAll("\u00A7[A-FK-NRa-fk-nr0-9]", "");
    }

    public static String getLastColor(String input) {
        if (input.contains(MARKER)) {
            int mark = input.lastIndexOf(MARKER);
            if (mark == input.length()) {
                return getLastColor(input.substring(0, mark));
            }
            else {
                return input.substring(mark, mark + 2);
            }
        }
        return null;
    }

}
