package net.canarymod.backbone;


import net.canarymod.database.Column;
import net.canarymod.database.DataAccess;
import net.canarymod.database.Column.ColumnType;
import net.canarymod.database.Column.DataType;

public class GroupAccess extends DataAccess {

    public GroupAccess() {
        super("group");
    }
    @Column(columnName = "id", dataType = DataType.INTEGER, autoIncrement = true, columnType = ColumnType.PRIMARY)
    public int id;
    
    @Column(columnName = "name", dataType = DataType.STRING)
    public String name;
    
    @Column(columnName = "prefix", dataType = DataType.STRING)
    public String prefix;
    
    @Column(columnName = "parent", dataType = DataType.STRING)
    public String parent;
    
    @Column(columnName = "isDefault", dataType = DataType.BOOLEAN)
    public boolean isDefault;
}
