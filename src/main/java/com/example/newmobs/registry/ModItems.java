package com.example.newmobs.registry;

import com.example.newmobs.NewMobs;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

import java.util.function.Function;

public class ModItems {
	public static final ResourceKey<Item> STONE_BRUTE_SPAWN_EGG_ID = key("stone_brute_spawn_egg");
	public static final ResourceKey<Item> MOSS_HOPPER_SPAWN_EGG_ID = key("moss_hopper_spawn_egg");

	public static final Item STONE_BRUTE_SPAWN_EGG = register(
			STONE_BRUTE_SPAWN_EGG_ID,
			SpawnEggItem::new,
			new Item.Properties().spawnEgg(ModEntityTypes.STONE_BRUTE)
	);

	public static final Item MOSS_HOPPER_SPAWN_EGG = register(
			MOSS_HOPPER_SPAWN_EGG_ID,
			SpawnEggItem::new,
			new Item.Properties().spawnEgg(ModEntityTypes.MOSS_HOPPER)
	);

	private static ResourceKey<Item> key(String name) {
		return ResourceKey.create(Registries.ITEM, NewMobs.id(name));
	}

	private static Item register(ResourceKey<Item> key, Function<Item.Properties, Item> factory, Item.Properties properties) {
		return Registry.register(BuiltInRegistries.ITEM, key, factory.apply(properties.setId(key)));
	}

	public static void initialize() {
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.SPAWN_EGGS).register(tab -> {
			tab.accept(STONE_BRUTE_SPAWN_EGG);
			tab.accept(MOSS_HOPPER_SPAWN_EGG);
		});
	}
}
