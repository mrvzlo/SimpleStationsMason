package com.ave.simplestationsmason.blockentity;

import com.ave.simplestationscore.mainblock.BaseStationBlock;
import com.ave.simplestationscore.mainblock.BaseStationBlockEntity;
import com.ave.simplestationscore.resources.ItemResource;
import com.ave.simplestationscore.resources.OptionalItemResource;
import com.ave.simplestationsmason.Config;
import com.ave.simplestationsmason.blockentity.enums.KilnType;
import com.ave.simplestationsmason.blockentity.handlers.FurnaceItemHandler;
import com.ave.simplestationsmason.blockentity.temperature.TemperatureResource;
import com.ave.simplestationsmason.registrations.Registrations;
import com.ave.simplestationsmason.registrations.VanillaBlocks;
import com.ave.simplestationsmason.screen.FurnaceMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.common.Tags;

public class FurnaceBlockEntity extends BaseStationBlockEntity {
    public static final int TYPE_SLOT = 2;
    public static final int COLOR_SLOT = 3;
    public boolean hasColor = false;

    public FurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(Registrations.FURNACE.getEntity(), pos, state);
        inventory = new FurnaceItemHandler(4) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
        resources.put(FUEL_SLOT, new TemperatureResource(Config.TEMP_RISE_SPEED.get()));
        resources.put(TYPE_SLOT, new ItemResource(inventory, TYPE_SLOT, 8));
        resources.put(COLOR_SLOT, new OptionalItemResource(inventory, COLOR_SLOT, 1));
    }

    @Override
    public void tick() {
        hasColor = !inventory.getStackInSlot(COLOR_SLOT).isEmpty();
        super.tick();
        if (working)
            return;
        resources.get(FUEL_SLOT).substract();
    }

    @Override
    public FurnaceMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new FurnaceMenu(containerId, inventory, this);
    }

    @Override
    public int getMaxProgress() {
        return Config.MAX_KILN_PROGRESS.getAsInt();
    }

    @Override
    public ItemStack getProduct(boolean __) {
        var type = getCurrentType();
        if (type <= 0)
            return ItemStack.EMPTY;
        var result = KilnType.values()[type].product;
        var color = getColor();
        if (color < 0)
            return new ItemStack(result, 8);

        return new ItemStack(getBlockToTransform(color, result), 8);
    }

    @Override
    protected int getCurrentType() {
        var stack = inventory.getStackInSlot(TYPE_SLOT);
        if (stack.isEmpty())
            return -1;
        var type = KilnType.findBySource(stack.getItem());
        // uncolored terracota without dye
        if (type.equals(KilnType.Glazed0) && inventory.getStackInSlot(COLOR_SLOT).isEmpty())
            return -1;
        return type.equals(KilnType.Unknown) ? -1 : type.ordinal();
    }

    private int getColor() {
        var stack = inventory.getStackInSlot(COLOR_SLOT);
        if (stack.isEmpty())
            return -1;
        var index = 0;
        for (var i : VanillaBlocks.COLOR_DYES) {
            if (i.equals(stack.getItem()))
                return index;
            index++;
        }
        return -1;
    }

    private Item getBlockToTransform(int index, Item item) {
        if (item.equals(Items.GLASS))
            return VanillaBlocks.GLASSES[index].asItem();
        if (item.equals(Items.TERRACOTTA))
            return VanillaBlocks.TERRACOTA[index].asItem();
        if (new ItemStack(item).is(Tags.Items.GLAZED_TERRACOTTAS))
            return VanillaBlocks.GLAZED_TERRACOTA[index].asItem();
        return item;
    }

    @Override
    public SoundEvent getWorkSound() {
        return SoundEvents.FURNACE_FIRE_CRACKLE;
    }

    public static void registerCaps(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                Registrations.FURNACE.getEntity(),
                (be, direction) -> be.getItemHandler(direction));
    }

    protected void addParticle() {
        particleCooldown = 10;
        var dir = getBlockState().getValue(BaseStationBlock.FACING);
        double x = getBlockPos().getX() + 0.5 + (RNG.nextDouble() / 2 - 0.25);
        double y = getBlockPos().getY() + 1;
        double z = getBlockPos().getZ() + 0.5 + (RNG.nextDouble() / 2 - 0.25);

        if (dir == Direction.NORTH || dir == Direction.SOUTH) {
            level.addParticle(ParticleTypes.SMOKE, x - 1, y, z, 0, 0.01, 0);
            level.addParticle(ParticleTypes.SMOKE, x + 1, y, z, 0, 0.01, 0);
        } else {
            level.addParticle(ParticleTypes.SMOKE, x, y, z - 1, 0, 0.01, 0);
            level.addParticle(ParticleTypes.SMOKE, x, y, z + 1, 0, 0.01, 0);
        }
    }
}
