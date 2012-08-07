package net.canarymod.api.world.blocks;

import java.util.HashMap;

/**
 * Lotsa block types sorted by Id AND metadata values!
 * 
 * @author Chris
 * 
 */
public enum BlockType {
    Air(0, 0),
    Stone(1, 0),
    Grass(2, 0),
    Dirt(3, 0),
    Cobble(4, 0),

    OakWood(5, 0),
    Wood(5, 0),
    SpruceWood(5, 1),
    BirchWood(5, 2),
    JungleWood(5, 3),

    OakSapling(6, 0),
    Sapling(6, 0),
    SpruceSapling(6, 1),
    BirchSapling(6, 2),
    JungleSapling(6, 3),

    Bedrock(7, 0),

    WaterStill(8, 0),
    Water(8, 0),
    WaterFlowing(9, 0),
    LavaStill(10, 0),
    Lava(10, 0),
    LavaFlowing(11, 0),

    Sand(12, 0),
    Gravel(13, 0),

    GoldOre(14, 0),
    IronOre(15, 0),
    CoalOre(16, 0),

    OakLog(17, 0),
    Log(17, 0),
    PineLog(17, 1),
    BirchLog(17, 2),
    JungleLog(17, 3),

    OakLeave(18, 0),
    Leave(18, 0),
    PineLeave(18, 1),
    BirchLeave(18, 2),
    JungleLeave(18, 3),

    Sponge(19, 0),
    Glass(20, 0),
    LapislazuliOre(21, 0),
    LapisOre(21, 0),
    LapisBlock(22, 0),

    Dispenser(23, 0),

    Sandstone(24, 0),
    SandstoneOrnate(24, 1),
    SandstoneBlank(24, 2),

    NoteBlock(25, 0),
    BedBlock(26, 0),

    PoweredRail(27, 0),
    DetectorRail(28, 0),

    StickyPiston(29, 0),
    SpiderWeb(30, 0),

    Shrub(31, 0),
    TallGrass(31, 1),
    TallFern(31, 2),
    DeadShrub(32, 0),

    Piston(33, 0),
    PistonExtended(34, 0),

    Cloth(35, 0),
    ClothWhite(35, 0),
    ClothOrange(35, 1),
    ClothLightPurple(35, 2),
    ClothLightBlue(35, 3),
    ClothYellow(35, 4),
    ClothLightGreen(35, 5),
    ClothPink(35, 6),
    ClothGray(35, 7),
    ClothGrey(35, 7),
    ClothLightGray(35, 8),
    ClothLightGrey(35, 8),
    ClothCyan(35, 9),
    ClothPurple(35, 10),
    ClothBlue(35, 11),
    ClothBrown(35, 12),
    ClothDarkGreen(35, 13),
    ClothRed(35, 14),
    ClothBlack(35, 15),

    PistonBlockFiller(36, 0),

    YellowFlower(37, 0),
    RedFlower(38, 0),
    BrownMushroom(39, 0),
    RedMushroom(40, 0),

    GoldBlock(41, 0),
    IronBlock(42, 0),

    DoublestepStone(43, 0),
    DoublestepSandStone(43, 1),
    DoublestepWood(43, 2), //Being moved to Block ID: 125 in 1.2.6/1.3
    DoublestepCobble(43, 3),
    DoublestepBrickBlock(43, 4),
    DoublestepStoneBricks(43, 5),
    DoublestepStone2(43, 6),
    DoublestepSandStone2(43, 9),
    DoublestepWood2(43, 10),
    DoublestepCobble2(43, 11),
    DoublestepBrickBlock2(43, 12),
    DoublestepStoneBricks2(43, 13),

    StepStone(44, 0),
    StepSandStone(44, 1),
    StepWood(44, 2), //Being moved to Block ID: 126 in 1.2.6/1.3
    StepCobble(44, 3),
    StepBrickBlock(44, 4),
    StepStoneBricks(44, 5),
    StepStone2(44, 6),
    StepSandStone2(44, 9),
    StepWood2(44, 10),
    StepCobble2(44, 11),
    StepBrickBlock2(44, 12),
    StepStoneBricks2(44, 13),

    BrickBlock(45, 0),
    Tnt(46, 0),
    Bookshelf(47, 0),
    MossyCobble(48, 0),
    Obsidian(49, 0),
    Torch(50, 0),
    FireBlock(51, 0),
    MobSpawner(52, 0),
    WoodenStair(53, 0),
    Chest(54, 0),
    RedstoneWire(55, 0),
    DiamondOre(56, 0),
    DiamondBlock(57, 0),
    Workbench(58, 0),
    Crops(59, 0),
    Soil(60, 0),
    Furnace(61, 0),
    BurningFurnace(62, 0),
    SignPost(63, 0),
    WoodenDoor(64, 0),
    Ladder(65, 0),
    Rail(66, 0),
    CobbleStair(67, 0),
    WallSign(68, 0),

    Lever(69, 0),
    StonePlate(70, 0),
    IronDoor(71, 0),
    WoodPlate(72, 0),
    RedstoneOre(73, 0),
    GlowingRedstoneOre(74, 0),
    RedstoneTorchOff(75, 0),
    RedstoneTorchOn(76, 0),
    StoneButton(77, 0),

    Snow(78, 0),
    Ice(79, 0),
    SnowBlock(80, 0),

    Cactus(81, 0),
    Clay(82, 0),
    Reed(83, 0),

    Jokebox(84, 0),

    Fence(85, 0),

    Pumpkin(86, 0),
    Netherrack(87, 0),
    SoulSand(88, 0),
    GlowStone(89, 0),
    Portal(90, 0),
    JackOLantern(91, 0),
    Cake(92, 0),
    RedstoneRepeaterOff(93, 0),
    RedstoneRepeaterOn(94, 0),
    LockedChest(95, 0),
    Trapdoor(96, 0),

    CleanSilverBlock(97, 0),
    CrackedSilverBlock(97, 1),
    SilverBlockStoneBrick(97, 2),
    StoneBrick(98, 0),
    CleanStoneBrick(98, 0),
    MossyStoneBrick(98, 1),
    CrackedStoneBrick(98, 2),
    OrnateStoneBrick(98, 3),

    HugeBrownMushroom(99, 0),
    HugeRedMushroom(100, 0),

    IronBars(101, 0),

    GlassPane(102, 0),

    Melon(103, 0),
    PumpkinStem(104, 0),
    MelonStem(105, 0),
    Vine(106, 0),
    FenceGate(107, 0),
    BrickStair(108, 0),
    StoneBrickStair(109, 0),
    Mycelium(110, 0),
    Lilypad(111, 0),
    NetherBrick(112, 0),
    NetherBrickFence(113, 0),
    NetherBrickStair(114, 0),
    NetherWart(115, 0),
    EnchantmentTable(116, 0),
    BrewingStand(117, 0),
    Cauldron(118, 0),
    EndPortal(119, 0),
    EndPortalFrame(120, 0),
    EndStone(121, 0),
    EnderDragonEgg(122, 0),
    RedstoneLampOff(123, 0),
    RedstoneLampOn(124, 0),
    OakWoodDoubleSlab(125, 0),
    SpruceWoodDoubleSlab(125, 1),
    BirchWoodDoubleSlab(125, 2),
    JungleWoodDoubleSlab(125, 3),
    OakWoodSlab(126, 0),
    SpruceWoodSlab(126, 1),
    BirchWoodSlab(126, 2),
    JungleWoodSlab(126, 3),
    CocoaPlant(127, 0),
    SandstoneStair(128, 0),
    EmeraldOre(129, 0),
    EnderChest(130, 0),
    TripwireHook(131, 0),
    Tripwire(132, 0),
    EmeraldBlock(133, 0),
    PineWoodStair(134, 0),
    BirchWoodStair(135, 0),
    JungleWoodStair(136, 0);

    private short id;
    private byte meta;
    private static HashMap<String, BlockType> map = new HashMap<String, BlockType>();

    private void add(String name) {
        map.put(name, this);
    }

    BlockType(int id, int meta) {
        this.id = (short) id;
        this.meta = (byte) meta;
        add(this.name());

    }

    public short getId() {
        return id;
    }

    public byte getMetaData() {
        return meta;
    }

    /**
     * Returns the item type that equals the given String. Null if there are no
     * matches
     * 
     * @param type
     * @return ItemType | NULL
     */
    public static BlockType fromString(String type) {
        return map.get(type);
    }

    /**
     * Returns the first found element that has the given id or null if id
     * wasn't found
     * 
     * @param id
     * @return ItemType | NULL
     */
    public static BlockType fromId(int id) {
        for(BlockType t : BlockType.values()) {
            if(t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    /**
     * Get ItemType from id and data
     * 
     * @param id
     * @param data
     * @return ItemType | NULL
     */
    public static BlockType fromIdAndMeta(int id, int data) {
        for(BlockType t : BlockType.values()) {
            if(t.getId() == id && t.getMetaData() == data) {
                return t;
            }
        }
        return null;
    }

    /**
     * Get a human readable name for this BlockType. For instance,
     * HugeBrownMushroom will return "Huge Brown Mushroom"
     * 
     * @return
     */
    public String getHumanReadableName() {
        return BlockType.getHumanReadableName(this);
    }

    /**
     * Get a human readable name for the given BlockType. For instance,
     * HugeBrownMushroom will return "Huge Brown Mushroom"
     * 
     * @param type
     * @return
     */
    public static String getHumanReadableName(BlockType type) {
        String[] words = type.name().split("(?=\\p{Upper})");
        StringBuilder sb = new StringBuilder();
        for (String p : words) {
            sb.append(p).append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
