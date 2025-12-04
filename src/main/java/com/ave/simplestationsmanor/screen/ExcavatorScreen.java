package com.ave.simplestationsmanor.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ExcavatorScreen extends BaseStationScreen {

    public ExcavatorScreen(BaseStationMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public Component getTitle() {
        return Component.translatable("screen.simplestationsmanor.manor");
    }

    @Override
    public int getFertColor() {
        return 0xAAEEFFFF;
    }
}
