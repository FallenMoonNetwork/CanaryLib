package net.canarymod.api.factory;


/**
 * Create new items, potions etc. Workaround because 
 * @author Brian McCarthy
 *
 */
public interface Factory {
    
    public ItemFactory itemFactory();
    
    public BlockFactory blockFactory();
    
    public PotionFactory potionFactory();
    
    public EntityLivingFactory entityLivingFactory();
    
}
