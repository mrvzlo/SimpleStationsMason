package com.ave.simplestationsmason.blockentity.handlers;

import com.ave.simplestationsmason.blockentity.BaseStationBlockEntity;
import com.ave.simplestationsmason.blockentity.StationContainer;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.items.ItemStackHandler;

public abstract class BaseSidedItemHandler extends ItemStackHandler {

    public BaseSidedItemHandler(int size) {
        super(size);
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        if (slot == StationContainer.OUTPUT_SLOT)
            return false;
        if (slot == BaseStationBlockEntity.FUEL_SLOT)
            return stack.getItem() == Items.COAL_BLOCK || stack.getItem() == Items.COAL;
        return super.isItemValid(slot, stack);
    }

    @Override
    public int getSlotLimit(int slot) {
        if (slot == BaseStationBlockEntity.TYPE_SLOT)
            return 1;
        return super.getSlotLimit(slot);
    }

    public NonNullList<ItemStack> getAsList() {
        return stacks;
    }
}