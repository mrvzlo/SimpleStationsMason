package com.ave.simplestationsmason.screen;

import java.util.Arrays;
import java.util.List;

import com.ave.simplestationsmason.Config;
import com.ave.simplestationsmason.blockentity.BaseStationBlockEntity;
import com.ave.simplestationsmason.uihelpers.NumToString;
import com.ave.simplestationsmason.uihelpers.UIBlocks;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public abstract class BaseStationScreen extends AbstractContainerScreen<BaseStationMenu> {

    public BaseStationScreen(BaseStationMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public void render(GuiGraphics gfx, int mouseX, int mouseY, float partialTicks) {
        super.render(gfx, mouseX, mouseY, partialTicks);
        this.renderTooltip(gfx, mouseX, mouseY);

        if (!(menu.blockEntity instanceof BaseStationBlockEntity station))
            return;

        if (station.progress > 0 && UIBlocks.PROGRESS_BAR.isHovered(mouseX - getStartX(), mouseY - getStartY())) {
            int progressPart = (int) Math.ceil(100 * station.progress / station.getMaxProgress());
            gfx.renderTooltip(font, Component.literal(progressPart + "%"), mouseX, mouseY);
        }

        renderPowerTooltip(gfx, mouseX, mouseY, station);
        renderMoreTooltips(gfx, mouseX, mouseY, station);
    }

    protected void renderPowerTooltip(GuiGraphics gfx, int mouseX, int mouseY, BaseStationBlockEntity station) {
        if (!UIBlocks.POWER_BAR.isHovered(mouseX - getStartX(), mouseY - getStartY()))
            return;
        String powerPart = NumToString.parse(station.fuelValue, "RF / ")
                + NumToString.parse(Config.POWER_MAX.get(), "RF");
        List<Component> powerText = Arrays.asList(
                Component.translatable("screen.simplestationsmason.power"),
                Component.literal(powerPart));
        gfx.renderComponentTooltip(font, powerText, mouseX, mouseY);
    }

    protected abstract void renderMoreTooltips(GuiGraphics gfx, int mouseX, int mouseY, BaseStationBlockEntity station);

    @Override
    protected void renderBg(GuiGraphics graphics, float tick, int mx, int my) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, getTexture());
        int x = getStartX();
        int y = getStartY();
        graphics.blit(getTexture(), x, y, 0, 0, imageWidth, imageHeight, imageWidth,
                imageHeight);

        int textWidth = font.width(getTitle());
        int centerX = (width / 2) - (textWidth / 2);
        graphics.drawString(font, getTitle(), centerX, y + 6, 0x222222, false);

        if (!(menu.blockEntity instanceof BaseStationBlockEntity station))
            return;

        float progressPart = station.progress / station.getMaxProgress();
        UIBlocks.PROGRESS_BAR.drawProgressToRight(graphics, x, y, progressPart, 0xFFCCFEDD);

        renderPowerBar(graphics, station, x, y);
    }

    protected void renderPowerBar(GuiGraphics graphics, BaseStationBlockEntity station, int x, int y) {
        float powerPart = (float) station.fuelValue / Config.POWER_MAX.get();
        UIBlocks.POWER_BAR.drawProgressToTop(graphics, x, y, powerPart, 0xAABB2211);
        if (station.fuelValue == 0)
            UIBlocks.FUEL_SLOT.drawBorder(graphics, x, y, getWarningColor());
    }

    protected int getWarningColor() {
        var tickAlpha = 96 + (int) (63 * Math.sin(System.currentTimeMillis() / 400.0));
        return (tickAlpha << 24) | 0xFF0000;
    }

    public abstract ResourceLocation getTexture();

    protected int getStartX() {
        return (width - imageWidth) / 2;
    }

    protected int getStartY() {
        return (height - imageHeight) / 2;
    }
}
