package com.ave.simplestationsmason.datagen;

import java.util.concurrent.CompletableFuture;

import com.ave.simplestationsmason.SimpleStationsMason;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
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
        generator.addProvider(event.includeServer(), new ModRecipeProvider(out));
        generator.addProvider(event.includeServer(), ModLootTableProvider.create(out));

    }
}
