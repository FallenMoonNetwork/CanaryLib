package net.canarymod.api.entity.living.monster;

/**
 * Enderman Wrapper
 * 
 * @author Jason (darkdiplomat)
 */
public interface Enderman extends EntityMob {

    /**
     * Get the block id for the block this Enderman carries
     * 
     * @return block id
     */
    public short getCarriedBlockID();

    /**
     * Sets the Block ID for the block this Enderman carries
     * 
     * @param blockid
     */
    public void setCarriedBlockID(short blockId);

    /**
     * Gets the metadata of the Block the Enderman carries
     * 
     * @return metadata
     */
    public short getCarriedBlockMetaData();

    /**
     * Sets the metadata of the Block
     * 
     * @param data
     */
    public void setCarriedBlockMetaData(short metadata);

    /**
     * Teleport the Enderman to a random position nearby
     * 
     * @return {@code true} if successful; {@code false} if not
     */
    public boolean randomTeleport();

}
