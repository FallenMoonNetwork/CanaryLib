package net.canarymod.database.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

import net.canarymod.Logman;
import net.canarymod.database.DatabaseRow;
import net.canarymod.database.DatabaseTable;

public class DatabaseTableMySql implements DatabaseTable {

    private String tableName;

    public DatabaseTableMySql(String tableName) {
        this.tableName = tableName.toLowerCase();
    }

    @Override
    public String getName() {
        return tableName;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            return;
        }
        
        name = name.toLowerCase();
        try {
            PreparedStatement ps = DatabaseMySql.getStatement("ALTER TABLE "+tableName+" RENAME ?");
            ps.setString(1, name);

            if (ps.execute()) {
                tableName = name;
            }
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while renaming table "+tableName, e);
        }
    }

    @Override
    public String getDescription() {
        try {
            PreparedStatement ps = DatabaseMySql
                    .getStatement("SHOW TABLE STATUS FROM "+tableName);

            ResultSet rs = ps.executeQuery();
            if (rs.first())
                return rs.getString("COMMENT");
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while getting description for table "+tableName, e);
        }
        return null;
    }

    @Override
    public void setDescription(String description) {
        if (description == null) {
            description = "";
        }

        try {
            PreparedStatement ps = DatabaseMySql
                    .getStatement("ALTER TABLE "+tableName+" COMMENT = ?");
            ps.setString(1, description);

            ps.execute();
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while setting description for table "+tableName, e);
        }
    }

    @Override
    public int getNumRows() {
        try {
            PreparedStatement ps = DatabaseMySql
                    .getStatement("SELECT count(*) as numRows FROM "+tableName);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("numRows");
            }
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while getting number of rows from "+tableName, e);
        }
        return -1;
    }

    @Override
    public DatabaseRow getRow(int rowID) {
        if (rowID <= 0) {
            return null;
        }

        try {
            PreparedStatement ps = DatabaseMySql
                    .getStatement("SELECT * FROM "+tableName+" WHERE ID = ?");
            ps.setInt(1, rowID);

            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            HashMap<String, Object> columnValues = new HashMap<String, Object>();

            for (int i = 0; i < rsmd.getColumnCount(); ++i) {
                columnValues.put(rsmd.getColumnName(i+1).toUpperCase(),
                        rs.getObject(i+1));
            }

            return new DatabaseRowMySql(this, rs.getInt("ID"), columnValues);
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while getting DatabaseRow from "+tableName, e);
            return null;
        }
    }

    @Override
    public DatabaseRow[] getAllRows() {
        try {
            PreparedStatement ps = DatabaseMySql
                    .getStatement("SELECT * FROM " + tableName);
//            ps.setString(1, tableName);

            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumns = rsmd.getColumnCount();

            // count Rows
            rs.last();
            int numRows = rs.getRow();
            
            if (numRows <= 0)
                return null;

            rs.beforeFirst();
            DatabaseRow[] result = new DatabaseRow[numRows];
            HashMap<String, Object> columnValues;
            while (rs.next()) {
                columnValues = new HashMap<String, Object>();
                for (int i = 0; i < numColumns; ++i) {
                    columnValues.put(rsmd.getColumnName(i+1).toUpperCase(),
                            rs.getObject(i+1));
                }
                result[rs.getRow() - 1] = new DatabaseRowMySql(this, rs.getInt("ID"), columnValues);
            }

            return result;
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while getting DatabaseRows from "+tableName, e);
            return null;
        }
    }

    @Override
    public DatabaseRow[] getFilteredRows(String column, Object value) {
        if (column == null || column.isEmpty() || value == null) {
            return null;
        }

        column = column.toUpperCase();
        try {
            PreparedStatement ps = DatabaseMySql
                    .getStatement("SELECT * FROM "+tableName+" WHERE "+column+" = ?");
            
            if(value instanceof Integer) {
                ps.setInt(1, (Integer)value);
            }
            else if(value instanceof Double || value instanceof Float) {
                ps.setDouble(1, (Double)value);
            }
            else if(value instanceof String) {
                ps.setString(1, (String)value);
            }
            else if(value instanceof Boolean) {
                ps.setBoolean(1, (Boolean)value);
            }

            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumns = rsmd.getColumnCount();

            // count Rows
            rs.last();
            int numRows = rs.getRow();
            
            if (numRows <= 0)
                return null;

            rs.beforeFirst();
            DatabaseRow[] result = new DatabaseRow[numRows];
            HashMap<String, Object> columnValues;
            while (rs.next()) {
                columnValues = new HashMap<String, Object>();
                for (int i = 0; i < numColumns; ++i) {
                    columnValues.put(rsmd.getColumnName(i+1).toUpperCase(),
                            rs.getObject(i+1));
                }
                result[rs.getRow() - 1] = new DatabaseRowMySql(this, rs.getInt("ID"), columnValues);
            }

            return result;
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while getting filtered DatabaseRows from "+tableName, e);
            return null;
        }
    }

    @Override
    public boolean rowExists(String column, String value) {
        if (column == null || column.isEmpty() || value == null
                || value.isEmpty()) {
            return false;
        }

        column = column.toUpperCase();
        try {
            PreparedStatement ps = DatabaseMySql
                    .getStatement("SELECT ID FROM "+tableName+" WHERE "+column+" = ? LIMIT 1");

            try {
                ps.setInt(1, Integer.parseInt(value));
            } catch (Exception ex) {
                try {
                    ps.setDouble(1, Double.parseDouble(value));
                } catch (Exception iex) {
                    ps.setString(1, value);
                }
            }

            ResultSet rs = ps.executeQuery();
            return rs.first();
        } catch (SQLException ex) {
            Logman.logStackTrace("Exception while checking if rows exsists in "+tableName+". It probably doesn't then ...", ex);
            return false;
        }
    }

    @Override
    public DatabaseRow addRow() {
        try {
            String[] availableColumns = getAllColumns();
            if (availableColumns == null || availableColumns.length  <= 1)
                return null;
            StringBuilder cols = new StringBuilder();
            StringBuilder nulls = new StringBuilder();
            for(String str : availableColumns) {
                if(!str.equalsIgnoreCase("ID")) {
                    cols.append(str).append(",");
                    nulls.append("NULL").append(",");
                }
            }
            if(cols.length() > 0) {
                cols.deleteCharAt(cols.length() - 1);
                nulls.deleteCharAt(nulls.length() - 1);
            }
            
            PreparedStatement ps = DatabaseMySql.getStatement("INSERT INTO "+tableName+" ("+cols.toString()+") VALUES ("+nulls.toString()+")");
            ps.executeUpdate();

            HashMap<String, Object> columnValues = new HashMap<String, Object>();

            ResultSet rs = ps.getGeneratedKeys();
            int lastId = -1;
            if(rs.next()) {
                lastId = rs.getInt(1);
            }
            if(lastId < 0) {
                Logman.logSevere("Nothing was inserted! returning null row!");
                return null;
            }
            for (String col : availableColumns) {
                columnValues.put(col.toUpperCase(), null);
            }

            return new DatabaseRowMySql(this, lastId, columnValues);
        } catch (SQLException ex) {
            Logman.logStackTrace("Exception while creating new DatabaseRow in "+tableName, ex);
            return null;
        }
    }

    @Override
    public void removeRow(DatabaseRow row) {
        removeRow(row.getRowID());
    }

    @Override
    public void removeRow(int rowID) {
        if (rowID <= 0) {
            return;
        }

        try {
            PreparedStatement ps = DatabaseMySql.getStatement("DELETE FROM "+tableName+" WHERE ID = ?");
            ps.setInt(1, rowID);

            ps.executeQuery();
        } catch (SQLException ex) {
            Logman.logStackTrace("Exception while removing row after index in "+tableName, ex);
        }
    }

    @Override
    public int getNumColumns() {
        try {
            PreparedStatement ps = DatabaseMySql
                    .getStatement("SELECT * FROM "+tableName+" LIMIT 1");
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            return rsmd.getColumnCount();
        } catch (SQLException ex) {
            Logman.logStackTrace("Exception while getNumColumns in "+tableName, ex);
            return -1;
        }
    }

    @Override
    public String[] getAllColumns() {
        try {
            PreparedStatement ps = DatabaseMySql
                    .getStatement("SELECT * FROM "+tableName+" LIMIT 1");
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumns = rsmd.getColumnCount();
            
            if (numColumns <= 0)
                return new String[0];

            String[] result = new String[numColumns];
            for (int i = 0; i < numColumns; ++i) {
                result[i] = rsmd.getColumnName(i+1).toUpperCase();
            }

            return result;
        } catch (SQLException ex) {
            Logman.logStackTrace("Exception while getting column names in "+tableName, ex);
            return new String[0];
        }
    }

    @Override
    public void appendColumn(String name, ColumnType type) {
        if (name == null || name.isEmpty())
            return;
        
        name = name.toUpperCase();
        try {
            String strType = "";

            if (type == ColumnType.BOOLEAN)
                strType = "TINYINT";
            else if (type == ColumnType.INTEGER)
                strType = "INTEGER";
            else if (type == ColumnType.LONG)
                strType = "BIGINT";
            else if (type == ColumnType.FLOAT)
                strType = "FLOAT";
            else if (type == ColumnType.DOUBLE)
                strType = "DOUBLE";
            else if (type == ColumnType.CHARACTER)
                strType = "CHAR";
            else if (type == ColumnType.STRING)
                strType = "TEXT";
            PreparedStatement ps = DatabaseMySql
                    .getStatement("ALTER TABLE "+tableName+" ADD `"+name+"` " + strType
                            + " NULL");
            ps.execute();
        } catch (SQLException ex) {
            Logman.logStackTrace("Exception while appending column to "+tableName, ex);
        }
    }

    @Override
    public void removeColumn(String name) {
        if (name == null || name.isEmpty() || name.toUpperCase() == "ID")
            return;
        
        name = name.toUpperCase();
        try {
            PreparedStatement ps = DatabaseMySql
                    .getStatement("ALTER TABLE "+tableName+" DROP COLUMN "+name);

            ps.executeQuery();
        } catch (SQLException ex) {
            Logman.logStackTrace("Exception while dropping column to "+tableName, ex);
        }
    }

    @Override
    public void insertColumn(String name, ColumnType type, String after) {
        if (name == null || name.isEmpty() || after == null || after.isEmpty())
            return;
        
        name = name.toUpperCase();
        after = after.toUpperCase();
        try {
            String strType = "";

            if (type == ColumnType.BOOLEAN)
                strType = "TINYINT";
            else if (type == ColumnType.INTEGER)
                strType = "INTEGER";
            else if (type == ColumnType.LONG)
                strType = "BIGINT";
            else if (type == ColumnType.FLOAT)
                strType = "FLOAT";
            else if (type == ColumnType.DOUBLE)
                strType = "DOUBLE";
            else if (type == ColumnType.CHARACTER)
                strType = "CHAR";
            else if (type == ColumnType.STRING)
                strType = "VARCHAR(max)";

            PreparedStatement ps = DatabaseMySql
                    .getStatement("ALTER TABLE "+tableName+" ADD "+ name + " " + strType
                            + " NULL AFTER "+after);
            ps.execute();
        } catch (SQLException ex) {
            Logman.logStackTrace("Exception while inserting column to "+tableName, ex);
        }
    }

    @Override
    public void truncateTable() {
        try {
            PreparedStatement ps = DatabaseMySql
                    .getStatement("TRUNCATE TABLE " + tableName);
            ps.executeQuery();
        } catch (SQLException ex) {
            Logman.logStackTrace("Exception while truncating "+tableName, ex);
        }
    }
}
