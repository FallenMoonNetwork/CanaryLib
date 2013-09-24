package net.canarymod.api;

/**
 * An enum of player game modes
 * 
 * @author Jason (darkdiplomat)
 */
public enum GameMode {
    /* DO NOT REORDER */
    SURVIVAL, //
    CREATIVE, //
    ADVENTURE, //
    ;

    public final int getId() {
        return this.ordinal();
    }

    public final static GameMode fromId(int id) {
        switch (id) {
            case 1:
                return CREATIVE;
            case 2:
                return ADVENTURE;
            default:
                return SURVIVAL;
        }
    }
}