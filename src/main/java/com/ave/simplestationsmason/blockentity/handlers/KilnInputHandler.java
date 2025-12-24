package com.ave.simplestationsmason.blockentity.handlers;

import com.ave.simplestationsmason.blockentity.BaseStationBlockEntity;
import com.ave.simplestationsmason.blockentity.KilnBlockEntity;
import com.ave.simplestationsmason.blockentity.enums.KilnType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraftforge.common.Tags;

public class KilnInputHandler extends BaseSidedItemHandler {
    public KilnInputHandler(int size) {
        super(size);
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        if (slot == KilnBlockEntity.TYPE_SLOT)
            return !KilnType.findBySource(stack.getItem()).equals(KilnType.Unknown);
        if (slot == BaseStationBlockEntity.FUEL_SLOT)
            return AbstractFurnaceBlockEntity.isFuel(stack);
        if (slot == KilnBlockEntity.COLOR_SLOT)
            return stack.is(Tags.Items.DYES);

        return super.isItemValid(slot, stack);
    }
}