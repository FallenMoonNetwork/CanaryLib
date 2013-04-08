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
        super("canary.command.group", Translator.translate("group info"), Translator.translateAndFormat("usage", "/group <create|delete|rename|list> <name> [parent|new name]"), 2, 4);
    }

    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
        if (caller == null) {
            throw new CommandException(Translator.translateAndFormat("unknown messagereceiver", "null"));
        }
        if(parameters[1].equalsIgnoreCase("create")) {
            if(parameters.length < 3) {
                caller.message(Translator.translateAndFormat("usage", "/group <create|delete|rename|list> <name> [parent|new name]"));
                return;
            }
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

        else if(parameters[1].equalsIgnoreCase("delete") || parameters[1].equalsIgnoreCase("remove")) {
            if(parameters.length < 3) {
                caller.message(Translator.translateAndFormat("usage", "/group <create|delete|rename|list> <name> [parent|new name]"));
                return;
            }
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

        else if(parameters[1].equalsIgnoreCase("rename")) {
            if(parameters.length != 4) {
                caller.notice(Translator.translate("group rename failed"));
                return;
            }
            if(caller.hasPermission("canary.command.group.rename")) {
                Group group = Canary.usersAndGroups().getGroup(parameters[2]);
                if(group == null) {
                    caller.notice(Translator.translateAndFormat("group unknown", parameters[2]));
                    return;
                }
                Canary.usersAndGroups().renameGroup(group, parameters[3]);
                caller.message(Colors.YELLOW + Translator.translateAndFormat("group rename", group.getName()));
            }
            else {
                caller.notice(Translator.translate("group failed"));
            }
        }

        else if(parameters[1].equalsIgnoreCase("list")) {

            for(Group g : Canary.usersAndGroups().getGroups()) {
                StringBuilder line = new StringBuilder();
                line.append(Colors.YELLOW).append("Name: ").append(Colors.WHITE).append(g.getName());
                if(g.hasParent()) {
                    line.append(", ").append(Colors.YELLOW).append("Parent: ").append(Colors.WHITE).append(g.getParent().getName());
                }
                caller.message(line.toString());
            }
        }
        else {
            caller.message(Translator.translateAndFormat("usage", "/group <create|delete|rename|list> <name> [parent|new name]"));
            return;
        }
    }

}
