package net.canarymod.database.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

import net.canarymod.Logman;
import net.canarymod.config.Configuration;
import net.canarymod.database.Database;
import net.canarymod.database.DatabaseRow;
import net.canarymod.database.DatabaseTable;

public class DatabaseMySql implements Database {

    String dbName = Configuration.getDbConfig().getDatabaseName();

    public static PreparedStatement getStatement(String statement)
            throws SQLException {
        CanaryConnection cconn = MySqlConnectionPool.getInstance()
                .getConnection();
        return cconn.prepareStatement(statement, 1);
    }

    public boolean prepare() {
        return false;
    }

    public boolean execute() {
        return false;
    }

    @Override
    public int getNumTables() {
        try {
            PreparedStatement ps = getStatement("SELECT count(*) as numTables FROM information_schema.tables WHERE table_schema = ?");
            ps.setString(1, dbName);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("numTables");
            }
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while counting tables!", e);
        }
        return -1;
    }

    @Override
    public String[] getAllTables() {
        try {
            // Tablename | Tabletype ['BASE TABLE' or 'VIEW']
            PreparedStatement ps = getStatement("SHOW FULL TABLES FROM "+dbName+" WHERE TABLE_TYPE = 'BASE TABLE'");
            ps.setString(1, dbName);
            ResultSet rs = ps.executeQuery();

            // go to last and pick row number
            rs.last();
            int numRows = rs.getRow();
            if (numRows <= 0)
                return new String[0];
            
            String[] result = new String[numRows];
            // reset the position before first row cause of iterator
            rs.beforeFirst();

            // get tablenames
            int i = 0;
            while (rs.next()) {
                result[i] = rs.getString(1).toLowerCase();
                ++i;
            }

            return result;
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while getting table names!", e);
            return new String[0];
        }
    }

    @Override
    public DatabaseTable getTable(String name) {
        try {
            PreparedStatement ps = getStatement("SELECT COUNT(*) AS hastable " + 
                      "FROM information_schema.tables "
                    + "WHERE table_schema = ? "
                    + "AND table_name = ? ");
            ps.setString(1, dbName);
            ps.setString(2, name.toLowerCase());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) { 
                if(rs.getInt("hastable") != 0) {
                    return new DatabaseTableMySql(name);
                }
                else {
                    return null;
                }
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while getting table!", e);
            return null;
        }
    }

    @Override
    public DatabaseTable addTable(String table) {
        if (table == null || table.isEmpty())
            return null;
        
        try {
            PreparedStatement ps = getStatement("CREATE TABLE IF NOT EXISTS "+table.toLowerCase()+" (ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY)");
            ps.execute();
            return new DatabaseTableMySql(table.toLowerCase());

        } catch (SQLException e) {
            Logman.logStackTrace("Exception while adding table "+table.toLowerCase()+"!", e);
        }
        return null;
    }

    @Override
    public void removeTable(String name) {
        if (name == null || name.isEmpty())
            return;
        
        name.toLowerCase();
        try {
            PreparedStatement ps = getStatement("DROP TABLE " + name.toLowerCase());
            ps.execute();
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while dropping table "+name.toLowerCase()+"!", e);
        }
    }

    @Override
    public DatabaseRow[] getRelatedRows(String table1, String table2, String relation1, String relation2, String searchColumn, String searchValue) {
        /*
         * SELECT t2.* FROM tabl2 AS t1
         * INNER JOIN table1_table2_rel AS rel ON(rel.relation1 = t1.relation1)
         * INNER JOIN table1 AS t2 ON(t2.relation2 = rel.relation2)
         * WHERE t1.searchColumn = t1.searchValue
         * 
         *  SELECT t2.* FROM groups AS t1
          INNER JOIN permissions_groups_rel AS rel ON(rel.NAME = t1.NAME)
          INNER JOIN permissions AS t2 ON(t2.ID = rel.PNID)
          WHERE t1.name = 'mods';
         * 
         */
        try {
            String relationId;
            if(relation2.equalsIgnoreCase("pnid")) {
                relationId = "ID";
            }
            else {
                relationId = relation2;
            }
            String query = "SELECT t2.* FROM "+table2.toLowerCase()+" AS t1 " +
                    "INNER JOIN "+table1.toLowerCase() + "_" + table2.toLowerCase() + "_rel AS rel ON(rel."+relation1.toUpperCase()+" = t1."+relation1.toUpperCase()+") "
                  + "INNER JOIN "+table1.toLowerCase()+" AS t2 ON(t2."+relationId.toUpperCase()+" = rel."+relation2.toUpperCase()+") "
                  + "WHERE t1."+searchColumn.toUpperCase()+" = ?";
            PreparedStatement ps = DatabaseMySql.getStatement(query);
            ps.setString(1, searchValue);
            ResultSet rs = ps.executeQuery();
            Logman.println("RELQURY: "+query);
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
            while(rs.next()) {
                columnValues = new HashMap<String, Object>();
                for (int i = 0; i < numColumns; ++i) {
                    columnValues.put(rsmd.getColumnName(i+1).toUpperCase(),
                            rs.getObject(i+1));
                }
                result[rs.getRow() - 1] = new DatabaseRowMySql((DatabaseTableMySql) getTable(table2), rs.getInt("ID"), columnValues);
            }
            return result;
        } catch (SQLException e) {
            Logman.logStackTrace("Error while resolving related rows.", e);
            return null;
        }
    }
    
    @Override
    public boolean setRelated(DatabaseRow row1, DatabaseRow row2) {
        throw new UnsupportedOperationException("Could not set relation between rows. This is an implementation issue!");
    }

    @Override
    public boolean unsetRelated(DatabaseRow row1, DatabaseRow row2) {
        throw new UnsupportedOperationException("Could not set relation between rows, therefore could not unset relation. This is an implementation issue!");
    }

    private String[] validatePath(String path, int columnsNeeded) {
        String[] values = path.split("\\.");
        
        if (values.length < columnsNeeded)
            return null;
        
        String[] result = new String[values.length];
        
        result[0] = values[0].toLowerCase();
        result[1] = values[1].toUpperCase();
        
        if (columnsNeeded == 2)
            return result;
        
        if (values.length >= 3 && columnsNeeded == 3)
        {
            try {
                Integer.parseInt(values[2]);
                result[2] = values[2];
            }
            catch (NumberFormatException nfe) {
                return null;
            }            
        }
        
        return result;
    }
    
    @Override
    public String getStringValue(String path) {
        String[] values = validatePath(path, 3);
        
        if (values == null)
            return null;
        
        try {
            PreparedStatement ps = getStatement("SELECT "+values[0].toUpperCase()+" FROM "+values[1].toLowerCase()+" WHERE ID = ?");
            // check for integer is done in validatePath 
            ps.setInt(1, Integer.parseInt(values[2]));
            
            ResultSet rs = ps.executeQuery();
            if(rs.first()) {
                return rs.getString(values[1]);
            }
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while getting String value from table "+values[0].toUpperCase()+" column "+values[1].toLowerCase(), e);
        }
        return null;
    }

    @Override
    public String[] getStringValues(String path) {
        String[] values = validatePath(path, 2);
        
        if (values == null)
            return null;
        
        try {
            PreparedStatement ps = getStatement("SELECT "+values[1].toUpperCase()+" FROM "+values[0].toLowerCase());
            ResultSet rs = ps.executeQuery();
            rs.last();
            int numRows = rs.getRow();
            
            if (numRows <= 0)
                return null;
            
            String[] results = new String[numRows];
            
            while(rs.next()) {
                results[rs.getRow()] = rs.getString(values[0]);
            }
            
            return results;
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while getting Int values from table "+values[0]+" column "+values[1], e);
        }
        return null;
    }

    @Override
    public Integer getIntValue(String path) {
        String[] values = validatePath(path, 3);
        
        if (values == null)
            return null;
        
        try {
            PreparedStatement ps = getStatement("SELECT ? FROM ? WHERE ID = ?");
            ps.setString(1, values[0].toUpperCase());
            ps.setString(2, values[1].toLowerCase());
            // check for integer is done in validatePath 
            ps.setInt(3, Integer.parseInt(values[2]));
            
            ResultSet rs = ps.executeQuery();
            if(rs.first()) {
                return rs.getInt(values[1]);
            }
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while getting Int value from table "+values[0]+" column "+values[1], e);
        }
        return null;
    }

    @Override
    public Integer[] getIntValues(String path) {
        String[] values = validatePath(path, 2);
        
        if (values == null)
            return null;
        
        try {
            PreparedStatement ps = getStatement("SELECT ? FROM ?");
            ps.setString(1, values[0].toUpperCase());
            ps.setString(2, values[1].toLowerCase());
            
            ResultSet rs = ps.executeQuery();
            rs.last();
            int numRows = rs.getRow();
            
            if (numRows <= 0)
                return null;
            
            Integer[] results = new Integer[numRows];
            
            while(rs.next()) {
                results[rs.getRow()] = rs.getInt(values[0]);
            }
            
            return results;
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while getting Int values from table "+values[0]+" column "+values[1], e);
        }
        return null;
    }

    @Override
    public Float getFloatValue(String path) {
        String[] values = validatePath(path, 3);
        
        if (values == null)
            return null;
        
        try {
            PreparedStatement ps = getStatement("SELECT ? FROM ? WHERE ID = ?");
            ps.setString(1, values[0].toUpperCase());
            ps.setString(2, values[1].toLowerCase());
            // check for integer is done in validatePath 
            ps.setInt(3, Integer.parseInt(values[2]));
            
            ResultSet rs = ps.executeQuery();
            if(rs.first()) {
                return rs.getFloat(values[1]);
            }
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while getting Float value from table "+values[0]+" column "+values[1], e);
        }
        return null;
    }

    @Override
    public Float[] getFloatValues(String path) {
        String[] values = validatePath(path, 2);
        
        if (values == null)
            return null;
        
        try {
            PreparedStatement ps = getStatement("SELECT ? FROM ?");
            ps.setString(1, values[0].toUpperCase());
            ps.setString(2, values[1].toLowerCase());
            
            ResultSet rs = ps.executeQuery();
            rs.last();
            int numRows = rs.getRow();
            
            if (numRows <= 0)
                return null;
            
            Float[] results = new Float[numRows];
            
            while(rs.next()) {
                results[rs.getRow()] = rs.getFloat(values[0]);
            }
            
            return results;
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while getting Float values from table "+values[0]+" column "+values[1], e);
        }
        return null;
    }

    @Override
    public Double getDoubleValue(String path) {
        String[] values = validatePath(path, 3);
        
        if (values == null)
            return null;
        
        try {
            PreparedStatement ps = getStatement("SELECT ? FROM ? WHERE ID = ?");
            ps.setString(1, values[0].toUpperCase());
            ps.setString(2, values[1].toLowerCase());
            // check for integer is done in validatePath 
            ps.setInt(3, Integer.parseInt(values[2]));
            
            ResultSet rs = ps.executeQuery();
            if(rs.first()) {
                return rs.getDouble(values[1]);
            }
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while getting Double value from table "+values[0]+" column "+values[1], e);
        }
        return null;
    }

    @Override
    public Double[] getDoubleValues(String path) {
        String[] values = validatePath(path, 2);
        
        if (values == null)
            return null;
        
        try {
            PreparedStatement ps = getStatement("SELECT ? FROM ?");
            ps.setString(1, values[0].toUpperCase());
            ps.setString(2, values[1].toLowerCase());
            
            ResultSet rs = ps.executeQuery();
            rs.last();
            int numRows = rs.getRow();
            
            if (numRows <= 0)
                return null;
            
            Double[] results = new Double[numRows];
            
            while(rs.next()) {
                results[rs.getRow()] = rs.getDouble(values[0]);
            }
            
            return results;
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while getting Double values from table "+values[0]+" column "+values[1], e);
        }
        return null;
    }

    @Override
    public boolean getBooleanValue(String path, boolean defaults) {
        String[] values = validatePath(path, 3);
        
        if (values == null)
            return defaults;
        
        try {
            PreparedStatement ps = getStatement("SELECT ? FROM ? WHERE ID = ?");
            ps.setString(1, values[0].toUpperCase());
            ps.setString(2, values[1].toLowerCase());
            // check for integer is done in validatePath 
            ps.setInt(3, Integer.parseInt(values[2]));
            
            ResultSet rs = ps.executeQuery();
            if(rs.first()) {
                return rs.getBoolean(values[1]);
            }
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while getting Float value from table "+values[0]+" column "+values[1], e);
        }
        return defaults;
    }

    @Override
    public Boolean getBooleanValue(String path) {
        String[] values = validatePath(path, 3);
        
        if (values == null)
            return null;
        
        try {
            PreparedStatement ps = getStatement("SELECT ? FROM ? WHERE ID = ?");
            ps.setString(1, values[0].toUpperCase());
            ps.setString(2, values[1].toLowerCase());
            // check for integer is done in validatePath 
            ps.setInt(3, Integer.parseInt(values[2]));
            
            ResultSet rs = ps.executeQuery();
            if(rs.first()) {
                return rs.getBoolean(values[1]);
            }
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while getting Boolean value from table "+values[0]+" column "+values[1], e);
        }
        return null;
    }

    @Override
    public Boolean[] getBooleanValues(String path) {
        String[] values = validatePath(path, 2);
        
        if (values == null)
            return null;
        
        try {
            PreparedStatement ps = getStatement("SELECT ? FROM ?");
            ps.setString(1, values[0]);
            ps.setString(2, values[1]);
            
            ResultSet rs = ps.executeQuery();
            rs.last();
            int numRows = rs.getRow();
            
            if (numRows <= 0)
                return null;
            
            Boolean[] results = new Boolean[numRows];
            
            while(rs.next()) {
                results[rs.getRow()] = rs.getBoolean(values[0]);
            }
            
            return results;
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while getting Int values from table "+values[0]+" column "+values[1], e);
        }
        return null;
    }

    @Override
    public Long getLongValue(String path) {
        String[] values = validatePath(path, 3);
        
        if (values == null)
            return null;
        
        try {
            PreparedStatement ps = getStatement("SELECT ? FROM ? WHERE ID = ?");
            ps.setString(1, values[0]);
            ps.setString(2, values[1]);
            // check for integer is done in validatePath 
            ps.setInt(3, Integer.parseInt(values[2]));
            
            ResultSet rs = ps.executeQuery();
            if(rs.first()) {
                return rs.getLong(values[1]);
            }
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while getting Float value from table "+values[0]+" column "+values[1], e);
        }
        return null;
    }

    @Override
    public Long[] getLongValues(String path) {
        String[] values = validatePath(path, 2);
        
        if (values == null)
            return null;
        
        try {
            PreparedStatement ps = getStatement("SELECT ? FROM ?");
            ps.setString(1, values[0]);
            ps.setString(2, values[1]);
            
            ResultSet rs = ps.executeQuery();
            rs.last();
            int numRows = rs.getRow();
            
            if (numRows <= 0)
                return null;
            
            Long[] results = new Long[numRows];
            
            while(rs.next()) {
                results[rs.getRow()] = rs.getLong(values[0]);
            }
            
            return results;
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while getting Long values from table "+values[0]+" column "+values[1], e);
        }
        return null;
    }

    @Override
    public Character getCharacterValue(String path) {
        String[] values = validatePath(path, 3);
        
        if (values == null)
            return null;
        
        try {
            PreparedStatement ps = getStatement("SELECT ? FROM ? WHERE ID = ?");
            ps.setString(1, values[0]);
            ps.setString(2, values[1]);
            // check for integer is done in validatePath 
            ps.setInt(3, Integer.parseInt(values[2]));
            
            ResultSet rs = ps.executeQuery();
            if(rs.first()) {
                return rs.getString(values[1]).charAt(0);
            }
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while getting Float value from table "+values[0]+" column "+values[1], e);
        }
        return null;
    }

    @Override
    public Character[] getCharacterValues(String path) {
        String[] values = validatePath(path, 2);
        
        if (values == null)
            return null;
        
        try {
            PreparedStatement ps = getStatement("SELECT ? FROM ?");
            ps.setString(1, values[0]);
            ps.setString(2, values[1]);
            
            ResultSet rs = ps.executeQuery();
            rs.last();
            int numRows = rs.getRow();
            
            if (numRows <= 0)
                return null;
            
            Character[] results = new Character[numRows];
            
            while(rs.next()) {
                results[rs.getRow()] = rs.getString(values[0]).charAt(0);
            }
            
            return results;
        } catch (SQLException e) {
            Logman.logStackTrace("Exception while getting Character values from table "+values[0]+" column "+values[1], e);
        }
        return null;
    }
}