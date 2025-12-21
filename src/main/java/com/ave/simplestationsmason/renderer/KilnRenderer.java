package com.ave.simplestationsmason.renderer;

import com.ave.simplestationsmason.blockentity.BaseStationBlock;
import com.ave.simplestationsmason.blockentity.KilnBlockEntity;
import com.ave.simplestationsmason.blockentity.enums.KilnType;
import com.ave.simplestationsmason.registrations.ModBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class KilnRenderer implements BlockEntityRenderer<KilnBlockEntity> {

    public KilnRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(KilnBlockEntity be, float pt, PoseStack pose, MultiBufferSource buf, int light, int overlay) {
        if (be.type < 0)
            return;

        pose.pushPose();
        var itemRenderer = Minecraft.getInstance().getItemRenderer();
        var direction = be.getBlockState().getValue(BaseStationBlock.FACING);

        var rotation = getRotation(direction);
        pose.translate(0.5, 0.5, 0.5);
        if (rotation > 0)
            pose.mulPose(Axis.YP.rotationDegrees(rotation));

        var item = getItem(be.type);
        itemRenderer.renderStatic(new ItemStack(item), ItemDisplayContext.FIXED, light, OverlayTexture.NO_OVERLAY, pose,
                buf, be.getLevel(), 1);
        pose.popPose();
    }

    private Item getItem(int type) {
        if (type == KilnType.Glass.ordinal() || type == KilnType.Glass2.ordinal())
            return ModBlocks.BLASTING_BLOCKS[1].asItem();
        if (type == KilnType.Bricks.ordinal())
            return ModBlocks.BLASTING_BLOCKS[0].asItem();
        if (type == KilnType.Terracota.ordinal())
            return ModBlocks.BLASTING_BLOCKS[3].asItem();
        if (type == KilnType.NetherBricks.ordinal())
            return ModBlocks.BLASTING_BLOCKS[4].asItem();
        if (type == KilnType.Stone.ordinal())
            return ModBlocks.BLASTING_BLOCKS[5].asItem();
        return ModBlocks.BLASTING_BLOCKS[2].asItem();
    }

    private int getRotation(Direction dir) {
        switch (dir) {
            case Direction.WEST:
                return 270;
            case Direction.NORTH:
                return 180;
            case Direction.EAST:
                return 90;
            default:
                return 0;
        }
    }
}
