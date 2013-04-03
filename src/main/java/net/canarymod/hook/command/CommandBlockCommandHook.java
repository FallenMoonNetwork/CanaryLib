package net.canarymod.hook.command;

import net.canarymod.hook.CancelableHook;

/**
 * CommandBlockCommandHook<br>
 * Called when a CommandBlock executes a command.
 * 
 * @author Jason (darkdiplomat)
 */
public final class CommandBlockCommandHook extends CancelableHook {
    // private CommandBlock block;
    private String[] args;

    // public CommandBlockCommandHook(CommandBlock block, String[] args){
    // this.block = block;
    // this.args = args;
    // }

    // public CommandBlock getCommandBlock(){
    // return block;
    // }

    public String[] getArguments() {
        return args;
    }

    // public final String toString(){
    // return String.format("%s[CommandBlock=%s, Arguments=%s]", getName(), block, StringUtils.joinString(args, " ", 0));
    // }

}
