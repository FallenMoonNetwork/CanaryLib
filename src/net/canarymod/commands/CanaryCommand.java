package net.canarymod.commands;

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
            if(player != null && !player.hasPermission(permission)){
               return false;
            }
            //Logic Here
            return false;
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
    
    WARP ("canary.command.warp", "") {
        @Override
        public boolean execute(Player player, String[] args) {
            if(player != null && !player.hasPermission(permission)){
                return false;
             }
             //Logic Here
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
    
    public abstract boolean execute(Player player, String[] args);
}
