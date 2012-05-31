package net.canarymod.api;

import net.canarymod.api.entity.Entity;

/**
 * EntityTrackerEntry to handle entries in entity trackers
 * Perhaps we need some more at some point, then we can have it here
 * @author Chris Ksoll
 *
 */
public interface EntityTrackerEntry {
    public Entity getEntity();
}
