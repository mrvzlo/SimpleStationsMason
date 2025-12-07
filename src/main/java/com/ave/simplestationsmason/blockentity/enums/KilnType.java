package com.ave.simplestationsmason.blockentity.enums;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public enum KilnType implements StringRepresentable {
    Unknown(null, null),
    Glass(Items.SAND, Items.GLASS),
    Terracota(Items.CLAY, Items.TERRACOTTA),
    // Glazed(Items.glazed),
    Bricks(Items.CLAY_BALL, Items.BRICK);

    public final Item source;
    public final Item product;

    KilnType(Item source, Item product) {
        this.source = source;
        this.product = product;
    }

    public static KilnType findById(int type) {
        return KilnType.values()[type];
    }

    public static KilnType findBySeed(Item stack) {
        for (var c : values()) {
            if (c.product != null && stack.equals(c.product))
                return c;
        }

        return KilnType.Unknown;
    }

    @Override
    public String getSerializedName() {
        return name().toLowerCase();
    }
}
