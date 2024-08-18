package com.blakebr0.pickletweaks.feature.client;

import com.blakebr0.cucumber.client.extensions.WateringCanClientItemExtensions;
import com.blakebr0.pickletweaks.feature.client.extensions.NightVisionGogglesClientExtensions;
import com.blakebr0.pickletweaks.init.ModItems;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

public final class ModClientExtensions {
    @SubscribeEvent
    public void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(NightVisionGogglesClientExtensions.INSTANCE, ModItems.NIGHT_VISION_GOGGLES.get(), ModItems.REINFORCED_NIGHT_VISION_GOGGLES.get());
        event.registerItem(WateringCanClientItemExtensions.INSTANCE, ModItems.WATERING_CAN.get(), ModItems.REINFORCED_WATERING_CAN.get());
    }
}
