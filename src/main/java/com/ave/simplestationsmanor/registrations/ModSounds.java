package com.ave.simplestationsmanor.registrations;

import java.util.function.Supplier;

import com.ave.simplestationsmanor.SimpleStationsManor;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSounds {
        public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister
                        .create(BuiltInRegistries.SOUND_EVENT, SimpleStationsManor.MODID);

        public static final Supplier<SoundEvent> WORK_SOUND = SOUND_EVENTS.register(
                        "work_sound",
                        () -> SoundEvent
                                        .createVariableRangeEvent(ResourceLocation
                                                        .fromNamespaceAndPath(SimpleStationsManor.MODID,
                                                                        "work_sound")));

}
