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
    private RecipeRow[] rows = new RecipeRow[0];
    private Item[] items = new Item[0];

    /**
     * Constructs a new SHAPED CraftingRecipe
     *
     * @param result
     *         the {@link Item} result of the recipe
     * @param rows
     *         the {@link RecipeRow}s that make up the recipe
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
     *         the {@link Item} result of the recipe
     * @param items
     *         the {@link Item}s that are needed to make the result
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

    public final boolean equals(Object obj) {
        if (!(obj instanceof CraftingRecipe)) {
            return false;
        }
        else if (obj == this) {
            return true;
        }
        CraftingRecipe theRecipe = (CraftingRecipe) obj;
        if (result.getType() != theRecipe.getResult().getType()) {
            return false;
        }
        if (!hasShape() && !theRecipe.hasShape()) {
            if (items.length != theRecipe.getItems().length) {
                return false;
            }
            for (int index = 0; index < items.length; index++) {
                if (items[index].getType() != theRecipe.getItems()[index].getType()) {
                    return false;
                }
            }
            return true;
        }
        else {
            if (rows.length != theRecipe.getRows().length) {
                return false;
            }
            for (int index = 0; index < rows.length; index++) {
                if (!rows[index].equals(theRecipe.getRows()[index])) {
                    return false;
                }
            }
            return true;
        }
    }
}
