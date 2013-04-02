package net.canarymod.api.factory;

import net.canarymod.api.VillagerTrade;
import net.canarymod.api.inventory.Item;

/**
 * Object Manufacturing Factory
 * <p>
 * For the odds and ends that don't fit else where
 * 
 * @author Jason (darkdiplomat)
 */
public interface ObjectFactory {

    VillagerTrade newVillagerTrade(Item buying, Item selling);

    VillagerTrade newVillagerTrade(Item buyingOne, Item buyingTwo, Item selling);
}
