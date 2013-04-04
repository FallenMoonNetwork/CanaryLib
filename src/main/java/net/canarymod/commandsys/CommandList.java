package net.canarymod.commandsys;


import net.canarymod.commandsys.commands.BanCommand;
import net.canarymod.commandsys.commands.Compass;
import net.canarymod.commandsys.commands.CreateVanilla;
import net.canarymod.commandsys.commands.EmoteChat;
import net.canarymod.commandsys.commands.GetPosition;
import net.canarymod.commandsys.commands.HelpCommand;
import net.canarymod.commandsys.commands.Home;
import net.canarymod.commandsys.commands.IpBanCommand;
import net.canarymod.commandsys.commands.Kill;
import net.canarymod.commandsys.commands.KitCommand;
import net.canarymod.commandsys.commands.ListPlugins;
import net.canarymod.commandsys.commands.ListWarps;
import net.canarymod.commandsys.commands.MobspawnCommand;
import net.canarymod.commandsys.commands.Mode;
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


public class CommandList {
    @Command("compass")
    public static CanaryCommand compass = new Compass();

    @Command("createvanilla")
    public static CanaryCommand vanilla = new CreateVanilla();

    @Command({ "emote", "me"})
    public static CanaryCommand me = new EmoteChat();

    @Command({ "getpos", "pos"})
    public static CanaryCommand pos = new GetPosition();

    @Command({ "help"})
    public static CanaryCommand help = new HelpCommand();

    @Command({ "home"})
    public static CanaryCommand home = new Home();

    @Command({ "kill", "murder"})
    public static CanaryCommand kill = new Kill();

    @Command({ "kit"})
    public static CanaryCommand kit = new KitCommand();

    @Command({ "listplugins", "plugins"})
    public static CanaryCommand listplugins = new ListPlugins();

    @Command({ "listwarps", "warps"})
    public static CanaryCommand listwarps = new ListWarps();

    @Command({ "mode"})
    public static CanaryCommand mode = new Mode();

    @Command({ "mute"})
    public static CanaryCommand mute = new Mute();

    @Command({ "playerlist", "players"})
    public static CanaryCommand playerlist = new PlayerList();

    @Command({ "enableplugin"})
    public static CanaryCommand plugin = new PluginCommand(false, false);

    @Command({ "disableplugin"})
    public static CanaryCommand plugin2 = new PluginCommand(true, false);

    @Command({ "reloadplugin"})
    public static CanaryCommand plugin3 = new PluginCommand(false, true);

    @Command({ "plugin"})
    public static CanaryCommand plugin4 = new PluginCommand(false, true);

    @Command({ "tell", "msg"})
    public static CanaryCommand msg = new PrivateMessage();

    @Command({ "sethome"})
    public static CanaryCommand sethome = new SetHome();

    @Command({ "setspawn"})
    public static CanaryCommand setspawn = new SetSpawn();

    @Command({ "setwarp"})
    public static CanaryCommand setwarp = new SetWarp();

    @Command({ "spawn"})
    public static CanaryCommand spawn = new SpawnCommand();

    @Command({ "tp", "teleport"})
    public static CanaryCommand tp = new TeleportCommand();

    @Command({ "tphere", "teleporthere"})
    public static CanaryCommand tphere = new TeleportHereCommand();

    @Command({ "time"})
    public static CanaryCommand time = new TimeCommand();

    @Command({ "warp"})
    public static CanaryCommand warp = new WarpCommand();

    @Command({ "stop", "shutdown"})
    public static CanaryCommand stop = new StopServer();

    @Command({ "reload"})
    public static CanaryCommand reload = new ReloadCommand();

    @Command({ "ban"})
    public static CanaryCommand ban = new BanCommand();

    @Command({ "ipban"})
    public static CanaryCommand ipban = new IpBanCommand();

    @Command({ "weather"})
    public static CanaryCommand weather = new WeatherCommand();

    @Command({ "mobspawn", "mspawn"})
    public static CanaryCommand mobspawn = new MobspawnCommand();

    @Command({ "whitelist", "wl", "wlist"})
    public static CanaryCommand whitelist = new WhitelistCommand();
}
