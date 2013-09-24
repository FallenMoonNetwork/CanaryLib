package net.canarymod.backbone;

import net.canarymod.database.Column;
import net.canarymod.database.Column.ColumnType;
import net.canarymod.database.Column.DataType;
import net.canarymod.database.DataAccess;

/**
 * Permission Data Access
 *
 * @author Chris (damagefilter)
 */
public class WhitelistDataAccess extends DataAccess {

    public WhitelistDataAccess() {
        super("whitelist");
    }

    /** id for this whitelist entry */
    @Column(columnName = "id", dataType = DataType.INTEGER, autoIncrement = true, columnType = ColumnType.PRIMARY)
    public int id;

    /** Playername for this whitelist entry */
    @Column(columnName = "player", dataType = DataType.STRING)
    public String player;

    @Override
    public DataAccess getInstance() {
        return new WhitelistDataAccess();
    }
}
