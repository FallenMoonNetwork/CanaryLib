package net.canarymod.api.entity;

/**
 * EntityType enum
 * 
 * @author Jason (darkdiplomat)
 */
public enum EntityType {

    ARROW(0), //
    BAT(4), //
    BLAZE(5), //
    BOAT(2), //
    CAVESPIDER(5), //
    CHESTMINECART(2), //
    CHICKEN(4), //
    CHICKENEGG(1), //
    COW(4), //
    CREEPER(5), //
    EMPTYMINECART(2), //
    ENDERCRYSTAL(0), //
    ENDERDRAGON(5), //
    ENDEREYE(0), //
    ENDERMAN(5), //
    ENDERPEARL(1), //
    ENTITYITEM(0), //
    ENTITYPOTION(1), //
    FALLINGBLOCK(0), //
    FIREWORKROCKET(0), //
    FURNACEMINECART(2), //
    GHAST(5), //
    GIANTZOMBIE(5), //
    HOPPERMINECART(2), //
    IRONGOLEM(3), //
    ITEMFRAME(0), //
    LARGEFIREBALL(0), //
    LIGHTNINGBOLT(0), //
    LAVASLIME(5), //
    MOBSPAWNERMINECART(2), //
    MOOSHROOM(4), //
    NPC(3), //
    NONPLAYABLECHARACTER(3), //
    OCELOT(4), //
    PAINTING(0), //
    PIG(4), //
    PIGZOMBIE(5), //
    SHEEP(4), //
    SILVERFISH(5), //
    SKELETON(5), //
    SLIME(5), //
    SMALLFIREBALL(0), //
    SNOWMAN(3), //
    SPIDER(5), //
    SQUID(4), //
    TNTMINECART(2), //
    TNTPRIMED(0), //
    VILLAGER(3), //
    WITCH(5), //
    WITHER(5), //
    WOLF(4), //
    XPBOTTLE(1), //
    XPORB(0), //
    ZOMBIE(5),    //
    ;

    private final byte type;

    private EntityType(int type) {
        this.type = (byte) type;
    }

    /**
     * Checks if the EntityType is that of a Throwable
     * 
     * @return {@code true} if throwable; {@code false} if not
     */
    public boolean isThrowable() {
        return this.type == 1;
    }

    /**
     * Checks if the EntityType is that of a Vehicle
     * 
     * @return {@code true} if vehicle; {@code false} if not
     */
    public boolean isVehicle() {
        return this.type == 2;
    }

    /**
     * Checks if the EntityType is that of a EntityLiving
     * 
     * @return {@code true} if living; {@code false} if not
     */
    public boolean isLiving() {
        return type >= 3;
    }

    /**
     * Checks if the EntityType is that of an Animal
     * 
     * @return {@code true} if animal; {@code false} if not
     */
    public boolean isAnimal() {
        return type == 4;
    }

    /**
     * Checks if the EntityType is that of a Mob
     * 
     * @return {@code true} if mob; {@code false} if not
     */
    public boolean isMob() {
        return type == 5;
    }

}
