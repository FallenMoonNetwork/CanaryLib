package net.canarymod.commandsys;


import net.canarymod.chat.MessageReceiver;


public class RegisteredCommand {
    private CommandOwner owner;
    private CanaryCommand command;
    
    public RegisteredCommand(CommandOwner owner, CanaryCommand command) {
        this.owner = owner;
        this.command = command;
    }
    
    /**
     * Get the name of the command owner
     * @return
     */
    public String getOwnerName() {
        return owner.getName();
    }
    
    /**
     * Get the stored command
     * @return
     */
    public CanaryCommand getCommand() {
        return command;
    }
    
    /**
     * Parse the stored command
     * @param caller
     * @param command
     * @return
     */
    public boolean parseCommand(MessageReceiver caller, String[] command) {
        return this.command.parseCommand(caller, command);
    }
}
