package net.canarymod.database.mysql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.canarymod.Canary;
import net.canarymod.database.Column;
import net.canarymod.database.DataAccess;
import net.canarymod.database.Database;
import net.canarymod.database.exceptions.DatabaseAccessException;
import net.canarymod.database.exceptions.DatabaseReadException;
import net.canarymod.database.exceptions.DatabaseTableInconsistencyException;
import net.canarymod.database.exceptions.DatabaseWriteException;

/**
 * Represents access to a MySQL database
 *
 * @author Somners
 */
public class MySQLDatabase extends Database {

    private static MySQLDatabase instance;
    private static MySQLConnectionPool pool;
    private final String LIST_REGEX = "\u00B6";
    private final String NULL_STRING = "NULL";

    private MySQLDatabase() {
        try {
            pool = new MySQLConnectionPool();
        }
        catch (Exception e) {
        }
    }

    public static MySQLDatabase getInstance() {
        if (instance == null) {
            instance = new MySQLDatabase();
        }
        return instance;
    }

    @Override
    public void insert(DataAccess data) throws DatabaseWriteException {
        if (this.doesEntryExist(data)) {
            return;
        }
        Connection conn = pool.getConnectionFromPool();
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
            ps = conn.prepareStatement("INSERT INTO `" + data.getName() + "` (" + fields.toString() + ") VALUES(" + values.toString() + ")");

            int i = 1;
            for (Column c : columns.keySet()) {
                if (!c.autoIncrement()) {
                    if (c.isList()) {
                        ps.setObject(i, this.getString((List<?>) columns.get(c)));
                    }
                    ps.setObject(i, this.convert(columns.get(c)));
                    i++;
                }
            }

            if (ps.executeUpdate() == 0) {
                throw new DatabaseWriteException("Error inserting MySQL: no rows updated!");
            }
        }
        catch (SQLException ex) {
            Canary.logStacktrace(ex.getMessage(), ex);
        }
        catch (DatabaseTableInconsistencyException dtie) {
            Canary.logStacktrace(dtie.getMessage(), dtie);
        }
        finally {
            this.closePS(ps);
            pool.returnConnectionToPool(conn);
        }

    }

    @Override
    public void update(DataAccess data, String[] fieldNames, Object[] fieldValues) throws DatabaseWriteException {
        if (!this.doesEntryExist(data)) {
            return;
        }

        Connection conn = pool.getConnectionFromPool();
        ResultSet rs = null;

        try {
            rs = this.getResultSet(conn, data, fieldNames, fieldValues, true);
            if (rs != null) {
                if (rs.next()) {
                    HashMap<Column, Object> columns = data.toDatabaseEntryList();
                    Iterator<Column> it = columns.keySet().iterator();
                    Column column;
                    while (it.hasNext()) {
                        column = it.next();
                        if (column.isList()) {
                            rs.updateObject(column.columnName(), this.getString((List<?>) columns.get(column)));
                        }
                        else {
                            rs.updateObject(column.columnName(), columns.get(column));
                        }
                    }
                    rs.updateRow();
                }
                else {
                    throw new DatabaseWriteException("Error updating DataAccess to MySQL, no such entry: " + data.toString());
                }
            }
        }
        catch (SQLException ex) {
            Canary.logStacktrace(ex.getMessage(), ex);
        }
        catch (DatabaseTableInconsistencyException dtie) {
            Canary.logStacktrace(dtie.getMessage(), dtie);
        }
        catch (DatabaseReadException ex) {
            Logger.getLogger(MySQLDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                PreparedStatement st = rs != null && rs.getStatement() instanceof PreparedStatement ? (PreparedStatement) rs.getStatement() : null;
                this.closeRS(rs);
                this.closePS(st);
                pool.returnConnectionToPool(conn);
            }
            catch (SQLException ex) {
                Canary.logStacktrace(ex.getMessage(), ex);
            }
        }
    }

    @Override
    public void remove(String tableName, String[] fieldNames, Object[] fieldValues) throws DatabaseWriteException {
        Connection conn = pool.getConnectionFromPool();
        ResultSet rs = null;

        try {
            rs = this.getResultSet(conn, tableName, fieldNames, fieldValues, true);
            if (rs != null) {
                if (rs.next()) {
                    rs.deleteRow();
                }
            }

        }
        catch (DatabaseReadException dre) {
            Canary.logStacktrace(dre.getMessage(), dre);
        }
        catch (SQLException ex) {
            Canary.logStacktrace(ex.getMessage(), ex);
        }
        finally {
            try {
                PreparedStatement st = rs != null && rs.getStatement() instanceof PreparedStatement ? (PreparedStatement) rs.getStatement() : null;
                this.closeRS(rs);
                this.closePS(st);
                pool.returnConnectionToPool(conn);
            }
            catch (SQLException ex) {
                Canary.logStacktrace(ex.getMessage(), ex);
            }
        }
    }

    @Override
    public void load(DataAccess dataset, String[] fieldNames, Object[] fieldValues) throws DatabaseReadException {
        ResultSet rs = null;
        Connection conn = pool.getConnectionFromPool();
        HashMap<String, Object> dataSet = new HashMap<String, Object>();
        try {
            rs = this.getResultSet(conn, dataset, fieldNames, fieldValues, true);
            if (rs != null) {
                if (rs.next()) {
                    for (Column column : dataset.getTableLayout()) {
                        if (column.isList()) {
                            dataSet.put(column.columnName(), this.getList(column.dataType(), rs.getString(column.columnName())));
                        }
                        else if (rs.getObject(column.columnName()) instanceof Boolean) {
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
                PreparedStatement st = rs != null && rs.getStatement() instanceof PreparedStatement ? (PreparedStatement) rs.getStatement() : null;
                this.closeRS(rs);
                this.closePS(st);
                pool.returnConnectionToPool(conn);
            }
            catch (SQLException ex) {
                Canary.logStacktrace(ex.getMessage(), ex);
            }
        }
        try {
            dataset.load(dataSet);
        }
        catch (DatabaseAccessException ex) {
            Canary.logStacktrace(ex.getMessage(), ex);
        }
    }

    @Override
    public void loadAll(DataAccess typeTemplate, List<DataAccess> datasets, String[] fieldNames, Object[] fieldValues) throws DatabaseReadException {
        ResultSet rs = null;
        Connection conn = pool.getConnectionFromPool();
        List<HashMap<String, Object>> stuff = new ArrayList<HashMap<String, Object>>();
        try {
            rs = this.getResultSet(conn, typeTemplate, fieldNames, fieldValues, false);
            if (rs != null) {
                while (rs.next()) {
                    HashMap<String, Object> dataSet = new HashMap<String, Object>();
                    for (Column column : typeTemplate.getTableLayout()) {
                        if (column.isList()) {
                            dataSet.put(column.columnName(), this.getList(column.dataType(), rs.getString(column.columnName())));
                        }
                        else if (rs.getObject(column.columnName()) instanceof Boolean) {
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
                PreparedStatement st = rs != null && rs.getStatement() instanceof PreparedStatement ? (PreparedStatement) rs.getStatement() : null;
                this.closeRS(rs);
                this.closePS(st);
                pool.returnConnectionToPool(conn);
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
        Connection conn = pool.getConnectionFromPool();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // First check if the table exists, if it doesn't we'll skip the rest
            // of this method since we're creating it fresh.
            DatabaseMetaData metadata = conn.getMetaData();
            rs = metadata.getTables(null, null, schemaTemplate.getName(), null);
            if (!rs.first()) {
                this.createTable(schemaTemplate);
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

                for (String col : this.getColumnNames(schemaTemplate)) {
                    if (!toAdd.containsKey(col)) {
                        toRemove.add(col);
                    }
                    else {
                        toAdd.remove(col);
                    }
                }

                for (String name : toRemove) {
                    this.deleteColumn(schemaTemplate.getName(), name);
                }
                for (Map.Entry<String, Column> entry : toAdd.entrySet()) {
                    this.insertColumn(schemaTemplate.getName(), entry.getValue());
                }
            }
        }
        catch (SQLException sqle) {
            throw new DatabaseWriteException("Error updating MySQL schema: " + sqle.getMessage());
        }
        catch (DatabaseTableInconsistencyException dtie) {
            Canary.logStacktrace("Error updating MySQL schema." + dtie.getMessage(), dtie);
        }
        finally {
            this.closeRS(rs);
            this.closePS(ps);
            pool.returnConnectionToPool(conn);
        }
    }

    public void createTable(DataAccess data) throws DatabaseWriteException {
        Connection conn = pool.getConnectionFromPool();
        PreparedStatement ps = null;

        try {
            StringBuilder fields = new StringBuilder();
            HashMap<Column, Object> columns = data.toDatabaseEntryList();
            Iterator<Column> it = columns.keySet().iterator();
            String primary = null;

            Column column;
            while (it.hasNext()) {
                column = it.next();
                fields.append("`").append(column.columnName()).append("` ");
                fields.append(this.getDataTypeSyntax(column.dataType()));
                if (column.autoIncrement()) {
                    fields.append(" AUTO_INCREMENT");
                }
                if (column.columnType().equals(Column.ColumnType.PRIMARY)) {
                    primary = column.columnName();
                }
                if (it.hasNext()) {
                    fields.append(", ");
                }
            }
            if (primary != null) {
                fields.append(", PRIMARY KEY(`").append(primary).append("`)");
            }
            ps = conn.prepareStatement("CREATE TABLE IF NOT EXISTS `" + data.getName() + "` (" + fields.toString() + ") ");
            ps.execute();
        }
        catch (SQLException ex) {
            throw new DatabaseWriteException("Error creating MySQL table '" + data.getName() + "'. " + ex.getMessage());
        }
        catch (DatabaseTableInconsistencyException ex) {
            Canary.logStacktrace(ex.getMessage() + " Error creating MySQL table '" + data.getName() + "'. ", ex);
        }
        finally {
            this.closePS(ps);
            pool.returnConnectionToPool(conn);
        }
    }

    public void insertColumn(String tableName, Column column) throws DatabaseWriteException {
        Connection conn = pool.getConnectionFromPool();
        PreparedStatement ps = null;

        try {
            if (column != null && !column.columnName().trim().equals("")) {
                ps = conn.prepareStatement("ALTER TABLE `" + tableName + "` ADD `" + column.columnName() + "` " + this.getDataTypeSyntax(column.dataType()));
                ps.execute();
            }
        }
        catch (SQLException ex) {
            throw new DatabaseWriteException("Error adding MySQL collumn: " + column.columnName());
        }
        finally {
            this.closePS(ps);
            pool.returnConnectionToPool(conn);
        }

    }

    public void deleteColumn(String tableName, String columnName) throws DatabaseWriteException {
        Connection conn = pool.getConnectionFromPool();
        PreparedStatement ps = null;

        try {
            if (columnName != null && !columnName.trim().equals("")) {
                ps = conn.prepareStatement("ALTER TABLE `" + tableName + "` DROP `" + columnName + "`");
                ps.execute();
            }
        }
        catch (SQLException ex) {
            throw new DatabaseWriteException("Error deleting MySQL collumn: " + columnName);
        }
        finally {
            this.closePS(ps);
            pool.returnConnectionToPool(conn);
        }
    }

    public boolean doesPrimaryKeyExist(DataAccess data, String primaryKey, Object value) throws DatabaseWriteException {
        Connection conn = pool.getConnectionFromPool();
        PreparedStatement ps = null;
        boolean toRet = false;

        try {
            ps = conn.prepareStatement("SELECT * FROM `" + data.getName() + "` WHERE '" + primaryKey + "' = ?");
            ps.setObject(1, this.convert(value));
            toRet = ps.execute();

        }
        catch (SQLException ex) {
            throw new DatabaseWriteException("Error checking Value for MySQL Primary "
                    + "Key in Table `" + data.getName() + "` for key `" + primaryKey
                    + "` and value '" + String.valueOf(value) + "'.");
        }
        finally {
            this.closePS(ps);
            pool.returnConnectionToPool(conn);
        }
        return toRet;
    }

    public boolean doesEntryExist(DataAccess data) throws DatabaseWriteException {
        Connection conn = pool.getConnectionFromPool();
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
                    // if (it.hasNext()) {
                    // sb.append("' = ? AND ");
                    // } else {
                    // sb.append("' = ?");
                    // }
                }
            }
            ps = conn.prepareStatement("SELECT * FROM `" + data.getName() + "` WHERE " + sb.toString());
            it = columns.keySet().iterator();

            int index = 1;
            while (it.hasNext()) {
                column = it.next();
                if (!column.autoIncrement()) {
                    ps.setObject(index, this.convert(columns.get(column)));
                    index++;
                }
            }
            rs = ps.executeQuery();
            if (rs != null) {
                toRet = rs.next();
            }

        }
        catch (SQLException ex) {
            throw new DatabaseWriteException(ex.getMessage() + " Error checking MySQL Entry Key in "
                    + data.toString());
        }
        catch (DatabaseTableInconsistencyException ex) {
            Logger.getLogger(MySQLDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            this.closePS(ps);
            this.closeRS(rs);
            pool.returnConnectionToPool(conn);
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
                if (!rs.isClosed()) {
                    rs.close();
                }
            }
            catch (SQLException sqle) {
                Canary.logStacktrace("Error closing ResultSet in MySQL database.", sqle);
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
                if (!ps.isClosed()) {
                    ps.close();
                }
            }
            catch (SQLException sqle) {
                Canary.logStacktrace("Error closing PreparedStatement in MySQL database.", sqle);
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
                    ps.setObject(i + 1, this.convert(fieldValues[i]));
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
            throw new DatabaseReadException("Error Querying MySQL ResultSet in "
                    + tableName);
        }
        catch (Exception ex) {
            Logger.getLogger(MySQLDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return toRet;
    }

    public List<String> getColumnNames(DataAccess data) {
        Statement statement = null;
        ResultSet resultSet = null;

        ArrayList<String> columns = new ArrayList<String>();
        String columnName;

        Connection connection = pool.getConnectionFromPool();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SHOW COLUMNS FROM `" + data.getName() + "`");
            while (resultSet.next()) {
                columnName = resultSet.getString("field");
                columns.add(columnName);
            }
        }
        catch (SQLException ex) {
            Canary.logStacktrace(ex.getMessage(), ex);
        }
        finally {
            this.closeRS(resultSet);
            if (statement != null) {
                try {
                    statement.close();
                }
                catch (SQLException ex) {
                    Logger.getLogger(MySQLDatabase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            pool.returnConnectionToPool(connection);
        }
        return columns;
    }

    private String getDataTypeSyntax(Column.DataType type) {
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
                return "INT";
            case STRING:
                return "TEXT";
            case BOOLEAN:
                return "BOOLEAN";
        }
        return "";
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
     * Gets a Java List representation from the mysql String.
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
