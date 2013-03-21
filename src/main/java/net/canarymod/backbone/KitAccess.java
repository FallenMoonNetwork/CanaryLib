package net.canarymod.backbone;

import java.util.List;

import net.canarymod.database.Column;
import net.canarymod.database.DataAccess;
import net.canarymod.database.Column.ColumnType;
import net.canarymod.database.Column.DataType;

public class KitAccess extends DataAccess {

    public KitAccess() {
        super("kit");
    }
    
    @Column(columnName = "id", dataType = DataType.INTEGER, autoIncrement = true, columnType = ColumnType.PRIMARY)
    public int id;
    
    @Column(columnName = "useDelay", dataType = DataType.INTEGER)
    public int useDelay;
    
    @Column(columnName = "owners", dataType = DataType.STRING, isList = true)
    public List<String> owners;
    
    @Column(columnName = "groups", dataType = DataType.STRING, isList = true)
    public List<String> groups;
    
    @Column(columnName = "name", dataType = DataType.STRING)
    public String name;
    
    @Column(columnName = "items", dataType = DataType.STRING, isList = true)
    public List<String> items;
}
