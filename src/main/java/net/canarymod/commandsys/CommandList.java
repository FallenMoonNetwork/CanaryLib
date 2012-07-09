package net.canarymod.commandsys;

import net.canarymod.commandsys.commands.Compass;
import net.canarymod.commandsys.commands.CreateVanilla;
import net.canarymod.commandsys.commands.EmoteChat;
import net.canarymod.commandsys.commands.GetPosition;
import net.canarymod.commandsys.commands.HelpCommand;
import net.canarymod.commandsys.commands.Home;
import net.canarymod.commandsys.commands.Kill;
import net.canarymod.commandsys.commands.KitCommand;
import net.canarymod.commandsys.commands.ListPlugins;
import net.canarymod.commandsys.commands.ListWarps;
import net.canarymod.commandsys.commands.Mode;
import net.canarymod.commandsys.commands.Mute;
import net.canarymod.commandsys.commands.PlayerList;
import net.canarymod.commandsys.commands.PluginCommand;
import net.canarymod.commandsys.commands.PrivateMessage;
import net.canarymod.commandsys.commands.SetHome;
import net.canarymod.commandsys.commands.SetSpawn;
import net.canarymod.commandsys.commands.SetWarp;
import net.canarymod.commandsys.commands.SpawnCommand;
import net.canarymod.commandsys.commands.TeleportCommand;
import net.canarymod.commandsys.commands.TeleportHereCommand;
import net.canarymod.commandsys.commands.TimeCommand;
import net.canarymod.commandsys.commands.WarpCommand;

public class CommandList {
    @Command("compass")
    public final CanaryCommand compass = new Compass();
    
    @Command("createvanilla")
    public final CanaryCommand vanilla = new CreateVanilla();
    
    @Command({"emote", "me"})
    public final CanaryCommand me = new EmoteChat();
    
    @Command({"getpos", "pos"})
    public final CanaryCommand pos = new GetPosition();
    
    @Command({"help"})
    public final CanaryCommand help = new HelpCommand();
    
    @Command({"home"})
    public final CanaryCommand home = new Home();
    
    @Command({"kill", "murder"})
    public final CanaryCommand kill = new Kill();
    
    @Command({"kit"})
    public final CanaryCommand kit = new KitCommand();
    
    @Command({"listplugins", "plugins"})
    public final CanaryCommand listplugins = new ListPlugins();
    
    @Command({"listwarps", "warps"})
    public final CanaryCommand listwarps = new ListWarps();
    
    @Command({"mode"})
    public final CanaryCommand mode = new Mode();
    
    @Command({"mute"})
    public final CanaryCommand mute = new Mute();
    
    @Command({"playerlist", "players"})
    public final CanaryCommand playerlist = new PlayerList();
    
    @Command({"plugin", "enableplugin"})
    public final CanaryCommand plugin = new PluginCommand(false, false);
    
    @Command({"plugin", "disableplugin"})
    public final CanaryCommand plugin2 = new PluginCommand(true, false);
    
    @Command({"plugin", "reloadplugin"})
    public final CanaryCommand plugin3 = new PluginCommand(false, true);
    
    @Command({"tell", "msg"})
    public final CanaryCommand msg = new PrivateMessage();
    
    @Command({"sethome"})
    public final CanaryCommand sethome = new SetHome();
    
    @Command({"setspawn"})
    public final CanaryCommand setspawn = new SetSpawn();
    
    @Command({"setwarp"})
    public final CanaryCommand setwarp = new SetWarp();
    
    @Command({"spawn"})
    public final CanaryCommand spawn = new SpawnCommand();
    
    @Command({"tp", "teleport"})
    public final CanaryCommand tp = new TeleportCommand();
    
    @Command({"tphere", "teleporthere"})
    public final CanaryCommand tphere = new TeleportHereCommand();
    
    @Command({"time"})
    public final CanaryCommand time = new TimeCommand();
    
    @Command({"warp"})
    public final CanaryCommand warp = new WarpCommand();
}
