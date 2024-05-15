package net.benjamin.bitsandbaubs.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties AMBROSIA = (new FoodProperties.Builder())
            .nutrition(6)
            .saturationMod(1.2F)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 200, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 0), 1.0F)
            .alwaysEat()
            .build();

    public static final FoodProperties BLACK_BERRIES = (new FoodProperties.Builder())
            .nutrition(2)
            .saturationMod(0.2F)
            .build();
}
