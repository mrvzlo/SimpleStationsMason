package com.ave.simplestationsmanor.blockentity.partblock;

import com.ave.simplestationsmanor.blockentity.BaseStationBlockEntity;
import com.ave.simplestationsmanor.blockentity.enums.CropType;
import com.ave.simplestationsmanor.blockentity.handlers.InputItemHandler;
import com.ave.simplestationsmanor.blockentity.handlers.OutputItemHandler;
import com.ave.simplestationsmanor.registrations.ModBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.energy.EnergyStorage;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.items.IItemHandler;

public class PartBlockEntity extends BlockEntity {

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

    public CropType getCropType() {
        if (this.getController(this) == null)
            return CropType.Unknown;
        return this.getController(this).type;
    }

    public BlockPos getControllerPos() {
        return controllerPos;
    }

    public static void registerCaps(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                ModBlockEntities.PART_ENTITY.get(),
                (be, direction) -> be.getItemHandler(direction, be));
    }

    public IItemHandler getItemHandler(Direction side, PartBlockEntity be) {
        var inventory = this.getController(be).inventory;
        if (side == Direction.DOWN)
            return new OutputItemHandler(inventory);
        return new InputItemHandler(inventory);
    }

    public EnergyStorage getEnergyStorage(PartBlockEntity be) {
        return this.getController(be).fuel;
    }

    public FluidTank getWaterStorage(PartBlockEntity be) {
        return this.getController(be).tank;
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
