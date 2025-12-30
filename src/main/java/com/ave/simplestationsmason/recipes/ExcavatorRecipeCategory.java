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
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class ExcavatorRecipeCategory implements IRecipeCategory<SimpleRecipe> {
        private static final String Path = "excavator";

        public final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(SimpleStationsMason.MODID, Path);
        private final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(
                        SimpleStationsMason.MODID,
                        "textures/gui/excavator_jei.png");

        public IGuiHelper guiHelper;
        public static RecipeType<SimpleRecipe> REGULAR = RecipeType.create(SimpleStationsMason.MODID, Path,
                        SimpleRecipe.class);

        private final IDrawableStatic bg;

        public ExcavatorRecipeCategory(IGuiHelper guiHelper) {
                this.guiHelper = guiHelper;
                bg = guiHelper.createDrawable(TEXTURE, 0, 0, 176, 80);
        }

        @Override
        public RecipeType<SimpleRecipe> getRecipeType() {
                return REGULAR;
        }

        @Override
        public Component getTitle() {
                return Component.translatable("screen.simplestationsmason.excavator_recipes");
        }

        @Override
        public @Nullable IDrawable getIcon() {
                return guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
                                new ItemStack(Registrations.EXCAVATOR.getItem()));
        }

        @Override
        public void setRecipe(IRecipeLayoutBuilder builder, SimpleRecipe recipe, IFocusGroup focuses) {
                builder.addSlot(RecipeIngredientRole.INPUT, UIBlocks.FUEL_SLOT.left, UIBlocks.FUEL_SLOT.top)
                                .addIngredients(Ingredient.of(Items.COAL));
                builder.addSlot(RecipeIngredientRole.OUTPUT, UIBlocks.OUT_SLOT.left, UIBlocks.OUT_SLOT.top)
                                .addItemStack(recipe.product);
                builder.addSlot(RecipeIngredientRole.CATALYST, UIBlocks.FILTER_SLOT.left, UIBlocks.FILTER_SLOT.top)
                                .addIngredients(Ingredient.of(recipe.source.getItem()));
        }

        @Override
        public IDrawable getBackground() {
                return bg;
        }
}
