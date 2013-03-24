package net.canarymod.commandsys.commands;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.canarymod.Canary;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.Item;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;
import net.canarymod.kit.Kit;
import net.canarymod.user.Group;


public class KitCommand extends CanaryCommand {

    public KitCommand() {
        super("canary.command.kit", "Give yourself or others kits or create new kits", "Usage: /kit create <name> <use delay> [G/P] [Groups/players to use this kit] - Create a kit from your current Inventory", 1);
    }

    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server) {
            caller.notice("You cannot work with kits as the console");
        } else if (caller instanceof Player) {
            player((Player) caller, parameters);
        } else {
            throw new CommandException("Unknown MessageReceiver: " + caller.getClass().getSimpleName());
        }
    }
    
    private void player(Player player, String[] args) {
        // List kits etc
        if (args.length == 1) {
            player.notice("Usage: /kit give <name> [player]- Give kit with given name, optionally to a player"); 
            player.notice("Usage: /kit create <name> <use delay> [G/P] [Groups/players to use this kit] - Create a kit from your current Inventory"); 
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
                            player.sendMessage(Colors.YELLOW + "Enjoy your kit.");
                            return;
                        } else {
                            player.notice("This kit is currently not available");
                            return;
                        }
                        
                    } else {
                        player.notice(args[2] + " is not a kit.");
                        return;
                    }
                }
                
                // Give kit to a subject
                if (args.length > 3) { 
                    if (!player.hasPermission("canary.command.kit.other")) {
                        return;
                    }
                    Player recipient = Canary.getServer().matchPlayer(args[3]);

                    if (recipient != null) {
                        Kit kit = Canary.kits().getKit(args[2]);

                        if (kit != null) {
                            if (kit.giveKit(recipient)) {
                                recipient.sendMessage(Colors.YELLOW + "Enjoy this kit " + player.getName() + " gave you.");
                                return;
                            } else {
                                player.notice("This kit is currently not available for " + recipient.getName());
                                return;
                            }
                        } else {
                            player.notice(args[2] + " is not a kit.");
                            return;
                        }
                    } else {
                        player.notice(args[3] + " not found on the server.");
                        return;
                    }
                }
            }
            
            //
            // CREATE KITS
            //
            if (args[1].equalsIgnoreCase("create")) {
                if (args.length < 4) {
                    player.notice("Usage: /kit create <name> <use delay> [G/P] [Groups/players to use this kit] - Create a kit from your current Inventory");
                    return;
                }
                // Default public kit
                if (args.length == 4) {
                    Kit newKit = new Kit();

                    newKit.setContent(new ArrayList<Item>(Arrays.asList(player.getInventory().getContents())));
                    newKit.setDelay(Integer.parseInt(args[3]));
                    newKit.setName(args[2]);
                    Canary.kits().addKit(newKit);
                    player.sendMessage(Colors.YELLOW + args[2] + " has been created.");
                    return;
                }
                
                if (args.length >= 6) {
                    // ADD GROUPS KIT
                    if (args[4].equalsIgnoreCase("G") && player.hasPermission("canary.command.kit.group")) {
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
                        player.sendMessage(Colors.YELLOW + "Group Kit " + args[2] + " has been created.");
                        return;
                    } // ADD PLAYER PRIVATE KIT
                    else if (args[4].equalsIgnoreCase("G") && player.hasPermission("canary.command.kit.group")) {
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
                        player.sendMessage(Colors.YELLOW + "Private Kit " + args[2] + " has been created.");
                        return;
                    } else {
                        player.notice("Usage: /kit create <name> <use delay> [G/P] [Groups/players to use this kit] - Create a kit from your current Inventory");
                        return;
                    }
                }
            }
            player.notice("Usage: /kit create <name> <use delay> [G/P] [Groups/players to use this kit] - Create a kit from your current Inventory");
            return;
        } else {
            player.notice("Usage: /kit give <name> [player]- Give kit with given name, optionally to a player");
            player.notice("Usage: /kit create <name> <use delay> [G/P] [Groups/players to use this kit] - Create a kit from your current Inventory");
            return;
        }
    }

}
