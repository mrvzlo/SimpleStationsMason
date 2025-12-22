package com.ave.simplestationsmason.recipes;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class SimpleRecipe {
    public ItemStack source = null;
    public ItemStack product = null;
    public TagKey<Item> productTag = null;
    public TagKey<Item> sourceTag = null;
    public int chance = 100;

    public SimpleRecipe(ItemStack source, ItemStack product) {
        this.source = source;
        this.product = product;
    }

    public SimpleRecipe(ItemStack source, ItemStack product, int chance) {
        this.source = source;
        this.product = product;
        this.chance = chance;
    }

    public SimpleRecipe(ItemStack source, TagKey<Item> product) {
        this.source = source;
        this.productTag = product;
    }

    public SimpleRecipe(TagKey<Item> source, TagKey<Item> product) {
        this.productTag = product;
        this.sourceTag = source;
    }
}
