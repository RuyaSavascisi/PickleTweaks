package com.blakebr0.pickletweaks.handler;

import com.blakebr0.pickletweaks.compat.curios.CuriosCompat;
import com.blakebr0.pickletweaks.config.ModConfigs;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public final class RegisterCapabilityHandler {
    @SubscribeEvent
    public void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        if (ModConfigs.isCuriosInstalled()) {
            CuriosCompat.registerCapabilities(event);
        }
    }
}
