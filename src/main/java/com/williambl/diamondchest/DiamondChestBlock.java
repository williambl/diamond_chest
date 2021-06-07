package com.williambl.diamondchest;

import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class DiamondChestBlock extends ChestBlock {
    public DiamondChestBlock(Properties properties) {
        super(properties, DiamondChest.DIAMOND_CHEST_BLOCK_ENTITY_TYPE::get);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new DiamondChestBlockEntity();
    }
}
