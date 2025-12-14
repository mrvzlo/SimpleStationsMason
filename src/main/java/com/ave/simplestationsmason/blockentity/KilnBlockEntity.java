package com.ave.simplestationsmason.blockentity;

import com.ave.simplestationsmason.Config;
import com.ave.simplestationsmason.blockentity.enums.KilnType;
import com.ave.simplestationsmason.blockentity.handlers.KilnInputHandler;
import com.ave.simplestationsmason.blockentity.resources.ItemResource;
import com.ave.simplestationsmason.blockentity.resources.OptionalItemResource;
import com.ave.simplestationsmason.blockentity.resources.TemperatureResource;
import com.ave.simplestationsmason.dyes.DyeDustItem;
import com.ave.simplestationsmason.registrations.ModBlockEntities;
import com.ave.simplestationsmason.screen.KilnMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class KilnBlockEntity extends BaseStationBlockEntity {
    public static final int TYPE_SLOT = 2;
    public static final int COLOR_SLOT = 3;

    public KilnBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.KILN_ENTITY.get(), pos, state);
        inventory = new KilnInputHandler(4) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
        resources.put(FUEL_SLOT, new TemperatureResource());
        resources.put(TYPE_SLOT, new ItemResource(inventory, TYPE_SLOT, 1));
        resources.put(COLOR_SLOT, new OptionalItemResource(inventory, COLOR_SLOT, 1));
    }

    @Override
    public void tick() {
        if (level.isClientSide)
            return;
        super.tick();
        if (working)
            return;
        resources.get(FUEL_SLOT).substract();
    }

    @Override
    public KilnMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new KilnMenu(containerId, inventory, this);
    }

    @Override
    public int getMaxProgress() {
        return Config.MAX_KILN_PROGRESS.getAsInt();
    }

    @Override
    public ItemStack getProduct() {
        var type = getCurrentType();
        if (type <= 0)
            return ItemStack.EMPTY;
        var result = KilnType.values()[type].product;
        var color = inventory.getStackInSlot(COLOR_SLOT).getItem();
        if (!(color instanceof DyeDustItem dust))
            return new ItemStack(result);

        return new ItemStack(dust.getTransform(result));
    }

    @Override
    protected int getCurrentType() {
        var stack = inventory.getStackInSlot(TYPE_SLOT);
        if (stack.isEmpty())
            return -1;
        var type = KilnType.findBySource(stack.getItem());
        return type.equals(KilnType.Unknown) ? -1 : type.ordinal();
    }

    @Override
    public SoundEvent getWorkSound() {
        return SoundEvents.FURNACE_FIRE_CRACKLE;
    }

    public static void registerCaps(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                ModBlockEntities.KILN_ENTITY.get(),
                (be, direction) -> be.getItemHandler(direction));
    }
}
