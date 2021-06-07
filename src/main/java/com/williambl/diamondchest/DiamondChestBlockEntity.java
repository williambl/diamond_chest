package com.williambl.diamondchest;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

@MethodsReturnNonnullByDefault
public class DiamondChestBlockEntity extends ChestTileEntity {
    public DiamondChestBlockEntity() {
        super(DiamondChest.DIAMOND_CHEST_BLOCK_ENTITY_TYPE.get());
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.diamond_chest");
    }
}
