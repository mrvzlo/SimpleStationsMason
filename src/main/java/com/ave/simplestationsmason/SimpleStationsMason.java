package com.ave.simplestationsmason;

import org.slf4j.Logger;

import com.ave.simplestationscore.partblock.PartBlockEntity;
import com.ave.simplestationscore.registrations.RegistrationManager;
import com.ave.simplestationsmason.blockentity.ExcavatorBlockEntity;
import com.ave.simplestationsmason.blockentity.MixerBlockEntity;
import com.ave.simplestationsmason.blockentity.SifterBlockEntity;
import com.ave.simplestationsmason.datagen.ModRecipes;
import com.ave.simplestationsmason.registrations.Registrations;
import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(SimpleStationsMason.MODID)
public class SimpleStationsMason {
        public static final String MODID = "simplestationsmason";
        public static final Logger LOGGER = LogUtils.getLogger();

        public SimpleStationsMason(IEventBus modEventBus, ModContainer modContainer) {
                modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
                Registrations.MANAGER.register(modEventBus);
                ModRecipes.register(modEventBus);
                modEventBus.addListener(this::addCreative);
                modEventBus.addListener(this::registerCapabilities);
        }

        private void addCreative(BuildCreativeModeTabContentsEvent event) {
                if (!event.getTab().equals(Registrations.MANAGER.CREATIVE_TAB.get()))
                        return;
                event.accept(Registrations.EXCAVATOR.getItem());
                event.accept(Registrations.MIXER.getItem());
                event.accept(Registrations.FURNACE.getItem());
                event.accept(Registrations.SIFTER.getItem());
                event.accept(Registrations.WHEEL.get());
                event.accept(Registrations.COIN.get());
                event.accept(Registrations.BUCKET.get());
        }

        private void registerCapabilities(RegisterCapabilitiesEvent event) {
                event.registerBlock(Capabilities.EnergyStorage.BLOCK,
                                (level, pos, state, be, side) -> ((ExcavatorBlockEntity) be).getEnergyStorage(),
                                Registrations.EXCAVATOR.getBlock());
                event.registerBlock(Capabilities.EnergyStorage.BLOCK,
                                (level, pos, state, be, side) -> ((MixerBlockEntity) be).getEnergyStorage(),
                                Registrations.MIXER.getBlock());
                event.registerBlock(Capabilities.EnergyStorage.BLOCK,
                                (level, pos, state, be, side) -> ((SifterBlockEntity) be).getEnergyStorage(),
                                Registrations.SIFTER.getBlock());
                event.registerBlock(Capabilities.FluidHandler.BLOCK,
                                (level, pos, state, be, side) -> ((MixerBlockEntity) be).getWaterStorage(),
                                Registrations.MIXER.getBlock());

                event.registerBlock(
                                Capabilities.FluidHandler.BLOCK, (level, pos, state, be,
                                                side) -> ((PartBlockEntity) be).getWaterStorage((PartBlockEntity) be),
                                RegistrationManager.PART.getBlock());
                event.registerBlock(
                                Capabilities.EnergyStorage.BLOCK, (level, pos, state, be,
                                                side) -> ((PartBlockEntity) be).getEnergyStorage((PartBlockEntity) be),
                                RegistrationManager.PART.getBlock());
        }
}