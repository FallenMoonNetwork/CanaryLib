package net.canarymod.commandsys.commands;


import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;
import net.canarymod.user.Group;


public class GroupCommand extends CanaryCommand {

    public GroupCommand() {
        super("canary.command.group", Translator.translate("group info"), Translator.translateAndFormat("usage", "/group <create|delete> <name> [parent]"), 3, 4);
    }

    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
        if (caller == null) {
            throw new CommandException(Translator.translateAndFormat("unknown messagereceiver", "null"));
        }
        if(parameters[1].equalsIgnoreCase("create")) {
            if(caller.hasPermission("canary.command.group.create")) {
                Group group = new Group();
                group.setName(parameters[2]);
                group.setPermissionProvider(Canary.permissionManager().getGroupsProvider(parameters[2]));
                if(parameters.length == 4) {
                    Group parent = Canary.usersAndGroups().getGroup(parameters[3]);
                    if(parent == null) {
                        caller.notice(Translator.translateAndFormat("group unknown parent", parameters[3]));
                        return;
                    }
                    group.setParent(parent);
                }
                Canary.usersAndGroups().addGroup(group);
                caller.message(Colors.YELLOW + Translator.translateAndFormat("group created", group.getName()));
            }
            else {
                caller.notice(Translator.translate("group failed"));
            }
        }

        if(parameters[1].equalsIgnoreCase("delete")) {
            if(caller.hasPermission("canary.command.group.delete")) {
                Group group = Canary.usersAndGroups().getGroup(parameters[2]);
                if(group == null) {
                    caller.notice(Translator.translateAndFormat("group unknown", parameters[2]));
                    return;
                }
                //
                for(Player player : Canary.getServer().getPlayerList()) {
                    if(player.getGroup().getName().equals(group.getName())) {
                        player.setGroup(group.getParent());
                    }
                }
                Canary.usersAndGroups().removeGroup(group);
                caller.message(Colors.YELLOW + Translator.translateAndFormat("group removed", group.getName()));
            }
            else {
                caller.notice(Translator.translate("group failed"));
            }
        }
    }

}
