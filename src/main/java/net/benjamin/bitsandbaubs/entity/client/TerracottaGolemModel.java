package net.benjamin.bitsandbaubs.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.benjamin.bitsandbaubs.entity.animations.ModAnimationDefinitions;
import net.benjamin.bitsandbaubs.entity.custom.TerracottaGolemEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class TerracottaGolemModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart terracotta_golem;
	private final ModelPart head;

	public TerracottaGolemModel(ModelPart root) {
		this.terracotta_golem = root.getChild("terracotta_golem");
		this.head = terracotta_golem.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition terracotta_golem = partdefinition.addOrReplaceChild("terracotta_golem", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition right_leg = terracotta_golem.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-4.0F, 2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -14.0F, 0.0F));

		PartDefinition head = terracotta_golem.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -34.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(24, 0).addBox(-1.0F, -27.0F, -6.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = terracotta_golem.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 20).addBox(-4.0F, -12.0F, -3.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 38).addBox(-4.0F, -12.0F, -3.0F, 8.0F, 18.0F, 6.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, -12.0F, 0.0F));

		PartDefinition left_arm = terracotta_golem.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(44, 22).mirror().addBox(4.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition right_arm = terracotta_golem.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(44, 22).addBox(-4.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -24.0F, 0.0F));

		PartDefinition left_leg = terracotta_golem.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 22).addBox(0.0F, 2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -14.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animateWalk(ModAnimationDefinitions.TERRACOTTA_GOLEM_WALK, limbSwing, limbSwingAmount, 2F, 2.5F);
		this.animate(((TerracottaGolemEntity) entity).idleAnimationState, ModAnimationDefinitions.TERRACOTTA_GOLEM_IDLE, ageInTicks, 1f);
		this.animate(((TerracottaGolemEntity) entity).attackAnimationState, ModAnimationDefinitions.TERRACOTTA_GOLEM_ATTACK, ageInTicks, 1f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		terracotta_golem.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return terracotta_golem;
	}
}