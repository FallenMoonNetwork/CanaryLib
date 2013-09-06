package net.canarymod.api.entity.living.humanoid;

import net.canarymod.api.entity.Entity;

/**
 * NPCBahavior<br>
 * Implement this interface and override the execute method to execute your<br>
 * own NPC code. Like changing position or being clicked.
 * <p/>
 * NOTE: This is a work-in-progress and subject to major changes
 *
 * @author Jason (darkdiplomat)
 */
public interface NPCBehavior {

    /** The method called from the {@link NonPlayableCharacter} implementation when it's update method gets called */
    public void onUpdate();

    /**
     * The method called from {@link NonPlayableCharacter} implementation when it gets attacked
     *
     * @param entity
     *         the {@link Entity} attacking
     */
    public void onAttacked(Entity entity);

    /**
     * The method called from {@link NonPlayableCharacter} implementation when a {@link Player} clicks the {@link NonPlayableCharacter}
     *
     * @param player
     *         the {@link Player} doing the clicking
     */
    public void onClicked(Player player);

    /** The method called from {@link NonPlayableCharacter} implementation when destroyed */
    public void onDestroy();

}
