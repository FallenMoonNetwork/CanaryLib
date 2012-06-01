package net.canarymod.hook.player;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

/**
 * The delegate for {@link FoodLevelHook}
 * @author Jason Jones
 *
 */
public class FoodLevelDelegate extends HookDelegate{

    @Override
    public Hook callHook(Hook hook) {
        switch(hook.getType()){
        case FOODEXHAUSTION_CHANGE:
            return this.li.onFoodExhaustionChange((FoodLevelHook) hook);
        case FOODLEVEL_CHANGE:
            return this.li.onFoodLevelChange((FoodLevelHook) hook);
        case FOODSATURATION_CHANGE:
            return this.li.onFoodSaturationChange((FoodLevelHook) hook);
        }
        return hook;
    }

}
