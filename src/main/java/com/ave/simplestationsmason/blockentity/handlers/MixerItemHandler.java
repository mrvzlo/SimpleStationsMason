package com.ave.simplestationsmason.blockentity.handlers;

import com.ave.simplestationscore.handlers.CommonItemHandler;
import com.ave.simplestationsmason.blockentity.MixerBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;

public class MixerItemHandler extends CommonItemHandler {
    public MixerItemHandler(int size) {
        super(size);
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        if (slot == MixerBlockEntity.OUTPUT_SLOT)
            return false;

        if (slot == MixerBlockEntity.SAND_SLOT)
            return stack.is(Items.SAND);
        if (slot == MixerBlockEntity.GRAVEL_SLOT)
            return stack.is(Items.GRAVEL);
        if (slot == MixerBlockEntity.WATER_SLOT)
            return stack.is(Items.WATER_BUCKET);
        if (slot == MixerBlockEntity.COLOR_SLOT)
            return stack.is(Tags.Items.DYES);

        return super.isItemValid(slot, stack);
    }

}