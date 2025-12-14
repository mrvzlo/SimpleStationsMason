package com.ave.simplestationsmason.blockentity.resources;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;

public interface StationResource {
    int get();

    int getMax();

    int getRequired();

    int getIncrement(Item item);

    void add(int amount);

    boolean useEveryTick();

    void substract();

    default boolean tryIncrement(Item item) {
        var inc = getIncrement(item);
        if (inc == 0 || !canAdd(inc))
            return false;
        add(inc);
        return true;
    }

    default boolean canAdd(int value) {
        return get() + value <= getMax();
    }

    default boolean canSubstract(int amount) {
        return get() >= amount;
    }

    void save(CompoundTag tag);

    void load(CompoundTag tag);

    default boolean isEnough() {
        return get() >= getRequired();
    }
}
