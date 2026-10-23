package com.example.newmobs.registry;

import com.example.newmobs.NewMobs;
import com.example.newmobs.entity.MossHopperEntity;
import com.example.newmobs.entity.StoneBruteEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;

public class ModEntityTypes {
	public static final ResourceKey<EntityType<?>> STONE_BRUTE_ID = key("stone_brute");
	public static final ResourceKey<EntityType<?>> MOSS_HOPPER_ID = key("moss_hopper");

	public static final EntityType<StoneBruteEntity> STONE_BRUTE = register(
			STONE_BRUTE_ID,
			FabricEntityType.Builder.<StoneBruteEntity>createMob(
							StoneBruteEntity::new,
							MobCategory.MONSTER,
							mob -> mob
									.defaultAttributes(StoneBruteEntity::createAttributes)
									.spawnPlacement(
											SpawnPlacementTypes.ON_GROUND,
											Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
											Monster::checkMonsterSpawnRules
									)
					)
					.sized(0.9f, 2.0f)
					.clientTrackingRange(8)
	);

	public static final EntityType<MossHopperEntity> MOSS_HOPPER = register(
			MOSS_HOPPER_ID,
			FabricEntityType.Builder.<MossHopperEntity>createMob(
							MossHopperEntity::new,
							MobCategory.CREATURE,
							mob -> mob
									.defaultAttributes(MossHopperEntity::createAttributes)
									.spawnPlacement(
											SpawnPlacementTypes.ON_GROUND,
											Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
											MossHopperEntity::checkHopperSpawnRules
									)
					)
					.sized(0.6f, 0.9f)
					.clientTrackingRange(8)
	);

	private static ResourceKey<EntityType<?>> key(String name) {
		return ResourceKey.create(Registries.ENTITY_TYPE, NewMobs.id(name));
	}

	private static <T extends Entity> EntityType<T> register(ResourceKey<EntityType<?>> key, EntityType.Builder<T> builder) {
		return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
	}

	/** เรียกเพื่อให้คลาสนี้โหลด และลงทะเบียนเอนทิตีทั้งหมด */
	public static void initialize() {
	}
}
