package com.ave.simplestationsmason.blockentity;

import javax.annotation.Nullable;

import com.ave.simplestationscore.mainblock.BaseStationBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ExcavatorBlock extends BaseStationBlock {
    public ExcavatorBlock(Properties props) {
        super(props);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ExcavatorBlockEntity(pos, state);
    }
}
