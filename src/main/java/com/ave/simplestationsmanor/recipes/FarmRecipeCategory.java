package com.ave.simplestationsmanor.recipes;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.ave.simplestationsmanor.SimpleStationsManor;
import com.ave.simplestationsmanor.blockentity.ExcavatorBlockEntity;
import com.ave.simplestationsmanor.registrations.ModBlocks;
import com.ave.simplestationsmanor.uihelpers.UIBlocks;
import com.google.common.collect.Lists;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
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

public class FarmRecipeCategory implements IRecipeCategory<SimpleRecipe> {
        private static final String Path = "farm";

        public final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(SimpleStationsManor.MODID, Path);
        private final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(
                        SimpleStationsManor.MODID,
                        "textures/gui/farm_jei.png");

        public IGuiHelper guiHelper;
        public static RecipeType<SimpleRecipe> REGULAR = RecipeType.create(SimpleStationsManor.MODID, Path,
                        SimpleRecipe.class);

        private final IDrawableStatic bg;

        public FarmRecipeCategory(IGuiHelper guiHelper) {
                this.guiHelper = guiHelper;
                bg = guiHelper.createDrawable(TEXTURE, 0, 0, 176, 80);
        }

        @Override
        public RecipeType<SimpleRecipe> getRecipeType() {
                return REGULAR;
        }

        @Override
        public Component getTitle() {
                return Component.translatable("screen.simplestationsmanor.farm_recipes");
        }

        @Override
        public @Nullable IDrawable getIcon() {
                return guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
                                new ItemStack(ModBlocks.FARMER_BLOCK_ITEM.get()));
        }

        @Override
        public void setRecipe(IRecipeLayoutBuilder builder, SimpleRecipe recipe, IFocusGroup focuses) {
                builder.addSlot(RecipeIngredientRole.INPUT, UIBlocks.RED_SLOT.left, UIBlocks.RED_SLOT.top)
                                .addIngredients(Ingredient.of(Items.REDSTONE));
                builder.addSlot(RecipeIngredientRole.INPUT, UIBlocks.FERTI_SLOT.left, UIBlocks.FERTI_SLOT.top)
                                .addIngredients(Ingredient.of(Items.BONE_MEAL));
                builder.addSlot(RecipeIngredientRole.INPUT, UIBlocks.WATER_SLOT.left, UIBlocks.WATER_SLOT.top)
                                .addIngredients(Ingredient.of(Items.WATER_BUCKET));
                builder.addSlot(RecipeIngredientRole.OUTPUT, UIBlocks.OUT_SLOT.left, UIBlocks.OUT_SLOT.top)
                                .addItemStack(recipe.outputType);
                builder.addSlot(RecipeIngredientRole.CATALYST, UIBlocks.FILTER_SLOT.left, UIBlocks.FILTER_SLOT.top)
                                .addIngredients(Ingredient.of(recipe.filter.getItem()));
        }

        @Override
        public IDrawable getBackground() {
                return bg;
        }

        @Override
        public List<Component> getTooltipStrings(SimpleRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX,
                        double mouseY) {
                List<Component> list = Lists.newArrayList();
                if (UIBlocks.WATER_BAR.isHovered(mouseX, mouseY))
                        list.add(Component.literal(ExcavatorBlockEntity.WaterUsage + " mB"));

                return list;
        }
}
