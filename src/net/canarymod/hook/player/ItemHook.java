package net.canarymod.hook.player;

import net.canarymod.api.entity.EntityItem;
import net.canarymod.api.entity.Player;
import net.canarymod.hook.CancelableHook;

public class ItemHook extends CancelableHook{

    private EntityItem item;
    private Player player;
    
    public ItemHook(Player player, EntityItem item, boolean isDropped){
        this.player = player;
        this.item = item;
        this.type = isDropped ? Type.ITEM_DROP : Type.ITEM_PICK_UP;
    }
    
    public Player getPlayer(){
        return player;
    }
    
    public EntityItem getItem(){
        return item;
    }
    
    public Object[] getDataSet(){
        return new Object[]{ player, item, isCancelled };
    }
}
