package com.ave.simplestationsmanor.blockentity.handlers;

import com.ave.simplestationsmanor.blockentity.BaseStationBlockEntity;
import com.ave.simplestationsmanor.blockentity.ModContainer;
import com.ave.simplestationsmanor.blockentity.enums.CropGroup;
import com.ave.simplestationsmanor.blockentity.enums.CropType;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.items.ItemStackHandler;

public class SidedItemHandler extends ItemStackHandler {
    private final CropGroup group;

    public SidedItemHandler(int size, CropGroup group) {
        super(size);
        this.group = group;
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        if (slot == ModContainer.OUTPUT_SLOT)
            return false;

        if (slot == BaseStationBlockEntity.FLUID_SLOT)
            return stack.getItem() == (group == CropGroup.Dark ? Items.LAVA_BUCKET : Items.WATER_BUCKET);

        if (slot == BaseStationBlockEntity.FERTI_SLOT)
            return stack.getItem() == this.group.fertilizer;
        if (slot == BaseStationBlockEntity.REDSTONE_SLOT)
            return stack.getItem() == Items.REDSTONE_BLOCK || stack.getItem() == Items.REDSTONE;
        if (slot == BaseStationBlockEntity.TYPE_SLOT)
            return CropType.findBySeed(stack.getItem()).group == group;

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