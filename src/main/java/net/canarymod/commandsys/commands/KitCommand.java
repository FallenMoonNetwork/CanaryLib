package net.canarymod.commandsys.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.Item;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.kit.Kit;
import net.canarymod.user.Group;

/**
 * Kit Command
 *
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public class KitCommand implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Player) {
            player((Player) caller, parameters);
        }
        else {
            server(caller, parameters);
        }
    }

    // Lets give Console ability to give out kits as well (Mainly for online store stuff, yeah! darkdiplomat is thinking!)
    // You're a wise old monk. In the spirit of your wise-ness I shall add this ability to CommandBlocks - Chris
    private void server(MessageReceiver caller, String[] args) {
        // List kits etc
        if (args.length < 4) {
            caller.notice(Translator.translateAndFormat("usage", "/kit give <name> <player> [override]"));
            caller.message(Colors.YELLOW + "Available Kits: ");
            List<Kit> kits = Canary.kits().getAllKits();
            StringBuilder kitList = new StringBuilder();

            for (Kit k : kits) {
                kitList.append(k.getName()).append(",");
            }
            caller.message(kitList.toString());
            return;
        }
        //
        // GIVE KITS
        //
        if (args[1].equalsIgnoreCase("give")) {
            // Give kit to a subject
            if (args.length >= 4) {
                boolean override = args.length > 4 && args[4].toLowerCase().equals("override");
                Player recipient = Canary.getServer().matchPlayer(args[3]);

                if (recipient != null) {
                    Kit kit = Canary.kits().getKit(args[2]);

                    if (kit != null) {
                        if (kit.giveKit(recipient, override)) {
                            recipient.message(Colors.YELLOW + Translator.translateAndFormat("kit given other", caller.getName()));
                            return;
                        }
                        else {
                            caller.notice(Translator.translateAndFormat("kit unavailable other", recipient.getName()));
                            return;
                        }
                    }
                    else {
                        caller.notice(Translator.translateAndFormat("kit invalid", args[2]));
                        return;
                    }
                }
                else {
                    caller.notice(Translator.translateAndFormat("unknown player", args[3]));
                    return;
                }
            }
        }
        caller.notice(Translator.translateAndFormat("usage", "/kit give <name> <player> [override]"));
    }

    private void player(Player player, String[] args) {
        // List kits etc
        if (args.length == 1) {
            player.notice(Translator.translateAndFormat("usage", "/kit give <name> [player]"));
            player.notice(Translator.translateAndFormat("usage", "/kit create <name> <use delay> [G|P Groups|Players]") + " - " + Translator.translate("kit from inventory"));
            player.message(Colors.YELLOW + "Available Kits: ");
            List<Kit> kits = Canary.kits().getAllKits();
            StringBuilder kitList = new StringBuilder();

            for (Kit k : kits) {
                kitList.append(k.getName()).append(",");
            }
            player.message(kitList.toString());
            return;
        }
        //
        // GIVE KITS
        //
        if (args[1].equalsIgnoreCase("give")) {
            if (args.length < 3) {
                player.message(Colors.YELLOW + "Available Kits: ");
                List<Kit> kits = Canary.kits().getAllKits();
                StringBuilder kitList = new StringBuilder();

                for (Kit k : kits) {
                    kitList.append(k.getName()).append(",");
                }
                player.message(kitList.toString());
                return;
            }
            // Give kit to player
            if (args.length == 3) {
                Kit kit = Canary.kits().getKit(args[2]);

                if (kit != null) {
                    if (kit.giveKit(player, false)) {
                        player.message(Colors.YELLOW + Translator.translate("kit given"));
                        return;
                    }
                    else {
                        player.notice(Translator.translate("kit unavailable"));
                        return;
                    }

                }
                else {
                    player.notice(Translator.translateAndFormat("kit invalid", args[2]));
                    return;
                }
            }

            // Give kit to a subject
            if (args.length > 3) {
                if (!player.hasPermission("canary.command.player.kit.other")) {
                    return;
                }
                Player recipient = Canary.getServer().matchPlayer(args[3]);

                if (recipient != null) {
                    Kit kit = Canary.kits().getKit(args[2]);

                    if (kit != null) {
                        if (kit.giveKit(recipient, false)) {
                            recipient.message(Colors.YELLOW + Translator.translateAndFormat("kit given other", player.getName()));
                            return;
                        }
                        else {
                            player.notice(Translator.translateAndFormat("kit unavailable other", recipient.getName()));
                            return;
                        }
                    }
                    else {
                        player.notice(Translator.translateAndFormat("kit invalid", args[2]));
                        return;
                    }
                }
                else {
                    player.notice(Translator.translateAndFormat("unknown player", args[3]));
                    return;
                }
            }
        }

        //
        // CREATE KITS
        //
        if (args[1].equalsIgnoreCase("create")) {
            if (args.length < 4) {
                player.notice(Translator.translateAndFormat("usage", "/kit create <name> <use delay> [G|P Groups|Players]") + " - " + Translator.translate("kit from inventory"));
                return;
            }
            // Default public kit
            if (args.length == 4) {
                Kit newKit = new Kit();

                newKit.setContent(new ArrayList<Item>(Arrays.asList(player.getInventory().getContents())));
                newKit.setDelay(Integer.parseInt(args[3]));
                newKit.setName(args[2]);
                Canary.kits().addKit(newKit);
                player.message(Colors.YELLOW + Translator.translateAndFormat("kit created", args[2]));
                return;
            }

            if (args.length >= 6) {
                // ADD GROUPS KIT
                if (args[4].equalsIgnoreCase("G") && player.hasPermission("canary.command.player.kit.group")) {
                    String[] groups = new String[args.length - 5];

                    for (int i = 0; i < groups.length; i++) {
                        Group g = Canary.usersAndGroups().getGroup(args[i + 5]);

                        if (g != null) {
                            groups[i] = g.getName();
                        }
                        else {
                            groups[i] = Canary.usersAndGroups().getDefaultGroup().getName();
                        }
                    }
                    Kit newKit = new Kit();

                    newKit.setContent(new ArrayList<Item>(Arrays.asList(player.getInventory().getContents())));
                    newKit.setDelay(Integer.parseInt(args[3]));
                    newKit.setName(args[2]);
                    newKit.setGroups(groups);
                    Canary.kits().addKit(newKit);
                    player.message(Colors.YELLOW + Translator.translateAndFormat("kit created group", args[2]));
                    return;
                } // ADD PLAYER PRIVATE KIT
                else if (args[4].equalsIgnoreCase("G") && player.hasPermission("canary.command.player.kit.private")) {
                    String[] players = new String[args.length - 5];

                    System.arraycopy(args, 5, players, 0, players.length);
                    Kit newKit = new Kit();

                    newKit.setContent(new ArrayList<Item>(Arrays.asList(player.getInventory().getContents())));
                    newKit.setDelay(Integer.parseInt(args[3]));
                    newKit.setName(args[2]);
                    newKit.setOwner(players);
                    Canary.kits().addKit(newKit);
                    player.message(Colors.YELLOW + Translator.translateAndFormat("kit created private", args[2]));
                    return;
                }
                else {
                    player.notice(Translator.translateAndFormat("usage", "/kit create <name> <use delay> [G|P Groups|Players]") + " - " + Translator.translate("kit from inventory"));
                    return;
                }
            }
            player.notice(Translator.translateAndFormat("usage", "/kit create <name> <use delay> [G|P Groups|Players]") + " - " + Translator.translate("kit from inventory"));
            return;

        }
        player.notice(Translator.translateAndFormat("usage", "/kit give <name> [player]"));
        player.notice(Translator.translateAndFormat("usage", "/kit create <name> <use delay> [G|P Groups|Players]") + " - " + Translator.translate("kit from inventory"));
    }
}
