package net.canarymod.api.factory;


import net.canarymod.api.world.World;
import net.canarymod.api.world.blocks.Block;


public interface BlockFactory {

    /**
     * Make a new {@link Block} with id. Damage 0, x 0, y 0, z 0, world default 
     * @param id
     * @return
     */
    Block newBlock(int id);
    
    /**
     * Make a new {@link Block} with id, damage. x 0, y 0, z 0, world default
     * @param id
     * @param damage
     * @return
     */
    Block newBlock(int id, int damage);
    
    /**
     * Make a new {@link Block} with id, world. Damage 0, x 0, y 0, z 0
     * @param id
     * @param world
     * @return
     */
    Block newBlock(int id, World world);
    
    /**
     * Make a new {@link Block} with id, damage, world. x 0, y 0, z 0
     * @param id
     * @param damage
     * @param world
     * @return
     */
    Block newBlock(int id, int damage, World world);
    
    /**
     * Make a new {@link Block} with id, x, y, z. Damage 0, world default
     * @param id
     * @param x
     * @param y
     * @param z
     * @return
     */
    Block newBlock(int id, int x, int y, int z);
    
    /**
     * Make a new {@link Block} with id, damage, x, y, z. World default
     * @param id
     * @param damage
     * @param x
     * @param y
     * @param z
     * @return
     */
    Block newBlock(int id, int damage, int x, int y, int z);
    
    /**
     * Make a new {@link Block} with id, damage, x, y, z, world.
     * @param id
     * @param damage
     * @param x
     * @param y
     * @param z
     * @param world
     * @return
     */
    Block newBlock(int id, int damage, int x, int y, int z, World world);
    
    /**
     * Make a new {@link Block} with another block
     * @param block
     * @return
     */
    Block newBlock(Block block);
}
