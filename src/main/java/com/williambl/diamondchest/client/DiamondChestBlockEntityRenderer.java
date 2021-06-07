package com.williambl.diamondchest.client;

import com.williambl.diamondchest.DiamondChestBlockEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.tileentity.ChestTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.ChestType;
import net.minecraft.util.ResourceLocation;

@MethodsReturnNonnullByDefault
public class DiamondChestBlockEntityRenderer extends ChestTileEntityRenderer<DiamondChestBlockEntity> {
    public static final RenderMaterial SINGLE_MATERIAL = chestMaterial("single");
    public static final RenderMaterial DOUBLE_LEFT_MATERIAL = chestMaterial("double_left");
    public static final RenderMaterial DOUBLE_RIGHT_MATERIAL = chestMaterial("double_right");

    public DiamondChestBlockEntityRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    protected RenderMaterial getMaterial(DiamondChestBlockEntity p_getMaterial_1_, ChestType p_getMaterial_2_) {
        switch(p_getMaterial_2_) {
            case LEFT:
                return DOUBLE_LEFT_MATERIAL;
            case RIGHT:
                return DOUBLE_RIGHT_MATERIAL;
            case SINGLE:
            default:
                return SINGLE_MATERIAL;
        }
    }

    private static RenderMaterial chestMaterial(String name) {
        return new RenderMaterial(Atlases.CHEST_SHEET, new ResourceLocation("diamond_chest:entity/diamond_chest/"+name));
    }
}
