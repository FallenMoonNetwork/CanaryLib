package net.canarymod;

/**
 * This class contains James' color list and additionally formatting options for
 * underlining, bolding, striking text etc etc
 * 
 * @author Chris
 * @author James
 * 
 */
public class TextFormat extends Colors {
    public static final String Bold = "\u00A7l";
    public static final String Strike = "\u00A7m";
    public static final String Underlined = "\u00A7n";
    public static final String Italics = "\u00A7o";
    public static final String Reset = "\u00A7r";
    
    public static final String removeFormatting(String text){
        return text.replaceAll("\u00A70|\u00A71|\u00A72|\u00A73|\u00A74|\u00A75|\u00A76|\u00A77|\u00A78|\u00A79|\u00A7a|\u00A7b|\u00A7c|\u00A7d|\u00A7e|\u00A7f|" +
        		"\u00A7l|\u00A7m|\u00A7n|\u00A7o|\u00A7r", "");
    }
}
