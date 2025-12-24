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
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
                        SimpleStationsMason.MODID);
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
                        SimpleStationsMason.MODID);

        public static final RegistryObject<Block> EXCAVATOR_BLOCK = BLOCKS.register("excavator",
                        () -> new ExcavatorBlock(BlockBehaviour.Properties.of()
                                        .strength(0.1F).lightLevel((state) -> 11).noOcclusion()));

        public static final RegistryObject<Block> MIXER_BLOCK = BLOCKS.register("mixer",
                        () -> new MixerBlock(BlockBehaviour.Properties.of()
                                        .strength(0.1F).lightLevel((state) -> 11).noOcclusion()));

        public static final RegistryObject<Block> KILN_BLOCK = BLOCKS.register("furnace",
                        () -> new KilnBlock(BlockBehaviour.Properties.of()
                                        .strength(0.1F).lightLevel((state) -> 11).noOcclusion()));

        public static final RegistryObject<Block> SIFTER_BLOCK = BLOCKS.register("sifter",
                        () -> new SifterBlock(BlockBehaviour.Properties.of()
                                        .strength(0.1F).lightLevel((state) -> 11).noOcclusion()));

        public static final RegistryObject<Block> PART = BLOCKS.register("part",
                        () -> new PartBlock(BlockBehaviour.Properties.of()
                                        .strength(0.1F).lightLevel((state) -> 11).noOcclusion()));

        public static final RegistryObject<BlockItem> EXCAVATOR_BLOCK_ITEM = ITEMS.register("excavator",
                        () -> new ItemNameBlockItem(EXCAVATOR_BLOCK.get(), new Item.Properties()));

        public static final RegistryObject<BlockItem> MIXER_BLOCK_ITEM = ITEMS.register("mixer",
                        () -> new ItemNameBlockItem(MIXER_BLOCK.get(), new Item.Properties()));

        public static final RegistryObject<BlockItem> KILN_BLOCK_ITEM = ITEMS.register("furnace",
                        () -> new ItemNameBlockItem(KILN_BLOCK.get(), new Item.Properties()));

        public static final RegistryObject<BlockItem> SIFTER_BLOCK_ITEM = ITEMS.register("sifter",
                        () -> new ItemNameBlockItem(SIFTER_BLOCK.get(), new Item.Properties()));

        public static final RegistryObject<Item> WHEEL = ITEMS.register("wheel", () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> BUCKET = ITEMS.register("bucket",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> COIN = ITEMS.register("coin",
                        () -> new Item(new Item.Properties().durability(777)));

        public static final Item[] EXCAVATABLE = new Item[] {
                        Items.CLAY,
                        Items.DIRT,
                        Items.GRAVEL,
                        Items.RED_SAND,
                        Items.SAND,
                        Items.SNOW_BLOCK
        };
        private static final String[] EXCAVATABLE_TYPES = { "clay", "dirt", "gravel", "red_sand", "sand", "snow" };
        public static final RegistryObject<Item>[] EXCAVATABLE_BLOCKS = Arrays.stream(EXCAVATABLE_TYPES)
                        .map(x -> ITEMS.register("ex_" + x, () -> new Item(new Item.Properties())))
                        .toArray(RegistryObject[]::new);

        private static final String[] BLASTING_TYPES = { "brick", "glass", "glazed", "terracota", "nether", "stone" };
        public static final RegistryObject<Item>[] BLASTING_BLOCKS = Arrays.stream(BLASTING_TYPES)
                        .map(x -> ITEMS.register("blast_" + x, () -> new Item(new Item.Properties())))
                        .toArray(RegistryObject[]::new);

        public static final Item[] SIFTABLE = new Item[] {
                        Items.CLAY,
                        Items.DIRT,
                        Items.GRAVEL,
                        Items.RED_SAND,
                        Items.SAND,
                        Items.SOUL_SAND
        };

        private static final String[] SIFTABLE_TYPES = { "clay", "dirt", "gravel", "red_sand", "sand", "soul_sand" };
        public static final RegistryObject<Item>[] SIFTABLE_BLOCKS = Arrays.stream(SIFTABLE_TYPES)
                        .map(x -> ITEMS.register("sift_" + x, () -> new Item(new Item.Properties())))
                        .toArray(RegistryObject[]::new);
}
