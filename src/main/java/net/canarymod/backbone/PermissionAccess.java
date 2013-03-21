package net.canarymod.backbone;


import net.canarymod.database.Column;
import net.canarymod.database.DataAccess;
import net.canarymod.database.Column.ColumnType;
import net.canarymod.database.Column.DataType;

public class PermissionAccess extends DataAccess {

    public PermissionAccess() {
        super("permission");
    }
    
    @Column(columnName = "id", dataType = DataType.INTEGER, autoIncrement = true, columnType = ColumnType.PRIMARY)
    public int id;
    
    @Column(columnName = "path", dataType = DataType.STRING)
    public String path;
    
    @Column(columnName = "value", dataType = DataType.BOOLEAN)
    public boolean value;
    
    @Column(columnName = "owner", dataType = DataType.STRING)
    public String owner; //The name of the owner of this node (group or player name)
    
    @Column(columnName = "type", dataType = DataType.STRING)
    public String type; //The permission node type (player or group)
    
}
