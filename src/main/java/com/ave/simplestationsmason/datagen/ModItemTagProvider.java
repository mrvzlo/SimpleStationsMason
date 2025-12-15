package com.ave.simplestationsmason.datagen;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import com.ave.simplestationsmason.SimpleStationsMason;
import com.ave.simplestationsmason.registrations.ModBlocks;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
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
        var dustTag = tag(ModTags.Items.DUSTS);
        Arrays.stream(ModBlocks.COLOR_DUST_ITEMS).forEach(x -> dustTag.add(x.asItem()));
    }
}
