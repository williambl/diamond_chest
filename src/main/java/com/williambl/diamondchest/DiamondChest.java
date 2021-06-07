package com.williambl.diamondchest;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;
import java.util.stream.Collectors;

@Mod("diamond_chest")
@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
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
            () -> new BlockItem(DIAMOND_CHEST_BLOCK.get(), new Item.Properties().tab(ItemGroup.TAB_DECORATIONS))
    );

    public DiamondChest() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        blockRegistry.register(bus);
        blockEntityTypeRegistry.register(bus);
        itemRegistry.register(bus);
    }
}
