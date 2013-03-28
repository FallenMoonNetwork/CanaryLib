package net.canarymod.api.entity.living.humanoid;

/**
 * NPCBahavior<br>
 * Implement this interface and override the execute method to execute your<br>
 * own NPC update code. Like changing position or being clicked.
 * <p>
 * NOTE: This is a work-in-progress and subject to major changes
 * 
 * @author Jason (darkdiplomat)
 */
public interface NPCBehavior {

    /**
     * The method called from {@link NonPlayableCharacter} when it's update method gets called
     */
    public void onUpdate();

    /**
     * The method called from {@link NonPlayableCharacter} when it gets attacked
     */
    public void onAttack();

    /**
     * The method called from {@link NonPlayableCharacter} when a {@link Player} clicks the {@link NonPlayableCharacter}
     */
    public void onClick();

}
