package net.canarymod.database.mysql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import net.canarymod.Logman;
import net.canarymod.database.DatabaseRow;
import net.canarymod.database.DatabaseTable;

public class DatabaseRowMySql implements DatabaseRow {

    private HashMap<String, Object> values;
    private DatabaseTableMySql parentTable;
    private int rowId;

    public DatabaseRowMySql(DatabaseTableMySql table, int rowID,
            HashMap<String, Object> values) {
        this.values = values;
        this.rowId = rowID;
        this.parentTable = table;
    }

    private Object getObjectFromColumn(String column) {
        column = column.toUpperCase();
        if (!values.containsKey(column))
            return null;

        return values.get(column);
    }

    @Override
    public int getRowID() {
        return rowId;
    }

    @Override
    public int getNumCells() {
        if (values == null)
            return -1;

        return values.size();
    }

    @Override
    public DatabaseTable getTable() {
        return parentTable;
    }

    @Override
    public String getStringCell(String column) {
        column = column.toUpperCase();
        Object value = getObjectFromColumn(column);

        if(value instanceof String) {
            return (String)value;
        }
        else {
            return value.toString();
        }
    }

    @Override
    public void setStringCell(String column, String value) {
        column = column.toUpperCase();
        try {
            PreparedStatement ps = DatabaseMySql
                    .getStatement("UPDATE ? SET ? = ? WHERE ID = ?");
            ps.setString(1, parentTable.getName());
            ps.setString(2, column);
            if (value == null)
                ps.setNull(3, java.sql.Types.NULL);
            else
                ps.setString(3, value);
            ps.setInt(4, rowId);

            ps.executeQuery();
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while updating row " + rowId
                    + " column " + column + " with value " + value, e);
        }
    }

    @Override
    public Integer getIntCell(String column) {
        column = column.toUpperCase();
        Object value = getObjectFromColumn(column);

        try {
            return (Integer) value;
        } catch (ClassCastException cce) {
            Logman.logStackTrace(
                    "Exception while getting Int value from column " + column,
                    cce);
            return null;
        }
    }

    @Override
    public void setIntCell(String column, Integer value) {
        column = column.toUpperCase();
        try {
            PreparedStatement ps = DatabaseMySql
                    .getStatement("UPDATE ? SET ? = ? WHERE ID = ?");
            ps.setString(1, parentTable.getName());
            ps.setString(2, column);
            if (value == null)
                ps.setNull(3, java.sql.Types.NULL);
            else
                ps.setInt(3, value);
            ps.setInt(4, rowId);

            ps.executeQuery();
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while updating row " + rowId
                    + " column " + column + " with value " + value, e);
        }
    }

    @Override
    public Float getFloatCell(String column) {
        column = column.toUpperCase();
        Object value = getObjectFromColumn(column);

        try {
            return (Float) value;
        } catch (ClassCastException cce) {
            Logman.logStackTrace(
                    "Exception while getting Float value from column " + column,
                    cce);
            return null;
        }
    }

    @Override
    public void setFloatCell(String column, Float value) {
        column = column.toUpperCase();
        try {
            PreparedStatement ps = DatabaseMySql
                    .getStatement("UPDATE ? SET ? = ? WHERE ID = ?");
            ps.setString(1, parentTable.getName());
            ps.setString(2, column);
            if (value == null)
                ps.setNull(3, java.sql.Types.NULL);
            else
                ps.setFloat(3, value);
            ps.setInt(4, rowId);

            ps.executeQuery();
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while updating row " + rowId
                    + " column " + column + " with value " + value, e);
        }
    }

    @Override
    public Double getDoubleCell(String column) {
        column = column.toUpperCase();
        Object value = getObjectFromColumn(column);

        try {
            return (Double) value;
        } catch (ClassCastException cce) {
            Logman.logStackTrace(
                    "Exception while getting Double value from column "
                            + column, cce);
            return null;
        }
    }

    @Override
    public void setDoubleCell(String column, Double value) {
        column = column.toUpperCase();
        try {
            PreparedStatement ps = DatabaseMySql
                    .getStatement("UPDATE ? SET ? = ? WHERE ID = ?");
            ps.setString(1, parentTable.getName());
            ps.setString(2, column);
            if (value == null)
                ps.setNull(3, java.sql.Types.NULL);
            else
                ps.setDouble(3, value);
            ps.setInt(4, rowId);

            ps.executeQuery();
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while updating row " + rowId
                    + " column " + column + " with value " + value, e);
        }
    }

    @Override
    public Boolean getBooleanCell(String column) {
        column = column.toUpperCase();
        Object value = getObjectFromColumn(column);

        try {
            return (Boolean) value;
        } catch (ClassCastException cce) {
            Logman.logStackTrace(
                    "Exception while getting Boolean value from column "
                            + column, cce);
            return null;
        }
    }

    public boolean getBooleanCell(String column, Boolean defaults) {
        column = column.toUpperCase();
        Boolean result = getBooleanCell(column);
        return result != null ? result : defaults;
    }

    @Override
    public void setBooleanCell(String column, Boolean value) {
        column = column.toUpperCase();
        try {
            PreparedStatement ps = DatabaseMySql
                    .getStatement("UPDATE ? SET ? = ? WHERE ID = ?");
            ps.setString(1, parentTable.getName());
            ps.setString(2, column);
            if (value == null)
                ps.setNull(3, java.sql.Types.NULL);
            else
                ps.setBoolean(3, value);
            ps.setInt(4, rowId);

            ps.executeQuery();
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while updating row " + rowId
                    + " column " + column + " with value " + value, e);
        }
    }
    
    @Override
    public Object getObjectCell(String column) {
        column = column.toUpperCase();
        Object value = getObjectFromColumn(column);
        return value;
    }

    @Override
    public Long getLongCell(String column) {
        column = column.toUpperCase();
        Object value = getObjectFromColumn(column);

        try {
            return (Long) value;
        } catch (ClassCastException cce) {
            Logman.logStackTrace(
                    "Exception while getting Long value from column " + column,
                    cce);
            return null;
        }
    }

    @Override
    public void setLongCell(String column, Long value) {
        column = column.toUpperCase();
        try {
            PreparedStatement ps = DatabaseMySql
                    .getStatement("UPDATE ? SET ? = ? WHERE ID = ?");
            ps.setString(1, parentTable.getName());
            ps.setString(2, column);
            if (value == null)
                ps.setNull(3, java.sql.Types.NULL);
            else
                ps.setLong(3, value);
            ps.setInt(4, rowId);

            ps.executeQuery();
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while updating row " + rowId
                    + " column " + column + " with value " + value, e);
        }
    }

    @Override
    public Character getCharacter(String column) {
        column = column.toUpperCase();
        Object value = getObjectFromColumn(column);

        try {
            return (Character) value;
        } catch (ClassCastException cce) {
            Logman.logStackTrace(
                    "Exception while getting Long value from column " + column,
                    cce);
            return null;
        }
    }

    @Override
    public void setCharacter(String column, Character value) {
        column = column.toUpperCase();
        try {
            PreparedStatement ps = DatabaseMySql
                    .getStatement("UPDATE ? SET ? = ? WHERE ID = ?");
            ps.setString(1, parentTable.getName());
            ps.setString(2, column);
            if (value == null)
                ps.setNull(3, java.sql.Types.NULL);
            else
                ps.setString(3, value.toString());
            ps.setInt(4, rowId);

            ps.executeQuery();
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while updating row " + rowId
                    + " column " + column + " with value " + value, e);
        }
    }

}
