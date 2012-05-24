package net.canarymod;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookExecutor;
import net.canarymod.hook.command.ConsoleCommandDelegate;
import net.canarymod.hook.command.PlayerCommandDelegate;
import net.canarymod.hook.player.ChatDelegate;
import net.canarymod.hook.player.LoginChecksDelegate;
import net.canarymod.hook.player.LoginDelegate;

public class Bootstrapper {

    
    public static void init(Canary mod) {
        Canary.setCanary(mod);
        registerHooks();
    }
    
    private static void registerHooks() {
        HookExecutor hookExecutor = Canary.hooks();
        hookExecutor.registerHook(Hook.Type.LOGINCHECKS, new LoginChecksDelegate());
        hookExecutor.registerHook(Hook.Type.LOGIN, new LoginDelegate());
        hookExecutor.registerHook(Hook.Type.CHAT, new ChatDelegate());
        hookExecutor.registerHook(Hook.Type.COMMAND, new PlayerCommandDelegate());
        hookExecutor.registerHook(Hook.Type.CONSOLECOMMAND, new ConsoleCommandDelegate());
    }
}
