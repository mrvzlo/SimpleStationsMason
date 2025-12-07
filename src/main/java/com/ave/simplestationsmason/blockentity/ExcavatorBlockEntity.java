package com.ave.simplestationsmason.blockentity;

import java.util.Arrays;

import com.ave.simplestationsmason.blockentity.handlers.ExcavatorItemHandler;
import com.ave.simplestationsmason.registrations.ModBlockEntities;
import com.ave.simplestationsmason.registrations.ModBlocks;
import com.ave.simplestationsmason.screen.ExcavatorMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;

public class ExcavatorBlockEntity extends BaseStationBlockEntity {
    private final Item[] EXCAVATABLE = ModBlocks.EXCAVATABLE;

    public ExcavatorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.EXCAVATOR_ENTITY.get(), pos, state);
        inventory = new ExcavatorItemHandler(5) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
        powerUsage = 2;
    }

    @Override
    public ExcavatorMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new ExcavatorMenu(containerId, inventory, this);
    }

    @Override
    protected Item getProduct() {
        return EXCAVATABLE[type];
    }

    @Override
    protected int getCurrentType() {
        var stack = inventory.getStackInSlot(TYPE_SLOT);
        return stack.isEmpty() ? -1 : Arrays.asList(EXCAVATABLE).indexOf(stack.getItem());
    }
}
