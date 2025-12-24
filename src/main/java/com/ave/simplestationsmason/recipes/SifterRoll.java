package com.ave.simplestationsmason.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

public record SifterRoll(Item output, int count, int chance) {
        public static final Codec<SifterRoll> CODEC = RecordCodecBuilder.create(inst -> inst.group(
                        BuiltInRegistries.ITEM.byNameCodec()
                                        .fieldOf("output")
                                        .forGetter(SifterRoll::output),
                        Codec.INT.fieldOf("count")
                                        .forGetter(SifterRoll::count),
                        Codec.INT.fieldOf("chance")
                                        .forGetter(SifterRoll::chance))
                        .apply(inst, SifterRoll::new));
}