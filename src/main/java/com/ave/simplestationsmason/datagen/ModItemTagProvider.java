package com.ave.simplestationsmason.datagen;

import java.util.concurrent.CompletableFuture;

import com.ave.simplestationsmason.SimpleStationsMason;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

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
    }
}
