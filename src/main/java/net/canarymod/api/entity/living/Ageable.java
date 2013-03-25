package net.canarymod.api.entity.living;

public interface Ageable {

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
