package com.blakebr0.pickletweaks.feature.client.handler;

import com.blakebr0.cucumber.iface.IColored;
import com.blakebr0.pickletweaks.init.ModBlocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

public final class ColorHandler {
    @SubscribeEvent
    public void onBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register(new IColored.BlockColors(),
                ModBlocks.WHITE_COBBLESTONE.get(),
                ModBlocks.ORANGE_COBBLESTONE.get(),
                ModBlocks.MAGENTA_COBBLESTONE.get(),
                ModBlocks.LIGHT_BLUE_COBBLESTONE.get(),
                ModBlocks.YELLOW_COBBLESTONE.get(),
                ModBlocks.LIME_COBBLESTONE.get(),
                ModBlocks.PINK_COBBLESTONE.get(),
                ModBlocks.GRAY_COBBLESTONE.get(),
                ModBlocks.LIGHT_GRAY_COBBLESTONE.get(),
                ModBlocks.CYAN_COBBLESTONE.get(),
                ModBlocks.PURPLE_COBBLESTONE.get(),
                ModBlocks.BLUE_COBBLESTONE.get(),
                ModBlocks.BROWN_COBBLESTONE.get(),
                ModBlocks.GREEN_COBBLESTONE.get(),
                ModBlocks.RED_COBBLESTONE.get(),
                ModBlocks.BLACK_COBBLESTONE.get()
        );
    }

    @SubscribeEvent
    public void onItemColors(RegisterColorHandlersEvent.Item event) {
        event.register(new IColored.ItemBlockColors(),
                ModBlocks.WHITE_COBBLESTONE.get(),
                ModBlocks.ORANGE_COBBLESTONE.get(),
                ModBlocks.MAGENTA_COBBLESTONE.get(),
                ModBlocks.LIGHT_BLUE_COBBLESTONE.get(),
                ModBlocks.YELLOW_COBBLESTONE.get(),
                ModBlocks.LIME_COBBLESTONE.get(),
                ModBlocks.PINK_COBBLESTONE.get(),
                ModBlocks.GRAY_COBBLESTONE.get(),
                ModBlocks.LIGHT_GRAY_COBBLESTONE.get(),
                ModBlocks.CYAN_COBBLESTONE.get(),
                ModBlocks.PURPLE_COBBLESTONE.get(),
                ModBlocks.BLUE_COBBLESTONE.get(),
                ModBlocks.BROWN_COBBLESTONE.get(),
                ModBlocks.GREEN_COBBLESTONE.get(),
                ModBlocks.RED_COBBLESTONE.get(),
                ModBlocks.BLACK_COBBLESTONE.get()
        );
    }
}
