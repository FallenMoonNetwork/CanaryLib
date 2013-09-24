package net.canarymod.backbone;

import java.util.List;

import net.canarymod.database.Column;
import net.canarymod.database.Column.ColumnType;
import net.canarymod.database.Column.DataType;
import net.canarymod.database.DataAccess;

/**
 * Warp Data Access
 *
 * @author Chris (damagefilter)
 */
public class WarpDataAccess extends DataAccess {

    public WarpDataAccess() {
        super("warp");
    }

    /** ID for this warp, serves as Primary Key, Auto Incremented. */
    @Column(columnName = "id", dataType = DataType.INTEGER, autoIncrement = true, columnType = ColumnType.PRIMARY)
    public int id;

    /** Name of this warp. */
    @Column(columnName = "name", dataType = DataType.STRING)
    public String name;

    /** Is this warp a player home? */
    @Column(columnName = "isPlayerHome", dataType = DataType.BOOLEAN)
    public boolean isPlayerHome;

    /** Owner of this warp. */
    @Column(columnName = "owner", dataType = DataType.STRING)
    public String owner;

    /** groups that can use this warp. */
    @Column(columnName = "groups", dataType = DataType.STRING, isList = true)
    public List<String> groups;

    /** Serialised location of this warp. */
    @Column(columnName = "location", dataType = DataType.STRING)
    public String location; // serialised location

    @Override
    public DataAccess getInstance() {
        return new WarpDataAccess();
    }
}
