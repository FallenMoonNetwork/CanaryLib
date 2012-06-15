package net.canarymod.database.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        return cconn.prepareStatement(statement);
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
//            select count(*) as number_of_tables 
//            from information_schema.tables 
//            where table_schema = 'my favourite schema' 
            // Tablename | Tabletype ['BASE TABLE' or 'VIEW']
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
            PreparedStatement ps = getStatement("SHOW FULL TABLES FROM ?");
            ps.setString(1, dbName);
            ResultSet rs = ps.executeQuery();

            // go to last and pick row number
            rs.last();
            String[] result = new String[rs.getRow()];
            // reset the position before first row cause of iterator
            rs.beforeFirst();

            // get tablenames
            int i = 0;
            while (rs.next()) {
                result[i] = rs.getString(1);
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
        return new DatabaseTableMySql(name);
    }

    @Override
    public DatabaseTable addTable(String table) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void removeTable(String name) {
        // TODO Auto-generated method stub

    }

    @Override
    public DatabaseRow[] getRelatedRows(String table1, String table2,
            String relation1, String relation2, String searchColumn,
            String searchValue) {
        return null;
    }

    public boolean setRelated(DatabaseRow row1, DatabaseRow row2) {

        return false;
    }

    public boolean unsetRelated(DatabaseRow row1, DatabaseRow row2) {
        return false;
    }

    @Override
    public String getStringValue(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String[] getStringValues(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getIntValue(String path) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int[] getIntValues(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public float getFloatValue(String path) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float[] getFloatValues(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double getDoubleValue(String path) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double[] getDoubleValues(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean getBooleanValue(String path, boolean defaults) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean getBooleanValue(String path) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean[] getBooleanValues(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getLongValue(String path) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long[] getLongValues(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Character getCharacterValue(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Character[] getCharacterValues(String path) {
        // TODO Auto-generated method stub
        return null;
    }

}