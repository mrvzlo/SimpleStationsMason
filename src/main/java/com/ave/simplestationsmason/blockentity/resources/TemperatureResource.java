package com.ave.simplestationsmason.blockentity.resources;

import com.ave.simplestationsmason.blockentity.managers.TemperatureManager;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class TemperatureResource implements StationResource {
    public float value;

    public TemperatureResource() {
        this.value = 20;
    }

    public boolean useEveryTick() {
        return true;
    }

    public void load(CompoundTag tag) {
        value = tag.getFloat("temp");
    }

    public void save(CompoundTag tag) {
        tag.putFloat("temp", value);
    }

    public int get() {
        return (int) value;
    }

    public int getMax() {
        return (int) TemperatureManager.MaxTemp;
    }

    public void add(int amount) {
        value += amount;
    }

    public void substract() {
        value -= TemperatureManager.getCool(value);

    }

    public int getRequired() {
        return 600;
    }

    public int getIncrement(Item item) {
        if (item.equals(Items.COAL_BLOCK))
            return 50 * 9;
        return 50;
    }
}
