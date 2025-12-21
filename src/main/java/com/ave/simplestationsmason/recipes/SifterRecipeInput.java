package com.ave.simplestationsmason.recipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record SifterRecipeInput(ItemStack input) implements RecipeInput {
    @Override
    public ItemStack getItem(int i) {
        return input;
    }

    @Override
    public int size() {
        return 1;
    }
}
