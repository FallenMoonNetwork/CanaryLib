package net.canarymod.database.sqlite;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.canarymod.Canary;
import net.canarymod.config.Configuration;
import net.canarymod.database.Column;
import net.canarymod.database.Column.DataType;
import static net.canarymod.database.Column.DataType.BOOLEAN;
import static net.canarymod.database.Column.DataType.BYTE;
import static net.canarymod.database.Column.DataType.DOUBLE;
import static net.canarymod.database.Column.DataType.FLOAT;
import static net.canarymod.database.Column.DataType.INTEGER;
import static net.canarymod.database.Column.DataType.LONG;
import static net.canarymod.database.Column.DataType.SHORT;
import static net.canarymod.database.Column.DataType.STRING;
import net.canarymod.database.DataAccess;
import net.canarymod.database.Database;
import net.canarymod.database.exceptions.DatabaseAccessException;
import net.canarymod.database.exceptions.DatabaseReadException;
import net.canarymod.database.exceptions.DatabaseTableInconsistencyException;
import net.canarymod.database.exceptions.DatabaseWriteException;

/**
 * SQLite Database
 *
 * @author Jason (darkdiplomat)
 */
public class SQLiteDatabase extends Database {

    private Connection conn; // One Connection, All the Time
    private static SQLiteDatabase instance;
    private final String LIST_REGEX = "\u00B6";
    private final String NULL_STRING = "NULL";
    private final String database;

    private SQLiteDatabase() {
        File path = new File("db/");

        if (!path.exists()) {
            path.mkdirs();
        }
        database = Configuration.getDbConfig().getDatabaseName();
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + database + ".db");
        }
        catch (Exception ex) {
            Canary.logStacktrace("Failed to create connection to SQLite database", ex);
        }
    }

    public static SQLiteDatabase getInstance() {
        if (SQLiteDatabase.instance == null) {
            SQLiteDatabase.instance = new SQLiteDatabase();
        }
        return SQLiteDatabase.instance;
    }

    @Override
    public void insert(DataAccess data) throws DatabaseWriteException {
        if (doesEntryExist(data)) {
            return;
        }
        PreparedStatement ps = null;

        try {
            StringBuilder fields = new StringBuilder();
            StringBuilder values = new StringBuilder();
            HashMap<Column, Object> columns = data.toDatabaseEntryList();
            Iterator<Column> it = columns.keySet().iterator();

            Column column;
            while (it.hasNext()) {
                column = it.next();
                if (!column.autoIncrement()) {
                    fields.append("`").append(column.columnName()).append("`").append(",");
                    values.append("?").append(",");
                }
            }
            if (fields.length() > 0) {
                fields.deleteCharAt(fields.length() - 1);
            }
            if (values.length() > 0) {
                values.deleteCharAt(values.length() - 1);
            }
            String state = "INSERT INTO `" + data.getName() + "` (" + fields.toString() + ") VALUES(" + values.toString() + ")";
            ps = conn.prepareStatement(state);

            int i = 1;
            for (Column c : columns.keySet()) {
                if (!c.autoIncrement()) {
                    if (c.isList()) {
                        ps.setObject(i, getString((List<?>) columns.get(c)));
                    }
                    ps.setObject(i, convert(columns.get(c)));
                    i++;
                }
            }

            if (ps.executeUpdate() == 0) {
                throw new DatabaseWriteException("Error inserting SQLite: no rows updated!");
            }
        }
        catch (SQLException ex) {
            Canary.logStacktrace(ex.getMessage(), ex);
        }
        catch (DatabaseTableInconsistencyException dtie) {
            Canary.logStacktrace(dtie.getMessage(), dtie);
        }
        finally {
            closePS(ps);
        }

    }

    @Override
    public void update(DataAccess data, String[] fieldNames, Object[] fieldValues) throws DatabaseWriteException {
        if (!doesEntryExist(data)) {
            return;
        }
        PreparedStatement ps = null;
        try {
            String updateClause = "UPDATE " + data.getName() + " SET %s WHERE %s";
            StringBuilder set = new StringBuilder();
            StringBuilder where = new StringBuilder();
            HashMap<Column, Object> columns = data.toDatabaseEntryList();
            Iterator<Column> it = columns.keySet().iterator();
            Column column;

            while (it.hasNext()) {
                column = it.next();
                if (where.length() > 0) {
                    where.append(", ").append(column.columnName());
                }
                else {
                    where.append(column.columnName());
                }
                set.append("=");
                set.append(convert(columns.get(column)));
            }

            for (int index = 0; index < fieldNames.length && index < fieldValues.length; index++) {
                if (where.length() > 0) {
                    where.append(" AND ").append(fieldNames[index]);
                }
                else {
                    where.append(fieldNames[index]);
                }
                where.append("=");
                where.append(fieldValues[index]);
            }

            ps = conn.prepareStatement(String.format(updateClause, set.toString(), where.toString()));
            ps.execute();
        }
        catch (SQLException ex) {
            Canary.logStacktrace(ex.getMessage(), ex);
        }
        catch (DatabaseTableInconsistencyException dbtiex) {
            Canary.logStacktrace(dbtiex.getMessage(), dbtiex);
        }
        finally {
            closePS(ps);
        }
    }

    @Override
    public void remove(String tableName, String[] fieldNames, Object[] fieldValues) throws DatabaseWriteException {
        PreparedStatement ps = null;
        try {
            StringBuilder buildState = new StringBuilder("DELETE FROM " + tableName + " WHERE ");
            for (int index = 0; index < fieldNames.length && index < fieldValues.length; index++) {
                if (buildState.length() > ("DELETE FROM " + tableName + " WHERE ").length()) {
                    buildState.append(" AND ").append(fieldNames[index]);
                }
                else {
                    buildState.append(fieldNames[index]);
                }
                buildState.append("=");
                buildState.append(fieldValues[index]);
            }
            ps = conn.prepareStatement(buildState.toString());
            ps.execute();
        }
        catch (SQLException ex) {
            Canary.logStacktrace(ex.getMessage(), ex);
        }
        finally {
            closePS(ps);
        }
    }

    @Override
    public void load(DataAccess dataset, String[] fieldNames, Object[] fieldValues) throws DatabaseReadException {
        ResultSet rs = null;
        HashMap<String, Object> dataSet = new HashMap<String, Object>();
        try {
            rs = this.getResultSet(conn, dataset, fieldNames, fieldValues, true);
            if (rs != null) {
                if (rs.next()) {
                    for (Column column : dataset.getTableLayout()) {
                        if (column.isList()) {
                            dataSet.put(column.columnName(), getList(column.dataType(), rs.getString(column.columnName())));
                        }
                        else if (column.dataType() == DataType.BOOLEAN) {
                            dataSet.put(column.columnName(), rs.getBoolean(column.columnName()));
                        }
                        else {
                            dataSet.put(column.columnName(), rs.getObject(column.columnName()));
                        }
                    }
                }
            }
        }
        catch (DatabaseReadException dre) {
            Canary.logStacktrace(dre.getMessage(), dre);
        }
        catch (SQLException ex) {
            Canary.logStacktrace(ex.getMessage(), ex);
        }
        catch (DatabaseTableInconsistencyException dtie) {
            Canary.logStacktrace(dtie.getMessage(), dtie);
        }
        finally {
            try {
                if (rs != null) {
                    PreparedStatement st = rs.getStatement() instanceof PreparedStatement ? (PreparedStatement) rs.getStatement() : null;
                    closeRS(rs);
                    closePS(st);
                }
            }
            catch (SQLException ex) {
                Canary.logStacktrace(ex.getMessage(), ex);
            }
        }
        try {
            if (!dataSet.isEmpty()) {
                dataset.load(dataSet);
            }
        }
        catch (DatabaseAccessException ex) {
            Canary.logStacktrace(ex.getMessage(), ex);
        }
    }

    @Override
    public void loadAll(DataAccess typeTemplate, List<DataAccess> datasets, String[] fieldNames, Object[] fieldValues) throws DatabaseReadException {
        ResultSet rs = null;
        List<HashMap<String, Object>> stuff = new ArrayList<HashMap<String, Object>>();
        try {
            rs = this.getResultSet(conn, typeTemplate, fieldNames, fieldValues, false);
            if (rs != null) {
                while (rs.next()) {
                    HashMap<String, Object> dataSet = new HashMap<String, Object>();
                    for (Column column : typeTemplate.getTableLayout()) {
                        if (column.isList()) {
                            dataSet.put(column.columnName(), getList(column.dataType(), rs.getString(column.columnName())));
                        }
                        else if (column.dataType() == DataType.BOOLEAN) {
                            dataSet.put(column.columnName(), rs.getBoolean(column.columnName()));
                        }
                        else {
                            dataSet.put(column.columnName(), rs.getObject(column.columnName()));
                        }
                    }
                    stuff.add(dataSet);
                }
            }

        }
        catch (DatabaseReadException dre) {
            Canary.logStacktrace(dre.getMessage(), dre);
        }
        catch (SQLException ex) {
            Canary.logStacktrace(ex.getMessage(), ex);
        }
        catch (DatabaseTableInconsistencyException dtie) {
            Canary.logStacktrace(dtie.getMessage(), dtie);
        }
        finally {
            try {
                if (rs != null) {
                    PreparedStatement st = rs.getStatement() instanceof PreparedStatement ? (PreparedStatement) rs.getStatement() : null;
                    closeRS(rs);
                    closePS(st);
                }
            }
            catch (SQLException ex) {
                Canary.logStacktrace(ex.getMessage(), ex);
            }
        }
        try {
            for (HashMap<String, Object> temp : stuff) {
                DataAccess newData = typeTemplate.getInstance();
                newData.load(temp);
                datasets.add(newData);
            }

        }
        catch (DatabaseAccessException dae) {
            Canary.logStacktrace(dae.getMessage(), dae);
        }
    }

    @Override
    public void updateSchema(DataAccess schemaTemplate) throws DatabaseWriteException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // First check if the table exists, if it doesn't we'll skip the rest
            // of this method since we're creating it fresh.
            DatabaseMetaData metadata = conn.getMetaData();
            rs = metadata.getTables(null, null, schemaTemplate.getName(), null);
            if (!rs.next()) {
                createTable(schemaTemplate);
            }
            else {

                LinkedList<String> toRemove = new LinkedList<String>();
                HashMap<String, Column> toAdd = new HashMap<String, Column>();
                Iterator<Column> it = schemaTemplate.getTableLayout().iterator();

                Column column;
                while (it.hasNext()) {
                    column = it.next();
                    toAdd.put(column.columnName(), column);
                }

                for (String col : getColumnNames(schemaTemplate)) {
                    if (!toAdd.containsKey(col)) {
                        toRemove.add(col);
                    }
                    else {
                        toAdd.remove(col);
                    }
                }

                for (String name : toRemove) {
                    deleteColumn(schemaTemplate.getName(), name);
                }
                for (Map.Entry<String, Column> entry : toAdd.entrySet()) {
                    insertColumn(schemaTemplate.getName(), entry.getValue());
                }
            }
        }
        catch (SQLException sqle) {
            throw new DatabaseWriteException("Error updating SQLite schema: " + sqle.getMessage());
        }
        catch (DatabaseTableInconsistencyException dtie) {
            Canary.logStacktrace("Error updating SQLite schema." + dtie.getMessage(), dtie);
        }
        finally {
            closeRS(rs);
            closePS(ps);
        }
    }

    public void createTable(DataAccess data) throws DatabaseWriteException {
        PreparedStatement ps = null;

        try {
            StringBuilder fields = new StringBuilder();
            HashMap<Column, Object> columns = data.toDatabaseEntryList();
            Iterator<Column> it = columns.keySet().iterator();
            Column column;
            while (it.hasNext()) {
                column = it.next();
                fields.append("`").append(column.columnName()).append("` ");
                if (column.columnType().equals(Column.ColumnType.PRIMARY) && column.autoIncrement() && column.dataType() == Column.DataType.INTEGER) {
                    fields.append(" INTEGER PRIMARY KEY ASC");
                    if (it.hasNext()) {
                        fields.append(", ");
                    }
                    continue;
                }
                else {
                    fields.append(getDataTypeSyntax(column.dataType()));
                }

                if (column.columnType() == Column.ColumnType.PRIMARY) {
                    fields.append(" PRIMARY KEY");
                }
                if (column.autoIncrement()) {
                    fields.append(" AUTOINCREMENT");
                }
                if (it.hasNext()) {
                    fields.append(", ");
                }
            }
            String state = "CREATE TABLE IF NOT EXISTS `" + data.getName() + "` (" + fields.toString() + ") ";
            ps = conn.prepareStatement(state);
            if (ps.execute()) {
                Canary.logDebug("Statment Executed!");
            }
        }
        catch (SQLException ex) {
            throw new DatabaseWriteException("Error creating SQLite table '" + data.getName() + "'. " + ex.getMessage());
        }
        catch (DatabaseTableInconsistencyException ex) {
            Canary.logStacktrace(ex.getMessage() + " Error creating SQLite table '" + data.getName() + "'. ", ex);
        }
        finally {
            closePS(ps);
        }
    }

    public void insertColumn(String tableName, Column column) throws DatabaseWriteException {
        PreparedStatement ps = null;

        try {
            if (column != null && !column.columnName().trim().equals("")) {
                ps = conn.prepareStatement("ALTER TABLE `" + tableName + "` ADD `" + column.columnName() + "` " + getDataTypeSyntax(column.dataType()));
                ps.execute();
            }
        }
        catch (SQLException ex) {
            throw new DatabaseWriteException("Error adding SQLite collumn: " + column.columnName());
        }
        finally {
            closePS(ps);
        }

    }

    public void deleteColumn(String tableName, String columnName) throws DatabaseWriteException {
        PreparedStatement ps = null;

        try {
            if (columnName != null && !columnName.trim().equals("")) {
                ps = conn.prepareStatement("ALTER TABLE `" + tableName + "` DROP `" + columnName + "`");
                ps.execute();
            }
        }
        catch (SQLException ex) {
            throw new DatabaseWriteException("Error deleting SQLite collumn: " + columnName);
        }
        finally {
            closePS(ps);
        }
    }

    public boolean doesPrimaryKeyExist(DataAccess data, String primaryKey, Object value) throws DatabaseWriteException {
        PreparedStatement ps = null;
        boolean toRet = false;

        try {
            ps = conn.prepareStatement("SELECT * FROM `" + data.getName() + "` WHERE '" + primaryKey + "' = ?");
            ps.setObject(1, convert(value));
            toRet = ps.execute();

        }
        catch (SQLException ex) {
            throw new DatabaseWriteException("Error checking Value for SQLite Primary "
                    + "Key in Table `" + data.getName() + "` for key `" + primaryKey
                    + "` and value '" + String.valueOf(value) + "'.");
        }
        finally {
            closePS(ps);
        }
        return toRet;
    }

    public boolean doesEntryExist(DataAccess data) throws DatabaseWriteException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean toRet = false;

        try {
            StringBuilder sb = new StringBuilder();
            HashMap<Column, Object> columns = data.toDatabaseEntryList();
            Iterator<Column> it = columns.keySet().iterator();

            Column column;
            while (it.hasNext()) {
                column = it.next();
                if (!column.autoIncrement()) {
                    if (sb.length() > 0) {
                        sb.append(" AND '").append(column.columnName());
                    }
                    else {
                        sb.append("'").append(column.columnName());
                    }
                    sb.append("' = ?");
                }
            }
            ps = conn.prepareStatement("SELECT * FROM `" + data.getName() + "` WHERE " + sb.toString());
            it = columns.keySet().iterator();

            int index = 1;
            while (it.hasNext()) {
                column = it.next();
                if (!column.autoIncrement()) {
                    ps.setObject(index, convert(columns.get(column)));
                    index++;
                }
            }
            rs = ps.executeQuery();
            if (rs != null) {
                toRet = rs.next();
            }

        }
        catch (SQLException ex) {
            throw new DatabaseWriteException(ex.getMessage() + " Error checking SQLite Entry Key in "
                    + data.toString());
        }
        catch (DatabaseTableInconsistencyException ex) {
            Logger.getLogger(SQLiteDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            closePS(ps);
            closeRS(rs);
        }
        return toRet;
    }

    /**
     * Safely Close a ResultSet.
     *
     * @param rs
     *         ResultSet to close.
     */
    public void closeRS(ResultSet rs) {
        if (rs != null) {
            try {
                // org.sqlite.RS doesn't have a isClosed()
                rs.close();
            }
            catch (SQLException sqle) {
                Canary.logStacktrace("Error closing ResultSet in SQLite database.", sqle);
            }
        }
    }

    /**
     * Safely Close a PreparedStatement.
     *
     * @param ps
     *         PreparedStatement to close.
     */
    public void closePS(PreparedStatement ps) {
        if (ps != null) {
            try {
                // org.sqlite.PrepStmt doesn't have isClosed()
                ps.close();
            }
            catch (SQLException sqle) {
                Canary.logStacktrace("Error closing PreparedStatement in SQLite database.", sqle);
            }
        }
    }

    public ResultSet getResultSet(Connection conn, DataAccess data, String[] fieldNames, Object[] fieldValues, boolean limitOne) throws DatabaseReadException {
        return this.getResultSet(conn, data.getName(), fieldNames, fieldValues, limitOne);
    }

    public ResultSet getResultSet(Connection conn, String tableName, String[] fieldNames, Object[] fieldValues, boolean limitOne) throws DatabaseReadException {
        PreparedStatement ps;
        ResultSet toRet = null;

        try {
            if (fieldNames.length > 0) {
                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < fieldNames.length && i < fieldValues.length; i++) {
                    sb.append("`").append(fieldNames[i]);
                    if (i + 1 < fieldNames.length) {
                        sb.append("`=? AND ");
                    }
                    else {
                        sb.append("`=?");
                    }
                }
                if (limitOne) {
                    sb.append(" LIMIT 1");
                }
                ps = conn.prepareStatement("SELECT * FROM `" + tableName + "` WHERE " + sb.toString());
                for (int i = 0; i < fieldNames.length && i < fieldValues.length; i++) {
                    ps.setObject(i + 1, convert(fieldValues[i]));
                }
                toRet = ps.executeQuery();
            }
            else {
                if (limitOne) {
                    ps = conn.prepareStatement("SELECT * FROM `" + tableName + "` LIMIT 1");
                }
                else {
                    ps = conn.prepareStatement("SELECT * FROM `" + tableName + "`");
                }

                toRet = ps.executeQuery();
            }
        }
        catch (SQLException ex) {
            throw new DatabaseReadException("Error Querying SQLite ResultSet in "
                    + tableName);
        }
        catch (Exception ex) {
            Logger.getLogger(SQLiteDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return toRet;
    }

    public List<String> getColumnNames(DataAccess data) {
        Statement statement = null;
        ResultSet resultSet = null;

        ArrayList<String> columns = new ArrayList<String>();
        String columnName;

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM '" + data.getName() + "'");
            ResultSetMetaData rsMeta = resultSet.getMetaData();
            for (int index = 1; index <= rsMeta.getColumnCount(); index++) {
                columnName = rsMeta.getColumnLabel(index);
                columns.add(columnName);
            }
        }
        catch (SQLException ex) {
            Canary.logStacktrace(ex.getMessage(), ex);
        }
        finally {
            closeRS(resultSet);
            if (statement != null) {
                try {
                    statement.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(SQLiteDatabase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return columns;
    }

    public String getDataTypeSyntax(Column.DataType type) {
        switch (type) {
            case BYTE:
                return "INT";
            case INTEGER:
                return "INT";
            case FLOAT:
                return "DOUBLE";
            case DOUBLE:
                return "DOUBLE";
            case LONG:
                return "BIGINT";
            case SHORT:
                return "TINYINT";
            case STRING:
                return "TEXT";
            case BOOLEAN:
                return "BOOLEAN";
        }
        return "";
    }

    public int getJDBCDataType(Column.DataType type) {
        switch (type) {
            case BYTE:
                return Types.INTEGER;
            case INTEGER:
                return Types.INTEGER;
            case FLOAT:
                return Types.DOUBLE;
            case DOUBLE:
                return Types.DOUBLE;
            case LONG:
                return Types.BIGINT;
            case SHORT:
                return Types.TINYINT;
            case STRING:
                return Types.BLOB;
            case BOOLEAN:
                return Types.TINYINT;
        }
        return 0;
    }

    public int getJDBCDataType(Object o) {
        if (o instanceof Byte) {
            return Types.INTEGER;
        }
        else if (o instanceof Integer) {
            return Types.INTEGER;
        }
        else if (o instanceof Float) {
            return Types.DOUBLE;
        }
        else if (o instanceof Double) {
            return Types.DOUBLE;
        }
        else if (o instanceof Long) {
            return Types.BIGINT;
        }
        else if (o instanceof Short) {
            return Types.TINYINT;
        }
        else if (o instanceof String) {
            return Types.BLOB;
        }
        else if (o instanceof Boolean) {
            return Types.TINYINT;
        }
        return 0;
    }

    /**
     * Replaces '*' character with '\\*' if the Object is a String.
     *
     * @param o
     *
     * @return
     */
    private Object convert(Object o) {
        if (o instanceof String && ((String) o).contains("*")) {
            ((String) o).replace("*", "\\*");
        }
        return o;
    }

    /**
     * Gets a Java List representation from the SQLite String.
     *
     * @param type
     * @param field
     *
     * @return
     */
    private List<Comparable<?>> getList(Column.DataType type, String field) {
        List<Comparable<?>> list = new ArrayList<Comparable<?>>();
        if (field == null) {
            return list;
        }
        switch (type) {
            case BYTE:
                for (String s : field.split(this.LIST_REGEX)) {
                    if (s.equals(NULL_STRING)) {
                        list.add(null);
                        continue;
                    }
                    list.add(Byte.valueOf(s));
                }
                break;
            case INTEGER:
                for (String s : field.split(this.LIST_REGEX)) {
                    if (s.equals(NULL_STRING)) {
                        list.add(null);
                        continue;
                    }
                    list.add(Integer.valueOf(s));
                }
                break;
            case FLOAT:
                for (String s : field.split(this.LIST_REGEX)) {
                    if (s.equals(NULL_STRING)) {
                        list.add(null);
                        continue;
                    }
                    list.add(Float.valueOf(s));
                }
                break;
            case DOUBLE:
                for (String s : field.split(this.LIST_REGEX)) {
                    if (s.equals(NULL_STRING)) {
                        list.add(null);
                        continue;
                    }
                    list.add(Double.valueOf(s));
                }
                break;
            case LONG:
                for (String s : field.split(this.LIST_REGEX)) {
                    if (s.equals(NULL_STRING)) {
                        list.add(null);
                        continue;
                    }
                    list.add(Long.valueOf(s));
                }
                break;
            case SHORT:
                for (String s : field.split(this.LIST_REGEX)) {
                    if (s.equals(NULL_STRING)) {
                        list.add(null);
                        continue;
                    }
                    list.add(Short.valueOf(s));
                }
                break;
            case STRING:
                for (String s : field.split(this.LIST_REGEX)) {
                    if (s.equals(NULL_STRING)) {
                        list.add(null);
                        continue;
                    }
                    list.add(s);
                }
                break;
            case BOOLEAN:
                for (String s : field.split(this.LIST_REGEX)) {
                    if (s.equals(NULL_STRING)) {
                        list.add(null);
                        continue;
                    }
                    list.add(Boolean.valueOf(s));
                }
                break;
        }
        return list;
    }

    /**
     * Get the database entry for a Java List.
     *
     * @param list
     *
     * @return a string representation of the passed list.
     */
    public String getString(List<?> list) {
        StringBuilder sb = new StringBuilder();
        Iterator<?> it = list.iterator();
        while (it.hasNext()) {
            Object o = it.next();
            if (o == null) {
                sb.append(NULL_STRING);
            }
            else {
                sb.append(String.valueOf(o));
            }
            if (it.hasNext()) {
                sb.append(this.LIST_REGEX);
            }
        }
        return sb.toString();
    }
}
