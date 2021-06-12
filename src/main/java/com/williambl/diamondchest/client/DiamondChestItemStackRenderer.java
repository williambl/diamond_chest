package com.williambl.diamondchest.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.williambl.diamondchest.DiamondChestBlockEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

@MethodsReturnNonnullByDefault
public class DiamondChestItemStackRenderer extends ItemStackTileEntityRenderer {
    private final Map<String, DiamondChestBlockEntity> chests = new HashMap<>();

    @Override
    public void renderByItem(ItemStack p_239207_1_, ItemCameraTransforms.TransformType p_239207_2_, MatrixStack matrixStack, IRenderTypeBuffer buffer, int light, int overlay) {
        TileEntityRendererDispatcher.instance.renderItem(chests.computeIfAbsent(p_239207_1_.getItem().getRegistryName().getPath(), DiamondChestBlockEntity::new), matrixStack, buffer, light, overlay);
    }
}
