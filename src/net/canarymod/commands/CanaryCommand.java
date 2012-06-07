package net.canarymod.commands;

import net.canarymod.Canary;
import net.canarymod.Colors;
import net.canarymod.api.entity.Player;

public enum CanaryCommand {

    EMOTE ("canary.command.emote", ""){
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            //Logic Here
            return false;
        }
    },

    GETPOS ("canary.command.getpos", "") {
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && !player.hasPermission(permission)){
                return false;
            }
            //Logic Here
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
            if(player != null && !player.hasPermission(permission)){
                return false;
            }

            if((args[1].equalsIgnoreCase("reload") || args[1].equalsIgnoreCase("-r"))
                    && !(player != null && !player.hasPermission("canary.command.plugin.reload"))) {
                if(args.length < 3) {
                    passMessage(Colors.Rose + "Not enought parameters to '/plugin reload <pluginname>'");
                    return true;
                }
                
                if(Canary.loader().reloadPlugin(args[2]) == false) {
                    passMessage(Colors.Rose + "Unable to reload plugin '"+args[2]+"'");
                }
                else {
                    passMessage(Colors.Green + "Reloaded plugin '" + args[2] +  "'");
                }
            }
            else if((args[1].equalsIgnoreCase("list") || args[1].equalsIgnoreCase("-l"))
                    && !(player != null && !player.hasPermission("canary.command.plugin.list"))) {
                String str;
                if((str = Canary.loader().getReadablePluginList()) == null) {
                    passMessage(Colors.Rose + "Failed to enable plugin");
                }
                else {
                    passMessage(Colors.Yellow + "Plugins: " + Colors.White + str);
                }
            }
            else if((args[1].equalsIgnoreCase("enable") || args[1].equalsIgnoreCase("-e"))
                    && !(player != null && !player.hasPermission("canary.command.plugin.enable"))) {
                if(args.length < 3) {
                    passMessage(Colors.Rose + "Not enought parameters to '/plugin enable <pluginname>'");
                    return true;
                }
                
                if(Canary.loader().enablePlugin(args[2]) == false) {
                    passMessage(Colors.Rose + "Unable to enable plugin '"+args[2]+"'");
                }
                else {
                    passMessage(Colors.Green + "Enabled plugin '" + args[2] +  "'");
                }
            }
            else if((args[1].equalsIgnoreCase("disable") || args[1].equalsIgnoreCase("-d"))
                    && !(player != null && !player.hasPermission("canary.command.plugin.disable"))) {
                if(args.length < 3) {
                    passMessage(Colors.Rose + "Not enought parameters to '/plugin disable <pluginname>'");
                    return true;
                }
                
                if(Canary.loader().disablePlugin(args[2]) == false) {
                    passMessage(Colors.Rose + "Unable to disable plugin '"+args[2]+"'");
                }
                else {
                    passMessage(Colors.Green + "Disabled plugin '" + args[2] +  "'");
                }
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

    LISTPLUGINS ("canary.command.plugin.list", ""){
        @Override
        public boolean execute(Player player, String[] args) {
            return false;
        }
    },

    ENABLEPLUGIN ("canary.command.plugin.enable", ""){
        @Override
        public boolean execute(Player player, String[] args) {
            return false;
        }
    },
    

    DISABLEPLUGIN ("canary.command.plugin.disable", ""){
        @Override
        public boolean execute(Player player, String[] args) {
            return false;
        }
    },
    

    RELOADPLUGIN ("canary.command.plugin.reload", ""){
        @Override
        public boolean execute(Player player, String[] args) {
            return false;
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
    
    void passMessage(String text) {
        
    }

    public abstract boolean execute(Player player, String[] args);
}
