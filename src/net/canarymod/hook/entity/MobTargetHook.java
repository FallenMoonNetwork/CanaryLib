package net.canarymod.hook.entity;

import net.canarymod.api.entity.EntityLiving;
import net.canarymod.api.entity.Player;
import net.canarymod.hook.CancelableHook;

/**
 * Mob target hook. Conatins information about a mob targeting a player either for attack or following
 * @author Jason Jones
 */
public class MobTargetHook extends CancelableHook{
    
    private EntityLiving entity;
    private Player player;
    
    public MobTargetHook(EntityLiving entity, Player player){
        this.entity = entity;
        this.player = player;
    }
    
    /**
     * Gets the mob doing the targeting
     * @return entity
     */
    public EntityLiving getEntity(){
        return entity;
    }
    
    /**
     * Gets the player being targeted
     * @return player
     */
    public Player getPlayer(){
        return player;
    }
    
    public Object[] getDataSet(){
        return new Object[]{ entity, player, isCancelled };
    }

}
