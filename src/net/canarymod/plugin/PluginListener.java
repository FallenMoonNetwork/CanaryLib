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
     * Calls a {@link CancelableHook} that contains entity and damage amount information
     * @param hook
     * @return AttackHook
     * call setCancelled if you wish to stop subsequent calls for this hook.
     */
    public Hook onAttack(AttackHook hook){
        return hook;
    }
    
    /**
     * Calls a {@link CancelableHook} that contains player swinging arm information
     * @param hook
     * @return LeftClickHook
     * call setCancelled if you wish to stop subsequent calls for this hook.
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
     * Calls a {@link CancelableHook} that contains player breaking block information
     * @param hook
     * @return BlockBreakHook
     * call setCancelled if you wish to stop subsequent calls for this hook.
     */
    public Hook onBlockBreak(LeftClickHook hook){
        return hook;
    }
    
    /**
     * Calls a {@link CancelableHook} that contains player left clicking block information
     * @param hook
     * @return LeftClickHook
     * call setCancelled if you wish to stop subsequent calls for this hook.
     */
    public Hook onBlockLeftClicked(LeftClickHook hook){
        return hook;
    }
    
    public Hook onBlockPhysics(BlockPhysicsHook hook){
        return hook;
    }
    
    public Hook onBlockPlace(RightClickHook hook){
        return hook;
    }
    
    public Hook onBlockRightClicked(RightClickHook hook){
        return hook;
    }
    
    public Hook onBlockUpdate(BlockUpdateHook hook){
        return hook;
    }
    
    public Hook onChunkCreation(ChunkCreationHook hook){
        return hook;
    }
    
    public Hook onChunkCreated(ChunkHook hook){
        return hook;
    }
    
    public Hook onChunkLoaded(ChunkHook hook){
        return hook;
    }
    
    public Hook onChunkUnloaded(ChunkHook hook){
        return hook;
    }
    
    /**
     * Calls a {@link ChatHook} that contains the chatting player, his message and the chat prefix,
     * @param hook
     * @return
     */
    public Hook onChat(ChatHook hook) {
        return hook;
    }
    
    public Hook onCloseInventory(InventoryHook hook){
        return hook;
    }
    
    /**
     * Calls a {@link CancelableHook} that contains a Player and the issued command.
     * @param hook
     * @return PlayerCommandHook
     * call setCancelled if you wish to stop subsequent calls for this hook.
     */
    public Hook onCommand(PlayerCommandHook hook) {
        return hook;
    }

    /**
     * Calls a {@link CancelableHook} that contains a command issued by the server.
     * @param hook
     * @return ServerCommandHook
     * call setCancelled if you wish to stop subsequent calls for this hook.
     */
    public Hook onConsoleCommand(ConsoleCommandHook hook) {
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
}
