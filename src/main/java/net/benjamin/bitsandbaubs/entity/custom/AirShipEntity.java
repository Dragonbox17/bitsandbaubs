package net.benjamin.bitsandbaubs.entity.custom;

import com.google.common.collect.Lists;
import net.benjamin.bitsandbaubs.entity.ModEntities;
import net.benjamin.bitsandbaubs.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.MinecraftForge;

import java.util.List;
import java.util.function.IntFunction;

public class AirShipEntity extends Boat implements net.minecraftforge.common.extensions.IForgeBoat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(AirShipEntity.class, EntityDataSerializers.INT);
    private float deltaRotation;
    private float invFriction;
    private float landFriction;
    private AirShipEntity.Status status;
    private AirShipEntity.Status oldStatus;
    private float h = 0.0F;

    public AirShipEntity(EntityType<? extends Boat> p_38290_, Level p_38291_) {
        super(p_38290_, p_38291_);
        this.blocksBuilding = true;
        this.setNoGravity(true);
        p_38291_.noCollision(this);
    }

    public AirShipEntity(Level pLevel, double pX, double pY, double pZ) {
        this(ModEntities.AIR_SHIP.get(), pLevel);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    @Override
    public Item getDropItem() {
        return switch (getModVariant(this)) {
            case OAK -> ModItems.OAK_AIR_SHIP.get();
        };
    }

    public void setModVariant(AirShipEntity.Type pVariant) {
        this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
    }

    public static Type getModVariant(AirShipEntity airShipEntity) {
        return Type.byId(airShipEntity.entityData.get(DATA_ID_TYPE));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE, Type.OAK.ordinal());
    }

    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putString("Type", this.getModVariant(this).getSerializedName());
    }

    protected void readAdditionalSaveData(CompoundTag pCompound) {
        if(pCompound.contains("Type", 8)) {
            this.setModVariant(Type.byName(pCompound.getString("Type")));
        }
    }

    public static enum Type implements StringRepresentable {
        OAK(Blocks.OAK_PLANKS, "oak");

        private final String name;
        private final Block planks;
        public static final StringRepresentable.EnumCodec<AirShipEntity.Type> CODEC = StringRepresentable.fromEnum(AirShipEntity.Type::values);
        private static final IntFunction<AirShipEntity.Type> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);

        private Type(Block p_38427_, String p_38428_) {
            this.name = p_38428_;
            this.planks = p_38427_;
        }

        public String getSerializedName() {
            return this.name;
        }

        public String getName() {
            return this.name;
        }

        public Block getPlanks() {
            return this.planks;
        }

        public String toString() {
            return this.name;
        }

        public static AirShipEntity.Type byId(int p_38431_) {
            return BY_ID.apply(p_38431_);
        }

        public static AirShipEntity.Type byName(String p_38433_) {
            return CODEC.byName(p_38433_, OAK);
        }
    }

    @Override
    protected void checkFallDamage(double p_38307_, boolean p_38308_, BlockState p_38309_, BlockPos p_38310_) {
        this.resetFallDistance();
    }

    @Override
    public void tick() {
        super.tick();
        this.oldStatus = this.status;
        this.status = this.getStatus();

        if (this.isControlledByLocalInstance()) {
            this.floatShip();
            if (this.level().isClientSide) {
                this.controlShip();
            }

            this.move(MoverType.SELF, this.getDeltaMovement());
        } else {
            this.setDeltaMovement(Vec3.ZERO);
        }
    }

    private void floatShip() {
        h = 0;
        double d1 = 1.1D;
        double d2 = 0.0D;
        this.invFriction = 1F;
        if (this.status == AirShipEntity.Status.IN_AIR) {
            this.invFriction = 0.9F;
            this.landFriction = 0.0F;
        } else if (this.status == AirShipEntity.Status.ON_LAND) {
            this.invFriction = this.landFriction;
            if (this.getControllingPassenger() instanceof Player) {
                this.landFriction /= 2.0F;
            }
        }

        Vec3 vec3 = this.getDeltaMovement();
        if(vec3.y * d1 < 2) {
            this.setDeltaMovement(vec3.x * (double)this.invFriction, vec3.y * d1, vec3.z * (double)this.invFriction);
        }
        this.deltaRotation *= this.invFriction;
        if (d2 > 0.0D) {
            Vec3 vec31 = this.getDeltaMovement();
            this.setDeltaMovement(vec31.x, (vec31.y + d2 * 0.06153846016296973D) * 0.75D, vec31.z);
        }
    }

    private void controlShip() {
        if (this.isVehicle()) {
            float f = 0.0F;

            if(Minecraft.getInstance().options.keyJump.isDown()) {
                if(h < 0.2) {
                    h += 0.1F;
                }
            }
            if (Minecraft.getInstance().options.keySprint.isDown()) {
                if(h > -0.2) {
                    h -= 0.1F;
                }
            }

            this.setDeltaMovement(this.getDeltaMovement().add((double)(Mth.sin(-this.getYRot() * ((float)Math.PI / 180F)) * f), h, (double)(Mth.cos(this.getYRot() * ((float)Math.PI / 180F)) * f)));
            this.setDeltaMovement(new Vec3(this.getDeltaMovement().x, h, this.getDeltaMovement().z));
        }
    }

    public Vec3 getDismountLocationForPassenger(LivingEntity p_38357_) {
        double d0 = this.getX();
        double d1 = this.getZ();
        BlockPos blockpos = BlockPos.containing(d0, this.getBoundingBox().minY, d1);
        BlockPos blockpos1 = blockpos.below();
        if (!this.level().isWaterAt(blockpos1)) {
            List<Vec3> list = Lists.newArrayList();
            double d2 = this.level().getBlockFloorHeight(blockpos);
            if (DismountHelper.isBlockFloorValid(d2)) {
                list.add(new Vec3(d0, (double)blockpos.getY() + d2, d1));
            }

            double d3 = this.level().getBlockFloorHeight(blockpos1);
            if (DismountHelper.isBlockFloorValid(d3)) {
                list.add(new Vec3(d0, (double)blockpos1.getY() + d3, d1));
            }

            for(Pose pose : p_38357_.getDismountPoses()) {
                for(Vec3 vec31 : list) {
                    if (DismountHelper.canDismountTo(this.level(), vec31, p_38357_, pose)) {
                        p_38357_.setPose(pose);
                        return vec31;
                    }
                }
            }
        }

        return super.getDismountLocationForPassenger(p_38357_);
    }

    private AirShipEntity.Status getStatus() {
        float f = this.getGroundFriction();
        if (f > 0.0F) {
            this.landFriction = f;
            return AirShipEntity.Status.ON_LAND;
        } else {
            return AirShipEntity.Status.IN_AIR;
        }
    }

    @Override
    public float getGroundFriction() {
        AABB aabb = this.getBoundingBox();
        AABB aabb1 = new AABB(aabb.minX, aabb.minY - 0.001D, aabb.minZ, aabb.maxX, aabb.minY, aabb.maxZ);
        VoxelShape voxelshape = Shapes.create(aabb1);
        float f = 0.0F;
        int k1 = 0;
        BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();

        blockPos.set(this.getX(), this.getY() -1, this.getZ());
        BlockState blockstate = this.level().getBlockState(blockPos);
        if(!(blockstate.getBlock() instanceof AirBlock)) {
            f = blockstate.getFriction(this.level(), blockPos, this);
            ++k1;
        }

        return f / (float)k1;
    }

    public static enum Status {
        ON_LAND,
        IN_AIR;
    }
}
