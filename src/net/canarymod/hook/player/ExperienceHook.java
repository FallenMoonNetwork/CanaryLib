package net.canarymod.hook.player;

import net.canarymod.api.entity.Player;
import net.canarymod.hook.CancelableHook;

/**
 * Experience hook. Contains information about player experience changes.
 * @author Jason Jones
 *
 */
public class ExperienceHook extends CancelableHook{
    
    private Player player;
    private int oldval, newval;

    public ExperienceHook(Player player, int oldval, int newval){
        this.player = player;
        this.oldval = oldval;
        this.newval = newval;
        this.type = Type.EXPERIENCE_CHANGE;
    }
    
    /**
     * gets the player
     * @return player
     */
    public Player getPlayer(){
        return player;
    }
    
    /**
     * gets the old experience value
     * @return oldval
     */
    public int getOldValue(){
        return oldval;
    }
    
    /**
     * gets the new experience value
     * @return newval
     */
    public int getNewValue(){
        return newval;
    }
    
    /**
     * Return the set of Data in this order: PLAYER OLDVAL NEWVAL ISCANCELLED
     */
    @Override
    public Object[] getDataSet(){
        return new Object[]{ player, oldval, newval, isCancelled };
    }
}
