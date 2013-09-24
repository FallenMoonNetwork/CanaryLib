package net.canarymod.backbone;

import net.canarymod.database.Column;
import net.canarymod.database.Column.ColumnType;
import net.canarymod.database.Column.DataType;
import net.canarymod.database.DataAccess;

/**
 * Operators Data Access
 *
 * @author Jason (darkdiplomat)
 */
public class OperatorsDataAccess extends DataAccess {

    public OperatorsDataAccess() {
        super("operators");
    }

    /** id for this operator entry */
    @Column(columnName = "id", dataType = DataType.INTEGER, autoIncrement = true, columnType = ColumnType.PRIMARY)
    public int id;

    /** Playername for this operator entry */
    @Column(columnName = "player", dataType = DataType.STRING)
    public String player;

    @Override
    public DataAccess getInstance() {
        return new OperatorsDataAccess();
    }
}
