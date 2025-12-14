package com.ave.simplestationsmason.recipes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import com.ave.simplestationsmason.SimpleStationsMason;
import com.ave.simplestationsmason.blockentity.enums.KilnType;
import com.ave.simplestationsmason.registrations.ModBlocks;
import com.ave.simplestationsmason.registrations.VanillaBlocks;
import com.ave.simplestationsmason.screen.ExcavatorScreen;
import com.ave.simplestationsmason.screen.MixerScreen;
import com.ave.simplestationsmason.uihelpers.UIBlocks;
import com.google.common.collect.Lists;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

@JeiPlugin
public class JEIModPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(SimpleStationsMason.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new KilnRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new ExcavatorRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new MixerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(KilnRecipeCategory.REGULAR, this.getRecipes());
        var excavatorRecipes = Arrays.stream(ModBlocks.EXCAVATABLE)
                .map(x -> new SimpleRecipe(new ItemStack(x), new ItemStack(x))).toList();
        registration.addRecipes(ExcavatorRecipeCategory.REGULAR, excavatorRecipes);

        var mixerRecipes = IntStream.range(0, 16)
                .mapToObj(x -> new SimpleRecipe(new ItemStack(VanillaBlocks.COLOR_DYES[x]),
                        new ItemStack(VanillaBlocks.CONCRETE[x], 32)))
                .toList();
        registration.addRecipes(MixerRecipeCategory.REGULAR, mixerRecipes);
    }

    private List<SimpleRecipe> getRecipes() {
        List<SimpleRecipe> list = Lists.newArrayList();
        for (var c : KilnType.values()) {
            if (c.equals(KilnType.Unknown))
                continue;
            list.add(new SimpleRecipe(new ItemStack(c.source), new ItemStack(c.product)));
        }
        return list;
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.EXCAVATOR_BLOCK.get()), ExcavatorRecipeCategory.REGULAR);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.MIXER_BLOCK.get()), MixerRecipeCategory.REGULAR);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(ExcavatorScreen.class, UIBlocks.OUT_SLOT.left - 16, 6,
                UIBlocks.OUT_SLOT.width + 32, UIBlocks.OUT_SLOT.height, ExcavatorRecipeCategory.REGULAR);
        registration.addRecipeClickArea(MixerScreen.class, UIBlocks.OUT_SLOT.left - 32, 6,
                UIBlocks.OUT_SLOT.width + 64, UIBlocks.OUT_SLOT.height, MixerRecipeCategory.REGULAR);
    }
}
