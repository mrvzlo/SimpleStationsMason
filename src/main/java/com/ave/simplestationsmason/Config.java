package com.ave.simplestationsmason;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
@EventBusSubscriber(modid = SimpleStationsMason.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config {
        private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
        static ModConfigSpec SPEC;

        public static ModConfigSpec.IntValue MAX_PROGRESS; // 24000

        public static ModConfigSpec.IntValue POWER_PER_RED; // 300
        public static ModConfigSpec.IntValue POWER_MAX; // 10000
        public static ModConfigSpec.DoubleValue POWER_MULT; // +1.0 (+200%)

        static {
                setupGenerationConfig();
                SPEC = BUILDER.build();
        }

        private static void setupGenerationConfig() {
                MAX_PROGRESS = BUILDER
                                .comment("Base working time in ticks\n Default: 24000")
                                .defineInRange("work_time", 24000, 1, 100000);

                POWER_PER_RED = BUILDER
                                .comment("How much power one redstone adds\n Default: 1800")
                                .defineInRange("power_per_red", 1800, 1, 10000);
                POWER_MAX = BUILDER
                                .comment("Max redstone power to store\n Default: 10000")
                                .defineInRange("power_max", 24000, 1, 100000);
                POWER_MULT = BUILDER
                                .comment("Redstone productivity multiplier\n Default: +2.0 (i.e. +200%)")
                                .defineInRange("power_mult", 2.0, 0.1, 10.0);
        }

        @SubscribeEvent
        static void onLoad(final ModConfigEvent event) {

        }

}
