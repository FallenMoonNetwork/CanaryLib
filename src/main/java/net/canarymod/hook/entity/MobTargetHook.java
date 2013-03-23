package net.canarymod.hook.entity;

import net.canarymod.api.entity.living.EntityLiving;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.CancelableHook;

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

    @Override
    public Object[] getDataSet(){
        return new Object[]{ entity, player, isCanceled };
    }
}
