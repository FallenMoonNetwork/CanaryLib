package net.canarymod.database;
public class DatabaseMySql implements Database {

    @Override
    public int getNumTables() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String[] getAllTables() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DatabaseTable getTable(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean addTable(DatabaseTable table) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void removeTable(String name) {
        // TODO Auto-generated method stub
        
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