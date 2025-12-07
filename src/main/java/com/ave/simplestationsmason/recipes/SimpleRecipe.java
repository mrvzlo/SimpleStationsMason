package com.ave.simplestationsmason.recipes;

import net.minecraft.world.item.ItemStack;

public class SimpleRecipe {
    public final ItemStack source;
    public final ItemStack product;

    public SimpleRecipe(ItemStack source, ItemStack product) {
        this.source = source;
        this.product = product;
    }
}
