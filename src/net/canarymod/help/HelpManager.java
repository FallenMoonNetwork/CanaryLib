package net.canarymod.help;

import java.util.ArrayList;
import java.util.HashMap;

import net.canarymod.Colors;
import net.canarymod.api.entity.Player;
import net.canarymod.plugin.Plugin;

public class HelpManager {
    
    private HashMap<String, HelpNode> nodes;
    private int pageSize = 7;
    
    public HelpManager() {
        nodes = new HashMap<String, HelpNode>();
    }
    
    /**
     * Register a command
     * @param plugin The plugin registering the command
     * @param command The actual command
     * @param description Description of the command, shown next to the command in the help text
     * @param permissionPath The permission node to be checked for player-visibility
     * @param keywords A number of keywords for this command used for search
     * @return true on success, false on failure
     */
    public boolean registerCommand(Plugin plugin, String command, String description, String permissionPath, String[] keywords) {
        
        // Allow new commands and updates of commands for the same plugin
        if(nodes.get(command.toLowerCase()) != null && nodes.get(command.toLowerCase()).plugin != plugin) {
            return false;
        }
        
        // Create the new node
        HelpNode newNode = new HelpNode();
        newNode.plugin = plugin;
        newNode.command = command;
        newNode.description = description;
        newNode.permissionPath = permissionPath;
        newNode.keywords = keywords;
        
        // And store it
        nodes.put(command.toLowerCase(), newNode);
        
        return true;
    }
    
    /**
     * Register a command
     * @param plugin The plugin registering the command
     * @param command The actual command
     * @param description Description of the command, shown next to the command in the help text
     * @param permissionPath The permission node to be checked for player-visibility
     * @return true on success, false on failure
     */
    public boolean registerCommand(Plugin plugin, String command, String description, String permissionPath) {
        return registerCommand(plugin, command, description, permissionPath, null);
    }
    
    /**
     * Register a command
     * @param plugin The plugin registering the command
     * @param command The actual command
     * @param description Description of the command, shown next to the command in the help text
     * @return true on success, false on failure
     */
    public boolean registerCommand(Plugin plugin, String command, String description) {
        return registerCommand(plugin, command, description, "*", null);
    }
    
    /**
     * Unregister a command
     * @param plugin
     * @param command
     * @return true on success, false on failure
     */
    public boolean unregisterCommand(Plugin plugin, String command) {
        
        HelpNode node = nodes.get(command.toLowerCase());
        if(node == null || node.plugin != plugin) {
            return false;
        }
        
        nodes.remove(command.toLowerCase());
        
        return true;
    }
    
    /**
     * Get the maximum number of entries in one page
     * @return
     */
    public int getEntriesPerPage() {
        return pageSize;
    }
    
    /**
     * Set the maximum number of entries in one page
     * @param num
     */
    public void setEntriesPerPage(int num) {
        pageSize = num;
    }
    
    /**
     * Get the number of pages to fit all commands for specified player
     * @param player The player used for permission validation, null for server op
     * @return
     */
    public int getPageCount(Player player) {
        int size = 0;
        
        if(player == null) {
            size = nodes.size();
        }
        else {
            for(HelpNode node : nodes.values()) {
                if(player.hasPermission(node.permissionPath)) {
                    size++;
                }
            }
        }
        
        return (int)Math.ceil((double)(size)/(double)pageSize);
    }
    
    public String[] getHelp(Player player, int page) {
        ArrayList<String> lines = new ArrayList<String>();
        ArrayList<HelpNode> nodes = new ArrayList<HelpNode>();
        
        if(page < 0) {
            return null;
        }
        
        // Get all nodes
        if(player == null) {
            nodes = (ArrayList<HelpNode>)this.nodes.values();
        }
        else {
            for(HelpNode node : this.nodes.values()) {
                if(player.hasPermission(node.permissionPath)) {
                    nodes.add(node);
                }
            }
        }
        int pageNum = (int)Math.ceil((double)nodes.size()/(double)pageSize);
        
        if(page >= pageNum) {
            return null;
        }
        
        // Header
        lines.add(Colors.Blue + "Available commands ( Page " + (page+1) + " of " + pageNum + ") <> = required [] = optional:");
        
        for(int i = page*pageSize; i < (page+1)*pageSize && i < nodes.size(); i++) {
            HelpNode node = nodes.get(i);
            lines.add(Colors.Rose + node.command + " - " + node.description);
        }
        
        String[] ret = {};
        return lines.toArray(ret);
    }
    
    class HelpNode {
        Plugin plugin;
        String command;
        String description;
        String permissionPath;
        String[] keywords;
    }
}
