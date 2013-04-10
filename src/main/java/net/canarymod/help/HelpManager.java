package net.canarymod.help;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CommandOwner;

/**
 *
 * @author Jos (Jarvix)
 * @author Chris (damagefilter)
 *
 */
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
     * @param toolTip The tooltip for this command
     * @param permissionPath The permission node to be checked for player-visibility
     * @param keywords A number of keywords for this command used for search
     * @return true on success, false on failure
     */
    public boolean registerCommand(CommandOwner plugin, String command, String description, String toolTip, String[] permissions, String[] keywords) {

        // Allow new commands and updates of commands for the same plugin
        if (nodes.containsKey(command.toLowerCase())) {
            return false;
        }
        // Create the new node
        HelpNode newNode = new HelpNode();

        newNode.plugin = plugin;
        newNode.command = command;
        newNode.description = description;
        newNode.toolTip = toolTip;
        newNode.permissions = permissions;
        newNode.keywords = keywords;

        // And store it
        nodes.put(command.toLowerCase(), newNode);

        return true;
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
     * @param owner
     */
    public void unregisterCommands(CommandOwner owner) {
        Iterator<String> itr = nodes.keySet().iterator();

        while (itr.hasNext()) {
            String entry = itr.next();
            HelpNode node = nodes.get(entry);

            if (node.plugin == owner) {
                itr.remove();
            }
        }
    }

    /**
     * Check if this command name already is registered
     * @param command
     * @return
     */
    public boolean hasHelp(String command) {
        return nodes.containsKey(command);
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
                if (node.canUse(player)) {
                    size++;
                }
            }
        }

        return (int) Math.ceil((double) (size) / (double) pageSize);
    }

    public String[] getHelp(Player player, int page) {
        ArrayList<String> lines = new ArrayList<String>();
        ArrayList<HelpNode> nodes = new ArrayList<HelpNode>();

        if (page < 1) {
            page = 1;
        }

        // Get all nodes
        if (player == null) {
            nodes = new ArrayList<HelpNode>(this.nodes.values());
        }
        else {
            for (HelpNode node : this.nodes.values()) {
                if (node.canUse(player)) {
                    nodes.add(node);
                }
            }
        }
        // Sort the nodes nicely
        Collections.sort(nodes, new HelpNodeComparator());
        int pageNum = (int) Math.ceil((double) nodes.size() / (double) pageSize);
        if(nodes.size() % pageSize > 0) {
            pageNum++;
        }
        if (page > pageNum) {
            page = 1;
        }
        int amount = (page - 1) * pageSize;
        // Header
        lines.add(Colors.CYAN + Translator.translateAndFormat("help title", page, pageNum));
        for (int i = amount; i < (amount + pageSize); i++) {
            if (nodes.size() <= i) {
                break;
            }
            HelpNode node = nodes.get(i);

            lines.add(Colors.LIGHT_RED + node.command + Colors.WHITE + " - " + Colors.YELLOW + node.description);
        }

        return lines.toArray(new String[lines.size()]);
    }

    /**
     * Searches through available help nodes for the given array of words
     * and returns help messages according to {@link Player} permissions
     * @param player
     * @param terms
     * @param page
     * @return
     */
    public String[] getHelp(Player player, String[] terms, int page) {
        ArrayList<HelpManager.HelpNode> hits = new ArrayList<HelpManager.HelpNode>();
        for(String key : nodes.keySet()) {
            HelpNode node = nodes.get(key);
            for(String word : terms) {
                if(node.description != null) {
                    if(node.description.toLowerCase().contains(word.toLowerCase())) {
                        if(player == null || node.canUse(player)) {
                            hits.add(node);
                        }
                        break;
                    }
                }
                if(node.keywords != null) {
                    for(String nodeTerm : node.keywords) {
                        if(nodeTerm.equalsIgnoreCase(word)) {
                            if(player == null || node.canUse(player)) {
                                hits.add(node);
                            }
                            break;
                        }
                    }
                }
            }
        }
        ArrayList<String> lines = new ArrayList<String>();
        Collections.sort(hits, new HelpNodeComparator());

        int pageNum = (int) Math.ceil((double) hits.size() / (double) pageSize);
        if(hits.size() % pageSize > 0) {
            pageNum++;
        }
        if (page > pageNum) {
            page = 1;
        }
        int amount = (page - 1) * pageSize;
        // Header
        lines.add(Colors.CYAN + Translator.translateAndFormat("help title", page, pageNum));
        for (int i = amount; i < (amount + pageSize); i++) {
            if (hits.size() <= i) {
                break;
            }
            HelpNode node = hits.get(i);
            lines.add(Colors.LIGHT_RED + node.command + Colors.WHITE + " - " + Colors.YELLOW + node.description);
        }

        return lines.toArray(new String[lines.size()]);
    }


    /**
     * Displays the given commands description and toolTip,
     * if the permissions allow it.
     * @param caller The MR to show the help for
     * @param commandName The command for which help is required
     */
    public void getHelp(MessageReceiver caller, String commandName) {
        HelpNode node = nodes.get(commandName);
        if(node != null && node.canUse(caller)) {
            caller.message(Colors.LIGHT_RED + node.command + Colors.WHITE + " - " + Colors.YELLOW + node.description);
            caller.message(Colors.LIGHT_RED + node.toolTip);
        }
    }

    class HelpNode {
        CommandOwner plugin;
        String command;
        String description;
        String toolTip;
        String[] permissions;
        String[] keywords;

        public boolean canUse(MessageReceiver caller) {
            for(String perm : permissions) {
                if(caller.hasPermission(perm)) {
                    return true;
                }
            }
            return false;
        }
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
