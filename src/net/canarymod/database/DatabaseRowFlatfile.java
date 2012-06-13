
package net.canarymod.database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * @author Jos Kuijpers
 */
public class DatabaseRowFlatfile implements DatabaseRow {

	private Logger log = Logger.getLogger("Minecraft");
	
	public ArrayList<String> cells; 
	private DatabaseTableFlatfile table;
	
	/**
	 * Used by DatabaseTableFlatfile to import rows. Creates a row with given data.
	 * 
	 * @param table
	 * @param cells
	 * @throws IOException on import fail
	 */
	public DatabaseRowFlatfile(DatabaseTableFlatfile table, String[] cells) {
		this.table = table;
		this.cells = new ArrayList<String>();
		
		if(cells == null) { // new row
			for(int i = 0; i < table.getNumColumns(); i++)
				this.cells.add("");
			return;
		}
		
		for(String c : cells)
			this.cells.add(c);
	}
	
	@Override
	public int getNumCells() {
		return this.cells.size();
	}

	@Override
	public DatabaseTable getTable() {
		return this.table;
	}

	@Override
	public String getStringCell(String column) {
		if(!this.table.columnNames.contains(column.toUpperCase()))
			return null;
		
		// Get an array of column names to compare
		int index = table.getColumnPosition(column);
		if(index == -1)
			return null;
		
		return this.cells.get(index);
	}

	@Override
	public void setStringCell(String column, String value) {
		if(!this.table.columnNames.contains(column.toUpperCase()))
			return;
		
		// Get an array of column names to compare
		int index = table.getColumnPosition(column);
		if(index == -1)
			return;
		
		this.cells.set(index, value);
	}

	@Override
	public int getIntCell(String column) {
		String sval = this.getStringCell(column); 
		if(sval == null)
			return Integer.MAX_VALUE;
		
		 try {
             return Integer.parseInt(sval);
         }
		 catch (NumberFormatException NFE) {
             log.warning("A NumberFormatException occurred in Table: '"+this.table.getName()+"' @ Column: "+column);
         }
		 
		return Integer.MAX_VALUE;
	}

	@Override
	public void setIntCell(String column, int value) {
		this.setStringCell(column,String.valueOf(value));
	}

	@Override
	public float getFloatCell(String column) {
		String sval = this.getStringCell(column); 
		if(sval == null)
			return Float.MAX_VALUE;
		
		try {
			return Float.parseFloat(sval);
		}
		catch(NumberFormatException e) {
			log.warning("A NumberFormatException occurred in Table: '"+this.table.getName()+"' @ Column: "+column);
		}
		
		return Float.MAX_VALUE;
	}

	@Override
	public void setFloatCell(String column, float value) {
		this.setStringCell(column,String.valueOf(value));
	}

	@Override
	public double getDoubleCell(String column) {
		String sval = this.getStringCell(column); 
		if(sval == null)
			return Double.MAX_VALUE;
		
		try {
			return Double.parseDouble(sval);
		}
		catch(NumberFormatException e) {
			log.warning("A NumberFormatException occurred in Table: '"+this.table.getName()+"' @ Column: "+column);
		}
		
		return Double.MAX_VALUE;
	}

	@Override
	public void setDoubleCell(String column, double value) {
		this.setStringCell(column,String.valueOf(value));
	}
	
	@Override
	public boolean getBooleanCell(String column) {
		return this.getBooleanCell(column, false);
	}
	
	@Override
	public boolean getBooleanCell(String column, boolean defaults) {
		String sval = this.getStringCell(column); 
		if(sval == null)
			return defaults;
		
		if(sval.equalsIgnoreCase("true") || sval.equalsIgnoreCase("yes") || sval.equals("1"))
			return true;
		if(sval.equalsIgnoreCase("false") || sval.equalsIgnoreCase("no") || sval.equals("0"))
			return false;
		
		return defaults;
	}

	@Override
	public void setBooleanCell(String column, boolean value) {
		this.setStringCell(column,String.valueOf(value));
	}

	@Override
	public long getLongCell(String column) {
		String sval = this.getStringCell(column); 
		if(sval == null)
			return Long.MAX_VALUE;
		
		try {
			return Long.parseLong(sval);
		}
		catch(NumberFormatException e) {
			log.warning("A NumberFormatException occurred in Table: '"+this.table.getName()+"' @ Column: "+column);
		}
		
		return Long.MAX_VALUE;
	}

	@Override
	public void setLongCell(String column, long value) {
		this.setStringCell(column,String.valueOf(value));
	}

	@Override
	public Character getCharacter(String column) {
		String sval = this.getStringCell(column); 
		if(sval == null || sval.length() == 0)
			return null;
		
		return sval.charAt(0);
	}

	@Override
	public void setCharacter(String column, Character value) {
		this.setStringCell(column,String.valueOf(value));
	}

}
