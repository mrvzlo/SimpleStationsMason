package com.ave.simplestationsmason;

import org.slf4j.Logger;

import com.ave.simplestationsmason.datagen.ModRecipes;
import com.ave.simplestationsmason.registrations.ModBlockEntities;
import com.ave.simplestationsmason.registrations.ModBlocks;
import com.ave.simplestationsmason.screen.ModMenuTypes;
import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(SimpleStationsMason.MODID)
public class SimpleStationsMason {
        public static final String MODID = "simplestationsmason";
        public static final Logger LOGGER = LogUtils.getLogger();
        public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
                        .create(Registries.CREATIVE_MODE_TAB, MODID);

        public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS
                        .register("example_tab", () -> CreativeModeTab.builder()
                                        .title(Component.translatable("itemGroup.simplestations.mason"))
                                        .withTabsBefore(CreativeModeTabs.COMBAT)
                                        .icon(() -> ModBlocks.EXCAVATOR_BLOCK_ITEM.get().getDefaultInstance())
                                        .displayItems((parameters, output) -> {
                                                output.accept(ModBlocks.EXCAVATOR_BLOCK_ITEM.get());
                                                output.accept(ModBlocks.MIXER_BLOCK_ITEM.get());
                                                output.accept(ModBlocks.KILN_BLOCK_ITEM.get());
                                                output.accept(ModBlocks.SIFTER_BLOCK_ITEM.get());
                                                output.accept(ModBlocks.WHEEL.get());
                                                output.accept(ModBlocks.COIN.get());
                                                output.accept(ModBlocks.BUCKET.get());
                                        }).build());

        public SimpleStationsMason(FMLJavaModLoadingContext context) {
                IEventBus modEventBus = context.getModEventBus();
                context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
                ModBlocks.BLOCKS.register(modEventBus);
                ModBlocks.ITEMS.register(modEventBus);
                CREATIVE_MODE_TABS.register(modEventBus);
                ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
                ModMenuTypes.register(modEventBus);
                ModRecipes.register(modEventBus);
        }
}