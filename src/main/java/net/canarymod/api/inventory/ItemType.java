package net.canarymod.api.inventory;


import java.util.HashMap;


public final class ItemType {
    public static final ItemType Air = new ItemType(0, "Air");
    public static final ItemType Stone = new ItemType(1, "Stone");
    public static final ItemType Grass = new ItemType(2, "Grass");
    public static final ItemType Dirt = new ItemType(3, "Dirt");
    public static final ItemType Cobblestone = new ItemType(4, "Cobblestone");
    public static final ItemType Wood = new ItemType(5, "Wood");
    public static final ItemType Sapling = new ItemType(6, "Sapling");
    public static final ItemType Bedrock = new ItemType(7, "Bedrock");
    public static final ItemType Water = new ItemType(8, "Water");
    public static final ItemType StationaryWater = new ItemType(9, "Stationary Water");
    public static final ItemType Lava = new ItemType(10, "Lava");
    public static final ItemType StationaryLava = new ItemType(11, "Stationary Lava");
    public static final ItemType Sand = new ItemType(12, "Sand");
    public static final ItemType Gravel = new ItemType(13, "Gravel");
    public static final ItemType GoldOre = new ItemType(14, "Gold Ore");
    public static final ItemType IronOre = new ItemType(15, "Iron Ore");
    public static final ItemType CoalOre = new ItemType(16, "Coal Ore");
    public static final ItemType Log = new ItemType(17, "Log");
    public static final ItemType Leaves = new ItemType(18, "Leaves");
    public static final ItemType Sponge = new ItemType(19, "Sponge");
    public static final ItemType Glass = new ItemType(20, "Glass");
    public static final ItemType LapisLazuliOre = new ItemType(21, "Lapis Lazuli Ore");
    public static final ItemType LapisLazuliBlock = new ItemType(22, "Lapis Lazuli Block");
    public static final ItemType Dispenser = new ItemType(23, "Dispenser");
    public static final ItemType SandStone = new ItemType(24, "Sand Stone");
    public static final ItemType NoteBlock = new ItemType(25, "Note Block");
    public static final ItemType BedBlock = new ItemType(26, "Bed Block");
    public static final ItemType PoweredRails = new ItemType(27, "Powered Rails");
    public static final ItemType DetectorRails = new ItemType(28, "Detector Rails");
    public static final ItemType StickyPiston = new ItemType(29, "Sticky Piston");
    public static final ItemType Web = new ItemType(30, "Web");
    public static final ItemType TallGrass = new ItemType(31, "Tall Grass");
    public static final ItemType DeadShrub = new ItemType(32, "Dead Shrub");
    public static final ItemType Piston = new ItemType(33, "Piston");
    public static final ItemType PistonExtended = new ItemType(34, "Piston Extended");
    public static final ItemType Cloth = new ItemType(35, "Cloth");
    public static final ItemType PistonBlockFiller = new ItemType(36, "Piston Block Filler");
    public static final ItemType YellowFlower = new ItemType(37, "Yellow Flower");
    public static final ItemType RedRose = new ItemType(38, "Red Rose");
    public static final ItemType BrownMushroom = new ItemType(39, "Brown Mushroom");
    public static final ItemType RedMushroom = new ItemType(40, "Red Mushroom");
    public static final ItemType GoldBlock = new ItemType(41, "Gold Block");
    public static final ItemType IronBlock = new ItemType(42, "Iron Block");
    public static final ItemType DoubleStep = new ItemType(43, "Double Step");
    public static final ItemType Step = new ItemType(44, "Step");
    public static final ItemType Brick = new ItemType(45, "Brick");
    public static final ItemType TNT = new ItemType(46, "T N T");
    public static final ItemType BookShelf = new ItemType(47, "Book Shelf");
    public static final ItemType MossyCobblestone = new ItemType(48, "Mossy Cobblestone");
    public static final ItemType Obsidian = new ItemType(49, "Obsidian");
    public static final ItemType Torch = new ItemType(50, "Torch");
    public static final ItemType Fire = new ItemType(51, "Fire");
    public static final ItemType MobSpawner = new ItemType(52, "Mob Spawner");
    public static final ItemType WoodStairs = new ItemType(53, "Wood Stairs");
    public static final ItemType Chest = new ItemType(54, "Chest");
    public static final ItemType RedstoneWire = new ItemType(55, "Redstone Wire");
    public static final ItemType DiamondOre = new ItemType(56, "Diamond Ore");
    public static final ItemType DiamondBlock = new ItemType(57, "Diamond Block");
    public static final ItemType Workbench = new ItemType(58, "Workbench");
    public static final ItemType Crops = new ItemType(59, "Crops");
    public static final ItemType Soil = new ItemType(60, "Soil");
    public static final ItemType Furnace = new ItemType(61, "Furnace");
    public static final ItemType BurningFurnace = new ItemType(62, "Burning Furnace");
    public static final ItemType SignPost = new ItemType(63, "Sign Post");
    public static final ItemType WoodenDoor = new ItemType(64, "Wooden Door");
    public static final ItemType Ladder = new ItemType(65, "Ladder");
    public static final ItemType Rails = new ItemType(66, "Rails");
    public static final ItemType CobblestoneStairs = new ItemType(67, "Cobblestone Stairs");
    public static final ItemType WallSign = new ItemType(68, "Wall Sign");
    public static final ItemType Lever = new ItemType(69, "Lever");
    public static final ItemType StonePlate = new ItemType(70, "Stone Plate");
    public static final ItemType IronDoorBlock = new ItemType(71, "Iron Door Block");
    public static final ItemType WoodPlate = new ItemType(72, "Wood Plate");
    public static final ItemType RedstoneOre = new ItemType(73, "Redstone Ore");
    public static final ItemType GlowingRedstoneOre = new ItemType(74, "Glowing Redstone Ore");
    public static final ItemType RedstoneTorchOff = new ItemType(75, "Redstone Torch Off");
    public static final ItemType RedstoneTorchOn = new ItemType(76, "Redstone Torch On");
    public static final ItemType StoneButton = new ItemType(77, "Stone Button");
    public static final ItemType Snow = new ItemType(78, "Snow");
    public static final ItemType Ice = new ItemType(79, "Ice");
    public static final ItemType SnowBlock = new ItemType(80, "Snow Block");
    public static final ItemType Cactus = new ItemType(81, "Cactus");
    public static final ItemType Clay = new ItemType(82, "Clay");
    public static final ItemType ReedBlock = new ItemType(83, "Reed Block");
    public static final ItemType Jukebox = new ItemType(84, "Jukebox");
    public static final ItemType Fence = new ItemType(85, "Fence");
    public static final ItemType Pumpkin = new ItemType(86, "Pumpkin");
    public static final ItemType Netherstone = new ItemType(87, "Netherstone");
    public static final ItemType SlowSand = new ItemType(88, "Slow Sand");
    public static final ItemType LightStone = new ItemType(89, "Light Stone");
    public static final ItemType Portal = new ItemType(90, "Portal");
    public static final ItemType JackOLantern = new ItemType(91, "Jack O Lantern");
    public static final ItemType CakeBlock = new ItemType(92, "Cake Block");
    public static final ItemType RedstoneRepeaterOff = new ItemType(93, "Redstone Repeater Off");
    public static final ItemType RedstoneRepeaterOn = new ItemType(94, "Redstone Repeater On");
    public static final ItemType LockedChest = new ItemType(95, "Locked Chest");
    public static final ItemType Trapdoor = new ItemType(96, "Trapdoor");
    public static final ItemType SilverBlock = new ItemType(97, "Silver Block");
    public static final ItemType StoneBrick = new ItemType(98, "Stone Brick");
    public static final ItemType HugeBrownMushroom = new ItemType(99, "Huge Brown Mushroom");
    public static final ItemType HugeRedMushroom = new ItemType(100, "Huge Red Mushroom");
    public static final ItemType IronBars = new ItemType(101, "Iron Bars");
    public static final ItemType GlassPane = new ItemType(102, "Glass Pane");
    public static final ItemType MelonBlock = new ItemType(103, "Melon Block");
    public static final ItemType PumpkinStem = new ItemType(104, "Pumpkin Stem");
    public static final ItemType MelonStem = new ItemType(105, "Melon Stem");
    public static final ItemType Vine = new ItemType(106, "Vine");
    public static final ItemType FenceGate = new ItemType(107, "Fence Gate");
    public static final ItemType BrickStair = new ItemType(108, "Brick Stair");
    public static final ItemType StonebrickStair = new ItemType(109, "Stonebrick Stair");
    public static final ItemType Mycelium = new ItemType(110, "Mycelium");
    public static final ItemType LilyPad = new ItemType(111, "Lily Pad");
    public static final ItemType NetherBrick = new ItemType(112, "Nether Brick");
    public static final ItemType NetherBrickFence = new ItemType(113, "Nether Brick Fence");
    public static final ItemType NetherBrickStair = new ItemType(114, "Nether Brick Stair");
    public static final ItemType NetherWartBlock = new ItemType(115, "Nether Wart Block");
    public static final ItemType EnchantmentTable = new ItemType(116, "Enchantment Table");
    public static final ItemType BrewingStandBlock = new ItemType(117, "Brewing Stand Block");
    public static final ItemType CauldronBlock = new ItemType(118, "Cauldron Block");
    public static final ItemType EndPortal = new ItemType(119, "End Portal");
    public static final ItemType EndPortalFrame = new ItemType(120, "End Portal Frame");
    public static final ItemType EndStone = new ItemType(121, "End Stone");
    public static final ItemType EnderDragonEgg = new ItemType(122, "Ender Dragon Egg");
    public static final ItemType RedstoneLampOff = new ItemType(123, "Redstone Lamp Off");
    public static final ItemType RedstoneLampOn = new ItemType(124, "Redstone Lamp On");
    public static final ItemType WoodDoubleStep = new ItemType(125, "Wood Double Step");
    public static final ItemType WoodStep = new ItemType(126, "Wood Step");
    public static final ItemType CocoaPlant = new ItemType(127, "Cocoa Plant");
    public static final ItemType SandstoneStairs = new ItemType(128, "Sandstone Stairs");
    public static final ItemType EmeraldOre = new ItemType(129, "Emerald Ore");
    public static final ItemType EnderChest = new ItemType(130, "Ender Chest");
    public static final ItemType TripwireHook = new ItemType(131, "Tripwire Hook");
    public static final ItemType Tripwire = new ItemType(132, "Tripwire");
    public static final ItemType EmeraldBlock = new ItemType(133, "Emerald Block");
    public static final ItemType SpruceWoodStairs = new ItemType(134, "Spruce Wood Stairs");
    public static final ItemType BirchWoodStairs = new ItemType(135, "Birch Wood Stairs");
    public static final ItemType JungleWoodStairs = new ItemType(136, "Jungle Wood Stairs");
    public static final ItemType CommandBlock = new ItemType(137, "Command Block");
    public static final ItemType Beacon = new ItemType(138, "Beacon");
    public static final ItemType CobblestoneWall = new ItemType(139, "Cobblestone Wall");
    public static final ItemType FlowerPotBlock = new ItemType(140, "Flower Pot Block");
    public static final ItemType CarrotBlock = new ItemType(141, "Carrot Block");
    public static final ItemType PotatoBlock = new ItemType(142, "Potato Block");
    public static final ItemType WoodenButton = new ItemType(143, "Wooden Button");
    public static final ItemType SkullBlock = new ItemType(144, "Skull Block");
    public static final ItemType Anvil = new ItemType(145, "Anvil");
    public static final ItemType TrappedChest = new ItemType(146, "Trapped Chest");
    public static final ItemType WeightedPressurePlateLight = new ItemType(147, "Weighted Pressure Plate Light");
    public static final ItemType WeightedPressurePlateHeavy = new ItemType(148, "Weighted Pressure Plate Heavy");
    public static final ItemType DaylightSensor = new ItemType(151, "Daylight Sensor");
    public static final ItemType RedstoneBlock = new ItemType(152, "Redstone Block");
    public static final ItemType NetherQuartzOre = new ItemType(153, "Nether Quartz Ore");
    public static final ItemType Hopper = new ItemType(154, "Hopper");
    public static final ItemType QuartzBlock = new ItemType(155, "Quartz Block");
    public static final ItemType QuartzStairs = new ItemType(156, "Quartz Stairs");
    public static final ItemType ActivatorRail = new ItemType(157, "Activator Rail");
    public static final ItemType Dropper = new ItemType(158, "Dropper");
    public static final ItemType IronSpade = new ItemType(256, "Iron Spade");
    public static final ItemType IronPickaxe = new ItemType(257, "Iron Pickaxe");
    public static final ItemType IronAxe = new ItemType(258, "Iron Axe");
    public static final ItemType FlintAndSteel = new ItemType(259, "Flint And Steel");
    public static final ItemType Apple = new ItemType(260, "Apple");
    public static final ItemType Bow = new ItemType(261, "Bow");
    public static final ItemType Arrow = new ItemType(262, "Arrow");
    public static final ItemType Coal = new ItemType(263, "Coal");
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
    public static final ItemType Sign = new ItemType(323, "Sign");
    public static final ItemType WoodDoor = new ItemType(324, "Wood Door");
    public static final ItemType Bucket = new ItemType(325, "Bucket");
    public static final ItemType WaterBucket = new ItemType(326, "Water Bucket");
    public static final ItemType LavaBucket = new ItemType(327, "Lava Bucket");
    public static final ItemType Minecart = new ItemType(328, "Minecart");
    public static final ItemType Saddle = new ItemType(329, "Saddle");
    public static final ItemType IronDoor = new ItemType(330, "Iron Door");
    public static final ItemType RedStone = new ItemType(331, "Red Stone");
    public static final ItemType SnowBall = new ItemType(332, "Snow Ball");
    public static final ItemType Boat = new ItemType(333, "Boat");
    public static final ItemType Leather = new ItemType(334, "Leather");
    public static final ItemType MilkBucket = new ItemType(335, "Milk Bucket");
    public static final ItemType ClayBrick = new ItemType(336, "Clay Brick");
    public static final ItemType ClayBall = new ItemType(337, "Clay Ball");
    public static final ItemType Reed = new ItemType(338, "Reed");
    public static final ItemType Paper = new ItemType(339, "Paper");
    public static final ItemType Book = new ItemType(340, "Book");
    public static final ItemType SlimeBall = new ItemType(341, "Slime Ball");
    public static final ItemType StorageMinecart = new ItemType(342, "Storage Minecart");
    public static final ItemType PoweredMinecart = new ItemType(343, "Powered Minecart");
    public static final ItemType Egg = new ItemType(344, "Egg");
    public static final ItemType Compass = new ItemType(345, "Compass");
    public static final ItemType FishingRod = new ItemType(346, "Fishing Rod");
    public static final ItemType Watch = new ItemType(347, "Watch");
    public static final ItemType LightstoneDust = new ItemType(348, "Lightstone Dust");
    public static final ItemType RawFish = new ItemType(349, "Raw Fish");
    public static final ItemType CookedFish = new ItemType(350, "Cooked Fish");
    public static final ItemType InkSack = new ItemType(351, "Ink Sack");
    public static final ItemType Bone = new ItemType(352, "Bone");
    public static final ItemType Sugar = new ItemType(353, "Sugar");
    public static final ItemType Cake = new ItemType(354, "Cake");
    public static final ItemType Bed = new ItemType(355, "Bed");
    public static final ItemType RedstoneRepeater = new ItemType(356, "Redstone Repeater");
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
    public static final ItemType BrewingStand = new ItemType(379, "Brewing Stand");
    public static final ItemType Cauldron = new ItemType(380, "Cauldron");
    public static final ItemType EyeofEnder = new ItemType(381, "Eyeof Ender");
    public static final ItemType GlisteringMelon = new ItemType(382, "Glistering Melon");
    public static final ItemType SpawnEgg = new ItemType(383, "Spawn Egg");
    public static final ItemType BottleOEnchanting = new ItemType(384, "Bottle O Enchanting");
    public static final ItemType FireCharge = new ItemType(385, "Fire Charge");
    public static final ItemType BookAndQuill = new ItemType(386, "Book And Quill");
    public static final ItemType WrittenBook = new ItemType(387, "Written Book");
    public static final ItemType Emerald = new ItemType(388, "Emerald");
    public static final ItemType ItemFrame = new ItemType(389, "Item Frame");
    public static final ItemType FlowerPot = new ItemType(390, "Flower Pot");
    public static final ItemType Carrot = new ItemType(391, "Carrot");
    public static final ItemType Potato = new ItemType(392, "Potato");
    public static final ItemType BakedPotato = new ItemType(393, "Baked Potato");
    public static final ItemType PoisonousPotato = new ItemType(394, "Poisonous Potato");
    public static final ItemType EmptyMap = new ItemType(395, "Empty Map");
    public static final ItemType GoldenCarrot = new ItemType(396, "Golden Carrot");
    public static final ItemType Skull = new ItemType(397, "Skull");
    public static final ItemType CarrotOnAStick = new ItemType(398, "Carrot On A Stick");
    public static final ItemType NetherStar = new ItemType(399, "Nether Star");
    public static final ItemType PumpkinPie = new ItemType(400, "Pumpkin Pie");
    public static final ItemType FireworkRocket = new ItemType(401, "Firework Rocket");
    public static final ItemType FireworkStar = new ItemType(402, "Firework Star");
    public static final ItemType EnchantedBook = new ItemType(403, "Enchanted Book");
    public static final ItemType RedstoneComparator = new ItemType(404, "Redstone Comparator");
    public static final ItemType NetherBricks = new ItemType(405, "Nether Bricks");
    public static final ItemType NetherQuartz = new ItemType(406, "Nether Quartz");
    public static final ItemType MinecartTNT = new ItemType(407, "Minecart TNT");
    public static final ItemType MinecartHopper = new ItemType(408, "Minecart Hopper");
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
    private final String machineName;
    private static HashMap<String, ItemType> itemTypes = new HashMap<String, ItemType>();

    public ItemType(int id, String name) {
        if (itemTypes.containsKey(name)) {
            throw new ItemTypeException("Item " + name + "already is registered!");
        }
        if (fromId(id) != null) {
            throw new ItemTypeException("Item ID " + id + "already exists!");
        }
        this.id = id;
        this.machineName = name.replace(" ", "").toLowerCase();
        itemTypes.put(name, this);
    }

    /**
     * Get the ID of this ItemType
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the readable name of this item type.
     * This is not to be confused with item names!
     * @return
     */
    public String getDisplayName() {
        for (String name : itemTypes.keySet()) {
            ItemType t = itemTypes.get(name);

            if (t.id == this.id) {
                return t.getDisplayName();
            }
        }
        return null;
    }

    /**
     * Get a custom block type.
     * Returns null if the requested BlockType does not exist.
     * @param name the machine name or the display name of the block type in question
     * @return
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
     * @param name The machine name or the display name
     * @return
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
     * @param id
     * @return
     */
    public static ItemType fromId(int id) {
        for (String name : itemTypes.keySet()) {
            ItemType t = itemTypes.get(name);

            if (t.id == id) {
                return t;
            }
        }
        return null;
    }

    /**
     * Returns a "machine readable" name.
     * That is: a representation of the Item Type name
     * in lowercase letters without whitespaces.
     * @return
     */
    public String getMachineName() {
        return machineName;
    }

}
