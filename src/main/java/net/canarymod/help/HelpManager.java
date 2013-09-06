package net.canarymod.help;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeMap;

import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandOwner;

/**
 * @author Jos (Jarvix)
 * @author Chris (damagefilter)
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
     *
     * @param owner
     * @param command
     *
     * @return
     */
    public boolean registerCommand(CommandOwner owner, CanaryCommand command) {
        String basename = command.meta.aliases()[0];
        if (getNode(basename) != null) {
            return false;
        }
        synchronized (nodes) {
            nodes.put(basename, new HelpNode(owner, command));
            return true;
        }
    }

    public boolean registerCommand(CommandOwner owner, CanaryCommand command, String lookup) {
        if (getNode(lookup) != null) {
            return false;
        }
        synchronized (nodes) {
            nodes.put(lookup, new HelpNode(owner, command));
            return true;
        }
    }

    /**
     * Unregister a command
     *
     * @param plugin
     * @param command
     *
     * @return true on success, false on failure
     */
    public boolean unregisterCommand(CommandOwner plugin, String command) {

        HelpNode node = getNode(command);

        if (node == null || node.getOwner() != plugin) {
            return false;
        }

        removeCommand(command.toLowerCase());

        return true;
    }

    /**
     * Unregisters all commands assigned to the given plugin
     *
     * @param owner
     */
    public void unregisterCommands(CommandOwner owner) {
        synchronized (nodes) {
            Iterator<HelpNode> itr = nodes.values().iterator();
            while (itr.hasNext()) {
                HelpNode node = itr.next();
                if (node.getOwner() == owner) {
                    itr.remove();
                }
            }
        }
    }

    /**
     * Check if this command name already is registered
     *
     * @param command
     *
     * @return
     */
    public boolean hasHelp(String command) {
        return getNode(command) != null;
    }

    /**
     * Get the maximum number of entries in one page
     *
     * @return
     */
    public int getEntriesPerPage() {
        return pageSize;
    }

    /**
     * Returns a formatted list (each entry is one line) of commands
     *
     * @param player
     * @param page
     *
     * @return
     */
    public ArrayList<String> getHelp(Player player, int page) {
        ArrayList<String> lines = new ArrayList<String>();

        if (page < 1) {
            page = 1;
        }
        // Get all nodes
        synchronized (nodes) {
            for (HelpNode node : this.nodes.values()) {
                if (node.canUse(player)) {
                    addHelpContext(player, node, lines, false, true);
                }
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
     *
     * @param player
     * @param terms
     * @param page
     *
     * @return
     */
    public ArrayList<String> getHelp(Player player, String[] terms, int page) {
        ArrayList<String> hits = new ArrayList<String>();
        for (String key : nodes.keySet()) {
            HelpNode node = nodes.get(key);
            for (String word : terms) {
                if (node.getDescription() != null) {
                    if (node.getDescription().toLowerCase().contains(word.toLowerCase()) || node.hasAlias(word.toLowerCase())) {
                        if (node.canUse(player)) {
                            addHelpContext(player, node, hits, false, true);
                        }
                        break;
                    }
                }
                if (node.getKeywords() != null) {
                    for (String nodeTerm : node.getKeywords()) {
                        if (nodeTerm.equalsIgnoreCase(word)) {
                            if (node.canUse(player)) {
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
     *
     * @param caller
     *         The MR to show the help for
     * @param commandName
     *         The command for which help is required
     */
    public void getHelp(MessageReceiver caller, String commandName) {
        HelpNode node = getNode(commandName);

        ArrayList<String> lines = new ArrayList<String>();
        if (node != null && node.canUse(caller)) {
            addHelpContext(caller, node, lines, true, false);
        }
        if (lines.size() == 0) {
            caller.message(Translator.translate("help not found"));
            return;
        }
        for (String line : lines) {
            caller.message(line);
        }
    }

    /**
     * Get the HelpNode for the given command.
     * Will return <code>null</code> if command is not registered
     *
     * @param command
     *
     * @return HelpNode || null
     */
    public HelpNode getRawHelp(String command) {
        return getNode(command);
    }

    /**
     * Returns all help nodes that the player has access too.
     *
     * @param caller
     *
     * @return
     */
    public ArrayList<HelpNode> getRawHelp(MessageReceiver caller) {
        return new ArrayList<HelpNode>(nodes.values());
    }

    static String[] subCommandsToStringArray(ArrayList<CanaryCommand> cmds) {
        ArrayList<String> list = new ArrayList<String>(cmds.size() + 1);
        for (CanaryCommand cmd : cmds) {
            if (cmd.meta.helpLookup().isEmpty()) {
                Collections.addAll(list, cmd.meta.aliases());
            }
            else {
                list.add(cmd.meta.helpLookup());
            }
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * Creates the help context including sub commands from the given node.
     *
     * @param node
     * @param list
     * @param ignoreSubCommands
     */
    private void addHelpContext(MessageReceiver caller, HelpNode node, ArrayList<String> list, boolean printToolTip, boolean ignoreSubCommands) {
        if (node.isSubCommand() && ignoreSubCommands) {
            return;
        }
        list.add(Colors.LIGHT_RED + node.getPrintableAliases(Colors.TURQUIOSE) + " - " + Colors.YELLOW + node.getDescription());
        if (printToolTip) {
            list.add(Colors.LIGHT_GRAY + node.getTooltip());
        }
        for (String sub : node.subCommands) {
            HelpNode subNode = nodes.get(sub);
            if (subNode != null && subNode.canUse(caller)) {
                if (subNode.isSubCommand() && subNode.getParent().equals(node.getCommand())) {
                    list.add("    " + subNode.getPrintableAliases(Colors.ORANGE) + " - " + Colors.YELLOW + subNode.getDescription());
                    if (printToolTip) {
                        list.add("    " + Colors.LIGHT_GRAY + subNode.getTooltip());
                    }
                }
            }
        }
    }

    private HelpNode getNode(String name) {
        synchronized (nodes) {
            if (nodes.containsKey(name)) {
                return nodes.get(name);
            }
            for (HelpNode n : nodes.values()) {
                if (n.hasAlias(name)) {
                    return n;
                }
            }
            return null;
        }
    }

    private void removeCommand(String name) {
        if (nodes.containsKey(name)) {
            nodes.remove(name);
        }
        synchronized (nodes) {
            Iterator<HelpNode> itr = nodes.values().iterator();
            while (itr.hasNext()) {
                HelpNode n = itr.next();
                if (n.hasAlias(name)) {
                    itr.remove();
                }
            }
        }
    }
}
