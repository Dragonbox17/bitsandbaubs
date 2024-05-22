package net.benjamin.bitsandbaubs.event;


import net.benjamin.bitsandbaubs.BitsAndBaubs;
import net.benjamin.bitsandbaubs.block.ModBlocks;
import net.benjamin.bitsandbaubs.block.entity.ModBlockEntities;
import net.benjamin.bitsandbaubs.entity.client.*;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BitsAndBaubs.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelsLayers.TERRACOTTA_GOLEM_LAYER, TerracottaGolemModel::createBodyLayer);
        event.registerLayerDefinition(ModModelsLayers.FANG_BEAST_LAYER, FangBeastModel::createBodyLayer);
        event.registerLayerDefinition(ModModelsLayers.CULLAGER_LAYER, CullagerModel::createBodyLayer);
        event.registerLayerDefinition(ModModelsLayers.AIR_SHIP_LAYER, AirShipModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
    }
}
