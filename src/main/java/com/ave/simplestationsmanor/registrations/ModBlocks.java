package com.ave.simplestationsmanor.registrations;

import java.util.Arrays;

import com.ave.simplestationsmanor.SimpleStationsManor;
import com.ave.simplestationsmanor.blockentity.ExcavatorBlock;
import com.ave.simplestationsmanor.blockentity.partblock.PartBlock;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
        public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(SimpleStationsManor.MODID);
        public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SimpleStationsManor.MODID);

        public static final DeferredBlock<Block> EXCAVATOR_BLOCK = BLOCKS.register("farm",
                        () -> new ExcavatorBlock(BlockBehaviour.Properties.of()
                                        .strength(0.1F).lightLevel((state) -> 15).noOcclusion()));

        public static final DeferredBlock<Block> PART = BLOCKS.register("part",
                        () -> new PartBlock(BlockBehaviour.Properties.of()
                                        .strength(0.1F).lightLevel((state) -> 15).noOcclusion()));

        public static final DeferredItem<BlockItem> FARMER_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("farm",
                        EXCAVATOR_BLOCK);

        public static final DeferredItem<Item> SPRINKLER = ITEMS.registerItem("sprinkler", Item::new,
                        new Item.Properties());

        private static final String[] CROPS = { "wheat", "beet", "carrot", "potato", "melon", "pumpkin",
                        "brown_mushroom", "red_mushroom",
                        "n_wart", "chorus", };

        public static final DeferredBlock<Block>[] CROP_BLOCKS = Arrays.stream(CROPS)
                        .map(x -> BLOCKS.register(x, () -> new Block(BlockBehaviour.Properties.of())))
                        .toArray(DeferredBlock[]::new);
}
