package com.ave.simplestationsmason.recipes;

import java.util.List;

import com.ave.simplestationsmason.SimpleStationsMason;
import com.ave.simplestationsmason.blockentity.enums.CropGroup;
import com.ave.simplestationsmason.blockentity.enums.CropType;
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
        registration.addRecipeCategories(new FarmRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(FarmRecipeCategory.REGULAR, this.getRecipes(CropGroup.Crop));
    }

    private List<SimpleRecipe> getRecipes(CropGroup group) {
        List<SimpleRecipe> list = Lists.newArrayList();
        for (var c : CropType.values()) {
            if (c.equals(CropType.Unknown) || !c.group.equals(group))
                continue;
            if (c.seed != null)
                list.add(new SimpleRecipe(new ItemStack(c.seed), new ItemStack(c.product, c.output)));
            if (c.tag != null)
                list.add(new SimpleRecipe(c.tag));
        }
        return list;
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.EXCAVATOR_BLOCK.get()), FarmRecipeCategory.REGULAR);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(ExcavatorScreen.class, UIBlocks.OUT_SLOT.left - 16, 6,
                UIBlocks.OUT_SLOT.width + 32, UIBlocks.OUT_SLOT.height, FarmRecipeCategory.REGULAR);
    }
}
