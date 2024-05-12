package net.benjamin.bitsandbaubs.item;

import net.benjamin.bitsandbaubs.BitsAndBaubs;
import net.benjamin.bitsandbaubs.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static final Tier PLATINUM = TierSortingRegistry.registerTier(
            new ForgeTier(4, 500, 1f, 1f, 20,
                ModTags.Blocks.NEEDS_PLATINUM_TOOL, () -> Ingredient.of(ModItems.PLATINUM_INGOT.get())),
            new ResourceLocation(BitsAndBaubs.MOD_ID, "platinum_ingot"), List.of(Tiers.DIAMOND), List.of()
    );

    public static final Tier JADE = TierSortingRegistry.registerTier(
            new ForgeTier(3, 324, 1f, 1f, 30,
                    ModTags.Blocks.NEEDS_JADE_TOOL, () -> Ingredient.of(ModItems.JADE.get())),
            new ResourceLocation(BitsAndBaubs.MOD_ID, "jade"), List.of(Tiers.IRON), List.of()
    );
}
