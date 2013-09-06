package net.canarymod.api.entity.living.animal;

import net.canarymod.api.entity.living.Ageable;
import net.canarymod.api.entity.vehicle.AnimalVehicle;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.world.blocks.Chest;

/**
 * Horse wrapper interface
 * <p/>
 * NOTE: This covers Horses, Donkeys, Mules, Zombie Horses, and Skeleton Horses.<br>
 * While Horses can be tame, there is no Owner information, there for does not extends Tameable
 *
 * @author Jason (darkdiplomat)
 */
public interface Horse extends AnimalVehicle, Ageable, Tameable {

    /**
     * The different Horse Types
     *
     * @author Jason (darkdiplomat)
     */
    public enum HorseType {
        HORSE, //
        DONKEY, //
        MULE, //
        ZOMBIE, //
        SKELETON, //
        ;
    }

    /**
     * Gets if the Horse is eating Hay
     *
     * @return {@code true} if eating; {@code false} if not
     */
    public boolean isEatingHay();

    /**
     * Gets if the Horse has been bred
     *
     * @return {@code true} if bred; {@code false} if not
     */
    public boolean isBred();

    /**
     * Sets if the Horse was bred
     *
     * @param bred
     *         {@code true} for bred; {@code false} for not
     */
    public void setIsBred(boolean bred);

    /**
     * Gets if the Horse has a {@link Chest} attached
     *
     * @return {@code true} if is chested; {@code false} if not
     */
    public boolean isChested();

    /**
     * Sets if the Horse has a {@link Chest} attached
     *
     * @param chested
     *         {@code true} for chested; {@code false} for not
     */
    public void setIsChested(boolean chested);

    /**
     * Gets if the Horse has reproduced
     *
     * @return {@code true} if reproduced; {@code false} if not
     */
    public boolean hasReproduced();

    /**
     * Sets if the Horse has reproduced
     *
     * @param reproduced
     *         {@code true} for reproduced; {@code false} if not
     */
    public void setHasReproduced(boolean reproduced);

    /**
     * Gets the {@link HorseType} of the Horse
     *
     * @return the Horse Type
     */
    public HorseType getType();

    /**
     * Gets the raw integer type of the Horse
     *
     * @return the type integer
     */
    public int getRawType();

    /**
     * Sets the {@link HorseType} of the Horse
     *
     * @param type
     *         the {@link HorseType} to set
     */
    public void setType(HorseType type);

    /**
     * Sets the type of the horse
     *
     * @param type
     *         {@code 0} for Horse; {@code 1} for Donkey; {@code 2} for Mule; {@code 3} for Zombie; {@code 4} for Skeleton
     */
    public void setType(int type);

    /**
     * Information unknown at this time.
     *
     * @return variant
     */
    public int getVariant();

    /**
     * Information unknown at this time.
     *
     * @param variant
     *         the variant integer
     */
    public void setVariant(int variant);

    /**
     * Gets the Temper level of the Horse. More information is unknown
     *
     * @return temper
     */
    public int getTemper();

    /**
     * Sets the Temper level of the Horse. More information is unknown
     *
     * @param temper
     *         the temper level
     */
    public void setTemper(int temper);

    /**
     * Gets the inventory for the Horse if Chested
     *
     * @return inventory
     */
    public Inventory getInventory();

}
