package com.ave.simplestationsmason.screen;

import com.ave.simplestationsmason.blockentity.BaseStationBlockEntity;
import com.ave.simplestationsmason.blockentity.KilnBlockEntity;
import com.ave.simplestationsmason.blockentity.StationContainer;
import com.ave.simplestationsmason.registrations.ModBlocks;
import com.ave.simplestationsmason.uihelpers.UIBlocks;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraftforge.items.SlotItemHandler;

public class KilnMenu extends BaseStationMenu {
    public KilnMenu(int containerId, Inventory inventory, FriendlyByteBuf data) {
        super(containerId, inventory, data, ModMenuTypes.KILN_MENU.get());
    }

    public KilnMenu(int containerId, Inventory inventory, StationContainer be) {
        super(containerId, inventory, be, ModMenuTypes.KILN_MENU.get());
    }

    @Override
    public void addItemSlots() {
        addSlot(new SlotItemHandler(blockEntity.inventory, KilnBlockEntity.TYPE_SLOT, UIBlocks.FILTER_SLOT.left,
                UIBlocks.FILTER_SLOT.top));
        addSlot(new SlotItemHandler(blockEntity.inventory, KilnBlockEntity.COLOR_SLOT, UIBlocks.FILTER3_SLOT.left,
                UIBlocks.FILTER3_SLOT.top));
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player,
                ModBlocks.KILN_BLOCK.get());
    }

    @Override
    public void addDataSlots(BaseStationBlockEntity station) {
        var kiln = (KilnBlockEntity) station;
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
