package net.canarymod.api.factory;

import net.canarymod.api.entity.EntityAnimal;
import net.canarymod.api.entity.EntityAnimal.AnimalType;
import net.canarymod.api.entity.EntityMob;
import net.canarymod.api.entity.EntityMob.MobType;
import net.canarymod.api.world.World;

public interface EntityLivingFactory {
    
    EntityMob newEntityMob(String name);
    
    EntityMob newEntityMob(String name, World world);
    
    EntityMob newEntityMob(MobType type);
    
    EntityMob newEntityMob(MobType type, World world);
    
    EntityAnimal newEntityAnimal(String name);
    
    EntityAnimal newEntityAnimal(String name, World world);
    
    EntityAnimal newEntityAnimal(AnimalType type);
    
    EntityAnimal newEntityAnimal(AnimalType type, World world);
    
}
