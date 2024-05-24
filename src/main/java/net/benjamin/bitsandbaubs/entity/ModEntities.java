package net.benjamin.bitsandbaubs.entity;

import net.benjamin.bitsandbaubs.BitsAndBaubs;
import net.benjamin.bitsandbaubs.entity.custom.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BitsAndBaubs.MOD_ID);

    public static final RegistryObject<EntityType<TerracottaGolemEntity>> TERRACOTTA_GOLEM =
            ENTITY_TYPES.register("terracotta_golem", () -> EntityType.Builder.of(TerracottaGolemEntity::new, MobCategory.CREATURE)
                    .sized(1, 2).build("terracotta_golem"));

    public static final RegistryObject<EntityType<AirShipEntity>> ACACIA_AIR_SHIP =
            ENTITY_TYPES.register("acacia_air_ship", () -> EntityType.Builder.<AirShipEntity>of(AirShipEntity::new, MobCategory.MISC)
                    .sized(2, 4).build("acacia_air_ship"));

    public static final RegistryObject<EntityType<AirShipEntity>> BIRCH_AIR_SHIP =
            ENTITY_TYPES.register("birch_air_ship", () -> EntityType.Builder.<AirShipEntity>of(AirShipEntity::new, MobCategory.MISC)
                    .sized(2, 4).build("birch_air_ship"));

    public static final RegistryObject<EntityType<AirShipEntity>> CHERRY_AIR_SHIP =
            ENTITY_TYPES.register("cherry_air_ship", () -> EntityType.Builder.<AirShipEntity>of(AirShipEntity::new, MobCategory.MISC)
                    .sized(2, 4).build("cherry_air_ship"));

    public static final RegistryObject<EntityType<AirShipEntity>> DARK_OAK_AIR_SHIP =
            ENTITY_TYPES.register("dark_oak_air_ship", () -> EntityType.Builder.<AirShipEntity>of(AirShipEntity::new, MobCategory.MISC)
                    .sized(2, 4).build("dark_oak_air_ship"));

    public static final RegistryObject<EntityType<AirShipEntity>> JUNGLE_AIR_SHIP =
            ENTITY_TYPES.register("jungle_air_ship", () -> EntityType.Builder.<AirShipEntity>of(AirShipEntity::new, MobCategory.MISC)
                    .sized(2, 4).build("jungle_air_ship"));

    public static final RegistryObject<EntityType<AirShipEntity>> MANGROVE_AIR_SHIP =
            ENTITY_TYPES.register("mangrove_air_ship", () -> EntityType.Builder.<AirShipEntity>of(AirShipEntity::new, MobCategory.MISC)
                    .sized(2, 4).build("mangrove_air_ship"));

    public static final RegistryObject<EntityType<AirShipEntity>> OAK_AIR_SHIP =
            ENTITY_TYPES.register("oak_air_ship", () -> EntityType.Builder.<AirShipEntity>of(AirShipEntity::new, MobCategory.MISC)
                    .sized(2, 4).build("oak_air_ship"));

    public static final RegistryObject<EntityType<AirShipEntity>> SPRUCE_AIR_SHIP =
            ENTITY_TYPES.register("spruce_air_ship", () -> EntityType.Builder.<AirShipEntity>of(AirShipEntity::new, MobCategory.MISC)
                    .sized(2, 4).build("spruce_air_ship"));

    public static final RegistryObject<EntityType<AbstractArrow>> BULLET_ROUND =
            ENTITY_TYPES.register("bullet_round", () -> EntityType.Builder.<AbstractArrow>of(BulletRound::new, MobCategory.MISC)
                    .sized(0.1F, 0.1F).build("bullet_round"));

    public static final RegistryObject<EntityType<CullagerEntity>> CULLAGER =
            ENTITY_TYPES.register("cullager", () -> EntityType.Builder.of(CullagerEntity::new, MobCategory.MONSTER)
                    .sized(1, 2).build("cullager"));

    public static final RegistryObject<EntityType<FangBeastEntity>> FANG_BEAST =
            ENTITY_TYPES.register("fang_beast", () -> EntityType.Builder.of(FangBeastEntity::new, MobCategory.MONSTER)
                    .sized(1F, 2F).build("fang_beast"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
