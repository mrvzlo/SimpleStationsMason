package com.ave.simplestationsmason.screen;

import com.ave.simplestationsmason.blockentity.SifterBlockEntity;
import com.ave.simplestationsmason.blockentity.StationContainer;
import com.ave.simplestationsmason.registrations.ModBlocks;
import com.ave.simplestationsmason.uihelpers.UIBlocks;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.neoforged.neoforge.items.SlotItemHandler;

public class SifterMenu extends BaseStationMenu {
    public SifterMenu(int containerId, Inventory inventory, FriendlyByteBuf data) {
        super(containerId, inventory, data, ModMenuTypes.SIFTER_MENU.get());
    }

    public SifterMenu(int containerId, Inventory inventory, StationContainer be) {
        super(containerId, inventory, be, ModMenuTypes.SIFTER_MENU.get());
    }

    @Override
    public void addItemSlots() {
        addSlot(new SlotItemHandler(blockEntity.inventory, SifterBlockEntity.TYPE_SLOT, UIBlocks.FILTER_SLOT.left,
                UIBlocks.FILTER_SLOT.top));
        addSlot(new SlotItemHandler(blockEntity.inventory, SifterBlockEntity.COIN_SLOT, UIBlocks.FILTER3_SLOT.left,
                UIBlocks.FILTER3_SLOT.top));
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player,
                ModBlocks.SIFTER_BLOCK.get());
    }
}
