package com.williambl.diamondchest.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.williambl.diamondchest.DiamondChestBlockEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;

@MethodsReturnNonnullByDefault
public class DiamondChestItemStackRenderer extends ItemStackTileEntityRenderer {
    private DiamondChestBlockEntity chest = null;

    @Override
    public void renderByItem(ItemStack p_239207_1_, ItemCameraTransforms.TransformType p_239207_2_, MatrixStack matrixStack, IRenderTypeBuffer buffer, int light, int overlay) {
        if (chest == null) {
            chest = new DiamondChestBlockEntity();
        }
        TileEntityRendererDispatcher.instance.renderItem(chest, matrixStack, buffer, light, overlay);
    }
}
