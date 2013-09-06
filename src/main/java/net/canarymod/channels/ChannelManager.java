package net.canarymod.channels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.canarymod.Canary;
import net.canarymod.api.NetServerHandler;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.plugin.Plugin;

/**
 * This class manages incoming and outgoing Packet250CustomPayload's. This allows
 * custom communication between the client and server. <br>
 * <br>
 * <b>NOTE:</b><br>
 * - Channel names must be 20 characters long or less.<br>
 * - Byte data can be no larger than 32 kb.<br>
 * - Neither the channel, plugin, listener, player or byte[] can be null in any case.<br>
 * - The channels 'REGISTER' and 'UNREGISTER' are reserved by the server and invalid.<br>
 * - no name or and empty String is an invalid channel name.<br>
 * - To register and unregister the client, send packets with the names 'REGISTER'
 * and 'UNREGISTER' respectively, with a message of the actual channel name to
 * register/unregister.<br>
 *
 * @author Somners
 */
public abstract class ChannelManager implements ChannelManagerInterface {

    private HashMap<String, List<RegisteredChannelListener>> listeners = new HashMap<String, List<RegisteredChannelListener>>();
    protected HashMap<String, List<NetServerHandler>> clients = new HashMap<String, List<NetServerHandler>>();

    /** {@inheritDoc} */
    @Override
    public void registerListener(Plugin plugin, String channel, ChannelListener listener) {
        try {
            if (plugin == null) {
                throw new CustomPayloadChannelException("Invalid Registered Listener: Plugin is null.");
            }
            if (channel == null || channel.trim().equals("") || channel.equalsIgnoreCase("REGISTER") || channel.equalsIgnoreCase("UNREGISTER")) {
                throw new CustomPayloadChannelException(String.format("Invalid Registered Listener: Invalid channel name of '%s'", channel));
            }
            if (channel.length() > 20) {
                throw new CustomPayloadChannelException(String.format("Invalid Custom Payload: Channel Name too long '%s'", channel));
            }
            if (listener == null) {
                throw new CustomPayloadChannelException("Invalid Registered Listener: Channel Listener is null.");
            }
            if (listeners.containsKey(channel)) {
                listeners.get(channel).add(new RegisteredChannelListener(plugin, listener));
            }
            else {
                ArrayList<RegisteredChannelListener> forMap = new ArrayList<RegisteredChannelListener>();
                forMap.add(new RegisteredChannelListener(plugin, listener));
                listeners.put(channel, forMap);
            }
        }
        catch (CustomPayloadChannelException ex) {
            Canary.logStacktrace(ex.getMessage(), ex);
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean unregisterListeners(Plugin plugin) {
        boolean toRet = false;
        for (List<RegisteredChannelListener> list : listeners.values()) {
            synchronized (list) {
                for (RegisteredChannelListener listener : list) {
                    if (listener.getPlugin().equals(plugin)) {
                        list.remove(listener);
                        toRet = true;
                    }
                }
            }
        }
        return toRet;
    }

    /** {@inheritDoc} */
    @Override
    public abstract boolean sendCustomPayloadToAllPlayers(String channel, byte[] bytestream);

    /** {@inheritDoc} */
    @Override
    public abstract boolean sendCustomPayloadToPlayer(String channel, byte[] bytestream, Player player);

    /** {@inheritDoc} */
    @Override
    public void sendCustomPayloadToListeners(String channel, byte[] byteStream, Player player) {
        if (listeners.containsKey(channel)) {
            for (RegisteredChannelListener listener : listeners.get(channel)) {
                listener.getChannelListener().onChannelInput(channel, player, byteStream);
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public void registerClient(String channel, NetServerHandler handler) {
        try {
            if (handler == null) {
                throw new CustomPayloadChannelException("Invalid Registered Client: NetServerHandler is null.");
            }
            if (clients.containsKey(channel)) {
                clients.get(channel).add(handler);
            }
            else {
                ArrayList<NetServerHandler> forMap = new ArrayList<NetServerHandler>();
                forMap.add(handler);
                clients.put(channel, forMap);
            }
        }
        catch (CustomPayloadChannelException ex) {
            Canary.logStacktrace(ex.getMessage(), ex);
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean unregisterClient(String channel, NetServerHandler handler) {
        if (clients.containsKey(channel)) {
            synchronized (clients.get(channel)) {
                if (clients.get(channel).remove(handler)) {
                    Canary.logInfo(String.format("Client Custom Payload channel '%s' has been unregistered for client '%s'", channel, handler.getUser().getName()));
                    return true;
                }
            }
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public boolean unregisterClientAll(NetServerHandler handler) {
        boolean toRet = true;
        for (String channel : clients.keySet()) {
            toRet &= unregisterClient(channel, handler);
        }
        return toRet;
    }

}
