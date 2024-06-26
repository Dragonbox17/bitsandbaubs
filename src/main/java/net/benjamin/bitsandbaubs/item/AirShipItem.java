package net.benjamin.bitsandbaubs.item;

import net.benjamin.bitsandbaubs.entity.ModEntities;
import net.benjamin.bitsandbaubs.entity.custom.AirShipEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.Objects;
import java.util.function.Predicate;

public class AirShipItem extends Item {
    private static final Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);
    private final AirShipEntity.Type type;
    private final boolean hasChest;

    public AirShipItem(boolean p_220013_, AirShipEntity.Type p_220014_, Item.Properties p_220015_) {
        super(p_220015_);
        this.hasChest = p_220013_;
        this.type = p_220014_;
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        if (!(level instanceof ServerLevel)) {
            return InteractionResult.SUCCESS;
        } else {
            ItemStack itemstack = pContext.getItemInHand();
            BlockPos blockpos = pContext.getClickedPos();
            Direction direction = pContext.getClickedFace();
            BlockState blockstate = level.getBlockState(blockpos);
            if (blockstate.is(Blocks.SPAWNER)) {
                BlockEntity blockentity = level.getBlockEntity(blockpos);
                if (blockentity instanceof SpawnerBlockEntity) {
                    SpawnerBlockEntity spawnerblockentity = (SpawnerBlockEntity)blockentity;

                    EntityType<?> entitytype1 = switch(this.type) {
                        case ACACIA -> ModEntities.ACACIA_AIR_SHIP.get();
                        case BIRCH -> ModEntities.BIRCH_AIR_SHIP.get();
                        case CHERRY -> ModEntities.CHERRY_AIR_SHIP.get();
                        case DARK_OAK -> ModEntities.DARK_OAK_AIR_SHIP.get();
                        case JUNGLE -> ModEntities.JUNGLE_AIR_SHIP.get();
                        case MANGROVE -> ModEntities.MANGROVE_AIR_SHIP.get();
                        case OAK -> ModEntities.OAK_AIR_SHIP.get();
                        case SPRUCE -> ModEntities.SPRUCE_AIR_SHIP.get();
                    };

                    spawnerblockentity.setEntityId(entitytype1, level.getRandom());
                    blockentity.setChanged();
                    level.sendBlockUpdated(blockpos, blockstate, blockstate, 3);
                    level.gameEvent(pContext.getPlayer(), GameEvent.BLOCK_CHANGE, blockpos);
                    itemstack.shrink(1);
                    return InteractionResult.CONSUME;
                }
            }

            BlockPos blockpos1;
            if (blockstate.getCollisionShape(level, blockpos).isEmpty()) {
                blockpos1 = blockpos;
            } else {
                blockpos1 = blockpos.relative(direction);
            }

            EntityType<?> entitytype = switch(this.type) {
                case ACACIA -> ModEntities.ACACIA_AIR_SHIP.get();
                case BIRCH -> ModEntities.BIRCH_AIR_SHIP.get();
                case CHERRY -> ModEntities.CHERRY_AIR_SHIP.get();
                case DARK_OAK -> ModEntities.DARK_OAK_AIR_SHIP.get();
                case JUNGLE -> ModEntities.JUNGLE_AIR_SHIP.get();
                case MANGROVE -> ModEntities.MANGROVE_AIR_SHIP.get();
                case OAK -> ModEntities.OAK_AIR_SHIP.get();
                case SPRUCE -> ModEntities.SPRUCE_AIR_SHIP.get();
            };

            AirShipEntity airShipEntity = (AirShipEntity) entitytype.spawn((ServerLevel)pContext.getLevel(), itemstack, pContext.getPlayer(), new BlockPos(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ()), MobSpawnType.TRIGGERED, false, false);
            if (airShipEntity != null) {
                airShipEntity.setModVariant(this.type);
                itemstack.shrink(1);
                level.gameEvent(pContext.getPlayer(), GameEvent.ENTITY_PLACE, blockpos);
            }

            return InteractionResult.CONSUME;
        }
    }
}
