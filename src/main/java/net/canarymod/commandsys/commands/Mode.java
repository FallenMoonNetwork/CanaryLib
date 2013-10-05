package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.GameMode;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

/**
 * Command to change the mode of yourself or another player 
 *
 * @author Chris (damagefilter)
 */
public class Mode implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        try{
            if (caller instanceof Player) {
                player((Player) caller, parameters);
            }
            else {
                console(caller, parameters);
            }
        }
        catch (NumberFormatException nfex){
            caller.notice(nfex.getMessage());
        }
    }

    private void console(MessageReceiver caller, String[] args) {
        if (args.length == 2) {
            caller.notice(Translator.translate("mode console"));
        }
        else {
            Player target = Canary.getServer().matchPlayer(args[2]);

            if (target != null) {
                int mode = Integer.parseInt(args[1]);

                target.setModeId(mode);
                caller.notice(Translator.translateAndFormat("mode set other", target.getName(), GameMode.fromId(mode).name()));
            }
            else {
                caller.notice(Translator.translateAndFormat("unknown player", args[2]));
            }

        }
    }

    private void player(Player player, String[] args) {
        int mode = Integer.parseInt(args[1]);

        if (args.length == 3) {
            Player receiver = Canary.getServer().matchPlayer(args[2]);

            if (receiver == null) {
                player.notice(Translator.translateAndFormat("unknown player", args[2]));
            }
            else {
                receiver.setModeId(mode);
                player.notice(Translator.translateAndFormat("mode set other", receiver.getName(), GameMode.fromId(mode).name()));
            }
        }
        else {
            player.setModeId(mode);
        }
    }

}
