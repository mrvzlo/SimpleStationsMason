package com.ave.simplestationsmason.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.Set;

import com.ave.simplestationsmason.registrations.Registrations;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(Registrations.EXCAVATOR.block.get());
        dropSelf(Registrations.SIFTER.block.get());
        dropSelf(Registrations.FURNACE.block.get());
        dropSelf(Registrations.MIXER.block.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return List.of(Registrations.EXCAVATOR.block.get(), Registrations.SIFTER.block.get(),
                Registrations.FURNACE.block.get(), Registrations.MIXER.block.get());
    }
}
