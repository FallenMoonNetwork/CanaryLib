package net.canarymod.channels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.canarymod.Canary;
import net.canarymod.api.NetServerHandler;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.plugin.Plugin;

/**
 *
 * @author Somners
 */
public class ChannelManager implements ChannelManagerInterface {

    private HashMap<String, List<RegisteredChannelListener>> listeners = new HashMap<String, List<RegisteredChannelListener>>();
    private HashMap<String, List<NetServerHandler>> clients = new HashMap<String, List<NetServerHandler>>();

    @Override
    public void registerListener(Plugin plugin, String channel, ChannelListener listener) {
        try {
            if (plugin == null) {
                throw new CustomPayloadChannelException("Invalid Registered Listener: Plugin is null.");
            }
            if (channel == null || channel.trim().equals("") || channel.equalsIgnoreCase("REGISTER") || channel.equalsIgnoreCase("UNREGISTER")) {
                throw new CustomPayloadChannelException(String.format("Invalid Registered Listener: Invalid channel name of '%s'", channel));
            }
            if (listener == null) {
                throw new CustomPayloadChannelException("Invalid Registered Listener: Channel Listener is null.");
            }
            if (listeners.containsKey(channel)) {
                listeners.get(channel).add(new RegisteredChannelListener(plugin, listener));
            } else{
                ArrayList<RegisteredChannelListener> forMap = new ArrayList<RegisteredChannelListener>();
                forMap.add(new RegisteredChannelListener(plugin, listener));
                listeners.put(channel, forMap);
            }
        } catch (CustomPayloadChannelException ex) {
            Canary.logStackTrace(ex.getMessage(), ex);
        }
    }

    @Override
    public boolean unregisterListeners(Plugin plugin) {
        boolean toRet = false;
        for (List<RegisteredChannelListener> list : listeners.values()) {
            for (RegisteredChannelListener listener : list) {
                if (listener.getPlugin().equals(plugin)) {
                    list.remove(listener);
                    toRet = true;
                }
            }
        }
        return toRet;
    }

    @Override
    public boolean sendCustomPayloadToAllPlayers(String channel, byte[] bytestream) {
        // TODO : implement
        return false;
    }

    @Override
    public boolean sendCustomPayloadToPlayer(String channel, byte[] bytestream, Player player) {
        // TODO : implement
        return false;
    }

    @Override
    public void sendCustomPayloadToListeners(String channel, byte[] byteStream, Player player) {
        if (listeners.containsKey(channel)) {
            for (RegisteredChannelListener listener : listeners.get(channel)) {
                listener.getChannelListener().onChannelInput(channel, player, byteStream);
            }
        }
    }

    @Override
    public void registerClient(String channel, NetServerHandler handler) {
        try {
            if (handler == null) {
                throw new CustomPayloadChannelException("Invalid Registered Client: NetServerHandler is null.");
            }
            if (clients.containsKey(channel)) {
                clients.get(channel).add(handler);
            } else{
                ArrayList<NetServerHandler> forMap = new ArrayList<NetServerHandler>();
                forMap.add(handler);
                clients.put(channel, forMap);
            }
        } catch (CustomPayloadChannelException ex) {
            Canary.logStackTrace(ex.getMessage(), ex);
        }
    }

    @Override
    public boolean unregisterClient(String channel, NetServerHandler handler) {
        boolean toRet = false;
        if (clients.containsKey(channel)) {
            for (NetServerHandler h : clients.get(channel)) {
                if (h.equals(handler)) {
                    clients.get(channel).remove(h);
                    toRet = true;
                }
            }
        }
        return toRet;
    }

    @Override
    public boolean unregisterClientAll(NetServerHandler handler) {
        boolean toRet = false;
        for (List<NetServerHandler> list : clients.values()) {
            for (NetServerHandler h : list) {
                if (h.equals(handler)) {
                    list.remove(h);
                    toRet = true;
                }
            }
        }
        return toRet;
    }

}
