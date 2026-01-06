package com.ave.simplestationsmason;

import com.ave.simplestationsmason.registrations.Registrations;
import com.ave.simplestationsmason.renderer.ExcavatorRenderer;
import com.ave.simplestationsmason.renderer.FurnaceRenderer;
import com.ave.simplestationsmason.renderer.SifterRenderer;
import com.ave.simplestationsmason.screen.SifterScreen;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import com.ave.simplestationsmason.screen.ExcavatorScreen;
import com.ave.simplestationsmason.screen.FurnaceScreen;
import com.ave.simplestationsmason.screen.MixerScreen;

@Mod.EventBusSubscriber(modid = SimpleStationsMason.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SimpleStationsMasonClient {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        MenuScreens.register(Registrations.EXCAVATOR_MENU.get(), ExcavatorScreen::new);
        MenuScreens.register(Registrations.MIXER_MENU.get(), MixerScreen::new);
        MenuScreens.register(Registrations.FURNACE_MENU.get(), FurnaceScreen::new);
        MenuScreens.register(Registrations.SIFTER_MENU.get(), SifterScreen::new);
    }

    @SubscribeEvent // on the mod event bus only on the physical client
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(Registrations.EXCAVATOR.getEntity(), ExcavatorRenderer::new);
        event.registerBlockEntityRenderer(Registrations.SIFTER.getEntity(), SifterRenderer::new);
        event.registerBlockEntityRenderer(Registrations.FURNACE.getEntity(), FurnaceRenderer::new);
    }
}
