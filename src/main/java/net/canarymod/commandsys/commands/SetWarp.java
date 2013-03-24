package net.canarymod.commandsys.commands;


import net.canarymod.Canary;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;
import net.canarymod.user.Group;
import net.canarymod.warp.Warp;


public class SetWarp extends CanaryCommand {

    public SetWarp() {
        super("canary.command.setwarp", "Set the current worlds spawn location", "Usage: /setspawn", 1);
    }

    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server) {
            console(caller);
        } else if (caller instanceof Player) {
            player((Player) caller, parameters);
        } else {
            throw new CommandException("Unknown MessageReceiver: " + caller.getClass().getSimpleName());
        }
    }
    
    private void console(MessageReceiver caller) {
        caller.notice("As the great Minecraft Skies cannot be reached by mortals, you cannot set a warp here.");
    }
    
    private void player(Player player, String[] args) {
        Warp test = Canary.warps().getWarp(args[1]);
        
        if (test != null) {
            if (test.isPlayerHome() || !player.hasPermission("canary.command.setwarp.admin")) {
                player.notice("Could not set the warp!");
                return;
            }
        }
        // SET PUBLIC WARP
        if (args.length == 2 && player.hasPermission("canary.command.setwarp.public")) {
            Warp newWarp = new Warp(player.getLocation(), args[1]);

            Canary.warps().addWarp(newWarp);
            player.sendMessage(Colors.YELLOW + "Warp " + args[1] + " has been set.");
            return;
        } else if (args.length > 3) {
            // SET GROUP SPECIFIC WARP
            if (args[2].equalsIgnoreCase("G") && player.hasPermission("canary.command.setwarp.group")) {
                Group[] groups = new Group[args.length - 3];

                for (int i = 0; i < groups.length; i++) { 
                    groups[i] = Canary.usersAndGroups().getGroup(args[i + 3]);
                }
                Warp newWarp = new Warp(player.getLocation(), groups, args[1]);

                Canary.warps().addWarp(newWarp);
                player.sendMessage(Colors.YELLOW + "Groupwarp " + args[1] + " has been set.");
                return;
            }
            // SET PRIVATE WARP
            if (args[2].equalsIgnoreCase("P") && player.hasPermission("canary.command.setwarp.private")) {
                Warp newWarp = new Warp(player.getLocation(), args[1], args[3], false);

                Canary.warps().addWarp(newWarp);
                player.sendMessage(Colors.YELLOW + "Private warp " + args[1] + " has been set.");
                return;
            } else {
                player.notice("Usage: setwarp <warp name> [G/P] [Group or owner name]");
                return;
            }
        } else {
            player.notice("Usage: setwarp <warp name> [G/P] [Group or owner name]");
            return;
        }
    }
}
