package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.ToolBox;
import net.canarymod.api.OfflinePlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Position;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.chat.TextFormat;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.user.Group;
import net.canarymod.warp.Warp;

/**
 * Command to view multiple types of info about a player (possible yourself)
 *
 * @author Jason (darkdiplomat)
 */
public class PlayerInformation implements NativeCommand {

    @Override
    public void execute(MessageReceiver caller, String[] args) {
        Player subject = null;
        if (args.length == 2) {
            subject = Canary.getServer().matchPlayer(args[1]);
        }
        else if (caller instanceof Player) {
            subject = (Player) caller;
        }
        if (subject != null) {
            caller.message(TextFormat.GREEN + subject.getName() + "'s info:");
            sendData(caller, "First Joined: ", subject.getFirstJoined());
            sendData(caller, "Time Played: ", ToolBox.getTimeUntil(subject.getTimePlayed()));
            sendData(caller, "Muted: ", subject.isMuted());
            sendData(caller, "Prefix: ", subject.getPrefix());
            sendData(caller, "IP: ", subject.getIP());
            sendData(caller, "Primary Group: ", subject.getGroup().getName());
            sendData(caller, "Other Groups: ", subject.getPlayerGroups());
            sendData(caller, "Health: ", subject.getHealth());
            sendData(caller, "Mode: ", subject.getMode());
            sendData(caller, "Food Level: ", subject.getHunger());
            sendData(caller, "Food Exhaustion: ", String.format("%.2f", subject.getExhaustionLevel()));
            sendData(caller, "XP Level: ", subject.getLevel());
            sendData(caller, "XP Total: ", subject.getExperience());
            Location l = subject.getLocation();

            sendData(caller, "Position: ", String.format("X: %.2f Y: %.2f Z: %.2f Pitch: %.2f Yawn: %.2f", l.getX(), l.getY(), l.getZ(), l.getRotation(), l.getPitch()));
            sendData(caller, "World: ", subject.getWorld().getFqName());
            Warp home = Canary.warps().getHome(subject.getName());

            if (home != null) {
                l = home.getLocation();
                sendData(caller, "Home: ", String.format("X: %.2f Y: %.2f Z: %.2f", l.getX(), l.getY(), l.getZ()));
            }
            else {
                sendData(caller, "Home: ", "Not set");
            }
        }
        else if (args.length == 2) {
            OfflinePlayer oSubject = Canary.getServer().getOfflinePlayer(args[1]);
            if (oSubject != null) {
                caller.message(TextFormat.GREEN + oSubject.getName() + "'s Offline info:");
                sendData(caller, "First Joined: ", oSubject.getFirstJoined());
                sendData(caller, "Time Played: ", ToolBox.getTimeUntil(oSubject.getTimePlayed()));
                sendData(caller, "Muted: ", oSubject.isMuted());
                sendData(caller, "Prefix: ", oSubject.getPrefix());
                sendData(caller, "Primary Group: ", oSubject.getGroup().getName());
                sendData(caller, "Other Groups: ", oSubject.getPlayerGroups());
                Position l = oSubject.getPosition();
                sendData(caller, "Position: ", String.format("X: %.2f Y: %.2f Z: %.2f", l.getX(), l.getY(), l.getZ()));
                sendData(caller, "World: ", oSubject.getWorld().getFqName());
                Warp home = Canary.warps().getHome(oSubject.getName());
                if (home != null) {
                    l = home.getLocation();
                    sendData(caller, "Home: ", String.format("X: %.2f Y: %.2f Z: %.2f", l.getX(), l.getY(), l.getZ()));
                }
                else {
                    sendData(caller, "Home: ", "Not set");
                }
            }
            else {
                caller.notice("Can't find player " + args[1]);
            }
        }
    }

    private void sendData(MessageReceiver caller, String caption, Group[] data) {
        StringBuilder gnames = new StringBuilder("");
        for (int index = 1; index < data.length; index++) {
            gnames.append(data[index].getName());
        }
        caller.message(TextFormat.LIGHT_GREEN + caption + TextFormat.ORANGE + gnames.toString());
    }

    private void sendData(MessageReceiver caller, String caption, Object data) {
        caller.message(TextFormat.LIGHT_GREEN + caption + TextFormat.ORANGE + String.valueOf(data));
    }

}
