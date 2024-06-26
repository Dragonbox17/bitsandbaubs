package net.benjamin.bitsandbaubs.item;

import net.benjamin.bitsandbaubs.BitsAndBaubs;
import net.benjamin.bitsandbaubs.block.ModBlocks;
import net.benjamin.bitsandbaubs.entity.ModEntities;
import net.benjamin.bitsandbaubs.entity.custom.AirShipEntity;
import net.benjamin.bitsandbaubs.item.custom.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BitsAndBaubs.MOD_ID);

    public static final RegistryObject<Item> RAW_PLATINUM =
            ITEMS.register("raw_platinum", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_INGOT =
            ITEMS.register("platinum_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_NUGGET =
            ITEMS.register("platinum_nugget", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATINUM_PICKAXE =
            ITEMS.register("platinum_pickaxe",
                    () -> new PickaxeItem(ModToolTiers.PLATINUM, 1, -3, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_AXE =
            ITEMS.register("platinum_axe",
                    () -> new AxeItem(ModToolTiers.PLATINUM, 6, -3, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_SHOVEL =
            ITEMS.register("platinum_shovel",
                    () -> new ShovelItem(ModToolTiers.PLATINUM, 1, -3, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_SWORD =
            ITEMS.register("platinum_sword",
                    () -> new SwordItem(ModToolTiers.PLATINUM, 5, -2, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_HOE =
            ITEMS.register("platinum_hoe",
                    () -> new HoeItem(ModToolTiers.PLATINUM, 0, 1, new Item.Properties()));


    public static final RegistryObject<Item> PLATINUM_HELMET =
            ITEMS.register("platinum_helmet",
                    () -> new ArmorItem(ModArmorMaterials.PLATINUM, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> PLATINUM_CHESTPLATE =
            ITEMS.register("platinum_chestplate",
                    () -> new ArmorItem(ModArmorMaterials.PLATINUM, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> PLATINUM_LEGGINGS =
            ITEMS.register("platinum_leggings",
                    () -> new ArmorItem(ModArmorMaterials.PLATINUM, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<Item> PLATINUM_BOOTS =
            ITEMS.register("platinum_boots",
                    () -> new ArmorItem(ModArmorMaterials.PLATINUM, ArmorItem.Type.BOOTS, new Item.Properties()));


    public static final RegistryObject<Item> TERRACOTTA_GOLEM_SPAWN_EGG =
            ITEMS.register("terracotta_golem_spawn_egg",
                    () -> new ForgeSpawnEggItem(ModEntities.TERRACOTTA_GOLEM, 0x6f4433, 0x7b4c37, new Item.Properties()));

    public static final RegistryObject<Item> CULLAGER_SPAWN_EGG =
            ITEMS.register("cullager_spawn_egg",
                    () -> new ForgeSpawnEggItem(ModEntities.CULLAGER, 0x63cb7d, 0x888e8e, new Item.Properties()));

    public static final RegistryObject<Item> FANG_BEAST_SPAWN_EGG =
            ITEMS.register("fang_beast_spawn_egg",
                    () -> new ForgeSpawnEggItem(ModEntities.FANG_BEAST, 0x404646, 0x7bffa3, new Item.Properties()));

    public static final RegistryObject<Item> ACACIA_AIR_SHIP =
            ITEMS.register("acacia_air_ship",
                    () -> new AirShipItem(false, AirShipEntity.Type.ACACIA, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> BIRCH_AIR_SHIP =
            ITEMS.register("birch_air_ship",
                    () -> new AirShipItem(false, AirShipEntity.Type.BIRCH, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> CHERRY_AIR_SHIP =
            ITEMS.register("cherry_air_ship",
                    () -> new AirShipItem(false, AirShipEntity.Type.CHERRY, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> DARK_OAK_AIR_SHIP =
            ITEMS.register("dark_oak_air_ship",
                    () -> new AirShipItem(false, AirShipEntity.Type.DARK_OAK, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> JUNGLE_AIR_SHIP =
            ITEMS.register("jungle_air_ship",
                    () -> new AirShipItem(false, AirShipEntity.Type.JUNGLE, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> MANGROVE_AIR_SHIP =
            ITEMS.register("mangrove_air_ship",
                    () -> new AirShipItem(false, AirShipEntity.Type.MANGROVE, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> OAK_AIR_SHIP =
            ITEMS.register("oak_air_ship",
                    () -> new AirShipItem(false, AirShipEntity.Type.OAK, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> SPRUCE_AIR_SHIP =
            ITEMS.register("spruce_air_ship",
                    () -> new AirShipItem(false, AirShipEntity.Type.SPRUCE, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> PROPELLER =
            ITEMS.register("propeller", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ENGINE =
            ITEMS.register("engine", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AIR_BALLOON =
            ITEMS.register("air_balloon", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> JADE =
            ITEMS.register("jade", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> JADE_PICKAXE =
            ITEMS.register("jade_pickaxe",
                    () -> new PickaxeItem(ModToolTiers.JADE, 1, -3, new Item.Properties()));
    public static final RegistryObject<Item> JADE_AXE =
            ITEMS.register("jade_axe",
                    () -> new AxeItem(ModToolTiers.JADE, 6, -3, new Item.Properties()));
    public static final RegistryObject<Item> JADE_SHOVEL =
            ITEMS.register("jade_shovel",
                    () -> new ShovelItem(ModToolTiers.JADE, 1, -3, new Item.Properties()));
    public static final RegistryObject<Item> JADE_CLUB =
            ITEMS.register("jade_club",
                    () -> new SwordItem(ModToolTiers.JADE, 6, -3F, new Item.Properties()));
    public static final RegistryObject<Item> JADE_HOE =
            ITEMS.register("jade_hoe",
                    () -> new HoeItem(ModToolTiers.JADE, 0, 1, new Item.Properties()));


    public static final RegistryObject<Item> JADE_HELMET =
            ITEMS.register("jade_helmet",
                    () -> new ArmorItem(ModArmorMaterials.JADE, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> JADE_CHESTPLATE =
            ITEMS.register("jade_chestplate",
                    () -> new ArmorItem(ModArmorMaterials.JADE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> JADE_LEGGINGS =
            ITEMS.register("jade_leggings",
                    () -> new ArmorItem(ModArmorMaterials.JADE, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<Item> JADE_BOOTS =
            ITEMS.register("jade_boots",
                    () -> new ArmorItem(ModArmorMaterials.JADE, ArmorItem.Type.BOOTS, new Item.Properties()));


    public static final RegistryObject<Item> JADE_STAFF =
            ITEMS.register("jade_staff", () -> new JadeStaffItem(new Item.Properties().stacksTo(1).defaultDurability(324)));

    public static final RegistryObject<Item> EMERALD_STAFF =
            ITEMS.register("emerald_staff", () -> new EmeraldStaffItem(new Item.Properties().stacksTo(1).defaultDurability(24)));


    public static final RegistryObject<Item> CORRUPTED_SIGN =
            ITEMS.register("infected_sign", () -> new SignItem(new Item.Properties().stacksTo(16)
                    , ModBlocks.INFECTED_SIGN.get(), ModBlocks.INFECTED_WALL_SIGN.get()));

    public static final RegistryObject<Item> CORRUPTED_HANGING_SIGN =
            ITEMS.register("infected_hanging_sign", () -> new HangingSignItem(ModBlocks.INFECTED_HANGING_SIGN.get()
                    , ModBlocks.INFECTED_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> CREEPER_WARD =
            ITEMS.register("creeper_ward", () -> new WardItem(new Item.Properties().stacksTo(1).defaultDurability(30), EntityType.CREEPER));

    public static final RegistryObject<Item> ZOMBIE_WARD =
            ITEMS.register("zombie_ward", () -> new WardItem(new Item.Properties().stacksTo(1).defaultDurability(30), EntityType.ZOMBIE));

    public static final RegistryObject<Item> ZOMBIFIED_PIGLIN_WARD =
            ITEMS.register("zombified_piglin_ward", () -> new WardItem(new Item.Properties().stacksTo(1).defaultDurability(30), EntityType.ZOMBIFIED_PIGLIN));

    public static final RegistryObject<Item> WITHER_SKELETON_WARD =
            ITEMS.register("wither_skeleton_ward", () -> new WardItem(new Item.Properties().stacksTo(1).defaultDurability(30), EntityType.WITHER_SKELETON));

    public static final RegistryObject<Item> BULLET_ROUND =
            ITEMS.register("bullet_round", () -> new BulletRoundItem(new Item.Properties()));

    public static final RegistryObject<Item> FLINTLOCK_PISTOL =
            ITEMS.register("flintlock_pistol", () -> new FlintlockItem(new Item.Properties().defaultDurability(300)));

    public static final RegistryObject<Item> AMBROSIA =
            ITEMS.register("ambrosia", () -> new Item(new Item.Properties().food(ModFoods.AMBROSIA)));

    public static final RegistryObject<Item> BLACK_BERRIES = ITEMS.register("black_berries",
            () -> new ItemNameBlockItem(ModBlocks.BLACK_BERRY_BUSH_BLOCK.get(), new Item.Properties().food(ModFoods.BLACK_BERRIES)));

    public static final RegistryObject<Item> IRON_SCYTHE =
            ITEMS.register("iron_scythe",
                    () -> new ScytheItem(Tiers.IRON, 2, -1F, new Item.Properties().stacksTo(1).defaultDurability(384)));

    public static final RegistryObject<Item> GOLDEN_SCYTHE =
            ITEMS.register("golden_scythe",
                    () -> new ScytheItem(Tiers.GOLD, 4, -1F, new Item.Properties().stacksTo(1).defaultDurability(64)));

    public static final RegistryObject<Item> PLATINUM_SCYTHE =
            ITEMS.register("platinum_scythe",
                    () -> new ScytheItem(ModToolTiers.PLATINUM, 3, -1F, new Item.Properties().stacksTo(1).defaultDurability(500)));

    public static final RegistryObject<Item> SCYTHE_OF_SPRING =
            ITEMS.register("scythe_of_spring",
                    () -> new SpringScytheItem(ModToolTiers.PLATINUM, 3, 0F, new Item.Properties().stacksTo(1).defaultDurability(600)));

    public static final RegistryObject<Item> SCYTHE_OF_SUMMER =
            ITEMS.register("scythe_of_summer",
                    () -> new SummerScytheItem(ModToolTiers.PLATINUM, 3, 0F, new Item.Properties().stacksTo(1).defaultDurability(600)));

    public static final RegistryObject<Item> SCYTHE_OF_AUTUMN =
            ITEMS.register("scythe_of_autumn",
                    () -> new AutumnScytheItem(ModToolTiers.PLATINUM, 3, 0F, new Item.Properties().stacksTo(1).defaultDurability(600)));

    public static final RegistryObject<Item> SCYTHE_OF_WINTER =
            ITEMS.register("scythe_of_winter",
                    () -> new WinterScytheItem(ModToolTiers.PLATINUM, 5, 0F, new Item.Properties().stacksTo(1).defaultDurability(600)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
