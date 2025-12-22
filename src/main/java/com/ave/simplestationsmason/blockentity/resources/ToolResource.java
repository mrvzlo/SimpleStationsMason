package com.ave.simplestationsmason.blockentity.resources;

import com.ave.simplestationsmason.blockentity.handlers.BaseSidedItemHandler;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ToolResource implements StationResource {
    private final BaseSidedItemHandler inventory;
    private final int slot;
    private int usage;

    public ToolResource(BaseSidedItemHandler inventory, int slot, int usage) {
        this.usage = usage;
        this.inventory = inventory;
        this.slot = slot;
    }

    public boolean useEveryTick() {
        return false;
    }

    public void load(CompoundTag tag) {
        return;
    }

    public void save(CompoundTag tag) {
        return;
    }

    @Override
    public boolean tryIncrement(ItemStack item) {
        return false;
    }

    public int get() {
        return getSlot().getCount();
    }

    public int getMax() {
        return getSlot().getMaxStackSize();
    }

    public void add(int amount) {
        getSlot().grow(amount);
    }

    public void substract() {
        getSlot().setDamageValue(getSlot().getDamageValue() + usage);
        if (getSlot().getDamageValue() < getSlot().getMaxDamage())
            return;
        getSlot().shrink(1);
    }

    public int getRequired() {
        return 0;
    }

    public int getIncrement(Item item) {
        return 1;
    }

    private ItemStack getSlot() {
        return inventory.getStackInSlot(slot);
    }
}
