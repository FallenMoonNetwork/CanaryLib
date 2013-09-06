package net.canarymod.api.entity;

/**
 * EntityType enum
 *
 * @author Jason (darkdiplomat)
 */
public enum EntityType {

    ARROW(10, 0), //
    BAT(65, 4), //
    BLACKSMITH(120, 3), //
    BLAZE(61, 5), //
    BUTCHER(120, 3), //
    BOAT(41, 2), //
    CAVESPIDER(59, 5), //
    CHESTMINECART(43, 2), //
    CHICKEN(93, 4), //
    CHICKENEGG(0, 1), //
    COW(92, 4), //
    CREEPER(50, 5), //
    DONKEY(100, 4), //
    EMPTYMINECART(42, 2), //
    ENDERCRYSTAL(200, 0), //
    ENDERDRAGON(63, 5), //
    ENDEREYE(15, 0), //
    ENDERMAN(58, 5), //
    ENDERPEARL(14, 1), //
    ENTITYITEM(1, 0), //
    ENTITYPOTION(16, 1), //
    FALLINGBLOCK(21, 0), //
    FARMER(120, 3), //
    FIREWORKROCKET(22, 0), //
    FURNACEMINECART(44, 2), //
    GHAST(56, 5), //
    GIANTZOMBIE(53, 5), //
    HOPPERMINECART(46, 2), //
    HORSE(100, 4), //
    IRONGOLEM(99, 3), //
    ITEMFRAME(18, 6), //
    LARGEFIREBALL(12, 0), //
    LEASHKNOT(8, 6), //
    LIBRARIAN(120, 3), //
    LIGHTNINGBOLT(0, 0), //
    MAGMACUBE(62, 5), //
    MOBSPAWNERMINECART(47, 2), //
    MOOSHROOM(96, 4), //
    MULE(100, 4), //
    NPC(48, 3), //
    NONPLAYABLECHARACTER(48, 3), //
    OCELOT(98, 4), //
    PAINTING(9, 6), //
    PIG(90, 4), //
    PIGZOMBIE(57, 5), //
    PLAYER(48, 3), //
    POTION(16, 0), //
    PRIEST(120, 3), //
    SHEEP(91, 4), //
    SILVERFISH(60, 5), //
    SKELETON(51, 5), //
    SKELETONHORSE(100, 4), //
    SLIME(55, 5), //
    SMALLFIREBALL(13, 0), //
    SNOWBALL(11, 0), //
    SNOWMAN(97, 3), //
    SPIDER(52, 5), //
    SQUID(94, 4), //
    TNTMINECART(45, 2), //
    TNTPRIMED(20, 0), //
    VILLAGER(120, 3), //
    WITCH(66, 5), //
    WITHER(64, 5), //
    WITHERSKELETON(51, 5), //
    WITHERSKULL(19, 0), //
    WOLF(95, 4), //
    XPBOTTLE(17, 1), //
    XPORB(2, 0), //
    ZOMBIE(54, 5), //
    ZOMBIEHORSE(100, 4), //
    ;

    private final byte type;
    private final short id;

    private EntityType(int id, int type) {
        this.id = (short) id;
        this.type = (byte) type;
    }

    public short getEntityID() {
        return id;
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
        return type == 3 || type == 4 || type == 5;
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

    /**
     * Checks if the EntityType is that of a HangingEntity
     *
     * @return {@code true} if hanging; {@code false} if not
     */
    public boolean isHanging() {
        return type == 6;
    }
}
