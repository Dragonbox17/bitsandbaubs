package net.benjamin.bitsandbaubs.entity.client;

// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.benjamin.bitsandbaubs.entity.animations.ModAnimationDefinitions;
import net.benjamin.bitsandbaubs.entity.custom.AirShipEntity;
import net.benjamin.bitsandbaubs.entity.custom.TerracottaGolemEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;

public class AirShipModel<T extends Entity> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "air_ship"), "main");
    private final ModelPart Airship;
    private final ModelPart Propeller;
    private final ModelPart Propeller2;

    public AirShipModel(ModelPart root) {
        this.Airship = root.getChild("Airship");
        this.Propeller = Airship.getChild("Balloon").getChild("engine").getChild("propeller");
        this.Propeller2 = Airship.getChild("Balloon").getChild("engine2").getChild("propeller2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Airship = partdefinition.addOrReplaceChild("Airship", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition Balloon = Airship.addOrReplaceChild("Balloon", CubeListBuilder.create().texOffs(0, 0).addBox(-15.0F, -63.0F, -28.0F, 30.0F, 30.0F, 47.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 5.5F));

        PartDefinition engine = Balloon.addOrReplaceChild("engine", CubeListBuilder.create().texOffs(0, 8).addBox(-21.0F, -3.0F, 12.0F, 6.0F, 6.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(-19.0F, -1.0F, 21.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -48.0F, 0.0F));

        PartDefinition propeller = engine.addOrReplaceChild("propeller", CubeListBuilder.create().texOffs(21, 8).addBox(-3.0F, -3.0F, 0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-18.0F, 0.0F, 21.6F));

        PartDefinition engine2 = Balloon.addOrReplaceChild("engine2", CubeListBuilder.create().texOffs(0, 8).mirror().addBox(15.0F, -53.0F, 17.5F, 6.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 8).mirror().addBox(17.0F, -51.0F, 26.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 2.0F, -5.5F));

        PartDefinition propeller2 = engine2.addOrReplaceChild("propeller2", CubeListBuilder.create().texOffs(21, 8).mirror().addBox(-3F, -3.0F, 0.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(18.0F, -50.0F, 27.1F));

        PartDefinition ropes = Balloon.addOrReplaceChild("ropes", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = ropes.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(36, 8).addBox(-10.0F, -37.0F, -14.0F, 2.0F, 31.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, -0.1309F));

        PartDefinition cube_r2 = ropes.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(36, 8).addBox(8.0F, -36.0F, 4.0F, 2.0F, 31.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.1309F));

        PartDefinition cube_r3 = ropes.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(36, 8).addBox(8.0F, -36.0F, -14.0F, 2.0F, 31.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.1309F));

        PartDefinition cube_r4 = ropes.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(36, 8).addBox(-10.0F, -36.0F, 4.0F, 2.0F, 31.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, -0.1309F));

        PartDefinition front = Airship.addOrReplaceChild("front", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -7.0F, 18.0F, 18.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 5.5F, 0.0F, 3.1416F, 0.0F));

        PartDefinition front2 = front.addOrReplaceChild("front2", CubeListBuilder.create().texOffs(0, 23).addBox(-8.0F, -3.0F, -0.5F, 16.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -10.5F, 0.0F, 3.1416F, 0.0F));

        PartDefinition sides = front.addOrReplaceChild("sides", CubeListBuilder.create().texOffs(62, 85).addBox(0.5F, -31.0F, -7.0F, 28.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.0F, 24.0F, -10.5F, 0.0F, -1.5708F, 0.0F));

        PartDefinition sides2 = sides.addOrReplaceChild("sides2", CubeListBuilder.create().texOffs(62, 77).addBox(-13.5F, -3.0F, -1.0F, 28.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.0F, -28.0F, -24.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition base = front.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 77).addBox(-13.5F, -8.0F, -2.0F, 28.0F, 16.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 4.5F, 1.5708F, 1.5708F, 0.0F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float spin = ageInTicks * 0.2F;
        this.Propeller.zRot = spin;
        this.Propeller2.zRot = -spin;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Airship.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
