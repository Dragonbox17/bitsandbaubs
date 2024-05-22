package net.benjamin.bitsandbaubs.entity.custom;

import net.benjamin.bitsandbaubs.entity.ai.TerracottaGolemAttackGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class CullagerEntity extends SpellcasterIllager {
    public CullagerEntity(EntityType<? extends SpellcasterIllager> entityType, Level pLevel) {
        super(entityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new SpellcasterCastingSpellGoal());
        this.goalSelector.addGoal(1, new CullagerEntity.CullagerBlindnessSpellGoal());
        this.goalSelector.addGoal(2, new CullagerEntity.CullagerAttackSpellGoal());
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 0.6D, 1.0D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Mob.class, 8.0F));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, Raider.class)).setAlertOthers());
        this.targetSelector.addGoal(2, (new NearestAttackableTargetGoal<>(this, Player.class, true)).setUnseenMemoryTicks(300));
        this.targetSelector.addGoal(3, (new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false)).setUnseenMemoryTicks(300));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, false));
    }

    @Override
    protected SoundEvent getCastingSoundEvent() {
        return SoundEvents.EVOKER_CAST_SPELL;
    }

    @Override
    protected void updateWalkAnimation(float p_268283_) {
        float f;
        if(this.getPose() == Pose.STANDING) {
            f = Math.min(p_268283_ * 5, 1F);
        }
        else {
            f = 0F;
        }

        this.walkAnimation.update(f, 0.2F);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    @Override
    public void applyRaidBuffs(int p_37844_, boolean p_37845_) {

    }

    @Override
    public SoundEvent getCelebrateSound() {
        return null;
    }


    @Override
    public int getExperienceReward() {
        return 1 + random.nextInt(15);
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.EVOKER_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_27517_) {
        return SoundEvents.EVOKER_HURT;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 24D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ARMOR_TOUGHNESS, 0.05D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 0.0D)
                .add(Attributes.ATTACK_DAMAGE, 1D);
    }

    class CullagerAttackSpellGoal extends SpellcasterIllager.SpellcasterUseSpellGoal {
        protected int getCastingTime() {
            return 40;
        }

        protected int getCastingInterval() {
            return 100;
        }

        protected void performSpellCasting() {
            LivingEntity livingentity = CullagerEntity.this.getTarget();
            double d0 = Math.min(livingentity.getY(), CullagerEntity.this.getY());
            double d1 = Math.max(livingentity.getY(), CullagerEntity.this.getY()) + 1.0D;
            float f = (float) Mth.atan2(livingentity.getZ() - CullagerEntity.this.getZ(), livingentity.getX() - CullagerEntity.this.getX());
            if (CullagerEntity.this.distanceToSqr(livingentity) < 9.0D) {
                for(int i = 0; i < 5; ++i) {
                    float f1 = f + (float)i * (float)Math.PI * 0.4F;
                    this.createSpellEntity(CullagerEntity.this.getX() + (double)Mth.cos(f1) * 1.5D, CullagerEntity.this.getZ() + (double)Mth.sin(f1) * 1.5D, d0, d1, f1, 0);
                }

                for(int k = 0; k < 8; ++k) {
                    float f2 = f + (float)k * (float)Math.PI * 2.0F / 8.0F + 1.2566371F;
                    this.createSpellEntity(CullagerEntity.this.getX() + (double)Mth.cos(f2) * 2.5D, CullagerEntity.this.getZ() + (double)Mth.sin(f2) * 2.5D, d0, d1, f2, 3);
                }
            } else {
                for(int l = 0; l < 16; ++l) {
                    double d2 = 1.25D * (double)(l + 1);
                    int j = 1 * l;
                    this.createSpellEntity(CullagerEntity.this.getX() + (double)Mth.cos(f) * d2, CullagerEntity.this.getZ() + (double)Mth.sin(f) * d2, d0, d1, f, j);
                }
            }
            setIsCastingSpell(IllagerSpell.NONE);
        }

        private void createSpellEntity(double p_32673_, double p_32674_, double p_32675_, double p_32676_, float p_32677_, int p_32678_) {
            BlockPos blockpos = BlockPos.containing(p_32673_, p_32676_, p_32674_);
            boolean flag = false;
            double d0 = 0.0D;

            do {
                BlockPos blockpos1 = blockpos.below();
                BlockState blockstate = CullagerEntity.this.level().getBlockState(blockpos1);
                if (blockstate.isFaceSturdy(CullagerEntity.this.level(), blockpos1, Direction.UP)) {
                    if (!CullagerEntity.this.level().isEmptyBlock(blockpos)) {
                        BlockState blockstate1 = CullagerEntity.this.level().getBlockState(blockpos);
                        VoxelShape voxelshape = blockstate1.getCollisionShape(CullagerEntity.this.level(), blockpos);
                        if (!voxelshape.isEmpty()) {
                            d0 = voxelshape.max(Direction.Axis.Y);
                        }
                    }

                    flag = true;
                    break;
                }

                blockpos = blockpos.below();
            } while(blockpos.getY() >= Mth.floor(p_32675_) - 1);

            if (flag) {
                CullagerEntity.this.level().addFreshEntity(new EvokerFangs(CullagerEntity.this.level(), p_32673_, (double)blockpos.getY() + d0, p_32674_, p_32677_, p_32678_, CullagerEntity.this));
            }

        }

        protected SoundEvent getSpellPrepareSound() {
            return SoundEvents.EVOKER_PREPARE_ATTACK;
        }

        protected SpellcasterIllager.IllagerSpell getSpell() {
            return SpellcasterIllager.IllagerSpell.FANGS;
        }
    }

    class CullagerBlindnessSpellGoal extends SpellcasterIllager.SpellcasterUseSpellGoal {
        private int lastTargetId;

        public boolean canUse() {
            if (!super.canUse()) {
                return false;
            } else if (CullagerEntity.this.getTarget() == null) {
                return false;
            } else if (CullagerEntity.this.getTarget().getId() == this.lastTargetId) {
                return false;
            } else {
                return CullagerEntity.this.level().getCurrentDifficultyAt(CullagerEntity.this.blockPosition()).isHarderThan((float) Difficulty.PEACEFUL.ordinal());
            }
        }

        public void start() {
            super.start();
            LivingEntity livingentity = CullagerEntity.this.getTarget();
            if (livingentity != null) {
                this.lastTargetId = livingentity.getId();
            }

        }

        protected int getCastingTime() {
            return 20;
        }

        protected int getCastingInterval() {
            return 180;
        }

        protected void performSpellCasting() {
            CullagerEntity.this.getTarget().addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 400), CullagerEntity.this);
            setIsCastingSpell(IllagerSpell.NONE);
        }

        protected SoundEvent getSpellPrepareSound() {
            return SoundEvents.ILLUSIONER_PREPARE_BLINDNESS;
        }

        protected SpellcasterIllager.IllagerSpell getSpell() {
            return SpellcasterIllager.IllagerSpell.BLINDNESS;
        }
    }
}
