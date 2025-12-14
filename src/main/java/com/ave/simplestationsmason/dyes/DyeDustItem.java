package com.ave.simplestationsmason.dyes;

import java.util.Arrays;
import com.ave.simplestationsmason.registrations.VanillaBlocks;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class DyeDustItem extends Item {
    private int id;

    public DyeDustItem(int id, Properties properties) {
        super(properties);
        this.id = id;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        var currentState = level.getBlockState(context.getClickedPos());
        Block clickedBlock = currentState.getBlock();
        if (level.isClientSide())
            return InteractionResult.SUCCESS;

        var toTransform = getTransform(clickedBlock);
        if (toTransform == null || toTransform.equals(clickedBlock))
            return InteractionResult.SUCCESS;

        var recalculated = toTransform.withPropertiesOf(currentState);
        level.setBlockAndUpdate(context.getClickedPos(), recalculated);

        context.getItemInHand().setCount(context.getItemInHand().getCount() - 1);
        return InteractionResult.SUCCESS;
    }

    private Block getTransform(Block clicked) {
        if (id < VanillaBlocks.GLASSES.length
                && Arrays.stream(VanillaBlocks.GLASSES).anyMatch(x -> x.equals(clicked)))
            return VanillaBlocks.GLASSES[id];
        if (id < VanillaBlocks.GLASS_PANES.length
                && Arrays.stream(VanillaBlocks.GLASS_PANES).anyMatch(x -> x.equals(clicked)))
            return VanillaBlocks.GLASS_PANES[id];
        if (id < VanillaBlocks.TERRACOTA.length
                && Arrays.stream(VanillaBlocks.TERRACOTA).anyMatch(x -> x.equals(clicked)))
            return VanillaBlocks.TERRACOTA[id];
        if (id < VanillaBlocks.CONCRETE.length
                && Arrays.stream(VanillaBlocks.CONCRETE).anyMatch(x -> x.equals(clicked)))
            return VanillaBlocks.CONCRETE[id];
        return null;
    }

    public Item getTransform(Item clicked) {
        if (id < VanillaBlocks.GLASSES.length
                && Arrays.stream(VanillaBlocks.GLASSES).anyMatch(x -> x.asItem().equals(clicked)))
            return VanillaBlocks.GLASSES[id].asItem();
        if (id < VanillaBlocks.GLASS_PANES.length
                && Arrays.stream(VanillaBlocks.GLASS_PANES).anyMatch(x -> x.asItem().equals(clicked)))
            return VanillaBlocks.GLASS_PANES[id].asItem();
        if (id < VanillaBlocks.TERRACOTA.length
                && Arrays.stream(VanillaBlocks.TERRACOTA).anyMatch(x -> x.asItem().equals(clicked)))
            return VanillaBlocks.TERRACOTA[id].asItem();
        if (id < VanillaBlocks.CONCRETE.length
                && Arrays.stream(VanillaBlocks.CONCRETE).anyMatch(x -> x.asItem().equals(clicked)))
            return VanillaBlocks.CONCRETE[id].asItem();
        if (id < VanillaBlocks.CONCRETE.length
                && Arrays.stream(VanillaBlocks.GLAZED_TERRACOTA).anyMatch(x -> x.asItem().equals(clicked)))
            return VanillaBlocks.GLAZED_TERRACOTA[id].asItem();
        return null;
    }
}
