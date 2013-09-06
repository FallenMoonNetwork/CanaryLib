package net.canarymod.api.world.position;

/**
 * A Vector3D represents a point ins in the 3D space.
 * That can be a block or a player coodinate
 *
 * @author chris
 */
public class Vector3D extends Position {
    /** This is the nullvector (0,0,0) */
    public static final Vector3D zero = new Vector3D(0, 0, 0);

    /** Shortcut to Vector3D(0,0,1) */
    public static final Vector3D forward = new Vector3D(0, 0, 1);

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    /**
     * Copy constructor copies the primitives
     *
     * @param key
     */
    public Vector3D(Vector3D templ) {
        this.x = templ.x;
        this.y = templ.y;
        this.z = templ.z;
    }

    /**
     * Get the distance between this and the given vector
     *
     * @param v
     *
     * @return
     */
    public double getDistance(Position v) {
        double distPower = (Math.pow(v.getX() - this.getX(), 2) + Math.pow(v.getY() - this.getY(), 2) + Math.pow(v.getZ() - this.getZ(), 2));

        return Math.sqrt(distPower);
    }

    /**
     * Retrieve the distance between 2 given vectors
     *
     * @param v
     *
     * @return double The Distance
     */
    public static double getDistance(Vector3D v1, Vector3D v2) {
        double distPower = (Math.pow(v1.getX() - v2.getX(), 2) + Math.pow(v1.getY() - v2.getY(), 2) + Math.pow(v1.getZ() - v2.getZ(), 2));

        return Math.sqrt(distPower);
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
        if (!(obj instanceof Vector3D)) {
            return false;
        }
        Vector3D other = (Vector3D) obj;

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

    @Override
    public String toString() {
        StringBuilder format = new StringBuilder();

        format.append(this.x).append(":").append(this.y).append(":").append(this.z);
        return format.toString();
    }

    /**
     * Add the given Vector to this Vector and return the result as new Vector3D
     *
     * @param toAdd
     *
     * @return Vector3D result of addition
     */
    public Vector3D add(Vector3D toAdd) {
        return new Vector3D(this.x + toAdd.x, this.y + toAdd.y, this.z + toAdd.z);
    }

    /**
     * Subtract the given Vector from this Vector and return the result as new Vector3D
     *
     * @param toRemove
     *
     * @return Vector3D result of subtraction
     */
    public Vector3D subtract(Vector3D toRemove) {
        return new Vector3D(this.x - toRemove.x, this.y - toRemove.y, this.z - toRemove.z);
    }

    /**
     * Scalar multiply this vector with a given factor and return the result as new Vector3D
     *
     * @param toRemove
     *
     * @return scalar product as Vector3D
     */
    public Vector3D multiply(double scalar) {
        return new Vector3D(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    /**
     * Get the length (or magnitude) of this vector
     *
     * @return
     */
    public double getMagnitude() {
        return Math.sqrt(x + y + z);
    }
}
