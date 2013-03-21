package net.canarymod.hook;

import net.canarymod.plugin.PluginListener;

/**
 * A basic hook you can implement to create custom hooks. System Hooks also
 * extend this
 * 
 * @author Chris Ksoll
 * @author Jason Jones
 * 
 */
public abstract class Hook {
    protected Type type = Type.NUM_HOOKS;
    protected boolean isCanceled;

    public Hook.Type getType() {
        return type;
    }

    /**
     * Get the name of this hook.
     * 
     * @return
     */
    public String getName() {
        return getClass().getSimpleName();
    }
    
    /**
     * Returns true if the hook is canceled.
     * @return True if the hook is canceled.
     */
    public boolean isCanceled() {
        return false;
    }

    /**
     * Returns an Object set of data this Hook contains
     * 
     * @return
     */
    public abstract Object[] getDataSet();
    
    /**
     * Dispatches this hook to the given plugin listener.
     * @param listener The listener to dispatch the hook to.
     */
    public abstract void dispatch(PluginListener listener);

    public int hashCode() {
        int hash = getClass().getSimpleName().length();
        return hash * getClass().getSimpleName().hashCode() + 2;
    }

    public enum Type {
        
        /**
         * Calls {@link PluginListener#onArmSwing }
         */
        ARM_SWING, // 
        /**
         * Calls {@link PluginListener#onBan }
         */
        BAN, //
        /**
         * Calls {@link PluginListener#onBlockBreak }
         */
        BLOCK_BREAK, //
        /**
         * Calls {@link PluginListener#onBlockLeftClicked }
         */
        BLOCK_LEFTCLICKED, // 
        /**
         * Calls {@link PluginListener#onBlockPhysics }
         */
        BLOCK_PHYSICS, // 
        /**
         * Calls {@link PluginListener#onBlockPlace }
         */
        BLOCK_PLACE, //
        /**
         * Calls {@link PluginListener#onBlockRightClicked) }
         */
        BLOCK_RIGHTCLICKED, //
        /**
         * Calls {@link PluginListener#onBlockUpdate }
         */
        BLOCK_UPDATE, // 
        /**
         * Breed hook, called when a player is about to make animals love each other
         */
        BREED,
        /**
         * Calls {@link PluginListener#onChat }
         */
        CHAT, //
        /**
         * Calls {@link PluginListener#onChunkCreated }
         */
        CHUNK_CREATED, //
        /**
         * Calls {@link PluginListener#onChunkCreation }
         */
        CHUNK_CREATION, //
        /**
         * Calls {@link PluginListener#onChunkLoaded }
         */
        CHUNK_LOADED, //
        /**
         * Calls {@link PluginListener#onChunkUnloaded }
         */
        CHUNK_UNLOADED, //
        /**
         * Calls {@link PluginListener#onCloseInventory }
         */
        CLOSE_INVENTORY, //
        /**
         * Calls {@link PluginListener#onCommand(net.canarymod.hook.command.PlayerCommandHook) }
         */
        COMMAND, //
        /**
         * Calls {@link PluginListener#onConsoleCommand(net.canarymod.hook.command.ConsoleCommandHook) }
         */
        CONSOLECOMMAND, //
        /**
         * Calls cow milk listener
         */
        COW_MILK, //
        /**
         * Calls {@link PluginListener#onCraft() }
         */
        CRAFT, //
        /**
         * Calls {@link PluginListener#onDamage }
         */
        DAMAGE, //
        /**
         * Calls {@link PluginListener#onDecorate }
         */
        DECORATE,
        
        /**
         * Calls {@link PluginListener#onDimensionSwitch }
         */
        DIMENSION_SWITCH,
        
        /**
         * Calls{@link PluginListener#onDispense }
         */
        DISPENSE, // 
        /**
         * Calls {@link PluginListener#onEat }
         */
        EAT, //
        /**
         * Calls {@link PluginListener#onEnchant }
         */
        ENCHANT, // 
        /**
         * Class {@link PluginListener#onEndermanDrop }
         */
        ENDERMAN_DROP, //
        /**
         * Class {@link PluginListener#onEndermanPickup }
         */
        ENDERMAN_PICKUP, //
        /**
         * Class {@link PluginListener#onEntityDespawn }
         */
        ENTITY_DESPAWN, // 
        /**
         * Calls {@link PluginListener#onEntityRightClick }
         */
        ENTITY_RIGHTCLICK,
        /**
         * Calls {@link PluginListener#onEntitySpawn }
         */
        ENTITY_SPAWN, // 
        /**
         * Class {@link PluginListener#onExpChange }
         */
        EXPERIENCE_CHANGE, // 
        /**
         * Calls {@link PluginListener#onExplosion }
         */
        EXPLOSION, // 
        /**
         * Calls {@link PluginListener#onFlow }
         */
        FLOW,
        /**
         * Calls {@link PluginListener#onFoodExahustionChange }
         */
        FOODEXHAUSTION_CHANGE, // 
        /**
         * Calls {@link PluginListener#onFoodLevelChange }
         */
        FOODLEVEL_CHANGE, // 
        /**
         * Calls {@link PluginListener#onFoodSaturationChange }
         */
        FOODSATURATION_CHANGE, // 
        /**
         * Calls {@link PluginListener#onPlayerListNameGet }
         */
        GET_PLAYERLISTENTRY, // TODO
        /**
         * Calls {@link PluginListener#onHealthChange }
         */
        HEALTH_CHANGE, // TODO
        /**
         * Calls {@link PluginListener#onIgnite }
         */
        IGNITE,
        /**
         * Calls {@link PluginListener#onItemDrop }
         */
        ITEM_DROP, // 
        /**
         * Calls {@link PluginListener#onItemPickUp }
         */
        ITEM_PICK_UP, // 
        /**
         * Calls {@link PluginListener#onItemUse }
         */
        ITEM_USE, // 
        /**
         * Calls {@link PluginListener#onKick }
         */
        KICK, // 
        /**
         * Calls {@link PluginListener#onLeafDecay }
         */
        LEAF_DECAY,
        /**
         * Class {@link PluginListener#onLevelUp }
         */
        LEVEL_UP,
        /**
         * Calls {@link PluginListener#onLightningStrike }
         */
        LIGHTNING_STRIKE, // TODO
        /**
         * Calls {@link PluginListener#onLiquidDestroy }
         */
        LIQUID_DESTROY,
        /**
         * Calls {@link PluginListener#onLogin(net.canarymod.hook.player.LoginHook) }
         */
        LOGIN, // 
        /**
         * Calls {@link PluginListener#onLoginChecks(net.canarymod.hook.player.LoginChecksHook)}
         */
        LOGINCHECKS, //
        /**
         * Calls {@link PluginListener#onMobDespawn }
         */
        MOB_DESPAWN, //
        /**
         * Calls {@link PluginListener#onMobSpawn }
         */
        MOB_SPAWN, //
        /**
         * Calls {@link PluginListener#onMobTarget }
         */
        MOB_TARGET, // TODO
        /**
         * Calls {@link PluginListener#onOpenInventory }
         */
        OPEN_INVENTORY, //
        /**
         * Calls {@link PluginListener#onPaintingDestory }
         */
        PAINTING_DESTROY, //
        /**
         * Calls {@link PluginListener#onCheckPermissions }
         */
        PERMISSION_CHECK, // TODO
        /**
         * Calls {@link PluginListener#onPistonExtend }
         */
        PISTON_EXTEND, // 
        /**
         * Calls {@link PluginListener#onPistonRetract }
         */
        PISTON_RETRACT, // 
        /**
         * Calls {@link PluginListener#onPlayerConnect }
         */
        PLAYER_CONNECT, //
        /**
         * Calls {@link PluginListener#onPlayerDeath }
         */
        PLAYER_DEATH, // TODO
        /**
         * Calls {@link PluginListener#onPlayerDisconnect) }
         */
        PLAYER_DISCONNECT, //
        /**
         * Calls {@link PluginListener#onPlayerMove }
         */
        PLAYER_MOVE, // 
        /**
         * Class {@link PluginListener#onPlayerRespawn }
         */
        PLAYER_RESPAWN, // 
        /**
         * Class {@link PluginListener#onPortalCreate }
         */
        PORTAL_CREATE,
        /**
         * Class {@link PluginListener#onPortalDestroy }
         */
        PORTAL_DESTROY, // TODO
        /**
         * Calls {@link PluginListener#onPortalUse }
         */
        PORTAL_USE,
        /**
         * Calls {@link PluginListener#onPotionEffect }
         */
        POTION_EFFECT, // TODO
        /**
         * Calls {@ling PluginListener#onRecipeMatch }
         */
        RECIPE_MATCH,
        /**
         * Calls {@link PluginListener#onRedstoneChange }
         */
        REDSTONE_CHANGE,
        /**
         * Calls {@link PluginListener#onSignChange }
         */
        SIGN_CHANGE, // 
        /**
         * Calls {@link PluginListener#onSignShow }
         */
        SIGN_SHOW, // 
        /**
         * Calls {@link PluginListener#onSpawnpointCreate }
         */
        SPAWNPOINT_CREATE, // TODO
        /**
         * Calls {@link PluginListener#onTame }
         */
        TAME, // TODO
        /**
         * Calls {@link PluginListener#onTeleport }
         */
        TELEPORT,
        /**
         * Calls {@link PluginListener#onThunderChange}
         */
        THUNDER_CHANGE, // TODO
        /**
         * Calls {@link PluginListener#onTimeChange }
         */
        TIME_CHANGE, // TODO
        /**
         * Calls {@link PluginListener#onVehicleCollision }
         */
        VEHICLE_COLLISION, // TODO
        /**
         * Calls {@link PluginListener#onVehicleCreate }
         */
        VEHICLE_CREATE, // TODO
        /**
         * Calls {@link PluginListener#onVehicleDamage }
         */
        VEHICLE_DAMAGE,
        /**
         * Calls {@link PluginListener#onVehicleDestroyed }
         */
        VEHICLE_DESTROYED, // TODO
        /**
         * Calls {@link PluginListener#onVehicleEnter }
         */
        VEHICLE_ENTERED, // TODO
        /**
         * Calls {@link PluginListener#onVehiclePositionChange }
         */
        VEHICLE_POSITIONCHANGE, // TODO
        /**
         * Calls {@link PluginListener#onVehicleUpdate }
         */
        VEHICLE_UPDATE, // TODO
        /**
         * Calls {@link PluginListener#onWeatherChange }
         */
        WEATHER_CHANGE, // TODO
        /**
         * This is reserved for custom created hooks by Plugins and has no
         * specific listener method attached. The Plugin Developer may define
         * the function to be called when registering the hook at the
         * {@link HookExecutor#registerCustomHook(CustomHook, String)}
         */
        CUSTOM, //
        /**
         * For internal use only.
         */
        NUM_HOOKS;
    }
}
