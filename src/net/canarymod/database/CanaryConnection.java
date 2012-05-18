package net.canarymod.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This is a connection to the canarymod database. Use it for your database
 * management stuff.
 *
 * @author Chris
 *
 */
public class CanaryConnection {

    private Connection con;
    private boolean leased;
    private long lastUsed;

    /**
     * Construct a new connection wrapper.
     *
     * @param c
     */
    public CanaryConnection(Connection c) {
        con = c;
        leased = false;
        lastUsed = 0;
    }

    /**
     * Check if this connection is in use or not.
     *
     * @return boolean true if connection is in use, false otherwise
     * @throws SQLException
     */
    public boolean isLeased() throws SQLException {
        return leased || con.isClosed();
    }

    /**
     * Returns the actual connection and sets the CanaryConnection to leased, so
     * it will not be used by something else
     *
     * @return
     */
    public Connection getConnection() {
        leased = true;
        lastUsed = System.currentTimeMillis();
        return con;
    }

    /**
     * Remove this connection's lease. Connection will remain idling until it is
     * used again or removed by the connection guard
     */
    public void release() {
        leased = false;
    }

    /**
     * Get systemTimeMillis() when this connection was last used.
     *
     * @return
     */
    public long getLastUsage() {
        return lastUsed;
    }

    /**
     * Check if this connection is closed
     *
     * @return
     * @throws SQLException
     */
    public boolean isClosed() throws SQLException {
        return con.isClosed();
    }
    
    public boolean isDead(int timeout) throws SQLException {
        return con.isValid(timeout);
    }

    /**
     * Use if you want this connection not to be used anymore
     *
     * @throws SQLException
     */
    public void close() throws SQLException {
        con.close();
    }

    /**
     * Create a prepared statement
     *
     * @param statement
     * @return
     * @throws SQLException
     */
    public PreparedStatement prepareStatement(String statement) throws SQLException {
        return con.prepareStatement(statement);
    }

    public PreparedStatement prepareStatement(String statement, int returnKeys) throws SQLException {
        return con.prepareStatement(statement, returnKeys);
    }
}
