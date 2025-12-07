package com.ave.simplestationsmason.recipes;

import java.util.List;

import com.ave.simplestationsmason.SimpleStationsMason;
import com.ave.simplestationsmason.blockentity.enums.KilnType;
import com.ave.simplestationsmason.registrations.ModBlocks;
import com.ave.simplestationsmason.screen.ExcavatorScreen;
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
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(KilnRecipeCategory.REGULAR, this.getRecipes());
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
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.EXCAVATOR_BLOCK.get()), KilnRecipeCategory.REGULAR);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(ExcavatorScreen.class, UIBlocks.OUT_SLOT.left - 16, 6,
                UIBlocks.OUT_SLOT.width + 32, UIBlocks.OUT_SLOT.height, KilnRecipeCategory.REGULAR);
    }
}
