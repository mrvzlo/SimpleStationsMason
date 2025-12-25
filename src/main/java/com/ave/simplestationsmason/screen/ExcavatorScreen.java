package com.ave.simplestationsmason.screen;

import com.ave.simplestationscore.mainblock.BaseStationBlockEntity;
import com.ave.simplestationscore.screen.BaseStationMenu;
import com.ave.simplestationscore.screen.BaseStationScreen;
import com.ave.simplestationsmason.SimpleStationsMason;
import com.ave.simplestationsmason.uihelpers.UIBlocks;

import net.minecraft.client.gui.GuiGraphics;
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
        return Component.translatable("screen.simplestationsmason.excavator");
    }

    public ResourceLocation getTexture() {
        return TEXTURE;
    }

    protected void renderMoreTooltips(GuiGraphics gfx, int mouseX, int mouseY, BaseStationBlockEntity station) {
        int x = getStartX();
        int y = getStartY();
        if (station.type < 0 && UIBlocks.FILTER_SLOT.isHovered(mouseX - x, mouseY - y)) {
            gfx.renderTooltip(font, Component.translatable("screen.simplestationsmason.filter"), mouseX, mouseY);
        }
        renderProgressTooltip(gfx, UIBlocks.PROGRESS_BAR, mouseX, mouseY, station);
        renderPowerTooltip(gfx, UIBlocks.POWER_BAR, mouseX, mouseY, station);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float tick, int mx, int my) {
        super.renderBg(graphics, tick, mx, my);
        if (!(menu.blockEntity instanceof BaseStationBlockEntity station))
            return;
        renderProgressBar(graphics, station, UIBlocks.PROGRESS_BAR);
        renderPowerBar(graphics, station, UIBlocks.POWER_BAR, UIBlocks.FUEL_SLOT);
    }
}
