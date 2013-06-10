package net.canarymod.commandsys.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.Item;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CommandException;
import net.canarymod.kit.Kit;
import net.canarymod.user.Group;

public class KitCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server) {
            caller.notice(Translator.translate("kit console"));
        } else if (caller instanceof Player) {
            player((Player) caller, parameters);
        } else {
            throw new CommandException(Translator.translateAndFormat("unknown messagereceiver", caller.getClass().getSimpleName()));
        }
    }

    private void player(Player player, String[] args) {
        // List kits etc
        if (args.length == 1) {
            player.notice(Translator.translateAndFormat("usage", "/kit give <name> [player]"));
            player.notice(Translator.translateAndFormat("usage", "/kit create <name> <use delay> [G|P Groups|Players]") + " - " + Translator.translate("kit from inventory"));
            player.sendMessage(Colors.YELLOW + "Available Kits: ");
            List<Kit> kits = Canary.kits().getAllKits();
            StringBuilder kitList = new StringBuilder();

            for (Kit k : kits) {
                kitList.append(k.getName()).append(",");
            }
            player.sendMessage(kitList.toString());
        }
        if (args.length > 2) {
            //
            // GIVE KITS
            //
            if (args[1].equalsIgnoreCase("give")) {
                // Give kit to player
                if (args.length == 3) {
                    Kit kit = Canary.kits().getKit(args[2]);

                    if (kit != null) {
                        if (kit.giveKit(player)) {
                            player.sendMessage(Colors.YELLOW + Translator.translate("kit given"));
                            return;
                        } else {
                            player.notice(Translator.translate("kit unavailable"));
                            return;
                        }

                    } else {
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
                            if (kit.giveKit(recipient)) {
                                recipient.sendMessage(Colors.YELLOW + Translator.translateAndFormat("kit given other", player.getName()));
                                return;
                            } else {
                                player.notice(Translator.translateAndFormat("kit unavailable other", recipient.getName()));
                                return;
                            }
                        } else {
                            player.notice(Translator.translateAndFormat("kit invalid", args[2]));
                            return;
                        }
                    } else {
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
                    player.sendMessage(Colors.YELLOW + Translator.translateAndFormat("kit created", args[2]));
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
                            } else {
                                groups[i] = Canary.usersAndGroups().getDefaultGroup().getName();
                            }
                        }
                        Kit newKit = new Kit();

                        newKit.setContent(new ArrayList<Item>(Arrays.asList(player.getInventory().getContents())));
                        newKit.setDelay(Integer.parseInt(args[3]));
                        newKit.setName(args[2]);
                        newKit.setGroups(groups);
                        Canary.kits().addKit(newKit);
                        player.sendMessage(Colors.YELLOW + Translator.translateAndFormat("kit created group", args[2]));
                        return;
                    } // ADD PLAYER PRIVATE KIT
                    else if (args[4].equalsIgnoreCase("G") && player.hasPermission("canary.command.player.kit.private")) {
                        String[] players = new String[args.length - 5];

                        for (int i = 0; i < players.length; i++) {
                            players[i] = args[i + 5];
                        }
                        Kit newKit = new Kit();

                        newKit.setContent(new ArrayList<Item>(Arrays.asList(player.getInventory().getContents())));
                        newKit.setDelay(Integer.parseInt(args[3]));
                        newKit.setName(args[2]);
                        newKit.setOwner(players);
                        Canary.kits().addKit(newKit);
                        player.sendMessage(Colors.YELLOW + Translator.translateAndFormat("kit created private", args[2]));
                        return;
                    } else {
                        player.notice(Translator.translateAndFormat("usage", "/kit create <name> <use delay> [G|P Groups|Players]") + " - " + Translator.translate("kit from inventory"));;
                        return;
                    }
                }
            }
            player.notice(Translator.translateAndFormat("usage", "/kit create <name> <use delay> [G|P Groups|Players]") + " - " + Translator.translate("kit from inventory"));
            return;
        } else {
            player.notice(Translator.translateAndFormat("usage", "/kit give <name> [player]"));
            player.notice(Translator.translateAndFormat("usage", "/kit create <name> <use delay> [G|P Groups|Players]") + " - " + Translator.translate("kit from inventory"));
            return;
        }
    }

}
