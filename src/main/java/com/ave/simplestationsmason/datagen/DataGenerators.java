package com.ave.simplestationsmason.datagen;

import java.util.Collections;
import java.util.List;
import com.ave.simplestationsmason.SimpleStationsMason;

import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = SimpleStationsMason.MODID)
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
        generator.addProvider(event.includeServer(), new ModRecipeProvider(out, lookup));
        generator.addProvider(true,
                new LootTableProvider(out, Collections.emptySet(),
                        List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new,
                                LootContextParamSets.BLOCK)),
                        lookup));

    }
}
