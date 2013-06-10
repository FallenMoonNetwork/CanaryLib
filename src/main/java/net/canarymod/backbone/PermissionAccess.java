package net.canarymod.backbone;

import net.canarymod.database.Column;
import net.canarymod.database.Column.ColumnType;
import net.canarymod.database.Column.DataType;
import net.canarymod.database.DataAccess;

public class PermissionAccess extends DataAccess {

    public PermissionAccess() {
        super("permission");
    }

    /**
     * ID for this Permission, serves as Primary Key, Auto Incremented.
     */
    @Column(columnName = "id", dataType = DataType.INTEGER, autoIncrement = true, columnType = ColumnType.PRIMARY)
    public int id;

    /**
     * Node for this Permission.
     */
    @Column(columnName = "path", dataType = DataType.STRING)
    public String path;

    /**
     * Whether or not this permission is true or false.
     */
    @Column(columnName = "value", dataType = DataType.BOOLEAN)
    public boolean value;

    /**
     * The name of the owner of this node (group or player name )
     */
    @Column(columnName = "owner", dataType = DataType.STRING)
    public String owner;

    /**
     * The permission node type (player or group)
     */
    @Column(columnName = "type", dataType = DataType.STRING)
    public String type;

}
