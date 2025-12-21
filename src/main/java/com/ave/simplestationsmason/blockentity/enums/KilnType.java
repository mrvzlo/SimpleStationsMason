package com.ave.simplestationsmason.blockentity.enums;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public enum KilnType implements StringRepresentable {
    Unknown(null, null),
    Glass(Items.SAND, Items.GLASS),
    Glass2(Items.RED_SAND, Items.GLASS),
    Terracota(Items.CLAY, Items.TERRACOTTA),
    Bricks(Items.CLAY_BALL, Items.BRICK),
    NetherBricks(Items.NETHERRACK, Items.NETHER_BRICK),
    Stone(Items.COBBLESTONE, Items.STONE),
    Glazed0(Items.TERRACOTTA, Items.WHITE_GLAZED_TERRACOTTA),
    Glazed1(Items.WHITE_TERRACOTTA, Items.WHITE_GLAZED_TERRACOTTA),
    Glazed2(Items.ORANGE_TERRACOTTA, Items.ORANGE_GLAZED_TERRACOTTA),
    Glazed3(Items.MAGENTA_TERRACOTTA, Items.MAGENTA_GLAZED_TERRACOTTA),
    Glazed4(Items.LIGHT_BLUE_TERRACOTTA, Items.LIGHT_BLUE_GLAZED_TERRACOTTA),
    Glazed5(Items.YELLOW_TERRACOTTA, Items.YELLOW_GLAZED_TERRACOTTA),
    Glazed6(Items.LIME_TERRACOTTA, Items.LIME_GLAZED_TERRACOTTA),
    Glazed7(Items.PINK_TERRACOTTA, Items.PINK_GLAZED_TERRACOTTA),
    Glazed8(Items.GRAY_TERRACOTTA, Items.GRAY_GLAZED_TERRACOTTA),
    Glazed9(Items.LIGHT_GRAY_TERRACOTTA, Items.LIGHT_GRAY_GLAZED_TERRACOTTA),
    Glazed10(Items.CYAN_TERRACOTTA, Items.CYAN_GLAZED_TERRACOTTA),
    Glazed11(Items.PURPLE_TERRACOTTA, Items.PURPLE_GLAZED_TERRACOTTA),
    Glazed12(Items.BLUE_TERRACOTTA, Items.BLUE_GLAZED_TERRACOTTA),
    Glazed13(Items.BROWN_TERRACOTTA, Items.BROWN_GLAZED_TERRACOTTA),
    Glazed14(Items.GREEN_TERRACOTTA, Items.GREEN_GLAZED_TERRACOTTA),
    Glazed15(Items.RED_TERRACOTTA, Items.RED_GLAZED_TERRACOTTA),
    Glazed16(Items.BLACK_TERRACOTTA, Items.BLACK_GLAZED_TERRACOTTA);

    public final Item source;
    public final Item product;

    KilnType(Item source, Item product) {
        this.source = source;
        this.product = product;
    }

    public static KilnType findById(int type) {
        return KilnType.values()[type];
    }

    public static KilnType findBySource(Item stack) {
        for (var c : values()) {
            if (c.source != null && stack.equals(c.source))
                return c;
        }

        return KilnType.Unknown;
    }

    @Override
    public String getSerializedName() {
        return name().toLowerCase();
    }
}
