package net.benjamin.bitsandbaubs.recipe;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.benjamin.bitsandbaubs.BitsAndBaubs;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class AlchemyRecipe implements Recipe<SimpleContainer> {
    private final NonNullList<Ingredient> inputItems;
    private final ItemStack output;
    private final ResourceLocation id;

    public AlchemyRecipe(NonNullList<Ingredient> inputItems, ItemStack output, ResourceLocation id) {
        this.inputItems = inputItems;
        this.output = output;
        this.id = id;
    }

    @Override
    public boolean matches(SimpleContainer p_44002_, Level p_44003_) {
        if(p_44003_.isClientSide()) {
            return false;
        }

        return inputItems.get(0).test(p_44002_.getItem(0));
    }

    @Override
    public ItemStack assemble(SimpleContainer p_44001_, RegistryAccess p_267165_) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess p_267052_) {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<AlchemyRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "alchemy";
    }

    public static class Serializer implements RecipeSerializer<AlchemyRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(BitsAndBaubs.MOD_ID, "alchemy");

        @Override
        public AlchemyRecipe fromJson(ResourceLocation p_44103_, JsonObject p_44104_) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(p_44104_, "output"));
            JsonArray ingredients = GsonHelper.getAsJsonArray(p_44104_, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new AlchemyRecipe(inputs, output, p_44103_);
        }

        @Override
        public @Nullable AlchemyRecipe fromNetwork(ResourceLocation p_44105_, FriendlyByteBuf p_44106_) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(p_44106_.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(p_44106_));
            }

            ItemStack output = p_44106_.readItem();
            return new AlchemyRecipe(inputs, output, p_44105_);
        }

        @Override
        public void toNetwork(FriendlyByteBuf p_44101_, AlchemyRecipe p_44102_) {
            p_44101_.writeInt(p_44102_.inputItems.size());

            for (Ingredient ingredient: p_44102_.getIngredients()) {
                ingredient.toNetwork(p_44101_);
            }

            p_44101_.writeItemStack(p_44102_.getResultItem(null), false);
        }
    }
}
