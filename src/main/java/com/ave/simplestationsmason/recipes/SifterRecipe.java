package com.ave.simplestationsmason.recipes;

import java.util.List;
import java.util.Random;

import com.ave.simplestationsmason.datagen.ModRecipes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public record SifterRecipe(Ingredient from, List<SifterRoll> rolls) implements Recipe<SifterRecipeInput> {
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(from);
        return list;
    }

    @Override
    public boolean matches(SifterRecipeInput input, Level level) {
        if (level.isClientSide()) {
            return false;
        }

        return from.test(input.getItem(0));
    }

    @Override
    public ItemStack assemble(SifterRecipeInput SifterRecipeInput, HolderLookup.Provider provider) {
        return ItemStack.EMPTY;
    }

    @Override
    public RecipeSerializer<? extends Recipe<SifterRecipeInput>> getSerializer() {
        return ModRecipes.SIFTER_SERIALIZER.get();
    }

    @Override
    public RecipeType<? extends Recipe<SifterRecipeInput>> getType() {
        return ModRecipes.SIFTER_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<SifterRecipe> {
        public static final MapCodec<SifterRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC.fieldOf("from").forGetter(SifterRecipe::from),
                SifterRoll.CODEC.listOf().fieldOf("rolls").forGetter(SifterRecipe::rolls))
                .apply(inst, SifterRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, SifterRoll> ROLL_STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.registry(Registries.ITEM), SifterRoll::output,
                ByteBufCodecs.INT, SifterRoll::count,
                ByteBufCodecs.INT, SifterRoll::chance,
                SifterRoll::new);

        public static final StreamCodec<RegistryFriendlyByteBuf, SifterRecipe> STREAM_CODEC = StreamCodec.composite(
                Ingredient.CONTENTS_STREAM_CODEC, SifterRecipe::from,
                ROLL_STREAM_CODEC.apply(ByteBufCodecs.list()), SifterRecipe::rolls,
                SifterRecipe::new);

        @Override
        public MapCodec<SifterRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, SifterRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }

    @Override
    public boolean canCraftInDimensions(int arg0, int arg1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(Provider arg0) {
        return ItemStack.EMPTY;
    }

    public ItemStack roll(Random random) {
        int roll = random.nextInt(100);
        int acc = 0;

        for (SifterRoll r : rolls) {
            acc += r.chance();
            if (roll < acc)
                return new ItemStack(r.output(), r.count());
        }
        return ItemStack.EMPTY;
    }
}
