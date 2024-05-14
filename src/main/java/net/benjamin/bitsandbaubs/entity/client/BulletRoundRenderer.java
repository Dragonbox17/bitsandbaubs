package net.benjamin.bitsandbaubs.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.benjamin.bitsandbaubs.BitsAndBaubs;
import net.benjamin.bitsandbaubs.entity.custom.AirShipEntity;
import net.benjamin.bitsandbaubs.entity.custom.BulletRound;
import net.benjamin.bitsandbaubs.entity.custom.TerracottaGolemEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class BulletRoundRenderer extends EntityRenderer {
    private static final ResourceLocation BULLET_ROUND_TEXTURE =
            new ResourceLocation(BitsAndBaubs.MOD_ID, "textures/entity/bullet_round.png");
    protected final EntityModel<BulletRound> model;

    public BulletRoundRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        model = new BulletRoundModel(pContext.bakeLayer(ModModelsLayers.BULLET_ROUND_LAYER));
    }

    @Override
    public void render(Entity p_113929_, float p_113930_, float p_113931_, PoseStack p_113932_, MultiBufferSource p_113933_, int p_113934_) {
        super.render(p_113929_, p_113930_, p_113931_, p_113932_, p_113933_, p_113934_);

        p_113932_.pushPose();
        p_113932_.translate(0.0F, 1.5F, 0.0F);
        p_113932_.mulPose(Axis.YP.rotationDegrees(180.0F - p_113930_));

        p_113932_.scale(-1.0F, -1.0F, 1.0F);
        VertexConsumer vertexconsumer = p_113933_.getBuffer(this.model.renderType(this.getTextureLocation(p_113929_)));
        this.model.setupAnim((BulletRound) p_113929_, 0, 0, p_113929_.tickCount, 0, 0);
        this.model.renderToBuffer(p_113932_, vertexconsumer, p_113934_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        p_113932_.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(Entity p_114482_) {
        return BULLET_ROUND_TEXTURE;
    }
}
