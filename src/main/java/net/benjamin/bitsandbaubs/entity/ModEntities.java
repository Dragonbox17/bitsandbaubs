package net.benjamin.bitsandbaubs.entity;

import net.benjamin.bitsandbaubs.BitsAndBaubs;
import net.benjamin.bitsandbaubs.entity.custom.AirShipEntity;
import net.benjamin.bitsandbaubs.entity.custom.TerracottaGolemEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
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

    public static final RegistryObject<EntityType<AirShipEntity>> AIR_SHIP =
            ENTITY_TYPES.register("air_ship", () -> EntityType.Builder.<AirShipEntity>of(AirShipEntity::new, MobCategory.MISC)
                    .sized(2, 4).build("air_ship"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
