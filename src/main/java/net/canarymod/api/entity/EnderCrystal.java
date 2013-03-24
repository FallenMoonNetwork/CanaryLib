package net.canarymod.api.entity;


/**
 * EnderCrystal
 * the Crystals that heal the EnderDragon
 * 
 * @author Jason (darkdiplomat)
 */
public interface EnderCrystal extends Entity, Explosive {

    public int getHealth();

    public void setHealth(int health);

}
