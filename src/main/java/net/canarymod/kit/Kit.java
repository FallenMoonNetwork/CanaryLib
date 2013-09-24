package net.canarymod.kit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.canarymod.Canary;
import net.canarymod.ToolBox;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.Item;
import net.canarymod.chat.TextFormat;

/**
 * Contains information about a kit
 *
 * @author Chris (damagefilter)
 */
public class Kit {

    private int id;

    /** Time between uses as unix timestamp applicable number */
    private int delay;

    /** Owner if applicable */
    private String[] owners = null;

    /** Groups if applicable */
    private String[] groups = null;

    /** List of last usages per player */
    private HashMap<String, Long> lastUsages = new HashMap<String, Long>();

    private String name;

    /**
     * The content of this kit as IItems Each list entry shall be a different
     * Item
     */
    private ArrayList<Item> content = new ArrayList<Item>();

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public String[] getOwner() {
        return owners;
    }

    public void setOwner(String[] owner) {
        this.owners = owner;
    }

    public String[] getGroups() {
        return groups;
    }

    public void setGroups(String[] groups) {
        this.groups = groups;
    }

    public ArrayList<Item> getContent() {
        return content;
    }

    public void setContent(ArrayList<Item> content) {
        this.content = content;
    }

    /**
     * Give this kit to player, if possible
     *
     * @param player
     *         the {@link Player} to give a kit too
     * @param override
     *         set to true to override delay, group, and owner checks
     *
     * @return {@code true} if successful; {@code false} if not
     */
    public boolean giveKit(Player player, boolean override) {
        if (override) {
            apply(player);
            return true;
        }
        Long lastUsed = lastUsages.get(player.getName());

        if (lastUsed == null) {
            lastUsed = 0L;
            lastUsages.put(player.getName(), lastUsed);
        }
        if (lastUsed.longValue() + delay < ToolBox.getUnixTimestamp()) {
            if (owners != null && owners.length > 0) {
                for (String owner : owners) {
                    if (owner.equals(player.getName())) {
                        lastUsages.put(player.getName(), ToolBox.getUnixTimestamp());
                        apply(player);
                        return true;
                    }
                }
                return false;
            }
            if (groups != null && groups.length > 0) {
                for (String g : groups) {
                    if (player.getGroup().hasControlOver(Canary.usersAndGroups().getGroup(g))) {
                        lastUsages.put(player.getName(), ToolBox.getUnixTimestamp());
                        apply(player);
                        return true;
                    }
                    else if (player.isInGroup(g, false)) {
                        apply(player);
                        lastUsages.put(player.getName(), ToolBox.getUnixTimestamp());
                        return true;
                    }
                }
                return false;
            }
            // Both null, must be public
            lastUsages.put(player.getName(), ToolBox.getUnixTimestamp());
            apply(player);
            return true;
        }
        else {
            player.notice("You have to wait " + TextFormat.ORANGE + ToolBox.getTimeUntil(lastUsed.longValue(), delay) + TextFormat.LIGHT_RED + " before using again.");
            return false;
        }
    }

    private void apply(Player player) {
        for (Item item : content) {
            if (item != null) { // Where these null items come from, ill never know
                item.setSlot(-1);
                player.giveItem(item);
            }
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Mostly used for adding the items into the database
     *
     * @return
     */
    public ArrayList<String> getItemsAsStringList() {
        ArrayList<String> list = new ArrayList<String>();

        for (Item i : content) {
            if (i != null) { // Null breaks the serializer... And we don't really need to track null items
                list.add(Canary.serialize(i));
            }
        }
        return list;
    }

    /**
     * Used to create a new item list from data coming from the database
     *
     * @param items
     */
    public void setContentFromStrings(List<String> items) {
        content.clear();
        for (String str : items) {
            content.add(Canary.deserialize(str, Item.class));
        }
    }

    /**
     * get the ID of this kit
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Only set this if you're 110% sure what you're doing.
     * Changing the ID will not always have an effect.
     * If you want to copy a kit and create a new one, change this kit
     * to your likings, then add it as new to the BackboneKits.
     * A new ID will be auto-assigned then.
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
}
