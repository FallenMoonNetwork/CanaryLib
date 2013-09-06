package net.canarymod.api.inventory.recipes;

import java.util.List;

import net.canarymod.api.inventory.Item;

/**
 * ShapelessRecipes wrapper interface
 *
 * @author Jason (darkdiplomat)
 */
public interface ShapelessRecipe extends Recipe {

    /**
     * Gets a list of {@link Item} that make up this recipe
     *
     * @return a list of {@link Item}
     */
    public List<Item> getRecipeItems();

}
