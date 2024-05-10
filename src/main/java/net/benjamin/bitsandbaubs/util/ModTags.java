package net.benjamin.bitsandbaubs.util;

import net.benjamin.bitsandbaubs.BitsAndBaubs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> NEEDS_PLATINUM_TOOL = blockTag("needs_platinum_tool");

        public static final TagKey<Block> NEEDS_JADE_TOOL = blockTag("needs_jade_tool");

        private static TagKey<Block> blockTag(String name) {
            return BlockTags.create(new ResourceLocation(BitsAndBaubs.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> CORRUPTED_LOGS = itemTag("corrupted_logs");

        private static TagKey<Item> itemTag(String name) {
            return ItemTags.create(new ResourceLocation(BitsAndBaubs.MOD_ID, name));
        }
    }
}
