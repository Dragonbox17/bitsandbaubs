package net.benjamin.bitsandbaubs.item;

import net.benjamin.bitsandbaubs.BitsAndBaubs;
import net.benjamin.bitsandbaubs.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class BitsAndBaubsModTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BitsAndBaubs.MOD_ID);

    public static final RegistryObject<CreativeModeTab> BITS_AND_BAUBS
            = CREATIVE_MODE_TABS.register("bits_and_baubs", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.PLATINUM_INGOT.get()))
            .title(Component.translatable("creativetab.bits_and_baubs_tab"))
            .displayItems((pParameters, pOutput) -> {
                pOutput.accept(ModItems.JADE.get());
                pOutput.accept(ModBlocks.JADE_ORE.get());
                pOutput.accept(ModBlocks.DEEPSLATE_JADE_ORE.get());

                pOutput.accept(ModBlocks.JADE_BLOCK.get());
                pOutput.accept(ModBlocks.JADE_BRICKS.get());
                pOutput.accept(ModBlocks.JADE_STAIRS.get());
                pOutput.accept(ModBlocks.JADE_BRICK_STAIRS.get());
                pOutput.accept(ModBlocks.JADE_SLAB.get());
                pOutput.accept(ModBlocks.JADE_BRICK_SLAB.get());
                pOutput.accept(ModBlocks.JADE_WALL.get());
                pOutput.accept(ModBlocks.JADE_BRICK_WALL.get());

                pOutput.accept(ModItems.JADE_PICKAXE.get());
                pOutput.accept(ModItems.JADE_AXE.get());
                pOutput.accept(ModItems.JADE_SHOVEL.get());
                pOutput.accept(ModItems.JADE_CLUB.get());
                pOutput.accept(ModItems.JADE_HOE.get());

                pOutput.accept(ModItems.JADE_OAK_STAFF.get());

                pOutput.accept(ModItems.PLATINUM_PICKAXE.get());
                pOutput.accept(ModItems.PLATINUM_AXE.get());
                pOutput.accept(ModItems.PLATINUM_SHOVEL.get());
                pOutput.accept(ModItems.PLATINUM_SWORD.get());
                pOutput.accept(ModItems.PLATINUM_HOE.get());

                pOutput.accept(ModItems.RAW_PLATINUM.get());
                pOutput.accept(ModItems.PLATINUM_INGOT.get());
                pOutput.accept(ModItems.PLATINUM_NUGGET.get());

                pOutput.accept(ModItems.PLATINUM_PICKAXE.get());
                pOutput.accept(ModItems.PLATINUM_AXE.get());
                pOutput.accept(ModItems.PLATINUM_SHOVEL.get());
                pOutput.accept(ModItems.PLATINUM_SWORD.get());
                pOutput.accept(ModItems.PLATINUM_HOE.get());

                pOutput.accept(ModItems.PLATINUM_HELMET.get());
                pOutput.accept(ModItems.PLATINUM_CHESTPLATE.get());
                pOutput.accept(ModItems.PLATINUM_LEGGINGS.get());
                pOutput.accept(ModItems.PLATINUM_BOOTS.get());

                pOutput.accept(ModBlocks.ALCHEMY_TABLE.get());

                pOutput.accept(ModBlocks.PLATINUM_BLOCK.get());
                pOutput.accept(ModBlocks.RAW_PLATINUM_BLOCK.get());

                pOutput.accept(ModBlocks.CORRUPTED_SAPLING.get());

                pOutput.accept(ModBlocks.CORRUPTED_LOG.get());
                pOutput.accept(ModBlocks.CORRUPTED_WOOD.get());
                pOutput.accept(ModBlocks.STRIPPED_CORRUPTED_LOG.get());
                pOutput.accept(ModBlocks.STRIPPED_CORRUPTED_WOOD.get());
                pOutput.accept(ModBlocks.CORRUPTED_PLANKS.get());
                pOutput.accept(ModBlocks.CORRUPTED_STAIRS.get());
                pOutput.accept(ModBlocks.CORRUPTED_SLAB.get());
                pOutput.accept(ModBlocks.CORRUPTED_BUTTON.get());
                pOutput.accept(ModBlocks.CORRUPTED_PRESSURE_PLATE.get());
                pOutput.accept(ModBlocks.CORRUPTED_FENCE.get());
                pOutput.accept(ModBlocks.CORRUPTED_FENCE_GATE.get());
                pOutput.accept(ModBlocks.CORRUPTED_DOOR.get());
                pOutput.accept(ModBlocks.CORRUPTED_TRAPDOOR.get());
                pOutput.accept(ModItems.CORRUPTED_SIGN.get());
                pOutput.accept(ModItems.CORRUPTED_HANGING_SIGN.get());
                pOutput.accept(ModBlocks.CORRUPTED_LEAVES.get());

                pOutput.accept(ModItems.PROPELLER.get());
                pOutput.accept(ModItems.ENGINE.get());
                pOutput.accept(ModItems.AIR_BALLOON.get());
                pOutput.accept(ModItems.OAK_AIR_SHIP.get());

                pOutput.accept(ModItems.TERRACOTTA_GOLEM_SPAWN_EGG.get());

                pOutput.accept(ModItems.CREEPER_WARD.get());
                pOutput.accept(ModItems.ZOMBIE_WARD.get());
                pOutput.accept(ModItems.ZOMBIFIED_PIGLIN_WARD.get());
                pOutput.accept(ModItems.WITHER_SKELETON_WARD.get());

                pOutput.accept(ModBlocks.SHOJI.get());
            })
            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
