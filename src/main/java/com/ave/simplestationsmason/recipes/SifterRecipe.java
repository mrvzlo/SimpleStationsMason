package com.ave.simplestationsmason.recipes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ave.simplestationsmason.SimpleStationsMason;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.item.crafting.*;

public class SifterRecipe implements Recipe<SimpleContainer> {
    public final Ingredient from;
    public final List<SifterRoll> rolls;
    private final ResourceLocation id;

    public SifterRecipe(Ingredient from, List<SifterRoll> rolls, ResourceLocation id) {
        this.from = from;
        this.rolls = rolls;
        this.id = id;
    }

    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(from);
        return list;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level level) {
        if (level.isClientSide())
            return false;
        return from.test(pContainer.getItem(0));
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return ItemStack.EMPTY;
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

    public static class Type implements RecipeType<SifterRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "sifter";
    }

    public static class Serializer implements RecipeSerializer<SifterRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(SimpleStationsMason.MODID, "sifter");

        @Override
        public SifterRecipe fromJson(ResourceLocation pRecipeId, JsonObject json) {
            var from = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "from"));
            var rollsJson = GsonHelper.getAsJsonArray(json, "rolls");

            var rolls = new ArrayList<SifterRoll>();

            for (var el : rollsJson) {
                var obj = el.getAsJsonObject();
                var output = BuiltInRegistries.ITEM.get(new ResourceLocation(GsonHelper.getAsString(obj, "output")));
                var count = GsonHelper.getAsInt(obj, "count", 1);
                var chance = GsonHelper.getAsInt(obj, "chance");
                rolls.add(new SifterRoll(output, count, chance));
            }

            return new SifterRecipe(from, rolls, pRecipeId);
        }

        @Override
        public @Nullable SifterRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            Ingredient from = Ingredient.fromNetwork(buf);

            int rollCount = buf.readVarInt();
            List<SifterRoll> rolls = new ArrayList<>(rollCount);

            for (int i = 0; i < rollCount; i++) {
                Item output = buf.readRegistryIdUnsafe(ForgeRegistries.ITEMS);
                int count = buf.readVarInt();
                int chance = buf.readVarInt();

                rolls.add(new SifterRoll(output, count, chance));
            }

            return new SifterRecipe(from, rolls, id);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, SifterRecipe recipe) {
            recipe.from.toNetwork(buf);

            buf.writeVarInt(recipe.rolls.size());
            for (SifterRoll roll : recipe.rolls) {
                buf.writeRegistryIdUnsafe(ForgeRegistries.ITEMS, roll.output());
                buf.writeVarInt(roll.count());
                buf.writeVarInt(roll.chance());
            }
        }
    }

    @Override
    public boolean canCraftInDimensions(int arg0, int arg1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return ItemStack.EMPTY;
    }

    public ItemStack roll(Random random, boolean luck) {
        int roll = random.nextInt(100);
        if (luck)
            roll += (99 - roll) / 3;

        int acc = 0;
        for (var r : rolls) {
            acc += r.chance();
            if (roll < acc)
                return new ItemStack(r.output(), r.count());
        }
        return ItemStack.EMPTY;
    }
}
