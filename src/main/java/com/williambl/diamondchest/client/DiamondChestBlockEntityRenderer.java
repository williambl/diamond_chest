package com.williambl.diamondchest.client;

import com.williambl.diamondchest.DiamondChest;
import com.williambl.diamondchest.DiamondChestBlockEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.tileentity.ChestTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.ChestType;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@MethodsReturnNonnullByDefault
public class DiamondChestBlockEntityRenderer extends ChestTileEntityRenderer<DiamondChestBlockEntity> {
    public static final Map<String, RenderMaterial> SINGLE_MATERIALS = Stream.of(DiamondChest.DIAMOND_CHEST, DiamondChest.IRON_CHEST, DiamondChest.END_CHEST, DiamondChest.WOOD_CHEST, DiamondChest.NETHER_CHEST, DiamondChest.SUPER_OP_CHEST)
            .collect(Collectors.toMap(it -> it.getId().getPath(), it -> chestMaterial(it.getId().getPath()+"/"+"single")));
    public static final RenderMaterial DOUBLE_LEFT_MATERIAL = chestMaterial("diamond_chest/double_left");
    public static final RenderMaterial DOUBLE_RIGHT_MATERIAL = chestMaterial("diamond_chest/double_right");

    public DiamondChestBlockEntityRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    protected RenderMaterial getMaterial(@Nonnull DiamondChestBlockEntity chest, ChestType type) {
        switch(type) {
            case LEFT:
                return DOUBLE_LEFT_MATERIAL;
            case RIGHT:
                return DOUBLE_RIGHT_MATERIAL;
            case SINGLE:
            default:
                return SINGLE_MATERIALS.get(chest.name);
        }
    }

    private static RenderMaterial chestMaterial(String name) {
        return new RenderMaterial(Atlases.CHEST_SHEET, new ResourceLocation("diamond_chest:entity/"+name));
    }
}
