package com.ave.simplestationsmason.blockentity;

import com.ave.simplestationscore.CoreConfig;
import com.ave.simplestationscore.mainblock.BaseStationBlockEntity;
import com.ave.simplestationscore.resources.EnergyResource;
import com.ave.simplestationscore.resources.ItemResource;
import com.ave.simplestationscore.resources.ToolResource;
import com.ave.simplestationsmason.Config;
import com.ave.simplestationsmason.blockentity.handlers.SifterItemHandler;
import com.ave.simplestationsmason.datagen.ModRecipes;
import com.ave.simplestationsmason.recipes.SifterRecipeInput;
import com.ave.simplestationsmason.registrations.Registrations;
import com.ave.simplestationsmason.screen.SifterMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class SifterBlockEntity extends BaseStationBlockEntity {
    public static final int TYPE_SLOT = 2;
    public static final int COIN_SLOT = 3;
    public static final int BATCH_SIZE = 16;

    public SifterBlockEntity(BlockPos pos, BlockState state) {
        super(Registrations.SIFTER.getEntity(), pos, state);
        inventory = new SifterItemHandler(4) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
        resources.put(FUEL_SLOT, new EnergyResource(CoreConfig.POWER_MAX.get(), 32, CoreConfig.POWER_PER_COAL.get()));
        resources.put(TYPE_SLOT, new ItemResource(inventory, TYPE_SLOT, BATCH_SIZE));
        resources.put(COIN_SLOT, new ToolResource(inventory, COIN_SLOT, 37));
    }

    @Override
    public void tick() {
        if (level.isClientSide) {
            addParticle();
            return;
        }

        super.tick();
    }

    @Override
    public SifterMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new SifterMenu(containerId, inventory, this);
    }

    @Override
    public int getMaxProgress() {
        return Config.MAX_SIFTER_PROGRESS.getAsInt();
    }

    @Override
    public ItemStack getProduct(boolean check) {
        var type = getCurrentType();
        if (type < 0 || check)
            return ItemStack.EMPTY;

        var input = new SifterRecipeInput(inventory.getStackInSlot(TYPE_SLOT));
        var recipe = level.getRecipeManager()
                .getRecipeFor(ModRecipes.SIFTER_TYPE.get(), input, level)
                .orElse(null);

        if (recipe == null)
            return ItemStack.EMPTY;

        var result = recipe.value().roll(RNG, hasLuck());
        return result;
    }

    @Override
    protected int getCurrentType() {
        var stack = inventory.getStackInSlot(TYPE_SLOT);
        if (stack.isEmpty())
            return -1;
        for (var i = 0; i < Registrations.SIFTABLE.length; i++) {
            if (Registrations.SIFTABLE[i].equals(stack.getItem()))
                return i;
        }
        return Registrations.SIFTABLE.length;
    }

    @Override
    public SoundEvent getWorkSound() {
        return SoundEvents.GRAVEL_FALL;
    }

    private boolean hasLuck() {
        return !inventory.getStackInSlot(COIN_SLOT).isEmpty();
    }

    protected void addParticle() {
        particleCooldown = 2;
        double x = getBlockPos().getX() + 0.5 + (RNG.nextDouble() - 0.5);
        double y = getBlockPos().getY() + 0.8;
        double z = getBlockPos().getZ() + 0.5 + (RNG.nextDouble() - 0.5);
        level.addParticle(ParticleTypes.ASH, x, y, z, 0d, 0.01, 0d);
    }
}
