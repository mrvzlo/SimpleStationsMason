package com.ave.simplestationsmason.datagen;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.Set;

import com.ave.simplestationsmason.registrations.ModBlocks;

public class ModBlockLootTable extends BlockLootSubProvider {
    protected ModBlockLootTable() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.EXCAVATOR_BLOCK.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return List.of(ModBlocks.EXCAVATOR_BLOCK.get());
    }
}
