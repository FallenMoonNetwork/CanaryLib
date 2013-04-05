package net.canarymod.commandsys.commands;


import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;


public class ReloadCommand extends CanaryCommand {

    public ReloadCommand() {
        super("canary.command.reload", Translator.translate("reload info"), Translator.translateAndFormat("usage", "/reload"), 1);
    }

    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server || caller instanceof Player) {
            exec(caller);
        } else {
            throw new CommandException("Unknown MessageReceiver: " + caller.getClass().getSimpleName());
        }
    }

    private void exec(MessageReceiver caller) {
        caller.notice(Translator.translate("reload reloading"));
        Canary.instance().reload();
        caller.notice(Translator.translate("reload reloading done"));
    }
}
