package com.ave.simplestationsmason.blockentity;

import com.ave.simplestationsmason.Config;
import com.ave.simplestationsmason.blockentity.managers.ExportManager;
import com.ave.simplestationsmason.registrations.ModSounds;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.EnergyStorage;

public abstract class BaseStationBlockEntity extends StationContainer {
    public EnergyStorage fuel = new EnergyStorage(Config.POWER_MAX.get());

    public int type = -1;
    public float progress = 0;
    public boolean working = false;

    protected int powerUsage = 1;
    protected int speed = 1;
    public int fluidUsage;

    public BaseStationBlockEntity(BlockEntityType entity, BlockPos pos, BlockState state) {
        super(entity, pos, state);
    }

    public void tick() {
        if (level.isClientSide)
            return;

        if (progress >= Config.MAX_PROGRESS.get())
            progress -= Config.MAX_PROGRESS.get();

        checkNewType();
        checkResource(FUEL_SLOT, Items.COAL_BLOCK, Config.POWER_PER_RED.get(), Config.POWER_MAX.get(),
                ResourceType.POWER);

        var slot = inventory.getStackInSlot(OUTPUT_SLOT);
        working = getWorking(slot);
        ExportManager.pushOutput(this);

        if (!working)
            return;

        progress += speed;
        fuel.extractEnergy(powerUsage, false);

        playSound();

        if (progress < Config.MAX_PROGRESS.get())
            return;

        var toAdd = new ItemStack(getProduct());
        toAdd.setCount(slot.getCount() + 1);
        inventory.setStackInSlot(OUTPUT_SLOT, toAdd);
        setChanged();
    }

    private boolean getWorking(ItemStack slot) {
        if (type == -1)
            return false;
        if (fuel.getEnergyStored() < powerUsage)
            return false;
        if (slot.getCount() == 0)
            return true;
        if (slot.getCount() + 1 > slot.getMaxStackSize())
            return false;
        return slot.getItem().equals(getProduct());
    }

    private void checkResource(int slot, Item blockItem, int increment, int maxCapacity, ResourceType type) {
        var stack = inventory.getStackInSlot(slot);

        if (blockItem != null && stack.getItem().equals(blockItem))
            increment *= 9;

        if (stack.isEmpty() || getResourceValue(type) + increment > maxCapacity)
            return;

        stack.shrink(1);
        inventory.setStackInSlot(slot, stack);
        addResource(type, increment);
    }

    private void addResource(ResourceType type, int amount) {
        switch (type) {
            case POWER -> fuel.receiveEnergy(amount, false);
        }
    }

    public int soundCooldown = 0;

    private void playSound() {
        if (soundCooldown > 0) {
            soundCooldown--;
            return;
        }
        soundCooldown += 20;
        level.playSound(null, getBlockPos(), ModSounds.WORK_SOUND.get(), SoundSource.BLOCKS);
    }

    private int getResourceValue(ResourceType type) {
        return switch (type) {
            case POWER -> fuel.getEnergyStored();
        };
    }

    private void checkNewType() {
        var newType = getCurrentType();
        if (type == newType)
            return;

        type = newType;
        progress = 0;
        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        saveAll(tag);
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider registries) {
        super.handleUpdateTag(tag, registries);
        saveAll(tag);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        type = tag.getInt("type");
        fuel = new EnergyStorage(Config.POWER_MAX.get(), Config.POWER_MAX.get(), Config.POWER_MAX.get(),
                tag.getInt("fuel"));
        progress = tag.getFloat("progress");
    }

    private void saveAll(CompoundTag tag) {
        tag.putInt("fuel", fuel.getEnergyStored());
        tag.putFloat("progress", progress);
        tag.putInt("type", type);
    }

    protected Item getProduct() {
        return null;
    }

    protected int getCurrentType() {
        return 0;
    }

    private enum ResourceType {
        POWER
    }
}
