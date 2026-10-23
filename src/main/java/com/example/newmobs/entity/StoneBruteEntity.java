package com.example.newmobs.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Stone Brute - มอนสเตอร์หินตัวใหญ่ เลือดเยอะ ตีแรง เดินช้า ไม่ค่อยสะเทือนเวลาโดนตี
 */
public class StoneBruteEntity extends Monster {
	public StoneBruteEntity(EntityType<? extends StoneBruteEntity> entityType, Level level) {
		super(entityType, level);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 40.0)
				.add(Attributes.MOVEMENT_SPEED, 0.23)
				.add(Attributes.ATTACK_DAMAGE, 6.0)
				.add(Attributes.ARMOR, 4.0)
				.add(Attributes.FOLLOW_RANGE, 32.0)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.6);
	}

	@Override
	protected void registerGoals() {
		// เลข priority ยิ่งน้อยยิ่งสำคัญ
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0, false));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0f));
		this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

		// เป้าหมาย: สู้กลับคนที่ตี และไล่ล่าผู้เล่น
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource) {
		return SoundEvents.IRON_GOLEM_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.IRON_GOLEM_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		this.playSound(SoundEvents.IRON_GOLEM_STEP, 1.0f, 1.0f);
	}
}
