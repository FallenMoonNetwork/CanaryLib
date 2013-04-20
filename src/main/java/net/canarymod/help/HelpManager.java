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
     * Registers a command. This is called from CommandManager upon registering a command.
     * Typically you don't need to call this yourself, however if you need to add custom help
     * content, then this is the right place for it.
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
            HelpNode newnode = new HelpNode();
            newnode.plugin = owner;
            newnode.command = alias;
            newnode.description = command.getLocaleDescription();
            newnode.toolTip = command.meta.toolTip();
            newnode.permissions = command.meta.permissions();
            newnode.subCommands = subCommandsToStringArray(command.getSubCommands());
            newnode.isSubgroup = !command.meta.parent().isEmpty();
            newnode.parent = command.meta.parent();
            newnode.keywords = command.meta.searchTerms();
            nodes.put(alias, newnode);
            added = true;
        }
        return added;
    }

    public boolean registerCommand(CommandOwner owner, CanaryCommand command, String lookup) {
        if(nodes.containsKey(lookup)) {
            return false;
        }
        HelpNode newnode = new HelpNode();
        newnode.plugin = owner;
        newnode.command = lookup;
        newnode.description = command.getLocaleDescription();
        newnode.toolTip = command.meta.toolTip();
        newnode.permissions = command.meta.permissions();
        newnode.subCommands = subCommandsToStringArray(command.getSubCommands());
        newnode.isSubgroup = !command.meta.parent().isEmpty();
        newnode.parent = command.meta.parent();
        newnode.keywords = command.meta.searchTerms();
        nodes.put(lookup, newnode);
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

    public ArrayList<String> getHelp(Player player, int page) {
        ArrayList<String> lines = new ArrayList<String>();

        if (page < 1) {
            page = 1;
        }
        // Get all nodes
        for (HelpNode node : this.nodes.values()) {
            if (node.canUse(player)) {
                addHelpContext(player, node, lines, false, true);
            }
        }
        int pageNum = (int) Math.ceil((double) lines.size() / (double) pageSize);

        if (page > pageNum) {
            page = 1;
        }
        int amount = (page - 1) * pageSize;
        // Header
        ArrayList<String> out = new ArrayList<String>(2);
        out.add(Colors.CYAN + Translator.translateAndFormat("help title", page, pageNum));
        Canary.println("Page " + page + "from " + pageNum);
        for (int i = amount; i < (amount + pageSize); i++) {
            if (lines.size() <= i) {
                break;
            }
            out.add(lines.get(i));
        }
        return out;
    }

    /**
     * Searches through available help nodes for the given array of words
     * and returns help messages according to {@link Player} permissions
     * @param player
     * @param terms
     * @param page
     * @return
     */
    public ArrayList<String> getHelp(Player player, String[] terms, int page) {
        ArrayList<String> hits = new ArrayList<String>();
        for(String key : nodes.keySet()) {
            HelpNode node = nodes.get(key);
            for(String word : terms) {
                if(node.description != null) {
                    if(node.description.toLowerCase().contains(word.toLowerCase()) || node.command.equalsIgnoreCase(word)) {
                        if(player == null || node.canUse(player)) {
                            addHelpContext(player, node, hits, false, true);
                        }
                        break;
                    }
                }
                if(node.keywords != null) {
                    for(String nodeTerm : node.keywords) {
                        if(nodeTerm.equalsIgnoreCase(word)) {
                            if(player == null || node.canUse(player)) {
                                addHelpContext(player, node, hits, false, true);
                            }
                            break;
                        }
                    }
                }
            }
        }
        ArrayList<String> lines = new ArrayList<String>();

        int pageNum = (int) Math.ceil((double) hits.size() / (double) pageSize);
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
            lines.add(hits.get(i));
        }
        return lines;
    }


    /**
     * Displays the given commands description and toolTip,
     * if the permissions allow it.
     * @param caller The MR to show the help for
     * @param commandName The command for which help is required
     */
    public void getHelp(MessageReceiver caller, String commandName) {
        HelpNode node = nodes.get(commandName);

        ArrayList<String> lines = new ArrayList<String>();
        if(node != null && node.canUse(caller)) {
            addHelpContext(caller, node, lines, true, false);
        }
        for(String line : lines) {
            caller.message(line);
        }
    }

    /**
     * Get the HelpNode for the given command.
     * Will return <code>null</code> if command is not registered
     * @param command
     * @return HelpNode || null
     */
    public HelpNode getRawHelp(String command) {
        return nodes.get(command);
    }

    /**
     * Returns all help nodes that the player has access too.
     * @param caller
     * @return
     */
    public ArrayList<HelpNode> getRawHelp(MessageReceiver caller) {
        ArrayList<HelpNode> list = new ArrayList<HelpNode>();
        for(String cmd : nodes.keySet()) {
            HelpNode node = nodes.get(cmd);
            if(node.canUse(caller)) {
                list.add(node);
            }
        }
        return list;
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
     * @param ignoreSubgroups TODO
     */
    private void addHelpContext(MessageReceiver caller, HelpNode node, ArrayList<String> list, boolean printToolTip, boolean ignoreSubgroups) {
        if(node.isSubgroup && ignoreSubgroups) {
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
}
