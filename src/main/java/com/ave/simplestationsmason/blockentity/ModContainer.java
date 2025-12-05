package com.ave.simplestationsmason.blockentity;

import com.ave.simplestationsmason.blockentity.enums.CropGroup;
import com.ave.simplestationsmason.blockentity.handlers.InputItemHandler;
import com.ave.simplestationsmason.blockentity.handlers.OutputItemHandler;
import com.ave.simplestationsmason.blockentity.handlers.SidedItemHandler;
import com.ave.simplestationsmason.registrations.ModBlockEntities;
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
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.items.IItemHandler;

public abstract class ModContainer extends BlockEntity implements MenuProvider {
    public final SidedItemHandler inventory;
    public static final int OUTPUT_SLOT = 0;
    public static final int FLUID_SLOT = 1;
    public static final int TYPE_SLOT = 2;
    public static final int FERTI_SLOT = 3;
    public static final int REDSTONE_SLOT = 4;

    public ModContainer(BlockEntityType<BlockEntity> entity, BlockPos pos, BlockState state, int size,
            CropGroup group) {
        super(entity, pos, state);
        inventory = new SidedItemHandler(size, group) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
    }

    public static void registerCaps(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                ModBlockEntities.EXCAVATOR_ENTITY.get(),
                (be, direction) -> be.getItemHandler(direction));
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
