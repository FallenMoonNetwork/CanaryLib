package net.canarymod.database;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.canarymod.Logman;
import net.canarymod.config.Configuration;

public class DatabaseMySql implements Database {

    public boolean prepare() {
        return false;
    }
    
    public boolean execute() {
        return false;
    }
    
    @Override
    public int getNumTables() {
        try {
            PreparedStatement ps = MySqlConnectionPool.getInstance().getConnection().prepareStatement("SELECT count(*) as amount FROM information_schema.tables WHERE table_schema = ?");
            ps.setString(1, Configuration.getDbConfig().getDatabaseName());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("amount");
            }
        } catch (SQLException e) {
            Logman.logStackTrace("Error while counting database rows!", e);
        }
        return -1;
    }

    @Override
    public String[] getAllTables() {
        ArrayList<String> tableNames = new ArrayList<String>();
        try {
            DatabaseMetaData dbMeta = (DatabaseMetaData)MySqlConnectionPool.getInstance().getConnection().getConnection().getMetaData();
            ResultSet rs = dbMeta.getTables(Configuration.getDbConfig().getDatabaseName(), null, null, null);
            while(rs.next()) {
               tableNames.add(rs.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            Logman.logStackTrace("Error while collecting database table names!", e);
        }
        return tableNames.toArray(new String[tableNames.size()]);
    }

    @Override
    public DatabaseTable getTable(String name) {
        // TODO need stuff form devenias
        return null;
    }

    @Override
    public DatabaseTable addTable(String table) {
//        TODO need stuff form devenias
        return null;
    }

    @Override
    public void removeTable(String name) {
        // TODO need stuff form devenias
        
    }
    
    @Override
    public DatabaseRow[] getRelatedRows(String table1, String table2, String relation1, String relation2, String searchColumn, String searchValue) {
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