package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.chat.MessageReceiver;

public class PlayerEditPermissions {

    //group) permission <player> <permission>:[value] add|remove
    public void execute(MessageReceiver caller, String[] args) {
        if(args[args.length - 1].equals("--help")) {
            Canary.help().getHelp(caller, "playermod permission");
            return;
        }
    }
}
