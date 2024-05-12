package net.benjamin.bitsandbaubs.datagen;

import net.benjamin.bitsandbaubs.BitsAndBaubs;
import net.benjamin.bitsandbaubs.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {

    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, BitsAndBaubs.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.PLATINUM_BLOCK.get(), ModBlocks.RAW_PLATINUM_BLOCK.get(), ModBlocks.ALCHEMY_TABLE.get()
                        , ModBlocks.JADE_ORE.get(), ModBlocks.DEEPSLATE_JADE_ORE.get(), ModBlocks.JADE_BLOCK.get()
                        , ModBlocks.JADE_BRICKS.get(), ModBlocks.JADE_SLAB.get(), ModBlocks.JADE_BRICK_SLAB.get()
                        , ModBlocks.JADE_STAIRS.get(), ModBlocks.JADE_BRICK_STAIRS.get(), ModBlocks.JADE_WALL.get()
                        , ModBlocks.JADE_BRICK_WALL.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.PLATINUM_BLOCK.get(), ModBlocks.RAW_PLATINUM_BLOCK.get(), ModBlocks.ALCHEMY_TABLE.get()
                        , ModBlocks.JADE_ORE.get(), ModBlocks.DEEPSLATE_JADE_ORE.get(), ModBlocks.JADE_BLOCK.get(),
                        ModBlocks.JADE_BRICKS.get(), ModBlocks.JADE_SLAB.get(), ModBlocks.JADE_BRICK_SLAB.get()
                        , ModBlocks.JADE_STAIRS.get(), ModBlocks.JADE_BRICK_STAIRS.get(), ModBlocks.JADE_WALL.get()
                        , ModBlocks.JADE_BRICK_WALL.get());

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.INFECTED_LOG.get())
                .add(ModBlocks.INFECTED_WOOD.get())
                .add(ModBlocks.STRIPPED_INFECTED_LOG.get())
                .add(ModBlocks.STRIPPED_INFECTED_WOOD.get());

        this.tag(BlockTags.PLANKS)
                .add(ModBlocks.INFECTED_PLANKS.get());

        this.tag(BlockTags.LEAVES)
                .add(ModBlocks.INFECTED_LEAVES.get());

        this.tag(BlockTags.FENCES)
                .add(ModBlocks.INFECTED_FENCE.get());
        this.tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.INFECTED_FENCE_GATE.get());
        this.tag(BlockTags.TRAPDOORS)
                .add(ModBlocks.INFECTED_TRAPDOOR.get());

        this.tag(BlockTags.WALLS)
                .add(ModBlocks.JADE_WALL.get())
                .add(ModBlocks.JADE_BRICK_WALL.get());
    }
}
