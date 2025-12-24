package com.ave.simplestationsmason.recipes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import com.ave.simplestationsmason.SimpleStationsMason;
import com.ave.simplestationsmason.blockentity.SifterBlockEntity;
import com.ave.simplestationsmason.datagen.ModTags;
import com.ave.simplestationsmason.registrations.Registrations;
import com.ave.simplestationsmason.registrations.VanillaBlocks;
import com.ave.simplestationsmason.screen.ExcavatorScreen;
import com.ave.simplestationsmason.screen.FurnaceScreen;
import com.ave.simplestationsmason.screen.MixerScreen;
import com.ave.simplestationsmason.screen.SifterScreen;
import com.ave.simplestationsmason.uihelpers.UIBlocks;
import com.google.common.collect.Lists;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

@JeiPlugin
public class JEIModPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(SimpleStationsMason.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new FurnaceRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new ExcavatorRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new MixerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new SifterRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(FurnaceRecipeCategory.REGULAR, this.getKilnRecipes());

        var excavatorRecipes = Arrays.stream(Registrations.EXCAVATABLE)
                .map(x -> new SimpleRecipe(new ItemStack(x), new ItemStack(x))).toList();
        registration.addRecipes(ExcavatorRecipeCategory.REGULAR, excavatorRecipes);

        var mixerRecipes = IntStream.range(0, 16)
                .mapToObj(x -> new SimpleRecipe(new ItemStack(VanillaBlocks.COLOR_DYES[x], 2),
                        new ItemStack(VanillaBlocks.CONCRETE[x], 32)))
                .toList();
        registration.addRecipes(MixerRecipeCategory.REGULAR, mixerRecipes);

        registration.addRecipes(SifterRecipeCategory.REGULAR, this.getSifterRecipes());
    }

    private List<SimpleRecipe> getKilnRecipes() {
        List<SimpleRecipe> list = Lists.newArrayList();
        list.add(new SimpleRecipe(new ItemStack(Items.CLAY_BALL), new ItemStack(Items.BRICK)));
        list.add(new SimpleRecipe(new ItemStack(Items.SAND), new ItemStack(Items.GLASS)));
        list.add(new SimpleRecipe(new ItemStack(Items.RED_SAND), new ItemStack(Items.GLASS)));
        list.add(new SimpleRecipe(new ItemStack(Items.CLAY), new ItemStack(Items.TERRACOTTA)));

        list.add(new SimpleRecipe(new ItemStack(Items.SAND), Tags.Items.GLASS));
        list.add(new SimpleRecipe(new ItemStack(Items.RED_SAND), Tags.Items.GLASS));
        list.add(new SimpleRecipe(new ItemStack(Items.CLAY), ItemTags.TERRACOTTA));
        list.add(new SimpleRecipe(ItemTags.TERRACOTTA, ModTags.Items.GLAZED_TAG));
        return list;
    }

    private List<SimpleRecipe> getSifterRecipes() {
        List<SimpleRecipe> recipes = Lists.newArrayList();
        var level = Minecraft.getInstance().level;
        var all = level.getRecipeManager().getAllRecipesFor(SifterRecipe.Type.INSTANCE);
        for (var recipe : all) {
            var stack = new ItemStack(recipe.from.getItems()[0].getItem(), SifterBlockEntity.BATCH_SIZE);
            for (var roll : recipe.rolls)
                recipes.add(new SimpleRecipe(stack, new ItemStack(roll.output(), roll.count()), roll.chance()));
        }
        return recipes;
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
        registry.addRecipeCatalyst(new ItemStack(Registrations.EXCAVATOR.getBlock()), ExcavatorRecipeCategory.REGULAR);
        registry.addRecipeCatalyst(new ItemStack(Registrations.MIXER.getBlock()), MixerRecipeCategory.REGULAR);
        registry.addRecipeCatalyst(new ItemStack(Registrations.FURNACE.getBlock()), FurnaceRecipeCategory.REGULAR);
        registry.addRecipeCatalyst(new ItemStack(Registrations.SIFTER.getBlock()), SifterRecipeCategory.REGULAR);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(ExcavatorScreen.class, UIBlocks.OUT_SLOT.left - 16, 6,
                UIBlocks.OUT_SLOT.width + 32, UIBlocks.OUT_SLOT.height, ExcavatorRecipeCategory.REGULAR);
        registration.addRecipeClickArea(FurnaceScreen.class, UIBlocks.OUT_SLOT.left - 16, 6,
                UIBlocks.OUT_SLOT.width + 32, UIBlocks.OUT_SLOT.height, FurnaceRecipeCategory.REGULAR);
        registration.addRecipeClickArea(SifterScreen.class, UIBlocks.OUT_SLOT.left - 16, 6,
                UIBlocks.OUT_SLOT.width + 32, UIBlocks.OUT_SLOT.height, SifterRecipeCategory.REGULAR);
        registration.addRecipeClickArea(MixerScreen.class, UIBlocks.OUT_SLOT.left - 32, 6,
                UIBlocks.OUT_SLOT.width + 64, UIBlocks.OUT_SLOT.height, MixerRecipeCategory.REGULAR);
    }
}
