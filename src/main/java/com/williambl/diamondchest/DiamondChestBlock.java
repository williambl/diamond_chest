package com.williambl.diamondchest;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.DoubleSidedInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;
import java.util.Random;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class DiamondChestBlock extends ChestBlock {
    private static final Random random = new Random();

    public final String name;
    private final boolean canBeDouble;

    // Why all this? Just so that it's not called 'double chest' but instead 'double diamond chest'.
    private final TileEntityMerger.ICallback<ChestTileEntity, Optional<INamedContainerProvider>> MENU_PROVIDER_COMBINER = new TileEntityMerger.ICallback<ChestTileEntity, Optional<INamedContainerProvider>>() {
        public Optional<INamedContainerProvider> acceptDouble(final ChestTileEntity chest1, final ChestTileEntity chest2) {
            final IInventory iinventory = new DoubleSidedInventory(chest1, chest2);
            return Optional.of(new INamedContainerProvider() {
                @Nullable
                public Container createMenu(int syncCode, PlayerInventory playerInv, PlayerEntity player) {
                    if (chest1.canOpen(player) && chest2.canOpen(player)) {
                        chest1.unpackLootTable(playerInv.player);
                        chest2.unpackLootTable(playerInv.player);
                        return ChestContainer.sixRows(syncCode, playerInv, iinventory);
                    } else {
                        return null;
                    }
                }

                public ITextComponent getDisplayName() {
                    if (chest1.hasCustomName()) {
                        return chest1.getDisplayName();
                    } else {
                        return chest2.hasCustomName() ? chest2.getDisplayName() : new TranslationTextComponent("container."+name+".double");
                    }
                }
            });
        }

        @Override
        public Optional<INamedContainerProvider> acceptSingle(ChestTileEntity chest) {
            return Optional.of(chest);
        }

        @Override
        public Optional<INamedContainerProvider> acceptNone() {
            return Optional.empty();
        }
    };


    public DiamondChestBlock(Properties properties, String name, boolean canBeDouble) {
        super(properties, DiamondChest.DIAMOND_CHEST_BLOCK_ENTITY_TYPE::get);
        this.name = name;
        this.canBeDouble = canBeDouble;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        DiamondChestBlockEntity blockEntity = new DiamondChestBlockEntity(name);
        blockEntity.setLootTable(new ResourceLocation("diamond_chest:chests/"+name), random.nextInt());
        return blockEntity;
    }

    @Nullable
    public INamedContainerProvider getMenuProvider(BlockState state, World world, BlockPos pos) {
        return this.combine(state, world, pos, false).apply(MENU_PROVIDER_COMBINER).orElse(null);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        if (canBeDouble) {
            return super.getStateForPlacement(p_196258_1_);
        } else {
            Direction direction = p_196258_1_.getHorizontalDirection().getOpposite();
            FluidState fluidstate = p_196258_1_.getLevel().getFluidState(p_196258_1_.getClickedPos());
            return this.defaultBlockState().setValue(FACING, direction).setValue(TYPE, ChestType.SINGLE).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
        }
    }
}
