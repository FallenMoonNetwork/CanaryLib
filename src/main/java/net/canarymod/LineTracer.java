package net.canarymod;

import net.canarymod.api.entity.Entity;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Vector3D;

/**
 * Traces the line of sight of an entity.
 * You can retrieve any blocks along the LOS or simply the last block
 * there is within a specified range. Range defaults to 200 blocks
 * @author Chris Ksoll
 *
 */
public class LineTracer {
    private Location entityLocation;
    private double eyeHeight, length, rotation, stepping;
    private int range;
    private Vector3D offset, lastPosition, target;
    
    public LineTracer(Location location, int range, double stepping) {
        init(location, range, stepping, 0);
    }
    
    public LineTracer(Entity entity, int range, double stepping) {
        init(entity.getLocation(), range, stepping, 1.65);
    }
    
    
    public LineTracer(Entity entity) {
        init(entity.getLocation(), 200, 0.2, 1.65);
    }
    
    public LineTracer(Location location) {
        init(location, 200, 0.2, 0);
    }
    
    /**
     * Initialize the tracer
     * @param location
     * @param range
     * @param step
     * @param eyeHeight
     */
    private void init(Location location, int range, double stepping, double eyeHeight) {
        entityLocation = location;
        this.eyeHeight = eyeHeight;
        this.range = range;
        this.stepping = stepping;
        length = 0;

        target = new Vector3D(location.getX(), location.getY(), location.getZ());
        lastPosition = new Vector3D(location.getX(), location.getY(), location.getZ());
    }
    
    /**
     * Returns the block at the cursor, or null if out of range
     * 
     * @return Block
     */
    public Block getTargetBlock() {
        while ((getNextBlock() != null) && (getCurrentBlock().getType() == 0)) {
            ;
        }
        return getCurrentBlock();
    }

    /**
     * Sets the type of the block at the cursor
     * 
     * @param type
     */
    public void setTargetBlock(int type) {
        while ((getNextBlock() != null) && (getCurrentBlock().getType() == 0)) {
            ;
        }
        if (getCurrentBlock() != null) {
            entityLocation.getWorld().setBlockAt(target, (short)type);
        }
    }

    /**
     * Returns the block attached to the face at the cursor, or null if out of
     * range
     * 
     * @return Block
     */
    public Block getFaceBlock() {
        while ((getNextBlock() != null) && (getCurrentBlock().getType() == 0)) {
            ;
        }
        if (getCurrentBlock() != null) {
            return getLastBlock();
        } else {
            return null;
        }
    }

    /**
     * Sets the type of the block attached to the face at the cursor
     * 
     * @param type
     */
    public void setFaceBlock(int type) {
        while ((getNextBlock() != null) && (getCurrentBlock().getType() == 0)) {
            ;
        }
        if (getCurrentBlock() != null) {
            entityLocation.getWorld().setBlockAt(lastPosition, (short)type);
        }
    }

    /**
     * Returns STEPS forward along line of vision and returns block
     * 
     * @return Block
     */
    public Block getNextBlock() {

        lastPosition = new Vector3D(target.getX(), target.getY(), target.getZ());

        do {
            length += stepping;

            rotation = (length * Math.cos(Math.toRadians(entityLocation.getRotation()))); //y
            double y_offset = (length * Math.sin(Math.toRadians(entityLocation.getRotation()))); //y
            double x_offset = (rotation * Math.cos(Math.toRadians(entityLocation.getPitch()))); //x
            double z_offset = (rotation * Math.sin(Math.toRadians(entityLocation.getPitch())));
            offset = new Vector3D(x_offset, y_offset, z_offset);

            target = new Vector3D(offset.getX(), offset.getY()+eyeHeight, offset.getZ());

        } while ((length <= range) && ((target.getX() == lastPosition.getX()) && (target.getY() == lastPosition.getY()) && (target.getZ() == lastPosition.getZ())));

        if (length > range) {
            return null;
        }

        return entityLocation.getWorld().getBlockAt(target);
    }
    
    /**
     * Returns the current block along the line of vision
     * 
     * @return Block
     */
    public Block getCurrentBlock() {
        if (length > range) {
            return null;
        } else {
            return entityLocation.getWorld().getBlockAt(target);
        }
    }

    /**
     * Sets current block type id
     * 
     * @param type
     */
    public void setCurrentBlock(int type) {
        entityLocation.getWorld().setBlockAt(target, (short)type);
    }

    /**
     * Returns the previous block along the line of vision
     * 
     * @return Block
     */
    public Block getLastBlock() {
        return entityLocation.getWorld().getBlockAt(lastPosition);
    }

    /**
     * Sets previous block type id
     * 
     * @param type
     */
    public void setLastBlock(int type) {
        entityLocation.getWorld().setBlockAt(lastPosition, (short)type);
    }
}
