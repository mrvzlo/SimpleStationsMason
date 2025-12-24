package com.ave.simplestationsmason.blockentity;

import com.ave.simplestationsmason.blockentity.handlers.InputItemHandler;
import com.ave.simplestationsmason.blockentity.handlers.OutputItemHandler;

import java.util.Random;

import com.ave.simplestationsmason.blockentity.handlers.BaseSidedItemHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.IItemHandler;

public abstract class StationContainer extends BlockEntity implements MenuProvider {
    public BaseSidedItemHandler inventory;
    protected static final Random RNG = new Random();

    public StationContainer(BlockEntityType<BlockEntity> entity, BlockPos pos, BlockState state) {
        super(entity, pos, state);
    }

    public IItemHandler getItemHandler(Direction side) {
        if (side == Direction.DOWN)
            return new OutputItemHandler(inventory);
        return new InputItemHandler(inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.simplestationsmason.mason");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("inventory", inventory.serializeNBT(registries));
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        inventory.deserializeNBT(registries, tag.getCompound("inventory"));
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
