package net.canarymod;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.position.Location;

/**
 * Traces the line of sight of an entity.
 * You can retrieve any blocks along the Line of Sight or simply the last block
 * there is within a specified range. Range defaults to 200 blocks
 *
 * @author Ho0ber
 */
public class LineTracer {
    private Location player_loc;
    private double rot_x, rot_y, view_height;
    private double length, h_length, step;
    private int range;
    private double x_offset, y_offset, z_offset;
    private int last_x, last_y, last_z;
    private int target_x, target_y, target_z;

    /**
     * Constructor requiring player, uses default values
     *
     * @param in_player
     *         the {@link Player} to check Line of Sight for
     */
    public LineTracer(Player in_player) {
        init(in_player.getLocation(), 300, 0.2, 1.65); // Reasonable default values
    }

    /**
     * Constructor requiring location, uses default values
     *
     * @param in_location
     *         the {@link Location} to check Line of Sight for
     */
    public LineTracer(Location in_location) {
        init(in_location, 300, 0.2, 0);
    }

    /**
     * Constructor requiring player, max range, and a stepping value
     *
     * @param in_player
     *         the {@link Player} to check Line of Sight for
     * @param in_range
     *         the maximum range to check
     * @param in_step
     *         the stepping value, the amount Y to increase/decrease the further away the checks get
     */
    public LineTracer(Player in_player, int in_range, double in_step) {
        init(in_player.getLocation(), in_range, in_step, 1.65);
    }

    /**
     * Constructor requiring location, max range, and a stepping value
     *
     * @param in_location
     *         the {@link Location} to check Line of Sight for
     * @param in_range
     *         the maximum range to check
     * @param in_step
     *         the stepping value, the amount Y to increase/decrease the further away the checks get
     */
    public LineTracer(Location in_location, int in_range, double in_step) {
        init(in_location, in_range, in_step, 0);
    }

    /**
     * Initialization method
     *
     * @param in_location
     *         the {@link Location} to check Line of Sight for
     * @param in_range
     *         the maximum range to check
     * @param in_step
     *         the stepping value, the amount Y to increase/decrease the further away the checks get
     * @param in_view_height
     *         the View Height to use, a {@link Player}'s view height is typically 1.62
     */
    public void init(Location in_location, int in_range, double in_step, double in_view_height) {
        player_loc = in_location;
        view_height = in_view_height;
        range = in_range;
        step = in_step;
        length = 0;
        rot_x = (player_loc.getRotation() + 90) % 360;
        rot_y = player_loc.getPitch() * -1;

        target_x = ToolBox.floorToBlock(player_loc.getX());
        target_y = ToolBox.floorToBlock(player_loc.getY() + view_height);
        target_z = ToolBox.floorToBlock(player_loc.getZ());
        last_x = target_x;
        last_y = target_y;
        last_z = target_z;
    }

    /**
     * Returns the block at the cursor, or null if out of range
     *
     * @return the Target {@link Block}
     */
    public Block getTargetBlock() {
        while ((getNextBlock() != null) && (getCurBlock().getTypeId() == 0)) {
            ;
        }
        return getCurBlock();
    }

    /**
     * Returns the block in the direction of the cursor, ignoring certain block types.
     * Null if out of range.
     *
     * @param blockIds
     *         The block id's to ignore.
     *
     * @return the Target Block
     */
    public Block getTargetBlockIgnoring(int... blockIds) {
        blockLoop:
        while (getNextBlock() != null) {
            for (int i : blockIds) {
                if (getCurBlock().getTypeId() == i) {
                    continue blockLoop;
                }
            }
            break;
        }
        return getCurBlock();
    }

    /**
     * Sets the type of the block at the cursor
     *
     * @param type
     *         the {@link Block} type id
     */
    public void setTargetBlock(int type) {
        while ((getNextBlock() != null) && (getCurBlock().getTypeId() == 0)) {
            ;
        }
        if (getCurBlock() != null) {
            player_loc.getWorld().setBlockAt(target_x, target_y, target_z, (short) type);
        }
    }

    /**
     * Returns the block attached to the face at the cursor, or null if out of range
     *
     * @return the face {@link Block}
     */
    public Block getFaceBlock() {
        while ((getNextBlock() != null) && (getCurBlock().getTypeId() == 0)) {
            ;
        }
        if (getCurBlock() != null) {
            return getLastBlock();
        }
        else {
            return null;
        }
    }

    /**
     * Sets the type of the block attached to the face at the cursor
     *
     * @param type
     *         the {@link Block} type id
     */
    public void setFaceBlock(int type) {
        while ((getNextBlock() != null) && (getCurBlock().getTypeId() == 0)) {
            ;
        }
        if (getCurBlock() != null) {
            player_loc.getWorld().setBlockAt(last_x, last_y, last_z, (short) type);
        }
    }

    /**
     * Returns STEPS forward along line of vision and returns block
     *
     * @return the next {@link Block}
     */
    public Block getNextBlock() {
        last_x = target_x;
        last_y = target_y;
        last_z = target_z;

        do {
            length += step;

            h_length = (length * Math.cos(Math.toRadians(rot_y)));
            y_offset = (length * Math.sin(Math.toRadians(rot_y)));
            x_offset = (h_length * Math.cos(Math.toRadians(rot_x)));
            z_offset = (h_length * Math.sin(Math.toRadians(rot_x)));

            target_x = ToolBox.floorToBlock(x_offset + player_loc.getX());
            target_y = ToolBox.floorToBlock(y_offset + player_loc.getY() + view_height);
            target_z = ToolBox.floorToBlock(z_offset + player_loc.getZ());

        }
        while ((length <= range) && ((target_x == last_x) && (target_y == last_y) && (target_z == last_z)));

        if (length > range) {
            return null;
        }
        return player_loc.getWorld().getBlockAt(target_x, target_y, target_z);
    }

    /**
     * Returns the current {@link Block} along the line of vision
     *
     * @return the current {@link Block}
     */
    public Block getCurBlock() {
        if (length > range) {
            return null;
        }
        else {
            return player_loc.getWorld().getBlockAt(target_x, target_y, target_z);
        }
    }

    /**
     * Sets current block type id
     *
     * @param type
     *         the {@link Block} type id
     */
    public void setCurBlock(int type) {
        if (getCurBlock() != null) {
            player_loc.getWorld().setBlockAt(target_x, target_y, target_z, (short) type);
        }
    }

    /**
     * Returns the previous block along the line of vision
     *
     * @return the last {@link Block}
     */
    public Block getLastBlock() {
        return player_loc.getWorld().getBlockAt(last_x, last_y, last_z);
    }

    /**
     * Sets previous block type id
     *
     * @param type
     *         the {@link Block} type id
     */
    public void setLastBlock(int type) {
        if (getLastBlock() != null) {
            player_loc.getWorld().setBlockAt(last_x, last_y, last_z, (short) type);
        }
    }
}
