package net.canarymod.hook.player;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.hook.Hook;

/**
 * Kick hook. Contains the player who was kicked and the MessageReceiver who kicked them
 *
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public final class KickHook extends Hook {
    private Player kicked;
    private MessageReceiver moderator;
    private String reason;

    public KickHook(Player kickedPlayer, MessageReceiver moderator, String reason) {
        this.kicked = kickedPlayer;
        this.moderator = moderator;
        this.reason = reason;
    }

    /**
     * Get the {@link Player} being kicked
     *
     * @return the {@link Player} kicked
     */
    public Player getKickedPlayer() {
        return kicked;
    }

    /**
     * Get the {@link MessageReceiver} that has issued the kick
     *
     * @return the {@link MessageReceiver} that issued the kick
     */
    public MessageReceiver getModerator() {
        return moderator;
    }

    /**
     * Gets the reason for the kick
     *
     * @return the kick reason
     */
    public String getReason() {
        return reason;
    }

    @Override
    public final String toString() {
        return String.format("%s[Player=%s, Kicked By=%s, Reason=%s]", getName(), kicked.getName(), moderator.getName(), reason);
    }
}
