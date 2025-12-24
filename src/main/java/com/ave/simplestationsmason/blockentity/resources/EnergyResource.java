package com.ave.simplestationsmason.blockentity.resources;

import com.ave.simplestationsmason.Config;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.energy.EnergyStorage;

public class EnergyResource implements StationResource {
    public EnergyStorage storage;
    private int usage;

    public EnergyResource(int max, int usage) {
        this.usage = usage;
        this.storage = new EnergyStorage(max);
    }

    public boolean useEveryTick() {
        return true;
    }

    public void load(CompoundTag tag) {
        storage.extractEnergy(get(), false);
        add(tag.getInt("fuel"));
    }

    public void save(CompoundTag tag) {
        tag.putInt("fuel", get());
    }

    public int get() {
        return storage.getEnergyStored();
    }

    public int getMax() {
        return storage.getMaxEnergyStored();
    }

    public void add(int amount) {
        storage.receiveEnergy(amount, false);
    }

    public void substract() {
        storage.extractEnergy(usage, false);
    }

    public int getRequired() {
        return usage;
    }

    public int getIncrement(Item item) {
        if (item.equals(Items.COAL_BLOCK))
            return Config.FUEL_PER_COAL.get() * 9;
        return Config.FUEL_PER_COAL.get();
    }
}
