package net.canarymod.api;

public enum DamageType {
    
    /**
     * Damage cause by an arrow
     */
    ARROW,
    /**
     * Damage caused by cactus (1)
     */
    CACTUS,
    /**
     * Damage caused by an enchantment
     */
    ENCHANTMENT,
    /**
     * Damage caused by explosion
     */
    EXPLOSION,
    /**
     * Damage caused from falling (fall distance - 3.0)
     */
    FALL,
    /**
     * Damage caused by fire (1)
     */
     FIRE,
     /**
      * Damage cause by a Fireball (Assuming Ghast Fireball)
      */
     FIREBALL,
     /**
      * Low periodic damage caused by burning (1)
      */
     FIRE_TICK,
     /**
      * Typical Vanilla's /kill but could be used for other things
      */
     GENERIC,
     /**
      * Damage caused from lava (4)
      */
     LAVA,
     /**
      * Damage dealt by a Mob
      */
     MOB,
     /**
      * Damage caused by a Player
      */
     PLAYER,
     /**
      * Damage caused by poison (1) (Potions, Poison)
      */
     POTION,
     /**
      * Damage caused by starvation (1)
      */
     STARVATION,
     /**
      * Damage caused by suffocating(1)
      */
     SUFFOCATION,
     /**
      * Damage caused by a thrown item (like a snowball)
      */
     THROWN,
     /**
      * Damage caused from falling into the void
      */
     VOID,
     /**
      * Damage caused from drowning (2)
      */
     WATER;
     
     public static DamageType fromDamageSource(DamageSource source) {
         if (source.getNotchianName().equals("arrow")){
             return ARROW;
         }
         else if (source.getNotchianName().equals("cactus")){
             return CACTUS;
         }
         else if (source.getNotchianName().equals("indirectMagic")){
             return ENCHANTMENT; // Assuming thats what it means
         }
         else if (source.getNotchianName().equals("explosion")){
             return EXPLOSION; // Can also be a creeper.
         }
         else if (source.getNotchianName().equals("fall")){
             return FALL;
         }
         else if (source.getNotchianName().equals("inFire")){
             return FIRE; // Can also be lightning
         }
         else if (source.getNotchianName().equals("fireball")){
             return FIREBALL;
         }
         else if (source.getNotchianName().equals("onFire")){
             return FIRE_TICK;
         }
         else if (source.getNotchianName().equals("generic")){
             return GENERIC; // Vanilla's /kill, we don't have this, but Generic may just work for other things :3
         }
         else if (source.getNotchianName().equals("lava")){
             return LAVA;
         }
         else if (source.getNotchianName().equals("mob")){
             return MOB;
         }
         else if (source.getNotchianName().equals("magic")){
             return POTION;
         }
         else if (source.getNotchianName().equals("starve")){
             return STARVATION;
         }
         else if (source.getNotchianName().equals("inWall")){
             return SUFFOCATION;
         }
         else if (source.getNotchianName().equals("thrown")){
             return THROWN;
         }
         else if (source.getNotchianName().matches("player")){
             return PLAYER;
         }
         else if (source.getNotchianName().equals("outOfWorld")){
             return VOID;
         }
         else if (source.getNotchianName().equals("drown")){
             return WATER;
         }
         else{
             return null; // Not a valid DamageSource
         }
     }
}
