package com.ave.simplestationsmason.registrations;

import com.ave.simplestationsmason.SimpleStationsMason;
import com.ave.simplestationsmason.blockentity.ExcavatorBlockEntity;
import com.ave.simplestationsmason.blockentity.KilnBlockEntity;
import com.ave.simplestationsmason.blockentity.MixerBlockEntity;
import com.ave.simplestationsmason.blockentity.SifterBlockEntity;
import com.ave.simplestationsmason.blockentity.partblock.PartBlockEntity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
        public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
                        .create(Registries.BLOCK_ENTITY_TYPE, SimpleStationsMason.MODID);

        public static final RegistryObject<BlockEntityType<ExcavatorBlockEntity>> EXCAVATOR_ENTITY = BLOCK_ENTITIES
                        .register("excavator", () -> BlockEntityType.Builder
                                        .of(ExcavatorBlockEntity::new, ModBlocks.EXCAVATOR_BLOCK.get()).build(null));

        public static final RegistryObject<BlockEntityType<MixerBlockEntity>> MIXER_ENTITY = BLOCK_ENTITIES
                        .register("mixer", () -> BlockEntityType.Builder
                                        .of(MixerBlockEntity::new, ModBlocks.MIXER_BLOCK.get()).build(null));

        public static final RegistryObject<BlockEntityType<KilnBlockEntity>> KILN_ENTITY = BLOCK_ENTITIES
                        .register("kiln", () -> BlockEntityType.Builder
                                        .of(KilnBlockEntity::new, ModBlocks.KILN_BLOCK.get()).build(null));

        public static final RegistryObject<BlockEntityType<SifterBlockEntity>> SIFTER_ENTITY = BLOCK_ENTITIES
                        .register("sifter", () -> BlockEntityType.Builder
                                        .of(SifterBlockEntity::new, ModBlocks.SIFTER_BLOCK.get()).build(null));

        public static final RegistryObject<BlockEntityType<PartBlockEntity>> PART_ENTITY = BLOCK_ENTITIES
                        .register("part", () -> BlockEntityType.Builder
                                        .of(PartBlockEntity::new, ModBlocks.PART.get()).build(null));
}