package net.canarymod.api.world.position;

import net.canarymod.api.world.Dimension;

public class Location extends Vector3D {

    private Dimension dimension;
    private float pitch, rotation;

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
     * @return the dimension
     */
    public Dimension getDimension() {
        return dimension;
    }

    /**
     * @param dimension
     *            the dimension to set
     */
    public void setDimension(Dimension dimension) {
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

            return ((l.x == x) && (l.y == y) && (l.z == z) && (l.dimension.equals(dimension)) && (l.pitch == pitch) && (l.rotation == rotation));
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

}
