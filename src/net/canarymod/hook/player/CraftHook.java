package net.canarymod.hook.player;

import net.canarymod.api.entity.Player;
import net.canarymod.api.inventory.Item;
import net.canarymod.hook.Hook;

public class CraftHook extends Hook{
    private Item result;
    private Player player;
    
    public CraftHook(Player player, Item result, boolean smelting){
        this.player = player;
        this.result = result;
        this.type = smelting ? /*Type.SMELT*/ null : Type.CRAFT;
    }
    
    public Player getPlayer(){
        return player;
    }
    
    public Item getCraftResult(){
        return result;
    }
    
    public void setCraftResult(Item result){
        this.result = result;
    }

    @Override
    public Object[] getDataSet() {
        // TODO Auto-generated method stub
        return null;
    }

}
