package net.canarymod.help;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandOwner;

/**
 *
 * @author Jos (Jarvix)
 * @author Chris (damagefilter)
 *
 */
public class HelpManager {

    private TreeMap<String, HelpNode> nodes;
    private int pageSize = 13;

    public HelpManager() {
        nodes = new TreeMap<String, HelpNode>();
    }

    /**
     * Registers a command.
     * @param owner
     * @param command
     * @return
     */
    public boolean registerCommand(CommandOwner owner, CanaryCommand command) {
        boolean added = false;
        for(String alias : command.meta.aliases()) {
            if(nodes.containsKey(alias)) {
                continue;
            }
            HelpNode newNode = new HelpNode();
            newNode.plugin = owner;
            newNode.command = alias;
            newNode.description = command.getLocaleDescription();
            newNode.toolTip = command.meta.toolTip();
            newNode.permissions = command.meta.permissions();
            newNode.subCommands = subCommandsToStringArray(command.getSubCommands());
            newNode.isSubgroup = !command.meta.parent().isEmpty();
            newNode.parent = command.meta.parent();
            nodes.put(alias, newNode);
            added = true;
        }
        return added;
    }

    public boolean registerCommand(CommandOwner owner, CanaryCommand command, String lookup) {
        if(nodes.containsKey(lookup)) {
            return false;
        }
        HelpNode newNode = new HelpNode();
        newNode.plugin = owner;
        newNode.command = lookup;
        newNode.description = command.getLocaleDescription();
        newNode.toolTip = command.meta.toolTip();
        newNode.permissions = command.meta.permissions();
        newNode.subCommands = subCommandsToStringArray(command.getSubCommands());
        newNode.isSubgroup = !command.meta.parent().isEmpty();
        newNode.parent = command.meta.parent();
        nodes.put(lookup, newNode);
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

    public String[] getHelp(Player player, int page) {
        ArrayList<String> lines = new ArrayList<String>();

        if (page < 1) {
            page = 1;
        }
        // Get all nodes
        for (HelpNode node : this.nodes.values()) {
            if (node.canUse(player)) {
                addHelpContext(player, node, lines, false);
            }
        }
        // Sort the nodes nicely
        int pageNum = (int) Math.ceil((double) lines.size() / (double) pageSize);
        if(lines.size() % pageSize > 0) {
            pageNum++;
        }
        if (page > pageNum) {
            page = 1;
        }
        int amount = (page - 1) * pageSize;
        // Header
        ArrayList<String> out = new ArrayList<String>(2);
        out.add(Colors.CYAN + Translator.translateAndFormat("help title", page, pageNum));
        for (int i = amount; i < (amount + pageSize); i++) {
            if (lines.size() <= i) {
                break;
            }
            out.add(lines.get(i));
        }

        return out.toArray(new String[out.size()]);
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
        Canary.println("helping search terms page " + page);
        ArrayList<String> hits = new ArrayList<String>();
        for(String key : nodes.keySet()) {
            HelpNode node = nodes.get(key);
            for(String word : terms) {
                if(node.description != null) {
                    if(node.description.toLowerCase().contains(word.toLowerCase()) || node.command.equalsIgnoreCase(word)) {
                        if(player == null || node.canUse(player)) {
                            addHelpContext(player, node, hits, false);
                        }
                        break;
                    }
                }
                if(node.keywords != null) {
                    for(String nodeTerm : node.keywords) {
                        if(nodeTerm.equalsIgnoreCase(word)) {
                            if(player == null || node.canUse(player)) {
                                addHelpContext(player, node, hits, false);
                            }
                            break;
                        }
                    }
                }
            }
        }
        ArrayList<String> lines = new ArrayList<String>();

        int pageNum = (int) Math.ceil((double) hits.size() / (double) pageSize);
        if(hits.size() % pageSize > 0) {
            pageNum++;
        }
        if (page > pageNum) {
            page = 1;
        }
        int amount = (page - 1) * pageSize;
        Canary.println("Amount is " + amount);
        // Header
        lines.add(Colors.CYAN + Translator.translateAndFormat("help title", page, pageNum));
        for (int i = amount; i < (amount + pageSize); i++) {
            if (hits.size() <= i) {
                Canary.println("Breaking at " + i + "list size is " + hits.size());
                break;
            }
            lines.add(hits.get(i));
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
        Canary.println("helping command name");
        HelpNode node = nodes.get(commandName);
        ArrayList<String> lines = new ArrayList<String>();
        if(node != null && node.canUse(caller)) {
            addHelpContext(caller, node, lines, true);
        }
        for(String line : lines) {
            caller.message(line);
        }
    }

    private String[] subCommandsToStringArray(ArrayList<CanaryCommand> cmds) {
        ArrayList<String> list = new ArrayList<String>(cmds.size() +1);
        for(CanaryCommand cmd : cmds) {
            if(cmd.meta.helpLookup().isEmpty()) {
                for(String alias : cmd.meta.aliases()) {
                    list.add(alias);
                }
            }
            else {
                list.add(cmd.meta.helpLookup());
            }
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * Creates the help context including sub commands from the given node.
     * @param node
     * @param list
     */
    private void addHelpContext(MessageReceiver caller, HelpNode node, ArrayList<String> list, boolean printToolTip) {
        if(node.isSubgroup) {
            return;
        }
        list.add(Colors.LIGHT_RED + node.command + Colors.WHITE + " - " + Colors.YELLOW + node.description);
        if(printToolTip) {
            list.add(Colors.LIGHT_RED + node.toolTip);
        }
        for(String sub : node.subCommands) {
            HelpNode subNode = nodes.get(sub);
            if(subNode != null && subNode.canUse(caller)) {
                if(subNode.isSubgroup && subNode.parent.equals(node.command)) {
                    list.add("    " + Colors.TURQUIOSE + subNode.command + Colors.WHITE + " - " + Colors.YELLOW + subNode.description);
                    if(printToolTip) {
                        list.add("    " + Colors.LIGHT_RED + subNode.toolTip);
                    }
                }
            }
        }
    }

    class HelpNode {
        CommandOwner plugin;
        String command;
        String description;
        String toolTip;
        String[] permissions;
        String[] keywords;
        String parent = "";
        boolean isSubgroup = false;
        String[] subCommands = new String[0];

        public boolean canUse(MessageReceiver caller) {
            for(String perm : permissions) {
                if(caller == null || caller.hasPermission(perm)) {
                    return true;
                }
            }
            return false;
        }
    }
}
