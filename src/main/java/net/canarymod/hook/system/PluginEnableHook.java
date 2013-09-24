package net.canarymod.hook.system;

import net.canarymod.hook.Hook;
import net.canarymod.plugin.Plugin;

/**
 * Called when a plugin is enabled.
 *
 * @author greatman
 */
public class PluginEnableHook extends Hook {

    private Plugin plugin;

    public PluginEnableHook(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Retrieve the plugin that is enabled
     *
     * @return The plugin that is enabled
     */
    public Plugin getPlugin() {
        return plugin;
    }

}
