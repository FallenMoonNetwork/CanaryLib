package net.canarymod;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.World;
import net.canarymod.chat.MessageReceiver;
import net.visualillusionsent.utils.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Message of the Day container
 * <p/>
 * Loads and sends the Message of the Day to a player just joining.
 */
public class MessageOfTheDay {
    private static final ArrayList<String> motd_lines = new ArrayList<String>();

    public MessageOfTheDay(){
        try{
             loadMOTD();
        }
         catch(Exception ex){
             Canary.logSevere("Failed to read/write Message of the Day from/to the motd.txt file.", ex);
         }
    }

    private void loadMOTD() throws IOException {
        File motd_file = new File("config/motd.txt");
        if(!motd_file.exists()){
            if(!motd_file.createNewFile()){
                return;
            }
            PrintWriter writer = new PrintWriter(new FileWriter(motd_file));
            writer.println("# (Login) Message of the Day");
            writer.println("# Colors can be prefixed with &");
            writer.println("# {name} gets the receivers's name");
            writer.println("# {player.list} gets the currently logged in players");
            writer.println("# {world} gets the receiver's current world");
            writer.println("# {world.time} gets the receiver's current world time converted into a 24Hr Clock time (ie: 18:32)");
            writer.println("# {world.time.total} gets the receiver's current world's total time");
            writer.println("# {world.time.relative} gets the receiver's current world's relative time (0-24000)");
            writer.println("# {world.weather} gets the receiver's current world's weather");
            writer.println("# # # # #");
            writer.flush();
            writer.close();
        }
        else{
            FileInputStream fis = new FileInputStream(motd_file);
            Scanner scanner = new Scanner(fis, "UTF-8");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.startsWith("#")) {
                    continue;
                }
                motd_lines.add(line);
            }
            scanner.close();
            fis.close();
        }
    }

    /**
     * Sends teh MOTD to a {@link MessageReceiver}
     *
     * @param msgrec
     * the {@link MessageReceiver} who will receiver the MOTD
     */
    public void sendMOTD(MessageReceiver msgrec){
        for(String line : motd_lines){
            String toSend = line;
            // Name
            toSend = toSend.replace("{name}", msgrec.getName());
            // Player List
            toSend = toSend.replace("{player.list}", StringUtils.joinString(Canary.getServer().getPlayerNameList(), ", ", 0));
            // Current Group
            toSend = toSend.replace("{group}", msgrec instanceof Player ? ((Player)msgrec).getGroup().getName() : "N/A");
            // Current World
            toSend = toSend.replace("{world}", msgrec instanceof Player ? ((Player)msgrec).getWorld().getName() : "N/A");
            // Current World Weather
            toSend = toSend.replace("{world.weather}", msgrec instanceof Player ? getWeather(((Player)msgrec).getWorld()) : "N/A");
            // Current World Time (24H)
            toSend = toSend.replace("{world.time}", msgrec instanceof Player ? ToolBox.minecraftTimeTo24hClock(((Player)msgrec).getWorld().getRelativeTime()) : "N/A");
            // Current World Time Total
            toSend = toSend.replace("{world.time.total}", msgrec instanceof Player ? String.valueOf(((Player)msgrec).getWorld().getTotalTime()) : "N/A");
            // Current World Time relative
            toSend = toSend.replace("{world.time.relative}", msgrec instanceof Player ? String.valueOf(((Player)msgrec).getWorld().getRelativeTime()) : "N/A");

            //Replace Color codes
            toSend = toSend.replaceAll("&([0-9A-FK-ORa-fk-or])", "\u00A7$1");
            msgrec.message(toSend);
        }
    }

    private final String getWeather(World world){
        if(world.isThundering()){
            return "thunderstorm";
        }
        else if (world.isRaining()){
            return "rain";
        }
        else{
            return "sun";
        }
    }
}
