package com.ave.simplestationsmason.blockentity.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public enum CropType implements StringRepresentable {
    Unknown(CropGroup.Unknown, null, null, 0),
    WHEAT(CropGroup.Crop, Items.WHEAT_SEEDS, Items.WHEAT, 32),
    BEETROOT(CropGroup.Crop, Items.BEETROOT_SEEDS, Items.BEETROOT, 32),
    CARROT(CropGroup.Crop, Items.CARROT, 32),
    POTATO(CropGroup.Crop, Items.POTATO, 32),
    MELON(CropGroup.Crop, Items.MELON_SEEDS, Items.MELON, 16),
    PUMPKIN(CropGroup.Crop, Items.PUMPKIN_SEEDS, Items.PUMPKIN, 16),
    BROWN_MUSHROOM(CropGroup.Dark, Items.BROWN_MUSHROOM, 32),
    RED_MUSHROOM(CropGroup.Dark, Items.RED_MUSHROOM, 32),
    NETHER_WART(CropGroup.Dark, Items.NETHER_WART, 64),
    CHORUS(CropGroup.Dark, Items.CHORUS_FRUIT, 16),
    ACACIA(CropGroup.Tree, Items.ACACIA_SAPLING, Items.ACACIA_LOG, 32),
    BIRCH(CropGroup.Tree, Items.BIRCH_SAPLING, Items.BIRCH_LOG, 32),
    CHERRY(CropGroup.Tree, Items.CHERRY_SAPLING, Items.CHERRY_LOG, 32),
    CR_STEM(CropGroup.Tree, Items.CRIMSON_FUNGUS, Items.CRIMSON_STEM, 32),
    DARK_OAK(CropGroup.Tree, Items.DARK_OAK_SAPLING, Items.DARK_OAK_LOG, 32),
    JUNGLE(CropGroup.Tree, Items.JUNGLE_SAPLING, Items.JUNGLE_LOG, 32),
    MANGROVE(CropGroup.Tree, Items.MANGROVE_PROPAGULE, Items.MANGROVE_LOG, 32),
    OAK(CropGroup.Tree, Items.OAK_SAPLING, Items.OAK_LOG, 32),
    SPRUCE(CropGroup.Tree, Items.SPRUCE_SAPLING, Items.SPRUCE_LOG, 32),
    WD_STEM(CropGroup.Tree, Items.WARPED_FUNGUS, Items.WARPED_STEM, 32),
    BAMBOO(CropGroup.Tree, Items.BAMBOO, 64),
    APPLE(CropGroup.Forage, Items.APPLE, 16),
    CACAO(CropGroup.Forage, Items.COCOA_BEANS, 16),
    BERRY(CropGroup.Forage, Items.SWEET_BERRIES, 16),
    GLOWBERRY(CropGroup.Forage, Items.GLOW_BERRIES, 16),
    CACTUS(CropGroup.Forage, Items.CACTUS, 16),
    SUGAR(CropGroup.Forage, Items.SUGAR_CANE, 16),
    FLOWERS(CropGroup.Forage, ItemTags.FLOWERS, 8);

    public final Item seed;
    public final Item product;
    public final int output;
    public final CropGroup group;
    public final TagKey tag;

    CropType(CropGroup group, Item seed, int output) {
        this.seed = seed;
        this.group = group;
        this.product = seed;
        this.output = output;
        this.tag = null;
    }

    CropType(CropGroup group, TagKey tag, int output) {
        this.group = group;
        this.output = output;
        this.tag = tag;
        this.seed = null;
        this.product = null;
    }

    CropType(CropGroup group, Item seed, Item product, int output) {
        this.seed = seed;
        this.product = product;
        this.output = output;
        this.group = group;
        this.tag = null;
    }

    public static CropType findById(int type) {
        return CropType.values()[type];
    }

    public static CropType findBySeed(Item stack) {
        for (var c : values()) {
            if (c.seed != null && stack.equals(c.seed))
                return c;
            if (c.tag != null && new ItemStack(stack).is(c.tag))
                return c;
        }

        return CropType.Unknown;
    }

    @Override
    public String getSerializedName() {
        return name().toLowerCase();
    }

    public Item getProduct() {
        if (product != null)
            return product;
        if (tag != null) {
            List<Holder<Item>> list = new ArrayList<>();
            BuiltInRegistries.ITEM.getTagOrEmpty(tag).forEach(x -> list.add((Holder<Item>) x));
            var rand = ThreadLocalRandom.current().nextInt(list.size());
            return list.get(rand).value();
        }
        return null;
    }
}
