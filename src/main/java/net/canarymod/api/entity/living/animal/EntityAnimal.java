package net.canarymod.api.entity.living.animal;


import net.canarymod.api.entity.living.EntityLiving;


public interface EntityAnimal extends EntityLiving {

    public enum AnimalType {
        BAT, CHICKEN, COW, MUSHROOMCOW, OCELOT, PIG, SHEEP, SQUID, VILLAGER, WOLF
    }

    /**
     * Set this Entities target entity. Depending on entity type this must not
     * necessarily be an attack target. Null to remove target
     * 
     * @param target
     */
    public void setTarget(EntityLiving target);

    /**
     * Get the current target of this entity
     * 
     * @return
     */
    public EntityLiving getTarget();

    /**
     * Set this animals age.
     * Set negative to turn it into a baby animal.
     * (Breeding related)
     * 
     * @param age
     */
    public void setGrowingAge(int age);

    /**
     * Get the growing age for this entity.
     * (Breeding related)
     * 
     * @return
     */
    public int getGrowingAge();
}
