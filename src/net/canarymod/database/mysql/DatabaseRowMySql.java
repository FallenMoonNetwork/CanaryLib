package net.canarymod.database.mysql;

import java.util.HashMap;

import net.canarymod.database.DatabaseRow;
import net.canarymod.database.DatabaseTable;

public class DatabaseRowMySql implements DatabaseRow {

	private HashMap<String, Object> _values;
	public DatabaseRowMySql(HashMap<String, Object> values) {
		_values = values;
	}
	
	@Override
	public int getNumCells() {
		return _values.size();
	}

	@Override
	public DatabaseTable getTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStringCell(String column) {
		column = column.toUpperCase();
		if (!_values.containsKey(column))
			return null;
		
		return String.valueOf(_values.get(column));
	}

	@Override
	public void setStringCell(String column, String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getIntCell(String column) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setIntCell(String column, int value) {
		// TODO Auto-generated method stub

	}

	@Override
	public float getFloatCell(String column) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setFloatCell(String column, float value) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getDoubleCell(String column) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setDoubleCell(String column, double value) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getBooleanCell(String column) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getBooleanCell(String column, boolean defaults) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setBooleanCell(String column, boolean value) {
		// TODO Auto-generated method stub

	}

	@Override
	public long getLongCell(String column) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLongCell(String column, long value) {
		// TODO Auto-generated method stub

	}

	@Override
	public Character getCharacter(String column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCharacter(String column, Character value) {
		// TODO Auto-generated method stub

	}

}
