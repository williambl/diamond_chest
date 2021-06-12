package com.williambl.diamondchest;

import com.williambl.diamondchest.client.DiamondChestBlockEntityRenderer;
import com.williambl.diamondchest.client.DiamondChestItemStackRenderer;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("diamond_chest")
public class DiamondChest
{
    private static final Logger LOGGER = LogManager.getLogger();
    private static final DeferredRegister<Block> blockRegistry = DeferredRegister.create(ForgeRegistries.BLOCKS, "diamond_chest");
    private static final DeferredRegister<TileEntityType<?>> blockEntityTypeRegistry = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, "diamond_chest");
    private static final DeferredRegister<Item> itemRegistry = DeferredRegister.create(ForgeRegistries.ITEMS, "diamond_chest");

    public static final RegistryObject<Block> DIAMOND_CHEST = register("diamond_chest", Blocks.DIAMOND_BLOCK);
    public static final RegistryObject<Block> SUPER_OP_CHEST = register("super_op_chest", Blocks.DIAMOND_BLOCK);
    public static final RegistryObject<Block> NETHER_CHEST = register("nether_chest", Blocks.NETHER_BRICKS);
    public static final RegistryObject<Block> END_CHEST = register("end_chest", Blocks.END_STONE);
    public static final RegistryObject<Block> WOOD_CHEST = register("wood_chest", Blocks.OAK_WOOD);
    public static final RegistryObject<Block> IRON_CHEST = register("iron_chest", Blocks.IRON_BLOCK);

    @SuppressWarnings("ConstantConditions")
    public static final RegistryObject<TileEntityType<DiamondChestBlockEntity>> DIAMOND_CHEST_BLOCK_ENTITY_TYPE
            = blockEntityTypeRegistry.register("diamond_chest", () ->
            TileEntityType.Builder.of(DiamondChestBlockEntity::new, DIAMOND_CHEST.get(), SUPER_OP_CHEST.get(), NETHER_CHEST.get(), END_CHEST.get(), WOOD_CHEST.get(), IRON_CHEST.get()).build(null)
    );


    public DiamondChest() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::clientSetup);
        MinecraftForge.EVENT_BUS.register(this);
        blockRegistry.register(bus);
        blockEntityTypeRegistry.register(bus);
        itemRegistry.register(bus);
    }

    private void clientSetup(FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntityRenderer(DIAMOND_CHEST_BLOCK_ENTITY_TYPE.get(), DiamondChestBlockEntityRenderer::new);
    }

    private static RegistryObject<Block> register(String name, Block madeOf) {
        RegistryObject<Block> block
                = blockRegistry.register(name,
                () -> new DiamondChestBlock(AbstractBlock.Properties.copy(madeOf), name, name.equals("diamond_chest"))
        );

        RegistryObject<Item> item
                = itemRegistry.register(name,
                () -> new BlockItem(block.get(), new Item.Properties().tab(ItemGroup.TAB_DECORATIONS).setISTER(() -> DiamondChestItemStackRenderer::new))
        );
        return block;
    }
}
