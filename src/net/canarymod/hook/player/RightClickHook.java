package net.canarymod.hook.player;

import net.canarymod.api.entity.EntityLiving;
import net.canarymod.api.entity.Player;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;

/**
 * Multi use hook for right click. Contains information about a player right clicking.
 * @author Jason Jones
 *
 */
public class RightClickHook extends CancelableHook{
    
    private Player player;
    private Block placed, clicked;
    private Item item;
    private EntityLiving entity;
    
    public RightClickHook(Player player, Block clicked, Block placed, Item item, EntityLiving entity, Type type){
        this.player = player;
        this.clicked = clicked;
        this.placed = placed;
        this.type = type;
    }
    
    /**
     * gets the player
     * @return
     */
    public Player getPlayer(){
        return player;
    }
    
    /**
     * gets the block clicked
     * @return
     */
    public Block getBlockClicked(){
        return clicked;
    }
    
    /**
     * get the block placed
     * @return
     */
    public Block getBlockPlaced(){
        return placed;
    }
    
    /**
     * gets the item used
     * @return item
     */
    public Item getItem(){
        return item;
    }
    
    public EntityLiving getEntityClicked(){
        return entity;
    }
    
    /**
     * Return the set of Data in this order: PLAYER CLICKEDBLOCK PLACEDBLOCK ITEM ENTITY ISCANCELLED
     */
    @Override
    public Object[] getDataSet(){
        return new Object[]{ player, clicked, placed, item, entity, isCancelled };
    }
}
