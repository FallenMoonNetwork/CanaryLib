package net.canarymod.api.inventory.fireworks;

import java.util.ArrayList;
import java.util.Arrays;
import net.canarymod.Canary;
import net.canarymod.api.DyeColor;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;
import net.canarymod.api.nbt.CompoundTag;

/**
 * FireworkStar helper class
 * <p>
 * Helps define a FireworkStar's data tags
 * 
 * @author Jason (darkdiplomat)
 */
public class FireworkStar {
    private Item fireworkStar;

    /**
     * Constructs a new FireworkStar and creating a new {@link Item}
     */
    public FireworkStar() {
        fireworkStar = Canary.factory().getItemFactory().newItem(ItemType.FireworkStar);
        fireworkStar.setAmount(1);
    }

    /**
     * Constructs a new FireworkStar based on specified {@link Item}
     * 
     * @param fireworkStar
     *            the {@link Item} that is a FireworkStar
     * @throws IllegalArgumentException
     *             it the {@link Item} argument is not of the {@link ItemType#FireworkStar} type
     */
    public FireworkStar(Item fireworkStar) throws IllegalArgumentException {
        if (fireworkStar == null || fireworkStar.getType() != ItemType.FireworkStar) {
            throw new IllegalArgumentException(String.format("Given Item Argument was not of the type FireworkStar (Given: %s)", fireworkStar));
        }
        this.fireworkStar = fireworkStar;
    }

    /**
     * Constructs a new FireworkStar and creating a new {@link Item}
     * 
     * @param explosion_type
     *            the {@link FireworkExplosion} to initialize with
     */
    public FireworkStar(FireworkExplosion explosion_type) {
        this(explosion_type, false, false, (DyeColor[]) null);
    }

    /**
     * Constructs a new FireworkStar and creating a new {@link Item}
     * 
     * @param colors
     *            the {@link DyeColor}(s) to add
     */
    public FireworkStar(DyeColor... colors) {
        this(null, false, false, colors);
    }

    /**
     * Constructs a new FireworkStar and creating a new {@link Item}
     * 
     * @param colors
     *            the colors to add
     */
    public FireworkStar(int... colors) {
        this(null, false, false, colors);
    }

    /**
     * Constructs a new FireworkStar and creating a new {@link Item}
     * 
     * @param explosion_type
     *            the {@link FireworkExplosion} to initialize with
     * @param colors
     *            the {@link DyeColor}(s) to add
     */
    public FireworkStar(FireworkExplosion explosion_type, DyeColor... colors) {
        this(explosion_type, false, false, colors);
    }

    /**
     * Constructs a new FireworkStar and creating a new {@link Item}
     * 
     * @param explosion_type
     *            the {@link FireworkExplosion} to initialize with
     * @param colors
     *            the colors to add
     */
    public FireworkStar(FireworkExplosion explosion_type, int... colors) {
        this(explosion_type, false, false, colors);
    }

    /**
     * Constructs a new FireworkStar and creating a new {@link Item}
     * 
     * @param trail
     *            {@code true} for trail; {@code false} for not
     * @param flicker
     *            {@code true} for flicker; {@code false} for not
     */
    public FireworkStar(boolean trail, boolean flicker) {
        this(null, false, false, (DyeColor[]) null);
    }

    /**
     * Constructs a new FireworkStar and creating a new {@link Item}
     * 
     * @param explosion_type
     *            the {@link FireworkExplosion} to initialize with
     * @param trail
     *            {@code true} for trail; {@code false} for not
     * @param flicker
     *            {@code true} for flicker; {@code false} for not
     */
    public FireworkStar(FireworkExplosion explosion_type, boolean trail, boolean flicker) {
        this(explosion_type, trail, flicker, (DyeColor[]) null);
    }

    /**
     * Constructs a new FireworkStar and creating a new {@link Item}
     * 
     * @param trail
     *            {@code true} for trail; {@code false} for not
     * @param flicker
     *            {@code true} for flicker; {@code false} for not
     * @param colors
     *            the {@link DyeColor}(s) to add
     */
    public FireworkStar(boolean trail, boolean flicker, DyeColor... colors) {
        this(null, trail, flicker, colors);
    }

    /**
     * Constructs a new FireworkStar and creating a new {@link Item}
     * 
     * @param trail
     *            {@code true} for trail; {@code false} for not
     * @param flicker
     *            {@code true} for flicker; {@code false} for not
     * @param colors
     *            the colors to add
     */
    public FireworkStar(boolean trail, boolean flicker, int... colors) {
        this(null, trail, flicker, colors);
    }

    /**
     * Constructs a new FireworkStar and creating a new {@link Item}
     * 
     * @param explosion_type
     *            the {@link FireworkExplosion} to initialize with
     * @param trail
     *            {@code true} for trail; {@code false} for not
     * @param flicker
     *            {@code true} for flicker; {@code false} for not
     * @param colors
     *            the {@link DyeColor}(s) to add
     */
    public FireworkStar(FireworkExplosion explosion_type, boolean trail, boolean flicker, DyeColor... colors) {
        this();
        setExplosionType(explosion_type);
        setDoesTrail(trail);
        setDoesFlicker(flicker);
        setColors(colors);
    }

    /**
     * Constructs a new FireworkStar and creating a new {@link Item}
     * 
     * @param explosion_type
     *            the {@link FireworkExplosion} to initialize with
     * @param trail
     *            {@code true} for trail; {@code false} for not
     * @param flicker
     *            {@code true} for flicker; {@code false} for not
     * @param colors
     *            the colors to add
     */
    public FireworkStar(FireworkExplosion explosion_type, boolean trail, boolean flicker, int... colors) {
        this();
        setExplosionType(explosion_type);
        setDoesTrail(trail);
        setDoesFlicker(flicker);
        setColorsRaw(colors);
    }

    /**
     * Sets the {@link FireworkExplosion} type of the FireworkStar
     * 
     * @param explosion_type
     *            the {@link FireworkExplosion} type
     */
    public void setExplosionType(FireworkExplosion explosion_type) {
        if (explosion_type == null || explosion_type == FireworkExplosion.UNKNOWN) {
            return;
        }
        getExplosionTag().put("Type", (byte) explosion_type.ordinal());
    }

    /**
     * Gets the {@link FireworkExplosion} of the FireworkStar
     * 
     * @return the {@link FireworkExplosion} of the FireworkStar
     */
    public FireworkExplosion getExplosionType() {
        if (getExplosionTag().containsKey("Type")) {
            byte type = fireworkStar.getDataTag().getCompoundTag("Explosion").getByte("Type");
            switch (type) {
                case 0:
                    return FireworkExplosion.SMALL;
                case 1:
                    return FireworkExplosion.LARGE;
                case 2:
                    return FireworkExplosion.STAR;
                case 3:
                    return FireworkExplosion.CREEPER;
                case 4:
                    return FireworkExplosion.BURST;
                default:
                    return FireworkExplosion.UNKNOWN;
            }
        }
        return null;
    }

    /**
     * Gets whether the FireworkStar leaves a trail
     * 
     * @return {@code true} if trails; {@code false} if not
     */
    public boolean doesTrail() {
        if (getExplosionTag().containsKey("Trail")) { // Test for tag
            return getExplosionTag().getBoolean("Trail"); // Get value
        }
        getExplosionTag().put("Trail", false); // Initialize tag for later use
        return false;
    }

    /**
     * Sets whether the FireworkStar leaves a trail
     * 
     * @param trail
     *            {@code true} for trails; {@code false} for not
     */
    public void setDoesTrail(boolean trail) {
        getExplosionTag().put("Trail", trail);
    }

    /**
     * Gets whether the FireworkStar does flicker
     * 
     * @return {@code true} if does flicker; {@code false} if not
     */
    public boolean doesFlicker() {
        if (getExplosionTag().containsKey("Flicker")) { // Test for tag
            return getExplosionTag().getBoolean("Flicker"); // Get value
        }
        getExplosionTag().put("Flicker", false); // Initialize tag for later use
        return false;
    }

    /**
     * Sets whether the FireworkStar does flicker
     * 
     * @param flicker
     *            {@code true} for flickering; {@code false} for not
     */
    public void setDoesFlicker(boolean flicker) {
        getExplosionTag().put("Flicker", flicker);
    }

    /**
     * Gets the colors of the FireworkStar as a {@link DyeColor} array
     * 
     * @return a {@link DyeColor} array if there are colors; {@code null} if no colors
     */
    public DyeColor[] getColors() {
        if (getExplosionTag().containsKey("Colors")) {
            int[] colors = getExplosionTag().getIntArray("Colors");
            DyeColor[] dyeColors = new DyeColor[colors.length];
            for (int index = 0; index < colors.length; index++) {
                dyeColors[index] = DyeColor.fromDecimalCode(colors[index]);
            }
            return dyeColors;
        }
        return null;
    }

    /**
     * Gets the raw decimal colors of the FireworkStar
     * 
     * @return a {@code int array} if there are colors; {@code null} if no colors
     */
    public int[] getColorsRaw() {
        if (getExplosionTag().containsKey("Colors")) {
            return getExplosionTag().getIntArray("Colors");
        }
        return null;
    }

    /**
     * Sets the colors of the FireworkStar based on the given {@link DyeColor}s<br>
     * Giving {@link DyeColor#CUSTOM} will result it the color being ignored.<br>
     * For custom colors use {@link #setColorsRaw(int...)}
     * 
     * @param colors
     *            the {@link DyeColors} to set to the FireworkStar
     */
    public void setColors(DyeColor... colors) {
        if (colors == null || colors.length < 1) {
            return;
        }
        ArrayList<Integer> rawColors = new ArrayList<Integer>();
        for (int index = 0; index < colors.length; index++) {
            if (colors[index] == null || colors[index] == DyeColor.CUSTOM) {
                continue;
            }
            rawColors.add(colors[index].getDecimalCode());
        }
        int[] rawColorsArray = new int[rawColors.size()];
        for (int index = 0; index < rawColors.size(); index++) {
            rawColorsArray[index] = rawColors.get(index).intValue();
        }
        getExplosionTag().put("Colors", rawColorsArray);
    }

    /**
     * Sets the colors of the FireworkStar based on raw integers
     * 
     * @param colors
     *            the {@code int} colors
     * @see <a href="http://www.mathsisfun.com/hexadecimal-decimal-colors.html">http://www.mathsisfun.com/hexadecimal-decimal-colors.html</a> for help with colors
     */
    public void setColorsRaw(int... colors) {
        if (colors == null || colors.length < 1) {
            return;
        }
        for (int index = 0; index < colors.length; index++) {
            if (colors[index] > 0xFFFFFF) { // if the color is greater than hex:FFFFFF (dec: 16777215), its invalid
                colors[index] = 0xFFFFFF;
            }
            else if (colors[index] < 0) { // if the color is less than 0, its invalid
                colors[index] = 0;
            }
        }
        getExplosionTag().put("Colors", colors);
    }

    /**
     * Adds a color to the FireworkStar based on {@link DyeColor}<br>
     * NOTE: If the {@link DyeColor} is {@link DyeColor#CUSTOM}, no action will be taken
     * 
     * @param color
     *            the {@link DyeColor} to be added
     */
    public void addColor(DyeColor color) {
        if (color == null || color == DyeColor.CUSTOM) {
            return;
        }
        addColorRaw(color.getDecimalCode());
    }

    /**
     * Adds a color to the FireworkStar based on raw integers
     * 
     * @param color
     *            the {@code int} color
     * @see <a href="http://www.mathsisfun.com/hexadecimal-decimal-colors.html">http://www.mathsisfun.com/hexadecimal-decimal-colors.html</a> for help with colors
     */
    public void addColorRaw(int color) {
        if (color < 0) { // if the color is less than 0, its invalid
            color = 0; // increase to min allowed decimal
        }
        else if (color > 0xFFFFFF) { // if the color is greater than hex:FFFFFF (dec: 16777215), its invalid
            color = 0xFFFFFF; // Reduce to max allowed decimal
        }
        int[] rawColors = getExplosionTag().containsKey("Colors") ? getExplosionTag().getIntArray("Colors") : null;
        if (rawColors != null) {
            int[] newColors = Arrays.copyOf(rawColors, rawColors.length + 1);
            newColors[rawColors.length] = color;
            getExplosionTag().put("Colors", newColors);
        }
        else {
            getExplosionTag().put("Colors", new int[]{ color });
        }
    }

    /**
     * Removes a color from the FireworkStar<br>
     * NOTE: if the {@link DyeColor} is {@link DyeColor#CUSTOM} no actions will be preformed
     * 
     * @param color
     *            the {@link DyeColor} to remove
     */
    public void removeColor(DyeColor color) {
        if (color == null || color == DyeColor.CUSTOM) {
            return;
        }
        removeColorRaw(color.getDecimalCode());
    }

    /**
     * Removes a color from the FireworkStar
     * 
     * @param rawColor
     *            the raw color to be removed
     */
    public void removeColorRaw(int rawColor) {
        if (rawColor > 0xFFFFFF || rawColor < 0) {
            return;
        }
        int[] rawColors = getExplosionTag().containsKey("Colors") ? getExplosionTag().getIntArray("Colors") : null;
        if (rawColors != null) {
            ArrayList<Integer> colors = new ArrayList<Integer>();
            for (int dec_Color : rawColors) {
                if (dec_Color == rawColor) {
                    continue;
                }
                colors.add(Integer.valueOf(dec_Color));
            }
            int[] newColors = new int[colors.size()];
            for (int index = 0; index < colors.size(); index++) {
                newColors[index] = colors.get(index);
            }
            getExplosionTag().put("Colors", newColors);
        }
    }

    /**
     * Removes all the colors from the FireworkStar
     */
    public void removeAllColors() {
        if (getExplosionTag().containsKey("Colors")) {
            getExplosionTag().remove("Colors");
        }
    }

    /**
     * Gets the {@link Item} of the FireworkStar
     * 
     * @return the {@link Item}
     */
    public Item getFireworkStarItem() {
        return fireworkStar;
    }

    /**
     * Test the item for the Explosion tag
     */
    private void testStar() {
        if (!fireworkStar.hasDataTag() || !fireworkStar.getDataTag().containsKey("Explosion")) {
            fireworkStar.getDataTag().put("Explosion", Canary.factory().getNBTFactory().newCompoundTag("Explosion"));
        }
    }

    /**
     * Gets the Explosion tag
     * 
     * @return the {@link CompoundTag} named Explosion
     */
    private CompoundTag getExplosionTag() {
        testStar();
        return fireworkStar.getDataTag().getCompoundTag("Explosion");
    }
}
