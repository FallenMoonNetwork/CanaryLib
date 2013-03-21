package net.canarymod.backbone;

import java.util.List;

import net.canarymod.database.Column;
import net.canarymod.database.DataAccess;
import net.canarymod.database.Column.ColumnType;
import net.canarymod.database.Column.DataType;

public class WarpDataAccess extends DataAccess {

    public WarpDataAccess() {
        super("warp");
    }

    @Column(columnName = "id", dataType = DataType.INTEGER, autoIncrement = true, columnType = ColumnType.PRIMARY)
    public int id;

    @Column(columnName = "name", dataType = DataType.STRING)
    public String name;

    @Column(columnName = "isPlayerHome", dataType = DataType.BOOLEAN)
    public boolean isPlayerHome;

    @Column(columnName = "owner", dataType = DataType.STRING)
    public String owner;

    @Column(columnName = "groups", dataType = DataType.STRING, isList = true)
    public List<String> groups;

    @Column(columnName = "location", dataType = DataType.STRING)
    public String location; //serialised location
}
