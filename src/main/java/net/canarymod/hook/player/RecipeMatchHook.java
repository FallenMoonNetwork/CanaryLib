package net.canarymod.hook.player;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.canarymod.hook.CancelableHook;

public class RecipeMatchHook extends CancelableHook
{
    private Player player;
    private Inventory craftingMatrix;
    private Item recipeResult;

    /**
     * Creates a new RecipeMatchHook.
     * @param player The crafting player.
     * @param craftingMatrix The crafting inventory used.
     * @param recipeResult The recipe's result.
     */
    public RecipeMatchHook(Player player, Inventory craftingMatrix, Item recipeResult)
    {
        this.player = player;
        this.craftingMatrix = craftingMatrix;
        this.recipeResult = recipeResult;
    }

    /**
     * Gets the crafting player.
     * @return The crafting player.
     */
    public Player getPlayer()
    {
        return this.player;
    }

    /**
     * Returns the crafting inventory the player is working on.
     * @return The crafting inventory the player is working on.
     */
    public Inventory getCraftingMatrix()
    {
        return this.craftingMatrix;
    }

    /**
     * Returns the recipe's result item.
     * @return The recipe's result item.
     */
    public Item getRecipeResult()
    {
        return this.recipeResult;
    }

    /**
     * Sets the recipe's result.
     * @param recipeResult The recipe's result.
     */
    public void setRecipeResult(Item recipeResult)
    {
        this.recipeResult = recipeResult;
    }
    /**
     * Return the set of Data in this order: PLAYER, PREFIX, MESSAGE
     */
    @Override
    public Object[] getDataSet() {
        return new Object[] {player, craftingMatrix, recipeResult};
    }
}
