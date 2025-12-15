package com.ave.simplestationsmason.screen;

import java.util.Arrays;
import java.util.List;

import com.ave.simplestationsmason.SimpleStationsMason;
import com.ave.simplestationsmason.blockentity.BaseStationBlockEntity;
import com.ave.simplestationsmason.blockentity.KilnBlockEntity;
import com.ave.simplestationsmason.blockentity.managers.TemperatureManager;
import com.ave.simplestationsmason.uihelpers.UIBlocks;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class KilnScreen extends BaseStationScreen {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SimpleStationsMason.MODID,
            "textures/gui/kiln_gui.png");

    public KilnScreen(BaseStationMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public Component getTitle() {
        return Component.translatable("screen.simplestationsmason.kiln");
    }

    public ResourceLocation getTexture() {
        return TEXTURE;
    }

    protected void renderMoreTooltips(GuiGraphics gfx, int mouseX, int mouseY, BaseStationBlockEntity station) {
        int x = getStartX();
        int y = getStartY();
        var kiln = (KilnBlockEntity) station;
        if (!kiln.hasColor && UIBlocks.FILTER3_SLOT.isHovered(mouseX - x, mouseY - y)) {
            gfx.renderTooltip(font, Component.translatable("screen.simplestationsmason.color"), mouseX, mouseY);
        }
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float tick, int mx, int my) {
        super.renderBg(graphics, tick, mx, my);
    }

    @Override
    protected void renderPowerTooltip(GuiGraphics gfx, int mouseX, int mouseY, BaseStationBlockEntity station) {
        if (!UIBlocks.POWER_BAR.isHovered(mouseX - getStartX(), mouseY - getStartY()))
            return;
        String powerPart = station.fuelValue + "° / " + (int) TemperatureManager.MaxTemp + "°";
        List<Component> powerText = Arrays.asList(
                Component.translatable("screen.simplestationsmason.temperature"),
                Component.literal(powerPart));
        gfx.renderComponentTooltip(font, powerText, mouseX, mouseY);
    }

    @Override
    protected void renderPowerBar(GuiGraphics graphics, BaseStationBlockEntity station, int x, int y) {
        float powerPart = (float) station.fuelValue / TemperatureManager.MaxTemp;
        var color = 0xAA000000 | temperatureToRGB(powerPart);

        UIBlocks.POWER_BAR.drawProgressToTop(graphics, x, y, powerPart, color);
        if (station.fuelValue == 0)
            UIBlocks.FUEL_SLOT.drawBorder(graphics, x, y, getWarningColor());
    }

    protected int temperatureToRGB(float t) {
        t = Math.max(0, Math.min(1, t));
        int r = 255;
        int g = (int) Math.floor(40 + 120 * t);
        int b = (int) Math.floor(20 + 70 * t * t);
        return r << 16 | g << 8 | b;
    }
}
