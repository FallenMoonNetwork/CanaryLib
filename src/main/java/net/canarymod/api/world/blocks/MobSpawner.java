package net.canarymod.api.world.blocks;

public interface MobSpawner extends ComplexBlock{
    
    public String getSpawnType();
    
    public void setSpawnType(String spawn);
    
    public int getDelay();
    
    public void setDelay(int delay);
}
