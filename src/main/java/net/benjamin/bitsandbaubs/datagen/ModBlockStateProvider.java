package net.benjamin.bitsandbaubs.datagen;

import net.benjamin.bitsandbaubs.BitsAndBaubs;
import net.benjamin.bitsandbaubs.block.ModBlocks;
import net.benjamin.bitsandbaubs.block.custom.BlackBerryBushBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

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

        logBlock((RotatedPillarBlock) ModBlocks.INFECTED_LOG.get());
        axisBlock(((RotatedPillarBlock) ModBlocks.INFECTED_WOOD.get()), blockTexture(ModBlocks.INFECTED_LOG.get()),
                blockTexture(ModBlocks.INFECTED_LOG.get()));

        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_INFECTED_LOG.get()), blockTexture(ModBlocks.STRIPPED_INFECTED_LOG.get()),
                new ResourceLocation(BitsAndBaubs.MOD_ID, "block/stripped_infected_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_INFECTED_WOOD.get()), blockTexture(ModBlocks.STRIPPED_INFECTED_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_INFECTED_LOG.get()));

        saplingBlock(ModBlocks.INFECTED_SAPLING);

        blockItem(ModBlocks.INFECTED_LOG);
        blockItem(ModBlocks.INFECTED_WOOD);
        blockItem(ModBlocks.STRIPPED_INFECTED_LOG);
        blockItem(ModBlocks.STRIPPED_INFECTED_WOOD);

        blockWithItem(ModBlocks.INFECTED_PLANKS);

        leavesBlock(ModBlocks.INFECTED_LEAVES);

        stairsBlock(((StairBlock) ModBlocks.INFECTED_STAIRS.get()), blockTexture(ModBlocks.INFECTED_PLANKS.get()));
        slabBlock(((SlabBlock) ModBlocks.INFECTED_SLAB.get()), blockTexture(ModBlocks.INFECTED_PLANKS.get()), blockTexture(ModBlocks.INFECTED_PLANKS.get()));

        buttonBlock(((ButtonBlock) ModBlocks.INFECTED_BUTTON.get()), blockTexture(ModBlocks.INFECTED_PLANKS.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.INFECTED_PRESSURE_PLATE.get()), blockTexture(ModBlocks.INFECTED_PLANKS.get()));

        fenceGateBlock(((FenceGateBlock) ModBlocks.INFECTED_FENCE_GATE.get()), blockTexture(ModBlocks.INFECTED_PLANKS.get()));
        fenceBlock(((FenceBlock) ModBlocks.INFECTED_FENCE.get()), blockTexture(ModBlocks.INFECTED_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) ModBlocks.INFECTED_DOOR.get()), modLoc("block/infected_door_bottom"), modLoc("block/infected_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.INFECTED_TRAPDOOR.get()), modLoc("block/infected_trapdoor"), true, "cutout");

        signBlock((StandingSignBlock) ModBlocks.INFECTED_SIGN.get(), (WallSignBlock) ModBlocks.INFECTED_WALL_SIGN.get(), blockTexture(ModBlocks.INFECTED_PLANKS.get()));
        hangingSignBlock(ModBlocks.INFECTED_HANGING_SIGN.get(), ModBlocks.INFECTED_WALL_HANGING_SIGN.get(), blockTexture(ModBlocks.INFECTED_PLANKS.get()));

        blockWithItem(ModBlocks.SHOJI);

        makeBlackberryBush((BushBlock) ModBlocks.BLACK_BERRY_BUSH_BLOCK.get(), "black_berry_bush_stage", "black_berry_bush_stage");
    }

    public void makeBlackberryBush(BushBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> blackberryStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] blackberryStates(BlockState state, BushBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().cross(modelName + state.getValue(((BlackBerryBushBlock) block).AGE),
                new ResourceLocation(BitsAndBaubs.MOD_ID, "block/" + textureName + state.getValue(((BlackBerryBushBlock) block).AGE))).renderType("cutout"));

        return models;
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ResourceLocation texture) {
        ModelFile sign = models().sign(name(signBlock), texture);
        hangingSignBlock(signBlock, wallSignBlock, sign);
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ModelFile sign) {
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
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
