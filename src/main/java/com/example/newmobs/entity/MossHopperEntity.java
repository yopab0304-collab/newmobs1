package com.example.newmobs.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

/**
 * Moss Hopper - สัตว์ตัวเล็กปกคลุมด้วยตะไคร่ ใจดี ตกใจง่าย ถือแครอทล่อให้เดินตามได้
 */
public class MossHopperEntity extends PathfinderMob {
	public MossHopperEntity(EntityType<? extends MossHopperEntity> entityType, Level level) {
		super(entityType, level);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return PathfinderMob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 8.0)
				.add(Attributes.MOVEMENT_SPEED, 0.25)
				.add(Attributes.TEMPT_RANGE, 10.0);
	}

	/** เกิดบนบล็อกที่สัตว์เกิดได้ (หญ้า ฯลฯ) และต้องมีแสงสว่างพอ */
	public static boolean checkHopperSpawnRules(EntityType<MossHopperEntity> type, ServerLevelAccessor level,
			EntitySpawnReason reason, BlockPos pos, RandomSource random) {
		return level.getBlockState(pos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON)
				&& level.getRawBrightness(pos, 0) > 8;
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.5));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.0, Ingredient.of(Items.CARROT), false));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0f));
		this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.FROG_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource) {
		return SoundEvents.FROG_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.FROG_DEATH;
	}
}
