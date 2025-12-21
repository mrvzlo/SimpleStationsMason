package com.ave.simplestationsmason.blockentity.handlers;

import com.ave.simplestationsmason.blockentity.KilnBlockEntity;
import com.ave.simplestationsmason.datagen.ModTags;

import net.minecraft.world.item.ItemStack;

public class SifterInputHandler extends BaseSidedItemHandler {
    public SifterInputHandler(int size) {
        super(size);
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        if (slot == KilnBlockEntity.TYPE_SLOT)
            return stack.is(ModTags.Items.SIFTABLE_TAG);
        return super.isItemValid(slot, stack);
    }
}