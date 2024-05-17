package net.benjamin.bitsandbaubs.item.custom;


import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class WinterScytheItem extends ScytheItem{
    private int useTime = 0;
    public WinterScytheItem(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        if(!pLevel.isClientSide) {
            useTime = 0;
            pPlayer.getItemInHand(pHand).hurtAndBreak(1, pPlayer, (pItem) -> {
                pItem.broadcastBreakEvent(pHand);
            });
            return InteractionResultHolder.success(pPlayer.getItemInHand(pHand));
        }
        else {
            return InteractionResultHolder.fail(pPlayer.getItemInHand(pHand));
        }
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pPlayer, int p_41407_, boolean p_41408_) {
        super.inventoryTick(pStack, pLevel, pPlayer, p_41407_, p_41408_);
        if (!pLevel.isClientSide && useTime < 500) {
            AABB areaAroundPlayer = new AABB(
                    new Vec3(pPlayer.getX() - 3, pPlayer.getY() - 3, pPlayer.getZ() - 3),
                    new Vec3(pPlayer.getX() + 3, pPlayer.getY() + 3, pPlayer.getZ() + 3));

            List<Monster> monsters = pLevel.getEntitiesOfClass(Monster.class, areaAroundPlayer);
            for (Monster monster : monsters) {
                monster.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 3));
            }
        }
        else if (useTime < 500){
            for(int x = 0; x <= 8; x++) {
                double offsetX = (pLevel.random.nextDouble() - 0.5) * 6;
                double offsetY = (pLevel.random.nextDouble() - 0.5) * 6;
                double offsetZ = (pLevel.random.nextDouble() - 0.5) * 6;
                pLevel.addParticle(ParticleTypes.SNOWFLAKE,
                        pPlayer.getX() + offsetX,
                        pPlayer.getY() + offsetY,
                        pPlayer.getZ() + offsetZ,
                        0.0, -0.02, 0.0);
            }
        }
        useTime++;
    }
}
