package net.canarymod.database;

import java.util.HashMap;
import java.util.List;

import net.canarymod.Canary;
import net.canarymod.config.Configuration;
import net.canarymod.database.exceptions.DatabaseException;
import net.canarymod.database.exceptions.DatabaseReadException;
import net.canarymod.database.exceptions.DatabaseWriteException;
import net.canarymod.database.mysql.MySQLDatabase;
import net.canarymod.database.sqlite.SQLiteDatabase;
import net.canarymod.database.xml.XmlDatabase;

/**
 * A database representation, used to store any kind of data
 *
 * @author Chris (damagefilter)
 */
public abstract class Database {

    /**
     * The datasource type
     *
     * @author chris
     */
    public static class Type {
        private static HashMap<String, Database> registeredDatabases = new HashMap<String, Database>();

        public static void registerDatabase(String name, Database db) throws DatabaseException {
            if (registeredDatabases.containsKey(name)) {
                throw new DatabaseException(name + " cannot be registered. Type already exists");
            }
            registeredDatabases.put(name, db);
        }

        public static Database getDatabaseFromType(String name) {
            return registeredDatabases.get(name);
        }

        static {
            try {
                Database.Type.registerDatabase("xml", XmlDatabase.getInstance());
                Database.Type.registerDatabase("mysql", MySQLDatabase.getInstance());
                Database.Type.registerDatabase("sqlite", SQLiteDatabase.getInstance());
            }
            catch (DatabaseException e) {
            }
        }
    }

    public static Database get() {
        Database ret = Database.Type.getDatabaseFromType(Configuration.getServerConfig().getDatasourceType());
        if (ret != null) {
            return ret;
        }
        else {
            Canary.logWarning("Database type " + Configuration.getServerConfig().getDatasourceType() + " is not available, falling back to XML! Fix your server.cfg");
            return XmlDatabase.getInstance();
        }
    }

    /**
     * Insert the given DataAccess object as new set of data into database
     *
     * @param data
     *         the data to insert
     *
     * @throws DatabaseWriteException
     *         when something went wrong during the write operation
     */
    public abstract void insert(DataAccess data) throws DatabaseWriteException;

    /**
     * Updates the record in the database that fits to your fields and values given.
     * Those are NOT the values and fields to update. Those are values and fields to identify
     * the correct entry in the database to update. The updated data must be provided in the DataAccess
     *
     * @param data
     *         the data to be updated
     * @param fieldNames
     *         the string array of field names
     * @param fieldValues
     *         the object array of field values
     *
     * @throws DatabaseWriteException
     */
    public abstract void update(DataAccess data, String[] fieldNames, Object[] fieldValues) throws DatabaseWriteException;

    /**
     * Removes the data set from the given table that suits the given field names and values
     *
     * @param tableName
     *         the name of the table
     * @param fieldNames
     *         the string array of field names
     * @param fieldValues
     *         the object array of field values
     *
     * @throws DatabaseWriteException
     */
    public abstract void remove(String tableName, String[] fieldNames, Object[] fieldValues) throws DatabaseWriteException;

    /**
     * This method will fill your DataAccess object with the first data set from database,
     * that matches the given values in the given fields.<br>
     * For instance if you pass String[] {"score", "name"}<br>
     * with respective values Object[] {130, "damagefilter"},<br>
     * Canary will look in the database for records where score=130 and name=damagefilter.<br>
     * Canary will only look in the table with the name you have set in your AccessObject<br>
     *
     * @param dataset
     *         The class of your DataAccess object
     * @param fieldNames
     *         Fields names to look for in the database
     * @param fieldValues
     *         Respective values of the fields to look for
     *
     * @throws DatabaseReadException
     */
    public abstract void load(DataAccess dataset, String[] fieldNames, Object[] fieldValues) throws DatabaseReadException;

    /**
     * Loads all results that match the field - values given into a DataAccess list.
     *
     * @param typeTemplate
     *         The type template (an instance of the dataaccess type you wanna load)
     * @param datasets
     *         DataAccess set - you can savely cast those to the type of typeTemplate
     * @param fieldNames
     *         Field names to look for
     * @param fieldValues
     *         Values for the respective fields
     *
     * @throws DatabaseReadException
     */
    public abstract void loadAll(DataAccess typeTemplate, List<DataAccess> datasets, String[] fieldNames, Object[] fieldValues) throws DatabaseReadException;

    /**
     * Updates the database table fields for the given DataAccess object.
     * This method will remove fields that aren't there anymore and add new ones if applicable.
     *
     * @param schemaTemplate
     *         the new schema
     */
    public abstract void updateSchema(DataAccess schemaTemplate) throws DatabaseWriteException;
}
