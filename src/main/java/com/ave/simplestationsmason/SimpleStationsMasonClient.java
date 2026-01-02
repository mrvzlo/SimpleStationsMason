package com.ave.simplestationsmason;

import com.ave.simplestationscore.partblock.PartBlockEntity;
import com.ave.simplestationscore.registrations.RegistrationManager;
import com.ave.simplestationsmason.blockentity.ExcavatorBlockEntity;
import com.ave.simplestationsmason.blockentity.FurnaceBlockEntity;
import com.ave.simplestationsmason.blockentity.MixerBlockEntity;
import com.ave.simplestationsmason.blockentity.SifterBlockEntity;
import com.ave.simplestationsmason.registrations.Registrations;
import com.ave.simplestationsmason.renderer.ExcavatorRenderer;
import com.ave.simplestationsmason.renderer.FurnaceRenderer;
import com.ave.simplestationsmason.renderer.SifterRenderer;
import com.ave.simplestationsmason.screen.SifterScreen;
import com.ave.simplestationsmason.screen.ExcavatorScreen;
import com.ave.simplestationsmason.screen.FurnaceScreen;
import com.ave.simplestationsmason.screen.MixerScreen;

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
@Mod(value = SimpleStationsMason.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods
// in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = SimpleStationsMason.MODID, value = Dist.CLIENT)
public class SimpleStationsMasonClient {
    public SimpleStationsMasonClient(ModContainer container) {
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
        event.register(Registrations.EXCAVATOR_MENU.get(), ExcavatorScreen::new);
        event.register(Registrations.MIXER_MENU.get(), MixerScreen::new);
        event.register(Registrations.FURNACE_MENU.get(), FurnaceScreen::new);
        event.register(Registrations.SIFTER_MENU.get(), SifterScreen::new);
    }

    @SubscribeEvent
    public static void registerCaps(RegisterCapabilitiesEvent event) {
        RegistrationManager.registerCaps(event, Registrations.EXCAVATOR.getEntity());
        RegistrationManager.registerCaps(event, Registrations.MIXER.getEntity());
        RegistrationManager.registerCaps(event, Registrations.FURNACE.getEntity());
        RegistrationManager.registerCaps(event, Registrations.SIFTER.getEntity());
    }

    @SubscribeEvent // on the mod event bus only on the physical client
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(Registrations.EXCAVATOR.getEntity(), ExcavatorRenderer::new);
        event.registerBlockEntityRenderer(Registrations.SIFTER.getEntity(), SifterRenderer::new);
        event.registerBlockEntityRenderer(Registrations.FURNACE.getEntity(), FurnaceRenderer::new);
    }
}
