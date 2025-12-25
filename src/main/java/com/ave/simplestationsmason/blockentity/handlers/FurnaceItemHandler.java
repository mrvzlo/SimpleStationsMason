package com.ave.simplestationsmason.blockentity.handlers;

import com.ave.simplestationscore.handlers.CommonItemHandler;
import com.ave.simplestationscore.mainblock.BaseStationBlockEntity;
import com.ave.simplestationsmason.blockentity.FurnaceBlockEntity;
import com.ave.simplestationsmason.blockentity.enums.KilnType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.common.Tags;

public class FurnaceItemHandler extends CommonItemHandler {
    public FurnaceItemHandler(int size) {
        super(size);
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        if (slot == FurnaceBlockEntity.TYPE_SLOT)
            return !KilnType.findBySource(stack.getItem()).equals(KilnType.Unknown);
        if (slot == BaseStationBlockEntity.FUEL_SLOT)
            return stack.getBurnTime(RecipeType.SMELTING) > 0;
        if (slot == FurnaceBlockEntity.COLOR_SLOT)
            return stack.is(Tags.Items.DYES);
        return super.isItemValid(slot, stack);
    }
}