package net.canarymod.api.inventory;

import java.util.HashMap;

/**
 * Item Types
 *
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public final class ItemType {
    /* Blocks */
    public static final ItemType Air = new ItemType(0, "Air");
    public static final ItemType Stone = new ItemType(1, true, "Stone");
    public static final ItemType Grass = new ItemType(2, true, "Grass");
    public static final ItemType Dirt = new ItemType(3, true, "Dirt");
    public static final ItemType Cobble = new ItemType(4, true, "Cobble");
    public static final ItemType OakWood = new ItemType(5, 0, true, "Oak Wood");
    public static final ItemType SpruceWood = new ItemType(5, 1, true, "Spruce Wood");
    public static final ItemType BirchWood = new ItemType(5, 2, true, "Birch Wood");
    public static final ItemType JungleWood = new ItemType(5, 3, true, "Jungle Wood");
    public static final ItemType OakSapling = new ItemType(6, 0, true, "Oak Sapling");
    public static final ItemType SpruceSapling = new ItemType(6, 1, true, "Spruce Sapling");
    public static final ItemType BirchSapling = new ItemType(6, 2, true, "Birch Sapling");
    public static final ItemType JungleSapling = new ItemType(6, 3, true, "Jungle Sapling");
    public static final ItemType Bedrock = new ItemType(7, true, "Bedrock");
    public static final ItemType Water = new ItemType(8, true, "Water");
    public static final ItemType WaterFlowing = new ItemType(9, true, "Water Flowing");
    public static final ItemType Lava = new ItemType(10, true, "Lava");
    public static final ItemType LavaFlowing = new ItemType(11, true, "Lava Flowing");
    public static final ItemType Sand = new ItemType(12, true, "Sand");
    public static final ItemType Gravel = new ItemType(13, true, "Gravel");
    public static final ItemType GoldOre = new ItemType(14, true, "Gold Ore");
    public static final ItemType IronOre = new ItemType(15, true, "Iron Ore");
    public static final ItemType CoalOre = new ItemType(16, true, "Coal Ore");
    public static final ItemType OakLog = new ItemType(17, 0, true, "Oak Log");
    public static final ItemType PineLog = new ItemType(17, 1, true, "Pine Log");
    public static final ItemType BirchLog = new ItemType(17, 2, true, "Birch Log");
    public static final ItemType JungleLog = new ItemType(17, 3, true, "Jungle Log");
    public static final ItemType OakLeaves = new ItemType(18, 0, true, "Oak Leaves");
    public static final ItemType PineLeaves = new ItemType(18, 1, true, "Pine Needles");
    public static final ItemType BirchLeaves = new ItemType(18, 2, true, "Birch Leaves");
    public static final ItemType JungleLeaves = new ItemType(18, 3, true, "Jungle Leaves");
    public static final ItemType Sponge = new ItemType(19, true, "Sponge"); // THE SPONGE IS A LIE!
    public static final ItemType Glass = new ItemType(20, true, "Glass");
    public static final ItemType LapislazuliOre = new ItemType(21, true, "Lapislazuli Pre");
    public static final ItemType LapisBlock = new ItemType(22, true, "Lapislazuli Block");
    public static final ItemType Dispenser = new ItemType(23, true, "Dispenser");
    public static final ItemType Sandstone = new ItemType(24, 0, true, "Sandstone");
    public static final ItemType SandstoneOrnate = new ItemType(24, 1, true, "Ornate Sandstone");
    public static final ItemType SandstoneBlank = new ItemType(24, 2, true, "Blank Sandstone");
    public static final ItemType NoteBlock = new ItemType(25, true, "Note Block");
    public static final ItemType BedBlock = new ItemType(26, true, "Bed Block");
    public static final ItemType PoweredRail = new ItemType(27, true, "Powered Rail");
    public static final ItemType DetectorRail = new ItemType(28, true, "Detector Rail");
    public static final ItemType StickyPiston = new ItemType(29, true, "Sticky Piston");
    public static final ItemType SpiderWeb = new ItemType(30, true, "Spider Web");
    public static final ItemType Shrub = new ItemType(31, 0, true, "Shrub");
    public static final ItemType TallGrass = new ItemType(31, 1, true, "Tall Grass");
    public static final ItemType TallFern = new ItemType(31, 2, true, "Tall Fern");
    // 32 MIA
    public static final ItemType Piston = new ItemType(33, true, "Piston");
    public static final ItemType PistonExtended = new ItemType(34, true, "Extended Piston");
    public static final ItemType WoolWhite = new ItemType(35, 0, true, "White Wool");
    public static final ItemType WoolOrange = new ItemType(35, 1, true, "Orange Wool");
    public static final ItemType WoolMagenta = new ItemType(35, 2, true, "Magenta Wool");
    public static final ItemType WoolLightBlue = new ItemType(35, 3, true, "Light Blue Wool");
    public static final ItemType WoolYellow = new ItemType(35, 4, true, "Yellow Wool");
    public static final ItemType WoolLightGreen = new ItemType(35, 5, true, "Light Green Wool");
    public static final ItemType WoolPink = new ItemType(35, 6, true, "Pink Wool");
    public static final ItemType WoolGray = new ItemType(35, 7, true, "Gray Wool");
    public static final ItemType WoolLightGray = new ItemType(35, 8, true, "Light Gray Wool");
    public static final ItemType WoolCyan = new ItemType(35, 9, true, "Cyan Wool");
    public static final ItemType WoolPurple = new ItemType(35, 10, true, "Purple Wool");
    public static final ItemType WoolBlue = new ItemType(35, 11, true, "Blue Wool");
    public static final ItemType WoolBrown = new ItemType(35, 12, true, "Brown Wool");
    public static final ItemType WoolDarkGreen = new ItemType(35, 13, true, "Dark Green Wool");
    public static final ItemType WoolRed = new ItemType(35, 14, true, "Red Wool");
    public static final ItemType WoolBlack = new ItemType(35, 15, true, "Black Wool");
    public static final ItemType PistonBlockFiller = new ItemType(36, true, "Piston Block Filler");
    public static final ItemType YellowFlower = new ItemType(37, true, "Yellow Flower");
    public static final ItemType RedFlower = new ItemType(38, true, "Red Flower");
    public static final ItemType BrownMushroom = new ItemType(39, true, "Brown Mushroom");
    public static final ItemType RedMushroom = new ItemType(40, true, "Red Mushroom");
    public static final ItemType GoldBlock = new ItemType(41, true, "Gold Block");
    public static final ItemType IronBlock = new ItemType(42, true, "Iron Block");
    public static final ItemType DoublestepOrnateStone = new ItemType(43, 0, true, "Doublesetp Ornate Stone");
    public static final ItemType DoublestepSandStoneTrim = new ItemType(43, 1, true, "Doublestep Sandstone Trim");
    public static final ItemType DoublestepWood = new ItemType(43, 2, true, "Doublestep Woodstone");
    public static final ItemType DoublestepCobble = new ItemType(43, 3, true, "Doublestep Cobble");
    public static final ItemType DoublestepBrickBlock = new ItemType(43, 4, true, "Doublestep Bricks");
    public static final ItemType DoublestepStoneBricks = new ItemType(43, 5, true, "Doublestep Stone Bricks");
    public static final ItemType DoublestepNetherBrick = new ItemType(43, 6, true, "Doublestep Nether Bricks");
    public static final ItemType DoublestepQuartz = new ItemType(43, 9, true, "Doublestep Quartz");
    public static final ItemType DoublestepStone = new ItemType(43, 8, true, "Doublestep Stone");
    public static final ItemType DoublestepSandStone = new ItemType(43, 11, true, "Doublestep Sandstone");
    public static final ItemType StepOrnateStone = new ItemType(44, 0, true, "Ornate Stone Step");
    public static final ItemType StepSandStoneTrim = new ItemType(44, 1, true, "Sandstone Trim Step");
    public static final ItemType StepWood = new ItemType(44, 2, true, "Wood Step");
    public static final ItemType StepCobble = new ItemType(44, 3, true, "Cobble Step");
    public static final ItemType StepBrickBlock = new ItemType(44, 4, true, "Bricks Step");
    public static final ItemType StepStoneBricks = new ItemType(44, 5, true, "Stone Bricks Step");
    public static final ItemType StepNetherBricks = new ItemType(44, 6, true, "Nether Bricks Step");
    public static final ItemType StepQuartz = new ItemType(44, 7, true, "Quartz Step");
    public static final ItemType StepStone = new ItemType(44, 10, true, "Stone Step");
    public static final ItemType StepSandStone = new ItemType(44, 11, true, "Sandstone Step");
    public static final ItemType BrickBlock = new ItemType(45, true, "Brick Block");
    public static final ItemType Tnt = new ItemType(46, true, "TNT");
    public static final ItemType Bookshelf = new ItemType(47, true, "Bookshelf");
    public static final ItemType MossyCobble = new ItemType(48, true, "Mossy Cobblestone");
    public static final ItemType Obsidian = new ItemType(49, true, "Obsidian");
    public static final ItemType Torch = new ItemType(50, true, "Torch");
    public static final ItemType FireBlock = new ItemType(51, true, "Fire");
    public static final ItemType MobSpawner = new ItemType(52, true, "Mob Spawner");
    public static final ItemType WoodenStair = new ItemType(53, true, "Wooden Stair");
    public static final ItemType Chest = new ItemType(54, true, "Chest");
    public static final ItemType RedstoneWire = new ItemType(55, true, "Redstone Wire");
    public static final ItemType DiamondOre = new ItemType(56, true, "Diamond Ore");
    public static final ItemType DiamondBlock = new ItemType(57, true, "Diamond Block");
    public static final ItemType Workbench = new ItemType(58, true, "Workbench");
    public static final ItemType Crops = new ItemType(59, true, "Crops");
    public static final ItemType Soil = new ItemType(60, true, "Farmland");
    public static final ItemType Furnace = new ItemType(61, true, "Furnace");
    public static final ItemType BurningFurnace = new ItemType(62, true, "Burning Furnace");
    public static final ItemType SignPost = new ItemType(63, true, "Sign Post");
    public static final ItemType WoodenDoor = new ItemType(64, true, "Wooden Door");
    public static final ItemType Ladder = new ItemType(65, true, "Ladder");
    public static final ItemType Rail = new ItemType(66, true, "Rail");
    public static final ItemType CobbleStair = new ItemType(67, true, "Cobble Stairs");
    public static final ItemType WallSign = new ItemType(68, true, "Wall Sign");
    public static final ItemType Lever = new ItemType(69, true, "Lever");
    public static final ItemType StonePlate = new ItemType(70, true, "Stone Pressure Plate");
    public static final ItemType IronDoorBlock = new ItemType(71, true, "Iron Door Block");
    public static final ItemType WoodPlate = new ItemType(72, true, "Wooden Pressure Plate");
    public static final ItemType RedstoneOre = new ItemType(73, true, "Redstone Ore");
    public static final ItemType GlowingRedstoneOre = new ItemType(74, true, "Glowing Redstone Ore");
    public static final ItemType RedstoneTorchOff = new ItemType(75, true, "Redstone Torch off");
    public static final ItemType RedstoneTorchOn = new ItemType(76, true, "Redstone Torch on");
    public static final ItemType StoneButton = new ItemType(77, true, "Stone Button");
    public static final ItemType Snow = new ItemType(78, true, "Snow");
    public static final ItemType Ice = new ItemType(79, true, "Ice");
    public static final ItemType SnowBlock = new ItemType(80, true, "Snow Block");
    public static final ItemType Cactus = new ItemType(81, true, "Cactus");
    public static final ItemType Clay = new ItemType(82, true, "Clay");
    public static final ItemType ReedBlock = new ItemType(83, true, "Reed Block");
    public static final ItemType Jokebox = new ItemType(84, true, "Jukebox");
    public static final ItemType Fence = new ItemType(85, true, "Fence");
    public static final ItemType Pumpkin = new ItemType(86, true, "Pumpkin");
    public static final ItemType Netherrack = new ItemType(87, true, "Netherrack");
    public static final ItemType SoulSand = new ItemType(88, true, "Soul Sand");
    public static final ItemType GlowStone = new ItemType(89, true, "Glowstone");
    public static final ItemType Portal = new ItemType(90, true, "Nether Portal");
    public static final ItemType JackOLantern = new ItemType(91, true, "Jack 'o' Lantern");
    public static final ItemType CakeBlock = new ItemType(92, true, "Cake Block");
    public static final ItemType RedstoneRepeaterOff = new ItemType(93, true, "Redstone Repeater off");
    public static final ItemType RedstoneRepeaterOn = new ItemType(94, true, "Redstone Repeater on");
    public static final ItemType LockedChest = new ItemType(95, true, "Locked Chest");
    public static final ItemType Trapdoor = new ItemType(96, true, "Trapdoor");
    public static final ItemType CleanSilverFishBlock = new ItemType(97, 0, true, "Silverfish Spawnblock");
    public static final ItemType MossySilverFishBlock = new ItemType(97, 1, true, "Mossy Silverfish Spawnblock");
    public static final ItemType CrackdSilverFishBlock = new ItemType(97, 2, true, "Cracked Silverfish Spawnblock");
    public static final ItemType OrnateSilverFishBlock = new ItemType(97, 3, true, "Ornate Silverfish Spawnblock");
    public static final ItemType StoneBrick = new ItemType(98, 0, true, "Stone Bricks");
    public static final ItemType MossyStoneBrick = new ItemType(98, 1, true, "Mossy Stone Bricks");
    public static final ItemType CrackedStoneBrick = new ItemType(98, 2, true, "Cracked Stone Bricks");
    public static final ItemType OrnateStoneBrick = new ItemType(98, 3, true, "Ornate Stone Bricks");
    public static final ItemType HugeBrownMushroom = new ItemType(99, true, "Huge Brown Mushroom");
    public static final ItemType HugeRedMushroom = new ItemType(100, true, "Huge Red Mushroom");
    public static final ItemType IronBars = new ItemType(101, true, "Iron Bars");
    public static final ItemType GlassPane = new ItemType(102, true, "Glass Pane");
    public static final ItemType Melon = new ItemType(103, true, "Melon");
    public static final ItemType PumpkinStem = new ItemType(104, true, "Pumpkin Stem");
    public static final ItemType MelonStem = new ItemType(105, true, "Melon Stem");
    public static final ItemType Vines = new ItemType(106, true, "Vines");
    public static final ItemType FenceGate = new ItemType(107, true, "Fence Gate");
    public static final ItemType BrickStair = new ItemType(108, true, "Brick Stairs");
    public static final ItemType StoneBrickStair = new ItemType(109, true, "Stone Brick Stairs");
    public static final ItemType Mycelium = new ItemType(110, true, "Mycelium");
    public static final ItemType Lilypad = new ItemType(111, true, "Lilypad");
    public static final ItemType NetherBrick = new ItemType(112, true, "Nether Brick");
    public static final ItemType NetherBrickFence = new ItemType(113, true, "Nether Brick Fence");
    public static final ItemType NetherBrickStair = new ItemType(114, true, "Nether Brick Stairs");
    public static final ItemType NetherWartBlock = new ItemType(115, true, "Nether Wart Block");
    public static final ItemType EnchantmentTable = new ItemType(116, true, "Enchantment Table");
    public static final ItemType BrewingStandBlock = new ItemType(117, true, "Brewing Stand Block");
    public static final ItemType CauldronBlock = new ItemType(118, true, "Cauldron Block");
    public static final ItemType EndPortal = new ItemType(119, true, "End Portal");
    public static final ItemType EndPortalFrame = new ItemType(120, true, "End Portal Frame");
    public static final ItemType EndStone = new ItemType(121, true, "End Stone");
    public static final ItemType EnderDragonEgg = new ItemType(122, true, "Enderdragon Egg");
    public static final ItemType RedstoneLampOff = new ItemType(123, true, "Redstone Lamp off");
    public static final ItemType RedstoneLampOn = new ItemType(124, true, "Redstone Lamp on");
    public static final ItemType OakWoodDoubleStep = new ItemType(125, 0, true, "Oak Wood Doublestep");
    public static final ItemType SpruceWoodDoubleStep = new ItemType(125, 1, true, "Spruce Wood Doublestep");
    public static final ItemType BirchWoodDoubleStep = new ItemType(125, 2, true, "Birch Wood Doublestep");
    public static final ItemType JungleWoodDoubleStep = new ItemType(125, 3, true, "Jungle Wood Doublestep");
    public static final ItemType OakWoodStep = new ItemType(126, 0, true, "Oak Woodstep");
    public static final ItemType SpruceWoodStep = new ItemType(126, 1, true, "Spruce Woodstep");
    public static final ItemType BirchWoodStep = new ItemType(126, 2, true, "Birch Woodstep");
    public static final ItemType JungleWoodStep = new ItemType(126, 3, true, "Jungle Woodstep");
    public static final ItemType CocoaPlant = new ItemType(127, true, "Cocoa Plant");
    public static final ItemType SandstoneStair = new ItemType(128, true, "Sandstone Stairs");
    public static final ItemType EmeraldOre = new ItemType(129, true, "Emerald Ore");
    public static final ItemType EnderChest = new ItemType(130, true, "Ender Chest");
    public static final ItemType TripwireHook = new ItemType(131, true, "Tripwire Hook");
    public static final ItemType Tripwire = new ItemType(132, true, "Tripwire");
    public static final ItemType EmeraldBlock = new ItemType(133, true, "Emerald Block");
    public static final ItemType PineWoodStair = new ItemType(134, true, "Spruce Wood Stairs");
    public static final ItemType BirchWoodStair = new ItemType(135, true, "Birch Wood Stairs");
    public static final ItemType JungleWoodStair = new ItemType(136, true, "Jungle Wood Stairs");
    public static final ItemType CommandBlock = new ItemType(137, true, "Command Block");
    public static final ItemType Beacon = new ItemType(138, true, "Beacon");
    public static final ItemType CobblestoneWall = new ItemType(139, true, "Cobblestone Wall");
    public static final ItemType FlowerpotBlock = new ItemType(140, true, "Flower Pot Block");
    public static final ItemType Carrots = new ItemType(141, true, "Carrots");
    public static final ItemType Potatoes = new ItemType(142, true, "Potatoes");
    public static final ItemType WoodenButton = new ItemType(143, true, "Wooden Button");
    public static final ItemType SkeletonHeadBlock = new ItemType(144, 0, true, "Skeleton Head Block");
    public static final ItemType WitherSkeletonHeadBlock = new ItemType(144, 1, true, "Wither Skeleton Head Block");
    public static final ItemType ZombieHeadBlock = new ItemType(144, 2, true, "Zombie Head Block");
    public static final ItemType HumanHeadBlock = new ItemType(144, 3, true, "Human Head Block");
    public static final ItemType CreeperHeadBlock = new ItemType(144, 4, true, "Creeper Head Block");
    public static final ItemType Anvil = new ItemType(145, true, "Anvil");
    public static final ItemType TrappedChest = new ItemType(146, true, "Trapped Chest");
    public static final ItemType LightWeightedPressurePlate = new ItemType(147, true, "Light Weighted Pressure Plate");
    public static final ItemType HeavyWeightedPressurePlate = new ItemType(148, true, "Heavy Weighted Pressure Plate");
    public static final ItemType RedstoneComparatorOn = new ItemType(149, true, "Redstone Comparator On");
    public static final ItemType RedstoneComparatorOff = new ItemType(150, true, "Redstone Comparator Off");
    public static final ItemType DaylightSensor = new ItemType(151, true, "Daylight Sensor");
    public static final ItemType RedstoneBlock = new ItemType(152, true, "Redstone Block");
    public static final ItemType NetherQuartzOre = new ItemType(153, true, "Nether Quartz Ore");
    public static final ItemType Hopper = new ItemType(154, true, "Hopper");
    public static final ItemType QuartzBlock = new ItemType(155, 0, true, "Quartz Block");
    public static final ItemType OrnateQuartzBlock = new ItemType(155, 1, true, "Ornate Quartz Block");
    public static final ItemType QuartzPillarVertical = new ItemType(155, 2, true, "Quartz Pillar Vertical");
    public static final ItemType QuartzPillarHorizontal = new ItemType(155, 3, true, "Quartz Pillar Horizontal");
    public static final ItemType QuartzPillarCap = new ItemType(155, 4, true, "Quartz Pillar Cap");
    public static final ItemType QuartzStairs = new ItemType(156, true, "Quartz Stairs");
    public static final ItemType ActivatorRail = new ItemType(157, true, "Activator Rail");
    public static final ItemType Dropper = new ItemType(158, true, "Dropper");
    public static final ItemType WhiteStainedClay = new ItemType(159, 0, true, "White Stained Clay");
    public static final ItemType OrangeStainedClay = new ItemType(159, 1, true, "Orange Stained Clay");
    public static final ItemType MagentaStainedClay = new ItemType(159, 2, true, "Magenta Stained Clay");
    public static final ItemType LightBlueStainedClay = new ItemType(159, 3, true, "LightBlue Stained Clay");
    public static final ItemType YellowStainedClay = new ItemType(159, 4, true, "Yellow Stained Clay");
    public static final ItemType LimeStainedClay = new ItemType(159, 5, true, "Lime Stained Clay");
    public static final ItemType PinkStainedClay = new ItemType(159, 6, true, "Pink Stained Clay");
    public static final ItemType GrayStainedClay = new ItemType(159, 7, true, "Gray Stained Clay");
    public static final ItemType LightGrayStainedClay = new ItemType(159, 8, true, "LightGray Stained Clay");
    public static final ItemType CyanStainedClay = new ItemType(159, 9, true, "Cyan Stained Clay");
    public static final ItemType PurpleStainedClay = new ItemType(159, 10, true, "Purple Stained Clay");
    public static final ItemType BlueStainedClay = new ItemType(159, 11, true, "Blue Stained Clay");
    public static final ItemType BrownStainedClay = new ItemType(159, 12, true, "Brown Stained Clay");
    public static final ItemType GreenStainedClay = new ItemType(159, 13, true, "Green Stained Clay");
    public static final ItemType RedStainedClay = new ItemType(159, 14, true, "Red Stained Clay");
    public static final ItemType BlackStainedClay = new ItemType(159, 15, true, "Black Stained Clay");
    // 160 - 169 MIA
    public static final ItemType HayBale = new ItemType(170, 0, true, "Hay Bale");
    public static final ItemType WhiteCarpet = new ItemType(171, 0, true, "White Carpet");
    public static final ItemType OrangeCarpet = new ItemType(171, 1, true, "Orange Carpet");
    public static final ItemType MagentaCarpet = new ItemType(171, 2, true, "Magenta Carpet");
    public static final ItemType LightBlueCarpet = new ItemType(171, 3, true, "LightBlue Carpet");
    public static final ItemType YellowCarpet = new ItemType(171, 4, true, "Yellow Carpet");
    public static final ItemType LimeCarpet = new ItemType(171, 5, true, "Lime Carpet");
    public static final ItemType PinkCarpet = new ItemType(171, 6, true, "Pink Carpet");
    public static final ItemType GrayCarpet = new ItemType(171, 7, true, "Gray Carpet");
    public static final ItemType LightGrayCarpet = new ItemType(171, 8, true, "LightGray Carpet");
    public static final ItemType CyanCarpet = new ItemType(171, 9, true, "Cyan Carpet");
    public static final ItemType PurpleCarpet = new ItemType(171, 10, true, "Purple Carpet");
    public static final ItemType BlueCarpet = new ItemType(171, 11, true, "Blue Carpet");
    public static final ItemType BrownCarpet = new ItemType(171, 12, true, "Brown Carpet");
    public static final ItemType GreenCarpet = new ItemType(171, 13, true, "Green Carpet");
    public static final ItemType RedCarpet = new ItemType(171, 14, true, "Red Carpet");
    public static final ItemType BlackCarpet = new ItemType(171, 15, true, "Black Carpet");
    public static final ItemType HardenedClay = new ItemType(172, 0, true, "Hardened Clay");
    public static final ItemType CoalBlock = new ItemType(173, 0, true, "Coal Block");
    /* Items */
    public static final ItemType IronSpade = new ItemType(256, "Iron Spade");
    public static final ItemType IronPickaxe = new ItemType(257, "Iron Pickaxe");
    public static final ItemType IronAxe = new ItemType(258, "Iron Axe");
    public static final ItemType FlintAndSteel = new ItemType(259, "Flint And Steel");
    public static final ItemType Apple = new ItemType(260, "Apple");
    public static final ItemType Bow = new ItemType(261, "Bow");
    public static final ItemType Arrow = new ItemType(262, "Arrow");
    public static final ItemType Coal = new ItemType(263, "Coal");
    public static final ItemType Charcoal = new ItemType(263, 1, "Charcoal");
    public static final ItemType Diamond = new ItemType(264, "Diamond");
    public static final ItemType IronIngot = new ItemType(265, "Iron Ingot");
    public static final ItemType GoldIngot = new ItemType(266, "Gold Ingot");
    public static final ItemType IronSword = new ItemType(267, "Iron Sword");
    public static final ItemType WoodSword = new ItemType(268, "Wood Sword");
    public static final ItemType WoodSpade = new ItemType(269, "Wood Spade");
    public static final ItemType WoodPickaxe = new ItemType(270, "Wood Pickaxe");
    public static final ItemType WoodAxe = new ItemType(271, "Wood Axe");
    public static final ItemType StoneSword = new ItemType(272, "Stone Sword");
    public static final ItemType StoneSpade = new ItemType(273, "Stone Spade");
    public static final ItemType StonePickaxe = new ItemType(274, "Stone Pickaxe");
    public static final ItemType StoneAxe = new ItemType(275, "Stone Axe");
    public static final ItemType DiamondSword = new ItemType(276, "Diamond Sword");
    public static final ItemType DiamondSpade = new ItemType(277, "Diamond Spade");
    public static final ItemType DiamondPickaxe = new ItemType(278, "Diamond Pickaxe");
    public static final ItemType DiamondAxe = new ItemType(279, "Diamond Axe");
    public static final ItemType Stick = new ItemType(280, "Stick");
    public static final ItemType Bowl = new ItemType(281, "Bowl");
    public static final ItemType MushroomSoup = new ItemType(282, "Mushroom Soup");
    public static final ItemType GoldSword = new ItemType(283, "Gold Sword");
    public static final ItemType GoldSpade = new ItemType(284, "Gold Spade");
    public static final ItemType GoldPickaxe = new ItemType(285, "Gold Pickaxe");
    public static final ItemType GoldAxe = new ItemType(286, "Gold Axe");
    public static final ItemType String = new ItemType(287, "String");
    public static final ItemType Feather = new ItemType(288, "Feather");
    public static final ItemType Gunpowder = new ItemType(289, "Gunpowder");
    public static final ItemType WoodHoe = new ItemType(290, "Wood Hoe");
    public static final ItemType StoneHoe = new ItemType(291, "Stone Hoe");
    public static final ItemType IronHoe = new ItemType(292, "Iron Hoe");
    public static final ItemType DiamondHoe = new ItemType(293, "Diamond Hoe");
    public static final ItemType GoldHoe = new ItemType(294, "Gold Hoe");
    public static final ItemType Seeds = new ItemType(295, "Seeds");
    public static final ItemType Wheat = new ItemType(296, "Wheat");
    public static final ItemType Bread = new ItemType(297, "Bread");
    public static final ItemType LeatherHelmet = new ItemType(298, "Leather Helmet");
    public static final ItemType LeatherChestplate = new ItemType(299, "Leather Chestplate");
    public static final ItemType LeatherLeggings = new ItemType(300, "Leather Leggings");
    public static final ItemType LeatherBoots = new ItemType(301, "Leather Boots");
    public static final ItemType ChainmailHelmet = new ItemType(302, "Chainmail Helmet");
    public static final ItemType ChainmailChestplate = new ItemType(303, "Chainmail Chestplate");
    public static final ItemType ChainmailLeggings = new ItemType(304, "Chainmail Leggings");
    public static final ItemType ChainmailBoots = new ItemType(305, "Chainmail Boots");
    public static final ItemType IronHelmet = new ItemType(306, "Iron Helmet");
    public static final ItemType IronChestplate = new ItemType(307, "Iron Chestplate");
    public static final ItemType IronLeggings = new ItemType(308, "Iron Leggings");
    public static final ItemType IronBoots = new ItemType(309, "Iron Boots");
    public static final ItemType DiamondHelmet = new ItemType(310, "Diamond Helmet");
    public static final ItemType DiamondChestplate = new ItemType(311, "Diamond Chestplate");
    public static final ItemType DiamondLeggings = new ItemType(312, "Diamond Leggings");
    public static final ItemType DiamondBoots = new ItemType(313, "Diamond Boots");
    public static final ItemType GoldHelmet = new ItemType(314, "Gold Helmet");
    public static final ItemType GoldChestplate = new ItemType(315, "Gold Chestplate");
    public static final ItemType GoldLeggings = new ItemType(316, "Gold Leggings");
    public static final ItemType GoldBoots = new ItemType(317, "Gold Boots");
    public static final ItemType Flint = new ItemType(318, "Flint");
    public static final ItemType Pork = new ItemType(319, "Pork");
    public static final ItemType GrilledPork = new ItemType(320, "Grilled Pork");
    public static final ItemType Painting = new ItemType(321, "Painting");
    public static final ItemType GoldenApple = new ItemType(322, "Golden Apple");
    public static final ItemType Sign = new ItemType(323, true, "Sign");
    public static final ItemType WoodDoor = new ItemType(324, true, "Wood Door");
    public static final ItemType Bucket = new ItemType(325, "Bucket");
    public static final ItemType WaterBucket = new ItemType(326, true, "Water Bucket");
    public static final ItemType LavaBucket = new ItemType(327, true, "Lava Bucket");
    public static final ItemType Minecart = new ItemType(328, "Minecart");
    public static final ItemType Saddle = new ItemType(329, "Saddle");
    public static final ItemType IronDoor = new ItemType(330, true, "Iron Door");
    public static final ItemType RedStone = new ItemType(331, true, "RedStone Dust");
    public static final ItemType SnowBall = new ItemType(332, "Snow Ball");
    public static final ItemType Boat = new ItemType(333, "Boat");
    public static final ItemType Leather = new ItemType(334, "Leather");
    public static final ItemType MilkBucket = new ItemType(335, "Milk Bucket");
    public static final ItemType ClayBrick = new ItemType(336, "Clay Brick");
    public static final ItemType ClayBall = new ItemType(337, "Clay Ball");
    public static final ItemType Reed = new ItemType(338, true, "Reed");
    public static final ItemType Paper = new ItemType(339, "Paper");
    public static final ItemType Book = new ItemType(340, "Book");
    public static final ItemType SlimeBall = new ItemType(341, "Slime Ball");
    public static final ItemType StorageMinecart = new ItemType(342, "Storage Minecart");
    public static final ItemType PoweredMinecart = new ItemType(343, "Powered Minecart");
    public static final ItemType Egg = new ItemType(344, "Egg");
    public static final ItemType Compass = new ItemType(345, "Compass");
    public static final ItemType FishingRod = new ItemType(346, "Fishing Rod");
    public static final ItemType Watch = new ItemType(347, "Watch");
    public static final ItemType GlowstoneDust = new ItemType(348, "Glowstone Dust");
    public static final ItemType RawFish = new ItemType(349, "Raw Fish");
    public static final ItemType CookedFish = new ItemType(350, "Cooked Fish");
    public static final ItemType InkSack = new ItemType(351, 0, "Ink Sack");
    public static final ItemType RoseRed = new ItemType(351, 1, "Rose Red");
    public static final ItemType CactusGreen = new ItemType(351, 2, "Cactus Green");
    public static final ItemType CocoaBeans = new ItemType(351, 3, "Cocoa Beans");
    public static final ItemType LapisLazuli = new ItemType(351, 4, "Lapis Lazuli");
    public static final ItemType PurpleDye = new ItemType(351, 5, "Purple Dye");
    public static final ItemType CyanDye = new ItemType(351, 6, "Cyan Dye");
    public static final ItemType LightGrayDye = new ItemType(351, 7, "Light Gray Dye");
    public static final ItemType GrayDye = new ItemType(351, 8, "Gray Dye");
    public static final ItemType PinkDye = new ItemType(351, 9, "Pink Dye");
    public static final ItemType LimeDye = new ItemType(351, 10, "Lime Dye");
    public static final ItemType DandelionYellow = new ItemType(351, 11, "Dandelion Yellow");
    public static final ItemType LightBlueDye = new ItemType(351, 12, "Light Blue Dye");
    public static final ItemType MagentaDye = new ItemType(351, 13, "Magenta Dye");
    public static final ItemType OrangeDye = new ItemType(351, 14, "Orange Dye");
    public static final ItemType Bonemeal = new ItemType(351, 15, "Bonemeal");
    public static final ItemType Bone = new ItemType(352, "Bone");
    public static final ItemType Sugar = new ItemType(353, "Sugar");
    public static final ItemType Cake = new ItemType(354, "Cake");
    public static final ItemType Bed = new ItemType(355, true, "Bed");
    public static final ItemType RedstoneRepeater = new ItemType(356, true, "Redstone Repeater");
    public static final ItemType Cookie = new ItemType(357, "Cookie");
    public static final ItemType Map = new ItemType(358, "Map");
    public static final ItemType Shears = new ItemType(359, "Shears");
    public static final ItemType MelonSlice = new ItemType(360, "Melon Slice");
    public static final ItemType PumpkinSeeds = new ItemType(361, "Pumpkin Seeds");
    public static final ItemType MelonSeeds = new ItemType(362, "Melon Seeds");
    public static final ItemType RawBeef = new ItemType(363, "Raw Beef");
    public static final ItemType Steak = new ItemType(364, "Steak");
    public static final ItemType RawChicken = new ItemType(365, "Raw Chicken");
    public static final ItemType CookedChicken = new ItemType(366, "Cooked Chicken");
    public static final ItemType RottenFlesh = new ItemType(367, "Rotten Flesh");
    public static final ItemType EnderPearl = new ItemType(368, "Ender Pearl");
    public static final ItemType BlazeRod = new ItemType(369, "Blaze Rod");
    public static final ItemType GhastTear = new ItemType(370, "Ghast Tear");
    public static final ItemType GoldNugget = new ItemType(371, "Gold Nugget");
    public static final ItemType NetherWart = new ItemType(372, "Nether Wart");
    public static final ItemType Potion = new ItemType(373, "Potion");
    public static final ItemType GlassBottle = new ItemType(374, "Glass Bottle");
    public static final ItemType SpiderEye = new ItemType(375, "Spider Eye");
    public static final ItemType FermentedSpiderEye = new ItemType(376, "Fermented Spider Eye");
    public static final ItemType BlazePowder = new ItemType(377, "Blaze Powder");
    public static final ItemType MagmaCream = new ItemType(378, "Magma Cream");
    public static final ItemType BrewingStand = new ItemType(379, true, "Brewing Stand");
    public static final ItemType Cauldron = new ItemType(380, true, "Cauldron");
    public static final ItemType EyeofEnder = new ItemType(381, "Eye of Ender");
    public static final ItemType GlisteringMelon = new ItemType(382, "Glistering Melon");
    public static final ItemType SpawnEgg = new ItemType(383, "Spawn Egg");
    public static final ItemType BottleOEnchanting = new ItemType(384, "Bottle O Enchanting");
    public static final ItemType FireCharge = new ItemType(385, "Fire Charge");
    public static final ItemType BookAndQuill = new ItemType(386, "Book And Quill");
    public static final ItemType WrittenBook = new ItemType(387, "Written Book");
    public static final ItemType Emerald = new ItemType(388, "Emerald");
    public static final ItemType ItemFrame = new ItemType(389, "Item Frame");
    public static final ItemType FlowerPot = new ItemType(390, true, "Flower Pot");
    public static final ItemType Carrot = new ItemType(391, "Carrot");
    public static final ItemType Potato = new ItemType(392, "Potato");
    public static final ItemType BakedPotato = new ItemType(393, "Baked Potato");
    public static final ItemType PoisonousPotato = new ItemType(394, "Poisonous Potato");
    public static final ItemType EmptyMap = new ItemType(395, "Empty Map");
    public static final ItemType GoldenCarrot = new ItemType(396, "Golden Carrot");
    public static final ItemType SkeletonHead = new ItemType(397, 0, true, "Skeleton Head");
    public static final ItemType WitherSkeletonHead = new ItemType(397, 1, true, "Wither Skeleton Head");
    public static final ItemType ZombieHead = new ItemType(397, 2, true, "Zombie Head");
    public static final ItemType HumanHead = new ItemType(397, 3, true, "Human Head");
    public static final ItemType CreeperHead = new ItemType(397, 4, true, "Creeper Head");
    public static final ItemType CarrotOnAStick = new ItemType(398, "Carrot On A Stick");
    public static final ItemType NetherStar = new ItemType(399, "Nether Star");
    public static final ItemType PumpkinPie = new ItemType(400, "Pumpkin Pie");
    public static final ItemType FireworkRocket = new ItemType(401, "Firework Rocket");
    public static final ItemType FireworkStar = new ItemType(402, "Firework Star");
    public static final ItemType EnchantedBook = new ItemType(403, "Enchanted Book");
    public static final ItemType RedstoneComparator = new ItemType(404, true, "Redstone Comparator");
    public static final ItemType NetherBricks = new ItemType(405, "Nether Bricks");
    public static final ItemType NetherQuartz = new ItemType(406, "Nether Quartz");
    public static final ItemType MinecartTNT = new ItemType(407, "Minecart TNT");
    public static final ItemType MinecartHopper = new ItemType(408, "Minecart Hopper");
    public static final ItemType IronHorseArmor = new ItemType(417, "Iron Horse Armor");
    public static final ItemType GoldHorseArmor = new ItemType(418, "Gold Horse Armor");
    public static final ItemType DiamondHorseArmor = new ItemType(419, "Diamond Horse Armor");
    public static final ItemType Lead = new ItemType(420, "Lead");
    public static final ItemType NameTag = new ItemType(421, "NameTag");
    /* Records */
    public static final ItemType GoldRecord = new ItemType(2256, "Gold Record");
    public static final ItemType GreenRecord = new ItemType(2257, "Green Record");
    public static final ItemType BlocksRecord = new ItemType(2258, "Blocks Record");
    public static final ItemType ChirpRecord = new ItemType(2259, "Chirp Record");
    public static final ItemType FarRecord = new ItemType(2260, "Far Record");
    public static final ItemType MallRecord = new ItemType(2261, "Mall Record");
    public static final ItemType MellohiRecord = new ItemType(2262, "Mellohi Record");
    public static final ItemType StalRecord = new ItemType(2263, "Stal Record");
    public static final ItemType StradRecord = new ItemType(2264, "Strad Record");
    public static final ItemType WardRecord = new ItemType(2265, "Ward Record");
    public static final ItemType ElevenRecord = new ItemType(2266, "Eleven Record");
    public static final ItemType WaitRecord = new ItemType(2267, "Wait Record");

    private final int id;
    private final int data;
    private final boolean blockCreating;
    private final String machineName;
    private final String displayName;
    private static HashMap<String, ItemType> itemTypes;

    public ItemType(int id) {
        this(id, 0, false, "unnamed_item_" + id + "_0");
    }

    public ItemType(int id, int data) {
        this(id, data, false, "unnamed_item_" + id + "_" + data);
    }

    public ItemType(int id, String name) {
        this(id, 0, false, name);
    }

    public ItemType(int id, boolean isBlockCreating) {
        this(id, 0, isBlockCreating, "unnamed_item_" + id + "_0");
    }

    public ItemType(int id, int data, String name) {
        this(id, data, false, name);
    }

    public ItemType(int id, boolean isBlockCreating, String name) {
        this(id, 0, isBlockCreating, name);
    }

    public ItemType(int id, int data, boolean isBlockCreating, String name) {
        if (itemTypes == null) {
            itemTypes = new HashMap<String, ItemType>();
        }
        if (name == null) {
            throw new ItemTypeException("ItemType name cannot be null");
        }
        if (itemTypes.containsKey(name)) {
            throw new ItemTypeException("ItemType '" + name + "' is already is registered!");
        }
        this.id = id;
        this.data = data;
        this.blockCreating = isBlockCreating;
        this.displayName = name;
        this.machineName = name.replace(" ", "").toLowerCase();
        itemTypes.put(name, this);
    }

    /**
     * Get the ID of this ItemType
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Get the data of the ItemType
     *
     * @return the data
     */
    public int getData() {
        return data;
    }

    /**
     * Gets the readable name of this item type.
     * This is not to be confused with item names!
     *
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Get a custom ItemType.
     *
     * @param name
     *         the machine name or the display name of the block type in question
     *
     * @return the ItemType if found; {@code null} if the requested ItemType does not exist.
     */
    public static ItemType getCustomItemType(String name) {
        if (!itemTypes.containsKey(name)) {
            for (String key : itemTypes.keySet()) {
                ItemType t = itemTypes.get(key);

                if (t.machineName.equalsIgnoreCase(name)) {
                    return t;
                }
            }
            return null;
        }
        return itemTypes.get(name);
    }

    /**
     * Returns an ItemType according to its name as defined in ItemType
     * This returns null if there is no ItemType with this name.
     *
     * @param name
     *         The machine name or the display name
     *
     * @return the ItemType if found; {@code null} if not
     */
    public static ItemType fromString(String name) {
        if (!itemTypes.containsKey(name)) {
            for (String key : itemTypes.keySet()) {
                ItemType t = itemTypes.get(key);

                if (t.machineName.equalsIgnoreCase(name)) {
                    return t;
                }
            }
            return null;
        }
        return itemTypes.get(name);
    }

    /**
     * Get the ItemType according to the given ID.
     * This will return null if there is no ItemType with this id.
     *
     * @param id
     *         the id to get type from
     *
     * @return the ItemType if found; {@code null} if not
     */
    public static ItemType fromId(int id) {
        for (ItemType type : itemTypes.values()) {
            if (type.id == id) {
                return type;
            }
        }
        return null;
    }

    /**
     * Gets an ItemType according to the given ID and Data values.
     * This will return null if there is no ItemType with this id.
     *
     * @param id
     *         the id to get type from
     * @param data
     *         the data (damage) to get type from
     *
     * @return the ItemType if found; {@code null} if not
     */
    public static ItemType fromIdAndData(int id, int data) {
        for (ItemType type : itemTypes.values()) {
            if (type.id == id && type.data == data) {
                return type;
            }
        }
        return fromId(id);
    }

    /**
     * Returns a "machine readable" name.
     * That is: a representation of the Item Type name
     * in lowercase letters without whitespaces.
     *
     * @return the machine name
     */
    public String getMachineName() {
        return machineName;
    }

    /**
     * Checks the Item ID if it is an Item that creates a Block
     *
     * @param itemId
     *         the Item ID to check
     *
     * @return {@code true} if creates blocks; {@code false} if not
     */
    public static boolean isBlockCreating(int itemId) {
        return fromId(itemId).blockCreating;
    }

    public boolean isBlockCreating() {
        return blockCreating;
    }

    /**
     * Gets an array of all ItemTypes
     *
     * @return all ItemTypes
     */
    public static ItemType[] values() {
        return itemTypes.values().toArray(new ItemType[itemTypes.size()]);
    }
}
