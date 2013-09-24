package net.canarymod.api.world.position;

import net.canarymod.ToolBox;

/**
 * Position is a x, y, z triple
 *
 * @author Chris (damagefilter)
 */
public class Position {
    protected double x, y, z;

    public Position(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Position(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Position(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Position() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    /**
     * Copy constructor copies the primitives
     *
     * @param templ
     */
    public Position(Position templ) {
        this.x = templ.x;
        this.y = templ.y;
        this.z = templ.z;
    }

    /**
     * Retrieve X component of Vector
     *
     * @return double x
     */
    public double getX() {
        return x;
    }

    /**
     * Returns a reliable block ordinate
     *
     * @return
     */
    public int getBlockX() {
        return ToolBox.floorToBlock(x);
    }

    /**
     * Set x component with native double
     *
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Set x component with a int2double conversion
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Retrieve Y component of Vector
     *
     * @return double y
     */
    public double getY() {
        return y;
    }

    /**
     * Returns a reliable block ordinate
     *
     * @return
     */
    public int getBlockY() {
        return ToolBox.floorToBlock(y);
    }

    /**
     * Set y component with native double
     *
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Set y component with a int2double conversion
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Retrieve Z component of Vector
     *
     * @return double z
     */
    public double getZ() {
        return z;
    }

    /**
     * Returns a reliable block ordinate
     *
     * @return
     */
    public int getBlockZ() {
        return ToolBox.floorToBlock(z);
    }

    /**
     * Set y component with native double
     *
     * @param z
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * Set z component with a int2double conversion
     *
     * @param z
     */
    public void setZ(int z) {
        this.z = z;
    }

    /**
     * Checks if another object equals this one
     *
     * @param obj
     *
     * @return whether the other object has the same values for x,y,z
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position)) {
            return false;
        }
        Position other = (Position) obj;

        return other.getX() == this.x && other.getY() == this.y && other.getZ() == this.z;

    }

    /** Return a hashcode for this object */
    @Override
    public int hashCode() {
        int hash = 3;

        hash = (int) (hash + x);
        hash = (int) (hash + y);
        hash = (int) (hash + z);
        return hash;
    }

    public String toString() {
        StringBuilder format = new StringBuilder();

        format.append(this.x).append(":").append(this.y).append(":").append(this.z);
        return format.toString();
    }
}
