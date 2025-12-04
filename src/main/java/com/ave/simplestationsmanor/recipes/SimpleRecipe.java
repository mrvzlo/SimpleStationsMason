package com.ave.simplestationsmanor.recipes;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;

public class SimpleRecipe {
    public final ItemStack filter;
    public final int water = 1000;
    public final int fertilizer = 1;
    public final ItemStack outputType;
    public final TagKey tag;

    public SimpleRecipe(ItemStack stack, ItemStack out) {
        this.filter = stack;
        outputType = out;
        tag = null;
    }

    public SimpleRecipe(TagKey tag) {
        this.tag = tag;
        outputType = null;
        filter = null;
    }
}
