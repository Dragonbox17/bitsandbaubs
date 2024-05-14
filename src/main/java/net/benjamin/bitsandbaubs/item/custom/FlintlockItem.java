package net.benjamin.bitsandbaubs.item.custom;

import net.benjamin.bitsandbaubs.entity.ModEntities;
import net.benjamin.bitsandbaubs.entity.custom.BulletRound;
import net.benjamin.bitsandbaubs.item.ModItems;
import net.benjamin.bitsandbaubs.util.ModTags;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.function.Predicate;

public class FlintlockItem extends ProjectileWeaponItem implements Vanishable {
    private static String TAG_CHARDED = "Charged";
    private static int MAX_CHARGE_DURATION = 30;
    private boolean startSoundPlayed = false;
    private static final float BULLET_POWER = 4F;

    public FlintlockItem(Item.Properties p_40850_) {
        super(p_40850_);
    }

    @Override
    public int getDefaultProjectileRange() {
        return 6;
    }

    public final Predicate<ItemStack> BULLET_ROUNDS_ONLY = (p_43017_) -> {
        return p_43017_.is(ModTags.Items.BULLET_ROUND);
    };

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return BULLET_ROUNDS_ONLY;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pHand);
        if(getCharged(itemStack)) {
            performShooting(pLevel, pPlayer, pHand, itemStack, getShootingPower(), 1.0F);
            setCharged(itemStack, false);
            return InteractionResultHolder.success(itemStack);
        } else if (!pPlayer.getProjectile(itemStack).isEmpty()) {
            this.startSoundPlayed = false;
            pPlayer.startUsingItem(pHand);
            return InteractionResultHolder.consume(itemStack);
        } else {
            return InteractionResultHolder.fail(itemStack);
        }
    }

    private float getShootingPower() {
        return BULLET_POWER;
    }

    @Override
    public void releaseUsing(ItemStack pItemStack, Level pLevel, LivingEntity pEntity, int pTimeLeft) {
        if(!pLevel.isClientSide) {
            int i = this.getUseDuration(pItemStack) - pTimeLeft;
            if (i >= 20 && !getCharged(pItemStack)) {
                setCharged(pItemStack, true);
                SoundSource soundSource = SoundSource.PLAYERS;

                ItemStack item = pEntity.getProjectile(pItemStack);
                if(BULLET_ROUNDS_ONLY.test(item)) {
                    if(!item.isEmpty() && item.getCount() > 0) {
                        item.shrink(1);
                    }
                }

                pLevel.playSound(null, pEntity.getX(), pEntity.getY(), pEntity.getZ(), SoundEvents.CROSSBOW_LOADING_END, soundSource, 1.0F, 1.0F);
            }
        }
    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 30;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.BOW;
    }

    public static boolean getCharged(ItemStack pItemStack) {
        CompoundTag compoundTag = pItemStack.getTag();
        return compoundTag != null && compoundTag.getBoolean("Charged");
    }

    private void setCharged(ItemStack itemStack, boolean b) {
        CompoundTag compoundTag = itemStack.getOrCreateTag();
        compoundTag.putBoolean("Charged", b);
    }

    private void performShooting(Level pLevel, Player pPlayer, InteractionHand pHand, ItemStack pItemStack, float shootingPower, float v) {
        if(!pLevel.isClientSide) {
            BulletRound bulletRound = createBullet(pLevel);

            Vec3 lookVec = pPlayer.getLookAngle();
            Vec3 forward = pPlayer.getEyePosition().add(lookVec);

            bulletRound.setPos(forward.x, forward.y, forward.z);

            bulletRound.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.6F * shootingPower, 1.0F);

            pItemStack.hurtAndBreak(1, pPlayer, (entity) -> entity.broadcastBreakEvent(pHand));

            pLevel.addFreshEntity(bulletRound);

            pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 0.5F, 1.0F);
        }
    }

    @Override
    public void onUseTick(Level pLevel, LivingEntity pEntity, ItemStack pItemStack, int pRemainingTicks) {
        if(!pLevel.isClientSide) {
            SoundEvent soundEvent = SoundEvents.CROSSBOW_LOADING_START;
            float f = (float)((this.getUseDuration(pItemStack) - pRemainingTicks) / 25F);
            if(f < 0.2F) {
                this.startSoundPlayed = false;
            }

            if(f >= 0.2F && !this.startSoundPlayed) {
                this.startSoundPlayed = true;
                pLevel.playSound(null, pEntity.getX(), pEntity.getY(), pEntity.getZ(), soundEvent, SoundSource.PLAYERS, 0.5F, 1.0F);
            }
        }
    }

    public BulletRound createBullet(Level p_40513_) {
        BulletRound bulletCreated = new BulletRound(ModEntities.BULLET_ROUND.get(), p_40513_);
        return bulletCreated;
    }

    @Override
    public boolean useOnRelease(ItemStack pItemStack) {
        return true;
    }
}
