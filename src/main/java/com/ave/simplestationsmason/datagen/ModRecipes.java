package com.ave.simplestationsmason.datagen;

import com.ave.simplestationsmason.SimpleStationsMason;
import com.ave.simplestationsmason.recipes.SifterRecipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister
            .create(ForgeRegistries.RECIPE_SERIALIZERS, SimpleStationsMason.MODID);

    public static final RegistryObject<RecipeSerializer<SifterRecipe>> SIFTER_SERIALIZER = SERIALIZERS
            .register("sifter", () -> SifterRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
