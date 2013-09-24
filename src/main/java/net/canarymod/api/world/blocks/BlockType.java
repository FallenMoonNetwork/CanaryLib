package net.canarymod.api.world.blocks;

import java.util.HashMap;

/**
 * Static class of BlockTypes
 *
 * @author Chris (damagefilter)
 */
public final class BlockType {
    public static final BlockType Air = new BlockType(0, 0, "Air");
    public static final BlockType Stone = new BlockType(1, 0, "Stone");
    public static final BlockType Grass = new BlockType(2, 0, "Grass");
    public static final BlockType Dirt = new BlockType(3, 0, "Dirt");
    public static final BlockType Cobble = new BlockType(4, 0, "Cobble");
    public static final BlockType OakWood = new BlockType(5, 0, "Oak Wood");
    public static final BlockType SpruceWood = new BlockType(5, 1, "Spruce Wood");
    public static final BlockType BirchWood = new BlockType(5, 2, "Birch Wood");
    public static final BlockType JungleWood = new BlockType(5, 3, "Jungle Wood");
    public static final BlockType OakSapling = new BlockType(6, 0, "Oak Sapling");
    public static final BlockType SpruceSapling = new BlockType(6, 1, "Spruce Sapling");
    public static final BlockType BirchSapling = new BlockType(6, 2, "Birch Sapling");
    public static final BlockType JungleSapling = new BlockType(6, 3, "Jungle Sapling");
    public static final BlockType Bedrock = new BlockType(7, 0, "Bedrock");
    public static final BlockType Water = new BlockType(8, 0, "Water");
    public static final BlockType WaterFlowing = new BlockType(9, 0, "Water Flowing");
    public static final BlockType Lava = new BlockType(10, 0, "Lava");
    public static final BlockType LavaFlowing = new BlockType(11, 0, "Lava Flowing");
    public static final BlockType Sand = new BlockType(12, 0, "Sand");
    public static final BlockType Gravel = new BlockType(13, 0, "Gravel");
    public static final BlockType GoldOre = new BlockType(14, 0, "Gold Ore");
    public static final BlockType IronOre = new BlockType(15, 0, "Iron Ore");
    public static final BlockType CoalOre = new BlockType(16, 0, "Coal Ore");
    public static final BlockType OakLog = new BlockType(17, 0, "Oak Log");
    public static final BlockType PineLog = new BlockType(17, 1, "Pine Log");
    public static final BlockType BirchLog = new BlockType(17, 2, "Birch Log");
    public static final BlockType JungleLog = new BlockType(17, 3, "Jungle Log");
    public static final BlockType OakLeaves = new BlockType(18, 0, "Oak Leaves");
    public static final BlockType PineLeaves = new BlockType(18, 1, "Pine Needles");
    public static final BlockType BirchLeaves = new BlockType(18, 2, "Birch Leaves");
    public static final BlockType JungleLeaves = new BlockType(18, 3, "Jungle Leaves");
    public static final BlockType Sponge = new BlockType(19, 0, "Sponge"); // THE SPONGE IS A LIE!
    public static final BlockType Glass = new BlockType(20, 0, "Glass");
    public static final BlockType LapislazuliOre = new BlockType(21, 0, "Lapislazuli Pre");
    public static final BlockType LapisBlock = new BlockType(22, 0, "Lapislazuli Block");
    public static final BlockType Dispenser = new BlockType(23, 0, "Dispenser");
    public static final BlockType Sandstone = new BlockType(24, 0, "Sandstone");
    public static final BlockType SandstoneOrnate = new BlockType(24, 1, "Ornate Sandstone");
    public static final BlockType SandstoneBlank = new BlockType(24, 2, "Blank Sandstone");
    public static final BlockType NoteBlock = new BlockType(25, 0, "Note Block");
    public static final BlockType BedBlock = new BlockType(26, 0, "Bed Block");
    public static final BlockType PoweredRail = new BlockType(27, 0, "Powered Rail");
    public static final BlockType DetectorRail = new BlockType(28, 0, "Detector Rail");
    public static final BlockType StickyPiston = new BlockType(29, 0, "Sticky Piston");
    public static final BlockType SpiderWeb = new BlockType(30, 0, "Spider Web");
    public static final BlockType Shrub = new BlockType(31, 0, "Shrub");
    public static final BlockType TallGrass = new BlockType(31, 1, "Tall Grass");
    public static final BlockType TallFern = new BlockType(31, 2, "Tall Fern");
    // 32 MIA
    public static final BlockType Piston = new BlockType(33, 0, "Piston");
    public static final BlockType PistonExtended = new BlockType(34, 0, "Extended Piston");
    public static final BlockType WoolWhite = new BlockType(35, 0, "White Wool");
    public static final BlockType WoolOrange = new BlockType(35, 1, "Orange Wool");
    public static final BlockType WoolMagenta = new BlockType(35, 2, "Magenta Wool");
    public static final BlockType WoolLightBlue = new BlockType(35, 3, "Light Blue Wool");
    public static final BlockType WoolYellow = new BlockType(35, 4, "Yellow Wool");
    public static final BlockType WoolLightGreen = new BlockType(35, 5, "Light Green Wool");
    public static final BlockType WoolPink = new BlockType(35, 6, "Pink Wool");
    public static final BlockType WoolGray = new BlockType(35, 7, "Gray Wool");
    public static final BlockType WoolLightGray = new BlockType(35, 8, "Light Gray Wool");
    public static final BlockType WoolCyan = new BlockType(35, 9, "Cyan Wool");
    public static final BlockType WoolPurple = new BlockType(35, 10, "Purple Wool");
    public static final BlockType WoolBlue = new BlockType(35, 11, "Blue Wool");
    public static final BlockType WoolBrown = new BlockType(35, 12, "Brown Wool");
    public static final BlockType WoolDarkGreen = new BlockType(35, 13, "Dark Green Wool");
    public static final BlockType WoolRed = new BlockType(35, 14, "Red Wool");
    public static final BlockType WoolBlack = new BlockType(35, 15, "Black Wool");
    public static final BlockType PistonBlockFiller = new BlockType(36, 0, "Piston Block Filler");
    public static final BlockType YellowFlower = new BlockType(37, 0, "Yellow Flower");
    public static final BlockType RedFlower = new BlockType(38, 0, "Red Flower");
    public static final BlockType BrownMushroom = new BlockType(39, 0, "Brown Mushroom");
    public static final BlockType RedMushroom = new BlockType(40, 0, "Red Mushroom");
    public static final BlockType GoldBlock = new BlockType(41, 0, "Gold Block");
    public static final BlockType IronBlock = new BlockType(42, 0, "Iron Block");
    public static final BlockType DoublestepOrnateStone = new BlockType(43, 0, "Doublesetp Ornate Stone");
    public static final BlockType DoublestepSandStoneTrim = new BlockType(43, 1, "Doublestep Sandstone Trim");
    public static final BlockType DoublestepWood = new BlockType(43, 2, "Doublestep Wood");
    public static final BlockType DoublestepCobble = new BlockType(43, 3, "Doublestep Cobble");
    public static final BlockType DoublestepBrickBlock = new BlockType(43, 4, "Doublestep Bricks");
    public static final BlockType DoublestepStoneBricks = new BlockType(43, 5, "Doublestep Stone Bricks");
    public static final BlockType DoublestepNetherBrick = new BlockType(43, 6, "Doublestep Nether Bricks");
    public static final BlockType DoublestepQuartz = new BlockType(43, 9, "Doublestep Quartz");
    public static final BlockType DoublestepStone = new BlockType(43, 8, "Doublestep Stone");
    public static final BlockType DoublestepSandStone = new BlockType(43, 11, "Doublestep Sandstone");
    public static final BlockType StepOrnateStone = new BlockType(44, 0, "Ornate Stone Step");
    public static final BlockType StepSandStoneTrim = new BlockType(44, 1, "Sandstone Trim Step");
    public static final BlockType StepWood = new BlockType(44, 2, "Wood Step");
    public static final BlockType StepCobble = new BlockType(44, 3, "Cobble Step");
    public static final BlockType StepBrickBlock = new BlockType(44, 4, "Bricks Step");
    public static final BlockType StepStoneBricks = new BlockType(44, 5, "Stone Bricks Step");
    public static final BlockType StepNetherBricks = new BlockType(44, 6, "Nether Bricks Step");
    public static final BlockType StepQuartz = new BlockType(44, 7, "Quartz Step");
    public static final BlockType StepStone = new BlockType(44, 10, "Stone Step");
    public static final BlockType StepSandStone = new BlockType(44, 11, "Sandstone Step");
    public static final BlockType BrickBlock = new BlockType(45, 0, "Brick Block");
    public static final BlockType Tnt = new BlockType(46, 0, "TNT");
    public static final BlockType Bookshelf = new BlockType(47, 0, "Bookshelf");
    public static final BlockType MossyCobble = new BlockType(48, 0, "Mossy Cobblestone");
    public static final BlockType Obsidian = new BlockType(49, 0, "Obsidian");
    public static final BlockType Torch = new BlockType(50, 0, "Torch");
    public static final BlockType FireBlock = new BlockType(51, 0, "Fire");
    public static final BlockType MobSpawner = new BlockType(52, 0, "Mob Spawner");
    public static final BlockType WoodenStair = new BlockType(53, 0, "Wooden Stair");
    public static final BlockType Chest = new BlockType(54, 0, "Chest");
    public static final BlockType RedstoneWire = new BlockType(55, 0, "Redstone Wire");
    public static final BlockType DiamondOre = new BlockType(56, 0, "Diamond Ore");
    public static final BlockType DiamondBlock = new BlockType(57, 0, "Diamond Block");
    public static final BlockType Workbench = new BlockType(58, 0, "Crafting Table");
    public static final BlockType Crops = new BlockType(59, 0, "Wheat");
    public static final BlockType Soil = new BlockType(60, 0, "Farmland");
    public static final BlockType Furnace = new BlockType(61, 0, "Furnace");
    public static final BlockType BurningFurnace = new BlockType(62, 0, "Burning Furnace");
    public static final BlockType SignPost = new BlockType(63, 0, "Sign Post");
    public static final BlockType WoodenDoor = new BlockType(64, 0, "Wooden Door");
    public static final BlockType Ladder = new BlockType(65, 0, "Ladder");
    public static final BlockType Rail = new BlockType(66, 0, "Rail");
    public static final BlockType CobbleStair = new BlockType(67, 0, "Cobble Stairs");
    public static final BlockType WallSign = new BlockType(68, 0, "Wall Sign");
    public static final BlockType Lever = new BlockType(69, 0, "Lever");
    public static final BlockType StonePlate = new BlockType(70, 0, "Stone Pressure Plate");
    public static final BlockType IronDoor = new BlockType(71, 0, "Iron Door");
    public static final BlockType WoodPlate = new BlockType(72, 0, "Wooden Pressure Plate");
    public static final BlockType RedstoneOre = new BlockType(73, 0, "Redstone Ore");
    public static final BlockType GlowingRedstoneOre = new BlockType(74, 0, "Glowing Redstone Ore");
    public static final BlockType RedstoneTorchOff = new BlockType(75, 0, "Redstone Torch off");
    public static final BlockType RedstoneTorchOn = new BlockType(76, 0, "Redstone Torch on");
    public static final BlockType StoneButton = new BlockType(77, 0, "Stone Button");
    public static final BlockType Snow = new BlockType(78, 0, "Snow");
    public static final BlockType Ice = new BlockType(79, 0, "Ice");
    public static final BlockType SnowBlock = new BlockType(80, 0, "Snow Block");
    public static final BlockType Cactus = new BlockType(81, 0, "Cactus");
    public static final BlockType Clay = new BlockType(82, 0, "Clay");
    public static final BlockType Reed = new BlockType(83, 0, "Sugar Cane");
    public static final BlockType Jokebox = new BlockType(84, 0, "Jukebox");
    public static final BlockType Fence = new BlockType(85, 0, "Fence");
    public static final BlockType Pumpkin = new BlockType(86, 0, "Pumpkin");
    public static final BlockType Netherrack = new BlockType(87, 0, "Netherrack");
    public static final BlockType SoulSand = new BlockType(88, 0, "Soul Sand");
    public static final BlockType GlowStone = new BlockType(89, 0, "Glowstone");
    public static final BlockType Portal = new BlockType(90, 0, "Nether Portal");
    public static final BlockType JackOLantern = new BlockType(91, 0, "Jack 'o' Lantern");
    public static final BlockType Cake = new BlockType(92, 0, "Cake");
    public static final BlockType RedstoneRepeaterOff = new BlockType(93, 0, "Redstone Repeater off");
    public static final BlockType RedstoneRepeaterOn = new BlockType(94, 0, "Redstone Repeater on");
    public static final BlockType LockedChest = new BlockType(95, 0, "Locked Chest");
    public static final BlockType Trapdoor = new BlockType(96, 0, "Trapdoor");
    public static final BlockType CleanSilverFishBlock = new BlockType(97, 0, "Silverfish Spawnblock");
    public static final BlockType MossySilverFishBlock = new BlockType(97, 1, "Mossy Silverfish Spawnblock");
    public static final BlockType CrackdSilverFishBlock = new BlockType(97, 2, "Cracked Silverfish Spawnblock");
    public static final BlockType OrnateSilverFishBlock = new BlockType(97, 3, "Ornate Silverfish Spawnblock");
    public static final BlockType StoneBrick = new BlockType(98, 0, "Stone Bricks");
    public static final BlockType MossyStoneBrick = new BlockType(98, 1, "Mossy Stone Bricks");
    public static final BlockType CrackedStoneBrick = new BlockType(98, 2, "Cracked Stone Bricks");
    public static final BlockType OrnateStoneBrick = new BlockType(98, 3, "Ornate Stone Bricks");
    public static final BlockType HugeBrownMushroom = new BlockType(99, 0, "Huge Brown Mushroom");
    public static final BlockType HugeRedMushroom = new BlockType(100, 0, "Huge Red Mushroom");
    public static final BlockType IronBars = new BlockType(101, 0, "Iron Bars");
    public static final BlockType GlassPane = new BlockType(102, 0, "Glass Pane");
    public static final BlockType Melon = new BlockType(103, 0, "Melon");
    public static final BlockType PumpkinStem = new BlockType(104, 0, "Pumpkin Stem");
    public static final BlockType MelonStem = new BlockType(105, 0, "Melon Stem");
    public static final BlockType Vines = new BlockType(106, 0, "Vines");
    public static final BlockType FenceGate = new BlockType(107, 0, "Fence Gate");
    public static final BlockType BrickStair = new BlockType(108, 0, "Brick Stairs");
    public static final BlockType StoneBrickStair = new BlockType(109, 0, "Stone Brick Stairs");
    public static final BlockType Mycelium = new BlockType(110, 0, "Mycelium");
    public static final BlockType Lilypad = new BlockType(111, 0, "Lilypad");
    public static final BlockType NetherBrick = new BlockType(112, 0, "Nether Brick");
    public static final BlockType NetherBrickFence = new BlockType(113, 0, "Nether Brick Fence");
    public static final BlockType NetherBrickStair = new BlockType(114, 0, "Nether Brick Stairs");
    public static final BlockType NetherWart = new BlockType(115, 0, "Nether Wart");
    public static final BlockType EnchantmentTable = new BlockType(116, 0, "Enchantment Table");
    public static final BlockType BrewingStand = new BlockType(117, 0, "Brewing Stand");
    public static final BlockType Cauldron = new BlockType(118, 0, "Cauldron");
    public static final BlockType EndPortal = new BlockType(119, 0, "End Portal");
    public static final BlockType EndPortalFrame = new BlockType(120, 0, "End Portal Frame");
    public static final BlockType EndStone = new BlockType(121, 0, "End Stone");
    public static final BlockType EnderDragonEgg = new BlockType(122, 0, "Enderdragon Egg");
    public static final BlockType RedstoneLampOff = new BlockType(123, 0, "Redstone Lamp off");
    public static final BlockType RedstoneLampOn = new BlockType(124, 0, "Redstone Lamp on");
    public static final BlockType OakWoodDoubleStep = new BlockType(125, 0, "Oak Wood Doublestep");
    public static final BlockType SpruceWoodDoubleStep = new BlockType(125, 1, "Spruce Wood Doublestep");
    public static final BlockType BirchWoodDoubleStep = new BlockType(125, 2, "Birch Wood Doublestep");
    public static final BlockType JungleWoodDoubleStep = new BlockType(125, 3, "Jungle Wood Doublestep");
    public static final BlockType OakWoodStep = new BlockType(126, 0, "Oak Woodstep");
    public static final BlockType SpruceWoodStep = new BlockType(126, 1, "Spruce Woodstep");
    public static final BlockType BirchWoodStep = new BlockType(126, 2, "Birch Woodstep");
    public static final BlockType JungleWoodStep = new BlockType(126, 3, "Jungle Woodstep");
    public static final BlockType CocoaPlant = new BlockType(127, 0, "Cocoa Plant");
    public static final BlockType SandstoneStair = new BlockType(128, 0, "Sandstone Stairs");
    public static final BlockType EmeraldOre = new BlockType(129, 0, "Emerald Ore");
    public static final BlockType EnderChest = new BlockType(130, 0, "Ender Chest");
    public static final BlockType TripwireHook = new BlockType(131, 0, "Tripwire Hook");
    public static final BlockType Tripwire = new BlockType(132, 0, "Tripwire");
    public static final BlockType EmeraldBlock = new BlockType(133, 0, "Emerald Block");
    public static final BlockType PineWoodStair = new BlockType(134, 0, "Spruce Wood Stairs");
    public static final BlockType BirchWoodStair = new BlockType(135, 0, "Birch Wood Stairs");
    public static final BlockType JungleWoodStair = new BlockType(136, 0, "Jungle Wood Stairs");
    public static final BlockType CommandBlock = new BlockType(137, 0, "Command Block");
    public static final BlockType Beacon = new BlockType(138, 0, "Beacon");
    public static final BlockType CobblestoneWall = new BlockType(139, 0, "Cobblestone Wall");
    public static final BlockType Flowerpot = new BlockType(140, 0, "Flower Pot");
    public static final BlockType Carrots = new BlockType(141, 0, "Carrots");
    public static final BlockType Potatoes = new BlockType(142, 0, "Potatoes");
    public static final BlockType WoodenButton = new BlockType(143, 0, "Wooden Button");
    public static final BlockType SkeletonHead = new BlockType(144, 0, "Skeleton Head");
    public static final BlockType WitherSkeletonHead = new BlockType(144, 1, "Wither Skeleton Head");
    public static final BlockType ZombieHead = new BlockType(144, 2, "Zombie Head");
    public static final BlockType HumanHead = new BlockType(144, 3, "Human Head");
    public static final BlockType CreeperHead = new BlockType(144, 4, "Creeper Head");
    public static final BlockType Anvil = new BlockType(145, 0, "Anvil");
    public static final BlockType TrappedChest = new BlockType(146, 0, "Trapped Chest");
    public static final BlockType LightWeightedPressurePlate = new BlockType(147, 0, "Light Weighted Pressure Plate");
    public static final BlockType HeavyWeightedPressurePlate = new BlockType(148, 0, "Heavy Weighted Pressure Plate");
    public static final BlockType RedstoneComparatorOn = new BlockType(149, 0, "Redstone Comparator On");
    public static final BlockType RedstoneComparatorOff = new BlockType(150, 0, "Redstone Comparator Off");
    public static final BlockType DaylightSensor = new BlockType(151, 0, "Daylight Sensor");
    public static final BlockType RedstoneBlock = new BlockType(152, 0, "Redstone Block");
    public static final BlockType NetherQuartzOre = new BlockType(153, 0, "Nether Quartz Ore");
    public static final BlockType Hopper = new BlockType(154, 0, "Hopper");
    public static final BlockType QuartzBlock = new BlockType(155, 0, "Quartz Block");
    public static final BlockType OrnateQuartzBlock = new BlockType(155, 1, "Ornate Quartz Block");
    public static final BlockType QuartzPillarVertical = new BlockType(155, 2, "Quartz Pillar Vertical");
    public static final BlockType QuartzPillarHorizontal = new BlockType(155, 3, "Quartz Pillar Horizontal");
    public static final BlockType QuartzPillarCap = new BlockType(155, 4, "Quartz Pillar Cap");
    public static final BlockType QuartzStairs = new BlockType(156, 0, "Quartz Stairs");
    public static final BlockType ActivatorRail = new BlockType(157, 0, "Activator Rail");
    public static final BlockType Dropper = new BlockType(158, 0, "Dropper");
    public static final BlockType WhiteStainedClay = new BlockType(159, 0, "White Stained Clay");
    public static final BlockType OrangeStainedClay = new BlockType(159, 1, "Orange Stained Clay");
    public static final BlockType MagentaStainedClay = new BlockType(159, 2, "Magenta Stained Clay");
    public static final BlockType LightBlueStainedClay = new BlockType(159, 3, "LightBlue Stained Clay");
    public static final BlockType YellowStainedClay = new BlockType(159, 4, "Yellow Stained Clay");
    public static final BlockType LimeStainedClay = new BlockType(159, 5, "Lime Stained Clay");
    public static final BlockType PinkStainedClay = new BlockType(159, 6, "Pink Stained Clay");
    public static final BlockType GrayStainedClay = new BlockType(159, 7, "Gray Stained Clay");
    public static final BlockType LightGrayStainedClay = new BlockType(159, 8, "LightGray Stained Clay");
    public static final BlockType CyanStainedClay = new BlockType(159, 9, "Cyan Stained Clay");
    public static final BlockType PurpleStainedClay = new BlockType(159, 10, "Purple Stained Clay");
    public static final BlockType BlueStainedClay = new BlockType(159, 11, "Blue Stained Clay");
    public static final BlockType BrownStainedClay = new BlockType(159, 12, "Brown Stained Clay");
    public static final BlockType GreenStainedClay = new BlockType(159, 13, "Green Stained Clay");
    public static final BlockType RedStainedClay = new BlockType(159, 14, "Red Stained Clay");
    public static final BlockType BlackStainedClay = new BlockType(159, 15, "Black Stained Clay");
    // 160 - 169 MIA
    public static final BlockType HayBale = new BlockType(170, 0, "Hay Bale");
    public static final BlockType WhiteCarpet = new BlockType(171, 0, "White Carpet");
    public static final BlockType OrangeCarpet = new BlockType(171, 1, "Orange Carpet");
    public static final BlockType MagentaCarpet = new BlockType(171, 2, "Magenta Carpet");
    public static final BlockType LightBlueCarpet = new BlockType(171, 3, "LightBlue Carpet");
    public static final BlockType YellowCarpet = new BlockType(171, 4, "Yellow Carpet");
    public static final BlockType LimeCarpet = new BlockType(171, 5, "Lime Carpet");
    public static final BlockType PinkCarpet = new BlockType(171, 6, "Pink Carpet");
    public static final BlockType GrayCarpet = new BlockType(171, 7, "Gray Carpet");
    public static final BlockType LightGrayCarpet = new BlockType(171, 8, "LightGray Carpet");
    public static final BlockType CyanCarpet = new BlockType(171, 9, "Cyan Carpet");
    public static final BlockType PurpleCarpet = new BlockType(171, 10, "Purple Carpet");
    public static final BlockType BlueCarpet = new BlockType(171, 11, "Blue Carpet");
    public static final BlockType BrownCarpet = new BlockType(171, 12, "Brown Carpet");
    public static final BlockType GreenCarpet = new BlockType(171, 13, "Green Carpet");
    public static final BlockType RedCarpet = new BlockType(171, 14, "Red Carpet");
    public static final BlockType BlackCarpet = new BlockType(171, 15, "Black Carpet");
    public static final BlockType HardenedClay = new BlockType(172, 0, "Hardened Clay");
    public static final BlockType CoalBlock = new BlockType(173, 0, "Coal Block");

    private final short id;
    private final short data;
    private final String displayName;
    private final String machineName;

    private static HashMap<String, BlockType> blockTypes;

    public BlockType(short id, short data) {
        this(id, data, "unnamed_block_" + id + "_" + data);
    }

    /**
     * Constructs a BlockType from integers.
     * Note if your id's exceed 32000, there will be errors
     * so make sure your block data and id are clamped to this value
     *
     * @param id
     *         the ID for the Block
     * @param data
     *         the Data for the Block
     */
    public BlockType(int id, int data) {
        this(id, data, "unnamed_block_" + id + "_" + data);
    }

    public BlockType(int id, String name) {
        this(id, 0, name);
    }

    /**
     * This will create this blockType and also add it to the BlockTypes cache,
     * if a BlockType with the same name doesn't already exist.
     * IF a BlockType with the given name already exists, nothing will happen to
     * the BlockType list, you can still use this BlockType if you need to
     *
     * @param id
     *         the block id
     * @param data
     *         the block data
     * @param name
     *         the block name
     */
    public BlockType(int id, int data, String name) {
        if (blockTypes == null) {
            blockTypes = new HashMap<String, BlockType>();
        }
        if (name == null) {
            throw new CustomBlockTypeException("BlockType name cannot be null!");
        }
        this.id = (short) id;
        this.data = (short) data;
        this.displayName = name;
        this.machineName = name.replace(" ", "").toLowerCase();
        if (!blockTypes.containsKey(name)) {
            blockTypes.put(name, this);
        }
        else {
            throw new CustomBlockTypeException("BlockType '" + name + "' already exists!");
        }
    }

    /**
     * Get the ID of this BlockType
     *
     * @return data
     */
    public short getData() {
        return data;
    }

    /**
     * Get the ID of this BlockType
     *
     * @return id
     */
    public short getId() {
        return id;
    }

    /**
     * Gets the readable name of this BlockType.
     *
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns a "machine readable" name.
     * That is: a representation of the Block Type name
     * in lowercase letters without whitespaces.
     *
     * @return machine name
     */
    public String getMachineName() {
        return machineName;
    }

    /**
     * Get a custom block type.
     * Returns null if the requested BlockType does not exist.
     *
     * @param name
     *         the machine name or the display name of the block type in question
     *
     * @return the custom {@link BlockType}
     */
    public static BlockType getCustomBlockType(String name) {
        if (!blockTypes.containsKey(name)) {
            for (String key : blockTypes.keySet()) {
                BlockType t = blockTypes.get(key);

                if (t.machineName.equalsIgnoreCase(name)) {
                    return t;
                }
            }
            return null;
        }
        return blockTypes.get(name);
    }

    /**
     * Get the BlockType according to the given ID.
     * This will return null if there is no ItemType with this id.
     *
     * @param id
     *         the id
     *
     * @return the associated {@link BlockType} or {@code null}
     */
    public static BlockType fromId(int id) {
        for (String name : blockTypes.keySet()) {
            BlockType t = blockTypes.get(name);

            if (t.id == id) {
                return t;
            }
        }
        return null;
    }

    /**
     * Get the BlockType according to the given ID and Data.
     * This will return null if there is no BlockType with this id and data.
     *
     * @param id
     *         the id
     * @param data
     *         the data value
     *
     * @return the associated {@link BlockType} or {@code null}
     */
    public static BlockType fromIdAndData(int id, int data) {
        for (String name : blockTypes.keySet()) {
            BlockType t = blockTypes.get(name);

            if (t.id == id && t.data == data) {
                return t;
            }
        }
        return fromId(id); // if data has bit's set, it won't perfectly equal
    }

    /**
     * Returns an BlockType according to its name as defined in ItemType
     * This returns null if there is no BlockType with this name.
     *
     * @param name
     *         The machine name or the display name
     *
     * @return the associated {@link BlockType} or {@code null}
     */
    public static BlockType fromString(String name) {
        if (!blockTypes.containsKey(name)) {
            for (String key : blockTypes.keySet()) {
                BlockType t = blockTypes.get(key);

                if (t.machineName.equalsIgnoreCase(name)) {
                    return t;
                }
            }
            return null;
        }
        return blockTypes.get(name);
    }

    /**
     * Gets an array of all ItemTypes
     *
     * @return all ItemTypes
     */
    public static BlockType[] values() {
        return blockTypes.values().toArray(new BlockType[blockTypes.size()]);
    }

}
