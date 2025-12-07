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

        int startX = (width - imageWidth) / 2;
        int startY = (height - imageHeight) / 2;

        if (UIBlocks.POWER_BAR.isHovered(mouseX - startX, mouseY - startY)) {
            String powerPart = NumToString.parse(station.fuel.getEnergyStored(), "RF / ")
                    + NumToString.parse(Config.POWER_MAX.get(), "RF");
            List<Component> powerText = Arrays.asList(
                    Component.translatable("screen.simplestationsmason.power"),
                    Component.literal(powerPart));
            gfx.renderComponentTooltip(font, powerText, mouseX, mouseY);
        }

        if (station.progress > 0 && UIBlocks.PROGRESS_BAR.isHovered(mouseX - startX, mouseY - startY)) {
            int progressPart = (int) Math.ceil(100 * station.progress / Config.MAX_PROGRESS.get());
            gfx.renderTooltip(font, Component.literal(progressPart + "%"), mouseX, mouseY);
        }

        if (station.type < 0 && UIBlocks.FILTER_SLOT.isHovered(mouseX - startX, mouseY - startY)) {
            gfx.renderTooltip(font, Component.translatable("screen.simplestationsmason.filter"), mouseX, mouseY);
        }
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float tick, int mx, int my) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, getTexture());
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        graphics.blit(getTexture(), x, y, 0, 0, imageWidth, imageHeight, imageWidth,
                imageHeight);

        int textWidth = font.width(getTitle());
        int centerX = (width / 2) - (textWidth / 2);
        graphics.drawString(font, getTitle(), centerX, y + 6, 0x222222, false);

        if (!(menu.blockEntity instanceof BaseStationBlockEntity station))
            return;

        int tickAlpha = 96 + (int) (63 * Math.sin(System.currentTimeMillis() / 400.0));
        int borderColor = (tickAlpha << 24) | 0xFF0000;
        float progressPart = station.progress / Config.MAX_PROGRESS.get();
        UIBlocks.PROGRESS_BAR.drawProgressToRight(graphics, x, y, progressPart, 0xFFCCFEDD);

        float powerPart = (float) station.fuel.getEnergyStored() / Config.POWER_MAX.get();
        UIBlocks.POWER_BAR.drawProgressToTop(graphics, x, y, powerPart, 0xAABB2211);
        if (station.fuel.getEnergyStored() == 0)
            UIBlocks.POWER_BAR.drawBorder(graphics, x, y, borderColor);

    }

    public int getFertColor() {
        return 0;
    }

    public int getFluidColor() {
        return 0xAA222299;
    }

    public ResourceLocation getTexture() {
        return null;
    }

    protected Component getFluidName() {
        return Component.translatable("screen.simplestationsmason.water");
    }
}
