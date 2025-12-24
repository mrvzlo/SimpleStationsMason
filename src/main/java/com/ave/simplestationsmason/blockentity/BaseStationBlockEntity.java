package com.ave.simplestationsmason.blockentity;

import java.util.HashMap;
import java.util.Map;

import com.ave.simplestationsmason.blockentity.handlers.InputItemHandler;
import com.ave.simplestationsmason.blockentity.handlers.OutputItemHandler;
import com.ave.simplestationsmason.blockentity.managers.ExportManager;
import com.ave.simplestationsmason.blockentity.managers.WorkManager;
import com.ave.simplestationsmason.blockentity.resources.EnergyResource;
import com.ave.simplestationsmason.blockentity.resources.StationResource;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandler;

public abstract class BaseStationBlockEntity extends StationContainer {
    public static final int FUEL_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;

    public final Map<Integer, StationResource> resources = new HashMap<Integer, StationResource>();
    public int type = -1;
    public float progress = 0;
    public boolean working = false;

    public int speed = 1;
    public int soundCooldown = 0;
    protected int particleCooldown = 0;

    public ItemStack toProduce;
    public int fuelLow = 0;
    public int fuelHigh = 0;
    public int fuelValue = 0;

    public BaseStationBlockEntity(BlockEntityType entity, BlockPos pos, BlockState state) {
        super(entity, pos, state);
    }

    public void tick() {
        if (level.isClientSide) {
            if (showParticle())
                addParticle();
            return;
        }

        if (progress >= getMaxProgress())
            progress -= getMaxProgress();

        checkNewType();
        for (var entry : resources.entrySet())
            checkResource(entry.getKey(), entry.getValue());

        working = WorkManager.getWorkingState(this);
        ExportManager.pushOutput(this);
        fuelValue = resources.get(BaseStationBlockEntity.FUEL_SLOT).get();
        fuelHigh = fuelValue >> 16;
        fuelLow = fuelValue & 0xFFFF;

        if (!working)
            return;

        WorkManager.performWorkTick(this);

        if (progress < getMaxProgress())
            return;

        WorkManager.performWorkEnd(this);
    }

    private void checkResource(int slot, StationResource resource) {
        var stack = inventory.getStackInSlot(slot);
        if (!resource.tryIncrement(stack))
            return;

        if (stack.getItem().equals(Items.WATER_BUCKET) || stack.getItem().equals(Items.LAVA_BUCKET))
            inventory.setStackInSlot(slot, new ItemStack(Items.BUCKET));
        else
            stack.shrink(1);
    }

    public IEnergyStorage getEnergyStorage() {
        var resource = resources.get(BaseStationBlockEntity.FUEL_SLOT);
        if (resource instanceof EnergyResource energy)
            return energy.storage;
        return null;
    }

    public FluidTank getWaterStorage() {
        return null;
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
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        saveAll(tag);
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        saveAll(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        type = tag.getInt("type");
        progress = tag.getFloat("progress");
        for (var res : resources.values())
            res.load(tag);
    }

    private void saveAll(CompoundTag tag) {
        tag.putFloat("progress", progress);
        tag.putInt("type", type);
        for (var res : resources.values())
            res.save(tag);
    }

    protected boolean showParticle() {
        if (progress == 0)
            return false;
        if (particleCooldown <= 0)
            return true;
        particleCooldown--;
        return false;
    }

    protected abstract void addParticle();

    public abstract int getMaxProgress();

    public abstract SoundEvent getWorkSound();

    public abstract ItemStack getProduct(boolean check);

    protected abstract int getCurrentType();

    private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> this.getEnergyStorage());
    private final LazyOptional<IFluidHandler> fluid = LazyOptional.of(() -> this.getWaterStorage());
    private final LazyOptional<IItemHandler> outputHandler = LazyOptional.of(() -> new OutputItemHandler(inventory));
    private final LazyOptional<IItemHandler> inputHandler = LazyOptional.of(() -> new InputItemHandler(inventory));

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (cap == ForgeCapabilities.ENERGY)
            return energy.cast();
        if (cap == ForgeCapabilities.FLUID_HANDLER)
            return fluid.cast();
        if (cap == ForgeCapabilities.ITEM_HANDLER)
            return side.equals(Direction.DOWN) ? outputHandler.cast() : inputHandler.cast();

        return super.getCapability(cap, side);
    }
}
