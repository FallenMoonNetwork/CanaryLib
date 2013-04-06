package net.canarymod.api.inventory.recipes;

import net.canarymod.api.inventory.Item;

/**
 * Crafting recipe helper
 * 
 * @author Jason (darkdiplomat)
 */
public final class CraftingRecipe {
    private boolean hasShape = false;
    private Item result;
    private RecipeRow[] rows;
    private Item[] items;
    
    /**
     * Constructs a new SHAPED CraftingRecipe
     * 
     * @param result
     *            the {@link Item} result of the recipe
     * @param rows
     *            the {@link RecipeRow}s that make up the recipe
     */
    public CraftingRecipe(Item result, RecipeRow... rows) {
        this.result = result;
        this.rows = rows;
        this.hasShape = true;
    }

    /**
     * Constructs a new SHAPELESS CraftingRecipe
     * 
     * @param result
     *            the {@link Item} result of the recipe
     * @param items
     *            the {@link Item}s that are needed to make the result
     */
    public CraftingRecipe(Item result, Item... items) {
        this.result = result;
        this.items = items;
    }

    /**
     * Checks if this recipe has a shape
     * 
     * @return {@code true} if shaped; {@code false} if not
     */
    public boolean hasShape() {
        return hasShape;
    }

    /**
     * Gets the {@link Item} result
     * 
     * @return the {@link Item} result
     */
    public Item getResult() {
        return result;
    }

    /**
     * If this is a shaped recipe, gets the {@link RecipeRow} array that makes up the shape
     * 
     * @return the rows
     */
    public RecipeRow[] getRows() {
        return rows;
    }

    /**
     * If this is a shapeless recipe, get the {@link Item} array that makes up the recipe
     * 
     * @return the items
     */
    public Item[] getItems() {
        return items;
    }
}
