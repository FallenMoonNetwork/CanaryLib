package net.canarymod.hook.player;

import net.canarymod.api.entity.EntityLiving;
import net.canarymod.api.entity.Player;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;
import net.canarymod.plugin.PluginListener;

/**
 * Multi use hook for right click. Contains information about a player right clicking.
 * @author Jason Jones
 *
 */
public final class RightClickHook extends CancelableHook{
    
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
     * Gets the {@link Player}
     * @return
     */
    public Player getPlayer(){
        return player;
    }
    
    /**
     * Gets the {@link Block} clicked
     * @return
     */
    public Block getBlockClicked(){
        return clicked;
    }
    
    /**
     * Get the {@link Block} placed
     * @return
     */
    public Block getBlockPlaced(){
        return placed;
    }
    
    /**
     * Gets the {@link Item} used
     * @return item
     */
    public Item getItem(){
        return item;
    }
    
    /**
     * Return the {@link EntityLiving} that was right clicked
     * @return entity clicked else null if none clicked
     */
    public EntityLiving getEntityClicked(){
        return entity;
    }
    
    /**
     * Return the set of Data in this order: PLAYER CLICKEDBLOCK PLACEDBLOCK ITEM ENTITY ISCANCELLED
     */
    @Override
    public Object[] getDataSet(){
        return new Object[]{ player, clicked, placed, item, entity, isCanceled };
    }
    
    /**
     * Dispatches the hook to the given listener.
     * @param listener The listener to dispatch the hook to.
     */
    @Override
    public void dispatch(PluginListener listener) {
        switch (this.type){
            case BLOCK_PLACE: {
                listener.onBlockPlace(this);
                break;
            }
            case BLOCK_RIGHTCLICKED: {
                listener.onBlockRightClicked(this);
                break;
            }
            case EAT: {
                listener.onEat(this);
                break;
            }
            case ENTITY_RIGHTCLICKED: {
                listener.onEntityRightClicked(this);
                break;
            }
            case ITEM_USE: {
                listener.onItemUse(this);
                break;
            }
        }
    }
}
