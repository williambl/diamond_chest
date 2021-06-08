package com.williambl.diamondchest.client;

import net.minecraft.client.renderer.Atlases;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = "diamond_chest", bus = Mod.EventBusSubscriber.Bus.MOD)
public class DiamondChestClient {
    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
        if (!event.getMap().location().equals(Atlases.CHEST_SHEET)) return;

        event.addSprite(DiamondChestBlockEntityRenderer.SINGLE_MATERIAL.texture());
        event.addSprite(DiamondChestBlockEntityRenderer.DOUBLE_LEFT_MATERIAL.texture());
        event.addSprite(DiamondChestBlockEntityRenderer.DOUBLE_RIGHT_MATERIAL.texture());
    }
}
