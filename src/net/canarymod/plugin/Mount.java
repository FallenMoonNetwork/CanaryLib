package net.canarymod.plugin;

/**
 * Define when this plugin is loaded (the Mount)
 * @author Chris
 *
 */
public enum Mount { //TODO:Refactor for a better name? lol
    /**
     * For tools and passive stuff
     */
    NEVER,
    
    /**
     * Load before the world loading routines are invoked
     */
    BEFORE_WORLD,
    
    /**
     * The normal mount situation, after the world has been loaded
     */
    AFTER_WORLD;
}
