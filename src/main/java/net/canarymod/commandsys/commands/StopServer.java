package net.canarymod.commandsys.commands;


import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;


public class StopServer extends CanaryCommand {

    public StopServer() {
        super("*", "Stops the server", "Usage: /stop", 1);
    }

    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server) {
            caller.notice(caller.getName() + " issued a manual shutdown!");
            ((Server) caller).initiateShutdown();
        } else if (caller instanceof Player) {
            caller.notice("You cannot stop the server from in-game. Please use the console!");
        } else {
            throw new CommandException("Unknown MessageReceiver: " + caller.getClass().getSimpleName());
        }
    }
}
