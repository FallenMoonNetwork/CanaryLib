package net.canarymod.kit;

import java.util.ArrayList;
import java.util.HashMap;

import net.canarymod.Canary;
import net.canarymod.Logman;
import net.canarymod.api.entity.Player;
import net.canarymod.api.inventory.Item;

public class Kit {
    /**
     * Time between uses as unix timestamp applicable number
     */
    private int delay;

    /**
     * Owner if applicable
     */
    private String[] owners = null;

    /**
     * Groups if applicable
     */
    private String[] groups = null;

    /**
     * List of last usages per player
     */
    private HashMap<String, Long> lastUsages = new HashMap<String, Long>();

    private String name;
    /**
     * The content of this kit as IItems Each list entry shall be a different
     * Item
     */
    private ArrayList<Item> content;

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
     * @return
     */
    public boolean giveKit(Player player) {
        Long lastUsed = lastUsages.get(player.getName());
        
        if (lastUsed == null) {
            lastUsed = new Long(0L);
            lastUsages.put(player.getName(), lastUsed);
        }
        if (lastUsed.longValue() + delay < Canary.getUnixTimestamp()) {
            Logman.println("Delay has passed");
            if (owners != null) {
                Logman.println("Owner not null");
                for (String owner : owners) {
                    if (owner.equals(player.getName())) {
                        lastUsages.put(player.getName(), Canary.getUnixTimestamp());
                        apply(player);
                        return true;
                    }
                }
                return false;
            }
            if (groups != null) {
                Logman.println("Groups not null");
                for (String g : groups) {
                    if (player.getGroup().hasControlOver(g)) {
                        lastUsages.put(player.getName(), Canary.getUnixTimestamp());
                        apply(player);
                        return true;
                    } 
                    else if (player.isInGroup(g, false)) {
                        apply(player);
                        lastUsages.put(player.getName(), Canary.getUnixTimestamp());
                        return true;
                    } 
                }
                return false;
            }
            Logman.println("Public kit");
            //Both null, must be public
            apply(player);
            return true;
        } 
        else {
            return false;
        }
    }

    private void apply(Player player) {
        for (Item item : content) {
            item.setSlot(-1);
            player.giveItem(item);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
