
package net.canarymod.database;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * @author Jos Kuijpers
 */
public class DatabaseFlatfile implements Database {

	private Logger log = Logger.getLogger("Minecraft");
	private HashMap<String, DatabaseTableFlatfile> tables;
	private File dbDirectory;
	
	/**
	 * Sets up the flatfile database
	 */
	public DatabaseFlatfile() {

		// Find the database table files
		this.dbDirectory = new File("db/");
		this.tables = new HashMap<String, DatabaseTableFlatfile>();
		String[] dbFiles = this.dbDirectory.list();
		
		// Extract the tablename and verify the extension
		for(String file : dbFiles) {
			if((new File("db/"+file)).isDirectory()) // TODO inefficient
				continue;
			if(!file.endsWith(".txt")) {
				log.warning("Invalid file '"+file+"' found in db/");
				continue;
			}

			DatabaseTableFlatfile table;
			try {
				table = new DatabaseTableFlatfile(this,file);
			}
			catch(IOException e) {
				log.severe("Failed to load flatfile database table in "+file);
				continue;
			}
			
			// Store the table object
			tables.put(table.getName(), table);
		}
	}
	
	public void reload() {
		// TODO implement database reload
		// look for removed files and remove them in our list
		// reload all tables
	}
	
	public void save() {
		
		// Write out the files... just save all tables
		for(DatabaseTableFlatfile t : this.tables.values())
			t.save();
	}
	
	/**
	 * Called by DatabaseTableFlatfile to synchronize the database info structure. Do not use!
	 * @param oldName
	 * @param newName
	 */
	public void tableRenamed(String oldName, String newName) {
		this.tables.put(newName,this.tables.get(oldName));
		this.tables.remove(oldName);
	}
	
	@Override
 	public int getNumTables() {
		return this.tables.size();
	}

	@Override
	public String[] getAllTables() {
		String[] ar = {};
		return this.tables.keySet().toArray(ar);
	}

	@Override
	public DatabaseTable getTable(String name) {
		if(!this.tables.containsKey(name.toLowerCase()))
			return null;
		return this.tables.get(name.toLowerCase());
	}

	@Override
	public boolean addTable(DatabaseTable table) {
		if(this.tables.containsKey(table.getName()))
			return false;

		// Just add the table, and save it
		this.tables.put(table.getName().toLowerCase(), (DatabaseTableFlatfile)table);
		
		this.save();
		
		return true;
	}

	@Override
	public void removeTable(String name) {
		if(!this.tables.containsKey(name.toLowerCase()))
			return;
		this.tables.remove(name.toLowerCase());
		File f = new File("db/"+name.toLowerCase()+".txt");
		f.delete();
	}

	private String[] resolvePath(String[] path) {
		String tableName = path[0].toLowerCase();
		String columnName = path[1].toUpperCase();
		ArrayList<String> data = new ArrayList<String>();
		String[] ret = {};
		DatabaseTableFlatfile table;

		if(!this.tables.containsKey(tableName))
			return null;

		table = this.tables.get(tableName);
		
		// Find the column index
		int index = table.getColumnPosition(columnName);
		if(index == -1)
			return null;

		for(DatabaseRow row : table.getAllRows()) {
			data.add(row.getStringCell(columnName));
		}
		
		
		ret = data.toArray(ret);
		
		return ret;
	}
	
	@Override
	public String getStringValue(String path) {
		String[] components = path.split("\\.");
		if(components.length != 3)
			return null;

		String[] values = this.resolvePath(components);
		int index;
		try {
			index = Integer.parseInt(components[2]);
		}
		catch(NumberFormatException e) {
			log.warning("A NumberFormatException occurred in Table: '"+components[0]+"' @ Column: "+components[1]);
			return null;
		}
		
		if(index < 1 || index > values.length)
			return null;
		
		return values[index-1];
	}

	@Override
	public String[] getStringValues(String path) {
		String[] components = path.split("\\.");
		if(components.length != 2)
			return null;

		return this.resolvePath(components);
	}

	@Override
	public int getIntValue(String path) {
		String sval = this.getStringValue(path);
		if(sval == null)
			return Integer.MAX_VALUE;
		
		try {
			return Integer.parseInt(sval);
		}
		catch(NumberFormatException e) {
			log.warning("A NumberFormatException occurred in database-path: "+path);
			return Integer.MAX_VALUE;
		}
	}

	@Override
	public int[] getIntValues(String path) {
		String[] svals = this.getStringValues(path);
		if(svals == null)
			return null;
		
		int[] ret = new int[svals.length];
		try {
			
			for(int i = 0; i < svals.length; i++)
				ret[i] = Integer.parseInt(svals[i]);
		}
		catch(NumberFormatException e) {
			log.warning("A NumberFormatException occurred in database-path: "+path);
			return null;
		}
		
		return ret;
	}

	@Override
	public float getFloatValue(String path) {
		String sval = this.getStringValue(path);
		if(sval == null)
			return Float.MAX_VALUE;
		
		try {
			return Float.parseFloat(sval);
		}
		catch(NumberFormatException e) {
			log.warning("A NumberFormatException occurred in database-path: "+path);
			return Float.MAX_VALUE;
		}
	}

	@Override
	public float[] getFloatValues(String path) {
		String[] svals = this.getStringValues(path);
		if(svals == null)
			return null;
		
		float[] ret = new float[svals.length];
		try {
			
			for(int i = 0; i < svals.length; i++)
				ret[i] = Float.parseFloat(svals[i]);
		}
		catch(NumberFormatException e) {
			log.warning("A NumberFormatException occurred in database-path: "+path);
			return null;
		}
		
		return ret;
	}

	@Override
	public double getDoubleValue(String path) {
		String sval = this.getStringValue(path);
		if(sval == null)
			return Double.MAX_VALUE;
		
		try {
			return Double.parseDouble(sval);
		}
		catch(NumberFormatException e) {
			log.warning("A NumberFormatException occurred in database-path: "+path);
			return Double.MAX_VALUE;
		}
	}

	@Override
	public double[] getDoubleValues(String path) {
		String[] svals = this.getStringValues(path);
		if(svals == null)
			return null;
		
		double[] ret = new double[svals.length];
		try {
			
			for(int i = 0; i < svals.length; i++)
				ret[i] = Double.parseDouble(svals[i]);
		}
		catch(NumberFormatException e) {
			log.warning("A NumberFormatException occurred in database-path: "+path);
			return null;
		}
		
		return ret;
	}

	@Override
	public boolean getBooleanValue(String path) {
		return this.getBooleanValue(path, false);
	}
	
	@Override
	public boolean getBooleanValue(String path, boolean defaults) {
		String sval = this.getStringValue(path);
		if(sval == null)
			return defaults;
		
		try {
			return Boolean.parseBoolean(sval);
		}
		catch(NumberFormatException e) {
			log.warning("A NumberFormatException occurred in database-path: "+path);
			return defaults;
		}
	}

	@Override
	public boolean[] getBooleanValues(String path) {
		String[] svals = this.getStringValues(path);
		if(svals == null)
			return null;
		
		boolean[] ret = new boolean[svals.length];
		for(int i = 0; i < svals.length; i++) {
			if(svals[i].equalsIgnoreCase("true") || svals[i].equalsIgnoreCase("yes") || svals[i].equals("1"))
				ret[i] = true;
			else if(svals[i].equalsIgnoreCase("false") || svals[i].equalsIgnoreCase("no") || svals[i].equals("0"))
				ret[i] = false;
			else {
				log.warning("A NumberFormatException occurred in database-path: "+path);
				return null;
			}
		}
		
		return ret;
	}

	@Override
	public long getLongValue(String path) {
		String sval = this.getStringValue(path);
		if(sval == null)
			return Long.MAX_VALUE;
		
		try {
			return Long.parseLong(sval);
		}
		catch(NumberFormatException e) {
			log.warning("A NumberFormatException occurred in database-path: "+path);
			return Long.MAX_VALUE;
		}
	}

	@Override
	public long[] getLongValues(String path) {
		String[] svals = this.getStringValues(path);
		if(svals == null)
			return null;
		
		long[] ret = new long[svals.length];
		try {
			
			for(int i = 0; i < svals.length; i++)
				ret[i] = Long.parseLong(svals[i]);
		}
		catch(NumberFormatException e) {
			log.warning("A NumberFormatException occurred in database-path: "+path);
			return null;
		}
		
		return ret;
	}

	@Override
	public Character getCharacterValue(String path) {
		String sval = this.getStringValue(path);
		if(sval != null && sval.length() > 0){
            return sval.charAt(0);
        }
		return null;
	}

	@Override
	public Character[] getCharacterValues(String path) {
		String[] svals = this.getStringValues(path);
		if(svals == null)
			return null;
		
		Character[] ret = new Character[svals.length];
		
		for(int i = 0; i < svals.length; i++) {
			if(svals[i].length() > 0)
				ret[i] = svals[i].charAt(0);
			else
				return null;
		}
	
		return ret;
	}

}
