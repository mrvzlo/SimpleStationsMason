package com.ave.simplestationsmason.datagen;

import com.ave.simplestationsmason.SimpleStationsMason;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> SIFTABLE_TAG = TagKey.create(Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(SimpleStationsMason.MODID, "siftable"));

        public static final TagKey<Item> GLAZED_TAG = TagKey.create(Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(SimpleStationsMason.MODID, "glazed"));
    }
}