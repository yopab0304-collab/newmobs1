package com.example.newmobs.client;

import com.example.newmobs.client.model.ModModelLayers;
import com.example.newmobs.client.renderer.MossHopperRenderer;
import com.example.newmobs.client.renderer.StoneBruteRenderer;
import com.example.newmobs.registry.ModEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class NewMobsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModModelLayers.registerModelLayers();
		EntityRenderers.register(ModEntityTypes.STONE_BRUTE, StoneBruteRenderer::new);
		EntityRenderers.register(ModEntityTypes.MOSS_HOPPER, MossHopperRenderer::new);
	}
}
