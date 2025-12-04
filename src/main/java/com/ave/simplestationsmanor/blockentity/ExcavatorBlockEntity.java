package com.ave.simplestationsmanor.blockentity;

import com.ave.simplestationsmanor.Config;
import com.ave.simplestationsmanor.blockentity.enums.CropGroup;
import com.ave.simplestationsmanor.registrations.ModBlockEntities;
import com.ave.simplestationsmanor.screen.ExcavatorMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;

public class ExcavatorBlockEntity extends BaseStationBlockEntity {
    public static final int WaterUsage = Config.WATER_PER_CYCLE.get();

    public ExcavatorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.EXCAVATOR_ENTITY.get(), pos, state, CropGroup.Crop);

        powerUsage = 2;
        fluidUsage = WaterUsage;
    }

    @Override
    public ExcavatorMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new ExcavatorMenu(containerId, inventory, this);
    }
}
