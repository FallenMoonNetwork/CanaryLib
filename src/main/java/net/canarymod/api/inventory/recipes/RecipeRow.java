package net.canarymod.api.inventory.recipes;

import net.canarymod.api.inventory.Item;

/**
 * Recipe row helper for Shaped Recipes
 * 
 * @author Jason (darkdiplomat)
 */
public final class RecipeRow {
    private String shape;
    private char[] identifiers;
    private Item[] items;
    
    /**
     * Constructs a new RecipeRow
     * 
     * @param shape
     *            the shape of the recipe, use spaces for empty slots, maximum of 3 characters.
     * @param items
     *            the {@link Item}s in the row, item index should match the character's first appearance index ignoring spaces.<br>
     *            Example: (underscore used to show spaces clearer)<br>
     *            shape = "X_Y", items = new Item[]{ itemA, itemB }, itemA = X and itemB = Y<br>
     *            or shape = "__X", items = new Item[]{ itemA }, itemA = X <br>
     *            or shape = "YYY", items = new Item[]{ itemA }, since its all Y only 1 item needed
     */
    public RecipeRow(String shape, Item[] items) {
        this.shape = verifyShape(shape);
        this.identifiers = getIdentifiersFromShape(this.shape);
        this.items = items;
    }

    /* Verifies the shape is at max 3 characters */
    private String verifyShape(String shape) {
        if (shape.length() < 3) {
            return shape;
        } else {
            return shape.substring(0, 3);
        }
    }

    /* Pulls the chars from the shape eliminating matching chars */
    private char[] getIdentifiersFromShape(String shape) {
        if (shape.length() < 3) {
            if (shape.length() == 1 || shape.charAt(0) == shape.charAt(1)) {
                return new char[]{ shape.charAt(0) };
            }
            return new char[]{ shape.charAt(0), shape.charAt(1) };
        }
        char[] chars = new char[3];
        shape.getChars(0, 3, chars, 0);
        if (chars[0] == chars[1] && chars[0] == chars[2]) {
            return new char[]{ chars[0] };
        } else if (chars[0] == chars[1]) {
            return new char[]{ chars[0], chars[2] };
        } else if (chars[0] == chars[2] || chars[1] == chars[2]) {
            return new char[]{ chars[0], chars[1] };
        }
        return chars;
    }

    /**
     * Gets the shape of the Row
     * 
     * @return the shape
     */
    public String getShape() {
        return shape;
    }

    /**
     * Gets the character identifiers
     * 
     * @return the char array
     */
    public char[] getIdentifiers() {
        return identifiers;
    }

    /**
     * Gets the items matching chars
     * 
     * @return the items
     */
    public Item[] getItems() {
        return items;
    }
}
