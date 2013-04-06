package net.canarymod.commandsys.commands;


import java.util.Arrays;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;


public class HelpCommand extends CanaryCommand {

    public HelpCommand() {
        super("canary.command.help", Translator.translate("help info"), Translator.translateAndFormat("usage", "/help [search terms] [page]"), 1);
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

    // TODO: Implement search terms

    private void console(MessageReceiver caller, String[] args) {
        int page = 0;
        String[] searchTerms = null;

        if(args.length == 1) {
            page = 1;
        }
        else if (args.length == 2) {
            if(args[1].matches("\\d+")) {
                page = Integer.parseInt(args[1]);
            }
            else {
                page = 0;
                searchTerms = Arrays.copyOfRange(args, 1, 1);
            }
        }
        else if(args.length > 2) {
            if(args[args.length-1].matches("\\d+")) {
                page = Integer.parseInt(args[args.length-1]);
                searchTerms = Arrays.copyOfRange(args, 1, args.length - 1);
            }
            else {
                page = 0;
            }
        }
        String[] lines;
        if(searchTerms == null) {
            lines = Canary.help().getHelp(null, page);
        }
        else {
            lines = Canary.help().getSearch(null, searchTerms, page);
        }

        if (lines == null) {
            Canary.logInfo(Translator.translate("help not found"));
        }
        // Send all lines
        for (String l : lines) {
            Canary.logInfo(l);
        }
    }

    private void player(Player player, String[] args) {
        int page = 0;
        String[] searchTerms = null;

        if(args.length == 1) {
            page = 1;
        }
        else if (args.length == 2) {
            if(args[1].matches("\\d+")) {
                page = Integer.parseInt(args[1]);
            }
            else {
                page = 0;
                searchTerms = Arrays.copyOfRange(args, 1, 2);
            }
        }
        else if(args.length > 2) {
            if(args[args.length-1].matches("\\d+")) {
                page = Integer.parseInt(args[args.length-1]);
                searchTerms = Arrays.copyOfRange(args, 1, args.length -1);
            }
            else {
                page = 0;
                searchTerms = Arrays.copyOfRange(args, 1, args.length);
            }
        }
        String[] lines;
        if(searchTerms == null) {
            lines = Canary.help().getHelp(player, page);
        }
        else {
            lines = Canary.help().getSearch(player, searchTerms, page);
        }

        if (lines == null) {
            player.notice(Translator.translate("help not found"));
            return;
        }

        // Send all the fancy pre-formatted lines
        for (String l : lines) {
            player.sendMessage(l);
        }
    }

}
