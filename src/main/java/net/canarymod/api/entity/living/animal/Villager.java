package net.canarymod.api.entity.living.animal;


import net.canarymod.api.entity.living.EntityLiving;


/**
 * Villager interface.
 * This inherits from EntityAnimal because EntityAnimal has the getters and setters for growingAge that is required by a villager
 * @author Chris
 *
 */
public interface Villager extends EntityAnimal {
    public enum Profession {
        FARMER(0), LIBRARIAN(1), PRIEST(2), BLACKSMITH(3), BUTCHER(4), /**
         * This has no effect and is actually never used in game
         */ VILLAGER(5);
        int id;
        Profession(int id) {
            this.id = id;
        }
        
        public int getId() {
            return id;
        }
        
        public static Profession fromId(int id) {
            switch (id) {
                case 0:
                    return FARMER;

                case 1:
                    return LIBRARIAN;

                case 2:
                    return PRIEST;

                case 3:
                    return BLACKSMITH;

                case 4:
                    return BUTCHER;

                case 5:
                    return VILLAGER;

                default:
                    return VILLAGER;
            }
        }
    }
    
    /**
     * Get the profession of this villager
     * @return
     */
    public Profession getProfession();
    
    /**
     * Manually set this villagers profession
     * @param profession
     */
    public void setProfession(Profession profession);
    
    /**
     * Check if this villager is mating ...
     * @return
     */
    public boolean isMating();
    
    /**
     * Set the villager mating or not mating ...
     * @param isMating
     */
    public void setMating(boolean isMating);
    
    /**
     * Set the entity that shall be the target of this villagers revenge.
     * TODO: Does that make villagers purposely attack other entities?
     * @param targetEntity
     */
    public void setRevengeTarget(EntityLiving targetEntity);
}
