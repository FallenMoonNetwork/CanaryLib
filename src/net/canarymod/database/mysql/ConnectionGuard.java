package net.canarymod.database.mysql;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * That guy runs to clean connections that are not used anymore in the
 * connection pool.
 *
 * @author Chris
 *
 */
public class ConnectionGuard implements Runnable {

    private MySqlConnectionPool cp;
    private static final Logger log = Logger.getLogger("Minecraft");

    public ConnectionGuard(MySqlConnectionPool cp) {
        log.info("CanaryMod: Starting connection cleanup thread.");
        this.cp = cp;
    }

    @Override
    public void run() {
        try {
            cp.clearConnections();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Error in ConnectionGuard", e);
        }
    }
}
