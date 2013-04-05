package net.canarymod.commandsys.commands;


import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.bansystem.Ban;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;
import net.canarymod.hook.player.BanHook;
import net.visualillusionsent.utils.StringUtils;


public class IpBanCommand extends CanaryCommand {

    public IpBanCommand() {
        super("canary.super.ban", Translator.translate("ipban info"), Translator.translateAndFormat("usage", "/ipban <player> [reason] [#number hour|day|week|month]"), 2);
    }

    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server) {
            console(caller, parameters);
        } else if (caller instanceof Player) {
            player((Player) caller, parameters);
        } else {
            throw new CommandException(Translator.translateAndFormat("unknown messagereceiver", caller.getClass().getSimpleName()));
        }
    }

    private void console(MessageReceiver caller, String[] cmd) {
        if(cmd.length < 2) {
            caller.notice(Translator.translateAndFormat("usage", "/ban <player> [reason] [#number hour|day|week|month]"));
            return;
        }

        Player p = Canary.getServer().matchPlayer(cmd[1]);
        if(p == null) {
            caller.notice(Translator.translate("ban failed") + " " + Translator.translateAndFormat("unknown player", cmd[1]));
            return;
        }
        Ban ban = new Ban();
        String reason = "Permanently Banned";
        long timestamp = -1L;

        if(cmd.length >= 3) {
            try {
                timestamp = Canary.parseTime(Long.parseLong(cmd[cmd.length - 2]), cmd[cmd.length - 1]);
                reason = StringUtils.joinString(cmd, " ", 2, cmd.length - 2);
            }
            catch(NumberFormatException e) {
                reason = StringUtils.joinString(cmd, " ", 2);
                timestamp = -1L;
            }
        }
        ban.setReason(reason);
        ban.setTimestamp(timestamp);
        ban.setBanningPlayer(caller.getName());
        ban.setSubject(p.getName());
        ban.setIp(p.getIP());
        ban.setIsIpBan(true);
        Canary.bans().issueBan(ban);
        Canary.hooks().callHook(new BanHook(p, p.getIP(), null, reason, timestamp));
        caller.notice(Translator.translateAndFormat("ipban banned", cmd[1]));
        p.kick(reason);
    }

    private void player(Player caller, String[] cmd) {
        if(cmd.length < 2) {
            caller.notice(Translator.translateAndFormat("usage", "/ban <player> [reason] [#number hour|day|week|month]"));
            return;
        }

        Player p = Canary.getServer().matchPlayer(cmd[1]);
        if(p == null) {
            caller.notice(Translator.translate("ban failed") + " " + Translator.translateAndFormat("unknown player", cmd[1]));
            return;
        }
        Ban ban = new Ban();
        String reason = "Permanently Banned";
        long timestamp = -1L;

        if(cmd.length >= 3) {
            try {
                timestamp = Canary.parseTime(Long.parseLong(cmd[cmd.length - 2]), cmd[cmd.length - 1]);
                reason = StringUtils.joinString(cmd, " ", 2, cmd.length - 2);
            }
            catch(NumberFormatException e) {
                reason = StringUtils.joinString(cmd, " ", 2);
                timestamp = -1L;
            }
        }
        ban.setReason(reason);
        ban.setTimestamp(timestamp);
        ban.setBanningPlayer(caller.getName());
        ban.setSubject(p.getName());
        ban.setIp(p.getIP());
        ban.setIsIpBan(true);
        Canary.bans().issueBan(ban);
        Canary.hooks().callHook(new BanHook(p, p.getIP(), caller, reason, timestamp));
        caller.notice(Translator.translateAndFormat("ipban banned", cmd[1]));
        p.kick(reason);
    }

}
