package net.canarymod.hook.system;

import net.canarymod.hook.Hook;
import net.canarymod.plugin.Plugin;

/** 
 * Called when a plugin is disabled
 *
 * @author greatman
 */
public class PluginDisableHook extends Hook {

    private Plugin plugin;

    public PluginDisableHook(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Retrieve the plugin that is disabled
     *
     * @return The plugin that is disabled
     */
    public Plugin getPlugin() {
        return plugin;
    }

}
