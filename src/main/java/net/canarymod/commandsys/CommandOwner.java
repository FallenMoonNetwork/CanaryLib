package net.canarymod.commandsys;

/**
 * This interface mandates methods for command ownership.
 * A command owner can be the server or a plugin.
 * 
 * @author chris ksoll
 */
public interface CommandOwner {

    /**
     * Get the name of this command owner
     * 
     * @return The name
     */
    public String getName();

}
