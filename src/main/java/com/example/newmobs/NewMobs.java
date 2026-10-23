package com.example.newmobs;

import com.example.newmobs.registry.ModEntityTypes;
import com.example.newmobs.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.MobCategory;

public class NewMobs implements ModInitializer {
	public static final String MOD_ID = "newmobs";

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		ModEntityTypes.initialize();
		ModItems.initialize();
		registerNaturalSpawns();
	}

	/**
	 * ทำให้มอนเกิดเองตามธรรมชาติ
	 * addSpawn(ตัวเลือกไบโอม, ประเภทมอน, ชนิดเอนทิตี, น้ำหนัก, ขนาดฝูงต่ำสุด, ขนาดฝูงสูงสุด)
	 * น้ำหนักยิ่งมาก = เกิดบ่อยขึ้น (เทียบ: Zombie = 95, Cow = 8)
	 */
	private static void registerNaturalSpawns() {
		// Stone Brute: มอนสเตอร์หายาก เกิดในโอเวอร์เวิลด์ (ตอนมืด ตามกฎของ Monster)
		BiomeModifications.addSpawn(
				BiomeSelectors.foundInOverworld(),
				MobCategory.MONSTER,
				ModEntityTypes.STONE_BRUTE,
				10, 1, 1
		);

		// Moss Hopper: สัตว์เชื่อง เกิดในป่าและป่าดิบชื้น
		BiomeModifications.addSpawn(
				BiomeSelectors.tag(BiomeTags.IS_FOREST).or(BiomeSelectors.tag(BiomeTags.IS_JUNGLE)),
				MobCategory.CREATURE,
				ModEntityTypes.MOSS_HOPPER,
				8, 2, 4
		);
	}
}
