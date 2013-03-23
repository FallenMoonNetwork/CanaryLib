package net.canarymod.api.entity.living.monster;


public interface PigZombie extends Zombie {
    
    /**
     * basic check to see if this PigZombie is angry
     * @return true if angry
     */
    public boolean isAngry();
    
    /**
     * gets this PigZombie's anger level
     * @return level
     */
    public int getAngerLevel();
    
    /**
     * sets this PigZombie's anger level
     * @param level
     */
    public void setAngerLevel(int level);
}
