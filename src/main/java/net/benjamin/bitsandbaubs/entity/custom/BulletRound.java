package net.benjamin.bitsandbaubs.entity.custom;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class BulletRound extends AbstractArrow {
    public BulletRound(EntityType<? extends AbstractArrow> p_36721_, Level p_36722_) {
        super(p_36721_, p_36722_);
        this.setSoundEvent(SoundEvents.METAL_BREAK);
        this.pickup = Pickup.ALLOWED;
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(Items.IRON_NUGGET);
    }
}
