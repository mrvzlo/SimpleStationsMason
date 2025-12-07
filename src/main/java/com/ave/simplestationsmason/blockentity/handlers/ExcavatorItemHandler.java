package com.ave.simplestationsmason.blockentity.handlers;

import java.util.Arrays;

import com.ave.simplestationsmason.blockentity.BaseStationBlockEntity;
import com.ave.simplestationsmason.blockentity.StationContainer;
import com.ave.simplestationsmason.registrations.ModBlocks;
import net.minecraft.world.item.ItemStack;

public class ExcavatorItemHandler extends BaseSidedItemHandler {
    public ExcavatorItemHandler(int size) {
        super(size);
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        if (slot == StationContainer.OUTPUT_SLOT)
            return false;

        if (slot == BaseStationBlockEntity.TYPE_SLOT)
            return Arrays.stream(ModBlocks.EXCAVATABLE).anyMatch(stack.getItem()::equals);

        return super.isItemValid(slot, stack);
    }
}