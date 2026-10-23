package com.example.newmobs.client.model;

import com.example.newmobs.client.state.MossHopperRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

/**
 * โมเดลของ Moss Hopper (texture ขนาด 64x32) เท้าอยู่ที่ y = 24
 */
public class MossHopperModel extends EntityModel<MossHopperRenderState> {
	private static final String HEAD = "head";
	private static final String BODY = "body";
	private static final String LEFT_EAR = "left_ear";
	private static final String RIGHT_EAR = "right_ear";
	private static final String FRONT_LEFT_LEG = "front_left_leg";
	private static final String FRONT_RIGHT_LEG = "front_right_leg";
	private static final String HIND_LEFT_LEG = "hind_left_leg";
	private static final String HIND_RIGHT_LEG = "hind_right_leg";

	private final ModelPart head;
	private final ModelPart frontLeftLeg;
	private final ModelPart frontRightLeg;
	private final ModelPart hindLeftLeg;
	private final ModelPart hindRightLeg;

	public MossHopperModel(ModelPart root) {
		super(root);
		this.head = root.getChild(HEAD);
		this.frontLeftLeg = root.getChild(FRONT_LEFT_LEG);
		this.frontRightLeg = root.getChild(FRONT_RIGHT_LEG);
		this.hindLeftLeg = root.getChild(HIND_LEFT_LEG);
		this.hindRightLeg = root.getChild(HIND_RIGHT_LEG);
	}

	public static LayerDefinition createLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();

		root.addOrReplaceChild(BODY,
				CubeListBuilder.create().texOffs(0, 12).addBox(-4, 0, -5, 8, 6, 10),
				PartPose.offset(0, 14, 0));

		root.addOrReplaceChild(HEAD,
				CubeListBuilder.create().texOffs(0, 0).addBox(-3, -4, -5, 6, 6, 6),
				PartPose.offset(0, 16, -4));

		root.addOrReplaceChild(LEFT_EAR,
				CubeListBuilder.create().texOffs(48, 0).addBox(-1, -4, -0.5f, 2, 4, 1),
				PartPose.offset(1.8f, 12, -5));

		root.addOrReplaceChild(RIGHT_EAR,
				CubeListBuilder.create().texOffs(48, 0).addBox(-1, -4, -0.5f, 2, 4, 1),
				PartPose.offset(-1.8f, 12, -5));

		CubeListBuilder frontLeg = CubeListBuilder.create().texOffs(36, 0).addBox(-1.5f, 0, -1.5f, 3, 4, 3);
		CubeListBuilder hindLeg = CubeListBuilder.create().texOffs(36, 8).addBox(-1.5f, 0, -1.5f, 3, 4, 3);

		root.addOrReplaceChild(FRONT_LEFT_LEG, frontLeg, PartPose.offset(2.5f, 20, -3.5f));
		root.addOrReplaceChild(FRONT_RIGHT_LEG, frontLeg, PartPose.offset(-2.5f, 20, -3.5f));
		root.addOrReplaceChild(HIND_LEFT_LEG, hindLeg, PartPose.offset(2.5f, 20, 3.5f));
		root.addOrReplaceChild(HIND_RIGHT_LEG, hindLeg, PartPose.offset(-2.5f, 20, 3.5f));

		return LayerDefinition.create(mesh, 64, 32);
	}

	@Override
	public void setupAnim(MossHopperRenderState state) {
		super.setupAnim(state);

		this.head.xRot = state.xRot * Mth.DEG_TO_RAD;
		this.head.yRot = state.yRot * Mth.DEG_TO_RAD;

		// ท่ากระโดด: ขาหน้าและขาหลังแกว่งสวนกัน ถี่กว่ามอนทั่วไป
		float amplitude = state.walkAnimationSpeed;
		float progress = state.walkAnimationPos;
		float swing = Mth.cos(progress * 0.9f) * 1.1f * amplitude;
		this.frontLeftLeg.xRot = swing;
		this.frontRightLeg.xRot = swing;
		this.hindLeftLeg.xRot = -swing;
		this.hindRightLeg.xRot = -swing;
	}
}
