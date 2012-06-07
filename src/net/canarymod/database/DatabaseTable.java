package net.canarymod.database;

/**
 * An interface representing a table of a database
 * 
 * The table has columns and rows. Each column has a name and a type. Selection
 * of rows is by row number, starting at 1. Selection of cells is by row and
 * column name.
 * 
 * @author Jos Kuijpers
 */
public interface DatabaseTable {

    public enum ColumnType {
        STRING, INTEGER, FLOAT, DOUBLE, BOOLEAN, LONG, CHARACTER
    }

    /**
     * Get the tablename
     * 
     * @return
     */
    public String getName();

    /**
     * Set the tablename
     * 
     * @param name
     */
    public void setName(String name);

    /**
     * Get the table description
     * 
     * @return
     */
    public String getDescription();

    /**
     * Set the table description
     * 
     * @param description
     */
    public void setDescription(String description);

    /**
     * Get the number of rows in the table
     * 
     * @return
     */
    public int getNumRows();

    /**
     * Get a specific row
     * 
     * Row numbers start at 1
     * 
     * @param row
     * @return a mutable IDatabaseRow object, usable for changing data
     */
    public DatabaseRow getRow(int row);

    /**
     * Return all rows in the table
     * 
     * @return an array of IDatabaseRow objects, usable for changing data
     */
    public DatabaseRow[] getAllRows();

    /**
     * Return an array of rows where the content of the cell in column 'column'
     * is 'value'
     * 
     * @param column
     * @param value
     * @return
     */
    public DatabaseRow[] getFilteredRows(String column, String value);

    /**
     * Check whether a row exists with given data
     * @param column
     * @param value
     * @return true if such row exists, false otherwise
     */
    public boolean rowExists(String column, String value);
    
    /**
     * Add a new row to the table.
     * 
     * @param row
     */
    public DatabaseRow addRow();

    /**
     * Remove the specified row
     * 
     * @param row
     */
    public void removeRow(DatabaseRow row);

    /**
     * Remove the specified row
     * 
     * @param row
     */
    public void removeRow(int row);

    /**
     * Get the number of columns in this table
     * 
     * @return
     */
    public int getNumColumns();

    /**
     * Get all column names
     * 
     * @return
     */
    public String[] getAllColumns();

    /**
     * Add a column with specified name and type to the end of the column-list
     * 
     * @param name
     * @param type
     */
    public void appendColumn(String name, ColumnType type);

    /**
     * Remove the specified column and all the row-data in this column
     * 
     * @param name
     */
    public void removeColumn(String name);

    /**
     * Inserts a column with the specified name and type, next to the column
     * specified by after.
     * 
     * When after is null, the column is placed in front
     * 
     * @param name
     * @param type
     * @param after
     */
    public void insertColumn(String name, ColumnType type, String after);

    /**
     * Removes all rows from the table
     */
    public void truncateTable();
}
