package com.example.newmobs.client.model;

import com.example.newmobs.NewMobs;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class ModModelLayers {
	public static final ModelLayerLocation STONE_BRUTE = createMain("stone_brute");
	public static final ModelLayerLocation MOSS_HOPPER = createMain("moss_hopper");

	private static ModelLayerLocation createMain(String name) {
		return new ModelLayerLocation(NewMobs.id(name), "main");
	}

	public static void registerModelLayers() {
		ModelLayerRegistry.registerModelLayer(STONE_BRUTE, StoneBruteModel::createLayer);
		ModelLayerRegistry.registerModelLayer(MOSS_HOPPER, MossHopperModel::createLayer);
	}
}
