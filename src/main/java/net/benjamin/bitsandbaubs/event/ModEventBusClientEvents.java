package net.benjamin.bitsandbaubs.event;


import net.benjamin.bitsandbaubs.BitsAndBaubs;
import net.benjamin.bitsandbaubs.entity.client.AirShipModel;
import net.benjamin.bitsandbaubs.entity.client.ModModelsLayers;
import net.benjamin.bitsandbaubs.entity.client.TerracottaGolemModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BitsAndBaubs.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static  void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelsLayers.TERRACOTTA_GOLEM_LAYER, TerracottaGolemModel::createBodyLayer);
        event.registerLayerDefinition(ModModelsLayers.AIR_SHIP_LAYER, AirShipModel::createBodyLayer);
    }
}
