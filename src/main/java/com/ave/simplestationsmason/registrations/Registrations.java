package com.ave.simplestationsmason.registrations;

import com.ave.simplestationscore.registrations.RegistrationManager;
import com.ave.simplestationscore.registrations.Station;
import com.ave.simplestationsmason.SimpleStationsMason;
import com.ave.simplestationsmason.blockentity.ExcavatorBlock;
import com.ave.simplestationsmason.blockentity.ExcavatorBlockEntity;
import com.ave.simplestationsmason.blockentity.FurnaceBlock;
import com.ave.simplestationsmason.blockentity.FurnaceBlockEntity;
import com.ave.simplestationsmason.blockentity.MixerBlock;
import com.ave.simplestationsmason.blockentity.MixerBlockEntity;
import com.ave.simplestationsmason.blockentity.SifterBlock;
import com.ave.simplestationsmason.blockentity.SifterBlockEntity;
import com.ave.simplestationsmason.screen.ExcavatorMenu;
import com.ave.simplestationsmason.screen.MixerMenu;
import com.ave.simplestationsmason.screen.SifterMenu;
import com.ave.simplestationsmason.screen.FurnaceMenu;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

public class Registrations {
        public static final RegistrationManager MANAGER = new RegistrationManager(SimpleStationsMason.MODID);

        public static final Station<ExcavatorBlockEntity, ExcavatorBlock> EXCAVATOR = MANAGER.registerStation(
                        "excavator", (p) -> new ExcavatorBlock(p), ExcavatorBlockEntity::new);
        public static final Station<MixerBlockEntity, MixerBlock> MIXER = MANAGER.registerStation(
                        "mixer", (p) -> new MixerBlock(p), MixerBlockEntity::new);
        public static final Station<FurnaceBlockEntity, FurnaceBlock> FURNACE = MANAGER.registerStation(
                        "furnace", (p) -> new FurnaceBlock(p), FurnaceBlockEntity::new);
        public static final Station<SifterBlockEntity, SifterBlock> SIFTER = MANAGER.registerStation(
                        "sifter", (p) -> new SifterBlock(p), SifterBlockEntity::new);

        public static final DeferredItem<Item> WHEEL = MANAGER.registerItem("wheel");
        public static final DeferredItem<Item> BUCKET = MANAGER.registerItem("bucket");
        public static final DeferredItem<Item> COIN = MANAGER.ITEMS.registerItem("coin", Item::new,
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
        public static final DeferredItem<Item>[] EXCAVATABLE_BLOCKS = MANAGER.registerEmptyItems("ex_",
                        EXCAVATABLE_TYPES);

        private static final String[] BLASTING_TYPES = { "brick", "glass", "glazed", "terracota", "nether", "stone" };
        public static final DeferredItem<Item>[] BLASTING_BLOCKS = MANAGER.registerEmptyItems("blast_",
                        BLASTING_TYPES);

        public static final Item[] SIFTABLE = new Item[] {
                        Items.CLAY,
                        Items.DIRT,
                        Items.GRAVEL,
                        Items.RED_SAND,
                        Items.SAND,
                        Items.SOUL_SAND
        };

        private static final String[] SIFTABLE_TYPES = { "clay", "dirt", "gravel", "red_sand", "sand", "soul_sand" };
        public static final DeferredItem<Item>[] SIFTABLE_BLOCKS = MANAGER.registerEmptyItems("sift_",
                        SIFTABLE_TYPES);

        public static final DeferredHolder<MenuType<?>, MenuType<ExcavatorMenu>> EXCAVATOR_MENU = MANAGER
                        .registerMenuType("excavator_menu", ExcavatorMenu::new);
        public static final DeferredHolder<MenuType<?>, MenuType<MixerMenu>> MIXER_MENU = MANAGER
                        .registerMenuType("mixer_menu", MixerMenu::new);
        public static final DeferredHolder<MenuType<?>, MenuType<FurnaceMenu>> FURNACE_MENU = MANAGER
                        .registerMenuType("furnace_menu", FurnaceMenu::new);
        public static final DeferredHolder<MenuType<?>, MenuType<SifterMenu>> SIFTER_MENU = MANAGER
                        .registerMenuType("sifter_menu", SifterMenu::new);
}
