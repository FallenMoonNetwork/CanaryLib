package net.canarymod.commandsys.commands;


import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;


public class SetSpawn extends CanaryCommand {

    public SetSpawn() {
        super("canary.command.setspawn", Translator.translate("setspawn info"), Translator.translateAndFormat("usage", "/setspawn"), 1);
    }

    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server) {
            console(caller);
        } else if (caller instanceof Player) {
            player((Player) caller, parameters);
        } else {
            throw new CommandException("Unknown MessageReceiver: " + caller.getClass().getSimpleName());
        }
    }

    private void console(MessageReceiver caller) {
        caller.notice(Translator.translate("setspawn console"));
    }

    private void player(Player player, String[] args) {
        player.getWorld().setSpawnLocation(player.getLocation());
        player.sendMessage(Colors.YELLOW + Translator.translate("setspawn success"));
    }

}
