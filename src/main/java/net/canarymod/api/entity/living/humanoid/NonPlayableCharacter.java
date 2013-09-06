package net.canarymod.api.entity.living.humanoid;

import java.util.List;

import net.canarymod.api.entity.Entity;

/**
 * Non-Playable Character interface
 *
 * @author Jason (darkdiplomat)
 */
public interface NonPlayableCharacter extends Human {

    /** Tell the NPC to look at nearest player */
    public void lookAtNearest();

    /**
     * Shows the NPC to the specified Player
     *
     * @param player
     *         the {@link Player} to be shown the NPC
     */
    public void haunt(Player player);

    /**
     * Hides the NPC from specified Player
     *
     * @param player
     *         the Player to hide from
     */
    public void ghost(Player player);

    /**
     * De-spawn the NPC and return its reference for further processing.
     * This does not DELETE this entity, it stays there, its just no longer shown
     *
     * @return the NonPlayableCharacter instance
     */
    public NonPlayableCharacter despawn();

    /**
     * Gets the list of NPCBehaviors for this NonPlayableCharacter
     *
     * @return the list of NPCBehaviors
     */
    public List<NPCBehavior> getBehaviors();

    /**
     * Removes a behavior from the NonPlayableCharacter
     *
     * @param behavior
     *         the NPCBehavior to be removed
     *
     * @return the removed NPCBehavior or {@code null} if the NPCBehavior wasn't in the list
     */
    public NPCBehavior removeBehavior(NPCBehavior behavior);

    /**
     * Adds a NPCBehavior to the NonPlayableCharacter
     *
     * @param behavior
     *         the NPCBehavior to be added
     *
     * @return {@code true} of successfully added, {@code false} if not
     */
    public boolean addBehavior(NPCBehavior behavior);

    /**
     * Sends a message as the NonPlayableCharacter
     *
     * @param msg
     *         the Message to send
     */
    public void chat(String msg);

    /**
     * Sends a private message as the NonPlayerCharacter to the specified Player
     *
     * @param player
     *         the {@link Player} to pm
     * @param msg
     *         the message to send
     */
    public void privateMessage(Player player, String msg);

    /**
     * Attacks for the NonPlayableCharacter the targeted entity with the
     * currently equipped item.
     *
     * @param entity
     *         Entity to attack.
     */
    public void attackEntity(Entity entity);

}
