package net.canarymod.database.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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
 *
 * @author Somners
 */
public class MySQLDatabase extends Database {

    private static MySQLDatabase instance;
    private static MySQLConnectionPool pool;

    private MySQLDatabase() {
        try {
            pool = new MySQLConnectionPool();
        }
        catch(Exception e) {
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

            for (Column column = it.next(); it.hasNext(); it.next()) {
                fields.append("`").append(column.columnName());
                if (it.hasNext()) {
                    fields.append("`, ");
                    values.append("?, ");
                } else {
                    fields.append("`");
                    values.append("?");
                }
            }
            ps = conn.prepareStatement("INSERT INTO `" + data.getName() + "` (" + fields.toString() + ") VALUES(" + values.toString() + ")");

            int i = 1;
            for (Column column : columns.keySet()) {
                ps.setObject(i, columns.get(column));
                i++;
            }

            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new DatabaseWriteException("Error inserting");
        } catch (DatabaseTableInconsistencyException dtie) {
            Canary.logStackTrace(dtie.getMessage(), dtie);
        } finally {
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
        PreparedStatement ps = null;

        try {
            rs = this.getResultSet(conn, data, fieldNames, fieldValues);
            if (rs == null) {
                return;
            }
            rs.next();
            HashMap<Column, Object> columns = data.toDatabaseEntryList();
            Iterator<Column> it = columns.keySet().iterator();
            for (Column column = it.next(); it.hasNext(); it.next()) {
                rs.updateObject(column.columnName(), columns.get(column));
            }
            rs.updateRow();
        } catch (SQLException ex) {
            throw new DatabaseWriteException("Error updating DataAccess to MySQL: " + data.toString());
        } catch (DatabaseTableInconsistencyException dtie) {
            Canary.logStackTrace(dtie.getMessage(), dtie);
        } catch (DatabaseReadException ex) {
            Logger.getLogger(MySQLDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.closePS(ps);
            pool.returnConnectionToPool(conn);
        }
    }

    @Override
    public void remove(String tableName, String[] fieldNames, Object[] fieldValues) throws DatabaseWriteException {
        Connection conn = pool.getConnectionFromPool();
        ResultSet rs = null;

        try {
            rs = this.getResultSet(conn, tableName, fieldNames, fieldValues);
            if (rs == null) {
                return;
            } else {
                rs.next();
                rs.deleteRow();
            }

        } catch (DatabaseReadException dre) {
            Canary.logStackTrace(dre.getMessage(), dre);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.closeRS(rs);
            pool.returnConnectionToPool(conn);
        }
    }

    @Override
    public void load(DataAccess dataset, String[] fieldNames, Object[] fieldValues) throws DatabaseReadException {
        Connection conn = pool.getConnectionFromPool();
        ResultSet rs = null;
        HashMap<String, Object> dataSet = new HashMap<String, Object>();
        try {
            rs = this.getResultSet(conn, dataset, fieldNames, fieldValues);
            if (rs == null) {
                return;
            }
            rs.next();
            for (Column column : dataset.getTableLayout()) {
                dataSet.put(column.columnName(), rs.getObject(column.columnName()));
            }
            dataset.load(dataSet);

        } catch (DatabaseReadException dre) {
            Canary.logStackTrace(dre.getMessage(), dre);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DatabaseTableInconsistencyException dtie) {
            Canary.logStackTrace(dtie.getMessage(), dtie);
        } catch (DatabaseAccessException dae) {
            Canary.logStackTrace(dae.getMessage(), dae);
        } finally {
            this.closeRS(rs);
            pool.returnConnectionToPool(conn);
        }
    }

    @Override
    public void loadAll(DataAccess typeTemplate, List<DataAccess> datasets, String[] fieldNames, Object[] fieldValues) throws DatabaseReadException {
        Connection conn = pool.getConnectionFromPool();
        ResultSet rs = null;
        try {
            rs = this.getResultSet(conn, typeTemplate, fieldNames, fieldValues);
            if (rs == null) {
                return;
            }
            while (rs.next()) {
                HashMap<String, Object> dataSet = new HashMap<String, Object>();
                for (Column column : typeTemplate.getTableLayout()) {
                    dataSet.put(column.columnName(), rs.getObject(column.columnName()));
                }
                DataAccess newData = typeTemplate.getClass().newInstance();
                newData.load(dataSet);
                datasets.add(newData);
            }

        } catch (DatabaseReadException dre) {
            Canary.logStackTrace(dre.getMessage(), dre);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DatabaseTableInconsistencyException dtie) {
            Canary.logStackTrace(dtie.getMessage(), dtie);
        } catch (DatabaseAccessException dae) {
            Canary.logStackTrace(dae.getMessage(), dae);
        } catch (InstantiationException ie) {
            Canary.logStackTrace("Error Loading from Database. " + ie.getMessage(), ie);
        } catch (IllegalAccessException iae) {
            Canary.logStackTrace("Error Loading from Database. " + iae.getMessage(), iae);
        } finally {
            this.closeRS(rs);
            pool.returnConnectionToPool(conn);
        }
    }

    @Override
    public void updateSchema(DataAccess schemaTemplate) throws DatabaseWriteException {
        Connection conn = pool.getConnectionFromPool();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM `" + schemaTemplate.getName() + "` LIMIT 1");

            rs = ps.executeQuery();
            if (rs == null) {
                return;
            }
            LinkedList<String> toRemove = new LinkedList<String>();
            HashMap<String, Column> toAdd = new HashMap<String, Column>();
            Iterator<Column> columns = schemaTemplate.getTableLayout().iterator();

            for (Column column = columns.next(); columns.hasNext(); columns.next()) {
                toAdd.put(column.columnName(), column);
            }

            ResultSetMetaData data = rs.getMetaData();

            for (int i = 1; i <= data.getColumnCount(); i++) {
                if (!toAdd.containsKey(data.getCatalogName(i))) {
                    toRemove.add(data.getCatalogName(i));
                } else {
                    toAdd.remove(data.getCatalogName(i));
                }
            }

            for (String name : toRemove) {
                this.deleteColumn(schemaTemplate.getName(), name);
            }
            for (Map.Entry<String, Column> entry : toAdd.entrySet()) {
                this.insertColumn(schemaTemplate.getName(), entry.getValue());
            }

        } catch (SQLException sqle) {
            throw new DatabaseWriteException("Error updating MySQL schema.");
        } catch (DatabaseTableInconsistencyException dtie) {
            Canary.logStackTrace("Error updating MySQL schema." + dtie.getMessage(), dtie);
        } finally {
            this.closePS(ps);
            this.closeRS(rs);
            pool.returnConnectionToPool(conn);
        }
    }

    public void insertColumn(String tableName, Column column) throws DatabaseWriteException {
        Connection conn = pool.getConnectionFromPool();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("ALTER TABLE `" + tableName + "` ADD COLUMN `" + column.columnName() + "` " + this.getDataTypeSyntax(column.dataType()));
            ps.execute();
        } catch (SQLException ex) {
            throw new DatabaseWriteException("Error adding MySQL collumn.");
        }

    }

    public void deleteColumn(String tableName, String columnName) throws DatabaseWriteException {
        Connection conn = pool.getConnectionFromPool();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("ALTER TABLE `" + tableName + "` DROP COLUMN `" + columnName + "`");
            ps.execute();
        } catch (SQLException ex) {
            throw new DatabaseWriteException("Error deleting MySQL collumn.");
        }
    }

    public boolean doesPrimaryKeyExist(DataAccess data, String primaryKey, Object value) throws DatabaseWriteException {
        Connection conn = pool.getConnectionFromPool();
        PreparedStatement ps = null;
        boolean toRet = false;

        try {
            ps = conn.prepareStatement("SELECT * FROM `" + data.getName() + "` WHERE `" + primaryKey + "` = ?");
            ps.setObject(1, value);
            toRet = ps.execute();

        } catch (SQLException ex) {
            throw new DatabaseWriteException("Error checking Value for MySQL Primary "
                    + "Key in Table `" + data.getName() + "` for key `" + primaryKey
                    + "` and value '" + String.valueOf(value) + "'.");
        } finally {
            this.closePS(ps);
            pool.returnConnectionToPool(conn);
        }
        return toRet;
    }

    public boolean doesEntryExist(DataAccess data) throws DatabaseWriteException {
        Connection conn = pool.getConnectionFromPool();
        PreparedStatement ps = null;
        boolean toRet = false;

        try {
            StringBuilder sb = new StringBuilder();
            HashMap<Column, Object> columns = data.toDatabaseEntryList();
            Iterator<Column> it = columns.keySet().iterator();

            for (Column column = it.next(); it.hasNext(); it.next()) {
                sb.append("`").append(column.columnName());
                if (it.hasNext()) {
                    sb.append("` = ?, ");
                } else {
                    sb.append("` = ?");
                }
            }
            ps = conn.prepareStatement("SELECT * FROM `" + data.getName() + "` WHERE " + sb.toString());

            int i = 1;
            for (Column column : columns.keySet()) {
                ps.setObject(i, columns.get(column));
                i++;
            }

            toRet = ps.execute();

        } catch (SQLException ex) {
            throw new DatabaseWriteException("Error checking MySQL Entry Key in "
                    + data.toString());
        } catch (DatabaseTableInconsistencyException ex) {
            Logger.getLogger(MySQLDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.closePS(ps);
            pool.returnConnectionToPool(conn);
        }
        return toRet;
    }

    /**
     * Safely Close a ResultSet.
     *
     * @param rs ResultSet to close.
     */
    public void closeRS(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException sqle) {
                Canary.logStackTrace("Error closing ResultSet in MySQL database.", sqle);
            }
        }
    }

    /**
     * Safely Close a PreparedStatement.
     *
     * @param ps PreparedStatement to close.
     */
    public void closePS(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException sqle) {
                Canary.logStackTrace("Error closing PreparedStatement in MySQL database.", sqle);
            }
        }
    }

    public ResultSet getResultSet(Connection conn, DataAccess data, String[] fieldNames, Object[] fieldValues) throws DatabaseReadException {
        return this.getResultSet(conn, data.getName(), fieldNames, fieldValues);
    }

    public ResultSet getResultSet(Connection conn, String tableName, String[] fieldNames, Object[] fieldValues) throws DatabaseReadException {
        PreparedStatement ps = null;
        ResultSet toRet = null;

        try {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < fieldNames.length && i < fieldValues.length; i++) {
                sb.append("`").append(fieldNames[i]);
                if (i + 1 < fieldNames.length) {
                    sb.append("` = ?, ");
                } else {
                    sb.append("` = ?");
                }
            }
            ps = conn.prepareStatement("SELECT * FROM `" + tableName + "` WHERE " + sb.toString());

            for (int i = 0; i < fieldNames.length && i < fieldValues.length; i++) {
                ps.setObject(i + 1, fieldValues[i]);
            }

            toRet = ps.executeQuery();
        } catch (SQLException ex) {
            throw new DatabaseReadException("Error Querying MySQL ResultSet in "
                    + tableName);
        } finally {
            this.closePS(ps);
        }
        return toRet;
    }

    public String getDataTypeSyntax(Column.DataType type) {
        switch (type) {
            case BYTE:
                return "INTEGER";
            case INTEGER:
                return "INTEGER";
            case FLOAT:
                return "DOUBLE";
            case DOUBLE:
                return "DOUBLE";
            case LONG:
                return "BIGINT";
            case SHORT:
                return "TINYINT";
            case STRING:
                return "VARCHAR";
            case BOOLEAN:
                return "INTEGER";
        }
        return "";
    }
}
