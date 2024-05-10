package net.benjamin.bitsandbaubs.block.entity;

import net.benjamin.bitsandbaubs.BitsAndBaubs;
import net.benjamin.bitsandbaubs.recipe.AlchemyRecipe;
import net.benjamin.bitsandbaubs.screen.AlchemyTableMenu;
import net.benjamin.bitsandbaubs.util.ModEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class AlchemyTableBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            if(stack.getItem() == Items.BLAZE_POWDER) {
                return switch (slot) {
                    case 0, 2 -> false;
                    case 1 -> true;
                    default -> super.isItemValid(slot, stack);
                };
            }
            else{
                return switch (slot) {
                    case 0 -> true;
                    case 1, 2 -> false;
                    default -> super.isItemValid(slot, stack);
                };
            }
        }
    };

    private final ModEnergyStorage ENERGY_STORAGE = new ModEnergyStorage(1440, 120) {
        @Override
        public void onEnergyChanged() {
            setChanged();

        }
    };

    private static final int REFUEL_TIME = 30;
    private static final int ENERGY_REQ = 360;
    private static final int INPUT_SLOT = 0;
    private static final int FUEL_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    private LazyOptional<IEnergyStorage> lazyEnergyHandler = LazyOptional.empty();

    protected  final ContainerData data;
    private int progress = 0;
    private int maxProgress = 360;
    private int fuelTime = 30;

    public AlchemyTableBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(ModBlockEntities.ALCHEMY_TABLE_BE.get(), p_155229_, p_155230_);
        this.data = new ContainerData() {
            @Override
            public int get(int p_39284_) {
                return switch (p_39284_) {
                    case 0 -> AlchemyTableBlockEntity.this.progress;
                    case 1 -> AlchemyTableBlockEntity.this.maxProgress;
                    case 2 -> AlchemyTableBlockEntity.this.ENERGY_STORAGE.getEnergyStored();
                    case 3 -> AlchemyTableBlockEntity.this.ENERGY_STORAGE.getMaxEnergyStored();
                    default ->  0;
                };
            }

            @Override
            public void set(int p_39285_, int p_39286_) {
                switch (p_39285_) {
                    case 0 -> AlchemyTableBlockEntity.this.progress = p_39286_;
                    case 1 -> AlchemyTableBlockEntity.this.maxProgress = p_39286_;
                    case 2 -> AlchemyTableBlockEntity.this.ENERGY_STORAGE.setEnergy(p_39286_);
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ENERGY){
            return lazyEnergyHandler.cast();
        }

        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
        lazyEnergyHandler = LazyOptional.of(() -> ENERGY_STORAGE);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        lazyEnergyHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.bitsandbaubs.alchemy_table");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int p_39954_, Inventory p_39955_, Player p_39956_) {
        return new AlchemyTableMenu(p_39954_, p_39955_, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());
        nbt.putInt("alchemy_table.progress", progress);
        nbt.putInt("alchemy_table.energy", ENERGY_STORAGE.getEnergyStored());

        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("alchemy_table.progress");
        ENERGY_STORAGE.setEnergy(nbt.getInt("alchemy_table.energy"));

        super.load(nbt);
    }

    public void tick(Level p155253, BlockPos p155254, BlockState p155255) {
        if(hasFuelInSecondSlot() && fuelTime >= REFUEL_TIME && this.ENERGY_STORAGE.getEnergyStored() <= this.ENERGY_STORAGE.getMaxEnergyStored() - (ENERGY_REQ / 3)) {
            this.itemHandler.extractItem(FUEL_SLOT, 1, false);
            this.ENERGY_STORAGE.receiveEnergy(120, false);
            fuelTime = 0;
        }

        fuelTime++;

        if(hasRecipe() && hasEnoughEnergy()) {
            increaseCraftingProgress();
            this.ENERGY_STORAGE.extractEnergy(1, false);
            setChanged(p155253, p155254, p155255);

            if(hasProgressFinished()) {
                craftItem();
                resetProgress();
            }
        }
        else{
            resetProgress();
        }
    }

    private boolean hasEnoughEnergy() {
        return this.ENERGY_STORAGE.getEnergyStored() >= ENERGY_REQ - progress;
    }

    private boolean hasFuelInSecondSlot() {
        return this.itemHandler.getStackInSlot(FUEL_SLOT).is(Items.BLAZE_POWDER);
    }

    private void resetProgress() {
        progress = 0;
    }

    private void craftItem() {
        Optional<AlchemyRecipe> recipe = getCurrentRecipe();
        ItemStack result = recipe.get().getResultItem(null);

        this.itemHandler.extractItem(INPUT_SLOT, 1, false);
        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        Optional<AlchemyRecipe> recipe = getCurrentRecipe();

        if(recipe.isEmpty()) {
            return false;
        }
        ItemStack result = recipe.get().getResultItem(null);

        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private Optional<AlchemyRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }

        return this.level.getRecipeManager().getRecipeFor(AlchemyRecipe.Type.INSTANCE, inventory, level);
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }
}
