package net.canarymod.commandsys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.canarymod.Canary;
import net.canarymod.Colors;
import net.canarymod.Logman;
import net.canarymod.TextFormat;
import net.canarymod.api.entity.Player;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.Dimension;
import net.canarymod.converter.CanaryToVanilla;
import net.canarymod.config.Configuration;
import net.canarymod.kit.Kit;
import net.canarymod.user.Group;
import net.canarymod.warp.Warp;

public enum CanaryCommands {
    
    COMPASS ("canary.command.player.compass", "Displays the cardinal direction you're looking at."){
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && player.hasPermission(permission)){
                double degrees =  (player.getRotation() - 180) % 360;
                if (degrees < 0) {
                    degrees += 360.0;
                }
                
                player.notify("Compass: " + player.getCardinalDirection().toString() + " (" + (Math.round(degrees * 10) / 10.0) + ")");
                return true;
            }
            else if(player == null){
                Logman.logInfo("Looking down from the great Minecraft Skies!");
            }
            return false;
        }
    },
    
    DISABLEPLUGIN ("canary.command.plugin.disable", "Disable a plugin"){
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            if(player != null) {
                if(args.length == 1){
                    return passMessage(player, "Correct Usage: 'disableplugin <pluginname>'");
                }
                else if(Canary.loader().disablePlugin(args[1])) {
                    return passMessage(player, Colors.Green + "Disabled plugin '" + args[1] +  "'");
                }
                else {
                    return passMessage(player, "Unable to disable plugin '"+args[1]+"'");
                }
            }
            else {
                //Console command
                if(args.length == 1){
                    Logman.logInfo("Correct Usage: 'disableplugin <pluginname>'");
                    return true;
                }
                else if(Canary.loader().disablePlugin(args[1])) {
                    Logman.logInfo("Disabled plugin '" + args[1] +  "'");
                    return true;
                }
                else {
                    Logman.logInfo("Unable to disable plugin '"+args[1]+"'");
                    return true;
                }
            }
        }
    },
    
    EMOTE ("canary.command.emote", "Show an emotion in chat (* player facepalms)"){
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            
            if (player != null && player.isMuted()) {
                player.notify("You are currently muted.");
                return true;
            }
            if(player != null) {
                if (args.length == 1) {
                    return passMessage(player, "Correct Usage: /emote <emotion>");
                }
                
                String emote = "* " + (player != null ? player.getColor()+player.getName() : Colors.DarkPurple+"CONSOLE") + Colors.White + " " + Canary.glueString(args, 1, " ");
                Logman.logInfo(emote);
                Canary.getServer().broadcastMessage(emote);
                
                return true;
            }
            Logman.logInfo("Command not supported from the console.");
            return true;
        }
    },

    ENABLEPLUGIN ("canary.command.plugin.enable", "<pluginname>"){
        @Override
        public boolean execute(Player player, String[] args) {
            if(player == null) {
                if(args.length == 1){
                    Logman.logInfo("Correct Usage: 'enableplugin <pluginname>'");
                    return true;
                }
                if(Canary.loader().enablePlugin(args[1])) {
                    Logman.logInfo("Enabled Plugin: '" + args[1] +  "'");
                    return true;
                }
                else {
                    Logman.logInfo("Unable to enable plugin '"+args[1]+"'");
                    return true;
                }
            }

            if(!player.hasPermission(permission)){
                return false;
            }
            
            if(args.length == 1){
                return passMessage(player, "Correct Usage: 'enableplugin <pluginname>'");
            }
            else if(Canary.loader().enablePlugin(args[1])) {
                return passMessage(player, Colors.Green + "Enabled Plugin: '" + args[1] +  "'");
            }
            else {
                return passMessage(player, "Unable to enable plugin '"+args[1]+"'");
            }
        }
    },

    GETPOS ("canary.command.getpos", "") {
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && player.hasPermission(permission)){
                player.sendMessage(Colors.Gold+"Pos X: "+Colors.LightGray+player.getX()+Colors.Gold+" Y: "+Colors.LightGray+ player.getY()+Colors.Gold+" Z: "+Colors.LightGray+player.getZ());
                player.sendMessage(Colors.Gold+"Rotation: "+Colors.LightGray+player.getRotation()+Colors.Gold+" Pitch: "+Colors.LightGray+player.getPitch());

                double degrees =  ((player.getRotation() - 90) % 360);
                if (degrees < 0) {
                    degrees += 360.0;
                }
                
                player.notify("Compass: " + player.getCardinalDirection().toString() + " (" + (Math.round(degrees * 10) / 10.0) + ")");
                return true;
            }
            else if (player == null){
                Logman.logInfo("You are in the great Minecraft Skies!");
                return true;
            }
            return false;
        }
    },

    HELP ("canary.command.help", "[page]"){
        @Override
        public boolean execute(Player player, String[] args) {
            if(player == null) {
                int page = 0;
                if(args.length > 1) {
                    page = Integer.valueOf(args[1])-1;
                }

                String[] lines = Canary.help().getHelp(player,page); 

                if(lines == null) {
                    Logman.logInfo("Help-page not found");
                    return true;
                }
                // Send all lines
                for(String l : lines) {
                    Logman.logInfo(l);
                }
                return true;
            }
            
            if(!player.hasPermission(permission)) {
                return false;
            }

            int page = 0;
            if(args.length > 1) {
                page = Integer.valueOf(args[1])-1;
            }

            String[] lines = Canary.help().getHelp(player,page); 

            if(lines == null) {
                return passMessage(player, "Help-page not found");
            }

            // Send all lines
            for(String l : lines) {
                passMessage(player, l);
            }
            return true;
        }
    },

    HOME ("canary.command.home", "") {
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && player.hasPermission(permission)){
                if(args.length > 1 && player.hasPermission(permission+".other")){
                    Player thePlayer = Canary.getServer().matchPlayer(args[1]);
                    if(thePlayer != null){
                        if(thePlayer.hasHome()){
                            player.notify("Going to "+thePlayer.getName()+"'s home.");
                            player.teleportTo(thePlayer.getHome());
                        }
                        else{
                            player.notify("Player: '"+thePlayer.getName()+"' does not have a home set.");
                        }
                    }
                    else{
                        player.notify("Player: '"+args[1]+"' not found.");
                    }
                }
                else if(player.hasHome()){
                    player.notify("Going home.");
                    player.teleportTo(player.getHome());
                }
                else{
                    player.notify("You do not have a home set.");
                }
                return true;
            }
            if(player == null){
                Logman.logInfo("Command not supported from the console.");
                return true;
            }
            return false;
        }
    },

    KILL ("canary.command.kill", "<player> - Kills a player"){
        @Override
        public boolean execute(Player player, String[] args) {
            if(player == null) {
                if(args.length == 1){
                    Logman.logInfo("Correct Usage: 'kill <player>'");
                    return true;
                }
                
                Player thePlayer = Canary.getServer().matchPlayer(args[1]);
                if(thePlayer != null) {
                    thePlayer.kill();
                    Canary.getServer().broadcastMessage(Colors.Yellow+thePlayer.getName()+Colors.Rose+" was murdered by " + Colors.Yellow + "CONSOLE.");
                }
                else {
                    Logman.logInfo(args[1] + " does not exist. Not killed.");
                    return true;
                }
            }
            
            if(!player.hasPermission(permission)){
                return false;
            }
            if(args.length == 1){
                return passMessage(player, "Correct Usage: 'kill <player>'"); 
            }
            
            Player thePlayer = Canary.getServer().matchPlayer(args[1]);
            if(thePlayer != null){
                if(player.getGroup().hasControlOver(thePlayer.getGroup())) {
                    thePlayer.kill();
                    Canary.getServer().broadcastMessage(Colors.Yellow+thePlayer.getName()+Colors.Rose+" was murdered by "+Colors.Yellow+player.getName()+".");
                    return true;
                }
                else {
                    return passMessage(player, "You cannot kill "+args[1]);
                }
            }
            else{
                return passMessage(player, "Player: "+args[1]+" not found.");
            }
        }
    },

    KIT ("canary.command.kit", ""){
        @Override
        public boolean execute(Player player, String[] args) {
            if(player == null) {
                Logman.logInfo("Command not supported from the console.");
                return true;
            }
            
            if(!player.hasPermission(permission)){
                return false;
            }
            //List kits etc
            if(args.length == 1) {
                player.notify("Usage: /kit give <name> [player]- Give kit with given name, optionally to a player"); 
                player.notify("Usage: /kit create <name> <use delay> [G/P] [Groups/players to use this kit] - Create a kit from your current Inventory"); 
                player.sendMessage(Colors.Yellow+"Available Kits: ");
                List<Kit> kits = Canary.kits().getAllKits();
                StringBuilder kitList = new StringBuilder();
                for(Kit k : kits) {
                    kitList.append(k.getName()).append(",");
                }
                player.sendMessage(kitList.toString());
                return true;
            }
            
            if(args.length > 2) {
                //
                // GIVE KITS
                //
                if(args[1].equalsIgnoreCase("give")) {
                    //Give kit to player
                    if(args.length == 3) {
                        Kit kit = Canary.kits().getKit(args[2]);
                        if(kit != null) {
                            if(kit.giveKit(player)) {
                                player.sendMessage(Colors.Yellow+"Enjoy your kit.");
                                return true;
                            }
                            else {
                                return passMessage(player, "This kit is currently not available");
                            }
                            
                        }
                        else {
                            return passMessage(player, args[2]+" is not a kit.");
                        }
                    }
                    
                    //Give kit to a subject
                    if(args.length > 3) { 
                        if(!player.hasPermission(permission+".other")) {
                            return false;
                        }
                        Player recipient = Canary.getServer().matchPlayer(args[3]);
                        if(recipient != null) {
                            Kit kit = Canary.kits().getKit(args[2]);
                            if(kit != null) {
                                if(kit.giveKit(recipient)) {
                                    recipient.sendMessage(Colors.Yellow+"Enjoy this kit "+player.getName()+" gave you.");
                                    return true;
                                }
                                else {
                                    return passMessage(player, "This kit is currently not available for "+recipient.getName());
                                }
                            }
                            else {
                                return passMessage(player, args[2]+" is not a kit.");
                            }
                        }
                        else {
                            return passMessage(player, args[3] + " not found on the server.");
                        }
                    }
                }
                
                //
                // CREATE KITS
                //
                if(args[1].equalsIgnoreCase("create")) {
                    if(args.length < 4) {
                        player.notify("Usage: /kit create <name> <use delay> [G/P] [Groups/players to use this kit] - Create a kit from your current Inventory");
                        return true;
                    }
                    //Default public kit
                    if(args.length == 4) {
                        Kit newKit = new Kit();
                        newKit.setContent(new ArrayList<Item>(Arrays.asList(player.getInventory().getContents())));
                        newKit.setDelay(Integer.parseInt(args[3]));
                        newKit.setName(args[2]);
                        Canary.kits().addKit(newKit);
                        player.sendMessage(Colors.Yellow+args[2]+" has been created.");
                        return true;
                    }
                    
                    if(args.length >= 6) {
                        //ADD GROUPS KIT
                        if(args[4].equalsIgnoreCase("G")  && player.hasPermission(permission+".group")) {
                            String[] groups = new String[args.length - 5];
                            for(int i = 0; i < groups.length; i++) { 
                                Group g = Canary.usersAndGroups().getGroup(args[i+5]);
                                if(g != null) {
                                    groups[i] = g.name;
                                }
                                else {
                                    groups[i] = Canary.usersAndGroups().getDefaultGroup().name;
                                }
                            }
                            Kit newKit = new Kit();
                            newKit.setContent(new ArrayList<Item>(Arrays.asList(player.getInventory().getContents())));
                            newKit.setDelay(Integer.parseInt(args[3]));
                            newKit.setName(args[2]);
                            newKit.setGroups(groups);
                            Canary.kits().addKit(newKit);
                            player.sendMessage(Colors.Yellow+"Group Kit "+args[2]+" has been created.");
                            return true;
                        }
                        //ADD PLAYER PRIVATE KIT
                        else if(args[4].equalsIgnoreCase("G")  && player.hasPermission(permission+".group")) {
                            String[] players = new String[args.length - 5];
                            for(int i = 0; i < players.length; i++) { 
                                players[i] = args[i+5];
                            }
                            Kit newKit = new Kit();
                            newKit.setContent(new ArrayList<Item>(Arrays.asList(player.getInventory().getContents())));
                            newKit.setDelay(Integer.parseInt(args[3]));
                            newKit.setName(args[2]);
                            newKit.setOwner(players);
                            Canary.kits().addKit(newKit);
                            player.sendMessage(Colors.Yellow+"Private Kit "+args[2]+" has been created.");
                            return true;
                        }
                        else {
                            player.notify("Usage: /kit create <name> <use delay> [G/P] [Groups/players to use this kit] - Create a kit from your current Inventory");
                            return true;
                        }
                    }
                }
                player.notify("Usage: /kit create <name> <use delay> [G/P] [Groups/players to use this kit] - Create a kit from your current Inventory");
                return true;
            }
            else {
                player.notify("Usage: /kit give <name> [player]- Give kit with given name, optionally to a player");
                player.notify("Usage: /kit create <name> <use delay> [G/P] [Groups/players to use this kit] - Create a kit from your current Inventory");
                return true;
            }
        }
    },
    
    LISTPLUGINS ("canary.command.plugin.list", "List plugins"){
        @Override
        public boolean execute(Player player, String[] args) {
            if(player == null) {
                String list = Canary.loader().getReadablePluginList();
                Logman.logInfo("**** PLUGINS ****");
                if(list != null) {
                    Logman.logInfo(list);
                }
                else {
                    Logman.logInfo("No plugins loaded.");
                }
                return true;
            }
            
            if(!player.hasPermission(permission)){
                return false;
            }
            
            String list = Canary.loader().getReadablePluginList();
            if(list != null) {
                return passMessage(player, Colors.Yellow + "Plugins: " + Colors.White + list);
            }
            else {
                return passMessage(player, "Failed to get plugin list");
            }
        }
    },

    LISTWARPS ("canary.command.listwarps", "List warps") {
        @Override
        public boolean execute(Player player, String[] args) {
            if(player == null) {
                List<Warp> warps = Canary.warps().getAllWarps();
                StringBuilder warpList = new StringBuilder();
                for(Warp warp : warps) {
                    warpList.append(warp.getName()).append(",");
                }
                Logman.logInfo("**** WARPS ****");
                if(warpList.length() > 0) {
                    Logman.logInfo(warpList.toString());
                }
                else {
                    Logman.logInfo("No warps loaded.");
                }
                return true;
            }
            if(!player.hasPermission(permission)){
                return false;
            }
            List<Warp> warps = Canary.warps().getAllWarps();
            StringBuilder warpList = new StringBuilder();
            for(Warp w : warps) {
                if(w.getOwner() != null) {
                    if(w.isPlayerHome() && w.getOwner().equals(player.getName())) {
                        warpList.append(Colors.LightGreen).append("Your Home").append(Colors.White).append(",");
                    }
                    else if(!w.isPlayerHome() && w.getOwner().equals(player.getName()) || (player.isAdmin() || player.hasPermission("canary.command.warp.admin"))) {
                        warpList.append(Colors.Gold).append(w.getName()).append("(private)").append(Colors.White).append(",");
                    }
                }
                else if(w.isGroupRestricted() && w.isGroupAllowed(player.getGroup())) {
                    warpList.append(Colors.Yellow).append(w.getName()).append("(group)").append(Colors.White).append(",");
                }
                else if(!w.isGroupRestricted()) {
                    warpList.append(w.getName()).append(",");
                }
            }
            player.sendMessage(Colors.Yellow+"Available Warps: ");
            if(warpList.length() > 0) {
                player.sendMessage(warpList.toString());
            }
            else {
                player.sendMessage("No warps loaded");
            }
            return true;
        }
    },

    MODE ("canary.command.mode", "Usage: /mode <mode id> [player]") {
        @Override
        public boolean execute(Player player, String[] args) {
            if(player == null) {
                if(args.length < 3) {
                    Logman.logInfo("Usage: mode <mode id> <player>");
                    return true;
                }
                else {
                    int mode = Integer.parseInt(args[1]);
                    Player receiver = Canary.getServer().matchPlayer(args[2]);
                    if(receiver != null) {
                        receiver.setMode(mode);
                        Logman.logInfo("Mode set for "+receiver.getName());
                        return true;
                    }
                    else {
                        Logman.logInfo("Could not find"+args[2]);
                        return true;
                    }
                }
            }
            
            if(!player.hasPermission(permission)){
                return false;
            }
            if(args.length < 2 && player != null){
                return passMessage(player, getToolTip());
            }
            int mode = Integer.parseInt(args[1]);
            if(args.length == 3) {
                Player receiver = Canary.getServer().matchPlayer(args[2]);
                if(receiver == null) {
                    return passMessage(player, "Player "+args[2]+" does not exist!");
                }
                receiver.setMode(mode);
            }
            else {
                player.setMode(mode);
            }
            return true;
        }
    },
    
    MOTD ("canary.command.motd", "") {
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            return passMessage(player, "Command not yet implemented...");
        }
    },

    MUTE ("canary.command.mute", "Mute a player") {
        @Override
        public boolean execute(Player player, String[] args) {
            if(player == null) {
                if(args.length < 2) {
                    Logman.logInfo("Usage: mute <player name>");
                    return true;
                }
                Player toMute = Canary.getServer().matchPlayer(args[1]);
                if(toMute != null) {
                    if(!toMute.isMuted()) {
                        toMute.setMuted(true);
                        Logman.logInfo("Muted "+toMute.getName());
                        return true;
                    }
                    else {
                        toMute.setMuted(false);
                        Logman.logInfo("Unmuted "+toMute.getName());
                        return true;
                    }
                }
                else {
                    Logman.logInfo("Can not mute/unmute "+args[1]+" - not found on the server.");
                    return true;
                }
            }
            // --- Player
            
            if(!player.hasPermission(permission)){
                return false;
            }
            if(args.length < 2) {
                return passMessage(player, "Usage: /mute <player name>");
            }
            Player toMute = Canary.getServer().matchPlayer(args[1]);
            if(toMute == null) {
                return passMessage(player, "Player "+args[1]+" not found.");
            }
            if(player.getGroup().hasControlOver(toMute.getGroup())) {
                if(toMute.isMuted()) {
                    toMute.setMuted(false);
                    player.sendMessage(Colors.Yellow+"Unmuted "+toMute.getName());
                }
                else {
                    toMute.setMuted(true);
                    player.sendMessage(Colors.Yellow+"Muted "+toMute.getName());
                }
            }
            return true;
        }
    },

    PLAYERLIST ("canary.command.playerlist", ""){
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            passMessage(player, "Command not yet implemented...");
            return true;
        }
    },

    SPAWN ("canary.command.spawn", "") {
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            if(args.length == 1 && player == null){
                Logman.logInfo("Correct Usage: 'spawn <player>'");
                return true;
            }
            if(args.length > 1){
                if(player != null && !player.hasPermission(permission+"other")){
                    return false;
                }
                Player thePlayer = Canary.getServer().matchPlayer(args[1]);
                if(thePlayer != null){
                    thePlayer.teleportTo(thePlayer.getDimension().getSpawnLocation());
                    return true;
                }
                else{
                    return passMessage(player, "Player: '"+args[1]+"' not found.");
                }
            }
            else{
                player.teleportTo(player.getDimension().getSpawnLocation());
                return true;
            }
        }
    },

    SETHOME ("canary.command.sethome", ""){
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && player.hasPermission(permission)){
                player.setHome(player.getLocation());
                player.sendMessage(Colors.Yellow+"Home set.");
                return true;
            }
            else if(player == null){
                Logman.logInfo("Command not supported from the console.");
                return true;
            }
            return false;
        }
    },

    SETSPAWN ("canary.command.setspawn", ""){
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && player.hasPermission(permission)){
                player.getDimension().setSpawnLocation(player.getLocation());
                player.sendMessage(Colors.Yellow+"Spawn set.");
                return true;
            }
            else if(player == null){
                Logman.logInfo("Command not supported from the console.");
                return true;
            }
            return false;
        }
    },

    SETWARP ("canary.command.setwarp", "") {
        @Override
        public boolean execute(Player player, String[] args) {
            if(player == null) {
                Logman.logInfo("Command not supported from the console.");
                return true;
            }
            if(!player.hasPermission(permission)){
                return false;
            }
            if(args.length == 1) {
                return passMessage(player, "Usage: setwarp <warp name> [G/P] [Group or owner name]");
            }
            
            Warp test = Canary.warps().getWarp(args[1]);
            
            if(test != null) {
                if(test.isPlayerHome() || !player.hasPermission(permission+".admin")) {
                    return passMessage(player, "Could not set the warp!");
                }
            }
            //SET PUBLIC WARP
            if(args.length == 2 && player.hasPermission(permission+".public")) {
                Warp newWarp = new Warp(player.getLocation(), args[1]);
                Canary.warps().addWarp(newWarp);
                player.sendMessage(Colors.Yellow+"Warp "+args[1]+" has been set.");
                return true;
            }

            else if(args.length > 3) {
                //SET GROUP SPECIFIC WARP
                if(args[2].equalsIgnoreCase("G") && player.hasPermission(permission+".group")) {
                    Group[] groups = new Group[args.length - 3];
                    for(int i = 0; i < groups.length; i++) { 
                        groups[i] = Canary.usersAndGroups().getGroup(args[i+3]);
                    }
                    Warp newWarp = new Warp(player.getLocation(), groups, args[1]);
                    Canary.warps().addWarp(newWarp);
                    player.sendMessage(Colors.Yellow+"Groupwarp "+args[1]+" has been set.");
                    return true;
                }
                //SET PRIVATE WARP
                if(args[2].equalsIgnoreCase("P") && player.hasPermission(permission+".private")) {
                    Warp newWarp = new Warp(player.getLocation(), args[1], args[3], false);
                    Canary.warps().addWarp(newWarp);
                    player.sendMessage(Colors.Yellow+"Private warp "+args[1]+" has been set.");
                    return true;
                }
                else {
                    return passMessage(player, "Usage: setwarp <warp name> [G/P] [Group or owner name]");
                }
            }
            else {
                return passMessage(player, "Usage: setwarp <warp name> [G/P] [Group or owner name]");
            }
        }
    },

    TELL ("canary.command.tell", "") {
        @Override
        public boolean execute(Player player, String[] args) {
            if(player == null) {
                if(args.length < 3 ) {
                    Logman.logInfo("Usage: tell <player name> <message>");
                    return true;
                }
                String message = Canary.glueString(args, 2, " ");
                Player receiver = Canary.getServer().matchPlayer(args[1]);
                if(receiver != null) {
                    receiver.sendMessage("(MSG)"+Colors.Purple+"<CONSOLE> "+message);
                    return true;
                }
                else {
                    Logman.logInfo("Player "+args[1]+" not found!");
                    return true;
                }
            }
            
            if(!player.hasPermission(permission)){
                return false;
            }
            if(args.length < 3 ) {
                player.notify("Usage: tell <player name> <message>");
                return true;
            }
            String message = Canary.glueString(args, 2, " ");
            Player receiver = Canary.getServer().matchPlayer(args[1]);
            if(receiver != null) {
                receiver.sendMessage("(MSG)<"+player.getColor()+player.getName()+Colors.White+"> "+message);
                player.sendMessage(Colors.LightGray+"To "+receiver.getName()+": "+Colors.White+message);
                return true;
            }
            else {
                player.notify("Player "+args[1]+" not found!");
                return true;
            }
        }
    },
    //Alias to TELL
    MSG ("canary.command.tell", "") {

        @Override
        public boolean execute(Player player, String[] args) {
            return TELL.execute(player, args);
        }
        
    },

    TP ("canary.command.tp", ""){
        @Override
        public boolean execute(Player player, String[] args) {
            if(player == null) {
                Logman.logInfo("Command not supported from the console.");
                return true;
            }
            if(!player.hasPermission(permission)){
                return false;
            }
            if(args.length == 1) {
                return passMessage(player, "Usage: /tp <player to teleport to>");
            }
            Player target = Canary.getServer().matchPlayer(args[1]);
            if(target != null) {
                player.teleportTo(target.getLocation());
                player.sendMessage(Colors.Yellow+"Teleported to "+target.getName());
                return true;
            }
            
            return passMessage(player, Colors.Rose+args[1]+" not found.");
        }
    },

    TPHERE ("canary.command.tphere", ""){
        @Override
        public boolean execute(Player player, String[] args) {
            if(player == null) {
                Logman.logInfo("Command not supported from the console.");
                return true;
            }
            if(!player.hasPermission(permission)){
                return false;
            }
            if(args.length == 1) {
                return passMessage(player, "Usage: /tphere <player to teleport to you>");
            }
            Player target = Canary.getServer().matchPlayer(args[1]);
            if(target != null) {
                target.teleportTo(player.getLocation());
                player.sendMessage(Colors.Yellow+"You teleported "+target.getName()+" to you.");
                return true;
            }
            return passMessage(player, Colors.Rose+args[1]+" not found.");
        }
    },

    PLUGIN ("canary.command.plugin", ""){
        @Override
        public boolean execute(Player player, String[] args) {
            if(args.length < 2){
                if(!(player != null && !player.hasPermission("canary.command.plugin"))) {
                    return passMessage(player, "Correct Usage: 'plugin <reload(-r)|list(-l)|enable(-e)|disable(-d)> <pluginname>'");
                }
                return false;
            }
            if(args[1].toLowerCase().matches("reload|\\-r")){
                if(args.length < 3){
                    if(!(player != null && !player.hasPermission("canary.command.plugin.reload"))) {
                        return passMessage(player, Colors.Rose + "Correct Usage: /plugin reload <pluginname>");
                    }
                    return false;
                }
                return CanaryCommands.RELOADPLUGIN.execute(player, new String[]{ null, args[2] });
            }
            else if(args[1].toLowerCase().matches("list|\\-l")){
                return CanaryCommands.LISTPLUGINS.execute(player, args);
            }
            else if(args[1].toLowerCase().matches("enable|\\-e")){
                if(args.length < 3) {
                    if(!(player != null && !player.hasPermission("canary.command.plugin.enable"))){
                        return passMessage(player, Colors.Rose + "Not enought parameters to '/plugin enable <pluginname>'");
                    }
                    return false;
                }
                return CanaryCommands.ENABLEPLUGIN.execute(player, new String[]{ null, args[2] });
            }
            else if(args[1].toLowerCase().matches("disable|\\-d")){
                if(args.length < 3){
                    if(!(player != null && !player.hasPermission("canary.command.plugin.disable"))) {
                        return passMessage(player, Colors.Rose + "Not enought parameters to '/plugin disable <pluginname>'");
                    }
                    return false;
                }
                return CanaryCommands.DISABLEPLUGIN.execute(player, new String[]{ null, args[2] });
            }
            else if(!(player != null && !player.hasPermission("canary.command.plugin.help"))) {
                // TODO: Write this
                return false;
            }
            else {
                if(!(player != null && !player.hasPermission("canary.command.plugin"))) {
                    return passMessage(player, Colors.Rose + "Correct Usage: /plugin <reload(-r)|list(-l)|enable(-e)|disable(-d)> <pluginname>");
                }
                return false;
            }
        }
    },

    RELOADPLUGIN ("canary.command.plugin.reload", ""){
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            
            if(args.length == 1){
                return passMessage(player, Colors.Rose + "Correct Usage: 'reloadplugin <pluginname>'");
            }
            else if(Canary.loader().reloadPlugin(args[1]) == false) {
                return passMessage(player, Colors.Rose + "Unable to reload plugin '"+args[1]+"'");
            }
            else {
                return passMessage(player, Colors.Green + "Reloaded Plugin: '" + args[1] +  "'");
            }
        }
    },
    
    WARP ("canary.command.warp", "") {
        @Override
        public boolean execute(Player player, String[] args) {
            if(player == null) {
                Logman.logInfo("Command not supported from the console.");
                return true;
            }
            if(!player.hasPermission(permission)){
                return false;
            }
            if(args.length == 1) {
                return passMessage(player, "Usage: /warp <warp name>");
            }
            Warp target = Canary.warps().getWarp(args[1]);
            if(target != null) {
                if(target.warp(player)) {
                    return passMessage(player, "Warped to "+target.getName());
                }
                else {
                    return passMessage(player, "You are not allowed to warp to "+target.getName());
                }
            }
            return passMessage(player, Colors.Rose+"Warp "+args[1]+" not found.");
        }
    },
    
    TIME ("canary.command.time", "Set or get the time") {

        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && player.hasPermission(permission)) {
                if(args.length == 1) {
                    player.notify("Usage: /time 'day'|'night'|'check'|'relative time (0 to 24000)'");
                    return true;
                }
                Dimension dim = player.getDimension();
                if(args[1].equalsIgnoreCase("check")) {
                    player.sendMessage(Colors.Yellow+"The time: " + dim.getRelativeTime() + Colors.LightGray + " (RAW: " + dim.getRawTime() + ")");
                    return true;
                }
                else if(args[1].equalsIgnoreCase("day")) {
                    dim.setTime(0L);
                    player.sendMessage(Colors.Yellow+"The time has been set. Good morning!");
                    return true;
                }
                else if(args[1].equalsIgnoreCase("night")) {
                    dim.setTime(13000L);
                    player.sendMessage(Colors.Yellow+"The time has been set. Carpe noctem.");
                    return true;
                }
                else if(args[1].matches("\\d+")) {
                    dim.setTime(Long.parseLong(args[1]));
                    player.sendMessage(Colors.Yellow+"The time has been set.");
                    return true;
                }
                else {
                    return passMessage(player, "Usage: /time 'day' | 'night' | 'check' | 'relative time (0 to 24000)'");
                }
            }
            if(player == null) {
                return passMessage(null, "Command not supported from the console.");
            }
            return false;
        }
        
    },
    
    CREATEVANILLA("canary.command.createvanilla", "[world]") {
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            CanaryToVanilla converter = new CanaryToVanilla();
            String world = Configuration.getServerConfig().getDefaultWorldName();
            if(args.length > 1) {
                world = args[1];
            }
            
            if(converter.convert(world) == false) {
                passMessage(player, Colors.Rose + "Failed to convert to vanilla.");
            }
            else {
                passMessage(player, Colors.Yellow + "Succeed to convert to vanilla; result is in the vanilla/ folder.");
            }
            
            return true;
        }
    };

    public final String tooltip;
    public final String permission;

    private CanaryCommands(String permission, String tooltip){
        this.tooltip = tooltip;
        this.permission = permission;
    }

    /**
     * Get the command descriptions used in the help
     * @return
     */
    public String getToolTip(){
        return tooltip;
    }

    /**
     * gets the permission associtated with this command
     * @return permission
     */
    public String getPermission(){
        return permission;
    }
    
    /**
     * Pass the given message to the sender: either the player or the console
     * @param player
     * @param text
     * @return true on success, false on failure
     */
    private static boolean passMessage(Player player, String text) {
        if(player != null){
            player.notify(text);
        }
        else{
            Logman.logInfo(TextFormat.removeFormatting(text));
        }
        return true;
    }

    /**
     * Abstract command executor method. Overridden per command.
     * @param player
     *              if player is null it will be seen as console executing command
     * @param args
     * @return true if parsed, false if denied
     */
    public abstract boolean execute(Player player, String[] args);
    
    /**
     * Returns the command according to the name given or null,
     * if the command wasn't found
     * @param name
     * @return command if found, null otherwise
     */
    public static CanaryCommands fromString(String name) {
        for(CanaryCommands cmd : CanaryCommands.values()) {
            if(cmd.name().equals(name.toUpperCase())) {
                return cmd;
            }
        }
        return null;
    }
}
