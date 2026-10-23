package com.example.newmobs.client.renderer;

import com.example.newmobs.NewMobs;
import com.example.newmobs.client.model.ModModelLayers;
import com.example.newmobs.client.model.MossHopperModel;
import com.example.newmobs.client.state.MossHopperRenderState;
import com.example.newmobs.entity.MossHopperEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class MossHopperRenderer extends MobRenderer<MossHopperEntity, MossHopperRenderState, MossHopperModel> {
	private static final Identifier TEXTURE = NewMobs.id("textures/entity/moss_hopper.png");

	public MossHopperRenderer(EntityRendererProvider.Context context) {
		super(context, new MossHopperModel(context.bakeLayer(ModModelLayers.MOSS_HOPPER)), 0.3f);
	}

	@Override
	public MossHopperRenderState createRenderState() {
		return new MossHopperRenderState();
	}

	@Override
	public Identifier getTextureLocation(MossHopperRenderState state) {
		return TEXTURE;
	}
}
