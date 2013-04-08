package net.canarymod.plugin;

public class PluginException extends RuntimeException {
    private static final long serialVersionUID = -8544875171002713131L;

    public PluginException(String str) {
        super(str);
    }

    public PluginException(String msg, Throwable t) {
        super(msg, t);
    }
}
