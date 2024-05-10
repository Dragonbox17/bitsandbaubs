package net.benjamin.bitsandbaubs.item.custom;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.List;

public class JadeStaffItem extends Item {
    private int useTime = 30;

    public JadeStaffItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        if (!pLevel.isClientSide && useTime >= 30) {
            shootProjectile(pLevel, pPlayer, 10, 5F, 5F, 0);

            useTime = 0;

            return InteractionResultHolder.success(pPlayer.getItemInHand(pHand));
        }

        return super.use(pLevel, pPlayer, pHand);
    }

    private static void shootProjectile(Level p_40895_, LivingEntity p_40896_, float p_40900_, float p_40902_, float p_40903_, float p_40904_) {
        if (!p_40895_.isClientSide) {
            Projectile projectile = new LargeFireball(p_40895_, p_40896_, 0, 0, 0, 0);
            projectile.absMoveTo(p_40896_.getX(), p_40896_.getY() + 2, p_40896_.getZ());
            projectile.setDeltaMovement(p_40896_.getForward());

            Vec3 vec31 = p_40896_.getUpVector(3.0F);
            Quaternionf quaternionf = (new Quaternionf()).setAngleAxis((double)(p_40904_), vec31.x, vec31.y, vec31.z);
            Vec3 vec3 = p_40896_.getViewVector(1.0F);
            Vector3f vector3f = vec3.toVector3f().rotate(quaternionf);
            projectile.shoot((double)vector3f.x(), (double)vector3f.y(), (double)vector3f.z(), p_40902_, p_40903_);
            p_40895_.addFreshEntity(projectile);
            p_40895_.playSound((Player)null, p_40896_.getX(), p_40896_.getY(), p_40896_.getZ(), SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 1.0F, p_40900_);
        }
    }

    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        super.inventoryTick(p_41404_, p_41405_, p_41406_, p_41407_, p_41408_);
        useTime ++;
    }
}
