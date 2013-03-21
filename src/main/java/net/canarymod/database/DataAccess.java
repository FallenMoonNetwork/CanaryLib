package net.canarymod.database;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;

import net.canarymod.Logman;
import net.canarymod.ToolBox;

public abstract class DataAccess {
    
    protected String tableName;
    private boolean isInconsistent = false;
    private boolean isLoaded = false;
    private boolean hasData = false;
    
    /**
     * Construct a new DataAccess object that represents a table 
     * in the database with the given name. This AccessObject is empty after it has been created.
     * You need to get data from Database and load it. CanaryMod will do this for you.
     * For this simply call Canary.db().load(yourDataAccess, String[]lookupFields, Object[]whereData);
     * This will fill your AccessObject.
     * @param tableName The table name
     */
    public DataAccess(String tableName) {
        this.tableName = tableName;
        createFile();
    }
    
    /**
     * Load a Data set into this DataAccess object
     * @param tableName
     * @throws DatabaseAccessException  
     */
    public final void load(HashMap<String, Object> dataSet) throws DatabaseAccessException {
        try {
            applyDataSet(dataSet);
        }
        catch(IllegalAccessException e) {
            throw new DatabaseAccessException(e.getMessage());
        }
        catch(IllegalArgumentException e) {
            throw new DatabaseAccessException(e.getMessage());
        }
        if(dataSet.size() > 0) {
            hasData = true;
        }
        isLoaded = true;
    }
    /**
     * Creates a HashMap containing all relevant fields for the database, which will then
     * be saved into the database along with their values
     * @return
     * @throws DatabaseTableInconsistencyException
     */
    public final HashMap<Column, Object> toDatabaseEntryList() throws DatabaseTableInconsistencyException {
        Field[] fields = ToolBox.safeArrayMerge(getClass().getFields(), getClass().getDeclaredFields(), new Field[1]);
        HashMap<Column, Object> fieldMap = new HashMap<Column, Object>(fields.length);
        for(Field field : fields) {
            Column colInfo = field.getAnnotation(Column.class);
            if(colInfo == null) {
                //Not what we're looking for
                continue;
            }
            if(fieldMap.containsKey(colInfo)) {
                //Seriously ...
                isInconsistent = true;
                throw new DatabaseTableInconsistencyException("Found duplicate column field: " + colInfo.columnName());
            }
            try {
                fieldMap.put(colInfo, field.get(this));
            } 
            catch (IllegalArgumentException e) {
                Logman.logStackTrace(e.getMessage(), e);
            } 
            catch (IllegalAccessException e) {
                isInconsistent = true;
                throw new DatabaseTableInconsistencyException("Could not access an annotated column field: " + field.getName());
            } 
        }
        return fieldMap;
    }
    
    public final void applyDataSet(HashMap<String, Object> dataSet) throws DatabaseAccessException, IllegalArgumentException, IllegalAccessException {
        Field[] fields = ToolBox.safeArrayMerge(getClass().getFields(), getClass().getDeclaredFields(), new Field[1]);
        int columnFields = 0;
        
        for(Field field : fields) {
            Column col = field.getAnnotation(Column.class);
            if(col == null) {
                continue;
            }
            if(!dataSet.containsKey(col.columnName())) {
                isInconsistent = true;
                throw new DatabaseAccessException("Cannot apply data to "+ getClass().getSimpleName() + ". Column name mismatches! (" + col.columnName() +" does not exist) - " + dataSet.keySet().toString());
            }
            field.set(this, dataSet.get(col.columnName()));
            columnFields++;
        }
        //If the columnFields is not the size of the dataSet,
        //There is either excess data or data that has not been put in the AccessObject.
        //This causes inconsistency and therefore must throw an exception
        if(columnFields != dataSet.size()) {
            isInconsistent = true;
            throw new DatabaseAccessException("Supplied Data set cannot be applied to this DataAccess("+ getClass().getSimpleName() +"). Column count mismatches!");
        }
    }
    
    /**
     * Gets the table layout. That is: all column annotations in this class that make up the table
     * @return
     * @throws DatabaseTableInconsistencyException
     */
    public final HashSet<Column> getTableLayout() throws DatabaseTableInconsistencyException {
        Field[] fields = ToolBox.safeArrayMerge(getClass().getFields(), getClass().getDeclaredFields(), new Field[1]);
        HashSet<Column> layout = new HashSet<Column>(fields.length);
        for(Field field : fields) {
            if(field == null) {
                throw new DatabaseTableInconsistencyException("A field of " + getClass().getSimpleName() + " is not initialized, check your DataAccess!");
            }
            Column colInfo = field.getAnnotation(Column.class);
            if(colInfo == null) {
                //Not what we're looking for
                continue;
            }
            if(layout.contains(colInfo)) {
                //Dude!
                isInconsistent = true;
                throw new DatabaseTableInconsistencyException("Found duplicate column field: " + colInfo.columnName());
            }
            layout.add(colInfo);
        }
        return layout;
    }
    
    /**
     * This shall return the name of the Table this DataAccess belongs to
     * @return
     */
    public String getName() {
        return tableName;
    }
    
    /**
     * Returns true if this DataAccess object has been marked as inconsistent.
     * Inconsistent DataAccess objects will not be saved into the database.
     * This is probably rarely going to be true but it's an extra security measure
     * to keep the data safe and consistent
     * @return
     */
    public boolean isInconsistent() {
        return isInconsistent;
    }
    
    /**
     * Check if this DataAccess has been loaded properly
     * @return
     */
    public boolean isLoaded() {
        return isLoaded;
    }
    
    /**
     * Check if there is data in this DataAccess object.
     * This will also return false if there was an exception while
     * the data has been loaded
     * @return
     */
    public boolean hasData() {
        return hasData;
    }
    
    /**
     * Makes sure the database file for this DataAccess exists before anythign starts to use it
     */
    private void createFile() {
        try {
            Database.get().updateSchema(this);
        } catch (DatabaseWriteException e) {
            Logman.logStackTrace(e.getMessage(), e);
        }
    }
}
