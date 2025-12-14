package com.ave.simplestationsmason;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
@SuppressWarnings("removal")
@EventBusSubscriber(modid = SimpleStationsMason.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config {
        private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
        static ModConfigSpec SPEC;

        public static ModConfigSpec.IntValue MAX_EXC_PROGRESS;
        public static ModConfigSpec.IntValue MAX_MIX_PROGRESS;
        public static ModConfigSpec.IntValue WATER_MAX; // 10000
        public static ModConfigSpec.IntValue FUEL_PER_COAL;
        public static ModConfigSpec.IntValue POWER_MAX; // 10000

        static {
                setupGenerationConfig();
                SPEC = BUILDER.build();
        }

        private static void setupGenerationConfig() {
                MAX_EXC_PROGRESS = BUILDER
                                .comment("Base excavator working time in ticks\n Default: 40")
                                .defineInRange("exc_work_time", 40, 1, 10000);
                MAX_MIX_PROGRESS = BUILDER
                                .comment("Base mixer working time in ticks\n Default: 600")
                                .defineInRange("mix_work_time", 600, 1, 10000);
                WATER_MAX = BUILDER
                                .comment("Max water to store\n Default: 10000")
                                .defineInRange("water_max", 10000, 1, 30000);

                FUEL_PER_COAL = BUILDER
                                .comment("Base RF amount received from 1 coal\n Default: 48000")
                                .defineInRange("fuel_rf", 48000, 1, 1000000);
                POWER_MAX = BUILDER
                                .comment("Max redstone power to store\n Default: 100000")
                                .defineInRange("power_max", 100000, 1, 10000000);
        }

        @SubscribeEvent
        static void onLoad(final ModConfigEvent event) {

        }

}
