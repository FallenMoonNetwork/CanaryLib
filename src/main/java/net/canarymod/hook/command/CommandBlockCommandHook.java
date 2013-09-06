package net.canarymod.hook.command;

import net.canarymod.api.world.blocks.CommandBlock;
import net.canarymod.hook.CancelableHook;
import net.visualillusionsent.utils.StringUtils;

/**
 * CommandBlockCommandHook<br>
 * Called when a CommandBlock executes a command.
 *
 * @author Jason (darkdiplomat)
 */
public final class CommandBlockCommandHook extends CancelableHook {
    private CommandBlock block;
    private String[] args;

    /**
     * Constructs a new CommandBlockCommandHook
     *
     * @param block
     *         the {@link CommandBlock} used
     * @param args
     *         the command and arguments
     */
    public CommandBlockCommandHook(CommandBlock block, String[] args) {
        this.block = block;
        this.args = args;
    }

    /**
     * Gets the CommandBlock
     *
     * @return the {@link CommandBlock}
     */
    public CommandBlock getCommandBlock() {
        return block;
    }

    /**
     * Gets the command and arguments
     *
     * @return the command and arguments
     */
    public String[] getArguments() {
        return args;
    }

    @Override
    public final String toString() {
        return String.format("%s[CommandBlock=%s, Arguments=%s]", getName(), block, StringUtils.joinString(args, " ", 0));
    }

}
