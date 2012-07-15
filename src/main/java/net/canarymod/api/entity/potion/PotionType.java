package net.canarymod.api.entity.potion;

/**
 * Enum of all possible potion types.
 * @author Brian McCarthy
 *
 */
public enum PotionType {
    MoveSpeed,
    MoveSlowdown,
    DigSpeed,
    DigSlowdown,
    DamageBoost,
    Heal,
    Harm,
    Jump,
    Confusion,
    Regeneration,
    Resistance,
    FireResistance,
    WaterBreathing,
    Invisibility,
    Blindness,
    NightVision,
    Hunger,
    Weakness,
    Poison;
    
    /**
     * Get a {@link PotionType} from a name
     * @param name
     * @return PotionTYpe or null if not valid name
     */
    public static PotionType fromName(String name) {
        for (PotionType type : values()) {
            if (type.name() == name) {
                return type;
            }
        }
        return null;
    }
}
