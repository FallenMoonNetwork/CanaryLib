package net.canarymod.hook.entity;

import net.canarymod.api.entity.EntityLiving;
import net.canarymod.hook.CancelableHook;

/**
 * Attack hook. Contains information about an entity attacking an entity.
 * 
 * @author Jason Jones
 */
public class AttackHook extends CancelableHook {
    private EntityLiving attacker;
    private EntityLiving defender;
    private int delt;
    
    public AttackHook(EntityLiving attacker, EntityLiving defender, int delt){
        this.attacker = attacker;
        this.defender = defender;
        this.delt   = delt;
        this.type = Type.ATTACK;
    }
    
    /**
     * gets the attacking entity
     * @return attacker
     */
    public EntityLiving getAttacker(){
        return attacker;
    }
    
    /**
     * gets the defending entity
     * @return defender
     */
    public EntityLiving getDefender(){
        return defender;
    }
    
    /**
     * gets damage delt amount
     * @return delt
     */
    public int getDamageAmount(){
        return delt;
    }

    /**
     * Return the set of Data in this order: ATTACKER DEFENDER DELT ISCANCELLED
     */
    @Override
    public Object[] getDataSet() {
        return new Object[]{ attacker, defender, delt, isCancelled};
    }

}
