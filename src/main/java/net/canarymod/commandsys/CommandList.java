package net.canarymod.commandsys;


import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.commands.BanCommand;
import net.canarymod.commandsys.commands.Compass;
import net.canarymod.commandsys.commands.CreateVanilla;
import net.canarymod.commandsys.commands.EmoteChat;
import net.canarymod.commandsys.commands.GetPosition;
import net.canarymod.commandsys.commands.Give;
import net.canarymod.commandsys.commands.GroupCommand;
import net.canarymod.commandsys.commands.HelpCommand;
import net.canarymod.commandsys.commands.Home;
import net.canarymod.commandsys.commands.IpBanCommand;
import net.canarymod.commandsys.commands.Kick;
import net.canarymod.commandsys.commands.Kill;
import net.canarymod.commandsys.commands.KitCommand;
import net.canarymod.commandsys.commands.ListPlugins;
import net.canarymod.commandsys.commands.ListWarps;
import net.canarymod.commandsys.commands.MobspawnCommand;
import net.canarymod.commandsys.commands.Mode;
import net.canarymod.commandsys.commands.Modify;
import net.canarymod.commandsys.commands.Motd;
import net.canarymod.commandsys.commands.Mute;
import net.canarymod.commandsys.commands.PlayerList;
import net.canarymod.commandsys.commands.PluginCommand;
import net.canarymod.commandsys.commands.PrivateMessage;
import net.canarymod.commandsys.commands.ReloadCommand;
import net.canarymod.commandsys.commands.SetHome;
import net.canarymod.commandsys.commands.SetSpawn;
import net.canarymod.commandsys.commands.SetWarp;
import net.canarymod.commandsys.commands.SpawnCommand;
import net.canarymod.commandsys.commands.StopServer;
import net.canarymod.commandsys.commands.TeleportCommand;
import net.canarymod.commandsys.commands.TeleportHereCommand;
import net.canarymod.commandsys.commands.TimeCommand;
import net.canarymod.commandsys.commands.WarpCommand;
import net.canarymod.commandsys.commands.WeatherCommand;
import net.canarymod.commandsys.commands.WhitelistCommand;

/**
 * Canary "native" commands
 * @author chris
 *
 */
public class CommandList implements CommandListener {
    @Command(aliases = { "ban" },
            description = "ban info",
            permissions = { "canary.super.ban", "canary.command.super.ban" },
            toolTip = "/ban <player> [reason] [#number hour|day|week|month]",
            min = 2)
    public void banCommand(MessageReceiver caller, String[] parameters) {
        new BanCommand().execute(caller, parameters);
    }

    @Command(aliases = { "compass" },
            description = "compass info",
            permissions = { "canary.command.player.compass" },
            toolTip = "/compass",
            min = 1)
    public void compassCommand(MessageReceiver caller, String[] parameters) {
        new Compass().execute(caller, parameters);
    }

    @Command(aliases = { "createvanilla", "makevanilla" },
            description = "makevanilla info",
            permissions = { "canary.super.createvanilla", "canary.command.super.createvanilla" },
            toolTip = "/createvanilla <defaultworld>",
            min = 2)
    public void createVanillaCommand(MessageReceiver caller, String[] parameters) {
        new CreateVanilla().execute(caller, parameters);
    }

    @Command(aliases = { "emote", "me" },
            description = "emote info",
            permissions = { "canary.command.player.emote" },
            toolTip = "/me <message>",
            min = 2)
    public void emoteCommand(MessageReceiver caller, String[] parameters) {
        new EmoteChat().execute(caller, parameters);
    }

    @Command(aliases = { "pos", "getpos" },
            description = "getpos info",
            permissions = { "canary.command.player.getpos" },
            toolTip = "/getpos")
    public void getPosCommand(MessageReceiver caller, String[] parameters) {
        new GetPosition().execute(caller, parameters);
    }

    @Command(aliases = { "give", "i" },
            description = "give info",
            permissions = { "canary.command.player.give" },
            toolTip = "/give <item>:[data] [amount] [player]",
            min = 2,
            max = 4)
    public void giveCommand(MessageReceiver caller, String[] parameters) {
        new Give().execute(caller, parameters);
    }

    @Command(aliases = { "group" },
            description = "group info",
            permissions = { "canary.command.super.group" },
            toolTip = "/group <create|delete|rename|list> <name> [parent|new name]",
            min = 2,
            max = 4)
    public void groupCommand(MessageReceiver caller, String[] parameters) {
        new GroupCommand().execute(caller, parameters);
    }


    @Command(aliases = { "help" },
            description = "help info",
            permissions = { "canary.command.help" },
            toolTip = "/help [search terms] [page]",
            min = 1)
    public void helpCommand(MessageReceiver caller, String[] parameters) {
        new HelpCommand().execute(caller, parameters);
    }

    @Command(aliases = { "home" },
            description = "home info",
            permissions = { "canary.command.teleport.home" },
            toolTip = "/home [playername]",
            min = 1,
            max = 2)
    public void homeCommand(MessageReceiver caller, String[] parameters) {
        new Home().execute(caller, parameters);
    }

    @Command(aliases = { "ipban" },
            description = "ipban info",
            permissions = { "canary.super.ipban", "canary.command.super.ipban" },
            toolTip = "/ipban <player> [reason] [#number hour|day|week|month]",
            min = 2)
    public void ipBanCommand(MessageReceiver caller, String[] parameters) {
        new IpBanCommand().execute(caller, parameters);
    }

    @Command(aliases = { "kick" },
            description = "kick info",
            permissions = { "canary.super.kick", "canary.command.super.kick" },
            toolTip = "/kick <playername> [reason]",
            min = 2)
    public void kickCommand(MessageReceiver caller, String[] parameters) {
        new Kick().execute(caller, parameters);
    }


    @Command(aliases = { "kill", "murder" },
            description = "kill info",
            permissions = { "canary.command.player.kill" },
            toolTip = "/kill [playername]",
            min = 2)
    public void killCommand(MessageReceiver caller, String[] parameters) {
        new Kill().execute(caller, parameters);
    }
    @Command(aliases = { "kit" },
            description = "kit info",
            permissions = { "canary.command.player.kit" },
            toolTip = "/kit <give|create> <name> <use delay> [G|P Groups|Players]",
            min = 4)
    public void kitCommand(MessageReceiver caller, String[] parameters) {
        new KitCommand().execute(caller, parameters);
    }

    @Command(aliases = { "listplugins", "plugins" },
            description = "lplugin info",
            permissions = { "canary.command.plugin.list" },
            toolTip = "/listplugins")
    public void listPluginsCommand(MessageReceiver caller, String[] parameters) {
        new ListPlugins().execute(caller, parameters);
    }

    @Command(aliases = { "listwarps", "warps" },
            description = "lwarps info",
            permissions = { "canary.command.warp.list" },
            toolTip = "/listwarps")
    public void listWarpsCommand(MessageReceiver caller, String[] parameters) {
        new ListWarps().execute(caller, parameters);
    }
    @Command(aliases = { "mobspawn", "mspawn" },
            description = "mobspawn info",
            permissions = { "canary.command.player.mobspawn" },
            toolTip = "/mobspawn <mobname> [rider] [amount]",
            min = 2,
            max = 4)
    public void mobSpawnCommand(MessageReceiver caller, String[] parameters) {
        new MobspawnCommand().execute(caller, parameters);
    }

    @Command(aliases = { "mode", "gm" },
            description = "mode info",
            permissions = { "canary.command.player.mode" },
            toolTip = "/mode <mode id> [playername] (0 = normal, 1 = creative, 2 = adventure)",
            min = 2,
            max = 3)
    public void gameModeCommand(MessageReceiver caller, String[] parameters) {
        new Mode().execute(caller, parameters);
    }

    @Command(aliases = { "modify", "mod" },
            description = "mode info",
            permissions = { "canary.super.modify", "canary.command.super.modify" },
            toolTip = "/modify <group|player> <name> <set|remove> <key> <value>",
            min = 6)
    public void modifyCommand(MessageReceiver caller, String[] parameters) {
        new Modify().execute(caller, parameters);
    }

    @Command(aliases = { "motd" },
            description = "motd info",
            permissions = { "canary.command.motd" },
            toolTip = "/modify <group|player> <name> <set|remove> <key> <value>",
            min = 6)
    public void motdCommand(MessageReceiver caller, String[] parameters) {
        new Motd().execute(caller, parameters);
    }

    @Command(aliases = { "msg", "tell" },
            description = "msg info",
            permissions = { "canary.command.player.msg" },
            toolTip = "/msg <playername> <message>",
            min = 3)
    public void msgCommand(MessageReceiver caller, String[] parameters) {
        new PrivateMessage().execute(caller, parameters);
    }

    @Command(aliases = { "mute", "stfu" },
            description = "mute info",
            permissions = { "canary.super.mute", "canary.command.super.mute" },
            toolTip = "/mute <playername>",
            min = 2)
    public void muteCommand(MessageReceiver caller, String[] parameters) {
        new Mute().execute(caller, parameters);
    }

    @Command(aliases = { "playerlist", "players", "who" },
            description = "who info",
            permissions = { "canary.command.player.list" },
            toolTip = "/who")
    public void playerListCommand(MessageReceiver caller, String[] parameters) {
        new PlayerList().execute(caller, parameters);
    }
    @Command(aliases = { "enableplugin" },
            description = "plugin enable info",
            permissions = { "canary.command.plugin.enable" },
            toolTip = "/enableplugin <plugin>",
            min = 2)
    public void enablePluginCommand(MessageReceiver caller, String[] parameters) {
        new PluginCommand(false, false).execute(caller, parameters);
    }

    @Command(aliases = { "disableplugin" },
            description = "plugin disable info",
            permissions = { "canary.command.plugin.disable" },
            toolTip = "/disableplugin <plugin>",
            min = 2)
    public void disablePluginCommand(MessageReceiver caller, String[] parameters) {
        new PluginCommand(true, false).execute(caller, parameters);
    }

    @Command(aliases = { "reloadplugin" },
            description = "plugin reload info",
            permissions = { "canary.command.plugin.reload" },
            toolTip = "/reloadplugin <plugin>",
            min = 2)
    public void reloadPluginCommand(MessageReceiver caller, String[] parameters) {
        new PluginCommand(false, true).execute(caller, parameters);
    }

    @Command(aliases = { "reload" },
            description = "reload info",
            permissions = { "canary.super.reload", "canary.command.super.reload" },
            toolTip = "/reload")
    public void reloadCommand(MessageReceiver caller, String[] parameters) {
        new ReloadCommand().execute(caller, parameters);
    }

    @Command(aliases = { "sethome" },
            description = "sethome info",
            permissions = { "canary.command.teleport.sethome" },
            toolTip = "/sethome")
    public void setHomeCommand(MessageReceiver caller, String[] parameters) {
        new SetHome().execute(caller, parameters);
    }
    @Command(aliases = { "setspawn" },
            description = "setspawn info",
            permissions = { "canary.super.setspawn", "canary.command.super.setspawn" },
            toolTip = "/setspawn")
    public void setSpawnCommand(MessageReceiver caller, String[] parameters) {
        new SetSpawn().execute(caller, parameters);
    }
    @Command(aliases = { "setwarp" },
            description = "setwarp info",
            permissions = { "canary.command.warp.set" },
            toolTip = "/setwarp <name> [G <group>|P <player>]",
            min = 2)
    public void setWarpCommand(MessageReceiver caller, String[] parameters) {
        new SetWarp().execute(caller, parameters);
    }

    @Command(aliases = { "spawn" },
            description = "spawn info",
            permissions = { "canary.command.teleport.spawn", "canary.command.super.setspawn" },
            toolTip = "/spawn [worldname] [player]",
            min = 1,
            max = 3)
    public void spawnCommand(MessageReceiver caller, String[] parameters) {
        new SpawnCommand().execute(caller, parameters);
    }

    @Command(aliases = { "stop", "shutdown" },
            description = "stop info",
            permissions = { "*" },
            toolTip = "/stop")
    public void stopCommand(MessageReceiver caller, String[] parameters) {
        new StopServer().execute(caller, parameters);
    }

    @Command(aliases = { "time", "thetime" },
            description = "time info",
            permissions = { "canary.command.time" },
            toolTip = "/time 'day'|'night'|'check'|'relative time (0 to 24000)'",
            min = 2,
            max = 3)
    public void timeCommand(MessageReceiver caller, String[] parameters) {
        new TimeCommand().execute(caller, parameters);
    }

    @Command(aliases = { "tp", "teleport" },
            description = "tp info",
            permissions = { "canary.command.teleport.self" },
            toolTip = "/tp <player>",
            min = 2)
    public void teleportCommand(MessageReceiver caller, String[] parameters) {
        new TeleportCommand().execute(caller, parameters);
    }
    @Command(aliases = { "tphere", "teleporthere" },
            description = "tphere info",
            permissions = { "canary.command.teleport.other" },
            toolTip = "/tphere <player>",
            min = 2)
    public void teleportOtherCommand(MessageReceiver caller, String[] parameters) {
        new TeleportHereCommand().execute(caller, parameters);
    }
    @Command(aliases = { "warp" },
            description = "warp info",
            permissions = { "canary.command.warp.use" },
            toolTip = "/warp <name>",
            min = 2)
    public void warpCommand(MessageReceiver caller, String[] parameters) {
        new WarpCommand().execute(caller, parameters);
    }

    @Command(aliases = { "weather" },
            description = "weather info",
            permissions = { "canary.command.weather" },
            toolTip = "/weather 'check'|'clear'|'rain'|'thunder'",
            min = 2,
            max = 3)
    public void weatherCommand(MessageReceiver caller, String[] parameters) {
        new WeatherCommand().execute(caller, parameters);
    }
    @Command(aliases = { "whitelist", "wlist", "wl" },
            description = "whitelist info",
            permissions = { "canary.command.weather" },
            toolTip = "/whitelist <add|remove> <playername>",
            min = 3)
    public void whitelistCommand(MessageReceiver caller, String[] parameters) {
        new WhitelistCommand().execute(caller, parameters);
    }
}
