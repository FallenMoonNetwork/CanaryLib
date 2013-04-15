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

    /**
     * Get the plugin associated with this registered channel listener.
     * @return
     */
    public Plugin getPlugin() {
        return this.plugin;
    }

    /**
     * Get the ChannelListener instancea associated with this registered channel listener.
     * @return
     */
    public ChannelListener getChannelListener() {
        return this.listener;
    }
}
