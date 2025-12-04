package com.ave.simplestationsmanor;

import com.ave.simplestationsmanor.blockentity.ExcavatorBlockEntity;
import com.ave.simplestationsmanor.blockentity.partblock.PartBlockEntity;
import com.ave.simplestationsmanor.registrations.ModBlockEntities;
import com.ave.simplestationsmanor.renderer.StationRenderer;
import com.ave.simplestationsmanor.screen.ModMenuTypes;
import com.ave.simplestationsmanor.screen.ExcavatorScreen;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = SimpleStationsManor.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods
// in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = SimpleStationsManor.MODID, value = Dist.CLIENT)
public class SimpleStationsManorClient {
    public SimpleStationsManorClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your
        // mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json
        // file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.EXCAVATOR_MENU.get(), ExcavatorScreen::new);
    }

    @SubscribeEvent
    public static void registerCaps(RegisterCapabilitiesEvent event) {
        ExcavatorBlockEntity.registerCaps(event);
        PartBlockEntity.registerCaps(event);
    }

    @SubscribeEvent // on the mod event bus only on the physical client
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.PART_ENTITY.get(), StationRenderer::new);
    }
}
