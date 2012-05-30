package net.canarymod.hook;

import net.canarymod.plugin.PluginListener;

/**
 * A basic hook you can implement to create custom hooks. System Hooks also
 * extend this
 * 
 * @author Chris Ksoll
 * 
 */
public abstract class Hook {
    protected Type type = Type.NUM_HOOKS;

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
     * Returns an Object set of data this Hook contains
     * 
     * @return
     */
    public abstract Object[] getDataSet();

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
         * Calls {@link PluginListener#onAttack }
         */
        ATTACK, //
        /**
         * Calls {@link PluginListener#onBan }
         */
        BAN, // CHECK
        /**
         * Calls {@link PluginListener#onBlockBreak }
         */
        BLOCK_BROKEN, //
        /**
         * Calls {@link PluginListener#onBlockCreate }
         */
        BLOCK_CREATED, //
        /**
         * Calls {@link PluginListener#onBlockDestroy }
         */
        BLOCK_DESTROYED, //
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
         * Calls {@link PluginListener#onChat(net.canarymod.hook.player.ChatHook)}
         */
        CHAT, // CHECK
        /**
         * Calls {@link PluginListener#onChunkCreate }
         */
        CHUNK_CREATE, //                                //Is there a difference between CHUNK_CREATE and CHUNK_CREATED ?
        /**
         * Calls {@link PluginListener#onChunkCreated }
         */
        CHUNK_CREATED, //
        /**
         * Calls {@link PluginListener#onChunkLoaded }
         */
        CHUNK_LOADED, //
        /**
         * Calls {@link PluginListener#onChunkUnload }
         */
        CHUNK_UNLOAD, //
        /**
         * Calls {@link PluginListener#onCloseInventory }
         */
        CLOSE_INVENTORY, //
        /**
         * Calls {@link PluginListener#onCommand(net.canarymod.hook.command.PlayerCommandHook) }
         */
        COMMAND, // CHECK
        /**
         * Calls {@link PluginListener#canPlayerUseCommand }
         */
        COMMAND_CHECK, //
        /**
         * Calls {@link PluginListener#onConsoleCommand(net.canarymod.hook.command.ConsoleCommandHook) }
         */
        CONSOLECOMMAND, // CHECK
        /**
         * Class {@link PluginListener#onCowMilk }
         */
        COW_MILK, //                                     //Um... pointless no? (entityrightclick)
        /**
         * Calls {@link PluginListener#onDamage }
         */
        DAMAGE, //
        /**
         * Calls {@link PluginListener#onDisconnect }
         */
        DISCONNECT, //
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
        ENTITY_RIGHTCLICKED, //
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
        FLOW, //
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
        GET_PLAYERLISTENTRY, //
        /**
         * Calls {@link PluginListener#onHealthChange }
         */
        HEALTH_CHANGE, //
        /**
         * Calls {@link PluginListener#onIgnite }
         */
        IGNITE, //
        /**
         * Calls {@link PluginListener#onIpBan }
         */
        IPBAN, // CHECK
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
        KICK, // CHECK
        /**
         * Calls {@link PluginListener#onLeafDecay }
         */
        LEAF_DECAY, //
        /**
         * Class {@link PluginListener#onLevelUp }
         */
        LEVEL_UP, //
        /**
         * Calls {@link PluginListener#onLightningStrike }
         */
        LIGHTNING_STRIKE, //
        /**
         * Calls {@link PluginListener#onLiquidDestroy }
         */
        LIQUID_DESTROY, //
        /**
         * Calls {@link PluginListener#onLogin(net.canarymod.hook.player.LoginHook) }
         */
        LOGIN, // CHECK
        /**
         * Calls {@link PluginListener#onLoginChecks(net.canarymod.hook.player.LoginChecksHook)}
         */
        LOGINCHECKS, // CHECK
        /**
         * Calls {@link PluginListener#onMobSpawn }
         */
        MOB_SPAWN, //
        /**
         * Calls {@link PluginListener#onMobTarget }
         */
        MOB_TARGET, //
        /**
         * Calls {@link PluginListener#onOpenInventory }
         */
        OPEN_INVENTORY, //
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
        PORTAL_CREATE, //
        /**
         * Class {@link PluginListener#onPortalDestroy }
         */
        PORTAL_DESTROY, //
        /**
         * Calls {@link PluginListener#onPortalUse }
         */
        PORTAL_USE, //
        /**
         * Calls {@link PluginListener#onPotionEffect }
         */
        POTION_EFFECT, //
        /**
         * Calls {@link PluginListener#onRedstoneChange }
         */
        REDSTONE_CHANGE, //
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
        SPAWNPOINT_CREATE, //
        /**
         * Calls {@link PluginListener#onTame }
         */
        TAME, //
        /**
         * Calls {@link PluginListener#onTeleport }
         */
        TELEPORT, //
        /**
         * Calls {@link PluginListener#onThunderChange}
         */
        THUNDER_CHANGE, //
        /**
         * Calls {@link PluginListener#onTimeChange }
         */
        TIME_CHANGE, //
        /**
         * Calls {@link PluginListener#onVehicleCollision }
         */
        VEHICLE_COLLISION, //
        /**
         * Calls {@link PluginListener#onVehicleCreate }
         */
        VEHICLE_CREATE, //
        /**
         * Calls {@link PluginListener#onVehicleDamage }
         */
        VEHICLE_DAMAGE, //
        /**
         * Calls {@link PluginListener#onVehicleDestroyed }
         */
        VEHICLE_DESTROYED, //
        /**
         * Calls {@link PluginListener#onVehicleEnter }
         */
        VEHICLE_ENTERED, //
        /**
         * Calls {@link PluginListener#onVehiclePositionChange }
         */
        VEHICLE_POSITIONCHANGE, //
        /**
         * Calls {@link PluginListener#onVehicleUpdate }
         */
        VEHICLE_UPDATE, //
        /**
         * Calls {@link PluginListener#onWeatherChange }
         */
        WEATHER_CHANGE, //
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
        //
        NUM_HOOKS;
    }
}
