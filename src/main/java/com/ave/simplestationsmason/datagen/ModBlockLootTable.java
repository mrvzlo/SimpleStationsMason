package com.ave.simplestationsmason.datagen;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.Set;

import com.ave.simplestationsmason.registrations.Registrations;

public class ModBlockLootTable extends BlockLootSubProvider {
    protected ModBlockLootTable() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(Registrations.EXCAVATOR.getBlock());
        dropSelf(Registrations.SIFTER.getBlock());
        dropSelf(Registrations.FURNACE.getBlock());
        dropSelf(Registrations.MIXER.getBlock());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return List.of(Registrations.EXCAVATOR.getBlock(), Registrations.SIFTER.getBlock(),
                Registrations.FURNACE.getBlock(), Registrations.MIXER.getBlock());
    }
}
