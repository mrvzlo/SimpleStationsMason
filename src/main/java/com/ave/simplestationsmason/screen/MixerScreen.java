package com.ave.simplestationsmason.screen;

import java.util.Arrays;
import java.util.List;

import com.ave.simplestationsmason.Config;
import com.ave.simplestationsmason.SimpleStationsMason;
import com.ave.simplestationsmason.blockentity.BaseStationBlockEntity;
import com.ave.simplestationsmason.blockentity.MixerBlockEntity;
import com.ave.simplestationsmason.uihelpers.NumToString;
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
            String waterPart = NumToString.parse(mixer.waterValue / 1000f, "B / ")
                    + NumToString.parse(Config.WATER_MAX.get(), "B");
            List<Component> text = Arrays.asList(
                    Component.translatable("screen.simplestationsmason.water"),
                    Component.literal(waterPart));
            gfx.renderComponentTooltip(font, text, mouseX, mouseY);
        }
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float tick, int mx, int my) {
        super.renderBg(graphics, tick, mx, my);

        if (!(menu.blockEntity instanceof MixerBlockEntity station))
            return;
        float waterPart = (float) station.waterValue / Config.WATER_MAX.get();
        UIBlocks.WATER_BAR.drawProgressToTop(graphics, getStartX(), getStartY(), waterPart, 0xAA222299);
        if (station.fuelValue == 0)
            UIBlocks.WATER_SLOT.drawBorder(graphics, getStartX(), getStartY(), getWarningColor());
    }

}
