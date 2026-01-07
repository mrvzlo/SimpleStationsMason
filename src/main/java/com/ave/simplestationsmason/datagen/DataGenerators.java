package com.ave.simplestationsmason.datagen;

import com.ave.simplestationsmason.SimpleStationsMason;

import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = SimpleStationsMason.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        if (!event.includeServer())
            return;
        var generator = event.getGenerator();
        var out = generator.getPackOutput();
        var lookup = event.getLookupProvider();
        var helper = event.getExistingFileHelper();
        var blockTags = new ModBlockTagProvider(out, lookup, helper);
        generator.addProvider(true, blockTags);
        generator.addProvider(true, new ModItemTagProvider(out, lookup, blockTags, helper));
        generator.addProvider(event.includeServer(), new ModRecipeProvider(out));
        generator.addProvider(event.includeServer(), ModLootTableProvider.create(out));

    }
}
