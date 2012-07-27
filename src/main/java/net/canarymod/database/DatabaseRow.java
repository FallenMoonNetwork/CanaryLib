package net.canarymod.database;

/**
 * A representation of a row in a database
 * 
 * A row is part of a table and contains cells. A cell is strongly typed, so
 * getters and setters on invalid typed-columns fail and throw an exception.
 * 
 * @author Jos Kuijpers
 */
public interface DatabaseRow {
    
    /**
     * Get the ID of the row
     * 
     * @return
     */
    public int getRowID();

    /**
     * Get the number of cells in the row. Is the same as number of columns in
     * table.
     * 
     * @return
     */
    public int getNumCells();

    /**
     * Get the table this row is in
     * 
     * @return
     */
    public DatabaseTable getTable();

    /**
     * Get a cell value as String
     * 
     * @param column
     * @return String the cell value
     */
    public String getStringCell(String column);
    
    /**
     * Get a cell value as Object
     * @param column
     * @return Object the cell value
     */
    public Object getObjectCell(String column);

    /**
     * Set a cell value as string
     * 
     * @param column
     * @param value
     */
    public void setStringCell(String column, String value);

    /**
     * Get a cell value as integer
     * 
     * @param column
     * @return
     */
    public Integer getIntCell(String column);

    /**
     * Set a cell value as integer
     * 
     * @param column
     * @param value
     */
    public void setIntCell(String column, Integer value);

    /**
     * Get a cell value as float
     * 
     * @param column
     * @return
     */
    public Float getFloatCell(String column);

    /**
     * Set a cell value as float
     * 
     * @param column
     * @param value
     */
    public void setFloatCell(String column, Float value);

    /**
     * Get a cell value as double
     * 
     * @param column
     * @return
     */
    public Double getDoubleCell(String column);

    /**
     * Set a cell value as double
     * 
     * @param column
     * @param value
     */
    public void setDoubleCell(String column, Double value);

    /**
     * Get a cell value as boolean
     * 
     * @param column
     * @return
     */
    public Boolean getBooleanCell(String column);

    /**
     * Get a cell value as boolean, or defaults when it fails to get the value
     * 
     * @param column
     * @param defaults
     * @return
     */
    public boolean getBooleanCell(String column, Boolean defaults);

    /**
     * Set a cell value as boolean
     * 
     * @param column
     * @param value
     */
    public void setBooleanCell(String column, Boolean value);

    /**
     * Get a cell value as long
     * 
     * @param column
     * @return
     */
    public Long getLongCell(String column);

    /**
     * Set a cell value as long
     * 
     * @param column
     * @param value
     */
    public void setLongCell(String column, Long value);

    /**
     * Get a cell value as character
     * 
     * @param column
     * @return
     */
    public Character getCharacter(String column);

    /**
     * Set a cell value as character
     * 
     * @param column
     * @param value
     */
    public void setCharacter(String column, Character value);
}
