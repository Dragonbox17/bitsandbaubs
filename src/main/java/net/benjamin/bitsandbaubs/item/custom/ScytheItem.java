package net.benjamin.bitsandbaubs.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class ScytheItem extends HoeItem {
    public ScytheItem(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }

    @Override
    public float getDestroySpeed(ItemStack p_43288_, BlockState p_43289_) {
        if (p_43289_.is(Blocks.GRASS)
                || p_43289_.getBlock() instanceof CropBlock
                || p_43289_.getBlock() instanceof LeavesBlock
                || p_43289_.getBlock() instanceof FlowerBlock) {
            return 15.0F;
        } else {
            return p_43289_.is(BlockTags.SWORD_EFFICIENT) ? 1.5F : 1.0F;
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext pUseContext) {
        return InteractionResult.PASS;
    }

    @Override
    public boolean isCorrectToolForDrops(BlockState p_43298_) {
        return (p_43298_.getBlock() instanceof TallGrassBlock
                || p_43298_.getBlock() instanceof CropBlock
                || p_43298_.getBlock() instanceof LeavesBlock
                || p_43298_.getBlock() instanceof FlowerBlock);
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pBlockPos, LivingEntity pEntity) {
        if(pEntity instanceof Player && isCorrectToolForDrops(pState)) {
            BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
            for(int x = pBlockPos.getX() - 2; x <= pBlockPos.getX() + 2; x++) {
                for(int z = pBlockPos.getZ() - 2; z <= pBlockPos.getZ() + 2; z++) {
                    mutablePos.set(x, pBlockPos.getY(), z);
                    BlockState blockState = pLevel.getBlockState(mutablePos);
                    Block block = blockState.getBlock();
                    if(block instanceof TallGrassBlock || block instanceof FlowerBlock ||
                            block instanceof TallFlowerBlock || block instanceof LeavesBlock) {
                        block.playerDestroy(pLevel, (Player) pEntity,
                                mutablePos, blockState,
                                pLevel.getBlockEntity(mutablePos),
                                pEntity.getUseItem());

                        pLevel.setBlock(mutablePos, Blocks.AIR.defaultBlockState(), 3);

                        pEntity.getItemInHand(InteractionHand.MAIN_HAND).hurtAndBreak(2, pEntity, (pItem) -> {
                            pItem.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                        });
                    }
                }
            }
        }

        return super.mineBlock(pStack, pLevel, pState, pBlockPos, pEntity);
    }
}
