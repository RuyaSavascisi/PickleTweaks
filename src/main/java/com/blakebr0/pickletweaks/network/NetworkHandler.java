package com.blakebr0.pickletweaks.network;

import com.blakebr0.pickletweaks.network.payload.ToggleMagnetPayload;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

public class NetworkHandler {
    @SubscribeEvent
    public void onRegisterPayloadHandlers(RegisterPayloadHandlersEvent event) {
        var registrar = event.registrar("1");

        registrar.playToServer(ToggleMagnetPayload.TYPE, ToggleMagnetPayload.STREAM_CODEC, ToggleMagnetPayload::handleServer);
    }
}
