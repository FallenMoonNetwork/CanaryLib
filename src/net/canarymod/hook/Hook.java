package net.canarymod.hook;

import net.canarymod.hook.command.*;
import net.canarymod.hook.entity.*;
import net.canarymod.hook.player.*;
import net.canarymod.hook.world.*;
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
        ARM_SWING(new LeftClickDelegate()), // 
        /**
         * Calls {@link PluginListener#onBan }
         */
        BAN(new BanDelegate()), //
        /**
         * Calls {@link PluginListener#onBlockBreak }
         */
        BLOCK_BREAK(new LeftClickDelegate()), //
        /**
         * Calls {@link PluginListener#onBlockLeftClicked }
         */
        BLOCK_LEFTCLICKED(new LeftClickDelegate()), // 
        /**
         * Calls {@link PluginListener#onBlockPhysics }
         */
        BLOCK_PHYSICS(new BlockPhysicsDelegate()), // 
        /**
         * Calls {@link PluginListener#onBlockPlace }
         */
        BLOCK_PLACE(new RightClickDelegate()), //
        /**
         * Calls {@link PluginListener#onBlockRightClicked) }
         */
        BLOCK_RIGHTCLICKED(new RightClickDelegate()), //
        /**
         * Calls {@link PluginListener#onBlockUpdate }
         */
        BLOCK_UPDATE(new BlockUpdateDelegate()), // 
        /**
         * Calls {@link PluginListener#onChat }
         */
        CHAT(new ChatDelegate()), //
        /**
         * Calls {@link PluginListener#onChunkCreated }
         */
        CHUNK_CREATED(new ChunkDelegate()), //
        /**
         * Calls {@link PluginListener#onChunkCreation }
         */
        CHUNK_CREATION(new ChunkCreationDelegate()), //
        /**
         * Calls {@link PluginListener#onChunkLoaded }
         */
        CHUNK_LOADED(new ChunkDelegate()), //
        /**
         * Calls {@link PluginListener#onChunkUnloaded }
         */
        CHUNK_UNLOADED(new ChunkDelegate()), //
        /**
         * Calls {@link PluginListener#onCloseInventory }
         */
        CLOSE_INVENTORY(new InventoryDelegate()), //
        /**
         * Calls {@link PluginListener#onCommand(net.canarymod.hook.command.PlayerCommandHook) }
         */
        COMMAND(new PlayerCommandDelegate()), //
        /**
         * Calls {@link PluginListener#onConsoleCommand(net.canarymod.hook.command.ConsoleCommandHook) }
         */
        CONSOLECOMMAND(new ConsoleCommandDelegate()), //
        /**
         * Calls {@link PluginListener#onCraft() }
         */
        CRAFT(new CraftDelegate()), //
        /**
         * Calls {@link PluginListener#onDamage }
         */
        DAMAGE(new DamageDelegate()), //
        /**
         * Calls {@link PluginListener#onDecorate }
         */
        DECORATE(new DecorateDelegate()),
        /**
         * Calls{@link PluginListener#onDispense }
         */
        DISPENSE(new DispenseDelegate()), // 
        /**
         * Calls {@link PluginListener#onEat }
         */
        EAT(new RightClickDelegate()), //
        /**
         * Calls {@link PluginListener#onEnchant }
         */
        ENCHANT(new EnchantDelegate()), // 
        /**
         * Class {@link PluginListener#onEndermanDrop }
         */
        ENDERMAN_DROP(new EndermanDelegate()), //
        /**
         * Class {@link PluginListener#onEndermanPickup }
         */
        ENDERMAN_PICKUP(new EndermanDelegate()), //
        /**
         * Class {@link PluginListener#onEntityDespawn }
         */
        ENTITY_DESPAWN(new EntitySpawnDelegate()), // 
        /**
         * Calls {@link PluginListener#onEntityRightClick }
         */
        ENTITY_RIGHTCLICKED(new RightClickDelegate()), //
        /**
         * Calls {@link PluginListener#onEntitySpawn }
         */
        ENTITY_SPAWN(new EntitySpawnDelegate()), // 
        /**
         * Class {@link PluginListener#onExpChange }
         */
        EXPERIENCE_CHANGE(new ExperienceDelegate()), // 
        /**
         * Calls {@link PluginListener#onExplosion }
         */
        EXPLOSION(new ExplosionDelegate()), // 
        /**
         * Calls {@link PluginListener#onFlow }
         */
        FLOW(new FlowDelegate()), // 
        /**
         * Calls {@link PluginListener#onFoodExahustionChange }
         */
        FOODEXHAUSTION_CHANGE(new FoodLevelDelegate()), // 
        /**
         * Calls {@link PluginListener#onFoodLevelChange }
         */
        FOODLEVEL_CHANGE(new FoodLevelDelegate()), // 
        /**
         * Calls {@link PluginListener#onFoodSaturationChange }
         */
        FOODSATURATION_CHANGE(new FoodLevelDelegate()), // 
        /**
         * Calls {@link PluginListener#onPlayerListNameGet }
         */
        GET_PLAYERLISTENTRY(new EmptyDelegate()), // TODO
        /**
         * Calls {@link PluginListener#onHealthChange }
         */
        HEALTH_CHANGE(new EmptyDelegate()), // TODO
        /**
         * Calls {@link PluginListener#onIgnite }
         */
        IGNITE(new EmptyDelegate()), // TODO
        /**
         * Calls {@link PluginListener#onItemDrop }
         */
        ITEM_DROP(new ItemDelegate()), // 
        /**
         * Calls {@link PluginListener#onItemPickUp }
         */
        ITEM_PICK_UP(new ItemDelegate()), // 
        /**
         * Calls {@link PluginListener#onItemUse }
         */
        ITEM_USE(new RightClickDelegate()), // 
        /**
         * Calls {@link PluginListener#onKick }
         */
        KICK(new KickDelegate()), // 
        /**
         * Calls {@link PluginListener#onLeafDecay }
         */
        LEAF_DECAY(new EmptyDelegate()), // TODO
        /**
         * Class {@link PluginListener#onLevelUp }
         */
        LEVEL_UP(new EmptyDelegate()), // TODO
        /**
         * Calls {@link PluginListener#onLightningStrike }
         */
        LIGHTNING_STRIKE(new EmptyDelegate()), // TODO
        /**
         * Calls {@link PluginListener#onLiquidDestroy }
         */
        LIQUID_DESTROY(new EmptyDelegate()), // TODO
        /**
         * Calls {@link PluginListener#onLogin(net.canarymod.hook.player.LoginHook) }
         */
        LOGIN(new LoginDelegate()), // 
        /**
         * Calls {@link PluginListener#onLoginChecks(net.canarymod.hook.player.LoginChecksHook)}
         */
        LOGINCHECKS(new LoginChecksDelegate()), //
        /**
         * Calls {@link PluginListener#onMobDespawn }
         */
        MOB_DESPAWN(new EntitySpawnDelegate()), //
        /**
         * Calls {@link PluginListener#onMobSpawn }
         */
        MOB_SPAWN(new EntitySpawnDelegate()), //
        /**
         * Calls {@link PluginListener#onMobTarget }
         */
        MOB_TARGET(new EmptyDelegate()), // TODO
        /**
         * Calls {@link PluginListener#onOpenInventory }
         */
        OPEN_INVENTORY(new InventoryDelegate()), //
        /**
         * Calls {@link PluginListener#onPaintingDestory }
         */
        PAINTING_DESTROY(new PaintingDelegate()), //
        /**
         * Calls {@link PluginListener#onCheckPermissions }
         */
        PERMISSION_CHECK(new EmptyDelegate()), // TODO
        /**
         * Calls {@link PluginListener#onPistonExtend }
         */
        PISTON_EXTEND(new PistonDelegate()), // 
        /**
         * Calls {@link PluginListener#onPistonRetract }
         */
        PISTON_RETRACT(new PistonDelegate()), // 
        /**
         * Calls {@link PluginListener#onPlayerConnect }
         */
        PLAYER_CONNECT(new ConnectionDelegate()), //
        /**
         * Calls {@link PluginListener#onPlayerDeath }
         */
        PLAYER_DEATH(new EmptyDelegate()), // TODO
        /**
         * Calls {@link PluginListener#onPlayerDisconnect) }
         */
        PLAYER_DISCONNECT(new ConnectionDelegate()), //
        /**
         * Calls {@link PluginListener#onPlayerMove }
         */
        PLAYER_MOVE(new PlayerMoveDelegate()), // 
        /**
         * Class {@link PluginListener#onPlayerRespawn }
         */
        PLAYER_RESPAWN(new PlayerRespawnDelegate()), // 
        /**
         * Class {@link PluginListener#onPortalCreate }
         */
        PORTAL_CREATE(new EmptyDelegate()), // TODO
        /**
         * Class {@link PluginListener#onPortalDestroy }
         */
        PORTAL_DESTROY(new EmptyDelegate()), // TODO
        /**
         * Calls {@link PluginListener#onPortalUse }
         */
        PORTAL_USE(new TeleportDelegate()),
        /**
         * Calls {@link PluginListener#onPotionEffect }
         */
        POTION_EFFECT(new EmptyDelegate()), // TODO
        /**
         * Calls {@link PluginListener#onRedstoneChange }
         */
        REDSTONE_CHANGE(new EmptyDelegate()), // TODO
        /**
         * Calls {@link PluginListener#onSignChange }
         */
        SIGN_CHANGE(new SignDelegate()), // 
        /**
         * Calls {@link PluginListener#onSignShow }
         */
        SIGN_SHOW(new SignDelegate()), // 
        /**
         * Calls {@link PluginListener#onSpawnpointCreate }
         */
        SPAWNPOINT_CREATE(new EmptyDelegate()), // TODO
        /**
         * Calls {@link PluginListener#onTame }
         */
        TAME(new EmptyDelegate()), // TODO
        /**
         * Calls {@link PluginListener#onTeleport }
         */
        TELEPORT(new TeleportDelegate()),
        /**
         * Calls {@link PluginListener#onThunderChange}
         */
        THUNDER_CHANGE(new EmptyDelegate()), // TODO
        /**
         * Calls {@link PluginListener#onTimeChange }
         */
        TIME_CHANGE(new EmptyDelegate()), // TODO
        /**
         * Calls {@link PluginListener#onVehicleCollision }
         */
        VEHICLE_COLLISION(new EmptyDelegate()), // TODO
        /**
         * Calls {@link PluginListener#onVehicleCreate }
         */
        VEHICLE_CREATE(new EmptyDelegate()), // TODO
        /**
         * Calls {@link PluginListener#onVehicleDamage }
         */
        VEHICLE_DAMAGE(new EmptyDelegate()), // TODO
        /**
         * Calls {@link PluginListener#onVehicleDestroyed }
         */
        VEHICLE_DESTROYED(new EmptyDelegate()), // TODO
        /**
         * Calls {@link PluginListener#onVehicleEnter }
         */
        VEHICLE_ENTERED(new EmptyDelegate()), // TODO
        /**
         * Calls {@link PluginListener#onVehiclePositionChange }
         */
        VEHICLE_POSITIONCHANGE(new EmptyDelegate()), // TODO
        /**
         * Calls {@link PluginListener#onVehicleUpdate }
         */
        VEHICLE_UPDATE(new EmptyDelegate()), // TODO
        /**
         * Calls {@link PluginListener#onWeatherChange }
         */
        WEATHER_CHANGE(new EmptyDelegate()), // TODO
        /**
         * This is reserved for custom created hooks by Plugins and has no
         * specific listener method attached. The Plugin Developer may define
         * the function to be called when registering the hook at the
         * {@link HookExecutor#registerCustomHook(CustomHook, String)}
         */
        CUSTOM(new EmptyDelegate()), //
        /**
         * For internal use only.
         */
        NUM_HOOKS(new EmptyDelegate()); //
        
        private final HookDelegate delegate;
        
        private Type(HookDelegate delegate){
            this.delegate = delegate;
        }
        
        HookDelegate getDelegate(){
            return delegate;
        }
    }
}
