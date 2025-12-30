package com.ave.simplestationsmason.screen;

import com.ave.simplestationscore.mainblock.StationContainer;
import com.ave.simplestationscore.screen.BaseStationMenu;
import com.ave.simplestationsmason.blockentity.ExcavatorBlockEntity;
import com.ave.simplestationsmason.registrations.Registrations;
import com.ave.simplestationsmason.uihelpers.UIBlocks;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;

public class ExcavatorMenu extends BaseStationMenu {
    public ExcavatorMenu(int containerId, Inventory inventory, FriendlyByteBuf data) {
        super(containerId, inventory, data, Registrations.EXCAVATOR_MENU.get());
    }

    public ExcavatorMenu(int containerId, Inventory inventory, StationContainer be) {
        super(containerId, inventory, be, Registrations.EXCAVATOR_MENU.get());
    }

    @Override
    public void addItemSlots() {
        addItemSlot(blockEntity.inventory, ExcavatorBlockEntity.FUEL_SLOT, UIBlocks.FUEL_SLOT);
        addItemSlot(blockEntity.inventory, ExcavatorBlockEntity.OUTPUT_SLOT, UIBlocks.OUT_SLOT);
        addItemSlot(blockEntity.inventory, ExcavatorBlockEntity.TYPE_SLOT, UIBlocks.FILTER_SLOT);
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player,
                Registrations.EXCAVATOR.getBlock());
    }
}
