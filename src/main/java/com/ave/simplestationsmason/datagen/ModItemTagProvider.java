package com.ave.simplestationsmason.datagen;

import java.util.concurrent.CompletableFuture;

import com.ave.simplestationsmason.SimpleStationsMason;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
            BlockTagsProvider blockTags,
            ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags.contentsGetter(), SimpleStationsMason.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.SIFTABLE_TAG)
                .add(Items.SAND)
                .add(Items.RED_SAND)
                .add(Items.CLAY)
                .add(Items.GRAVEL)
                .add(Items.SOUL_SAND)
                .add(Items.DIRT);

        tag(ModTags.Items.GLAZED_TAG)
                .add(Items.WHITE_GLAZED_TERRACOTTA)
                .add(Items.ORANGE_GLAZED_TERRACOTTA)
                .add(Items.MAGENTA_GLAZED_TERRACOTTA)
                .add(Items.LIGHT_BLUE_GLAZED_TERRACOTTA)
                .add(Items.YELLOW_GLAZED_TERRACOTTA)
                .add(Items.LIME_GLAZED_TERRACOTTA)
                .add(Items.PINK_GLAZED_TERRACOTTA)
                .add(Items.GRAY_GLAZED_TERRACOTTA)
                .add(Items.LIGHT_GRAY_GLAZED_TERRACOTTA)
                .add(Items.CYAN_GLAZED_TERRACOTTA)
                .add(Items.PURPLE_GLAZED_TERRACOTTA)
                .add(Items.BLUE_GLAZED_TERRACOTTA)
                .add(Items.BROWN_GLAZED_TERRACOTTA)
                .add(Items.GREEN_GLAZED_TERRACOTTA)
                .add(Items.RED_GLAZED_TERRACOTTA)
                .add(Items.BLACK_GLAZED_TERRACOTTA);
    }
}
