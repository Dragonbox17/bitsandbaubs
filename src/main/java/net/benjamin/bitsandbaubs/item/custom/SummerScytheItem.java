package net.benjamin.bitsandbaubs.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;

public class SummerScytheItem extends ScytheItem{
    public SummerScytheItem(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }

    @Override
    public InteractionResult useOn(UseOnContext pUseContext) {
        if(!pUseContext.getLevel().isClientSide && pUseContext.getHand() == InteractionHand.MAIN_HAND) {
            BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
            BlockPos blockPos = pUseContext.getClickedPos();
            Level level = pUseContext.getLevel();
            Player player = pUseContext.getPlayer();
            for(int x = blockPos.getX() - 1; x <= blockPos.getX() + 1; x++) {
                for(int z = blockPos.getZ() - 1; z <= blockPos.getZ() + 1; z++) {
                    mutablePos.set(x, blockPos.getY(), z);
                    BlockState blockState = level.getBlockState(mutablePos);
                    Block block = blockState.getBlock();

                    if(block instanceof CropBlock) {
                        int age = ((CropBlock) block).getAge(blockState);
                        if(age >= ((CropBlock) block).getMaxAge()) {
                            consumeSeed(player, (CropBlock) block);

                            block.playerDestroy(level, player,
                                    mutablePos, blockState,
                                    level.getBlockEntity(mutablePos),
                                    player.getUseItem());

                            level.setBlock(mutablePos, block.defaultBlockState(), 3);
                        }
                        else {
                            bonemealBlock(level, mutablePos, player);
                        }
                    }
                }
            }
            player.getItemInHand(InteractionHand.MAIN_HAND).hurtAndBreak(1, player, (pItem) -> {
                pItem.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });

            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    public void bonemealBlock(Level pLevel, BlockPos pBlockPos, Player pPlayer) {
        BlockState blockState = pLevel.getBlockState(pBlockPos);
        Block block = blockState.getBlock();
        if (((BonemealableBlock)block).isBonemealSuccess(pLevel, pLevel.random, pBlockPos, blockState)) {
            ((BonemealableBlock)block).performBonemeal((ServerLevel)pLevel, pLevel.random, pBlockPos, blockState);
        }
    }

    public void consumeSeed(Player player, CropBlock crop) {
        Inventory inventory = player.getInventory();
        for(int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if(crop.equals(Blocks.WHEAT) && stack.getItem() == Items.WHEAT_SEEDS) {
                stack.shrink(1);
            } else if (crop.equals(Blocks.POTATOES) && stack.getItem() == Items.POTATO) {
                stack.shrink(1);
            } else if (crop.equals(Blocks.CARROTS) && stack.getItem() == Items.CARROT) {
                stack.shrink(1);
            } else if (crop.equals(Blocks.BEETROOTS) && stack.getItem() == Items.BEETROOT) {
                stack.shrink(1);
            }
        }
    }

}
