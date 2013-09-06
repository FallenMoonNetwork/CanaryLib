package net.canarymod.commandsys;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.canarymod.Canary;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.commands.BanCommand;
import net.canarymod.commandsys.commands.CanaryModCommand;
import net.canarymod.commandsys.commands.ClearInventoryCommand;
import net.canarymod.commandsys.commands.Compass;
import net.canarymod.commandsys.commands.CreateVanilla;
import net.canarymod.commandsys.commands.EmoteChat;
import net.canarymod.commandsys.commands.GetPosition;
import net.canarymod.commandsys.commands.Give;
import net.canarymod.commandsys.commands.GodCommand;
import net.canarymod.commandsys.commands.HelpCommand;
import net.canarymod.commandsys.commands.Home;
import net.canarymod.commandsys.commands.IpBanCommand;
import net.canarymod.commandsys.commands.Kick;
import net.canarymod.commandsys.commands.Kill;
import net.canarymod.commandsys.commands.KitCommand;
import net.canarymod.commandsys.commands.ListPlugins;
import net.canarymod.commandsys.commands.MobspawnCommand;
import net.canarymod.commandsys.commands.Mode;
import net.canarymod.commandsys.commands.Motd;
import net.canarymod.commandsys.commands.Mute;
import net.canarymod.commandsys.commands.PlayerInformation;
import net.canarymod.commandsys.commands.PlayerList;
import net.canarymod.commandsys.commands.PluginCommand;
import net.canarymod.commandsys.commands.PrivateMessage;
import net.canarymod.commandsys.commands.ReloadCommand;
import net.canarymod.commandsys.commands.ReservelistCommand;
import net.canarymod.commandsys.commands.SetHome;
import net.canarymod.commandsys.commands.SetSpawn;
import net.canarymod.commandsys.commands.SpawnCommand;
import net.canarymod.commandsys.commands.StopServer;
import net.canarymod.commandsys.commands.SystemInformation;
import net.canarymod.commandsys.commands.TeleportCommand;
import net.canarymod.commandsys.commands.TeleportHereCommand;
import net.canarymod.commandsys.commands.TimeCommand;
import net.canarymod.commandsys.commands.UnbanCommand;
import net.canarymod.commandsys.commands.Uptime;
import net.canarymod.commandsys.commands.WeatherCommand;
import net.canarymod.commandsys.commands.WhitelistCommand;
import net.canarymod.commandsys.commands.group.GroupBase;
import net.canarymod.commandsys.commands.group.GroupCheck;
import net.canarymod.commandsys.commands.group.GroupCreate;
import net.canarymod.commandsys.commands.group.GroupList;
import net.canarymod.commandsys.commands.group.GroupPermissionAdd;
import net.canarymod.commandsys.commands.group.GroupPermissionCheck;
import net.canarymod.commandsys.commands.group.GroupPermissionFlush;
import net.canarymod.commandsys.commands.group.GroupPermissionList;
import net.canarymod.commandsys.commands.group.GroupPermissionRemove;
import net.canarymod.commandsys.commands.group.GroupPrefix;
import net.canarymod.commandsys.commands.group.GroupRemove;
import net.canarymod.commandsys.commands.group.GroupRename;
import net.canarymod.commandsys.commands.playermod.PlayerCreate;
import net.canarymod.commandsys.commands.playermod.PlayerGroupAdd;
import net.canarymod.commandsys.commands.playermod.PlayerGroupCheck;
import net.canarymod.commandsys.commands.playermod.PlayerGroupList;
import net.canarymod.commandsys.commands.playermod.PlayerGroupRemove;
import net.canarymod.commandsys.commands.playermod.PlayerGroupSet;
import net.canarymod.commandsys.commands.playermod.PlayerPermissionAdd;
import net.canarymod.commandsys.commands.playermod.PlayerPermissionCheck;
import net.canarymod.commandsys.commands.playermod.PlayerPermissionList;
import net.canarymod.commandsys.commands.playermod.PlayerPermissionRemove;
import net.canarymod.commandsys.commands.playermod.PlayerPrefix;
import net.canarymod.commandsys.commands.playermod.PlayerRemove;
import net.canarymod.commandsys.commands.playermod.PlayermodBase;
import net.canarymod.commandsys.commands.warp.WarpList;
import net.canarymod.commandsys.commands.warp.WarpRemove;
import net.canarymod.commandsys.commands.warp.WarpSet;
import net.canarymod.commandsys.commands.warp.WarpUse;

/**
 * Canary "native" commands
 *
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 * @author Aaron (somners)
 */
public class CommandList implements CommandListener {
    private final static Map<String, NativeCommand> natives;

    static {
        HashMap<String, NativeCommand> temp = new HashMap<String, NativeCommand>();
        temp.put("ban", new BanCommand());
        temp.put("unban", new UnbanCommand());
        temp.put("compass", new Compass());
        temp.put("createvanilla", new CreateVanilla());
        temp.put("emote", new EmoteChat());
        temp.put("pos", new GetPosition());
        temp.put("give", new Give());
        temp.put("groupmod_base", new GroupBase());
        temp.put("groupmod_add", new GroupCreate());
        temp.put("groupmod_perms_add", new GroupPermissionAdd());
        temp.put("groupmod_perms_remove", new GroupPermissionRemove());
        temp.put("groupmod_perms_check", new GroupPermissionCheck());
        temp.put("groupmod_perms_list", new GroupPermissionList());
        temp.put("groupmod_perms_flush", new GroupPermissionFlush());
        temp.put("groupmod_list", new GroupList());
        temp.put("groupmod_remove", new GroupRemove());
        temp.put("groupmod_check", new GroupCheck());
        temp.put("groupmod_rename", new GroupRename());
        temp.put("groupmod_prefix", new GroupPrefix());
        temp.put("playermod", new PlayermodBase());
        temp.put("playermod_add", new PlayerCreate());
        temp.put("playermod_perms_add", new PlayerPermissionAdd());
        temp.put("playermod_perms_remove", new PlayerPermissionRemove());
        temp.put("playermod_perms_check", new PlayerPermissionCheck());
        temp.put("playermod_perms_list", new PlayerPermissionList());
        temp.put("playermod_prefix", new PlayerPrefix());
        temp.put("playermod_remove", new PlayerRemove());
        temp.put("playermod_group_set", new PlayerGroupSet());
        temp.put("playermod_group_add", new PlayerGroupAdd());
        temp.put("playermod_group_list", new PlayerGroupList());
        temp.put("playermod_group_check", new PlayerGroupCheck());
        temp.put("playermod_group_remove", new PlayerGroupRemove());
        temp.put("help", new HelpCommand());
        temp.put("home", new Home());
        temp.put("ipban", new IpBanCommand());
        temp.put("kick", new Kick());
        temp.put("kill", new Kill());
        temp.put("kit", new KitCommand());
        temp.put("listplugins", new ListPlugins());
        temp.put("mobspawn", new MobspawnCommand());
        temp.put("mode", new Mode());
        temp.put("motd", new Motd());
        temp.put("msg", new PrivateMessage());
        temp.put("mute", new Mute());
        temp.put("playerlist", new PlayerList());
        temp.put("enableplugin", new PluginCommand(false, false));
        temp.put("disableplugin", new PluginCommand(true, false));
        temp.put("reloadplugin", new PluginCommand(false, true));
        temp.put("reload", new ReloadCommand());
        temp.put("sethome", new SetHome());
        temp.put("setspawn", new SetSpawn());
        temp.put("warp", new WarpUse());
        temp.put("setwarp", new WarpSet());
        temp.put("listwarps", new WarpList());
        temp.put("delwarp", new WarpRemove());
        temp.put("spawn", new SpawnCommand());
        temp.put("stop", new StopServer());
        temp.put("time", new TimeCommand());
        temp.put("teleport", new TeleportCommand());
        temp.put("teleporthere", new TeleportHereCommand());
        temp.put("weather", new WeatherCommand());
        temp.put("whitelist", new WhitelistCommand());
        temp.put("god", new GodCommand());
        temp.put("reservelist", new ReservelistCommand());
        temp.put("clearinventory", new ClearInventoryCommand());
        temp.put("canarymod", new CanaryModCommand());
        temp.put("playerinfo", new PlayerInformation());
        temp.put("sysinfo", new SystemInformation());
        temp.put("uptime", new Uptime());
        natives = Collections.unmodifiableMap(temp);
    }

    @Command(aliases = { "ban" },
            description = "ban info",
            permissions = { "canary.super.ban", "canary.command.super.ban" },
            toolTip = "/ban <player> [reason] [#number hour|day|week|month]",
            min = 2)
    public void banCommand(MessageReceiver caller, String[] parameters) {
        natives.get("ban").execute(caller, parameters);
    }

    @Command(aliases = { "unban" },
            description = "unban info",
            permissions = { "canary.super.unban", "canary.command.super.unban" },
            toolTip = "/unban <player>",
            min = 2)
    public void unbanCommand(MessageReceiver caller, String[] parameters) {
        natives.get("unban").execute(caller, parameters);
    }

    @Command(aliases = { "compass" },
            description = "compass info",
            permissions = { "canary.command.player.compass" },
            toolTip = "/compass",
            min = 1)
    public void compassCommand(MessageReceiver caller, String[] parameters) {
        natives.get("compass").execute(caller, parameters);
    }

    @Command(aliases = { "createvanilla", "makevanilla" },
            description = "makevanilla info",
            permissions = { "canary.super.createvanilla", "canary.command.super.createvanilla" },
            toolTip = "/createvanilla <defaultworld>",
            min = 2)
    public void createVanillaCommand(MessageReceiver caller, String[] parameters) {
        natives.get("createvanilla").execute(caller, parameters);
    }

    @Command(aliases = { "me" },
            description = "emote info",
            permissions = { "canary.command.player.emote", "canary.command.player.me" },
            toolTip = "/me <message>",
            min = 2)
    public void emoteCommand(MessageReceiver caller, String[] parameters) {
        natives.get("emote").execute(caller, parameters);
    }

    @Command(aliases = { "pos", "getpos" },
            description = "getpos info",
            permissions = { "canary.command.player.getpos" },
            toolTip = "/getpos")
    public void getPosCommand(MessageReceiver caller, String[] parameters) {
        natives.get("pos").execute(caller, parameters);
    }

    @Command(aliases = { "give", "i" },
            description = "give info",
            permissions = { "canary.command.player.give" },
            toolTip = "/give <item>:[data] [amount] [player]",
            min = 2,
            max = 4)
    public void giveCommand(MessageReceiver caller, String[] parameters) {
        natives.get("give").execute(caller, parameters);
    }

    // XXX groupmod start
    @Command(aliases = { "groupmod", "group" },
            description = "groupmod info",
            permissions = { "canary.command.super.groupmod" },
            toolTip = "/groupmod <add|delete|rename|permission|list> [parameters...] [--help]",
            min = 1)
    public void groupBase(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_base").execute(caller, parameters);
    }

    @Command(aliases = { "add", "create" },
            parent = "groupmod",
            helpLookup = "groupmod add",
            description = "group add info",
            permissions = { "canary.command.super.groupmod.add" },
            toolTip = "/groupmod add <name> [[parent] [world[:dimension]]]",
            min = 2,
            max = 4)
    public void groupAdd(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_add").execute(caller, parameters);
    }

    @Command(aliases = { "permission", "perms" },
            parent = "groupmod",
            helpLookup = "groupmod permission",
            description = "group permission info",
            permissions = { "canary.command.super.groupmod.permissions" },
            toolTip = "/groupmod permission <add|remove|check|list> [arguments...] [--help]",
            min = 2)
    public void groupPerms(MessageReceiver caller, String[] parameters) {
        Canary.help().getHelp(caller, "groupmod permission");
    }

    @Command(aliases = { "add" },
            parent = "groupmod.permission",
            helpLookup = "groupmod permission add",
            description = "groupmod permission add info",
            permissions = { "canary.command.super.groupmod.permissions" },
            toolTip = "/groupmod permission add <group> <path>:[value]",
            min = 3)
    public void groupPermissionsAdd(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_perms_add").execute(caller, parameters);
    }

    @Command(aliases = { "remove" },
            parent = "groupmod.permission",
            helpLookup = "groupmod permission remove",
            description = "groupmod permission remove info",
            permissions = { "canary.command.super.groupmod.permissions" },
            toolTip = "/groupmod permission remove <group> <path>:[value]",
            min = 3)
    public void groupPermissionsRemove(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_perms_remove").execute(caller, parameters);
    }

    @Command(aliases = { "check" },
            parent = "groupmod.permission",
            helpLookup = "groupmod permission check",
            description = "groupmod permission check info",
            permissions = { "canary.command.super.groupmod.permissions" },
            toolTip = "/groupmod permission check <group> <path>:[value]",
            min = 3)
    public void groupPermissionsCheck(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_perms_check").execute(caller, parameters);
    }

    @Command(aliases = { "list" },
            parent = "groupmod.permission",
            helpLookup = "groupmod permission list",
            description = "groupmod permission list info",
            permissions = { "canary.command.super.groupmod.permissions" },
            toolTip = "/groupmod permission list <group>",
            min = 2)
    public void groupPermissionsList(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_perms_list").execute(caller, parameters);
    }

    @Command(aliases = { "flush" },
            parent = "groupmod.permission",
            helpLookup = "groupmod permission flush",
            description = "group permissionflush info",
            permissions = { "canary.command.super.groupmod.flush" },
            toolTip = "/groupmod permission flush <group>",
            min = 2)
    public void groupFlush(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_perms_flush").execute(caller, parameters);
    }

    @Command(aliases = { "list", "show" },
            parent = "groupmod",
            helpLookup = "groupmod list",
            description = "group list info",
            permissions = { "canary.command.super.groupmod.list" },
            toolTip = "/groupmod list")
    public void groupList(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_list").execute(caller, parameters);
    }

    @Command(aliases = { "delete", "remove" },
            parent = "groupmod",
            helpLookup = "groupmod remove",
            description = "group remove info",
            permissions = { "canary.command.super.groupmod.delete" },
            toolTip = "/groupmod remove <name>",
            min = 2)
    public void groupRemove(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_remove").execute(caller, parameters);
    }

    @Command(aliases = { "check", "show" },
            parent = "groupmod",
            helpLookup = "groupmod check",
            description = "group check info",
            permissions = { "canary.command.super.groupmod.check" },
            toolTip = "/groupmod check <name>",
            min = 2)
    public void groupCheck(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_check").execute(caller, parameters);
    }

    @Command(aliases = { "rename" },
            parent = "groupmod",
            helpLookup = "groupmod rename",
            description = "group rename info",
            permissions = { "canary.command.super.groupmod.rename" },
            toolTip = "/groupmod rename <group> <newname>",
            min = 3)
    public void groupRename(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_rename").execute(caller, parameters);
    }

    @Command(aliases = { "prefix" },
            parent = "groupmod",
            helpLookup = "groupmod prefix",
            description = "group prefix info",
            permissions = { "canary.command.super.groupmod.prefix" },
            toolTip = "/groupmod prefix <group> <prefix>",
            min = 2)
    public void groupPrefix(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_prefix").execute(caller, parameters);
    }

    // groupmod end

    // XXX PLAYER Start
    @Command(aliases = { "playermod", "player" },
            description = "playermod info",
            permissions = { "canary.command.super.playermod" },
            toolTip = "/playermod <add|remove|prefix|permission|group> [parameters...] [--help]")
    public void playerBase(MessageReceiver caller, String[] parameters) {
        natives.get("playermod").execute(caller, parameters);
    }

    @Command(aliases = { "add", "create" },
            parent = "playermod",
            helpLookup = "playermod add",
            description = "playermod add info",
            permissions = { "canary.command.super.playermod.add" },
            toolTip = "/playermod add <name> <group>",
            min = 3)
    public void playerAdd(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_add").execute(caller, parameters);
    }

    @Command(aliases = { "permission", "perms" },
            parent = "playermod",
            helpLookup = "playermod permission",
            description = "playermod permission info",
            permissions = { "canary.command.super.playermod.permissions" },
            toolTip = "/playermod permission <add|remove|check|list> [arguments...] [--help]", // <player> <path>:[value] <add|remove|check|list>
            min = 1)
    public void playerPermissions(MessageReceiver caller, String[] parameters) {
        Canary.help().getHelp(caller, "playermod permission");
    }

    @Command(aliases = { "add" },
            parent = "playermod.permission",
            helpLookup = "playermod permission add",
            description = "playermod permission add info",
            permissions = { "canary.command.super.playermod.permissions" },
            toolTip = "/playermod permission add <player> <path>:[value]",
            min = 3)
    public void playerPermissionsAdd(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_perms_add").execute(caller, parameters);
    }

    @Command(aliases = { "remove" },
            parent = "playermod.permission",
            helpLookup = "playermod permission remove",
            description = "playermod permission remove info",
            permissions = { "canary.command.super.playermod.permissions" },
            toolTip = "/playermod permission remove <player> <path>",
            min = 3)
    public void playerPermissionsRemove(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_perms_remove").execute(caller, parameters);
    }

    @Command(aliases = { "check" },
            parent = "playermod.permission",
            helpLookup = "playermod permission check",
            description = "playermod permission check info",
            permissions = { "canary.command.super.playermod.permissions" },
            toolTip = "/playermod permission check <player> <path>",
            min = 3)
    public void playerPermissionsCheck(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_perms_check").execute(caller, parameters);
    }

    @Command(aliases = { "list" },
            parent = "playermod.permission",
            helpLookup = "playermod permission list",
            description = "playermod permission list info",
            permissions = { "canary.command.super.playermod.permissions" },
            toolTip = "/playermod permission list <player>",
            min = 2)
    public void playerPermissionsList(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_perms_list").execute(caller, parameters);
    }

    @Command(aliases = { "prefix", "color" },
            parent = "playermod",
            helpLookup = "playermod prefix",
            description = "playermod prefix info",
            permissions = { "canary.command.super.playermod.prefix" },
            toolTip = "/playermod prefix <name> <prefix>",
            min = 2)
    public void playerPrefix(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_prefix").execute(caller, parameters);
    }

    @Command(aliases = { "remove", "delete" },
            parent = "playermod",
            helpLookup = "playermod remove",
            description = "playermod remove info",
            permissions = { "canary.command.super.playermod.remove" },
            toolTip = "/playermod remove <name>",
            min = 2)
    public void playerRemove(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_remove").execute(caller, parameters);
    }

    @Command(aliases = { "group" },
            parent = "playermod",
            helpLookup = "playermod group",
            description = "playermod group info",
            permissions = { "canary.command.super.playermod.group" },
            toolTip = "/playermod group <list|check|set|add> [arguments...] [--help]",
            min = 1)
    public void playerGroup(MessageReceiver caller, String[] parameters) {
        Canary.help().getHelp(caller, "playermod group");
    }

    @Command(aliases = { "set" },
            parent = "playermod.group",
            helpLookup = "playermod group set",
            description = "playermod group set info",
            permissions = { "canary.command.super.playermod.group.set" },
            toolTip = "/playermod group set <player> <group> [--help]",
            min = 2)
    public void playerGroupSet(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_group_set").execute(caller, parameters);
    }

    @Command(aliases = { "add" },
            parent = "playermod.group",
            helpLookup = "playermod group add",
            description = "playermod group add info",
            permissions = { "canary.command.super.playermod.group.add" },
            toolTip = "/playermod group add <player> <group> [--help]",
            min = 2)
    public void playerGroupAdd(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_group_add").execute(caller, parameters);
    }

    @Command(aliases = { "list" },
            parent = "playermod.group",
            helpLookup = "playermod group list",
            description = "playermod group list info",
            permissions = { "canary.command.super.playermod.group.list" },
            toolTip = "/playermod group list <player> [--help]",
            min = 2)
    public void playerGroupList(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_group_list").execute(caller, parameters);
    }

    @Command(aliases = { "check" },
            parent = "playermod.group",
            helpLookup = "playermod group check",
            description = "playermod group check info",
            permissions = { "canary.command.super.playermod.group.check" },
            toolTip = "/playermod group check <player> <group> [--help]",
            min = 3)
    public void playerGroupCheck(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_group_check").execute(caller, parameters);
    }

    @Command(aliases = { "remove" },
            parent = "playermod.group",
            helpLookup = "playermod group remove",
            description = "playermod group remove info",
            permissions = { "canary.command.super.playermod.group.remove" },
            toolTip = "/playermod group remove <player> <group> [--help]",
            min = 3)
    public void playerGroupRemove(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_group_remove").execute(caller, parameters);
    }

    // playermod end

    @Command(aliases = { "help" },
            description = "help info",
            permissions = { "canary.command.help" },
            toolTip = "/help [search terms] [page]",
            min = 1)
    public void helpCommand(MessageReceiver caller, String[] parameters) {
        natives.get("help").execute(caller, parameters);
    }

    @Command(aliases = { "home" },
            description = "home info",
            permissions = { "canary.command.teleport.home" },
            toolTip = "/home [playername]",
            min = 1,
            max = 2)
    public void homeCommand(MessageReceiver caller, String[] parameters) {
        natives.get("home").execute(caller, parameters);
    }

    @Command(aliases = { "ipban" },
            description = "ipban info",
            permissions = { "canary.super.ipban", "canary.command.super.ipban" },
            toolTip = "/ipban <player> [reason] [#number hour|day|week|month]",
            min = 2)
    public void ipBanCommand(MessageReceiver caller, String[] parameters) {
        natives.get("ipban").execute(caller, parameters);
    }

    @Command(aliases = { "kick" },
            description = "kick info",
            permissions = { "canary.super.kick", "canary.command.super.kick" },
            toolTip = "/kick <playername> [reason]",
            min = 2)
    public void kickCommand(MessageReceiver caller, String[] parameters) {
        natives.get("kick").execute(caller, parameters);
    }

    @Command(aliases = { "kill", "murder" },
            description = "kill info",
            permissions = { "canary.command.player.kill" },
            toolTip = "/kill [playername]",
            min = 1)
    public void killCommand(MessageReceiver caller, String[] parameters) {
        natives.get("kill").execute(caller, parameters);
    }

    @Command(aliases = { "kit" },
            description = "kit info",
            permissions = { "canary.command.player.kit" },
            toolTip = "/kit <give|create> <name> <use delay> [G|P Groups|Players]")
    public void kitCommand(MessageReceiver caller, String[] parameters) {
        natives.get("kit").execute(caller, parameters);
    }

    @Command(aliases = { "listplugins", "plugins" },
            description = "lplugin info",
            permissions = { "canary.command.plugin.list" },
            toolTip = "/listplugins")
    public void listPluginsCommand(MessageReceiver caller, String[] parameters) {
        natives.get("listplugins").execute(caller, parameters);
    }

    @Command(aliases = { "mobspawn", "mspawn", "spawnmob" },
            description = "mobspawn info",
            permissions = { "canary.command.player.mobspawn" },
            toolTip = "/mobspawn <mobname> [rider] [amount]",
            min = 2,
            max = 4)
    public void mobSpawnCommand(MessageReceiver caller, String[] parameters) {
        natives.get("mobspawn").execute(caller, parameters);
    }

    @Command(aliases = { "mode", "gm" },
            description = "mode info",
            permissions = { "canary.command.player.mode" },
            toolTip = "/mode <mode id> [playername] (0 = normal, 1 = creative, 2 = adventure)",
            min = 2,
            max = 3)
    public void gameModeCommand(MessageReceiver caller, String[] parameters) {
        natives.get("mode").execute(caller, parameters);
    }

    @Command(aliases = { "motd" },
            description = "motd info",
            permissions = { "canary.command.motd" },
            toolTip = "/motd",
            min = 1)
    public void motdCommand(MessageReceiver caller, String[] parameters) {
        natives.get("motd").execute(caller, parameters);
    }

    @Command(aliases = { "msg", "tell" },
            description = "msg info",
            permissions = { "canary.command.player.msg" },
            toolTip = "/msg <playername> <message>",
            min = 3)
    public void msgCommand(MessageReceiver caller, String[] parameters) {
        natives.get("msg").execute(caller, parameters);
    }

    @Command(aliases = { "mute", "stfu" },
            description = "mute info",
            permissions = { "canary.super.mute", "canary.command.super.mute" },
            toolTip = "/mute <playername>",
            min = 2)
    public void muteCommand(MessageReceiver caller, String[] parameters) {
        natives.get("mute").execute(caller, parameters);
    }

    @Command(aliases = { "playerlist", "players", "who" },
            description = "who info",
            permissions = { "canary.command.player.list" },
            toolTip = "/who")
    public void playerListCommand(MessageReceiver caller, String[] parameters) {
        natives.get("playerlist").execute(caller, parameters);
    }

    @Command(aliases = { "enableplugin" },
            description = "plugin enable info",
            permissions = { "canary.command.plugin.enable" },
            toolTip = "/enableplugin <plugin>",
            min = 2)
    public void enablePluginCommand(MessageReceiver caller, String[] parameters) {
        natives.get("enableplugin").execute(caller, parameters);
    }

    @Command(aliases = { "disableplugin" },
            description = "plugin disable info",
            permissions = { "canary.command.plugin.disable" },
            toolTip = "/disableplugin <plugin>",
            min = 2)
    public void disablePluginCommand(MessageReceiver caller, String[] parameters) {
        natives.get("disableplugin").execute(caller, parameters);
    }

    @Command(aliases = { "reloadplugin" },
            description = "plugin reload info",
            permissions = { "canary.command.plugin.reload" },
            toolTip = "/reloadplugin <plugin>",
            min = 2)
    public void reloadPluginCommand(MessageReceiver caller, String[] parameters) {
        natives.get("reloadplugin").execute(caller, parameters);
    }

    @Command(aliases = { "reload" },
            description = "reload info",
            permissions = { "canary.super.reload", "canary.command.super.reload" },
            toolTip = "/reload")
    public void reloadCommand(MessageReceiver caller, String[] parameters) {
        natives.get("reload").execute(caller, parameters);
    }

    @Command(aliases = { "sethome" },
            description = "sethome info",
            permissions = { "canary.command.teleport.sethome" },
            toolTip = "/sethome [player]",
            min = 1,
            max = 2)
    public void setHomeCommand(MessageReceiver caller, String[] parameters) {
        natives.get("sethome").execute(caller, parameters);
    }

    @Command(aliases = { "setspawn" },
            description = "setspawn info",
            permissions = { "canary.super.setspawn", "canary.command.super.setspawn" },
            toolTip = "/setspawn")
    public void setSpawnCommand(MessageReceiver caller, String[] parameters) {
        natives.get("setspawn").execute(caller, parameters);
    }

    @Command(aliases = { "warp" },
            description = "warp info",
            permissions = { "canary.command.warp.use" },
            toolTip = "/warp <name>",
            min = 2,
            max = 2)
    public void warpUse(MessageReceiver caller, String[] parameters) {
        natives.get("warp").execute(caller, parameters);
    }

    @Command(aliases = { "setwarp" },
            description = "setwarp info",
            permissions = { "canary.command.warp.set" },
            toolTip = "/setwarp <name> [G <group>|P <player>]",
            min = 2)
    public void setWarpCommand(MessageReceiver caller, String[] parameters) {
        natives.get("setwarp").execute(caller, parameters);
    }

    @Command(aliases = { "listwarps", "warps" },
            description = "lwarps info",
            permissions = { "canary.command.warp.list" },
            toolTip = "/listwarps")
    public void listWarpsCommand(MessageReceiver caller, String[] parameters) {
        natives.get("listwarps").execute(caller, parameters);
    }

    @Command(aliases = { "delwarp", "removewarp" },
            description = "delwarp info",
            permissions = { "canary.command.warp.remove" },
            toolTip = "/delwarp <name>",
            min = 2)
    public void delWarpCommand(MessageReceiver caller, String[] parameters) {
        natives.get("delwarp").execute(caller, parameters);
    }

    @Command(aliases = { "spawn" },
            description = "spawn info",
            permissions = { "canary.command.teleport.spawn" },
            toolTip = "/spawn [worldname] [player]",
            min = 1,
            max = 3)
    public void spawnCommand(MessageReceiver caller, String[] parameters) {
        natives.get("spawn").execute(caller, parameters);
    }

    @Command(aliases = { "stop", "shutdown" },
            description = "stop info",
            permissions = { "canary.super.command.stop" },
            toolTip = "/stop")
    public void stopCommand(MessageReceiver caller, String[] parameters) {
        natives.get("stop").execute(caller, parameters);
    }

    @Command(aliases = { "time", "thetime" },
            description = "time info",
            permissions = { "canary.command.time" },
            toolTip = "/time 'day'|'night'|'check'|'relative time (0 to 24000)'",
            min = 2,
            max = 3)
    public void timeCommand(MessageReceiver caller, String[] parameters) {
        natives.get("time").execute(caller, parameters);
    }

    @Command(aliases = { "tp", "teleport" },
            description = "tp info",
            permissions = { "canary.command.teleport.self" },
            toolTip = "/tp <player|x y z [world]> ",
            min = 2)
    public void teleportCommand(MessageReceiver caller, String[] parameters) {
        natives.get("teleport").execute(caller, parameters);
    }

    @Command(aliases = { "tphere", "teleporthere" },
            description = "tphere info",
            permissions = { "canary.command.teleport.other" },
            toolTip = "/tphere <player>",
            min = 2)
    public void teleportOtherCommand(MessageReceiver caller, String[] parameters) {
        natives.get("teleporthere").execute(caller, parameters);
    }

    @Command(aliases = { "weather" },
            description = "weather info",
            permissions = { "canary.command.weather" },
            toolTip = "/weather 'check'|'clear'|'rain'|'thunder'",
            min = 2,
            max = 3)
    public void weatherCommand(MessageReceiver caller, String[] parameters) {
        natives.get("weather").execute(caller, parameters);
    }

    @Command(aliases = { "whitelist", "wlist", "wl" },
            description = "whitelist info",
            permissions = { "canary.command.super.whitelist" },
            toolTip = "/whitelist <add|remove> <playername>",
            min = 3)
    public void whitelistCommand(MessageReceiver caller, String[] parameters) {
        natives.get("whitelist").execute(caller, parameters);
    }

    @Command(aliases = { "god", "godmode" },
            description = "enable god mode",
            permissions = { "canary.command.god", "canary.command.god.other" },
            toolTip = "/god <playername>",
            min = 1,
            max = 2)
    public void godCommand(MessageReceiver caller, String[] parameters) {
        natives.get("god").execute(caller, parameters);
    }

    @Command(aliases = { "reservelist", "rlist", "rl" },
            description = "reservelist info",
            permissions = { "canary.command.super.reservelist" },
            toolTip = "/reservelist <add|remove> <playername>",
            min = 3)
    public void reservelistCommand(MessageReceiver caller, String[] parameters) {
        natives.get("reservelist").execute(caller, parameters);
    }

    @Command(aliases = { "clearinventory", "clearinv" },
            description = "clearinventory info",
            permissions = { "canary.command.super.clearinventory" },
            toolTip = "/clearinventory [player]",
            min = 1,
            max = 2)
    public void clearInventoryCommand(MessageReceiver caller, String[] parameters) {
        natives.get("clearinventory").execute(caller, parameters);
    }

    @Command(aliases = { "canarymod", "version" },
            description = "CanaryMod Information",
            permissions = { "canary.command.canarymod" },
            toolTip = "/canarymod")
    public void canarymodInfoCommand(MessageReceiver caller, String[] parameters) {
        natives.get("canarymod").execute(caller, parameters);
    }

    @Command(aliases = { "playerinfo", "pinfo" },
            description = "Player Information",
            permissions = { "canary.command.player.information" },
            toolTip = "/playerinfo [player]")
    public void playerinfo(MessageReceiver caller, String[] parameters) {
        natives.get("playerinfo").execute(caller, parameters);
    }

    @Command(aliases = { "sysinfo" },
            description = "System Information",
            permissions = { "canary.command.sysinfo" },
            toolTip = "/sysinfo")
    public void sysinfo(MessageReceiver caller, String[] parameters) {
        natives.get("sysinfo").execute(caller, parameters);
    }

    @Command(aliases = { "uptime" },
            description = "server uptime",
            permissions = { "canary.command.uptime" },
            toolTip = "/uptime")
    public void uptime(MessageReceiver caller, String[] parameters) {
        natives.get("uptime").execute(caller, parameters);
    }
}
