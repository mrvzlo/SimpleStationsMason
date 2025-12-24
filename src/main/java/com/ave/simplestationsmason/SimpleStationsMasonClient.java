package com.ave.simplestationsmason;

import com.ave.simplestationsmason.registrations.ModBlockEntities;
import com.ave.simplestationsmason.renderer.ExcavatorRenderer;
import com.ave.simplestationsmason.renderer.KilnRenderer;
import com.ave.simplestationsmason.renderer.SifterRenderer;
import com.ave.simplestationsmason.screen.ModMenuTypes;
import com.ave.simplestationsmason.screen.SifterScreen;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import com.ave.simplestationsmason.screen.ExcavatorScreen;
import com.ave.simplestationsmason.screen.KilnScreen;
import com.ave.simplestationsmason.screen.MixerScreen;

@Mod.EventBusSubscriber(modid = SimpleStationsMason.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SimpleStationsMasonClient {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        MenuScreens.register(ModMenuTypes.EXCAVATOR_MENU.get(), ExcavatorScreen::new);
        MenuScreens.register(ModMenuTypes.MIXER_MENU.get(), MixerScreen::new);
        MenuScreens.register(ModMenuTypes.KILN_MENU.get(), KilnScreen::new);
        MenuScreens.register(ModMenuTypes.SIFTER_MENU.get(), SifterScreen::new);
    }

    @SubscribeEvent // on the mod event bus only on the physical client
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.EXCAVATOR_ENTITY.get(), ExcavatorRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.SIFTER_ENTITY.get(), SifterRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.KILN_ENTITY.get(), KilnRenderer::new);
    }
}
