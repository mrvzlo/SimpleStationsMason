package com.ave.simplestationsmanor.blockentity.handlers;

import com.ave.simplestationsmanor.blockentity.BaseStationBlockEntity;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;

public class OutputItemHandler implements IItemHandler {
    private final SidedItemHandler parent;

    public OutputItemHandler(SidedItemHandler parent) {
        this.parent = parent;
    }

    @Override
    public int getSlots() {
        return parent.getSlots();
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return parent.getStackInSlot(slot);
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        return stack;
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (slot != BaseStationBlockEntity.OUTPUT_SLOT)
            return ItemStack.EMPTY;
        return parent.extractItem(slot, amount, simulate);
    }

    @Override
    public int getSlotLimit(int slot) {
        return parent.getSlotLimit(slot);
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        return parent.isItemValid(slot, stack);
    }
}
