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
                double degreeRotation = (player.getRotation() - 90) % 360;

                if (degreeRotation < 0) {
                    degreeRotation += 360.0;
                }
                
                player.sendMessage("Penises...");
            }
            else if(player == null){
                Logman.logInfo("Looking down from the great Minecraft Skies!");
            }
            return false;
        }
    },
    
    DISABLEPLUGIN ("canary.command.plugin.disable", ""){
        @Override
        public boolean execute(Player player, String[] args) {
            
            if(args == null){
                passMessage(player, Colors.Rose + "Correct Usage: 'disableplugin <pluginname>'");
                return true;
            }
            
            if(Canary.loader().disablePlugin(args[0])) {
                passMessage(player, Colors.Green + "Disabled plugin '" + args[0] +  "'");
            }
            else {
                passMessage(player, Colors.Rose + "Unable to disable plugin '"+args[0]+"'");
            }
            return true;
        }
    },
    
    EMOTE ("canary.command.emote", ""){
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            
            if (player != null && player.isMuted()) {
                player.notify("You are currently muted.");
                return true;
            }
                
            if (args == null) {
                passMessage(player, Colors.Rose+"Correct Usage: /emote <emotion>");
                return true;
            }
            
            String emote = "* " + (player != null ? player.getColor()+player.getName() : Colors.DarkPurple+"CONSOLE") + Colors.White + " " + Canary.glueString(args, 0, " ");
            Logman.logInfo(emote);
            Canary.getServer().broadcastMessage(emote);
            
            return true;
        }
    },

    ENABLEPLUGIN ("canary.command.plugin.enable", ""){
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            
            if(args == null){
                passMessage(player, Colors.Rose + "Correct Usage: 'enableplugin <pluginname>'");
                return true;
            }
            
            if(Canary.loader().enablePlugin(args[0])) {
                passMessage(player, Colors.Green + "Enabled Plugin: '" + args[0] +  "'");
            }
            else {
                passMessage(player, Colors.Rose + "Unable to enable plugin '"+args[0]+"'");
            }
            return true;
        }
    },

    GETPOS ("canary.command.getpos", "") {
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && player.hasPermission(permission)){
                player.sendMessage(Colors.Gold+"Pos X: "+Colors.LightGray+player.getX()+Colors.Gold+" Y: "+Colors.LightGray+ player.getY()+Colors.Gold+" Z: "+Colors.LightGray+player.getZ());
                player.sendMessage(Colors.Gold+"Rotation: "+Colors.LightGray+player.getRotation()+Colors.Gold+" Pitch: "+Colors.LightGray+player.getPitch());

                //double degreeRotation = ((p.getRotation() - 90) % 360);

                //if (degreeRotation < 0) {
                //    degreeRotation += 360.0;
                //}
                //player.sendMessage("Compass: " + Canary.getCompassPointForDirection(degreeRotation) + " (" + (Math.round(degreeRotation * 10) / 10.0) + ")");
                return true;
            }
            else if (player == null){
                Logman.logInfo("You are in the great Minecraft Skies!");
                return true;
            }
            return false;
        }
    },

    HELP ("canary.command.help", ""){
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
                player.sendMessage(Colors.Red + "Help-page not found");
                return true;
            }

            // Send all lines
            for(String l : lines) {
                player.sendMessage(l);
            }

            return true;
        }
    },

    HOME ("canary.command.home", "") {
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            //Logic Here
            return false;
        }
    },

    KILL ("canary.command.kill", "<player> - Kills a player"){
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            //Logic Here
            return false;
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
                passMessage(player, Colors.Yellow + "Plugins: " + Colors.White + list);
            }
            else {
                passMessage(player, Colors.Rose + "Failed to get plugin list");
            }
            return true;
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

    MODE ("canary.command.mode", "") {
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            //Logic Here
            return false;
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
            //Logic Here
            return false;
        }
    },

    SETHOME ("canary.command.sethome", ""){
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            //Logic Here
            return false;
        }
    },

    SETSPAWN ("canary.command.setspawn", ""){
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            //Logic Here
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
            return false;
        }
    },

    PLUGIN ("canary.command.plugin", ""){
        @Override
        public boolean execute(Player player, String[] args) {
            if(args[0].toLowerCase().matches("reload|\\-r")){
                if(args.length < 2 && !(player != null && !player.hasPermission("canary.command.plugin.reload"))) {
                    passMessage(player, Colors.Rose + "Correct Usage: /plugin reload <pluginname>");
                    return true;
                }
                return CanaryCommand.RELOADPLUGIN.execute(player, new String[]{ args[1] });
            }
            else if(args[0].toLowerCase().matches("list|\\-l")){
                return CanaryCommand.LISTPLUGINS.execute(player, args);
            }
            else if(args[0].toLowerCase().matches("enable|\\-e")){
                if(args.length < 2 && !(player != null && !player.hasPermission("canary.command.plugin.enable"))) {
                    passMessage(player, Colors.Rose + "Not enought parameters to '/plugin enable <pluginname>'");
                    return true;
                }
                return CanaryCommand.ENABLEPLUGIN.execute(player, new String[]{ args[1] });
            }
            else if((args[1].equalsIgnoreCase("disable") || args[1].equalsIgnoreCase("-d"))
                    && !(player != null && !player.hasPermission("canary.command.plugin.disable"))) {
                if(args.length < 3) {
                    passMessage(player, Colors.Rose + "Not enought parameters to '/plugin disable <pluginname>'");
                    return true;
                }
                return CanaryCommand.DISABLEPLUGIN.execute(player, new String[]{ args[1] });
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
                passMessage(player, Colors.Rose + "Correct Usage: 'reloadplugin <pluginname>'");
            }
            else if(Canary.loader().reloadPlugin(args[0]) == false) {
                passMessage(player, Colors.Rose + "Unable to reload plugin '"+args[0]+"'");
            }
            else {
                passMessage(player, Colors.Green + "Reloaded Plugin: '" + args[0] +  "'");
            }
            return true;
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

    public String getToolTip(){
        return tooltip;
    }

    public String getPermission(){
        return permission;
    }
    
    void passMessage(Player player, String text) {
        if(player != null){
            
        }
        else{
            Logman.logInfo(TextFormat.removeFormatting(text));
        }
    }

    public abstract boolean execute(Player player, String[] args);
    
    /**
     * Returns the command according to the name given or null,
     * if the command wasn't found
     * @param name
     * @return
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
