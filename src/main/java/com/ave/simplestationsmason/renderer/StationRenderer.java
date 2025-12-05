package com.ave.simplestationsmason.renderer;

import com.ave.simplestationsmason.blockentity.enums.CropGroup;
import com.ave.simplestationsmason.blockentity.enums.CropType;
import com.ave.simplestationsmason.blockentity.partblock.PartBlockEntity;
import com.ave.simplestationsmason.registrations.ModBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.neoforged.neoforge.client.model.data.ModelData;

public class StationRenderer implements BlockEntityRenderer<PartBlockEntity> {
    private final BakedModel[] cropModels = new BakedModel[CropType.values().length];

    public StationRenderer(BlockEntityRendererProvider.Context context) {
        var shaper = Minecraft.getInstance().getBlockRenderer().getBlockModelShaper();
        for (var i = 0; i < CropType.values().length; i++)
            cropModels[i] = shaper.getBlockModel(ModBlocks.CROP_BLOCKS[i].get().defaultBlockState());
    }

    @Override
    public void render(PartBlockEntity be, float pt, PoseStack pose, MultiBufferSource buf, int light,
            int overlay) {

        var type = be.getCropType();
        if (type == null || type == CropType.Unknown)
            return;

        pose.pushPose();
        var dispatcher = Minecraft.getInstance().getBlockRenderer();

        BakedModel model;
        model = cropModels[type.ordinal()];

        dispatcher.getModelRenderer().renderModel(pose.last(), buf.getBuffer(RenderType.cutout()), null,
                model, 1f, 1f, 1f, light, overlay, ModelData.EMPTY, null);
        pose.popPose();
    }

}
