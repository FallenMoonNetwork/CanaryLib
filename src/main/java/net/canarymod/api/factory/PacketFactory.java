package net.canarymod.api.factory;

import net.canarymod.api.DataWatcher;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.XPOrb;
import net.canarymod.api.entity.hanging.Painting;
import net.canarymod.api.entity.living.LivingBase;
import net.canarymod.api.entity.living.humanoid.Human;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.packet.Packet;
import net.canarymod.api.packet.InvalidPacketConstructionException;
import net.canarymod.api.potion.PotionEffect;

/**
 * Packet manufacturing Factory
 * <p/>
 * See <a href="http://wiki.vg/Protocol">wiki.vg/Protocol</a> for information about Packets
 * <p/>
 * Some implemented packets may not function as intended or at all.
 *
 * @author Jason (darkdiplomat)
 */
public interface PacketFactory {

    /**
     * Creates Packets to be sent later
     * <p/>
     * NOTE: Not all packets can be constructed<br/>
     * All non-constructable packets will throw an {@link InvalidPacketConstructionException} or similar exception
     *
     * @param id
     *         the Packet ID
     * @param args
     *         the arguments for Packet Construction.
     *
     * @return the new Packet
     *
     * @throws Exception
     *         any of {@link InvalidPacketConstructionException}, {@link ClassCastException}, {@link IllegalArgumentException}, or any other possible exception that can occur
     * @see http://wiki.vg/Protocol
     */
    Packet createPacket(int id, Object... args) throws Exception;

    /**
     * Creates a Update Time {@link Packet}
     * <p/>
     * Time is based on ticks, where 20 ticks happen every second. There are 24000 ticks in a day, making Minecraft days exactly 20 minutes long.<br/>
     * The time of day is based on the timestamp modulo 24000. 0 is sunrise, 6000 is noon, 12000 is sunset, and 18000 is midnight.<br/>
     * The default SMP server increments the time by 20 every second.
     *
     * @param worldAge
     *         the age of the world
     * @param time
     *         the time of the world
     *
     * @return a new Update Time packet
     */
    Packet updateTime(long worldAge, long time);

    /**
     * Creates a Player Equipment {@link Packet}
     *
     * @param playerID
     *         the Player ID
     * @param slot
     *         Equipment slot: 0=held, 1-4=armor slot (1 - boots, 2 - leggings, 3 - chestplate, 4 - helmet)
     * @param item
     *         the {@link Item} in the slot
     *
     * @return new Player Equipment Packet
     */
    Packet playerEquipment(int playerID, int slot, Item item);

    /**
     * Creates a Spawn Position {@link Packet}
     * <p/>
     * Sent by the server after login to specify the coordinates of the spawn point (the point at which players spawn at, and which the compass points to).<br/>
     * It can be sent at any time to update the point compasses point at.
     *
     * @param x
     *         the X coordinate
     * @param y
     *         the Y coordinate
     * @param z
     *         the Z coordinate
     *
     * @return new Spawn Position {@link Packet}
     */
    Packet spawnPosition(int x, int y, int z);

    /**
     * Creates a Update Health {@link Packet}
     * <p/>
     * Sent by the server to update/set the health of the player it is sent to.<br/>
     * Food saturation acts as a food "overcharge". Food values will not decrease while the saturation is over zero.<br/>
     * Players logging in automatically get a saturation of 5.0.<br/>
     * Eating food increases the saturation as well as the food bar.<br/>
     *
     * @param health
     *         the player's health
     * @param foodLevel
     *         the player's food level
     * @param saturation
     *         the player's food saturation
     *
     * @return new Update Health {@link Packet}
     */
    Packet updateHealth(float health, int foodLevel, float saturation);

    /**
     * Creates a Player Position and Look {@link Packet}
     *
     * @param x
     *         the X coordinate
     * @param stance
     *         the Player stance (Typically Y + 1.62D for standing)
     * @param y
     *         the Y coordinate
     * @param z
     *         the Z coordinate
     * @param yaw
     *         the Yaw rotation
     * @param pitch
     *         the Pitch rotation
     * @param onGround
     *         {@code true} for on ground; {@code false} for not
     *
     * @return new PlayerLookMove {@link Packet}
     */
    Packet playerPositionLook(double x, double stance, double y, double z, float yaw, float pitch, boolean onGround);

    /**
     * Creates a HeldItemChange {@link Packet}
     * <p/>
     * Sent when the player changes the slot selection
     *
     * @param slot
     *         The slot which the player has selected (0-8)
     *
     * @return new HeldItemChange {@link Packet}
     */
    Packet heldItemChange(int slot);

    /**
     * Creates a UseBed {@link Packet}
     * <p/>
     * This packet tells that a player goes to bed.<br/>
     * The client with the matching Entity ID will go into bed mode.<br/>
     * This Packet is sent to all nearby players including the one sent to bed.
     *
     * @param player
     *         the {@link Player} going to bed
     * @param x
     *         Bed headboard X as block coordinate
     * @param y
     *         Bed headboard Y as block coordinate
     * @param z
     *         Bed headboard Z as block coordinate
     *
     * @return new UseBed {@link Packet}
     */
    Packet useBed(Player player, int x, int y, int z);

    /**
     * Creates a Animation {@link Packet}
     * <p/>
     * Sent whenever an player should change animation.<br/>
     *
     * @param player
     *         the {@link Player}
     * @param animation
     *         the animation id 0: No animation 1: Swing arm 2: Damage animation 3: Leave bed 5: Eat food 6: Critical effect 7: Magic critical effect
     *
     * @return new Animation {@link Packet}
     */
    Packet animation(Player player, int animation);

    /**
     * Creates a NamedEntity {@link Packet}
     *
     * @param human
     *         the {@link Human} to spawn (either a NPC or Player)
     *
     * @return new SpawnNamedEntity {@link Packet}
     */
    Packet spawnNamedEntity(Human human);


    /**
     * Creates a CollectItem {@link Packet}
     * <p/>
     * Sent by the server when someone picks up an item lying on the ground - its sole purpose appears to be the animation of the item flying towards you.<br/>
     * It doesn't destroy the entity in the client memory (0x1D does that), and it doesn't add it to your inventory (0x67 does that).<br/>
     * The server only checks for items to be picked up after each Player Position and Player Position & Look packet sent by the client.
     *
     * @param entityItemID
     *         the ID of the EntityItem
     * @param collectorID
     *         the ID of the Player
     *
     * @return new CollectItem packet
     */
    Packet collectItem(int entityItemID, int collectorID);

    /**
     * Creates a SpawnObjectVehicle {@link Packet}
     *
     * @param entity
     *         the entity being spawned
     * @param objectID
     *         See <a href="http://wiki.vg/Entities#Objects">wiki.vg/Entities#Objects</a>
     * @param throwerID
     *         the EntityID of the Thrower (if applicable); Otherwise see <a href="http://wiki.vg/Object_Data">wiki.vg/Object_Data</a>
     *
     * @return new SpawnObjectVehicle {@link Packet}
     */
    Packet spawnObjectVehicle(Entity entity, int objectID, int throwerID);

    /**
     * Creates a SpawnMob {@link Packet}
     *
     * @param livingbase
     *         the {@link LivingBase} to spawn
     *
     * @return new SpawnMob {@link Packet}
     */
    Packet spawnMob(LivingBase livingbase);

    /**
     * Creates a SpawnPainting {@link Packet}
     *
     * @param painting
     *         the {@link Painting} to spawn
     *
     * @return new SpawnPainting {@link Packet}
     */
    Packet spawnPainting(Painting painting);

    /**
     * Creates a SpawnXPOrb {@link Packet}
     *
     * @param xporb
     *         the {@link XPOrb} to spawn
     *
     * @return new SpawnXPOrb {@link Packet}
     */
    Packet spawnXPOrb(XPOrb xporb);

    /**
     * Creates a EntityVelocity {@link Packet}
     * <p/>
     * Velocity is believed to be in units of 1/8000 of a block per server tick (50ms); for example, -1343 would move (-1343 / 8000) = −0.167875 blocks per tick (or −3,3575 blocks per second).
     *
     * @param entityID
     *         the entity ID
     * @param motX
     *         the X-wise motion
     * @param motY
     *         the Y-wise motion
     * @param motZ
     *         the Z-wise motion
     *
     * @return new EntityVelocity {@link Packet}
     */
    Packet entityVelocity(int entityID, double motX, double motY, double motZ);

    /**
     * Creates a EntityDestroy {@link Packet}
     * <p/>
     * Sent by the server when an list of Entities is to be destroyed on the client.
     *
     * @param ids
     *         the IDs of the Entities to destroy
     *
     * @return new EntityDestroy {@link Packet}
     */
    Packet destroyEntity(int... ids);

    /**
     * Creates a EntityRelativeMove {@link Packet}
     * <p/>
     * This packet is sent by the server when an entity moves less then 4 blocks; if an entity moves more than 4 blocks Entity Teleport should be sent instead.<br/>
     * This packet allows at most four blocks movement in any direction, because byte range is from -128 to 127.<br/>
     * Movement is an offset of Absolute Int; to convert relative move to block coordinate offset, divide by 32.
     *
     * @param entityID
     *         the entity ID
     * @param x
     *         the X block coordinate offset
     * @param y
     *         the Y block coordinate offset
     * @param z
     *         the Z block coordinate offset
     *
     * @return new EntityRelativeMove {@link Packet}
     */
    Packet entityRelativeMove(int entityID, byte x, byte y, byte z);

    /**
     * Creates a EntityLook {@link Packet}
     * <p/>
     * This packet is sent by the server when an entity rotates. Example: "Yaw" field 64 means a 90 degree turn.
     *
     * @param entityID
     *         the entity ID
     * @param yaw
     *         the yaw offset
     * @param pitch
     *         the pitch offset
     *
     * @return new EntityLook {@link Packet}
     */
    Packet entityLook(int entityID, byte yaw, byte pitch);

    /**
     * Creates a EntityLookRelativeMove {@link Packet}
     * <p/>
     * Since a byte range is limited from -128 to 127, and movement is offset of Absolute Int, this packet allows at most four blocks movement in any direction. (-128/32 == -4)
     *
     * @param entityID
     *         the entityID
     * @param x
     *         the X block coordinate offset
     * @param y
     *         the Y block coordinate offset
     * @param z
     *         the Z block coordinate offset
     * @param yaw
     *         the yaw offset
     * @param pitch
     *         the pitch offset
     *
     * @return new EntityLookRelativeMove {@link Packet}
     */
    Packet entityLookRelativeMove(int entityID, byte x, byte y, byte z, byte yaw, byte pitch);

    /**
     * Creates a EntityTeleport {@link Packet}
     *
     * @param entityID
     *         the EntityID
     * @param x
     *         the X coordinate
     * @param y
     *         the Y coordinate
     * @param z
     *         the Z coordinate
     * @param yaw
     *         the yaw offset
     * @param pitch
     *         the pitch offset
     *
     * @return new EntityTeleport {@link Packet}
     */
    Packet entityTeleport(int entityID, int x, int y, int z, byte yaw, byte pitch);

    /**
     * Creates a EntityHeadLook {@link Packet}
     *
     * @param entityID
     *         the Entity ID
     * @param yaw
     *         head yaw in steps of 2p/256
     *
     * @return new EntityHeadLook {@link Packet}
     */
    Packet entityHeadLook(int entityID, byte yaw);

    /**
     * Creates a new EntityStatus {@link Packet}
     *
     * @param entityID
     *         the entity ID
     * @param status
     *         2: Entity hurt <br/>
     *         3: Entity dead <br/>
     *         6: Wolf taming <br/>
     *         7: Wolf tamed <br/>
     *         8: Wolf shaking water off itself <br/>
     *         9: (of self) Eating accepted by server <br/>
     *         10: Sheep eating grass <br/>
     *         11: Iron Golem handing over a rose <br/>
     *         12: Spawn "heart" particles near a villager <br/>
     *         13: Spawn particles indicating that a villager is angry and seeking revenge <br/>
     *         14: Spawn happy particles near a villager <br/>
     *         15: Spawn a "magic" particle near the Witch <br/>
     *         16: Zombie converting into a villager by shaking violently <br/>
     *         17: A firework exploding <br/>
     *
     * @return new EntityStatus {@link Packet}
     */
    Packet entityStatus(int entityID, byte status);

    /**
     * Creates a AttachEntity {@link Packet}
     *
     * @param leashId
     *         the Leash ID
     * @param attaching
     *         the entity being attached
     * @param vehicle
     *         the entity being attached to
     *
     * @return new AttachEntity {@link Packet}
     */
    Packet attachEntity(int leashId, Entity attaching, Entity vehicle);

    /**
     * Creates a EntityMetaData {@link Packet}
     *
     * @param entityID
     *         the entity ID
     * @param watcher
     *         the {@link DataWatcher} data
     *
     * @return new EntityMetaData {@link Packet}
     */
    Packet entityMetaData(int entityID, DataWatcher watcher);

    /**
     * Creates a EntityEffect {@link Packet}
     *
     * @param entityID
     *         the Entity ID
     * @param effect
     *         the {@link PotionEffect}
     *
     * @return new EntityEffect {@link Packet}
     */
    Packet entityEffect(int entityID, PotionEffect effect);

    /**
     * Creates a RemoveEntityEffect {@link Packet}
     *
     * @param entityID
     *         the Entity ID
     * @param effect
     *         the {@link PotionEffect}
     *
     * @return new RemoveEntityEffect {@link Packet}
     */
    Packet removeEntityEffect(int entityID, PotionEffect effect);

    /**
     * Creates a SetExperience {@link Packet}
     *
     * @param bar
     *         Used for drawing the experience bar - value is between 0 and 1.
     * @param level
     *         the Experience Level
     * @param totalXp
     *         the Total Experience
     *
     * @return new SetExperience {@link Packet}
     */
    Packet setExperience(float bar, int level, int totalXp);

}
