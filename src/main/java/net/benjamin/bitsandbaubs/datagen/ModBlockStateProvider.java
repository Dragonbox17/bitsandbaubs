package net.benjamin.bitsandbaubs.datagen;

import net.benjamin.bitsandbaubs.BitsAndBaubs;
import net.benjamin.bitsandbaubs.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, BitsAndBaubs.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.PLATINUM_BLOCK);
        blockWithItem(ModBlocks.RAW_PLATINUM_BLOCK);

        blockWithItem(ModBlocks.JADE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_JADE_ORE);

        blockWithItem(ModBlocks.JADE_BLOCK);
        blockWithItem(ModBlocks.JADE_BRICKS);
        stairsBlock(((StairBlock) ModBlocks.JADE_STAIRS.get()), blockTexture(ModBlocks.JADE_BLOCK.get()));
        stairsBlock(((StairBlock) ModBlocks.JADE_BRICK_STAIRS.get()), blockTexture(ModBlocks.JADE_BRICKS.get()));
        slabBlock(((SlabBlock) ModBlocks.JADE_SLAB.get()), blockTexture(ModBlocks.JADE_BLOCK.get()), blockTexture(ModBlocks.JADE_BLOCK.get()));
        slabBlock(((SlabBlock) ModBlocks.JADE_BRICK_SLAB.get()), blockTexture(ModBlocks.JADE_BRICKS.get()), blockTexture(ModBlocks.JADE_BRICKS.get()));
        wallBlock(((WallBlock) ModBlocks.JADE_WALL.get()), "jade_wall", blockTexture(ModBlocks.JADE_BLOCK.get()));
        wallBlock(((WallBlock) ModBlocks.JADE_BRICK_WALL.get()), "jade_brick_wall", blockTexture(ModBlocks.JADE_BRICKS.get()));

        simpleBlockWithItem(ModBlocks.ALCHEMY_TABLE.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/alchemy_table")));

        logBlock((RotatedPillarBlock) ModBlocks.CORRUPTED_LOG.get());
        axisBlock(((RotatedPillarBlock) ModBlocks.CORRUPTED_WOOD.get()), blockTexture(ModBlocks.CORRUPTED_LOG.get()),
                blockTexture(ModBlocks.CORRUPTED_LOG.get()));

        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_CORRUPTED_LOG.get()), blockTexture(ModBlocks.STRIPPED_CORRUPTED_LOG.get()),
                new ResourceLocation(BitsAndBaubs.MOD_ID, "block/stripped_corrupted_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_CORRUPTED_WOOD.get()), blockTexture(ModBlocks.STRIPPED_CORRUPTED_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_CORRUPTED_LOG.get()));

        saplingBlock(ModBlocks.CORRUPTED_SAPLING);

        blockItem(ModBlocks.CORRUPTED_LOG);
        blockItem(ModBlocks.CORRUPTED_WOOD);
        blockItem(ModBlocks.STRIPPED_CORRUPTED_LOG);
        blockItem(ModBlocks.STRIPPED_CORRUPTED_WOOD);

        blockWithItem(ModBlocks.CORRUPTED_PLANKS);

        leavesBlock(ModBlocks.CORRUPTED_LEAVES);

        stairsBlock(((StairBlock) ModBlocks.CORRUPTED_STAIRS.get()), blockTexture(ModBlocks.CORRUPTED_PLANKS.get()));
        slabBlock(((SlabBlock) ModBlocks.CORRUPTED_SLAB.get()), blockTexture(ModBlocks.CORRUPTED_PLANKS.get()), blockTexture(ModBlocks.CORRUPTED_PLANKS.get()));

        buttonBlock(((ButtonBlock) ModBlocks.CORRUPTED_BUTTON.get()), blockTexture(ModBlocks.CORRUPTED_PLANKS.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.CORRUPTED_PRESSURE_PLATE.get()), blockTexture(ModBlocks.CORRUPTED_PLANKS.get()));

        fenceGateBlock(((FenceGateBlock) ModBlocks.CORRUPTED_FENCE_GATE.get()), blockTexture(ModBlocks.CORRUPTED_PLANKS.get()));
        fenceBlock(((FenceBlock) ModBlocks.CORRUPTED_FENCE.get()), blockTexture(ModBlocks.CORRUPTED_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) ModBlocks.CORRUPTED_DOOR.get()), modLoc("block/corrupted_door_bottom"), modLoc("block/corrupted_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.CORRUPTED_TRAPDOOR.get()), modLoc("block/corrupted_trapdoor"), true, "cutout");
    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), new ResourceLocation("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(BitsAndBaubs.MOD_ID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

}
