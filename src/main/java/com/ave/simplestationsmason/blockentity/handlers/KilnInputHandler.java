package com.ave.simplestationsmason.blockentity.handlers;

import java.util.Arrays;

import com.ave.simplestationsmason.blockentity.BaseStationBlockEntity;
import com.ave.simplestationsmason.blockentity.KilnBlockEntity;
import com.ave.simplestationsmason.blockentity.enums.KilnType;
import com.ave.simplestationsmason.registrations.ModBlocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

public class KilnInputHandler extends BaseSidedItemHandler {
    public KilnInputHandler(int size) {
        super(size);
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        if (slot == KilnBlockEntity.TYPE_SLOT)
            return !KilnType.findBySource(stack.getItem()).equals(KilnType.Unknown);
        if (slot == BaseStationBlockEntity.FUEL_SLOT)
            return stack.getBurnTime(RecipeType.SMELTING) > 0;
        if (slot == KilnBlockEntity.COLOR_SLOT)
            return Arrays.stream(ModBlocks.COLOR_DUST_ITEMS).anyMatch(stack::is);
        return super.isItemValid(slot, stack);
    }
}