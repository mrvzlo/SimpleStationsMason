package com.ave.simplestationsmason;

import org.slf4j.Logger;

import com.ave.simplestationscore.registrations.CoreRegistrations;
import com.ave.simplestationsmason.datagen.ModRecipes;
import com.ave.simplestationsmason.registrations.Registrations;
import com.mojang.logging.LogUtils;

import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(SimpleStationsMason.MODID)
public class SimpleStationsMason {
        public static final String MODID = "simplestationsmason";
        public static final Logger LOGGER = LogUtils.getLogger();

        public SimpleStationsMason(FMLJavaModLoadingContext context) {
                IEventBus modEventBus = context.getModEventBus();
                context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
                Registrations.MANAGER.register(modEventBus);
                ModRecipes.register(modEventBus);
                modEventBus.addListener(this::addCreative);
        }

        private void addCreative(BuildCreativeModeTabContentsEvent event) {
                if (!event.getTab().equals(CoreRegistrations.CREATIVE_TAB.get()))
                        return;
                event.accept(Registrations.EXCAVATOR.getItem());
                event.accept(Registrations.MIXER.getItem());
                event.accept(Registrations.FURNACE.getItem());
                event.accept(Registrations.SIFTER.getItem());
                event.accept(Registrations.WHEEL.get());
                event.accept(Registrations.COIN.get());
                event.accept(Registrations.BUCKET.get());
        }
}