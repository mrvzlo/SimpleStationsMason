package com.ave.simplestationsmason.datagen;

import java.util.concurrent.CompletableFuture;

import com.ave.simplestationsmason.registrations.Registrations;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

public class ModRecipeProvider extends RecipeProvider {
        public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
                super(output, registries);
        }

        @Override
        protected void buildRecipes(RecipeOutput consumer) {
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registrations.BUCKET.get())
                                .pattern("A A")
                                .pattern(" A ")
                                .define('A', ItemTags.STONE_CRAFTING_MATERIALS)
                                .unlockedBy("has_c", has(Items.COBBLESTONE))
                                .save(consumer);

                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registrations.WHEEL.get())
                                .pattern("AAA")
                                .pattern("A A")
                                .pattern("AAA")
                                .define('A', Registrations.BUCKET.get())
                                .unlockedBy("has_c", has(Items.COBBLESTONE))
                                .save(consumer);

                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registrations.EXCAVATOR.getBlock())
                                .pattern("CCC")
                                .pattern("WRW")
                                .pattern("HSH")
                                .define('C', ItemTags.STONE_CRAFTING_MATERIALS)
                                .define('W', Registrations.WHEEL.get())
                                .define('S', Items.REDSTONE)
                                .define('R', Items.POWERED_RAIL)
                                .define('H', Items.HOPPER)
                                .unlockedBy("has_h", has(Items.HOPPER))
                                .save(consumer);

                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registrations.MIXER.getBlock())
                                .pattern("HBH")
                                .pattern("RCR")
                                .pattern("WRW")
                                .define('B', Registrations.BUCKET.get())
                                .define('R', Items.REDSTONE)
                                .define('C', Items.CAULDRON)
                                .define('W', ItemTags.PLANKS)
                                .define('H', Items.HOPPER)
                                .unlockedBy("has_h", has(Items.HOPPER))
                                .save(consumer);

                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registrations.FURNACE.getBlock())
                                .pattern("WPH")
                                .pattern("FFF")
                                .pattern("HPW")
                                .define('F', Items.FURNACE)
                                .define('P', Items.POWERED_RAIL)
                                .define('W', ItemTags.PLANKS)
                                .define('H', Items.HOPPER)
                                .unlockedBy("has_h", has(Items.HOPPER))
                                .save(consumer);

                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registrations.COIN.get())
                                .pattern("GEG")
                                .pattern("ERE")
                                .pattern("GEG")
                                .define('G', Items.GOLD_INGOT)
                                .define('E', Items.EMERALD)
                                .define('R', Items.RABBIT_FOOT)
                                .unlockedBy("has_r", has(Items.RABBIT_FOOT))
                                .save(consumer);

                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registrations.SIFTER.getBlock())
                                .pattern("HBH")
                                .pattern("RSR")
                                .pattern("WBW")
                                .define('S', Items.REDSTONE)
                                .define('H', Items.HOPPER)
                                .define('R', Items.POWERED_RAIL)
                                .define('B', Items.IRON_BARS)
                                .define('W', ItemTags.PLANKS)
                                .unlockedBy("has_r", has(Items.REDSTONE))
                                .save(consumer);
        }
}