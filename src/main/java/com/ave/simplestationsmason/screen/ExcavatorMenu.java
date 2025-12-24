package com.ave.simplestationsmason.screen;

import com.ave.simplestationsmason.blockentity.ExcavatorBlockEntity;
import com.ave.simplestationsmason.blockentity.StationContainer;
import com.ave.simplestationsmason.registrations.ModBlocks;
import com.ave.simplestationsmason.uihelpers.UIBlocks;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraftforge.items.SlotItemHandler;

public class ExcavatorMenu extends BaseStationMenu {
    public ExcavatorMenu(int containerId, Inventory inventory, FriendlyByteBuf data) {
        super(containerId, inventory, data, ModMenuTypes.EXCAVATOR_MENU.get());
    }

    public ExcavatorMenu(int containerId, Inventory inventory, StationContainer be) {
        super(containerId, inventory, be, ModMenuTypes.EXCAVATOR_MENU.get());
    }

    @Override
    public void addItemSlots() {
        addSlot(new SlotItemHandler(blockEntity.inventory, ExcavatorBlockEntity.TYPE_SLOT, UIBlocks.FILTER_SLOT.left,
                UIBlocks.FILTER_SLOT.top));
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player,
                ModBlocks.EXCAVATOR_BLOCK.get());
    }
}
