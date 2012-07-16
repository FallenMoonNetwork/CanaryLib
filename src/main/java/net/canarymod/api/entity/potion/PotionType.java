package net.canarymod.api.entity.potion;

/**
 * Enum of all possible potion types.
 * @author Brian McCarthy
 *
 */
public enum PotionType {
    MOVE_SPEED,
    MOVE_SLOWDOWN,
    DIG_SPEED,
    DIG_SLOWDOWN,
    DAMAGE_BOOST,
    HEAL,
    HARM,
    JUMP,
    CONFUSION,
    REGENERATION,
    RESISTANCE,
    FIRE_RESISTANCE,
    WATER_BREATHING,
    INVISIBILITY,
    BLINDNESS,
    NIGHTVISION,
    HUNGER,
    WEAKNESS,
    POISON;
    
    /**
     * Get a {@link PotionType} from a name
     * @param name
     * @return PotionTYpe or null if not valid name
     */
    public static PotionType fromName(String name) {
        if (name.indexOf(".") != -1) {
            name = name.substring(name.indexOf(".") + 1);
        }
        name = name.toUpperCase();
        
        for (PotionType type : values()) {
            if (type.name() == name) {
                return type;
            }
        }
        return null;
    }
}
