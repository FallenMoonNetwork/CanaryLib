package net.canarymod.api.entity;

public interface Chicken extends EntityAnimal {
    /**
     * Time in ticks (20/Minute) until the chicken lays the next egg.
     * Set 0 to make it lay an egg more or less instantly
     * @param timeTicks
     */
    public void setTimeUntilNextEgg(int timeTicks);
}
