package net.canarymod.api.entity.living.monster;


import net.canarymod.api.entity.EnderCrystal;
import net.canarymod.api.entity.living.EntityLiving;


/**
 * EnderDragon wrapper
 * NOTE: This is not fully classified as a Mob
 * 
 * @author Jason (darkdiplomat)
 */
public interface EnderDragon extends EntityLiving {

    public boolean isSlowed();

    public EnderCrystal getHealingCrystal();

}
