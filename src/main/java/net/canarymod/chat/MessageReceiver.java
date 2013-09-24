package net.canarymod.chat;

/**
 * Callback interface for commands.
 *
 * @author Willem (l4mRh4X0r)
 */
public interface MessageReceiver {

    /**
     * Returns the name of this <tt>MessageReceiver</tt>.
     *
     * @return the <tt>MessageReceiver</tt>'s name.
     */
    public String getName();

    /**
     * Sends a message to this <tt>MessageReceiver</tt>.
     *
     * @param message
     *         The message to send.
     */
    public void notice(String message);

    /**
     * Sends a message to this <tt>MessageReceiver</tt>.
     *
     * @param message
     *         The message to send.
     */
    public void message(String message);

    /**
     * Check if the {@link MessageReceiver} has this permission.
     * This will issue a PermissionCheck hook that means,
     * the returned result is not reliable.
     * However, this allows other Plugins to have a say in the permission lookup process.
     *
     * @param node
     *         The node to check for.
     *
     * @return <tt>true</tt> if this <tt>MessageReceiver</tt> has the given
     *         permission, <tt>false</tt> otherwise.
     */
    public boolean hasPermission(String node);

    /**
     * Check if a {@link MessageReceiver} has this permission.
     * This will not issue a PermissionCheck hook so the returned
     * result is reliable.
     *
     * @param permission
     *         ther permission node to check for
     *
     * @return <tt>true</tt> if this <tt>MessageReceiver</tt> has the given
     *         permission, <tt>false</tt> otherwise.
     */
    public boolean safeHasPermission(String permission);
}
