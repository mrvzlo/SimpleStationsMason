package com.ave.simplestationsmason.blockentity.handlers;

import java.util.Arrays;

import com.ave.simplestationsmason.blockentity.ExcavatorBlockEntity;
import com.ave.simplestationsmason.registrations.ModBlocks;
import net.minecraft.world.item.ItemStack;

public class ExcavatorInputHandler extends BaseSidedItemHandler {
    public ExcavatorInputHandler(int size) {
        super(size);
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        if (slot == ExcavatorBlockEntity.OUTPUT_SLOT)
            return false;

        if (slot == ExcavatorBlockEntity.TYPE_SLOT)
            return Arrays.stream(ModBlocks.EXCAVATABLE).anyMatch(stack.getItem()::equals);

        return super.isItemValid(slot, stack);
    }

    @Override
    public int getSlotLimit(int slot) {
        if (slot == ExcavatorBlockEntity.TYPE_SLOT)
            return 1;
        return super.getSlotLimit(slot);
    }
}