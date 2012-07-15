package net.canarymod.api.entity.potion;

/**
 * A potion, can give various {@link PotionEffect}s
 * @author Brian McCarthy
 *
 */
public interface Potion {

    public int getID();
    
    public String getName();
    
    public PotionType getType();
    
    public boolean isBad();
    
    public double getEffectiveness();
    
    public boolean isUsable();
    
    public boolean isInstant();
}
