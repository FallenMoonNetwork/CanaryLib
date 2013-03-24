package net.canarymod.api;


/**
 * A particle that can be spawned in the world.
 * @author Chris Ksoll
 *
 */
public class Particle {
    public enum Type {
        SNOWBALLSPLASH("snowballspoof"), PORTAL("portal"), WATERSPLASH("splash"), WATERBUBBLES("bubble"), EXPLOSION("hugeexplosion"), FLAME("flame"), HEART("heart");
        
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
    }
}
