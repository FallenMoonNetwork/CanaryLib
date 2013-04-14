package net.canarymod.channels;

import net.canarymod.api.entity.living.humanoid.Player;

/**
 *
 * @author Somners
 */
public abstract class ChannelListener {

    public abstract void onChannelInput(String channel, Player player, byte[] byteStream);
}
