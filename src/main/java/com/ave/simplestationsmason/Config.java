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

        public static ModConfigSpec.IntValue MAX_EXC_PROGRESS;
        public static ModConfigSpec.IntValue MAX_MIX_PROGRESS;
        public static ModConfigSpec.IntValue MAX_KILN_PROGRESS;
        public static ModConfigSpec.IntValue MAX_SIFTER_PROGRESS;
        public static ModConfigSpec.IntValue TEMP_RISE_SPEED;

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
                MAX_KILN_PROGRESS = BUILDER
                                .comment("Base kiln working time in ticks\n Default: 120")
                                .defineInRange("kiln_work_time", 120, 1, 10000);
                MAX_SIFTER_PROGRESS = BUILDER
                                .comment("Base sifter working time in ticks\n Default: 1200")
                                .defineInRange("sifter_work_time", 1200, 1, 10000);
                TEMP_RISE_SPEED = BUILDER
                                .comment("Temperature rising per tick\n Default: 10")
                                .defineInRange("temp_rise_speed", 10, 1, 100);
        }

        @SubscribeEvent
        static void onLoad(final ModConfigEvent event) {

        }

}
