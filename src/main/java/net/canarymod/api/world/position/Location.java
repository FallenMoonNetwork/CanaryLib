package net.canarymod.api.world.position;


import net.canarymod.Canary;
import net.canarymod.CanaryDeserializeException;
import net.canarymod.api.world.World;
import net.canarymod.api.world.WorldType;
import net.canarymod.config.Configuration;


/**
 * A Location represents a point in the world including pitch and rotation headings.
 * @author Chris Ksoll
 *
 */
public class Location extends Position {

    private WorldType dimension;
    private String world;
    private float pitch, rotation;

    public Location(World world, double x, double y, double z, float pitch, float rotation) {
        super(x, y, z);
        dimension = world.getType();
        this.world = world.getName();
        this.pitch = pitch;
        this.rotation = rotation;
    }
    
    public Location(double x, double y, double z) {
        super(x, y, z);
        world = Configuration.getServerConfig().getDefaultWorldName();
        dimension = WorldType.fromName("NORMAL");
        pitch = rotation = 0f;
    }

    /**
     * Get the rotation around the Y axis
     * @return the rotation
     */
    public float getRotation() {
        return rotation;
    }

    /**
     * Set the rotation around the Y axis
     * @param rotation
     *            the rotation to set
     */
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    /**
     * Get the rotation around the X axis
     * @return the pitch
     */
    public float getPitch() {
        return pitch;
    }

    /**
     * Set the rotation around the X axis
     * @param pitch
     *            the pitch to set
     */
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    /**
     * The dimension ID
     * @return the dimension
     */
    public WorldType getType() {
        return dimension;
    }

    /**
     * @param dimension
     *            the dimension to set
     */
    public void setType(WorldType dimension) {
        this.dimension = dimension;
    }

    /**
     * Check if this object is equal to another one
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Location)) {
            return false;
        } else {
            Location l = (Location) other;

            return ((l.x == x) && (l.y == y) && (l.z == z) && (l.dimension == dimension) && (l.pitch == pitch) && (l.rotation == rotation) && (l.world.equals(world)));
        }
    }

    /**
     * Return a hashcode for this object
     */
    @Override
    public int hashCode() {
        int hash = 5;

        hash = (int) (hash + x);
        hash = (int) (hash + y);
        hash = (int) (hash + z);
        hash = (int) (hash + pitch);
        hash = (int) (hash + rotation);
        return hash;
    }

    /**
     * @return the world
     */
    public String getWorldName() {
        return world;
    }

    /**
     * @param world the world to set
     */
    public void setWorldName(String world) {
        this.world = world;
    }
    
    /**
     * Returns the actual world this location belongs to
     * @return
     */
    public World getWorld() {
        return Canary.getServer().getWorldManager().getWorld(world, dimension, false);
    }

    /**
     * Return a String representation that can also be used for storing somewhere
     * for this Location.
     */
    public String toString() {
        StringBuilder fields = new StringBuilder();

        fields.append(this.x).append(";").append(this.y).append(";").append(this.z).append(";").append(this.pitch).append(";").append(this.rotation).append(";").append(this.dimension.getId()).append(";").append(this.world);
        return fields.toString();
    }
    
    /**
     * Turn a String Location that has been formatted by Location.toString() (or has a compatible format) into a Location object
     * @param format
     * @return
     * @throws CanaryDeserializeException
     */
    public static Location fromString(String fields) throws CanaryDeserializeException {
        Location loc = new Location(0, 0, 0);
        String[] split = fields.split(";");

        if (split.length != 7) {
            throw new CanaryDeserializeException("Failed to deserialize Location: Expected fields are 7. Found " + split.length, "CanaryMod");
        }
        try {
            loc.setX(Double.parseDouble(split[0]));
            loc.setY(Double.parseDouble(split[1]));
            loc.setZ(Double.parseDouble(split[2]));
            loc.setPitch(Float.parseFloat(split[3]));
            loc.setRotation(Float.parseFloat(split[4]));
            loc.setType(WorldType.fromId(Integer.parseInt(split[5])));
            loc.setWorldName(split[6]);
            return loc;
        } catch (NumberFormatException e) {
            throw new CanaryDeserializeException("Failed to deserialize Location: " + e.getMessage(), "CanaryMod");
        }
    }

}
