package net.benjamin.bitsandbaubs.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.benjamin.bitsandbaubs.BitsAndBaubs;
import net.benjamin.bitsandbaubs.entity.custom.TerracottaGolemEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class TerracottaGolemRenderer extends MobRenderer<TerracottaGolemEntity, TerracottaGolemModel<TerracottaGolemEntity>> {
    public TerracottaGolemRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new TerracottaGolemModel<>(pContext.bakeLayer(ModModelsLayers.TERRACOTTA_GOLEM_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(TerracottaGolemEntity pEntity) {
        return new ResourceLocation(BitsAndBaubs.MOD_ID, "textures/entity/terracotta_golem.png");
    }

    @Override
    public void render(TerracottaGolemEntity p_115455_, float p_115456_, float p_115457_, PoseStack p_115458_, MultiBufferSource p_115459_, int p_115460_) {
        super.render(p_115455_, p_115456_, p_115457_, p_115458_, p_115459_, p_115460_);
    }
}
