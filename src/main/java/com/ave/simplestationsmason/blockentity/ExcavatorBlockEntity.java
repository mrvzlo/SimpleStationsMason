package com.ave.simplestationsmason.blockentity;

import com.ave.simplestationsmason.Config;
import com.ave.simplestationsmason.blockentity.enums.CropGroup;
import com.ave.simplestationsmason.registrations.ModBlockEntities;
import com.ave.simplestationsmason.screen.ExcavatorMenu;

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
