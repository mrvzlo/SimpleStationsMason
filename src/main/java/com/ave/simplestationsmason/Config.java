package com.ave.simplestationsmason;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
@Mod.EventBusSubscriber(modid = SimpleStationsMason.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
        private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
        static ForgeConfigSpec SPEC;

        public static ForgeConfigSpec.IntValue MAX_EXC_PROGRESS;
        public static ForgeConfigSpec.IntValue MAX_MIX_PROGRESS;
        public static ForgeConfigSpec.IntValue MAX_KILN_PROGRESS;
        public static ForgeConfigSpec.IntValue MAX_SIFTER_PROGRESS;
        public static ForgeConfigSpec.IntValue WATER_MAX;
        public static ForgeConfigSpec.IntValue FUEL_PER_COAL;
        public static ForgeConfigSpec.IntValue POWER_MAX;
        public static ForgeConfigSpec.IntValue TEMP_RISE_SPEED;

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

                WATER_MAX = BUILDER
                                .comment("Max water to store\n Default: 10000")
                                .defineInRange("water_max", 10000, 1, 30000);

                FUEL_PER_COAL = BUILDER
                                .comment("Base RF amount received from 1 coal\n Default: 48000")
                                .defineInRange("fuel_rf", 48000, 1, 1000000);
                POWER_MAX = BUILDER
                                .comment("Max redstone power to store\n Default: 100000")
                                .defineInRange("power_max", 100000, 1, 10000000);
                TEMP_RISE_SPEED = BUILDER
                                .comment("Temperature rising per tick\n Default: 10")
                                .defineInRange("temp_rise_speed", 10, 1, 100);
        }

        @SubscribeEvent
        static void onLoad(final ModConfigEvent event) {

        }

}
