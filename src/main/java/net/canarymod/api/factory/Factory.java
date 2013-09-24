package net.canarymod.api.factory;

/**
 * Factory interface<br>
 * For creation of Entities, Items, etc...
 *
 * @author Brian (WWOL)
 * @author Jason (darkdiplomat)
 */
public interface Factory {

    /**
     * Gets the {@link ItemFactory} instance
     *
     * @return {@link ItemFactory}
     */
    public ItemFactory getItemFactory();

    /**
     * Gets the {@link PotionFactory} instance
     *
     * @return {@link PotionFactory}
     */
    public PotionFactory getPotionFactory();

    /**
     * Gets the {@link EntityFactory} instance
     *
     * @return {@link EntityFactory}
     */
    public EntityFactory getEntityFactory();

    /**
     * Gets the {@link ObjectFactory} instance
     *
     * @return {@link ObjectFactory}
     */
    public ObjectFactory getObjectFactory();

    /**
     * Gets the {@link NBTFactory} instance
     *
     * @return {@link NBTFactory}
     */
    public NBTFactory getNBTFactory();

    /**
     * Gets the {@link PacketFactory} instance
     *
     * @return {@link PacketFactory}
     */
    public PacketFactory getPacketFactory();

}
