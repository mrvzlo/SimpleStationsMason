package com.ave.simplestationsmason.blockentity.handlers;

import java.util.Arrays;

import com.ave.simplestationscore.handlers.CommonItemHandler;
import com.ave.simplestationsmason.blockentity.ExcavatorBlockEntity;
import com.ave.simplestationsmason.registrations.Registrations;
import net.minecraft.world.item.ItemStack;

public class ExcavatorItemHandler extends CommonItemHandler {
    public ExcavatorItemHandler(int size) {
        super(size);
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        if (slot == ExcavatorBlockEntity.TYPE_SLOT)
            return Arrays.stream(Registrations.EXCAVATABLE).anyMatch(stack::is);

        return super.isItemValid(slot, stack);
    }

    @Override
    public int getSlotLimit(int slot) {
        if (slot == ExcavatorBlockEntity.TYPE_SLOT)
            return 1;
        return super.getSlotLimit(slot);
    }
}