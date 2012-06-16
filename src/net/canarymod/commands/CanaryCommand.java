package net.canarymod.commands;

import net.canarymod.Canary;
import net.canarymod.Colors;
import net.canarymod.Logman;
import net.canarymod.TextFormat;
import net.canarymod.api.entity.Player;

public enum CanaryCommand {
    
    COMPASS ("canary.command.player.compass", ""){
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && player.hasPermission(permission)){
                double degrees =  ((player.getRotation() - 90) % 360);
                if (degrees < 0) {
                    degrees += 360.0;
                }
                
                player.sendMessage("Compass: " + player.getCardinalDirection().toString() + " (" + (Math.round(degrees * 10) / 10.0) + ")");
                return true;
            }
            else if(player == null){
                Logman.logInfo("Looking down from the great Minecraft Skies!");
            }
            return false;
        }
    },
    
    DISABLEPLUGIN ("canary.command.plugin.disable", "<pluginname>"){
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            
            if(args.length == 1){
                return passMessage(player, Colors.Rose + "Correct Usage: 'disableplugin <pluginname>'");
            }
            else if(Canary.loader().disablePlugin(args[1])) {
                return passMessage(player, Colors.Green + "Disabled plugin '" + args[1] +  "'");
            }
            else {
                return passMessage(player, Colors.Rose + "Unable to disable plugin '"+args[1]+"'");
            }
        }
    },
    
    EMOTE ("canary.command.emote", "<emotion>"){
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            
            if (player != null && player.isMuted()) {
                player.notify("You are currently muted.");
                return true;
            }
                
            if (args.length == 1) {
                return passMessage(player, Colors.Rose+"Correct Usage: /emote <emotion>");
            }
            
            String emote = "* " + (player != null ? player.getColor()+player.getName() : Colors.DarkPurple+"CONSOLE") + Colors.White + " " + Canary.glueString(args, 1, " ");
            Logman.logInfo(emote);
            Canary.getServer().broadcastMessage(emote);
            
            return true;
        }
    },

    ENABLEPLUGIN ("canary.command.plugin.enable", "<pluginname>"){
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            
            if(args.length == 1){
                return passMessage(player, Colors.Rose + "Correct Usage: 'enableplugin <pluginname>'");
            }
            else if(Canary.loader().enablePlugin(args[1])) {
                return passMessage(player, Colors.Green + "Enabled Plugin: '" + args[1] +  "'");
            }
            else {
                return passMessage(player, Colors.Rose + "Unable to enable plugin '"+args[1]+"'");
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
                
                player.sendMessage("Compass: " + player.getCardinalDirection().toString() + " (" + (Math.round(degrees * 10) / 10.0) + ")");
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
            if(player != null && !player.hasPermission(permission)) {
                return false;
            }

            int page = 0;
            if(args.length > 1) {
                page = Integer.valueOf(args[1])-1;
            }

            String[] lines = Canary.help().getHelp(player,page); 

            if(lines == null) {
                return passMessage(player, Colors.Red + "Help-page not found");
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
                if(args.length > 1 && player.hasPermission(permission+"other")){
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
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            if(args.length == 1){
                return passMessage(player, "Correct Usage: 'kill <player>'"); 
            }
            
            Player thePlayer = Canary.getServer().matchPlayer(args[1]);
            if(thePlayer != null){
                thePlayer.kill();
                Canary.getServer().broadcastMessage(Colors.Yellow+thePlayer.getName()+Colors.Rose+" was murdered by "+Colors.Yellow+(player != null ? player.getName() : "CONSOLE")+".");
                return true;
            }
            else{
                return passMessage(player, "Player: "+args[1]+" not found.");
            }
        }
    },

    KIT ("canary.command.kit", ""){
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            //Logic Here
            return false;
        }
    },
    
    LISTPLUGINS ("canary.command.plugin.list", ""){
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            
            String list = Canary.loader().getReadablePluginList();
            if(list != null) {
                return passMessage(player, Colors.Yellow + "Plugins: " + Colors.White + list);
            }
            else {
                return passMessage(player, Colors.Rose + "Failed to get plugin list");
            }
        }
    },

    LISTWARPS ("canary.command.listwarps", "") {
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            //Logic Here
            return false;
        }
    },

    MODE ("canary.command.mode", "Usage: /mode <mode id> [Playername]") {
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            if(args.length < 2) {
                player.notify(getToolTip());
                return true;
            }
            int mode = Integer.parseInt(args[1]);
            if(args.length == 3) {
                Player receiver = Canary.getServer().matchPlayer(args[2]);
                if(receiver == null) {
                    player.notify("Player "+args[2]+" does not exist!");
                    return true;
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
            // TODO Auto-generated method stub
            return false;
        }
    },

    MUTE ("canary.command.mute", "") {
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            //Logic Here
            return false;
        }
    },

    PLAYERLIST ("canary.command.playerlist", ""){
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            //Logic Here
            return false;
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
                player.setHome(player.getPosition());
                player.notify("Home set.");
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
                player.notify("Spawn set.");
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
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            //Logic Here
            return false;
        }
    },

    TELL ("canary.command.tell", "") {
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            //Logic Here
            return false;
        }
    },

    TP ("canary.command.tp", ""){
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            //Logic Here
            return false;
        }
    },

    TPHERE ("canary.command.tphere", ""){
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            //Logic Here
            return true;
        }
    },

    PLUGIN ("canary.command.plugin", ""){
        @Override
        public boolean execute(Player player, String[] args) {
            if(args[1].toLowerCase().matches("reload|\\-r")){
                if(args.length < 2){
                    if(!(player != null && !player.hasPermission("canary.command.plugin.reload"))) {
                        return passMessage(player, Colors.Rose + "Correct Usage: /plugin reload <pluginname>");
                    }
                    return false;
                }
                return CanaryCommand.RELOADPLUGIN.execute(player, new String[]{ null, args[1] });
            }
            else if(args[1].toLowerCase().matches("list|\\-l")){
                return CanaryCommand.LISTPLUGINS.execute(player, args);
            }
            else if(args[1].toLowerCase().matches("enable|\\-e")){
                if(args.length < 2) {
                    if(!(player != null && !player.hasPermission("canary.command.plugin.enable"))){
                        return passMessage(player, Colors.Rose + "Not enought parameters to '/plugin enable <pluginname>'");
                    }
                    return false;
                }
                return CanaryCommand.ENABLEPLUGIN.execute(player, new String[]{ null, args[1] });
            }
            else if(args[1].toLowerCase().matches("disable|\\-d")){
                if(args.length < 2){
                    if(!(player != null && !player.hasPermission("canary.command.plugin.disable"))) {
                        return passMessage(player, Colors.Rose + "Not enought parameters to '/plugin disable <pluginname>'");
                    }
                    return false;
                }
                return CanaryCommand.DISABLEPLUGIN.execute(player, new String[]{ null, args[1] });
            }
            else if(!(player != null && !player.hasPermission("canary.command.plugin.help"))) {
                // TODO: Write this shit
            }
            else {
                return false;
            }

            return true;
        }
    },

    RELOADPLUGIN ("canary.command.plugin.reload", ""){
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            
            if(args == null){
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
            return false;
        }
    };

    public final String tooltip;
    public final String permission;

    private CanaryCommand(String permission, String tooltip){
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
            player.sendMessage(text);
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
    public static CanaryCommand fromString(String name) {
        for(CanaryCommand cmd : CanaryCommand.values()) {
            if(cmd.name().equals(name.toUpperCase())) {
                return cmd;
            }
        }
        return null;
    }
}
