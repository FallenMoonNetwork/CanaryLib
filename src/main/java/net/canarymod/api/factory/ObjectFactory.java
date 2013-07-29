package net.canarymod.api.factory;

import net.canarymod.api.MobSpawnerEntry;
import net.canarymod.api.VillagerTrade;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.living.humanoid.Villager;
import net.canarymod.api.inventory.CustomStorageInventory;
import net.canarymod.api.inventory.Item;

/**
 * Object Manufacturing Factory
 * <p>
 * For the odds and ends that don't fit else where
 * 
 * @author Jason (darkdiplomat)
 */
public interface ObjectFactory {

    /**
     * Creates a new {@link VillagerTrade}
     * 
     * @param buying
     *            the {@link Item} the {@link Villager} will buy
     * @param selling
     *            the {@link Item} the {@link Villager} will sell
     * @return new {@link VillagerTrade}
     */
    VillagerTrade newVillagerTrade(Item buying, Item selling);

    /**
     * Creates a new {@link Villager} trade
     * 
     * @param buyingOne
     *            the {@link Item} the {@link Villager} will buy
     * @param buyingTwo
     *            the second {@link Item} the {@link Villager} will buy
     * @param selling
     *            the {@link Item} the {@link Villager} will sell
     * @return new {@link VillagerTrade}
     */
    VillagerTrade newVillagerTrade(Item buyingOne, Item buyingTwo, Item selling);

    /**
     * Creates a new {@link MobSpawnerEntry}
     * 
     * @param entity
     *            the name of the {@link Entity} to create entry for
     * @return new {@link MobSpawnerEntry}
     */
    MobSpawnerEntry newMobSpawnerEntry(String entity);

    /**
     * Creates a new {@link MobSpawnerEntry}
     * 
     * @param entity
     *            the {@link Entity} to create entry for
     * @return new {@link MobSpawnerEntry}
     */
    MobSpawnerEntry newMobSpawnerEntry(Entity entity);

    /**
     * Creates a new {@link MobSpawnerEntry}
     * 
     * @param item
     *            the {@link Item} to create entry for
     * @return new {@link MobSpawnerEntry}
     */
    MobSpawnerEntry newMobSpawnerEntry(Item item);

    /**
     * Creates a new empty CustomStorageInventory
     * 
     * @param rows
     *            the number of Rows (9 slots per row)
     * @return new {@link CustomStorageInventory}
     */
    CustomStorageInventory newCustomStorageInventory(int rows);

    /**
     * Creates a new empty CustomStorageInventory
     * 
     * @param name
     *            the name of the custom inventory
     * @param rows
     *            the number of Rows (9 slots per row)
     * @return new {@link CustomStorageInventory}
     */
    CustomStorageInventory newCustomStorageInventory(String name, int rows);

}
