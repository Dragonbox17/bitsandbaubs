package net.benjamin.bitsandbaubs.datagen.loot;

import net.benjamin.bitsandbaubs.block.ModBlocks;
import net.benjamin.bitsandbaubs.block.custom.BlackBerryBushBlock;
import net.benjamin.bitsandbaubs.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.PLATINUM_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_PLATINUM_BLOCK.get());

        this.dropSelf(ModBlocks.ALCHEMY_TABLE.get());

        this.dropSelf(ModBlocks.INFECTED_LOG.get());
        this.dropSelf(ModBlocks.INFECTED_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_INFECTED_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_INFECTED_WOOD.get());

        this.dropSelf(ModBlocks.INFECTED_PLANKS.get());
        this.add(ModBlocks.INFECTED_LEAVES.get(),
                block -> createLeavesDrops(block, ModBlocks.INFECTED_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(ModBlocks.INFECTED_SAPLING.get());

        this.dropSelf(ModBlocks.INFECTED_STAIRS.get());
        this.dropSelf(ModBlocks.INFECTED_BUTTON.get());
        this.dropSelf(ModBlocks.INFECTED_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.INFECTED_FENCE.get());
        this.dropSelf(ModBlocks.INFECTED_FENCE_GATE.get());
        this.dropSelf(ModBlocks.INFECTED_TRAPDOOR.get());

        this.add(ModBlocks.INFECTED_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.INFECTED_SLAB.get()));
        this.add(ModBlocks.INFECTED_DOOR.get(),
                block -> createDoorTable(ModBlocks.INFECTED_DOOR.get()));

        this.add(ModBlocks.INFECTED_SIGN.get(),
                block -> createSingleItemTable(ModItems.CORRUPTED_SIGN.get()));
        this.add(ModBlocks.INFECTED_WALL_SIGN.get(),
                block -> createSingleItemTable(ModItems.CORRUPTED_SIGN.get()));
        this.add(ModBlocks.INFECTED_HANGING_SIGN.get(),
                block -> createSingleItemTable(ModItems.CORRUPTED_HANGING_SIGN.get()));
        this.add(ModBlocks.INFECTED_WALL_HANGING_SIGN.get(),
                block -> createSingleItemTable(ModItems.CORRUPTED_HANGING_SIGN.get()));

        this.add(ModBlocks.JADE_ORE.get(),
                block -> createOreDrop(ModBlocks.JADE_ORE.get(), ModItems.JADE.get()));
        this.add(ModBlocks.DEEPSLATE_JADE_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_JADE_ORE.get(), ModItems.JADE.get()));

        this.dropSelf(ModBlocks.JADE_BLOCK.get());
        this.dropSelf(ModBlocks.JADE_BRICKS.get());
        this.dropSelf(ModBlocks.JADE_STAIRS.get());
        this.dropSelf(ModBlocks.JADE_BRICK_STAIRS.get());
        this.add(ModBlocks.JADE_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.JADE_SLAB.get()));
        this.add(ModBlocks.JADE_BRICK_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.JADE_BRICK_SLAB.get()));
        this.dropSelf(ModBlocks.JADE_WALL.get());
        this.dropSelf(ModBlocks.JADE_BRICK_WALL.get());

        this.dropSelf(ModBlocks.SHOJI.get());

        this.add(ModBlocks.BLACK_BERRY_BUSH_BLOCK.get(), (bush) -> {
            return this.applyExplosionDecay(bush, LootTable.lootTable().withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.BLACK_BERRY_BUSH_BLOCK.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlackBerryBushBlock.AGE, 3))).add(LootItem.lootTableItem(ModItems.BLACK_BERRIES.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))).withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.BLACK_BERRY_BUSH_BLOCK.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlackBerryBushBlock.AGE, 2))).add(LootItem.lootTableItem(ModItems.BLACK_BERRIES.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))));
        });
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
