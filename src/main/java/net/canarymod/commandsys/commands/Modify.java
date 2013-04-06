package net.canarymod.commandsys.commands;


import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;
import net.canarymod.user.Group;


public class Modify extends CanaryCommand {
    public Modify() {
        super("canary.command.modify", Translator.translate("modify info"), Translator.translateAndFormat("usage", "/modify <group|player> <name> <set|remove> <key> <value>"), 6);
    }

    private Key key;
    private Action action;
    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
        if (caller == null) {
            throw new CommandException(Translator.translateAndFormat("unknown messagereceiver", "null"));
        }
        if(!checkPreconditions(parameters)) {
            if(key == null) {
                caller.notice(Translator.translateAndFormat("modify invalid key", parameters[4]));
            }
            if(action == null) {
                caller.notice(Translator.translateAndFormat("modify invalid action", parameters[3]));
            }
            caller.notice(Translator.translateAndFormat("usage", "/modify <group|player> <name> <set|remove> <key> <value>"));
            return;
        }

        if(parameters[1].equalsIgnoreCase("player")) {
            managePlayers(caller, parameters);
        }
        if(parameters[1].equalsIgnoreCase("group")) {
            manageGroups(caller, parameters);
        }
        else {
            caller.notice(Translator.translateAndFormat("usage", "/modify <group|player> <name> <set|remove> <key> <value>"));
        }
    }

    private void manageGroups(MessageReceiver caller, String[] args) {
        Group group = Canary.usersAndGroups().getGroup(args[2]);
        if(group == null) {
            caller.notice(Translator.translateAndFormat("modify invalid group", args[2]));
            caller.notice(Translator.translate("modify group tooltip")); //use /group create to create new groups
            return;
        }
        switch(action) {
            case REMOVE:
                removeKeyOnGroup(caller, group, args[5]);
                break;
            case SET:
                setKeyOnGroup(caller, group, args[5]);
                break;
            default:
                break;

        }
    }

    private void managePlayers(MessageReceiver caller, String[] args) {
        Player target = Canary.getServer().getPlayer(args[2]);
        if(target == null) {
            caller.notice(Translator.translateAndFormat("unknown player", args[2]));
        }
        switch(action) {
            case REMOVE:
                removeKeyOnPlayer(caller, target, args[5]);
                break;
            case SET:
                setKeyOnPlayer(caller, target, args[5]);
                break;
            default:
                break;

        }
    }

    private void setKeyOnPlayer(MessageReceiver caller, Player player, String value) {
        switch(key) {
            case GROUP:
                Group g = Canary.usersAndGroups().getGroup(value);
                if(g == null) {
                    caller.notice(Translator.translate("modify invalid group"));
                    break;
                }
                player.setGroup(g);
                caller.message(Colors.YELLOW + Translator.translate("modify group set"));
                break;

            case PERMISSION:
                player.getPermissionProvider().addPermission(value, true);
                caller.message(Colors.YELLOW + Translator.translate("modify permission added"));
                break;

            case PREFIX:
                player.setColor(value);
                caller.message(Colors.YELLOW + Translator.translate("modify prefix set"));
                break;

            default:
                break;

        }
    }

    private void removeKeyOnPlayer(MessageReceiver caller, Player player, String value) {
        switch(key) {
            case GROUP:
                if(player.getGroup().getName().equalsIgnoreCase(value)) {
                    player.setGroup(Canary.usersAndGroups().getDefaultGroup());
                    caller.message(Colors.YELLOW + Translator.translate("modify group removed"));
                }
                break;

            case PERMISSION:
                Canary.permissionManager().removePlayerPermission(value, player.getName());
                player.getPermissionProvider().reload();
                caller.message(Colors.YELLOW + Translator.translate("modify permission removed"));
                break;

            case PREFIX:
                player.setColor(null);
                caller.message(Colors.YELLOW + Translator.translate("modify prefix removed"));
                break;

            default:
                break;

        }
    }

    private void setKeyOnGroup(MessageReceiver caller, Group group, String value) {
        switch(key) {
            case GROUP:
                Group g = Canary.usersAndGroups().getGroup(value);
                if(g == null) {
                    caller.notice(Translator.translate("modify invalid group"));
                    break;
                }
                group.setParent(g);
                caller.message(Colors.YELLOW + Translator.translate("modify group set"));
                break;

            case PERMISSION:
                group.getPermissionProvider().addPermission(value, true);
                caller.message(Colors.YELLOW + Translator.translate("modify permission added"));
                break;

            case PREFIX:
                group.setPrefix(value);
                caller.message(Colors.YELLOW + Translator.translate("modify prefix set"));
                break;

            default:
                break;

        }
    }

    private void removeKeyOnGroup(MessageReceiver caller, Group group, String value) {
        switch(key) {
            case GROUP:
                if(group.getParent().getName().equalsIgnoreCase(value)) {
                    group.setParent(null);
                }
                caller.message(Colors.YELLOW + Translator.translate("modify group removed"));
                break;

            case PERMISSION:
                Canary.permissionManager().removeGroupPermission(value, group.getName());
                group.getPermissionProvider().reload();
                caller.message(Colors.YELLOW + Translator.translate("modify permission removed"));
                break;

            case PREFIX:
                group.setPrefix(null);
                caller.message(Colors.YELLOW + Translator.translate("modify prefix removed"));
                break;

            default:
                break;

        }
    }

    private boolean checkPreconditions(String[] args) {
        key = Key.fromString(args[4]);
        action = Action.fromString(args[3]);
        return key != null && action != null;
    }
    private enum Key {
        PERMISSION,
        GROUP,
        PREFIX;

        public static Key fromString(String in) {
            for(Key n : Key.values()) {
                if(n.name().equals(in.toUpperCase())) {
                    return n;
                }
            }
            return null;
        }
    }

    private enum Action {
        SET,
        REMOVE;

        public static Action fromString(String in) {
            for(Action n : Action.values()) {
                if(n.name().equals(in.toUpperCase())) {
                    return n;
                }
            }
            return null;
        }
    }
}
