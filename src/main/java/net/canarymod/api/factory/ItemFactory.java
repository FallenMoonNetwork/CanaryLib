package net.canarymod.api.factory;

import net.canarymod.api.inventory.Enchantment;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;

/**
 * Item Manufacturing Factory
 * 
 * @author Brian (WWOL)
 * @author Jason (darkdiplomat)
 */
public interface ItemFactory {

    /**
     * Make a new {@link Item} with an id. Damage 0, stack size = 0
     * 
     * @param id
     * @return
     */
    Item newItem(int id);

    /**
     * Make a new {@link Item} with id, damage. Stack size 0
     * 
     * @param id
     * @param damage
     * @return
     */
    Item newItem(int id, int damage);

    /**
     * Make a new {@link Item} with id, damage, stack size
     * 
     * @param id
     * @param damage
     * @return
     */
    Item newItem(int id, int damage, int stackSize);

    /**
     * Make a new {@link Item} with item type. Damage 0, stack size 0
     * 
     * @param id
     * @param damage
     * @return
     */
    Item newItem(ItemType type);

    /**
     * Make a new {@link Item} with item type, damage. Stack size 0
     * 
     * @param id
     * @param damage
     * @return
     */
    Item newItem(ItemType type, int damage);

    /**
     * Make a new {@link Item} with item type, damage, stack size
     * 
     * @param id
     * @param damage
     * @return
     */
    Item newItem(ItemType type, int damage, int stackSize);

    /**
     * Make a new {@link Item} with another item
     * 
     * @param id
     * @param damage
     * @return
     */
    Item newItem(Item item);

    /**
     * Make a new {@link Item} with id, damage and enchantments
     * 
     * @param id
     * @param damage
     * @param enchantments
     * @return
     */
    Item newItem(int id, int damage, Enchantment[] enchantments);

    /**
     * Parses a new {@link Item} with a given command input string.
     * This String needs to be formatted like:<br>
     * itemName:data or <br>
     * itemId:data<br>
     * Alternatively it can only be the itemId or name, then default 0 will be set as data
     * 
     * @param commandInput
     * @return Item or null if the itemName/id is not a valid ItemType
     */
    Item newItem(String commandInput);

    /**
     * Creates a new {@link Enchantment}
     * 
     * @param id
     *            the ID of the {@link Enchantment}
     * @param level
     *            the level of the {@link Enchantment}
     * @return a new {@link Enchantment} if arguments are valid
     */
    Enchantment newEnchantment(short id, short level);

    /**
     * Creates a new {@link Enchantment}
     * 
     * @param type
     *            the Type of the {@link Enchantment}
     * @param level
     *            the level of the {@link Enchantment}
     * @return a new {@link Enchantment} if arguments are valid
     */
    Enchantment newEnchantment(Enchantment.Type type, short level);

}
