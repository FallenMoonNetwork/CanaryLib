package net.canarymod.commandsys.commands.group;

import net.canarymod.Canary;
import net.canarymod.chat.MessageReceiver;

public class GroupEditPermissions {

    //group) permission <group> <permission>:[value] add|remove
    public void execute(MessageReceiver caller, String[] args) {
        if(args[args.length - 1].equals("--help")) {
            Canary.help().getHelp(caller, "groupmod permission");
            return;
        }
    }
}
