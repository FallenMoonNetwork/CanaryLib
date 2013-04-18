package net.canarymod.commandsys.commands;


import java.util.ArrayList;
import java.util.Arrays;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.chat.TextFormat;
import net.canarymod.commandsys.CommandException;


public class HelpCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server) {
            console(caller, parameters);
        } else if (caller instanceof Player) {
            player((Player) caller, parameters);
        } else {
            throw new CommandException(Translator.translateAndFormat("unknown messagereceiver", caller.getClass().getSimpleName()));
        }
    }
    private void console(MessageReceiver caller, String[] args) {
        int page = 1;
        String[] searchTerms = null;
        if (args.length == 2) {
            if(args[1].matches("\\d+")) {
                page = Integer.parseInt(args[1]);
            }
            else {
                searchTerms = Arrays.copyOfRange(args, 1, 1);
            }
        }
        else if(args.length > 2) {
            if(args[args.length-1].matches("\\d+")) {
                page = Integer.parseInt(args[args.length-1]);
                searchTerms = Arrays.copyOfRange(args, 1, args.length - 1);
            }
            else {
                searchTerms = Arrays.copyOfRange(args, 1, args.length);
            }
        }
        ArrayList<String> lines;
        if(searchTerms == null) {
            lines = Canary.help().getHelp(null, page);
        }
        else {
            lines = Canary.help().getHelp(null, searchTerms, page);
        }

        if (lines == null) {
            Canary.logInfo(Translator.translate("help not found"));
        }
        // Send all lines
        for (String l : lines) {

            Canary.logInfo(TextFormat.removeFormatting(l));
        }
    }

    private void player(Player player, String[] args) {
        int page = 1;
        String[] searchTerms = null;
        if (args.length == 2) {
            if(args[1].matches("\\d+")) {
                page = Integer.parseInt(args[1]);
            }
            else {
                searchTerms = Arrays.copyOfRange(args, 1, 2);
            }
        }
        else if(args.length > 2) {
            if(args[args.length-1].matches("\\d+")) {
                page = Integer.parseInt(args[args.length-1]);
                searchTerms = Arrays.copyOfRange(args, 1, args.length -1);
            }
            else {
                searchTerms = Arrays.copyOfRange(args, 1, args.length);
            }
        }
        ArrayList<String> lines;
        if(searchTerms == null) {
            lines = Canary.help().getHelp(player, page);
        }
        else {
            lines = Canary.help().getHelp(player, searchTerms, page);
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
