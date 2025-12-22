package com.ave.simplestationsmason.registrations;

import java.util.Arrays;

import com.ave.simplestationsmason.SimpleStationsMason;
import com.ave.simplestationsmason.blockentity.ExcavatorBlock;
import com.ave.simplestationsmason.blockentity.KilnBlock;
import com.ave.simplestationsmason.blockentity.MixerBlock;
import com.ave.simplestationsmason.blockentity.SifterBlock;
import com.ave.simplestationsmason.blockentity.partblock.PartBlock;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
        public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(SimpleStationsMason.MODID);
        public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SimpleStationsMason.MODID);

        public static final DeferredBlock<Block> EXCAVATOR_BLOCK = BLOCKS.register("excavator",
                        () -> new ExcavatorBlock(BlockBehaviour.Properties.of()
                                        .strength(0.1F).lightLevel((state) -> 11).noOcclusion()));

        public static final DeferredBlock<Block> MIXER_BLOCK = BLOCKS.register("mixer",
                        () -> new MixerBlock(BlockBehaviour.Properties.of()
                                        .strength(0.1F).lightLevel((state) -> 11).noOcclusion()));

        public static final DeferredBlock<Block> KILN_BLOCK = BLOCKS.register("furnace",
                        () -> new KilnBlock(BlockBehaviour.Properties.of()
                                        .strength(0.1F).lightLevel((state) -> 11).noOcclusion()));

        public static final DeferredBlock<Block> SIFTER_BLOCK = BLOCKS.register("sifter",
                        () -> new SifterBlock(BlockBehaviour.Properties.of()
                                        .strength(0.1F).lightLevel((state) -> 11).noOcclusion()));

        public static final DeferredBlock<Block> PART = BLOCKS.register("part",
                        () -> new PartBlock(BlockBehaviour.Properties.of()
                                        .strength(0.1F).lightLevel((state) -> 11).noOcclusion()));

        public static final DeferredItem<BlockItem> EXCAVATOR_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("excavator",
                        EXCAVATOR_BLOCK);

        public static final DeferredItem<BlockItem> MIXER_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("mixer",
                        MIXER_BLOCK);

        public static final DeferredItem<BlockItem> KILN_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("furnace",
                        KILN_BLOCK);

        public static final DeferredItem<BlockItem> SIFTER_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("sifter",
                        SIFTER_BLOCK);

        public static final DeferredItem<Item> WHEEL = ITEMS.registerItem("wheel", Item::new, new Item.Properties());
        public static final DeferredItem<Item> BUCKET = ITEMS.registerItem("bucket", Item::new, new Item.Properties());
        public static final DeferredItem<Item> COIN = ITEMS.registerItem("coin", Item::new,
                        new Item.Properties().durability(777));

        public static final Item[] EXCAVATABLE = new Item[] {
                        Items.CLAY,
                        Items.DIRT,
                        Items.GRAVEL,
                        Items.RED_SAND,
                        Items.SAND,
                        Items.SNOW_BLOCK
        };
        private static final String[] EXCAVATABLE_TYPES = { "clay", "dirt", "gravel", "red_sand", "sand", "snow" };
        public static final DeferredItem<Item>[] EXCAVATABLE_BLOCKS = Arrays.stream(EXCAVATABLE_TYPES)
                        .map(x -> ITEMS.registerItem("ex_" + x, Item::new, new Item.Properties()))
                        .toArray(DeferredItem[]::new);

        private static final String[] BLASTING_TYPES = { "brick", "glass", "glazed", "terracota", "nether", "stone" };
        public static final DeferredItem<Item>[] BLASTING_BLOCKS = Arrays.stream(BLASTING_TYPES)
                        .map(x -> ITEMS.registerItem("blast_" + x, Item::new, new Item.Properties()))
                        .toArray(DeferredItem[]::new);

        public static final Item[] SIFTABLE = new Item[] {
                        Items.CLAY,
                        Items.DIRT,
                        Items.GRAVEL,
                        Items.RED_SAND,
                        Items.SAND,
                        Items.SOUL_SAND
        };

        private static final String[] SIFTABLE_TYPES = { "clay", "dirt", "gravel", "red_sand", "sand", "soul_sand" };
        public static final DeferredItem<Item>[] SIFTABLE_BLOCKS = Arrays.stream(SIFTABLE_TYPES)
                        .map(x -> ITEMS.registerItem("sift_" + x, Item::new, new Item.Properties()))
                        .toArray(DeferredItem[]::new);
}
