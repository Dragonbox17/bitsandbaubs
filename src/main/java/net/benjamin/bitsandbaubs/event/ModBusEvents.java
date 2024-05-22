package net.benjamin.bitsandbaubs.event;


import net.benjamin.bitsandbaubs.BitsAndBaubs;
import net.benjamin.bitsandbaubs.entity.ModEntities;
import net.benjamin.bitsandbaubs.entity.client.AirShipModel;
import net.benjamin.bitsandbaubs.entity.client.BulletRoundModel;
import net.benjamin.bitsandbaubs.entity.client.FangBeastModel;
import net.benjamin.bitsandbaubs.entity.client.ModModelsLayers;
import net.benjamin.bitsandbaubs.entity.custom.CullagerEntity;
import net.benjamin.bitsandbaubs.entity.custom.FangBeastEntity;
import net.benjamin.bitsandbaubs.entity.custom.TerracottaGolemEntity;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BitsAndBaubs.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEvents {
    @SubscribeEvent
    public static void registerLayers(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelsLayers.AIR_SHIP_LAYER, AirShipModel::createBodyLayer);
        event.registerLayerDefinition(ModModelsLayers.BULLET_ROUND_LAYER, BulletRoundModel::createBodyLayer);
        event.registerLayerDefinition(ModModelsLayers.FANG_BEAST_LAYER, FangBeastModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.TERRACOTTA_GOLEM.get(), TerracottaGolemEntity.createAttributes().build());
        event.put(ModEntities.FANG_BEAST.get(), FangBeastEntity.createAttributes().build());
        event.put(ModEntities.CULLAGER.get(), CullagerEntity.createAttributes().build());
    }
}
