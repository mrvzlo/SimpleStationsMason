package com.ave.simplestationsmason.recipes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import com.ave.simplestationsmason.SimpleStationsMason;
import com.ave.simplestationsmason.registrations.ModBlocks;
import com.ave.simplestationsmason.registrations.VanillaBlocks;
import com.ave.simplestationsmason.screen.ExcavatorScreen;
import com.ave.simplestationsmason.screen.KilnScreen;
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
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;

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
        registration.addRecipes(KilnRecipeCategory.REGULAR, this.getKilnRecipes());

        var excavatorRecipes = Arrays.stream(ModBlocks.EXCAVATABLE)
                .map(x -> new SimpleRecipe(new ItemStack(x), new ItemStack(x))).toList();
        registration.addRecipes(ExcavatorRecipeCategory.REGULAR, excavatorRecipes);

        var mixerRecipes = IntStream.range(0, 16)
                .mapToObj(x -> new SimpleRecipe(new ItemStack(VanillaBlocks.COLOR_DYES[x]),
                        new ItemStack(VanillaBlocks.CONCRETE[x], 32)))
                .toList();
        registration.addRecipes(MixerRecipeCategory.REGULAR, mixerRecipes);
    }

    private List<SimpleRecipe> getKilnRecipes() {
        List<SimpleRecipe> list = Lists.newArrayList();
        list.add(new SimpleRecipe(new ItemStack(Items.CLAY_BALL), new ItemStack(Items.BRICK)));
        list.add(new SimpleRecipe(new ItemStack(Items.SAND), Tags.Items.GLASS_BLOCKS_CHEAP));
        list.add(new SimpleRecipe(new ItemStack(Items.RED_SAND), Tags.Items.GLASS_BLOCKS_CHEAP));
        list.add(new SimpleRecipe(new ItemStack(Items.CLAY), ItemTags.TERRACOTTA));
        list.add(new SimpleRecipe(new ItemStack(Items.TERRACOTTA), Tags.Items.GLAZED_TERRACOTTAS));
        list.add(new SimpleRecipe(ItemTags.TERRACOTTA, Tags.Items.GLAZED_TERRACOTTAS));
        return list;
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.EXCAVATOR_BLOCK.get()), ExcavatorRecipeCategory.REGULAR);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.MIXER_BLOCK.get()), MixerRecipeCategory.REGULAR);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.KILN_BLOCK.get()), KilnRecipeCategory.REGULAR);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(ExcavatorScreen.class, UIBlocks.OUT_SLOT.left - 16, 6,
                UIBlocks.OUT_SLOT.width + 32, UIBlocks.OUT_SLOT.height, ExcavatorRecipeCategory.REGULAR);
        registration.addRecipeClickArea(KilnScreen.class, UIBlocks.OUT_SLOT.left - 16, 6,
                UIBlocks.OUT_SLOT.width + 32, UIBlocks.OUT_SLOT.height, KilnRecipeCategory.REGULAR);
        registration.addRecipeClickArea(MixerScreen.class, UIBlocks.OUT_SLOT.left - 32, 6,
                UIBlocks.OUT_SLOT.width + 64, UIBlocks.OUT_SLOT.height, MixerRecipeCategory.REGULAR);
    }
}
