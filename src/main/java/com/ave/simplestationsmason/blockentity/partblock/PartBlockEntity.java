package com.ave.simplestationsmason.blockentity.partblock;

import com.ave.simplestationsmason.blockentity.BaseStationBlockEntity;
import com.ave.simplestationsmason.blockentity.handlers.InputItemHandler;
import com.ave.simplestationsmason.blockentity.handlers.OutputItemHandler;
import com.ave.simplestationsmason.registrations.ModBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandler;

public class PartBlockEntity extends BlockEntity {
    private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> getEnergyStorage(this));
    private final LazyOptional<IFluidHandler> fluid = LazyOptional.of(() -> getWaterStorage(this));
    private final LazyOptional<IItemHandler> outputHandler = LazyOptional
            .of(() -> getItemHandler(Direction.DOWN, this));
    private final LazyOptional<IItemHandler> inputHandler = LazyOptional.of(() -> getItemHandler(Direction.UP, this));

    private BlockPos controllerPos;

    public PartBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PART_ENTITY.get(), pos, state);
    }

    public void setControllerPos(BlockPos pos) {
        controllerPos = pos;
        setChanged();
        if (level == null || level.isClientSide)
            return;
        level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
    }

    public int getStationType() {
        return (this.getController(this) == null) ? 0 : this.getController(this).type;
    }

    public BlockPos getControllerPos() {
        return controllerPos;
    }

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

    public IItemHandler getItemHandler(Direction side, PartBlockEntity be) {
        var controller = this.getController(be);
        if (controller == null)
            return null;
        var inventory = controller.inventory;
        if (side == Direction.DOWN)
            return new OutputItemHandler(inventory);
        return new InputItemHandler(inventory);
    }

    public IEnergyStorage getEnergyStorage(PartBlockEntity be) {
        var controller = this.getController(be);
        if (controller == null)
            return null;
        return controller.getEnergyStorage();
    }

    public FluidTank getWaterStorage(PartBlockEntity be) {
        var controller = this.getController(be);
        if (controller == null)
            return null;
        return controller.getWaterStorage();
    }

    private BaseStationBlockEntity getController(PartBlockEntity be) {
        if (be.controllerPos == null)
            return null;
        return ((BaseStationBlockEntity) be.getLevel().getBlockEntity(be.controllerPos));
    }

    public boolean isEdge() {
        return sameX() || sameZ();
    }

    public boolean sameX() {
        if (this.controllerPos == null)
            return false;
        return this.controllerPos.getX() == getBlockPos().getX();
    }

    public boolean sameZ() {
        if (this.controllerPos == null)
            return false;
        return this.controllerPos.getZ() == getBlockPos().getZ();
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (controllerPos == null)
            return;
        tag.putLong("Controller", controllerPos.asLong());
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        controllerPos = BlockPos.of(tag.getLong("Controller"));
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag, registries);
        return tag;
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
