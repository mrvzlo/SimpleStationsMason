package com.ave.simplestationsmason;

import org.slf4j.Logger;

import com.ave.simplestationscore.partblock.PartBlockEntity;
import com.ave.simplestationscore.registrations.RegistrationManager;
import com.ave.simplestationsmason.blockentity.ExcavatorBlockEntity;
import com.ave.simplestationsmason.blockentity.MixerBlockEntity;
import com.ave.simplestationsmason.blockentity.SifterBlockEntity;
import com.ave.simplestationsmason.datagen.ModRecipes;
import com.ave.simplestationsmason.registrations.Registrations;
import com.ave.simplestationsmason.screen.ModMenuTypes;
import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(SimpleStationsMason.MODID)
public class SimpleStationsMason {
        public static final String MODID = "simplestationsmason";
        public static final Logger LOGGER = LogUtils.getLogger();
        public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
                        .create(Registries.CREATIVE_MODE_TAB, MODID);

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS
                        .register("example_tab", () -> CreativeModeTab.builder()
                                        .title(Component.translatable("itemGroup.simplestations.mason"))
                                        .withTabsBefore(CreativeModeTabs.COMBAT)
                                        .icon(() -> Registrations.EXCAVATOR.getItem().getDefaultInstance())
                                        .displayItems((parameters, output) -> {
                                                output.accept(Registrations.EXCAVATOR.getItem());
                                                output.accept(Registrations.MIXER.getItem());
                                                output.accept(Registrations.FURNACE.getItem());
                                                output.accept(Registrations.SIFTER.getItem());
                                                output.accept(Registrations.WHEEL.get());
                                                output.accept(Registrations.COIN.get());
                                                output.accept(Registrations.BUCKET.get());
                                        }).build());

        public SimpleStationsMason(IEventBus modEventBus, ModContainer modContainer) {
                modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
                Registrations.MANAGER.register(modEventBus);
                CREATIVE_MODE_TABS.register(modEventBus);
                ModMenuTypes.register(modEventBus);
                ModRecipes.register(modEventBus);
                modEventBus.addListener(this::registerCapabilities);
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