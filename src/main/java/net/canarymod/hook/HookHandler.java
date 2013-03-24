package net.canarymod.hook;


import net.canarymod.plugin.Priority;


public @interface HookHandler {
    Priority priority() default Priority.NORMAL;

    boolean ignoreCanceled() default false;
}
