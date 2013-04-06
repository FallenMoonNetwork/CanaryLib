package net.canarymod.help;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.commandsys.CommandOwner;
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
    public boolean registerCommand(CommandOwner plugin, String command, String description, String permissionPath, String[] keywords) {

        // Allow new commands and updates of commands for the same plugin
        if (nodes.containsKey(command.toLowerCase())) {
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
    public boolean registerCommand(CommandOwner plugin, String command, String description, String permissionPath) {
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
    public boolean unregisterCommand(CommandOwner plugin, String command) {

        HelpNode node = nodes.get(command.toLowerCase());

        if (node == null || node.plugin != plugin) {
            return false;
        }

        nodes.remove(command.toLowerCase());

        return true;
    }

    /**
     * Unregisters all commands assigned to the given plugin
     * @param plugin
     */
    public void unregisterCommands(Plugin plugin) {
        Iterator<String> itr = nodes.keySet().iterator();

        while (itr.hasNext()) {
            String entry = itr.next();
            HelpNode node = nodes.get(entry);

            if (node.plugin == plugin) {
                itr.remove();
            }
        }
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

        if (player == null) {
            size = nodes.size();
        } else {
            for (HelpNode node : nodes.values()) {
                if (player.hasPermission(node.permissionPath)) {
                    size++;
                }
            }
        }

        return (int) Math.ceil((double) (size) / (double) pageSize);
    }

    public String[] getHelp(Player player, int page) {
        ArrayList<String> lines = new ArrayList<String>();
        ArrayList<HelpNode> nodes = new ArrayList<HelpNode>();

        if (page < 0) {
            return null;
        }

        // Get all nodes
        if (player == null) {
            nodes = new ArrayList<HelpNode>(this.nodes.values());
        }
        else {
            for (HelpNode node : this.nodes.values()) {
                if (player.hasPermission(node.permissionPath)) {
                    nodes.add(node);
                }
            }
        }
        int pageNum = (int) Math.ceil((double) nodes.size() / (double) pageSize);

        // Sort the nodes nicely
        Collections.sort(nodes, new HelpNodeComparator());

        if (page >= pageNum) {
            return null;
        }

        // Header
        lines.add(Colors.CYAN + Translator.translateAndFormat("help title", (page+1), pageNum));

        for (int i = page * pageSize; i < (page + 1) * pageSize && i < nodes.size(); i++) {
            HelpNode node = nodes.get(i);

            lines.add(Colors.LIGHT_RED + node.command + Colors.WHITE + " - " + Colors.YELLOW + node.description);
        }

        return lines.toArray(new String[lines.size()]);
    }

    /**
     * Searches through available help nodes for the given array of words
     * @param player
     * @param terms
     * @param page
     * @return
     */
    public String[] getSearch(Player player, String[] terms, int page) {
        ArrayList<HelpManager.HelpNode> hits = new ArrayList<HelpManager.HelpNode>();
        for(String key : nodes.keySet()) {
            HelpNode node = nodes.get(key);
            for(String word : terms) {
                if(node.description != null) {
                    if(node.description.toLowerCase().contains(word.toLowerCase())) {
                        if(player == null || player.hasPermission(node.permissionPath)) {
                            hits.add(node);
                        }
                        break;
                    }
                }
                if(node.keywords != null) {
                    for(String nodeTerm : node.keywords) {
                        if(nodeTerm.equalsIgnoreCase(word)) {
                            if(player == null || player.hasPermission(node.permissionPath)) {
                                hits.add(node);
                            }
                            break;
                        }
                    }
                }
            }
        }

        int pageNum = (int) Math.ceil((double) hits.size() / (double) pageSize);
        ArrayList<String> lines = new ArrayList<String>();
        lines.add(Colors.CYAN + Translator.translateAndFormat("help title", (page+1), pageNum));
        for (int i = page * pageSize; i < (page + 1) * pageSize && i < hits.size(); i++) {
            HelpNode node = hits.get(i);
            lines.add(Colors.LIGHT_RED + node.command + Colors.WHITE + " - " + Colors.YELLOW + node.description);
        }

        return lines.toArray(new String[lines.size()]);
    }

    class HelpNode {
        CommandOwner plugin;
        String command;
        String description;
        String permissionPath;
        String[] keywords;
    }


    class HelpNodeComparator implements Comparator<HelpNode> {
        @Override
        public int compare(HelpNode o1, HelpNode o2) {
            // We want null-plugins always in front. null-plugins are canary-commands
            if (o1.plugin == null) {
                return -1;
            }
            if (o2.plugin == null) {
                return 1;
            }

            // Plugin sorting before command sorting
            int pc = o1.plugin.getName().compareToIgnoreCase(o2.plugin.getName());

            if (pc != 0) {
                return pc;
            }

            // In case the plugin is the same, sort the name
            return o1.command.compareToIgnoreCase(o2.command);
        }
    }
}
