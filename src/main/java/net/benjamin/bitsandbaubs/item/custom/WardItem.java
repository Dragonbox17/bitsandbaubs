package net.benjamin.bitsandbaubs.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class WardItem<T extends Monster> extends Item {
    private int useTime = 0;
    private EntityType<T> monsterToRepel;
    public WardItem(Properties p_41383_, EntityType<T> monsterToRepel) {
        super(p_41383_);
        this.monsterToRepel = monsterToRepel;
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
                    new Vec3(pPlayer.getX() - 5, pPlayer.getY() - 5, pPlayer.getZ() - 5),
                    new Vec3(pPlayer.getX() + 5, pPlayer.getY() + 5, pPlayer.getZ() + 5));

            List<Monster> monsters = pLevel.getEntitiesOfClass(Monster.class, areaAroundPlayer);
            for (Monster monster : monsters) {
                if (monster.getType() == (EntityType<Monster>) monsterToRepel) {
                    double moveX;
                    double moveY;
                    double moveZ;
                    if (monster.getX() > pPlayer.getX()) {
                        moveX = 1;
                    } else {
                        moveX = -1;
                    }
                    if (monster.getY() > pPlayer.getY()) {
                        moveY = 1;
                    } else {
                        moveY = -1;
                    }
                    if (monster.getZ() > pPlayer.getZ()) {
                        moveZ = 1;
                    } else {
                        moveZ = -1;
                    }
                    monster.move(MoverType.SELF, new Vec3(moveX, moveY, moveZ));
                }
            }
        }
        useTime++;
    }
}