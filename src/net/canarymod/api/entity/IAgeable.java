package net.canarymod.api.entity;

public interface IAgeable {
    
    /**
     * Get the growing age of this entity.
     * Please note, this is not the IEntityLiving.getAge()
     * @return
     */
    public int getGrowingAge();
    
    /**
     * Set the new growing age. Negative values turn this entity into a baby!
     * @param age
     */
    public void setGrowingAge(int age);
}
