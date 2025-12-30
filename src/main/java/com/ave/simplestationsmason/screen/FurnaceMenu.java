package com.ave.simplestationsmason.screen;

import com.ave.simplestationscore.mainblock.BaseStationBlockEntity;
import com.ave.simplestationscore.mainblock.StationContainer;
import com.ave.simplestationscore.screen.BaseStationMenu;
import com.ave.simplestationsmason.blockentity.FurnaceBlockEntity;
import com.ave.simplestationsmason.registrations.Registrations;
import com.ave.simplestationsmason.uihelpers.UIBlocks;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;

public class FurnaceMenu extends BaseStationMenu {
    public FurnaceMenu(int containerId, Inventory inventory, FriendlyByteBuf data) {
        super(containerId, inventory, data, Registrations.FURNACE_MENU.get());
    }

    public FurnaceMenu(int containerId, Inventory inventory, StationContainer be) {
        super(containerId, inventory, be, Registrations.FURNACE_MENU.get());
    }

    @Override
    public void addItemSlots() {
        addItemSlot(blockEntity.inventory, FurnaceBlockEntity.FUEL_SLOT, UIBlocks.FUEL_SLOT);
        addItemSlot(blockEntity.inventory, FurnaceBlockEntity.OUTPUT_SLOT, UIBlocks.OUT_SLOT);
        addItemSlot(blockEntity.inventory, FurnaceBlockEntity.TYPE_SLOT, UIBlocks.FILTER_SLOT);
        addItemSlot(blockEntity.inventory, FurnaceBlockEntity.COLOR_SLOT, UIBlocks.FILTER3_SLOT);
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player,
                Registrations.FURNACE.getBlock());
    }

    @Override
    public void addDataSlots(BaseStationBlockEntity station) {
        var kiln = (FurnaceBlockEntity) station;
        super.addDataSlots(station);

        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return kiln.hasColor ? 1 : 0;
            }

            @Override
            public void set(int value) {
                kiln.hasColor = value != 0;
            }
        });
    }
}
