package net.benjamin.bitsandbaubs.item.custom;

import net.benjamin.bitsandbaubs.entity.ModEntities;
import net.benjamin.bitsandbaubs.entity.custom.TerracottaGolemEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class EmeraldStaffItem extends Item implements Vanishable {
    private int useTime = 30;

    public EmeraldStaffItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        if (!pLevel.isClientSide && useTime >= 30 && pHand == InteractionHand.MAIN_HAND) {
            summonGolem(pLevel, pPlayer, 10, 5F, 5F, 0);

            useTime = 0;

            pPlayer.getItemInHand(pHand).hurtAndBreak(1, pPlayer, (pItem) -> {
                pItem.broadcastBreakEvent(pHand);
            });

            return InteractionResultHolder.success(pPlayer.getItemInHand(pHand));
        }

        return super.use(pLevel, pPlayer, pHand);
    }

    private static void summonGolem(Level pLevel, LivingEntity pPlayer, float p_40900_, float p_40902_, float p_40903_, float p_40904_) {
        if (!pLevel.isClientSide) {
            TerracottaGolemEntity golem = new TerracottaGolemEntity(ModEntities.TERRACOTTA_GOLEM.get(), pLevel);

            Vec3 lookVec = pPlayer.getLookAngle();
            Vec3 forward = pPlayer.getEyePosition().add(lookVec);
            golem.absMoveTo(forward.x, forward.y, forward.z);

            pLevel.addFreshEntity(golem);
            pLevel.playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.ANVIL_BREAK, SoundSource.PLAYERS, 1.0F, p_40900_);
        }
    }

    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        super.inventoryTick(p_41404_, p_41405_, p_41406_, p_41407_, p_41408_);
        useTime ++;
    }
}
