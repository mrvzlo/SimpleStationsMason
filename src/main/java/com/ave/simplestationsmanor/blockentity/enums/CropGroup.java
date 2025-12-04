package com.ave.simplestationsmanor.blockentity.enums;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public enum CropGroup {
    Unknown(null), Crop(Items.BONE_MEAL), Tree(Items.BONE_MEAL), Dark(Items.ROTTEN_FLESH), Forage(Items.BONE_MEAL);

    public final Item fertilizer;

    private CropGroup(Item fertilizer) {
        this.fertilizer = fertilizer;
    }
}
