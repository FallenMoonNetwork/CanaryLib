package net.canarymod;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.World;
import net.canarymod.api.world.blocks.CommandBlock;
import net.canarymod.chat.MessageReceiver;
import net.visualillusionsent.utils.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
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
     * Sends the MOTD to a {@link MessageReceiver}
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
            toSend = toSend.replace("{player.list}", Canary.getServer().getNumPlayersOnline() > 0 ? StringUtils.joinString(Canary.getServer().getPlayerNameList(), ", ", 0) : "");
            // Player Count
            toSend = toSend.replace("{player.count}", String.valueOf(Canary.getServer().getNumPlayersOnline()));
            // Max Allowed online
            toSend = toSend.replace("{player.max}", String.valueOf(Canary.getServer().getMaxPlayers()));
            // Current Group
            toSend = toSend.replace("{group}", msgrec instanceof Player ? ((Player)msgrec).getGroup().getName() : "N/A");
            // Current World
            toSend = toSend.replace("{world}", msgrec instanceof Player ? ((Player)msgrec).getWorld().getName() : Canary.getServer().getDefaultWorldName());
            // Current World Weather
            toSend = toSend.replace("{world.weather}", msgrec instanceof Player ? getWeather(((Player)msgrec).getWorld()) : getWeather(Canary.getServer().getDefaultWorld()));
            // Current World Time (24H)
            toSend = toSend.replace("{world.time.24h}", getTime(msgrec, 0));
            // Current World Time (12H)
            toSend = toSend.replace("{world.time.12h}", getTime(msgrec, 1));
            // Current World Time Total
            toSend = toSend.replace("{world.time.total}", getTime(msgrec, 2));
            // Current World Time relative
            toSend = toSend.replace("{world.time.raw}", getTime(msgrec, 3));
            // Server Uptime
            toSend = toSend.replace("{server.uptime}", ToolBox.getTimeUntil(ManagementFactory.getRuntimeMXBean().getUptime() / 1000));
            
            
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
    
    private final String getTime(MessageReceiver msgrec, int type){
    	switch(type){
    	case 0: // 24hr
    		if(msgrec instanceof CommandBlock){
    			return ToolBox.minecraftTimeTo24hClock(((CommandBlock)msgrec).getWorld().getRelativeTime());
    		}
    		else if (msgrec instanceof Player){
    			return ToolBox.minecraftTimeTo24hClock(((Player)msgrec).getWorld().getRelativeTime());
    		}
    		else{
    			return ToolBox.minecraftTimeTo24hClock(Canary.getServer().getDefaultWorld().getRelativeTime());
    		}
    				
    	case 1: // 12hr
    		if(msgrec instanceof CommandBlock){
    			return ToolBox.minecraftTimeTo12hClock(((CommandBlock)msgrec).getWorld().getRelativeTime());
    		}
    		else if (msgrec instanceof Player){
    			return ToolBox.minecraftTimeTo12hClock(((Player)msgrec).getWorld().getRelativeTime());
    		}
    		else{
    			return ToolBox.minecraftTimeTo12hClock(Canary.getServer().getDefaultWorld().getRelativeTime());
    		}
    	case 2: // total[
    		if(msgrec instanceof CommandBlock){
    			return String.valueOf(((CommandBlock)msgrec).getWorld().getTotalTime());
    		}
    		else if (msgrec instanceof Player){
    			return String.valueOf(((Player)msgrec).getWorld().getTotalTime());
    		}
    		else{
    			return String.valueOf(Canary.getServer().getDefaultWorld().getTotalTime());
    		}
    	case 3: // raw
    		if(msgrec instanceof CommandBlock){
    			return String.valueOf(((CommandBlock)msgrec).getWorld().getRelativeTime());
    		}
    		else if (msgrec instanceof Player){
    			return String.valueOf(((Player)msgrec).getWorld().getRelativeTime());
    		}
    		else{
    			return String.valueOf(Canary.getServer().getDefaultWorld().getRelativeTime());
    		}
    	}
    	return "N/A";
    }
}
