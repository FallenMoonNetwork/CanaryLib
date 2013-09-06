package net.canarymod.hook.player;

import net.canarymod.api.PlayerListEntry;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.Hook;

/**
 * PlayerListEntryHook
 * <p/>
 * Called when a {@link Player} information is sent to another {@link Player}
 *
 * @author Jason (darkdiplomat)
 */
public final class PlayerListEntryHook extends Hook {
    private final PlayerListEntry entry;
    private final Player receiver;

    /**
     * Constructs a new PlayerListEntryHook
     *
     * @param entry
     *         the {@link PlayerListEntry} being sent
     * @param receiver
     *         the {@link Player} to receiver the {@link PlayerListEntry}
     */
    public PlayerListEntryHook(PlayerListEntry entry, Player receiver) {
        this.entry = entry;
        this.receiver = receiver;
    }

    /**
     * Gets the {@link PlayerListEntry} being sent
     * <p/>
     * NOTE: If {@link PlayerListEntry#isShown} is {@code false}, it is likely the {@link Player} is disconnecting
     *
     * @return {@link PlayerListEntry} being sent
     */
    public final PlayerListEntry getEntry() {
        return entry;
    }

    /**
     * Gets the {@link Player} to receive the PlayerListEntry
     *
     * @return the {@link Player} receiver
     */
    public final Player getReceiver() {
        return receiver;
    }

    /** {@inheritDoc} */
    @Override
    public final String toString() {
        return String.format("PlayerListEntryHook[Entry=%s Receiver=%s]", entry, receiver);
    }

}
