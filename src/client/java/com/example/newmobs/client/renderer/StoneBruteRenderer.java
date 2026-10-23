package com.example.newmobs.client.renderer;

import com.example.newmobs.NewMobs;
import com.example.newmobs.client.model.ModModelLayers;
import com.example.newmobs.client.model.StoneBruteModel;
import com.example.newmobs.client.state.StoneBruteRenderState;
import com.example.newmobs.entity.StoneBruteEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class StoneBruteRenderer extends MobRenderer<StoneBruteEntity, StoneBruteRenderState, StoneBruteModel> {
	private static final Identifier TEXTURE = NewMobs.id("textures/entity/stone_brute.png");

	public StoneBruteRenderer(EntityRendererProvider.Context context) {
		super(context, new StoneBruteModel(context.bakeLayer(ModModelLayers.STONE_BRUTE)), 0.6f); // รัศมีเงา
	}

	@Override
	public StoneBruteRenderState createRenderState() {
		return new StoneBruteRenderState();
	}

	@Override
	public Identifier getTextureLocation(StoneBruteRenderState state) {
		return TEXTURE;
	}
}
