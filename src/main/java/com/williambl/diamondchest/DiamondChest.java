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

    public static final RegistryObject<Block> DIAMOND_CHEST_BLOCK
            = blockRegistry.register("diamond_chest",
            () -> new DiamondChestBlock(AbstractBlock.Properties.copy(Blocks.DIAMOND_BLOCK))
    );

    @SuppressWarnings("ConstantConditions")
    public static final RegistryObject<TileEntityType<DiamondChestBlockEntity>> DIAMOND_CHEST_BLOCK_ENTITY_TYPE
            = blockEntityTypeRegistry.register("diamond_chest", () ->
            TileEntityType.Builder.of(DiamondChestBlockEntity::new, DIAMOND_CHEST_BLOCK.get()).build(null)
    );

    public static final RegistryObject<Item> DIAMOND_CHEST_ITEM
            = itemRegistry.register("diamond_chest",
            () -> new BlockItem(DIAMOND_CHEST_BLOCK.get(), new Item.Properties().tab(ItemGroup.TAB_DECORATIONS).setISTER(() -> DiamondChestItemStackRenderer::new))
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
}
