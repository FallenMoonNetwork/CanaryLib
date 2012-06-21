package net.canarymod.hook.entity;

import net.canarymod.api.DamageSource;
import net.canarymod.api.entity.EntityLiving;
import net.canarymod.hook.CancelableHook;

/**
 * Damage hook. Contains information about an entity taking damage.
 * @author Jason Jones
 *
 */
public class DamageHook extends CancelableHook{
    
    private EntityLiving attacker, defender;
    private DamageSource source;
    private int delt;
    
    public DamageHook(EntityLiving attacker, EntityLiving defender, DamageSource source, int delt){
        this.attacker = attacker;
        this.defender = defender;
        this.source = source;
        this.delt = delt;
        this.type = Type.DAMAGE;
    }
    
    /**
     * Gets the attacking {@link EntityLiving} if present
     * @return attacker if there is one, null otherwise
     */
    public EntityLiving getAttacker(){
        return attacker;
    }
    
    /**
     * Gets the defending {@link EntityLiving}
     * @return defender
     */
    public EntityLiving getDefender(){
        return defender;
    }
    
    /**
     * Gets the {@link DamageSource} type
     * @return source
     */
    public DamageSource getDamageSource(){
        return source;
    }
    
    /**
     * Gets the amount of damage delt
     * @return delt
     */
    public int getDamageDelt(){
        return delt;
    }
    
    /**
     * Return the set of Data in this order: ATTACKER DEFENDER SOURCE DELT ISCANCELLED
     */
    @Override
    public Object[] getDataSet(){
        return new Object[]{ attacker, defender, source, delt, isCanceled };
    }

}
