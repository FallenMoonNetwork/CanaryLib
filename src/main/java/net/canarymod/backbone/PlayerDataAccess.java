package net.canarymod.backbone;

import java.util.ArrayList;

import net.canarymod.database.Column;
import net.canarymod.database.Column.ColumnType;
import net.canarymod.database.Column.DataType;
import net.canarymod.database.DataAccess;

/**
 * Player Data Access
 *
 * @author Chris (damagefilter)
 */
public class PlayerDataAccess extends DataAccess {

    public PlayerDataAccess() {
        super("player");
    }

    /** ID for this player, serves as Primary Key, Auto Incremented. */
    @Column(columnName = "id", dataType = DataType.INTEGER, autoIncrement = true, columnType = ColumnType.PRIMARY)
    public int id;

    /** name of the player. */
    @Column(columnName = "name", dataType = DataType.STRING)
    public String name;

    /** Player prefix. */
    @Column(columnName = "prefix", dataType = DataType.STRING)
    public String prefix;

    /** Player group. */
    @Column(columnName = "group", dataType = DataType.STRING)
    public String group;

    /** Is this player muted? */
    @Column(columnName = "isMuted", dataType = DataType.BOOLEAN)
    public boolean isMuted = false;

    /**
     * A list of additional sub groups.
     * Permissions will be solved according to first to come, first to rule.
     * Unless specified otherwise, a players color will be the one of the main group
     */
    @Column(columnName = "subgroups", dataType = DataType.STRING, isList = true)
    public ArrayList<String> subgroups;

    @Override
    public DataAccess getInstance() {
        return new PlayerDataAccess();
    }
}
