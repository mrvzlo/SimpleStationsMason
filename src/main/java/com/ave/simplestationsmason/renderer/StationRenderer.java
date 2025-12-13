package com.ave.simplestationsmason.renderer;

import com.ave.simplestationsmason.blockentity.BaseStationBlock;
import com.ave.simplestationsmason.blockentity.BaseStationBlockEntity;
import com.ave.simplestationsmason.registrations.ModBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.neoforged.neoforge.client.model.data.ModelData;

public class StationRenderer implements BlockEntityRenderer<BaseStationBlockEntity> {
    private final BakedModel[] excavatableModels = new BakedModel[ModBlocks.EXCAVATABLE.length];

    public StationRenderer(BlockEntityRendererProvider.Context context) {
        var shaper = Minecraft.getInstance().getBlockRenderer().getBlockModelShaper();
        for (var i = 0; i < ModBlocks.EXCAVATABLE.length; i++)
            excavatableModels[i] = shaper.getBlockModel(ModBlocks.EXCAVATABLE_BLOCKS[i].get().defaultBlockState());
    }

    @Override
    public void render(BaseStationBlockEntity be, float pt, PoseStack pose, MultiBufferSource buf, int light,
            int overlay) {

        if (be.type < 0)
            return;

        pose.pushPose();
        var dispatcher = Minecraft.getInstance().getBlockRenderer();

        BakedModel model;
        model = excavatableModels[be.type];

        Direction direction = be.getBlockState().getValue(BaseStationBlock.FACING);
        var rotation = getRotation(direction);
        if (rotation > 0) {
            pose.translate(0.5, 0.0, 0.5);
            pose.mulPose(Axis.YP.rotationDegrees(rotation));
            pose.translate(-0.5, 0.0, -0.5);
        }

        dispatcher.getModelRenderer().renderModel(pose.last(), buf.getBuffer(RenderType.cutout()), null,
                model, 1f, 1f, 1f, light, overlay, ModelData.EMPTY, null);
        pose.popPose();
    }

    private int getRotation(Direction dir) {
        switch (dir) {
            case Direction.EAST:
                return 270;
            case Direction.SOUTH:
                return 180;
            case Direction.WEST:
                return 90;
            default:
                return 0;
        }
    }
}
