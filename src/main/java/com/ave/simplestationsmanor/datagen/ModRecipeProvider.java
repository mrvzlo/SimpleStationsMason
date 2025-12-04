package com.ave.simplestationsmanor.datagen;

import java.util.concurrent.CompletableFuture;

import com.ave.simplestationsmanor.registrations.ModBlocks;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

public class ModRecipeProvider extends RecipeProvider {
        public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
                super(output, registries);
        }

        @Override
        protected void buildRecipes(RecipeOutput consumer) {
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SPRINKLER.get())
                                .pattern("RBR")
                                .pattern("HDH")
                                .pattern("RBR")
                                .define('R', Items.REDSTONE)
                                .define('H', Items.HOPPER)
                                .define('B', Items.BUCKET)
                                .define('D', Items.DISPENSER)
                                .unlockedBy("has_redstone", has(Items.REDSTONE))
                                .save(consumer);

                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.EXCAVATOR_BLOCK.get())
                                .pattern("DHD")
                                .pattern("LRL")
                                .pattern("DSD")
                                .define('R', ModBlocks.SPRINKLER.get())
                                .define('L', Items.LANTERN)
                                .define('D', Items.DIRT)
                                .define('H', Items.STONE_HOE)
                                .define('S', Items.STONE_SHOVEL)
                                .unlockedBy("has_redstone", has(Items.REDSTONE))
                                .save(consumer);
        }
}