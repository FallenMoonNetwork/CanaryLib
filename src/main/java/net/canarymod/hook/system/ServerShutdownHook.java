package net.canarymod.hook.system;

import net.canarymod.hook.Hook;

/**
 * Server shutdown hook. Contains information about the reason the server is
 * shutting down
 *
 * @author Jason (darkdiplomat)
 */
public final class ServerShutdownHook extends Hook {

    private String reason;

    public ServerShutdownHook(String reason) {
        this.reason = reason;
    }

    /**
     * Get the reason for disconnect (if applicable)
     *
     * @return reason if disconnecting, null otherwise
     */
    public String getReason() {
        return reason;
    }

    /**
     * Set the reason for disconnection that will be displayed
     *
     * @param reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public final String toString() {
        return String.format("%s[Reason=%s]", getName(), reason);
    }
}
