package net.benjamin.bitsandbaubs.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.benjamin.bitsandbaubs.entity.animations.ModAnimationDefinitions;
import net.benjamin.bitsandbaubs.entity.custom.FangBeastEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class FangBeastModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart Fang_beast;
	private final ModelPart Left_leg;
	private final ModelPart Right_leg;
	private final ModelPart Body;
	private final ModelPart Head;
	private final ModelPart Left_arm;
	private final ModelPart Right_arm;

	public FangBeastModel(ModelPart root) {
		this.Fang_beast = root;
		this.Left_leg = root.getChild("Left_leg");
		this.Right_leg = root.getChild("Right_leg");
		this.Body = root.getChild("Body");
		this.Head = root.getChild("Head");
		this.Left_arm = root.getChild("Left_arm");
		this.Right_arm = root.getChild("Right_arm");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Left_leg = partdefinition.addOrReplaceChild("Left_leg", CubeListBuilder.create().texOffs(36, 38).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 15.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 9.0F, 0.0F));

		PartDefinition Right_leg = partdefinition.addOrReplaceChild("Right_leg", CubeListBuilder.create().texOffs(20, 38).addBox(0.0F, 0.0F, -2.0F, 4.0F, 15.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 9.0F, 0.0F));

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 15).addBox(-3.0F, -29.0F, -2.0F, 8.0F, 14.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -30.0F, -1.0F, 14.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 24.0F, -1.0F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(50, 10).addBox(-6.0F, -2.0F, -7.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(42, 0).addBox(-6.0F, -1.0F, -13.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -3.0F, -1.0F));

		PartDefinition Left_arm = partdefinition.addOrReplaceChild("Left_arm", CubeListBuilder.create().texOffs(0, 36).addBox(0.0F, 0.0F, -2.5F, 5.0F, 18.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, -4.0F, 1.5F));

		PartDefinition Right_arm = partdefinition.addOrReplaceChild("Right_arm", CubeListBuilder.create().texOffs(30, 15).mirror().addBox(-5.0F, 0.0F, -2.5F, 5.0F, 18.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-7.0F, -4.0F, 1.5F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animateWalk(ModAnimationDefinitions.FANG_BEAST_WALK, limbSwing, limbSwingAmount, 2F, 2.5F);
		this.animate(((FangBeastEntity) entity).idleAnimationState, ModAnimationDefinitions.TERRACOTTA_GOLEM_IDLE, ageInTicks, 1f);
		this.animate(((FangBeastEntity) entity).attackAnimationState, ModAnimationDefinitions.FANG_BEAST_ATTACK, ageInTicks, 1f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return this.Fang_beast;
	}
}