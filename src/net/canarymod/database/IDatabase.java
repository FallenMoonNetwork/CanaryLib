package net.canarymod.database;

/**
 * A database representation, used to store any kind of data
 * 
 * @author Jos Kuijpers
 */
public interface IDatabase {
	
	/**
	 * Returns the number of existing tables
	 * @return
	 */
	public int getNumTables();
	
	/**
	 * Returns an array of names of all the tables
	 * @return
	 */
	public String[] getAllTables();
	
	/**
	 * Get the table object with specified table name
	 * @param name
	 * @return
	 */
	public IDatabaseTable getTable(String name);
	
	/**
	 * Add a table to the database
	 * 
	 * This stores the table and its data into the database
	 * @param table
	 */
	public boolean addTable(IDatabaseTable table);
	
	/**
	 * Remove a table permanently
	 * @param name
	 */
	public void removeTable(String name);
	
	/**
	 * Get the value at the path as string
	 * @param path
	 * @return
	 */
	public String getStringValue(String path);
	
	/**
	 * Get the values at the path as an array of strings
	 * @param path
	 * @return
	 */
	public String[] getStringValues(String path);

	/**
	 * Get the value at the path as integer
	 * @param path
	 * @return
	 */
	public int getIntValue(String path);
	
	/**
	 * Get the value at the path as an array of integers
	 * @param path
	 * @return
	 */
	public int[] getIntValues(String path);
	
	/**
	 * Get the value at the path as float
	 * @param path
	 * @return
	 */
	public float getFloatValue(String path);
	
	/**
	 * Get the value at the path as an array of floats
	 * @param path
	 * @return
	 */
	public float[] getFloatValues(String path);

	/**
	 * Get the value at the path as double
	 * @param path
	 * @return
	 */
	public double getDoubleValue(String path);
	
	/**
	 * Get the value at the path as an array of doubles
	 * @param path
	 * @return
	 */
	public double[] getDoubleValues(String path);

	/**
	 * Get the value at the path as boolean
	 * @param path
	 * @param defaults
	 * @return value, or defaults on failure
	 */
	public boolean getBooleanValue(String path, boolean defaults);
	
	/**
	 * Get the value at the path as boolean
	 * @param path
	 * @return value or  false on failure
	 */
	public boolean getBooleanValue(String path);
	
	/**
	 * Get the value at the path as an array of booleans
	 * @param path
	 * @return
	 */
	public boolean[] getBooleanValues(String path);

	/**
	 * Get the value at the path as long
	 * @param path
	 * @return
	 */
	public long getLongValue(String path);
	
	/**
	 * Get the value at the path as an array of longs
	 * @param path
	 * @return
	 */
	public long[] getLongValues(String path);

	/**
	 * Get the value at the path as character
	 * @param path
	 * @return
	 */
	public Character getCharacterValue(String path);
	
	/**
	 * Get the value at the path as an array of characters
	 * @param path
	 * @return
	 */
	public Character[] getCharacterValues(String path);
}
