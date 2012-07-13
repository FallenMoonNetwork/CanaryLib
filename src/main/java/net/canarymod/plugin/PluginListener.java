package net.canarymod.plugin;

import net.canarymod.hook.Hook;
import net.canarymod.hook.command.*;
import net.canarymod.hook.entity.*;
import net.canarymod.hook.player.*;
import net.canarymod.hook.world.*;

/**
 * Plugin listener. Plugins can implement this to listen to specific events
 * 
 * @author Chris Ksoll
 * @author Jason Jones
 * 
 */
public abstract class PluginListener {
    
    /**
     * Calls a {@link CancelableHook} that contains player swinging arm information.<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     */
    public void onArmSwing(LeftClickHook hook) {}
    
    /**
     * Calls a {@link BanHook} that contains the banned player or banned ip, and the moderator.
     * @param hook
     */
    public void onBan(BanHook hook) {}
    
    /**
     * Calls a {@link CancelableHook} that contains player breaking block information.<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     */
    public void onBlockBreak(LeftClickHook hook) {}
    
    /**
     * Calls a {@link CancelableHook} that contains player left clicking block information.<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     */
    public void onBlockLeftClicked(LeftClickHook hook) {}
    
    /**
     * Calls a {@link CancelableHook} that contains block physics information.<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     */
    public void onBlockPhysics(BlockPhysicsHook hook) {}
    
    /**
     * Calls a {@link CancelableHook} that contains player placing block information.<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     */
    public void onBlockPlace(RightClickHook hook) {}
    
    /**
     * Calls a {@link CancelableHook} that contains player right clicking block information.<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     */
    public void onBlockRightClicked(RightClickHook hook) {}
    
    /**
     * Calls a {@link CancelableHook} that contains block updating information.<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     */
    public void onBlockUpdate(BlockUpdateHook hook) {}
    
    /**
     * Calls a {@link ChunkCreationHook} that contains chunk creation information.
     * @param hook
     */
    public void onChunkCreation(ChunkCreationHook hook) {}
    
    /**
     * Calls a {@link ChunkHook} that contains chunk information.
     * @param hook
     */
    public void onChunkCreated(ChunkHook hook) {}
    
    /**
     * Calls a {@link ChunkHook} that contains chunk information.
     * @param hook
     */
    public void onChunkLoaded(ChunkHook hook) {}
    
    /**
     * Calls a {@link ChunkHook} that contains chunk information.
     * @param hook
     */
    public void onChunkUnloaded(ChunkHook hook) {}
    
    /**
     * Calls a {@link ChatHook} that contains the chatting player, his message and the chat prefix,
     * @param hook
     */
    public void onChat(ChatHook hook) {}
    
    /**
     * Calls a {@link InventoryHook} that contains player and inventory information.
     * @param hook
     */
    public void onCloseInventory(InventoryHook hook) {}
    
    /**
     * Calls a {@link CancelableHook} that contains a Player and the issued command.<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     */
    public void onCommand(PlayerCommandHook hook) {}

    /**
     * Calls a {@link CancelableHook} that contains a command issued by the server.<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     */
    public void onConsoleCommand(ConsoleCommandHook hook) {}
    
    /**
     * Calls a {@link CancelableHook} that contains Entity damage information.<br>
     * This can be things like PvP, Mob attacks, PvE, MvE (Mob vs Environment)<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     */
    public void onDamage(DamageHook hook) {}
    
    /**
     * Calls a {@link CancelableHook} that contains Dispenser dispensing entities information. (Not just Item entities)<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     */
    public void onDispense(DispenseHook hook) {}
    
    /**
     * Calls a {@link CancelableHook} that contains player eating information.<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     */
    public void onEat(RightClickHook hook) {}
    
    /**
     * Calls a {@link CancelableHook} that contains entity despawn information. (Not including Living Entities)<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     */
    public void onEntityDespawn(EntitySpawnHook hook) {}
    
    /**
     * Calls a {@link CancelableHook} that contains a player rightclicking an entity information.<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     */
    public void onEntityRightClicked(RightClickHook hook) {}
    
    /**
     * Calls a {@link CancelableHook} that contains entity spawning information. (Not including Living entities)<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     */
    public void onEntitySpawn(EntitySpawnHook hook) {}
    
    /**
     * Calls a {@link CancelableHook} that contains player enchanting information.<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     */
    public void onEnchant(EnchantHook hook) {}
    
    /**
     * Calls a {@link CancelableHook} that contains Enderman placing block information.<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     */
    public void onEndermanDrop(EndermanHook hook) {}
    
    /**
     * Calls a {@link CancelableHook} that contains Enderman picking up a block information.<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     */
    public void onEndermanPickUp(EndermanHook hook) {}
    
    /**
     * Calls a {@link CancelableHook} that contains player experience information.<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     */
    public void onExpChange(ExperienceHook hook) {}
    
    /**
     * Calls a {@link CancelableHook} that contains entity exploding information.<br>
     * TNTPrimed and Fireballs will also now show up as the entity now.<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     */
    public void onExplosion(ExplosionHook hook) {}
    
    /**
     * Calls a {@link CancelableHook} that contains water/lava flowing information.<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     */
    public void onFlow(FlowHook hook) {}

    /**
     * Calls a {@link Hook} that contains player food exhaustion changing information.<br>
     * Call setNewLevel if you wish to modify the level being set.
     * @param hook
     */
    public void onFoodExhaustionChange(FoodLevelHook hook) {}
    
    /**
     * Calls a {@link Hook} that contains player food level changing information.<br>
     * Call setNewLevel if you wish to modify the level being set.
     * @param hook
     */
    public void onFoodLevelChange(FoodLevelHook hook) {}
    
    /**
     * Calls a {@link Hook} that contains player food saturation changing information.<br>
     * Call setNewLevel if you wish to modify the level being set.
     * @param hook
     */
    public void onFoodSaturationChange(FoodLevelHook hook) {}
    
    public void onItemDrop(ItemHook hook) {}
    
    /**
     * Calls a {@link CancelableHook} that contains player using an Item information.<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     */
    public void onItemUse(RightClickHook hook) {}
    
    public void onItemPickup(ItemHook hook) {}
    
    public void onKick(KickHook hook) {}

    /**
     * Calls a {@link Hook} that contains IP, player name and kickReason for the joining player.<br>
     * This hook is called in the early login stage before a player instance is created.<br>
     * Set this hooks kickReason to null if you want to allow the player to join (default)
     * or to something else (preferably reason for the join rejection) to kick the player
     * @param hook
     */
    public void onLoginChecks(LoginChecksHook hook) {}

    /**
     * Calls a {@link Hook} that contains a Player.<br>
     * This hook is called in the later login process, after the player instance
     * has been created. The login message has been send already.
     * @param hook
     */
    public void onLogin(LoginHook hook) {}
    
    public void onMobDespawn(EntitySpawnHook hook) {}
    
    public void onMobSpawn(EntitySpawnHook hook) {}
    
    public void onMobTarget(MobTargetHook hook) {}
    
    public void onOpenInventory(InventoryHook hook) {}
    
    public void onPaintingDestroy(PaintingHook hook) {}
    
    public void onPistonExtend(PistonHook hook) {}
    
    public void onPistonRetract(PistonHook hook) {}
    
    public void onPlayerConnect(ConnectionHook hook) {}
    
    public void onPlayerDisconnect(ConnectionHook hook) {}
    
    public void onPlayerMove(PlayerMoveHook hook) {}
    
    public void onPlayerRespawn(PlayerRespawnHook hook) {}
    
    /**
     * Calls a {@link TeleportHook} that contains teleport information.
     * @param hook
     */
    public void onPortalUse(TeleportHook hook) {}
    
    /**
     * Calls a {@link RecipeMatchHook} that contains information about a matching recipe.
     * @param hook
     */
    public void onRecipeMatch(RecipeMatchHook hook) {}
    
    public void onSignChange(SignHook hook) {}
    
    public void onSignShow(SignHook hook) {}
    
    /**
     * Calls a {@link TeleportHook} that contains teleport information.
     * @param hook
     */
    public void onTeleport(TeleportHook hook) {}
    
    /**
     * Calls a {@link DecorateHook} that contains decorator information.
     * @param hook
     */
    public void onDecorate(DecorateHook hook) {}

    public void onLevelUp(LevelUpHook levelUpHook) {}
}
