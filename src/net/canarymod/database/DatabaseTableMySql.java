package net.canarymod.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

public class DatabaseTableMySql implements DatabaseTable {

	private String _tableName;

	public DatabaseTableMySql(String tableName) {
		_tableName = tableName;
	}

	@Override
	public String getName() {
		return _tableName;
	}

	@Override
	public void setName(String name) {
		try {
			PreparedStatement ps = DatabaseMySql.getStatement("ALERT TABLE ? RENAME ?");
			ps.setString(0, _tableName);
			ps.setString(1, name);

			if (ps.execute()) {
				_tableName = name;
			}
		} catch (SQLException e) {
			// TODO Logging
		}
	}

	@Override
	public String getDescription() {
		try {
			PreparedStatement ps = DatabaseMySql.getStatement("SHOW TABLE STATUS FROM ?");
			ps.setString(0, _tableName);
			
			ResultSet rs = ps.executeQuery();
			rs.first();
			return rs.getString("COMMENT");
		} catch (SQLException e) {
			// TODO Logging
			return "";
		}
	}

	@Override
	public void setDescription(String description) {
		try {
			PreparedStatement ps = DatabaseMySql.getStatement("ALTER TABLE ? COMMENT = ?");
			ps.setString(0, _tableName);
			ps.setString(1, description);
			
			ps.execute();
		} catch (SQLException e) {
			// TODO Logging
		}
	}

	@Override
	public int getNumRows() {
		try {
			PreparedStatement ps = DatabaseMySql.getStatement("SELECT * FROM ?");
			ps.setString(0, _tableName);
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
	public DatabaseRow getRow(int row) {
		try {
			PreparedStatement ps = DatabaseMySql.getStatement("SELECT * FROM ? LIMIT ?,1");
			ps.setString(0, _tableName);
			ps.setInt(1, row-1);
			
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			HashMap<String, Object> columValues = new HashMap<String, Object>();
						
			for (int i = 0; i < rsmd.getColumnCount(); ++i)
			{
				columValues.put(rsmd.getColumnName(i).toUpperCase(), rs.getObject(i));
			}
			
			return new DatabaseRowMySql(columValues);
		} catch (SQLException e) {
			// TODO Logging
			return null;
		}
	}

	@Override
	public DatabaseRow[] getAllRows() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DatabaseRow[] getFilteredRows(String column, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean rowExists(String column, String value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DatabaseRow addRow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeRow(DatabaseRow row) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeRow(int row) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getNumColumns() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[] getAllColumns() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void appendColumn(String name, ColumnType type) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeColumn(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertColumn(String name, ColumnType type, String after) {
		// TODO Auto-generated method stub

	}

	@Override
	public void truncateTable() {
		// TODO Auto-generated method stub

	}

}
