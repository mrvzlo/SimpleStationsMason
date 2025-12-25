package com.ave.simplestationsmason.renderer;

import com.ave.simplestationsmason.blockentity.ExcavatorBlock;
import com.ave.simplestationsmason.blockentity.ExcavatorBlockEntity;
import com.ave.simplestationsmason.registrations.Registrations;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

public class ExcavatorRenderer implements BlockEntityRenderer<ExcavatorBlockEntity> {

    public ExcavatorRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(ExcavatorBlockEntity be, float pt, PoseStack pose, MultiBufferSource buf, int light,
            int overlay) {

        var itemRenderer = Minecraft.getInstance().getItemRenderer();
        var direction = be.getBlockState().getValue(ExcavatorBlock.FACING);
        var gameTime = be.getLevel().getGameTime();
        var wheelRotation = be.type < 0 ? 0 : ((gameTime) % 360);
        var wheel = be.type < 0 ? Registrations.WHEEL.get() : Registrations.EXCAVATABLE_BLOCKS[be.type].get();
        drawBlock(pose, itemRenderer, wheel, be, buf, -0.5f, 0.5f, direction, wheelRotation);
        drawBlock(pose, itemRenderer, wheel, be, buf, 1.5f, 0.5f, direction, wheelRotation);
    }

    private void drawBlock(PoseStack pose, ItemRenderer itemRenderer, Item item,
            ExcavatorBlockEntity be, MultiBufferSource buf, float sx, float sz, Direction direction,
            float rotate) {
        pose.pushPose();
        adjustWheelPosition(pose, sx, sz, direction);
        pose.scale(2, 2, 2);

        if (rotate > 0) {
            pose.mulPose(getRotationAxis(direction).rotationDegrees(rotate));
        }

        if (direction == Direction.NORTH || direction == Direction.SOUTH)
            pose.mulPose(Axis.YP.rotationDegrees(90));
        if (direction == Direction.WEST || direction == Direction.SOUTH)
            pose.mulPose(Axis.YP.rotationDegrees(180));

        int light = getLightLevel(be.getLevel(), be.getBlockPos());
        itemRenderer.renderStatic(new ItemStack(item), ItemDisplayContext.FIXED, light, OverlayTexture.NO_OVERLAY, pose,
                buf,
                be.getLevel(), 1);
        pose.popPose();
    }

    private Axis getRotationAxis(Direction direction) {
        switch (direction) {
            case Direction.WEST:
                return Axis.ZN;
            case Direction.EAST:
                return Axis.ZP;
            case Direction.NORTH:
                return Axis.XP;
            case Direction.SOUTH:
                return Axis.XN;
            default:
                return null;
        }
    }

    private void adjustWheelPosition(PoseStack pose, float sx, float sz, Direction direction) {
        if (direction == Direction.EAST || direction == Direction.WEST) {
            float temp = sx;
            sx = sz;
            sz = temp;
        }
        pose.translate(sx, 0.1f, sz);
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
