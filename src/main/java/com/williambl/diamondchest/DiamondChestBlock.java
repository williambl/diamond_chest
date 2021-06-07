package com.williambl.diamondchest;

import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;
import java.util.Random;

public class DiamondChestBlock extends ChestBlock {
    private static Random random = new Random();

    public DiamondChestBlock(Properties properties) {
        super(properties, DiamondChest.DIAMOND_CHEST_BLOCK_ENTITY_TYPE::get);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        DiamondChestBlockEntity blockEntity = new DiamondChestBlockEntity();
        blockEntity.setLootTable(new ResourceLocation("diamond_chest:chests/diamond_chest"), random.nextInt());
        return blockEntity;
    }
}
