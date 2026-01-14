package com.ave.simplestationsmason.screen;

import java.util.Arrays;
import java.util.List;

import com.ave.simplestationscore.CoreConfig;
import com.ave.simplestationscore.mainblock.BaseStationBlockEntity;
import com.ave.simplestationscore.screen.BaseStationMenu;
import com.ave.simplestationscore.screen.BaseStationScreen;
import com.ave.simplestationscore.uihelpers.NumToString;
import com.ave.simplestationsmason.SimpleStationsMason;
import com.ave.simplestationsmason.blockentity.MixerBlockEntity;
import com.ave.simplestationsmason.uihelpers.UIBlocks;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MixerScreen extends BaseStationScreen {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SimpleStationsMason.MODID,
            "textures/gui/mixer_gui.png");

    public MixerScreen(BaseStationMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public Component getTitle() {
        return Component.translatable("screen.simplestationsmason.mixer");
    }

    public ResourceLocation getTexture() {
        return TEXTURE;
    }

    protected void renderMoreTooltips(GuiGraphics gfx, int mouseX, int mouseY, BaseStationBlockEntity station) {
        int x = getStartX();
        int y = getStartY();
        var mixer = (MixerBlockEntity) station;
        if (station.type < 0 && UIBlocks.FILTER3_SLOT.isHovered(mouseX - x, mouseY - y)) {
            gfx.renderTooltip(font, Component.translatable("screen.simplestationsmason.color"), mouseX, mouseY);
        }
        if (UIBlocks.WATER_BAR.isHovered(mouseX - x, mouseY - y)) {
            String waterPart = NumToString.parse(mixer.getWaterResource().get() / 1000f, "B / ")
                    + NumToString.parse(CoreConfig.FLUID_MAX.get(), "B");
            List<Component> text = Arrays.asList(
                    Component.translatable("screen.simplestationsmason.water"),
                    Component.literal(waterPart));
            gfx.renderComponentTooltip(font, text, mouseX, mouseY);
        }
        renderProgressTooltip(gfx, UIBlocks.PROGRESS_BAR, mouseX, mouseY, station);
        renderPowerTooltip(gfx, UIBlocks.POWER_BAR, mouseX, mouseY, station);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float tick, int mx, int my) {
        super.renderBg(graphics, tick, mx, my);

        if (!(menu.blockEntity instanceof MixerBlockEntity station))
            return;
        float waterPart = station.getWaterResource().getPercent();
        UIBlocks.WATER_BAR.drawProgressToTop(graphics, getStartX(), getStartY(), waterPart, 0xAA222299);
        if (!station.getWaterResource().isEnough())
            UIBlocks.WATER_SLOT.drawBorder(graphics, getStartX(), getStartY(), getWarningColor());

        renderProgressBar(graphics, station, UIBlocks.PROGRESS_BAR);
        renderPowerBar(graphics, station, UIBlocks.POWER_BAR, UIBlocks.FUEL_SLOT);
    }
}
