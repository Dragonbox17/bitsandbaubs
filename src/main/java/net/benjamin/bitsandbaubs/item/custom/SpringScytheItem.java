package net.benjamin.bitsandbaubs.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
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
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import java.util.function.Consumer;

public class SpringScytheItem extends ScytheItem{
    public SpringScytheItem(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }

    @Override
    public InteractionResult useOn(UseOnContext pUseContext) {
        if(!pUseContext.getLevel().isClientSide && pUseContext.getHand() == InteractionHand.MAIN_HAND) {
            BlockState blockState = pUseContext.getLevel().getBlockState(pUseContext.getClickedPos());
            Block block = blockState.getBlock();
            if(block instanceof CropBlock) {
                int age = ((CropBlock) block).getAge(blockState);
                if(age >= ((CropBlock) block).getMaxAge()) {
                    consumeSeed(pUseContext.getPlayer(), (CropBlock) block);
                    block.playerDestroy(pUseContext.getLevel(), pUseContext.getPlayer(),
                            pUseContext.getClickedPos(), blockState,
                            pUseContext.getLevel().getBlockEntity(pUseContext.getClickedPos()),
                            pUseContext.getPlayer().getUseItem());

                    pUseContext.getLevel().setBlock(pUseContext.getClickedPos(), block.defaultBlockState(), 3);

                    pUseContext.getItemInHand().hurtAndBreak(1, pUseContext.getPlayer(), (pItem) -> {
                        pItem.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                    });
                }
                else {
                    bonemealBlock(pUseContext);
                }
            }
            else if(block instanceof BonemealableBlock) {
                bonemealBlock(pUseContext);
            }

            pUseContext.getItemInHand().hurtAndBreak(1, pUseContext.getPlayer(), (pItem) -> {
                pItem.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });

            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    public void bonemealBlock(UseOnContext pUseContext) {
        BlockState blockState = pUseContext.getLevel().getBlockState(pUseContext.getClickedPos());
        Block block = blockState.getBlock();
        if (((BonemealableBlock)block).isBonemealSuccess(pUseContext.getLevel(), pUseContext.getLevel().random, pUseContext.getClickedPos(), blockState)) {
            ((BonemealableBlock)block).performBonemeal((ServerLevel)pUseContext.getLevel(), pUseContext.getLevel().random, pUseContext.getClickedPos(), blockState);
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
