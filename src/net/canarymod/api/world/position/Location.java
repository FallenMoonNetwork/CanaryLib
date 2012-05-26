package net.canarymod.api.world.position;

import net.canarymod.Canary;
import net.canarymod.api.world.Dimension;

/**
 * A Location represents a point in the world including pitch and rotation headings.
 * @author Chris Ksoll
 *
 */
public class Location extends Vector3D {

    private int dimension;
    private String world;
    private float pitch, rotation;

    public Location(Dimension world, double x, double y, double z, float pitch, float rotation) {
        super(x, y, z);
        dimension = world.getType().getId();
        this.world = world.getWorld().getName();
        this.pitch = pitch;
        this.rotation = rotation;
    }
    
    public Location(double x, double y, double z) {
        super(x, y, z);
        world = Canary.getServer().getDefaultWorldName();
        dimension = 0;
        pitch = rotation = 0f;
    }
    /**
     * @return the rotation
     */
    public float getRotation() {
        return rotation;
    }

    /**
     * @param rotation
     *            the rotation to set
     */
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    /**
     * @return the pitch
     */
    public float getPitch() {
        return pitch;
    }

    /**
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
    public int getDimension() {
        return dimension;
    }

    /**
     * @param dimension
     *            the dimension to set
     */
    public void setDimension(int dimension) {
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

            return ((l.x == x) && (l.y == y) && (l.z == z) && (l.dimension == dimension) && (l.pitch == pitch) && (l.rotation == rotation));
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
    public String getWorld() {
        return world;
    }

    /**
     * @param world the world to set
     */
    public void setWorld(String world) {
        this.world = world;
    }

}
