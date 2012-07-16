package net.canarymod.api.entity.potion;

/**
 * Enum of all possible potion types.
 * @author Brian McCarthy
 *
 */
public enum PotionType {
    MOVE_SPEED(1),
    MOVE_SLOWDOWN(2),
    DIG_SPEED(3),
    DIG_SLOWDOWN(4),
    DAMAGE_BOOST(5),
    HEAL(6),
    HARM(7),
    JUMP(8),
    CONFUSION(9),
    REGENERATION(10),
    RESISTANCE(11),
    FIRE_RESISTANCE(12),
    WATER_BREATHING(13),
    INVISIBILITY(14),
    BLINDNESS(15),
    NIGHTVISION(16),
    HUNGER(17),
    WEAKNESS(18),
    POISON(19);
    
    int id;
    PotionType(int id) {
        this.id = id;
    }
    
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
    
    public int getID() {
        return id;
    }
}
