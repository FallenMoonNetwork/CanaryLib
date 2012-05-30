package net.canarymod.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.canarymod.config.Configuration;
import net.canarymod.config.DatabaseConfiguration;

public class MySqlConnectionPool {
    
	/**
     * Connection information
     */
    private String url, user, passwd;
    
    /**
     * This is the time a connection is allowed to idle until it gets removed
     * and closed.
     */
    private int timeout = 60000;
    
    /**
     * Use a vector to store the connections as a vector is synchronized and we
     * don't need to worry about thread safety. Also new connections aren't
     * invoked that often so the extra costs of having it synchronized are
     * minimal.
     */
    private List<CanaryConnection> connectionPool;
    
    /**
     * Connection guard etc
     */
    private ScheduledExecutorService ses;

    private static MySqlConnectionPool instance = null;
    
    /**
     * Create a new connection service with the given objects.
     *
     * @param url url to database
     * @param user the db user
     * @param passwd the db password
     * @param poolsize the initial connection pool size. 10 is a suitable value
     * most of the time
     */
    private MySqlConnectionPool(String url, String user, String passwd, int poolsize) {
        this.url = url;
        this.user = user;
        this.passwd = passwd;
        connectionPool = Collections.synchronizedList(
                new ArrayList<CanaryConnection>(poolsize));

        ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(new ConnectionGuard(this),
                timeout / 2, timeout / 2, TimeUnit.MILLISECONDS); //start cleanup thread
    }
    
    public static MySqlConnectionPool getInstance() {
        if(instance == null) {
            DatabaseConfiguration sql = Configuration.getDbConfig();
            instance = new MySqlConnectionPool(sql.getDatabaseUrl(), sql.getDatabaseUser(), sql.getDatabasePassword(), 5);
        }
        return instance;
    }

    /**
     * Destroys all connections and empties the connection pool
     *
     * @throws SQLException
     */
    public synchronized void destroy() throws SQLException {
        Iterator<CanaryConnection> v = connectionPool.iterator();

        while (v.hasNext()) {
            CanaryConnection c = v.next();
            if (!c.isClosed()) {
                c.close();
            }
            connectionPool.remove(c);
        }
    }

    /**
     * Get a new CanaryConnection COnnection wrapper
     * @return
     * @throws SQLException
     */
    public synchronized CanaryConnection getConnection() throws SQLException {
        CanaryConnection c;
        Iterator<CanaryConnection> v = connectionPool.iterator();
        while (v.hasNext()) {
            c = v.next();
            if (!c.isLeased()) {
                return c;
            }
        }
        //All connections are in use, create a new one
        Connection conn = DriverManager.getConnection(url, user, passwd);
        CanaryConnection cconn = new CanaryConnection(conn);
        connectionPool.add(cconn);
        return cconn;
    }

    /**
     * Method is called from the connection guard to keep the connections clean
     *
     * @throws SQLException
     */
    public synchronized void clearConnections() throws SQLException {
        Iterator<CanaryConnection> v = connectionPool.iterator();
        long idle = System.currentTimeMillis() - timeout;

        while (v.hasNext()) {
            CanaryConnection c = v.next();
            //connection exists for too long
            if ((idle > c.getLastUsage())) {
                if (!c.isClosed()) {
                    c.close();
                }
                connectionPool.remove(c);
            }
        }
    }
    
    /**
     * Get the username for this connection
     * @return
     */
    public String getConnectionCredentialsUser() {
        return user;
    }
    
    /**
     * Get the password for this connection
     * @return
     */
    public String getConnectionCredentialsPasswd() {
        return passwd;
    }
    
    /**
     * Get the url for this connection
     * @return
     */
    public String getConnectionCredentialsUrl() {
        return url;
    }
}
