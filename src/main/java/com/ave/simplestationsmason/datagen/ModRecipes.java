package com.ave.simplestationsmason.datagen;

import com.ave.simplestationsmason.SimpleStationsMason;
import com.ave.simplestationsmason.recipes.SifterRecipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister
            .create(Registries.RECIPE_SERIALIZER, SimpleStationsMason.MODID);
    public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(Registries.RECIPE_TYPE,
            SimpleStationsMason.MODID);

    public static final RegistryObject<RecipeSerializer<SifterRecipe>> SIFTER_SERIALIZER = SERIALIZERS
            .register("sifter", SifterRecipe.Serializer::new);
    public static final RegistryObject<RecipeType<SifterRecipe>> SIFTER_TYPE = TYPES
            .register("sifter", () -> new RecipeType<SifterRecipe>() {
                @Override
                public String toString() {
                    return "sifter";
                }
            });

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
