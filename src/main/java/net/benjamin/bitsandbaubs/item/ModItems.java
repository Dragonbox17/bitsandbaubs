package net.benjamin.bitsandbaubs.item;

import net.benjamin.bitsandbaubs.BitsAndBaubs;
import net.benjamin.bitsandbaubs.entity.ModEntities;
import net.benjamin.bitsandbaubs.entity.custom.AirShipEntity;
import net.benjamin.bitsandbaubs.item.custom.JadeStaffItem;
import net.minecraft.world.entity.vehicle.Boat;
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
                    () -> new PickaxeItem(ModToolTiers.PLATINUM, 1, 2, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_AXE =
            ITEMS.register("platinum_axe",
                    () -> new AxeItem(ModToolTiers.PLATINUM, 6, 1, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_SHOVEL =
            ITEMS.register("platinum_shovel",
                    () -> new ShovelItem(ModToolTiers.PLATINUM, 1, 1, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_SWORD =
            ITEMS.register("platinum_sword",
                    () -> new SwordItem(ModToolTiers.PLATINUM, 5, 2, new Item.Properties()));
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

    public static final RegistryObject<Item> OAK_AIR_SHIP =
            ITEMS.register("oak_air_ship",
                    () -> new AirShipItem(false, AirShipEntity.Type.OAK, new Item.Properties().stacksTo(1)));

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
                    () -> new PickaxeItem(ModToolTiers.JADE, 1, 2, new Item.Properties()));
    public static final RegistryObject<Item> JADE_AXE =
            ITEMS.register("jade_axe",
                    () -> new AxeItem(ModToolTiers.JADE, 6, 1, new Item.Properties()));
    public static final RegistryObject<Item> JADE_SHOVEL =
            ITEMS.register("jade_shovel",
                    () -> new ShovelItem(ModToolTiers.JADE, 1, 1, new Item.Properties()));
    public static final RegistryObject<Item> JADE_CLUB =
            ITEMS.register("jade_club",
                    () -> new SwordItem(ModToolTiers.JADE, 6, 2, new Item.Properties()));
    public static final RegistryObject<Item> JADE_HOE =
            ITEMS.register("jade_hoe",
                    () -> new HoeItem(ModToolTiers.JADE, 0, 1, new Item.Properties()));

    public static final RegistryObject<Item> JADE_OAK_STAFF =
            ITEMS.register("jade_oak_staff", () -> new JadeStaffItem(new Item.Properties().stacksTo(1)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
