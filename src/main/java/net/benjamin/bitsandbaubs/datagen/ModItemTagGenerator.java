package net.benjamin.bitsandbaubs.datagen;

import net.benjamin.bitsandbaubs.BitsAndBaubs;
import net.benjamin.bitsandbaubs.block.ModBlocks;
import net.benjamin.bitsandbaubs.item.ModItems;
import net.benjamin.bitsandbaubs.item.custom.BulletRoundItem;
import net.benjamin.bitsandbaubs.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {

    public ModItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, BitsAndBaubs.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.PLATINUM_HELMET.get(),
                        ModItems.PLATINUM_CHESTPLATE.get(),
                        ModItems.PLATINUM_LEGGINGS.get(),
                        ModItems.PLATINUM_BOOTS.get());

        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.INFECTED_LOG.get().asItem())
                .add(ModBlocks.INFECTED_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_INFECTED_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_INFECTED_WOOD.get().asItem());

        this.tag(ItemTags.PLANKS)
                .add(ModBlocks.INFECTED_PLANKS.get().asItem());

        this.tag(ItemTags.LEAVES)
                .add(ModBlocks.INFECTED_LEAVES.get().asItem());

        this.tag(ModTags.Items.CORRUPTED_LOGS)
                .add(ModBlocks.INFECTED_LOG.get().asItem())
                .add(ModBlocks.INFECTED_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_INFECTED_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_INFECTED_WOOD.get().asItem());

        this.tag((ItemTags.TRAPDOORS))
                .add(ModBlocks.INFECTED_TRAPDOOR.get().asItem());

        this.tag(ModTags.Items.BULLET_ROUND)
                .add(ModItems.BULLET_ROUND.get());
    }
}
