package net.canarymod.api.potion;


/**
 * A potion, can give various {@link PotionEffect}s
 * @author Brian McCarthy
 *
 */
public interface Potion {

    public int getID();
    
    public String getName();
    
    public PotionEffectType getEffectType();
    
    public boolean isBad();
    
    public double getEffectiveness();
    
    public boolean isUsable();
    
    public boolean isInstant();
}
