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
		if (name == null || name.isEmpty()) {
			return;
		}
		
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
		if (description == null) {
			description = "";
		}
		
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
		if (row <= 0) {
			return null;
		}
		
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
		try {
			PreparedStatement ps = DatabaseMySql.getStatement("SELECT * FROM ?");
			ps.setString(0, _tableName);
			
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int numColumns = rsmd.getColumnCount();
			
			// count Rows
			rs.last();
			int numRows = rs.getRow();
			
			rs.beforeFirst();
			DatabaseRow[] result = new DatabaseRow[numRows];
			HashMap<String, Object> columValues;
			while (rs.next()) {
				columValues = new HashMap<String, Object>();
				for (int i = 0; i < numColumns; ++i)	{
					columValues.put(rsmd.getColumnName(i).toUpperCase(), rs.getObject(i));
				}
				result[rs.getRow()-1] = new DatabaseRowMySql(columValues);
			}
			
			return result;
		} catch (SQLException e) {
			// TODO Logging
			return null;
		}
	}

	@Override
	public DatabaseRow[] getFilteredRows(String column, String value) {
		if (column == null || column.isEmpty() || value == null || value.isEmpty()) {
			return null;
		}
		
		try {
			PreparedStatement ps = DatabaseMySql.getStatement("SELECT * FROM ? WHERE ? = ?");
			ps.setString(0, _tableName);
			ps.setString(1, column);
			
			try {
				ps.setInt(2, Integer.parseInt(value));
			}
			catch (Exception ex) {
				try {
					ps.setDouble(2, Double.parseDouble(value));
				}
				catch (Exception iex) {
					ps.setString(2, value);
				}
			}
			
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int numColumns = rsmd.getColumnCount();
			
			// count Rows
			rs.last();
			int numRows = rs.getRow();
			
			rs.beforeFirst();
			DatabaseRow[] result = new DatabaseRow[numRows];
			HashMap<String, Object> columValues;
			while (rs.next()) {
				columValues = new HashMap<String, Object>();
				for (int i = 0; i < numColumns; ++i)	{
					columValues.put(rsmd.getColumnName(i).toUpperCase(), rs.getObject(i));
				}
				result[rs.getRow()-1] = new DatabaseRowMySql(columValues);
			}
			
			return result;
		} catch (SQLException e) {
			// TODO Logging
			return null;
		}
	}

	@Override
	public boolean rowExists(String column, String value) {
		if (column == null || column.isEmpty() || value == null || value.isEmpty()) {
			return false;
		}
		
		try {
			PreparedStatement ps = DatabaseMySql.getStatement("SELECT * FROM ? WHERE ? = ? LIMIT 1");
			ps.setString(0, _tableName);
			ps.setString(1, column);
			
			try {
				ps.setInt(2, Integer.parseInt(value));
			}
			catch (Exception ex) {
				try {
					ps.setDouble(2, Double.parseDouble(value));
				}
				catch (Exception iex) {
					ps.setString(2, value);
				}
			}
			
			ResultSet rs = ps.executeQuery();
			return rs.first();
		}
		catch (SQLException ex) {
			// TODO Logging
			return false;
		}
	}

	@Override
	public DatabaseRow addRow() {
		try {
			// what should be inserted in the database????
			PreparedStatement ps = DatabaseMySql.getStatement("INSERT INTO ? VALUES (LOL?, WTF?)");
			ps.setString(0, _tableName);
			
			ResultSet rs = ps.executeQuery();
			rs.first();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			HashMap<String, Object> columValues = new HashMap<String, Object>();
						
			for (int i = 0; i < rsmd.getColumnCount(); ++i)
			{
				columValues.put(rsmd.getColumnName(i).toUpperCase(), rs.getObject(i));
			}
			
			return new DatabaseRowMySql(columValues);
		}
		catch (SQLException ex) {
			// TODO Logging
			return null;
		}
	}

	@Override
	public void removeRow(DatabaseRow row) {
		try {

			StringBuilder whereQuery = new StringBuilder();
			// for numColumns in row build where query
			// ? = ? AND ? = ? ...
			
			PreparedStatement ps = DatabaseMySql.getStatement("DELETE FROM ? WHERE " + whereQuery.toString());
			ps.setString(0, _tableName);
			
			// for numColumns in row set values
			// ps.setString(x, col);
			// ps.setLong/setDouble/setString(x+1, val)
			
			ps.executeQuery();
		}
		catch (SQLException ex) {
			// TODO Logging
		}
	}

	@Override
	public void removeRow(int row) {
		if (row <= 0) {
			return;
		}
		
		try {
			// does the query works?
			PreparedStatement ps = DatabaseMySql.getStatement("DELETE FROM ? WHERE (SELECT * FROM ? LIMIT ?,1)");
			ps.setString(0, _tableName);
			ps.setString(1, _tableName);
			ps.setInt(2, row-1);
			
			ps.executeQuery();
		}
		catch (SQLException ex) {
			// TODO Logging
		}
	}

	@Override
	public int getNumColumns() {
		try {
			PreparedStatement ps = DatabaseMySql.getStatement("SELECT * FROM ? LIMIT 1");
			ps.setString(0, _tableName);
			
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			return rsmd.getColumnCount();
		}
		catch (SQLException ex) {
			// TODO Logging
			return -1;
		}
	}

	@Override
	public String[] getAllColumns() {
		try {
			PreparedStatement ps = DatabaseMySql.getStatement("SELECT * FROM ? LIMIT 1");
			ps.setString(0, _tableName);
			
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int numColumns = rsmd.getColumnCount();

			String[] result = new String[numColumns];
			for (int i = 0; i < numColumns; ++i)	{
				result[i] = rsmd.getColumnName(i).toUpperCase();
			}
			
			return result;
		}
		catch (SQLException ex) {
			// TODO Logging
			return new String[0];
		}
	}

	@Override
	public void appendColumn(String name, ColumnType type) {
		try {
			
			// TODO check for existing column
			
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
			
			PreparedStatement ps = DatabaseMySql.getStatement("ALTER TABLE ? ADD COLUMN ? " + strType + " NULL");
			ps.setString(0, _tableName);
			ps.setString(1, name);
			
			ps.executeQuery();
		}
		catch (SQLException ex) {
			// TODO Logging
		}
	}

	@Override
	public void removeColumn(String name) {
		try {
			PreparedStatement ps = DatabaseMySql.getStatement("ALTER TABLE ? DROP COLUMN ?");
			ps.setString(0, _tableName);
			ps.setString(1, name);
			
			ps.executeQuery();
		}
		catch (SQLException ex) {
			// TODO Logging
		}
	}

	@Override
	public void insertColumn(String name, ColumnType type, String after) {
		try {
			
			// TODO check for existing column
			// TODO check column "after" exists
			
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
			
			PreparedStatement ps = DatabaseMySql.getStatement("ALTER TABLE ? ADD COLUMN ? " + strType + " NULL AFTER ?");
			ps.setString(0, _tableName);
			ps.setString(1, name);
			ps.setString(2, after);
			
			ps.executeQuery();
		}
		catch (SQLException ex) {
			// TODO Logging
		}
	}

	@Override
	public void truncateTable() {
		try {
			PreparedStatement ps = DatabaseMySql.getStatement("TRUNCATE TABLE ?");
			ps.setString(0, _tableName);
			
			ps.executeQuery();
		}
		catch (SQLException ex) {
			// TODO Logging
		}
	}
}
