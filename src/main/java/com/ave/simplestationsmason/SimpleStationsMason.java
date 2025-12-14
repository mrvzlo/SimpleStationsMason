package com.ave.simplestationsmason;

import java.util.Arrays;

import org.slf4j.Logger;

import com.ave.simplestationsmason.blockentity.ExcavatorBlockEntity;
import com.ave.simplestationsmason.blockentity.MixerBlockEntity;
import com.ave.simplestationsmason.blockentity.partblock.PartBlockEntity;
import com.ave.simplestationsmason.registrations.ModBlockEntities;
import com.ave.simplestationsmason.registrations.ModBlocks;
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
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
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
                                        .icon(() -> ModBlocks.EXCAVATOR_BLOCK_ITEM.get().getDefaultInstance())
                                        .displayItems((parameters, output) -> {
                                                output.accept(ModBlocks.EXCAVATOR_BLOCK_ITEM.get());
                                                output.accept(ModBlocks.MIXER_BLOCK_ITEM.get());
                                                output.accept(ModBlocks.KILN_BLOCK_ITEM.get());
                                                output.accept(ModBlocks.WHEEL.get());
                                                Arrays.stream(ModBlocks.COLOR_DUST_ITEMS)
                                                                .forEach(x -> output.accept(x.get()));
                                        }).build());

        public SimpleStationsMason(IEventBus modEventBus, ModContainer modContainer) {
                modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
                ModBlocks.BLOCKS.register(modEventBus);
                ModBlocks.ITEMS.register(modEventBus);
                CREATIVE_MODE_TABS.register(modEventBus);
                ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
                ModMenuTypes.register(modEventBus);

                modEventBus.addListener(this::addCreative);
                modEventBus.addListener(this::registerCapabilities);
        }

        // Add the example block item to the building blocks tab
        private void addCreative(BuildCreativeModeTabContentsEvent event) {
                if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
                        event.accept(ModBlocks.EXCAVATOR_BLOCK_ITEM);
        }

        private void registerCapabilities(RegisterCapabilitiesEvent event) {
                event.registerBlock(Capabilities.EnergyStorage.BLOCK,
                                (level, pos, state, be, side) -> ((ExcavatorBlockEntity) be).getEnergyStorage(),
                                ModBlocks.EXCAVATOR_BLOCK.get());
                event.registerBlock(Capabilities.EnergyStorage.BLOCK,
                                (level, pos, state, be, side) -> ((MixerBlockEntity) be).getEnergyStorage(),
                                ModBlocks.MIXER_BLOCK.get());
                event.registerBlock(
                                Capabilities.EnergyStorage.BLOCK, (level, pos, state, be,
                                                side) -> ((PartBlockEntity) be).getEnergyStorage((PartBlockEntity) be),
                                ModBlocks.PART.get());
        }
}