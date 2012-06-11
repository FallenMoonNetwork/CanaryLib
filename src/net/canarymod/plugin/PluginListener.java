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
     * Calls a {@link CancelableHook} that contains entity and damage amount information.<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     * @return AttackHook
     */
    public Hook onAttack(AttackHook hook){
        return hook;
    }
    
    /**
     * Calls a {@link CancelableHook} that contains player swinging arm information.<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     * @return LeftClickHook
     */
    public Hook onArmSwing(LeftClickHook hook){
        return hook;
    }
    
    /**
     * Calls a {@link BanHook} that contains the banned player or banned ip, and the moderator.
     * @param hook
     * @return BanHook
     */
    public Hook onBan(BanHook hook) {
        return hook;
    }
    
    /**
     * Calls a {@link CancelableHook} that contains player breaking block information.<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     * @return BlockBreakHook
     */
    public Hook onBlockBreak(LeftClickHook hook){
        return hook;
    }
    
    /**
     * Calls a {@link CancelableHook} that contains player left clicking block information.<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     * @return LeftClickHook
     */
    public Hook onBlockLeftClicked(LeftClickHook hook){
        return hook;
    }
    
    /**
     * Calls a {@link CancelableHook} that contains block physics information.<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     * @return BlockPhysicsHook
     */
    public Hook onBlockPhysics(BlockPhysicsHook hook){
        return hook;
    }
    
    /**
     * Calls a {@link CancelableHook} that contains player placing block information.<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     * @return RightClickHook
     */
    public Hook onBlockPlace(RightClickHook hook){
        return hook;
    }
    
    /**
     * Calls a {@link CancelableHook} that contains player right clicking block information.<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     * @return RightClickHook
     */
    public Hook onBlockRightClicked(RightClickHook hook){
        return hook;
    }
    
    /**
     * Calls a {@link CancelableHook} that contains block updating information.<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     * @return BlockUpdateHook
     */
    public Hook onBlockUpdate(BlockUpdateHook hook){
        return hook;
    }
    
    /**
     * Calls a {@link ChunkCreationHook} that contains chunk creation information.
     * @param hook
     * @return ChunkCreationHook
     */
    public Hook onChunkCreation(ChunkCreationHook hook){
        return hook;
    }
    
    /**
     * Calls a {@link ChunkHook} that contains chunk information.
     * @param hook
     * @return ChunkHook
     */
    public Hook onChunkCreated(ChunkHook hook){
        return hook;
    }
    
    /**
     * Calls a {@link ChunkHook} that contains chunk information.
     * @param hook
     * @return ChunkHook
     */
    public Hook onChunkLoaded(ChunkHook hook){
        return hook;
    }
    
    /**
     * Calls a {@link ChunkHook} that contains chunk information.
     * @param hook
     * @return ChunkHook
     */
    public Hook onChunkUnloaded(ChunkHook hook){
        return hook;
    }
    
    /**
     * Calls a {@link ChatHook} that contains the chatting player, his message and the chat prefix,
     * @param hook
     * @return ChunkHook
     */
    public Hook onChat(ChatHook hook) {
        return hook;
    }
    
    /**
     * Calls a {@link InventoryHook} that contains player and inventory information.
     * @param hook
     * @return ChunkHook
     */
    public Hook onCloseInventory(InventoryHook hook){
        return hook;
    }
    
    /**
     * Calls a {@link CancelableHook} that contains a Player and the issued command.<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     * @return PlayerCommandHook
     */
    public Hook onCommand(PlayerCommandHook hook) {
        return hook;
    }

    /**
     * Calls a {@link CancelableHook} that contains a command issued by the server.<br>
     * Call setCancelled if you wish to stop subsequent calls for this hook.
     * @param hook
     * @return ServerCommandHook
     */
    public Hook onConsoleCommand(ConsoleCommandHook hook) {
        return hook;
    }
    
    public Hook onDamage(DamageHook hook){
        return hook;
    }
    
    public Hook onDispense(DispenseHook hook){
        return hook;
    }
    
    public Hook onEat(RightClickHook hook){
        return hook;
    }
    
    public Hook onEntityRightClicked(RightClickHook hook){
        return hook;
    }
    
    public Hook onEnchant(EnchantHook hook){
        return hook;
    }
    
    public Hook onEndermanDrop(EndermanHook hook){
        return hook;
    }
    
    public Hook onEndermanPickUp(EndermanHook hook){
        return hook;
    }
    
    public Hook onExpChange(ExperienceHook hook){
        return hook;
    }
    
    public Hook onExplosion(ExplosionHook hook){
        return hook;
    }
    
    public Hook onFlow(FlowHook hook){
        return hook;
    }

    public Hook onFoodExhaustionChange(FoodLevelHook hook){
        return hook;
    }
    
    public Hook onFoodLevelChange(FoodLevelHook hook){
        return hook;
    }
    
    public Hook onFoodSaturationChange(FoodLevelHook hook){
        return hook;
    }
    
    public Hook onItemUse(RightClickHook hook){
        return hook;
    }

    /**
     * Calls a {@link Hook} that contains IP, player name and kickReason for the joining player.<br>
     * This hook is called in the early login stage before a player instance is created.<br>
     * Set this hooks kickReason to null if you want to allow the player to join (default)
     * or to something else (preferably reason for the join rejection) to kick the player
     * @param hook
     * @return
     */
    public Hook onLoginChecks(LoginChecksHook hook) {
        return hook;
    }

    /**
     * Calls a {@link Hook} that contains a Player.<br>
     * This hook is called in the later login process, after the player instance
     * has been created. The login message has been send already.
     * @param hook
     * @return
     */
    public Hook onLogin(LoginHook hook) {
        return hook;
    }
    
    public Hook onOpenInventory(InventoryHook hook){
        return hook;
    }
    
    public Hook onPaintingDestroy(PaintingHook hook){
        return hook;
    }
    
    public Hook onPistonExtend(PistonHook hook){
        return hook;
    }
    
    public Hook onPistonRetract(PistonHook hook){
        return hook;
    }
    
    public Hook onPlayerConnect(ConnectionHook hook){
        return hook;
    }
    
    public Hook onPlayerDisconnect(ConnectionHook hook){
        return hook;
    }
    
    /**
     * Calls a {@link InventoryHook} that contains teleport information.
     * @param hook
     * @return
     */
    public Hook onPortalUse(TeleportHook hook){
        return hook;
    }
    
    /**
     * Calls a {@link InventoryHook} that contains teleport information.
     * @param hook
     * @return
     */
    public Hook onTeleport(TeleportHook hook){
        return hook;
    }
}
