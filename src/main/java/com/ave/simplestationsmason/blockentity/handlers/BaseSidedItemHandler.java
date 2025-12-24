package com.ave.simplestationsmason.blockentity.handlers;

import com.ave.simplestationsmason.blockentity.BaseStationBlockEntity;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.ItemStackHandler;

public abstract class BaseSidedItemHandler extends ItemStackHandler {

    public BaseSidedItemHandler(int size) {
        super(size);
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        if (slot == BaseStationBlockEntity.OUTPUT_SLOT)
            return false;
        if (slot == BaseStationBlockEntity.FUEL_SLOT)
            return stack.getItem() == Items.COAL_BLOCK || stack.getItem() == Items.COAL;
        return super.isItemValid(slot, stack);
    }

    public NonNullList<ItemStack> getAsList() {
        return stacks;
    }
}