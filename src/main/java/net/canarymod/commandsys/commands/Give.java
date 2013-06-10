package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.Item;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CommandException;

public class Give {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server) {
            console(caller, parameters);
        } else if (caller instanceof Player) {
            player((Player) caller, parameters);
        } else {
            throw new CommandException("Unknown MessageReceiver: " + caller.getClass().getSimpleName());
        }
    }

    private void console(MessageReceiver caller, String[] args) {
        if (args.length < 3) {
            Canary.help().getHelp(caller, "give");
            return;
        }

        int amount = 1;
        if (args[args.length - 2].matches("\\d+")) {
            amount = Integer.parseInt(args[args.length - 2]);
        }
        Item item = Canary.factory().getItemFactory().newItem(args[1]);
        if (item == null) {
            caller.notice(Translator.translateAndFormat("give invalid itemtype", args[1]));
            return;
        }
        item.setAmount(amount);
        Player target = Canary.getServer().matchPlayer(args[args.length - 1]);
        if (target == null) {
            caller.notice(Translator.translateAndFormat("unknown player", args[args.length - 1]));
            return;
        }
        target.giveItem(item);
        target.sendMessage(Colors.YELLOW + Translator.translateAndFormat("give received", item.getType().getDisplayName()));
        caller.notice(Translator.translateAndFormat("give success other", target.getName()));

    }

    private void player(Player player, String[] args) {
        // Give to player
        if (args.length == 2) {
            Item item = makeItem(args[1], 1);
            if (item == null) {
                player.notice(Translator.translateAndFormat("give invalid itemtype", args[1]));
                return;
            }
            player.giveItem(item);
            player.sendMessage(Colors.YELLOW + Translator.translate("give success"));
        }
        // Give to player or other
        else if (args.length == 3) {
            if (args[2].matches("\\d+")) {
                Item item = makeItem(args[1], Integer.parseInt(args[2]));
                if (item == null) {
                    player.notice(Translator.translateAndFormat("give invalid itemtype", args[1]));
                    return;
                }
                player.giveItem(item);
                player.sendMessage(Colors.YELLOW + Translator.translate("give success"));
            }
            else {
                if (!player.hasPermission("canary.command.give.other")) {
                    player.notice(Translator.translate("give failed"));
                    return;
                }
                Player target = Canary.getServer().matchPlayer(args[2]);
                if (target == null) {
                    player.notice(Translator.translateAndFormat("unknown player", args[2]));
                    return;
                }
                Item item = makeItem(args[1], 1);
                target.giveItem(item);
                target.sendMessage(Colors.YELLOW + Translator.translateAndFormat("give received", item.getType().getDisplayName()));
                player.notice(Translator.translateAndFormat("give success other", target.getName()));
            }
        }
        // Give to other
        else if (args.length == 4) {
            if (!player.hasPermission("canary.command.give.other")) {
                player.notice(Translator.translate("give failed"));
                return;
            }
            int amount = 1;
            if (args[2].matches("\\d+")) {
                amount = Integer.parseInt(args[2]);
            }
            Item item = makeItem(args[1], amount);
            if (item == null) {
                player.notice(Translator.translateAndFormat("give invalid itemtype", args[1]));
                return;
            }
            Player target = Canary.getServer().matchPlayer(args[3]);
            if (target == null) {
                player.notice(Translator.translateAndFormat("unknown player", args[3]));
                return;
            }
            target.giveItem(item);
            target.sendMessage(Colors.YELLOW + Translator.translateAndFormat("give received", item.getType().getDisplayName()));
            player.notice(Translator.translateAndFormat("give success other", target.getName()));
        }
    }

    /**
     * Make item from command string and amount
     * 
     * @param input
     * @param amount
     * @return
     */
    private Item makeItem(String input, int amount) {
        Item i = Canary.factory().getItemFactory().newItem(input);
        if (i == null) {
            return null;
        }
        i.setAmount(amount);
        return i;
    }

}
