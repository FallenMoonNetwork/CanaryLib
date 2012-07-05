package net.canarymod.hook.entity;

import net.canarymod.api.entity.EntityLiving;
import net.canarymod.api.entity.Player;
import net.canarymod.hook.CancelableHook;
import net.canarymod.plugin.PluginListener;

/**
 * Mob target hook. Conatins information about a mob targeting a player either for attack or following
 * @author Jason Jones
 */
public final class MobTargetHook extends CancelableHook{
    
    private EntityLiving entity;
    private Player player;
    
    public MobTargetHook(EntityLiving entity, Player player){
        this.entity = entity;
        this.player = player;
    }
    
    /**
     * Gets the {@link EntityLiving} doing the targeting
     * @return entity
     */
    public EntityLiving getEntity(){
        return entity;
    }
    
    /**
     * Gets the {@link Player} being targeted
     * @return player
     */
    public Player getPlayer(){
        return player;
    }
    
    public Object[] getDataSet(){
        return new Object[]{ entity, player, isCanceled };
    }
    
    /**
     * Dispatches the hook to the given listener.
     * @param listener The listener to dispatch the hook to.
     */
    @Override
    public void dispatch(PluginListener listener) {
        listener.onMobTarget(this);
    }

}
