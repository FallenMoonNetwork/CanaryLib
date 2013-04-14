package net.canarymod.channels;

import net.canarymod.plugin.Plugin;

/**
 *
 * @author Somners
 */
public class RegisteredChannelListener {

    private Plugin plugin;
    private ChannelListener listener;

    public RegisteredChannelListener(Plugin plugin, ChannelListener listener) {
        this.plugin = plugin;
        this.listener = listener;
    }

    public Plugin getPlugin() {
        return this.plugin;
    }

    public ChannelListener getChannelListener() {
        return this.listener;
    }
}
