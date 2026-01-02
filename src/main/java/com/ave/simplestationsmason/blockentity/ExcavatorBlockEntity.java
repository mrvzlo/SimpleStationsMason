package com.ave.simplestationsmason.blockentity;

import java.util.Arrays;

import com.ave.simplestationscore.mainblock.BaseStationBlockEntity;
import com.ave.simplestationscore.resources.EnergyResource;
import com.ave.simplestationsmason.Config;
import com.ave.simplestationsmason.blockentity.handlers.ExcavatorItemHandler;
import com.ave.simplestationsmason.registrations.Registrations;
import com.ave.simplestationsmason.screen.ExcavatorMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class ExcavatorBlockEntity extends BaseStationBlockEntity {
    private final Item[] EXCAVATABLE = Registrations.EXCAVATABLE;
    public static final int TYPE_SLOT = 2;

    public ExcavatorBlockEntity(BlockPos pos, BlockState state) {
        super(Registrations.EXCAVATOR.getEntity(), pos, state);
        inventory = new ExcavatorItemHandler(3) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
        resources.put(FUEL_SLOT, new EnergyResource(Config.POWER_MAX.get(), 64, Config.FUEL_PER_COAL.get()));
    }

    @Override
    public ExcavatorMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new ExcavatorMenu(containerId, inventory, this);
    }

    @Override
    public int getMaxProgress() {
        return Config.MAX_EXC_PROGRESS.getAsInt();
    }

    @Override
    public ItemStack getProduct(boolean __) {
        return new ItemStack(EXCAVATABLE[type]);
    }

    @Override
    protected int getCurrentType() {
        var stack = inventory.getStackInSlot(TYPE_SLOT);
        return stack.isEmpty() ? -1 : Arrays.asList(EXCAVATABLE).indexOf(stack.getItem());
    }

    @Override
    public SoundEvent getWorkSound() {
        return SoundEvents.SAND_BREAK;
    }

    @Override
    protected void addParticle() {
    }
}
