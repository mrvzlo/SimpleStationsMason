package com.ave.simplestationsmason.recipes;

import org.jetbrains.annotations.Nullable;

import com.ave.simplestationsmason.SimpleStationsMason;
import com.ave.simplestationsmason.registrations.Registrations;
import com.ave.simplestationsmason.uihelpers.UIBlocks;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;

public class FurnaceRecipeCategory implements IRecipeCategory<SimpleRecipe> {
        private static final String Path = "kiln";

        public final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(SimpleStationsMason.MODID, Path);
        private final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(
                        SimpleStationsMason.MODID,
                        "textures/gui/kiln_jei.png");

        public IGuiHelper guiHelper;
        public static RecipeType<SimpleRecipe> REGULAR = RecipeType.create(SimpleStationsMason.MODID, Path,
                        SimpleRecipe.class);

        private final IDrawableStatic bg;

        public FurnaceRecipeCategory(IGuiHelper guiHelper) {
                this.guiHelper = guiHelper;
                bg = guiHelper.createDrawable(TEXTURE, 0, 0, 176, 80);
        }

        @Override
        public RecipeType<SimpleRecipe> getRecipeType() {
                return REGULAR;
        }

        @Override
        public Component getTitle() {
                return Component.translatable("screen.simplestationsmason.kiln_recipes");
        }

        @Override
        public @Nullable IDrawable getIcon() {
                return guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
                                new ItemStack(Registrations.EXCAVATOR.item.get()));
        }

        @Override
        public void setRecipe(IRecipeLayoutBuilder builder, SimpleRecipe recipe, IFocusGroup focuses) {
                if (recipe.sourceTag != null)
                        builder.addSlot(RecipeIngredientRole.INPUT, UIBlocks.FILTER_SLOT.left, UIBlocks.FILTER_SLOT.top)
                                        .addIngredients(Ingredient.of(recipe.sourceTag));
                else
                        builder.addSlot(RecipeIngredientRole.INPUT, UIBlocks.FILTER_SLOT.left, UIBlocks.FILTER_SLOT.top)
                                        .addIngredients(Ingredient.of(recipe.source.getItem()));
                if (recipe.productTag != null) {
                        builder.addSlot(RecipeIngredientRole.INPUT, UIBlocks.FILTER3_SLOT.left,
                                        UIBlocks.FILTER3_SLOT.top)
                                        .addIngredients(Ingredient.of(Tags.Items.DYES));
                        builder.addSlot(RecipeIngredientRole.OUTPUT, UIBlocks.OUT_SLOT.left, UIBlocks.OUT_SLOT.top)
                                        .addIngredients(Ingredient.of(recipe.productTag));
                } else
                        builder.addSlot(RecipeIngredientRole.OUTPUT, UIBlocks.OUT_SLOT.left, UIBlocks.OUT_SLOT.top)
                                        .addIngredients(Ingredient.of(recipe.product));
        }

        @Override
        public IDrawable getBackground() {
                return bg;
        }
}
