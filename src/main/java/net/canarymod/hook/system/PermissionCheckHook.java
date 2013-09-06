package net.canarymod.hook.system;

import net.canarymod.chat.MessageReceiver;
import net.canarymod.hook.Hook;

/**
 * This hook is called after the PermissionProvider has resolved the permission,
 * to alter the final outcome and eventually temporary allow a permission to a player
 *
 * @author Chris (damagefilter)
 */
public final class PermissionCheckHook extends Hook {

    private MessageReceiver subject;
    private String permission;
    private boolean result;

    public PermissionCheckHook(String permission, MessageReceiver player, boolean result) {
        this.subject = player;
        this.permission = permission;
        this.result = result;
    }

    /**
     * Gets the permission that was checked
     *
     * @return
     */
    public String getPermission() {
        return permission;
    }

    /**
     * Get the MessageReceiver for whom the permission check was issued
     *
     * @return
     */
    public MessageReceiver getSubject() {
        return subject;
    }

    /**
     * Get the result of the check.
     *
     * @return
     */
    public boolean getResult() {
        return result;
    }

    /**
     * Override the final result for the permission check
     *
     * @param result
     */
    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public final String toString() {
        return String.format("%s[subject=%s, permission=%s, result=%s]", getName(), subject.getName(), permission, result);
    }
}
