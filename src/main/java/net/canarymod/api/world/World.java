package net.canarymod.api.world;

import java.util.ArrayList;

import net.canarymod.api.EntityTracker;
import net.canarymod.api.entity.Player;

/**
 * This is a container for all of the dimensions containing a world
 * 
 * @author Chris Ksoll
 * @author Jos Kuijpers
 * 
 */
public interface World {
    
    public enum Difficulty {
        PEACEFUL(0),
        EASY(1),
        NORMAL(2),
        HARD(3);
        
        private int id;
        
        Difficulty(int id) {
            this.id = id;
        }
        
        public int getId() {
            return id;
        }
        
        public static Difficulty fromId(int id) {
            switch(id) {
            case 0:
                return PEACEFUL;
            case 1:
                return EASY;
            case 3:
                return HARD;
            default:
                return NORMAL;
            }
        }
    }
    
    public enum GameMode {
        SURVIVAL(0),
        CREATIVE(1);
        //ADVENTURE(2);
        
        private int id;
        
        GameMode(int id) {
            this.id = id;
        }
        
        public int getId() {
            return id;
        }
        
        public static GameMode fromId(int id) {
            switch(id) {
            case 1:
                return CREATIVE;
//            case 2:
//                return ADVENTURE;
            default:
                return SURVIVAL;
            }
        }
    }
    
    public enum Type {
        DEFAULT("DEFAULT"),
        SUPERFLAT("FLAT");
        //LARGEBIOMES();
        
        private String string;
        
        Type(String string) {
            this.string = string;
        }
        
        public String toString() {
            return string;
        }
        
        public static Type fromString(String string) {
            if(string.equalsIgnoreCase("FLAT")) {
                return SUPERFLAT;
            }
            else {
                return DEFAULT;
            }
        }
    }
    
    /**
     * Return this worlds name (its the same for the dimensions)
     * 
     * @return
     */
    public String getName();

    /**
     * Get normal dimension
     * 
     * @return
     */
    public Dimension getNormal();

    /**
     * Get nether dimension
     * 
     * @return
     */
    public Dimension getNether();

    /**
     * Get End Dimension
     * 
     * @return
     */
    public Dimension getEnd();

    /**
     * Get one of the dimensions
     * 
     * @return
     */
    public Dimension getDimension(Dimension.Type dimension);

    /**
     * Set a nano tick for this dimension
     * 
     * @param dimensionIndex
     *            (0=normal,1=nether,2=end)
     * @param tickIndex
     *            the Index for the tick (this.j/100% in notch code)
     * @param tick
     */
    public void setNanoTick(Dimension.Type dimension, int tickIndex, long tick);

    /**
     * Enable or disable this world
     * 
     * By default, a newly created world is enabled
     * 
     * @param enabled
     */
    public void setEnabled(boolean enabled);

    /**
     * Whether this world is enabled. A disabled world can't be accessed by
     * anyone, and might not be loaded at all.
     * 
     * @return
     */
    public boolean isEnabled();
    
    /**
     * Returns all players in all dimensions from this world
     * @return
     */
    public ArrayList<Player> getAllPlayers();

    /**
     * Whether the specified player is allowed to enter this world
     * 
     * @param player
     * @return true if the player is allowed
     */
    public boolean canEnterWorld(Player player);

    /**
     * Whether the specified player is allowed to leave this world
     * 
     * @param player
     * @return true if the player is allowed
     */
    public boolean canLeaveWorld(Player player);
    
    /**
     * get the dimensions in the minecraft-classic order: normal, nether, end
     * @return
     */
    public Dimension[] getDimensions();
    
    /**
     * Returns an array of all entity trackers contained in this world.
     * Usually 3 trackers, one for each dimension
     * @return
     */
    public EntityTracker[] getAllEntityTrackers();

}
