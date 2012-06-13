package net.canarymod.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.canarymod.database.DatabaseTable.ColumnType;

public class DatabaseMySql implements Database {

	String _dbName = "test";

	public static PreparedStatement getStatement(String statement) throws SQLException {
		CanaryConnection cconn = MySqlConnectionPool.getInstance().getConnection();
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
			// Tablename | Tabletype ['BASE TABLE' or 'VIEW']
			PreparedStatement ps = getStatement("SHOW FULL TABLES FROM ? WHERE TABLE_TYPE == 'BASE TABLE'");
			ps.setString(0, _dbName);
			ResultSet rs = ps.executeQuery();

			// go to last and pick row number
			rs.last();
			return rs.getRow();
		} catch (SQLException e) {
			// TODO Logging
			return -1;
		}
	}

	@Override
	public String[] getAllTables() {
		try {
			// Tablename | Tabletype ['BASE TABLE' or 'VIEW']
			PreparedStatement ps = getStatement("SHOW FULL TABLES IFROM ? WHERE TABLE_TYPE == 'BASE TABLE'");
			ps.setString(0, _dbName);
			ResultSet rs = ps.executeQuery();

			// go to last and pick row number
			rs.last();
			int numTables = rs.getRow();
			String[] result = new String[numTables];
			// reset the position before first row cause of iterator
			rs.beforeFirst();

			// get tablenames
			int i = 0;
			while (rs.next()) {
				result[i] = rs.getString(0);
				++i;
			}

			return result;
		} catch (SQLException e) {
			// TODO Logging
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