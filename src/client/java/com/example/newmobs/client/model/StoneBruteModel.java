package com.example.newmobs.client.model;

import com.example.newmobs.client.state.StoneBruteRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

/**
 * โมเดลของ Stone Brute (texture ขนาด 64x64)
 * หมายเหตุ: ในโมเดล ค่า Y ยิ่งมาก = ยิ่งอยู่ต่ำ (กลับกับพิกัดในเกม) เท้าอยู่ที่ y = 24
 */
public class StoneBruteModel extends EntityModel<StoneBruteRenderState> {
	private static final String HEAD = "head";
	private static final String BODY = "body";
	private static final String RIGHT_ARM = "right_arm";
	private static final String LEFT_ARM = "left_arm";
	private static final String RIGHT_LEG = "right_leg";
	private static final String LEFT_LEG = "left_leg";

	private final ModelPart head;
	private final ModelPart rightArm;
	private final ModelPart leftArm;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;

	public StoneBruteModel(ModelPart root) {
		super(root);
		this.head = root.getChild(HEAD);
		this.rightArm = root.getChild(RIGHT_ARM);
		this.leftArm = root.getChild(LEFT_ARM);
		this.rightLeg = root.getChild(RIGHT_LEG);
		this.leftLeg = root.getChild(LEFT_LEG);
	}

	public static LayerDefinition createLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();

		root.addOrReplaceChild(HEAD,
				CubeListBuilder.create().texOffs(0, 0).addBox(-4, -8, -4, 8, 8, 8),
				PartPose.offset(0, 2, 0));

		root.addOrReplaceChild(BODY,
				CubeListBuilder.create().texOffs(0, 16).addBox(-6, 0, -3, 12, 12, 6),
				PartPose.offset(0, 2, 0));

		root.addOrReplaceChild(RIGHT_ARM,
				CubeListBuilder.create().texOffs(40, 16).addBox(-2.5f, -2, -2.5f, 5, 14, 5),
				PartPose.offset(-8.5f, 4, 0));

		root.addOrReplaceChild(LEFT_ARM,
				CubeListBuilder.create().texOffs(40, 16).addBox(-2.5f, -2, -2.5f, 5, 14, 5),
				PartPose.offset(8.5f, 4, 0));

		root.addOrReplaceChild(RIGHT_LEG,
				CubeListBuilder.create().texOffs(0, 36).addBox(-2.5f, 0, -2.5f, 5, 10, 5),
				PartPose.offset(-3, 14, 0));

		root.addOrReplaceChild(LEFT_LEG,
				CubeListBuilder.create().texOffs(0, 36).addBox(-2.5f, 0, -2.5f, 5, 10, 5),
				PartPose.offset(3, 14, 0));

		return LayerDefinition.create(mesh, 64, 64);
	}

	@Override
	public void setupAnim(StoneBruteRenderState state) {
		super.setupAnim(state);

		// หัวหันตามทิศที่มอนมอง (state เก็บเป็นองศา โมเดลใช้เรเดียน)
		this.head.xRot = state.xRot * Mth.DEG_TO_RAD;
		this.head.yRot = state.yRot * Mth.DEG_TO_RAD;

		// แกว่งแขน-ขาตามการเดิน
		float amplitude = state.walkAnimationSpeed;
		float progress = state.walkAnimationPos;
		this.rightLeg.xRot = Mth.cos(progress * 0.6662f) * 1.2f * amplitude;
		this.leftLeg.xRot = Mth.cos(progress * 0.6662f + Mth.PI) * 1.2f * amplitude;
		this.rightArm.xRot = Mth.cos(progress * 0.6662f + Mth.PI) * 0.9f * amplitude;
		this.leftArm.xRot = Mth.cos(progress * 0.6662f) * 0.9f * amplitude;
	}
}
