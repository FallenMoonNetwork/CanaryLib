package net.canarymod.hook.player;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.CancelableHook;

public final class AnvilUseHook extends CancelableHook {
    private Player player;

    // private Anvil anvil;

    // public AnvilUseHook(Player player, Anvil anvil){
    // this.player = player;
    // this.anvil = anvil;
    // }

    public Player getPlayer() {
        return player;
    }

    // public Anvil getAnvil(){
    // return anvil;
    // }

    // public final String toString(){
    // return String.format("%s[Player=%s, Anvil=%s]", getName(), player, anvil);
    // }

}
