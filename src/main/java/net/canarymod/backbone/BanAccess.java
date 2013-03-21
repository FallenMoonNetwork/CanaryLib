package net.canarymod.backbone;

import net.canarymod.database.Column;
import net.canarymod.database.Column.ColumnType;
import net.canarymod.database.DataAccess;
import net.canarymod.database.Column.DataType;

public class BanAccess extends DataAccess {

    public BanAccess() {
        super("ban");
    }
    
    @Column(columnName="id", dataType = DataType.INTEGER, autoIncrement=true, columnType=ColumnType.PRIMARY)
    public int id;
    
    @Column(columnName="player", dataType = DataType.STRING)
    public String player;
    
    @Column(columnName="ip", dataType = DataType.STRING)
    public String ip;
    
    @Column(columnName="reason", dataType = DataType.STRING)
    public String reason;
    
    @Column(columnName="banningPlayer", dataType = DataType.STRING)
    public String banningPlayer;
    
    @Column(columnName="unbanDate", dataType = DataType.LONG)
    public long unbanDate = -1;

}
