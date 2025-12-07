package com.ave.simplestationsmason.screen;

import com.ave.simplestationsmason.SimpleStationsMason;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ExcavatorScreen extends BaseStationScreen {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SimpleStationsMason.MODID,
            "textures/gui/excavator_gui.png");

    public ExcavatorScreen(BaseStationMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public Component getTitle() {
        return Component.translatable("screen.simplestationsmason.mason");
    }

    @Override
    public int getFertColor() {
        return 0xAAEEFFFF;
    }

    @Override
    public ResourceLocation getTexture() {
        return TEXTURE;
    }
}
