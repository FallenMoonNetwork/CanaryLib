package net.canarymod.api.entity;

/**
 * Falling Block wrapper
 * 
 * @author Jason (darkdiplomat)
 */
public interface FallingBlock extends Entity {

    /**
     * Gets the Block ID of the FallingBlock
     * 
     * @return the Block ID
     */
    public short getBlockID();

    /**
     * Sets the Block ID of the FallingBlock
     * 
     * @param id
     *            the Block ID
     */
    public void setBlockID(int id);

    /**
     * Gets the Block's metadata
     * 
     * @return the Block's MetaData
     */
    public short getBlockMetaData();

    /**
     * Sets the Block's metadata
     * 
     * @param data
     *            the Block's MetaData
     */
    public void setBlockMetaData(int data);

    /**
     * Gets the maximum damage the Block can cause if it hits an Entity
     * 
     * @return maximum damage
     */
    public int getMaxDamage();

    /**
     * Sets the maximum damage the Block can cause if it hits an Entity
     */
    public void setMaxDamage(int max);

    /**
     * Gets the damage caused by the FallingBlock multiplied by the number of blocks fell
     * 
     * @return damage
     *         the amount of base damage
     */
    public float getDamageAmount();

    /**
     * Sets the damage caused by the FallingBlock
     * 
     * @param damage
     *            the amount of base damage to cause
     */
    public void setDamageAmount(float damage);
}
