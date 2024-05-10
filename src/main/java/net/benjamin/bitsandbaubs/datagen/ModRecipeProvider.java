package net.benjamin.bitsandbaubs.datagen;

import net.benjamin.bitsandbaubs.BitsAndBaubs;
import net.benjamin.bitsandbaubs.block.ModBlocks;
import net.benjamin.bitsandbaubs.item.ModItems;
import net.benjamin.bitsandbaubs.util.ModTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> PLATINUM_SMELTABLES = List.of(ModItems.RAW_PLATINUM.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }


    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pRecipeOutput) {
        oreBlasting(pRecipeOutput, PLATINUM_SMELTABLES, RecipeCategory.MISC, ModItems.PLATINUM_INGOT.get(), 0.25f, 100, "platinum_ingot");
        oreSmelting(pRecipeOutput, PLATINUM_SMELTABLES, RecipeCategory.MISC, ModItems.PLATINUM_INGOT.get(), 0.25f, 200, "platinum_ingot");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PLATINUM_BLOCK.get())
                .pattern("PPP")
                .pattern("PPP")
                .pattern("PPP")
                .define('P', ModItems.PLATINUM_INGOT.get())
                .unlockedBy(getHasName(ModItems.PLATINUM_INGOT.get()), has(ModItems.PLATINUM_INGOT.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_PLATINUM_BLOCK.get())
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .define('R', ModItems.RAW_PLATINUM.get())
                .unlockedBy(getHasName(ModItems.RAW_PLATINUM.get()), has(ModItems.RAW_PLATINUM.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PLATINUM_PICKAXE.get())
                .pattern("PPP")
                .pattern(" S ")
                .pattern(" S ")
                .define('P', ModItems.PLATINUM_INGOT.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.PLATINUM_INGOT.get()), has(ModItems.PLATINUM_INGOT.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PLATINUM_AXE.get())
                .pattern("PP ")
                .pattern("PS ")
                .pattern(" S ")
                .define('P', ModItems.PLATINUM_INGOT.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.PLATINUM_INGOT.get()), has(ModItems.PLATINUM_INGOT.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PLATINUM_SHOVEL.get())
                .pattern(" P ")
                .pattern(" S ")
                .pattern(" S ")
                .define('P', ModItems.PLATINUM_INGOT.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.PLATINUM_INGOT.get()), has(ModItems.PLATINUM_INGOT.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PLATINUM_SWORD.get())
                .pattern(" P ")
                .pattern(" P ")
                .pattern(" S ")
                .define('P', ModItems.PLATINUM_INGOT.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.PLATINUM_INGOT.get()), has(ModItems.PLATINUM_INGOT.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PLATINUM_HOE.get())
                .pattern("PP ")
                .pattern(" S ")
                .pattern(" S ")
                .define('P', ModItems.PLATINUM_INGOT.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.PLATINUM_INGOT.get()), has(ModItems.PLATINUM_INGOT.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALCHEMY_TABLE.get())
                .pattern("S B")
                .pattern("PAP")
                .pattern("AAA")
                .define('P', ModItems.PLATINUM_INGOT.get())
                .define('B', Items.GLASS_BOTTLE)
                .define('S', Blocks.BREWING_STAND)
                .define('A', Blocks.ANDESITE)
                .unlockedBy(getHasName(ModItems.PLATINUM_INGOT.get()), has(ModItems.PLATINUM_INGOT.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PROPELLER.get())
                .pattern(" I ")
                .pattern("IPI")
                .pattern(" I ")
                .define('I', Items.IRON_INGOT)
                .define('P', ModItems.PLATINUM_INGOT.get())
                .unlockedBy(getHasName(ModItems.PLATINUM_INGOT.get()), has(ModItems.PLATINUM_INGOT.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ENGINE.get())
                .pattern("IPI")
                .pattern("PRP")
                .pattern("IPI")
                .define('I', Items.IRON_INGOT)
                .define('P', Blocks.PISTON)
                .define('R', ModItems.PROPELLER.get())
                .unlockedBy(getHasName(ModItems.PROPELLER.get()), has(ModItems.PROPELLER.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.AIR_BALLOON.get())
                .pattern("WMW")
                .pattern("M M")
                .pattern("WMW")
                .define('M', Items.PHANTOM_MEMBRANE)
                .define('W', Blocks.WHITE_WOOL)
                .unlockedBy(getHasName(Items.PHANTOM_MEMBRANE), has(Items.PHANTOM_MEMBRANE))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.OAK_AIR_SHIP.get())
                .pattern("EAE")
                .pattern("LBL")
                .pattern("   ")
                .define('E', ModItems.ENGINE.get())
                .define('A', ModItems.AIR_BALLOON.get())
                .define('B', Items.OAK_BOAT)
                .define('L', Items.LEAD)
                .unlockedBy(getHasName(ModItems.AIR_BALLOON.get()), has(ModItems.AIR_BALLOON.get()))
                .save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.PLATINUM_INGOT.get(), 9)
                .requires(ModBlocks.PLATINUM_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.PLATINUM_BLOCK.get()), has(ModBlocks.PLATINUM_BLOCK.get()))
                .save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_PLATINUM.get(), 9)
                .requires(ModBlocks.RAW_PLATINUM_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.RAW_PLATINUM_BLOCK.get()), has(ModBlocks.RAW_PLATINUM_BLOCK.get()))
                .save(pRecipeOutput);

        planksFromLogs(pRecipeOutput, ModBlocks.CORRUPTED_PLANKS.get(), ModTags.Items.CORRUPTED_LOGS, 4);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.JADE_OAK_STAFF.get())
                .pattern("  J")
                .pattern(" O ")
                .pattern("J  ")
                .define('J', ModItems.JADE.get())
                .define('O', Blocks.OAK_PLANKS)
                .unlockedBy(getHasName(ModItems.JADE.get()), has(ModItems.JADE.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.JADE_PICKAXE.get())
                .pattern("JJJ")
                .pattern(" S ")
                .pattern(" S ")
                .define('J', ModItems.JADE.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.JADE.get()), has(ModItems.JADE.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.JADE_AXE.get())
                .pattern("JJ ")
                .pattern("JS ")
                .pattern(" S ")
                .define('J', ModItems.JADE.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.JADE.get()), has(ModItems.JADE.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.JADE_SHOVEL.get())
                .pattern(" J ")
                .pattern(" S ")
                .pattern(" S ")
                .define('J', ModItems.JADE.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.JADE.get()), has(ModItems.JADE.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.JADE_CLUB.get())
                .pattern(" J ")
                .pattern(" J ")
                .pattern(" S ")
                .define('J', ModItems.JADE.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.JADE.get()), has(ModItems.JADE.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.JADE_HOE.get())
                .pattern("JJ ")
                .pattern(" S ")
                .pattern(" S ")
                .define('J', ModItems.JADE.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.JADE.get()), has(ModItems.JADE.get()))
                .save(pRecipeOutput);
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pRecipeOutput, RecipeSerializer<? extends AbstractCookingRecipe> pSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pSuffix) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                    pExperience, pCookingTime, pSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pRecipeOutput, BitsAndBaubs.MOD_ID + ":" + getItemName(pResult) + pSuffix + "_" + getItemName(itemlike));
        }

    }

}
