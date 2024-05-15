package net.benjamin.bitsandbaubs.datagen;

import net.benjamin.bitsandbaubs.BitsAndBaubs;
import net.benjamin.bitsandbaubs.item.ModItems;
import net.benjamin.bitsandbaubs.loot.AddItemModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {

    public ModGlobalLootModifierProvider(PackOutput output) {
        super(output, BitsAndBaubs.MOD_ID);
    }

    @Override
    protected void start() {
        add("raw_platinum_from_deepslate_gold_ore", new AddItemModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.DEEPSLATE_GOLD_ORE).build(),
                LootItemRandomChanceCondition.randomChance(0.40f).build()}
                , ModItems.RAW_PLATINUM.get()));

        add("raw_platinum_from_gold_ore", new AddItemModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.GOLD_ORE).build(),
                LootItemRandomChanceCondition.randomChance(0.40f).build()}
                , ModItems.RAW_PLATINUM.get()));

        add("platinum_nugget_from_nether_gold_ore", new AddItemModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.NETHER_GOLD_ORE).build(),
                LootItemRandomChanceCondition.randomChance(0.50f).build()}
                , ModItems.PLATINUM_NUGGET.get()));

        add("black_berries_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build()}, ModItems.BLACK_BERRIES.get()));
    }
}
