package net.benjamin.bitsandbaubs.block;

import net.benjamin.bitsandbaubs.BitsAndBaubs;

import net.benjamin.bitsandbaubs.block.custom.*;
import net.benjamin.bitsandbaubs.item.ModItems;
import net.benjamin.bitsandbaubs.util.ModWoodTypes;
import net.benjamin.bitsandbaubs.worldgen.tree.CorruptedTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, BitsAndBaubs.MOD_ID);

    public static final RegistryObject<Block> PLATINUM_BLOCK =
            registerBlock("platinum_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK)));

    public static final RegistryObject<Block> RAW_PLATINUM_BLOCK =
            registerBlock("raw_platinum_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_GOLD_BLOCK)));


    public static final RegistryObject<Block> ALCHEMY_TABLE =
            registerBlock("alchemy_table", () -> new AlchemyTableBlock(BlockBehaviour.Properties.copy(Blocks.STONECUTTER).noOcclusion()));


    public static final RegistryObject<Block> JADE_ORE =
            registerBlock("jade_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops(), UniformInt.of(3, 6)));

    public static final RegistryObject<Block> DEEPSLATE_JADE_ORE =
            registerBlock("deepslate_jade_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).requiresCorrectToolForDrops(), UniformInt.of(3, 6)));

    public static final RegistryObject<Block> JADE_BLOCK =
            registerBlock("jade_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK)));

    public static final RegistryObject<Block> JADE_BRICKS =
            registerBlock("jade_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK).strength(40)));

    public static final RegistryObject<Block> JADE_STAIRS = registerBlock("jade_stairs",
            () -> new StairBlock(() -> ModBlocks.JADE_BLOCK.get().defaultBlockState() ,
                    BlockBehaviour.Properties.copy(Blocks.STONE_STAIRS).strength(40)));

    public static final RegistryObject<Block> JADE_BRICK_STAIRS = registerBlock("jade_brick_stairs",
            () -> new StairBlock(() -> ModBlocks.JADE_BLOCK.get().defaultBlockState() ,
                    BlockBehaviour.Properties.copy(Blocks.STONE_STAIRS).strength(40)));

    public static final RegistryObject<Block> JADE_SLAB =
            registerBlock("jade_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_SLAB).strength(40)));

    public static final RegistryObject<Block> JADE_BRICK_SLAB =
            registerBlock("jade_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_SLAB).strength(40)));

    public static final RegistryObject<Block> JADE_WALL =
            registerBlock("jade_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_WALL).strength(40)));

    public static final RegistryObject<Block> JADE_BRICK_WALL =
            registerBlock("jade_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_WALL).strength(40)));

    public static final RegistryObject<Block> INFECTED_SAPLING =
            registerBlock("infected_sapling", () -> new SaplingBlock(new CorruptedTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<Block> INFECTED_LOG =
            registerBlock("infected_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(3)));
    public static final RegistryObject<Block> INFECTED_WOOD =
            registerBlock("infected_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(3)));
    public static final RegistryObject<Block> STRIPPED_INFECTED_LOG =
            registerBlock("stripped_infected_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(3)));
    public static final RegistryObject<Block> STRIPPED_INFECTED_WOOD =
            registerBlock("stripped_infected_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(3)));

    public static final RegistryObject<Block> INFECTED_PLANKS =
            registerBlock("infected_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });
    public static final RegistryObject<Block> INFECTED_LEAVES =
            registerBlock("infected_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    public static final RegistryObject<Block> INFECTED_STAIRS = registerBlock("infected_stairs",
            () -> new StairBlock(() -> ModBlocks.INFECTED_PLANKS.get().defaultBlockState() ,
            BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)){
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });

    public static final RegistryObject<Block> INFECTED_SLAB =
            registerBlock("infected_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> INFECTED_BUTTON =
            registerBlock("infected_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON)
                    , BlockSetType.OAK, 20, true){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> INFECTED_PRESSURE_PLATE =
            registerBlock("infected_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE), BlockSetType.OAK){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> INFECTED_FENCE_GATE =
            registerBlock("infected_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE),
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> INFECTED_FENCE =
            registerBlock("infected_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> INFECTED_DOOR =
            registerBlock("infected_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), BlockSetType.OAK){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> INFECTED_TRAPDOOR =
            registerBlock("infected_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(ModBlocks.INFECTED_PLANKS.get()), BlockSetType.OAK){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> INFECTED_SIGN = BLOCKS.register("infected_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), ModWoodTypes.CORRUPTED));

    public static final RegistryObject<Block> INFECTED_WALL_SIGN = BLOCKS.register("infected_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), ModWoodTypes.CORRUPTED));

    public static final RegistryObject<Block> INFECTED_HANGING_SIGN = BLOCKS.register("infected_hanging_sign",
            () -> new ModHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), ModWoodTypes.CORRUPTED));

    public static final RegistryObject<Block> INFECTED_WALL_HANGING_SIGN = BLOCKS.register("infected_wall_hanging_sign",
            () -> new ModWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), ModWoodTypes.CORRUPTED));

    public static final RegistryObject<Block> SHOJI =
            registerBlock("shoji", () -> new ModFlammableGlassBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.WOOL)));

    public static final RegistryObject<Block> BLACK_BERRY_BUSH_BLOCK =
            registerBlock("black_berry_bush_block", () -> new BlackBerryBushBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH).noOcclusion().noCollission()));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
