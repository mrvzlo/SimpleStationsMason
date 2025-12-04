package com.ave.simplestationsmanor.blockentity;

import javax.annotation.Nullable;

import com.ave.simplestationsmanor.registrations.ModBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ExcavatorBlock extends BaseStationBlock {
    public ExcavatorBlock(Properties props) {
        super(props, ModBlocks.EXCAVATOR_BLOCK);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ExcavatorBlockEntity(pos, state);
    }
}
