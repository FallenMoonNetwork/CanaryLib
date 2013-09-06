package net.canarymod.api.world.effects;

/**
 * A particle that can be spawned in the world.
 *
 * @author Chris Ksoll
 */
public class Particle {
    public enum Type {
        SNOWBALLSPLASH("snowballspoof"), //
        PORTAL("portal"), //
        WATERSPLASH("splash"), //
        WATERBUBBLES("bubble"), //
        EXPLOSION("hugeexplosion"), //
        FLAME("flame"), //
        HEART("heart"), //
        SMOKE("smoke"), //
        LARGESMOKE("largesmoke"), //
        CRITICALHITSPARK("crit"), //
        MAGICCRITICAL("magicCrit"), //
        NOTE("note"), //
        MAGICRUNES("enchantmenttable"), //
        LAVASPARK("lava"), //
        WATERDRIP("dripWater"), //
        LAVADRIP("dripLava"), //
        SLIMESPLATTER("slime"), //
        REDSTONEFUMES("reddust"), //
        MYCELIUMSPORES("townaura"), //
        FIREWORKSPARK("fireworksSpark"), //
        SUSPENDED("suspended"), //
        MOBSPELL("mobSpell"), //
        MOBSPELLAMBIENT("mobSpellAmbient"), //
        SPELL("spell"), //
        INSTANTSPELL("instantSpell"), //
        WITCHMAGIC("witchMagic"), //
        LAVA("lava"), //
        FOOTSTEP("footstep"), //
        SNOWSHOVEL("snowshovel"), //
        ANGRYVILLAGER("angryVillager"), //
        HAPPYVILLAGER("happyVillager"), //

        ;

        private String mcName;

        Type(String name) {
            mcName = name;
        }

        public String getMcName() {
            return mcName;
        }
    }

    public Type type;
    public double x, y, z, velocityX, velocityY, velocityZ;
    public float speed;
    public int quantity;

    public Particle(double x, double y, double z, Type type) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.type = type;
        velocityX = velocityZ = 0;
        velocityY = 0.5;
    }

    public Particle(double x, double y, double z, double velocityX, double velocityY, double velocityZ, Type type) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.type = type;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
        this.speed = 0.5F;
        this.quantity = 10;
    }

    public Particle(double x, double y, double z, double velocityX, double velocityY, double velocityZ, float speed, int quantity, Type type) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.type = type;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
        this.speed = speed;
        this.quantity = quantity;
    }
}
