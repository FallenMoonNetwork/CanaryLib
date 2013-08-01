package net.canarymod.api.inventory.fireworks;

import java.util.ArrayList;
import java.util.Iterator;
import net.canarymod.Canary;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;
import net.canarymod.api.nbt.BaseTag;
import net.canarymod.api.nbt.CompoundTag;
import net.canarymod.api.nbt.ListTag;

/**
 * Firework Rocket helper
 * 
 * @author Jason (darkdiplomat)
 */
public class FireworkRocketItem {
    private Item fireworkRocket;

    /**
     * Constructs a new FireworkRocket helper
     * 
     * @param fireworkRocket
     *            the FireworkRocket item
     * @throws IllegalArgumentException
     *             if the item passed is not a FireworkRocket
     */
    public FireworkRocketItem(Item fireworkRocket) throws IllegalArgumentException {
        if (fireworkRocket.getType() != ItemType.FireworkRocket) {
            throw new IllegalArgumentException(String.format("Given Item Argument was not of the type FireworkRocket (Given: %s)", fireworkRocket));
        }
        this.fireworkRocket = fireworkRocket;
        fireworkRocket.getDataTag();
    }

    /**
     * Gets all the {@link FireworkStar} attached to the FireworkRocket.<br>
     * Manipulation of the stars should work without the need to re-attach.
     * 
     * @return {@link FireworkStar} array
     */
    public FireworkStar[] getAttachedFireworkStars() {
        if (hasFireworksData() && hasExplosionsData()) {
            ListTag<BaseTag> explosions = fireworkRocket.getDataTag().getCompoundTag("Fireworks").getListTag("Explosions");
            if (!explosions.isEmpty()) {
                ArrayList<FireworkStar> stars = new ArrayList<FireworkStar>();
                for (BaseTag explosion : explosions) {
                    if (explosion instanceof CompoundTag) {
                        stars.add(new FireworkStar((CompoundTag) explosion));
                    }
                }
                return stars.toArray(new FireworkStar[stars.size()]);
            }
        }
        return null;
    }

    /**
     * Attaches the Explosions tag from the {@link FireworkStar}
     * 
     * @param star
     *            the {@link FireworkStar} to use
     */
    public void attachFireworkStar(FireworkStar star) {
        if (hasFireworksData() && hasExplosionsData()) {
            fireworkRocket.getDataTag().getCompoundTag("Fireworks").getListTag("Explosions").add(star.getExplosionTag());
        }
    }

    /**
     * Attempts to remove a {@link FireworkStar} information from the FireworkRocket<br>
     * For best Success, use the FireworkStar object retrieved from the {@link #getFireworkStars()} method
     * 
     * @param star
     *            the {@link FireworkStar} to have removed
     * @return {@code true} if successful; {@code false} if not successful
     */
    public boolean removeFireworkStar(FireworkStar star) {
        if (hasFireworksData() && hasExplosionsData()) {
            if (fireworkRocket.getDataTag().getCompoundTag("Fireworks").getListTag("Explosions").remove(star.getExplosionTag())) {
                return true; // Success with same tag
            }
            else {
                // We need to do some magic
                ListTag<CompoundTag> explosions = fireworkRocket.getDataTag().getCompoundTag("Fireworks").getListTag("Explosions");
                Iterator<CompoundTag> expItr = explosions.iterator();
                while (expItr.hasNext()) {
                    if (star.equals(expItr.next())) {
                        expItr.remove();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Gets the Flight Duration of the FireworkRocket<br>
     * Flight Duration is normally a byte between 1 and 3
     * 
     * @return flight duration byte or {@code 0} if flight duration was not initialized
     */
    public byte getFlightDuration() {
        if (hasFireworksData() && hasFlightData()) {
            return fireworkRocket.getDataTag().getCompoundTag("Fireworks").getByte("Flight");
        }
        return 0;
    }

    /**
     * Gets the Flight Duration of the FireworkRocket<br>
     * Flight Duration is normally a byte between 1 and 3<br>
     * 
     * @param duration
     *            the flight duration byte; if arg is less than 1 it is set to 1; if arg is greater than 3 it is set to 3
     */
    public void setFlightDuration(byte duration) {
        if (duration > 3) {
            duration = 3;
        }
        if (duration < 1) {
            duration = 1;
        }
        fireworkRocket.getDataTag().getCompoundTag("Fireworks").put("Flight", duration);
    }

    /**
     * Gets the {@link Item} Firework Rocket
     * 
     * @return the {@link Item}
     */
    public Item getItem() {
        return fireworkRocket;
    }

    /**
     * Checks if the FireworkRocket has firework data
     * 
     * @return {@code true} if has data; {@code false} if not
     */
    private boolean hasFireworksData() {
        if (!fireworkRocket.hasDataTag()) {
            fireworkRocket.setDataTag(Canary.factory().getNBTFactory().newCompoundTag("tag"));
            return false;
        }
        return fireworkRocket.getDataTag().containsKey("Fireworks");
    }

    /**
     * Checks if the FireworkRocket has explosions data
     * 
     * @return {@code true} if has data; {@code false} if not
     */
    private boolean hasExplosionsData() {
        return fireworkRocket.getDataTag().getCompoundTag("Fireworks").containsKey("Explosions");
    }

    /**
     * Checks if the FireworkRocket has flight data
     * 
     * @return {@code true} if has data; {@code false} if not
     */
    private boolean hasFlightData() {
        return fireworkRocket.getDataTag().getCompoundTag("Fireworks").containsKey("Flight");
    }
}
