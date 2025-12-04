package com.ave.simplestationsmanor;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
@EventBusSubscriber(modid = SimpleStationsManor.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config {
        private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
        static ModConfigSpec SPEC;

        public static ModConfigSpec.IntValue MAX_PROGRESS; // 24000

        public static ModConfigSpec.IntValue WATER_PER_CYCLE; // 1000
        public static ModConfigSpec.IntValue WATER_MAX; // 10000

        public static ModConfigSpec.IntValue FERT_PER_ITEM; // 300
        public static ModConfigSpec.IntValue FERT_MAX; // 1000
        public static ModConfigSpec.DoubleValue FERT_MULT; // +2.0 (+200%)

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
                WATER_PER_CYCLE = BUILDER
                                .comment("How much water to consume per tick\n Default: 1")
                                .defineInRange("water_per_cycle", 1, 1, 1000);
                WATER_MAX = BUILDER
                                .comment("Max water to store\n Default: 10000")
                                .defineInRange("water_max", 10000, 1, 30000);
                FERT_PER_ITEM = BUILDER
                                .comment("How much fertilizer one item adds\n Default: 3000")
                                .defineInRange("fert_per_item", 3000, 1, 10000);
                FERT_MAX = BUILDER
                                .comment("Max fertilizer to store\n Default: 24000")
                                .defineInRange("fert_max", 24000, 1, 30000);
                FERT_MULT = BUILDER
                                .comment("Fertilizer productivity multiplier\n Default: +5.0 (i.e. +500%)")
                                .defineInRange("fert_mult", 5.0, 0.1, 10.0);
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
