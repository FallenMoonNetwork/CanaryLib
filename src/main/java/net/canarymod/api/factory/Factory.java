package net.canarymod.api.factory;


/**
 * Factory interface<br>
 * For creation of Entities, Items, etc...
 * 
 * @author Brian McCarthy (WWOL)
 * @author Jason (darkdiplomat)
 */
public interface Factory {
    
    public ItemFactory getItemFactory();
    
    public PotionFactory getPotionFactory();
    
    public EntityFactory getEntityFactory();
    
    public ObjectFactory getObjectFactory();

}
