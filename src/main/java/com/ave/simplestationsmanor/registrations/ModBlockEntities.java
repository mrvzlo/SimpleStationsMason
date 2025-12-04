package com.ave.simplestationsmanor.registrations;

import com.ave.simplestationsmanor.SimpleStationsManor;
import com.ave.simplestationsmanor.blockentity.ExcavatorBlockEntity;
import com.ave.simplestationsmanor.blockentity.partblock.PartBlockEntity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {
        public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
                        .create(Registries.BLOCK_ENTITY_TYPE, SimpleStationsManor.MODID);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ExcavatorBlockEntity>> EXCAVATOR_ENTITY = BLOCK_ENTITIES
                        .register("excavator", () -> BlockEntityType.Builder
                                        .of(ExcavatorBlockEntity::new, ModBlocks.EXCAVATOR_BLOCK.get()).build(null));

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PartBlockEntity>> PART_ENTITY = BLOCK_ENTITIES
                        .register("part", () -> BlockEntityType.Builder
                                        .of(PartBlockEntity::new, ModBlocks.PART.get()).build(null));
}