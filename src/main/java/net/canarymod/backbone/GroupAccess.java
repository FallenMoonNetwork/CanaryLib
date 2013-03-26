package net.canarymod.backbone;


import net.canarymod.database.Column;
import net.canarymod.database.Column.ColumnType;
import net.canarymod.database.Column.DataType;
import net.canarymod.database.DataAccess;


public class GroupAccess extends DataAccess {

    public GroupAccess() {
        super("group");
    }

    /**
     * ID for this Group, serves as Primary Key, Auto Incremented.
     */
    @Column(columnName = "id", dataType = DataType.INTEGER, autoIncrement = true, columnType = ColumnType.PRIMARY)
    public int id;

    /**
     * Name of this group.
     */
    @Column(columnName = "name", dataType = DataType.STRING)
    public String name;

    /**
     * Chat prefix for this group.
     */
    @Column(columnName = "prefix", dataType = DataType.STRING)
    public String prefix;

    /**
     * Parent group for this group.
     */
    @Column(columnName = "parent", dataType = DataType.STRING)
    public String parent;

    /**
     * Is this the default group?
     */
    @Column(columnName = "isDefault", dataType = DataType.BOOLEAN)
    public boolean isDefault;
}
