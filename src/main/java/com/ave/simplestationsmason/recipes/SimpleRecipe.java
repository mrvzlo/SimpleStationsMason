package com.ave.simplestationsmason.recipes;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class SimpleRecipe {
    public final ItemStack source;
    public final ItemStack product;
    public final TagKey<Item> productTag;
    public final TagKey<Item> sourceTag;

    public SimpleRecipe(ItemStack source, ItemStack product) {
        this.source = source;
        this.product = product;
        this.productTag = null;
        this.sourceTag = null;
    }

    public SimpleRecipe(ItemStack source, TagKey<Item> product) {
        this.source = source;
        this.product = null;
        this.productTag = product;
        this.sourceTag = null;
    }

    public SimpleRecipe(TagKey<Item> source, TagKey<Item> product) {
        this.source = null;
        this.product = null;
        this.productTag = product;
        this.sourceTag = source;
    }
}
