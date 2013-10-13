package net.canarymod.motd;

import net.canarymod.plugin.Plugin;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The {@link Plugin} {@link net.canarymod.motd.MessageOfTheDay} key/value replacement @interface.
 *
 * @author Jason (darkdiplomat)
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface MOTDKey {

    /**
     * The key to be replaced in the MessageOfTheDay (ie: {name} )
     *
     * @return the key to be replaced
     */
    String key();
}
