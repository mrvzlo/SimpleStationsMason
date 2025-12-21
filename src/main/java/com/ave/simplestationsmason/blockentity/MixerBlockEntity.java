package com.ave.simplestationsmason.blockentity;

import java.util.Arrays;

import com.ave.simplestationsmason.Config;
import com.ave.simplestationsmason.blockentity.handlers.MixerInputHandler;
import com.ave.simplestationsmason.blockentity.resources.EnergyResource;
import com.ave.simplestationsmason.blockentity.resources.ItemResource;
import com.ave.simplestationsmason.blockentity.resources.WaterResource;
import com.ave.simplestationsmason.registrations.ModBlockEntities;
import com.ave.simplestationsmason.registrations.VanillaBlocks;
import com.ave.simplestationsmason.screen.MixerMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class MixerBlockEntity extends BaseStationBlockEntity {
    public static final int SAND_SLOT = 2;
    public static final int GRAVEL_SLOT = 4;
    public static final int COLOR_SLOT = 3;
    public static final int WATER_SLOT = 5;
    public int waterValue = 0;

    public MixerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MIXER_ENTITY.get(), pos, state);
        inventory = new MixerInputHandler(6) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
        resources.put(FUEL_SLOT, new EnergyResource(Config.POWER_MAX.get(), 16));
        resources.put(WATER_SLOT, new WaterResource(Config.WATER_MAX.get(), 100));
        resources.put(SAND_SLOT, new ItemResource(inventory, SAND_SLOT, 16));
        resources.put(GRAVEL_SLOT, new ItemResource(inventory, GRAVEL_SLOT, 16));
        resources.put(COLOR_SLOT, new ItemResource(inventory, COLOR_SLOT, 2));
    }

    @Override
    public void tick() {
        waterValue = resources.get(WATER_SLOT).get();
        super.tick();
    }

    @Override
    public MixerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new MixerMenu(containerId, inventory, this);
    }

    @Override
    public int getMaxProgress() {
        return Config.MAX_MIX_PROGRESS.getAsInt();
    }

    @Override
    public ItemStack getProduct(boolean __) {
        var type = getCurrentType();
        if (type < 0)
            return ItemStack.EMPTY;
        var concrete = VanillaBlocks.CONCRETE[type];
        return new ItemStack(concrete, 32);
    }

    @Override
    protected int getCurrentType() {
        var stack = inventory.getStackInSlot(COLOR_SLOT);
        return stack.isEmpty() ? -1 : Arrays.asList(VanillaBlocks.COLOR_DYES).indexOf(stack.getItem());
    }

    @Override
    public SoundEvent getWorkSound() {
        return SoundEvents.LAVA_AMBIENT;
    }

    public static void registerCaps(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                ModBlockEntities.MIXER_ENTITY.get(),
                (be, direction) -> be.getItemHandler(direction));
    }

    protected void addParticle() {
        particleCooldown = 10;
        double x = getBlockPos().getX() + 0.5 + (RNG.nextDouble() / 2 - 0.25);
        double y = getBlockPos().getY() + 0.4;
        double z = getBlockPos().getZ() + 0.5 + (RNG.nextDouble() / 2 - 0.25);
        level.addParticle(ParticleTypes.DOLPHIN, x, y, z, 0, 0.01, 0);
    }
}
