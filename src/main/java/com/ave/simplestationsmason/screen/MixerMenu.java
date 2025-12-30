package com.ave.simplestationsmason.screen;

import com.ave.simplestationscore.mainblock.BaseStationBlockEntity;
import com.ave.simplestationscore.mainblock.StationContainer;
import com.ave.simplestationscore.screen.BaseStationMenu;
import com.ave.simplestationsmason.blockentity.MixerBlockEntity;
import com.ave.simplestationsmason.registrations.Registrations;
import com.ave.simplestationsmason.uihelpers.UIBlocks;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;

public class MixerMenu extends BaseStationMenu {
    public MixerMenu(int containerId, Inventory inventory, FriendlyByteBuf data) {
        super(containerId, inventory, data, ModMenuTypes.MIXER_MENU.get());
    }

    public MixerMenu(int containerId, Inventory inventory, StationContainer be) {
        super(containerId, inventory, be, ModMenuTypes.MIXER_MENU.get());
    }

    @Override
    public void addItemSlots() {
        addItemSlot(blockEntity.inventory, MixerBlockEntity.FUEL_SLOT, UIBlocks.FUEL_SLOT);
        addItemSlot(blockEntity.inventory, MixerBlockEntity.OUTPUT_SLOT, UIBlocks.OUT_SLOT);
        addItemSlot(blockEntity.inventory, MixerBlockEntity.WATER_SLOT, UIBlocks.WATER_SLOT);
        addItemSlot(blockEntity.inventory, MixerBlockEntity.SAND_SLOT, UIBlocks.FILTER_SLOT);
        addItemSlot(blockEntity.inventory, MixerBlockEntity.GRAVEL_SLOT, UIBlocks.FILTER2_SLOT);
        addItemSlot(blockEntity.inventory, MixerBlockEntity.COLOR_SLOT, UIBlocks.FILTER3_SLOT);
    }

    @Override
    public void addDataSlots(BaseStationBlockEntity station) {
        var mixer = (MixerBlockEntity) station;
        super.addDataSlots(station);
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return mixer.waterValue;
            }

            @Override
            public void set(int value) {
                mixer.waterValue = value;
            }
        });
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player,
                Registrations.MIXER.getBlock());
    }
}
