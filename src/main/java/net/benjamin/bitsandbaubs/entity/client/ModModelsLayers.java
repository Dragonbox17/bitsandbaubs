package net.benjamin.bitsandbaubs.entity.client;

import net.benjamin.bitsandbaubs.BitsAndBaubs;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelsLayers {
    public static final ModelLayerLocation TERRACOTTA_GOLEM_LAYER =
            new ModelLayerLocation(
                    new ResourceLocation(BitsAndBaubs.MOD_ID, "terracotta_golem_layer"), "main"
            );

    public static final ModelLayerLocation AIR_SHIP_LAYER =
            new ModelLayerLocation(
                    new ResourceLocation(BitsAndBaubs.MOD_ID, "oak_air_ship"), "main"
            );

    public static final ModelLayerLocation BULLET_ROUND_LAYER =
            new ModelLayerLocation(
                    new ResourceLocation(BitsAndBaubs.MOD_ID, "bullet_round_layer"), "main"
            );

    public static final ModelLayerLocation CULLAGER_LAYER =
            new ModelLayerLocation(
                    new ResourceLocation(BitsAndBaubs.MOD_ID, "cullager_layer"), "main"
            );

    public static final ModelLayerLocation FANG_BEAST_LAYER =
            new ModelLayerLocation(
                    new ResourceLocation(BitsAndBaubs.MOD_ID, "fang_beast_layer"), "main"
            );
}
