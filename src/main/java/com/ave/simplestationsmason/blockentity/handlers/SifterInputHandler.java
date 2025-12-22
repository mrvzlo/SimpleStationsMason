package com.ave.simplestationsmason.blockentity.handlers;

import com.ave.simplestationsmason.blockentity.SifterBlockEntity;
import com.ave.simplestationsmason.datagen.ModTags;
import com.ave.simplestationsmason.registrations.ModBlocks;

import net.minecraft.world.item.ItemStack;

public class SifterInputHandler extends BaseSidedItemHandler {
    public SifterInputHandler(int size) {
        super(size);
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        if (slot == SifterBlockEntity.TYPE_SLOT)
            return stack.is(ModTags.Items.SIFTABLE_TAG);
        if (slot == SifterBlockEntity.COIN_SLOT)
            return stack.is(ModBlocks.COIN.get());
        return super.isItemValid(slot, stack);
    }
}