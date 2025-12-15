package com.ave.simplestationsmason.datagen;

import java.util.concurrent.CompletableFuture;

import com.ave.simplestationsmason.registrations.ModBlocks;
import com.ave.simplestationsmason.registrations.VanillaBlocks;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;

public class ModRecipeProvider extends RecipeProvider {
        public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
                super(output, registries);
        }

        @Override
        protected void buildRecipes(RecipeOutput consumer) {
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BUCKET.get())
                                .pattern("A A")
                                .pattern(" A ")
                                .define('A', ItemTags.STONE_CRAFTING_MATERIALS)
                                .unlockedBy("has_c", has(Items.COBBLESTONE))
                                .save(consumer);

                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.WHEEL.get())
                                .pattern("AAA")
                                .pattern("A A")
                                .pattern("AAA")
                                .define('A', ModBlocks.BUCKET.get())
                                .unlockedBy("has_c", has(Items.COBBLESTONE))
                                .save(consumer);

                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.EXCAVATOR_BLOCK.get())
                                .pattern("CCC")
                                .pattern("WRW")
                                .pattern("HSH")
                                .define('C', ItemTags.STONE_CRAFTING_MATERIALS)
                                .define('W', ModBlocks.WHEEL.get())
                                .define('S', Items.REDSTONE)
                                .define('R', Items.POWERED_RAIL)
                                .define('H', Items.HOPPER)
                                .unlockedBy("has_h", has(Items.HOPPER))
                                .save(consumer);

                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MIXER_BLOCK.get())
                                .pattern("HBH")
                                .pattern("RBR")
                                .pattern("WRW")
                                .define('B', ModBlocks.BUCKET.get())
                                .define('R', Items.REDSTONE)
                                .define('W', ItemTags.PLANKS)
                                .define('H', Items.HOPPER)
                                .unlockedBy("has_h", has(Items.HOPPER))
                                .save(consumer);

                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.KILN_BLOCK.get())
                                .pattern("WPH")
                                .pattern("FFF")
                                .pattern("HPW")
                                .define('F', Items.FURNACE)
                                .define('P', Items.POWERED_RAIL)
                                .define('W', ItemTags.PLANKS)
                                .define('H', Items.HOPPER)
                                .unlockedBy("has_h", has(Items.HOPPER))
                                .save(consumer);

                for (var i = 0; i < ModBlocks.COLOR_NAMES.length; i++) {
                        var dye = VanillaBlocks.COLOR_DYES[i];
                        var dust = ModBlocks.COLOR_DUST_ITEMS[i].get();
                        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, dust, 9)
                                        .requires(dye)
                                        .unlockedBy("has_dye", has(dye)).save(consumer);

                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, dye)
                                        .pattern("AAA").pattern("AAA").pattern("AAA").define('A', dust)
                                        .unlockedBy("has_dye", has(dye)).save(consumer);

                        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VanillaBlocks.GLASSES[i])
                                        .requires(dust)
                                        .requires(Tags.Items.GLASS_BLOCKS_CHEAP)
                                        .unlockedBy("has_dust", has(dust)).save(consumer);

                        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VanillaBlocks.GLASS_PANES[i])
                                        .requires(dust)
                                        .requires(Tags.Items.GLASS_PANES)
                                        .unlockedBy("has_dust", has(dust)).save(consumer);

                        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VanillaBlocks.TERRACOTA[i])
                                        .requires(dust)
                                        .requires(ItemTags.TERRACOTTA)
                                        .unlockedBy("has_dust", has(dust)).save(consumer);
                }
        }
}