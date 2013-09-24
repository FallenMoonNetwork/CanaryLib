package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.ToolBox;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.bansystem.Ban;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.hook.player.BanHook;
import net.visualillusionsent.utils.StringUtils;

/**
 * Command to ban players by ip
 *
 * @author Chris (damagefilter)
 */
public class IpBanCommand implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Player) {
            player((Player) caller, parameters);
        }
        else {
            console(caller, parameters);
        }
    }

    private void console(MessageReceiver caller, String[] cmd) {
        if (cmd.length < 2) {
            Canary.help().getHelp(caller, "ipban");
            return;
        }

        Player p = Canary.getServer().matchPlayer(cmd[1]);
        if (p == null) {
            caller.notice(Translator.translate("ban failed") + " " + Translator.translateAndFormat("unknown player", cmd[1]));
            return;
        }
        Ban ban = new Ban();
        String reason = "Permanently Banned";
        long timestamp = -1L;

        if (cmd.length >= 3) {
            try {
                timestamp = ToolBox.parseTime(Long.parseLong(cmd[cmd.length - 2]), cmd[cmd.length - 1]);
                reason = StringUtils.joinString(cmd, " ", 2, cmd.length - 2);
            }
            catch (NumberFormatException e) {
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
        if (cmd.length < 2) {
            Canary.help().getHelp(caller, "ipban");
            return;
        }

        Player p = Canary.getServer().matchPlayer(cmd[1]);
        if (p == null) {
            caller.notice(Translator.translate("ban failed") + " " + Translator.translateAndFormat("unknown player", cmd[1]));
            return;
        }
        Ban ban = new Ban();
        String reason = "Permanently Banned";
        long timestamp = -1L;

        if (cmd.length >= 3) {
            try {
                timestamp = ToolBox.parseTime(Long.parseLong(cmd[cmd.length - 2]), cmd[cmd.length - 1]);
                reason = StringUtils.joinString(cmd, " ", 2, cmd.length - 2);
            }
            catch (NumberFormatException e) {
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
