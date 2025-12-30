package com.ave.simplestationsmason.screen;

import com.ave.simplestationscore.mainblock.StationContainer;
import com.ave.simplestationscore.screen.BaseStationMenu;
import com.ave.simplestationsmason.blockentity.SifterBlockEntity;
import com.ave.simplestationsmason.registrations.Registrations;
import com.ave.simplestationsmason.uihelpers.UIBlocks;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;

public class SifterMenu extends BaseStationMenu {
    public SifterMenu(int containerId, Inventory inventory, FriendlyByteBuf data) {
        super(containerId, inventory, data, ModMenuTypes.SIFTER_MENU.get());
    }

    public SifterMenu(int containerId, Inventory inventory, StationContainer be) {
        super(containerId, inventory, be, ModMenuTypes.SIFTER_MENU.get());
    }

    @Override
    public void addItemSlots() {
        addItemSlot(blockEntity.inventory, SifterBlockEntity.FUEL_SLOT, UIBlocks.FUEL_SLOT);
        addItemSlot(blockEntity.inventory, SifterBlockEntity.OUTPUT_SLOT, UIBlocks.OUT_SLOT);
        addItemSlot(blockEntity.inventory, SifterBlockEntity.TYPE_SLOT, UIBlocks.FILTER_SLOT);
        addItemSlot(blockEntity.inventory, SifterBlockEntity.COIN_SLOT, UIBlocks.FILTER3_SLOT);
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player,
                Registrations.SIFTER.getBlock());
    }
}
