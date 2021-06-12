package com.williambl.diamondchest;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

@MethodsReturnNonnullByDefault
public class DiamondChestBlockEntity extends ChestTileEntity {
    public String name;

    public DiamondChestBlockEntity() {
        super(DiamondChest.DIAMOND_CHEST_BLOCK_ENTITY_TYPE.get());
        this.name = "diamond_chest";
    }

    public DiamondChestBlockEntity(String name) {
        super(DiamondChest.DIAMOND_CHEST_BLOCK_ENTITY_TYPE.get());
        this.name = name;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT tag = super.serializeNBT();
        tag.putString("ChestTypeName", name);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        super.deserializeNBT(nbt);
        name = getUpdateTag().getString("ChestTypeName");
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container."+name);
    }
}
