package net.canarymod.plugin;

import net.canarymod.hook.Hook;
import net.canarymod.hook.command.PlayerCommandHook;
import net.canarymod.hook.command.ConsoleCommandHook;
import net.canarymod.hook.player.LoginChecksHook;
import net.canarymod.hook.player.LoginHook;

/**
 * Plugin listener. Plugins can implement this to listen to specific events
 * 
 * @author Chris
 * 
 */
public abstract class PluginListener {

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
}
