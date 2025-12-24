package com.ave.simplestationsmason.datagen;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.ave.simplestationsmason.SimpleStationsMason;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SimpleStationsMason.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        if (!event.includeServer())
            return;
        DataGenerator generator = event.getGenerator();
        PackOutput out = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookup = event.getLookupProvider();
        var helper = event.getExistingFileHelper();
        var blockTags = new ModBlockTagProvider(out, lookup, helper);
        generator.addProvider(true, blockTags);
        generator.addProvider(true, new ModItemTagProvider(out, lookup, blockTags, helper));
        generator.addProvider(event.includeServer(), new ModRecipeProvider(out, lookup));
        generator.addProvider(true,
                new LootTableProvider(out, Collections.emptySet(),
                        List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new,
                                LootContextParamSets.BLOCK)),
                        lookup));

    }
}
