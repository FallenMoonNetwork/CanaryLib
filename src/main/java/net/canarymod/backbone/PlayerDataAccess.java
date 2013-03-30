package net.canarymod.backbone;


import net.canarymod.database.Column;
import net.canarymod.database.Column.ColumnType;
import net.canarymod.database.Column.DataType;
import net.canarymod.database.DataAccess;


public class PlayerDataAccess extends DataAccess {

    public PlayerDataAccess() {
        super("player");
    }

    /**
     * ID for this player, serves as Primary Key, Auto Incremented.
     */
    @Column(columnName = "id", dataType = DataType.INTEGER, autoIncrement = true, columnType = ColumnType.PRIMARY)
    public int id;

    /**
     * name of the player.
     */
    @Column(columnName = "name", dataType = DataType.STRING)
    public String name;

    /**
     * Player prefix.
     */
    @Column(columnName = "prefix", dataType = DataType.STRING)
    public String prefix;

    /**
     * Player group.
     */
    @Column(columnName = "group", dataType = DataType.STRING)
    public String group;

    /**
     * Is this player muted?
     */
    @Column(columnName = "isMuted", dataType = DataType.BOOLEAN)
    public boolean isMuted = false;
}
