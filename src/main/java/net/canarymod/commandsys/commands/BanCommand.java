package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.bansystem.Ban;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CommandException;
import net.canarymod.hook.player.BanHook;
import net.visualillusionsent.utils.StringUtils;

public class BanCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server) {
            console(caller, parameters);
        } else if (caller instanceof Player) {
            player((Player) caller, parameters);
        } else {
            throw new CommandException(Translator.translateAndFormat("unknown messagereceiver", caller.getClass().getSimpleName()));
        }
    }

    private void console(MessageReceiver caller, String[] cmd) {
        if (cmd.length < 2) {
            Canary.help().getHelp(caller, "ban");
            return;
        }
        Ban ban = new Ban();
        Player p = Canary.getServer().matchPlayer(cmd[1]);
        String reason = "Permanently Banned";
        long timestamp = -1L;

        if (cmd.length >= 3) {
            try {
                timestamp = Canary.parseTime(Long.parseLong(cmd[cmd.length - 2]), cmd[cmd.length - 1]);
                reason = StringUtils.joinString(cmd, " ", 2, cmd.length - 2);
            } catch (NumberFormatException e) {
                reason = StringUtils.joinString(cmd, " ", 2);
                timestamp = -1L;
            }
        }
        ban.setReason(reason);
        ban.setTimestamp(timestamp);
        ban.setBanningPlayer(caller.getName());
        if (p != null) {
            ban.setSubject(p.getName());
            Canary.bans().issueBan(ban);
            Canary.hooks().callHook(new BanHook(p, p.getIP(), null, reason, timestamp));
            caller.notice(Translator.translateAndFormat("ban banned", p.getName()));
            p.kick(reason);
        }
        else {
            ban.setSubject(cmd[1]);
            Canary.bans().issueBan(ban);
            Canary.hooks().callHook(new BanHook(null, "xxx.xxx.xxx.xxx", null, reason, timestamp));
            caller.notice(Translator.translateAndFormat("ban banned", cmd[1]));
        }
    }

    private void player(Player caller, String[] cmd) {
        if (cmd.length < 2) {
            Canary.help().getHelp(caller, "ban");
            return;
        }
        Ban ban = new Ban();
        Player p = Canary.getServer().matchPlayer(cmd[1]);
        String reason = "Permanently Banned";
        long timestamp = -1L;

        if (cmd.length >= 3) {
            try {
                timestamp = Canary.parseTime(Long.parseLong(cmd[cmd.length - 2]), cmd[cmd.length - 1]);
                reason = StringUtils.joinString(cmd, " ", 2, cmd.length - 2);
            } catch (NumberFormatException e) {
                reason = StringUtils.joinString(cmd, " ", 2);
                timestamp = -1L;
            }
        }
        ban.setReason(reason);
        ban.setTimestamp(timestamp);
        ban.setBanningPlayer(caller.getName());
        if (p != null) {
            ban.setSubject(p.getName());
            Canary.bans().issueBan(ban);
            Canary.hooks().callHook(new BanHook(p, p.getIP(), caller, reason, timestamp));
            caller.notice(Translator.translateAndFormat("ban banned", p.getName()));
            p.kick(reason);
        }
        else {
            ban.setSubject(cmd[1]);
            Canary.bans().issueBan(ban);
            Canary.hooks().callHook(new BanHook(null, "xxx.xxx.xxx.xxx", caller, reason, timestamp));
            caller.notice(Translator.translateAndFormat("ban banned", cmd[1]));
        }
    }

}
