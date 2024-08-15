package com.blakebr0.pickletweaks.network.payload;

import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.feature.item.MagnetItem;
import com.blakebr0.pickletweaks.init.ModDataComponentTypes;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record ToggleMagnetPayload(int slot) implements CustomPacketPayload {
    public static final Type<ToggleMagnetPayload> TYPE = new Type<>(PickleTweaks.resource("toggle_magnet"));

    public static final StreamCodec<ByteBuf, ToggleMagnetPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            ToggleMagnetPayload::slot,
            ToggleMagnetPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handleServer(ToggleMagnetPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            var player = context.player();
            var stack = player.containerMenu.slots.get(payload.slot).getItem();
            var item = stack.getItem();

            if (item instanceof MagnetItem) {
                var enabled = stack.getOrDefault(ModDataComponentTypes.MAGNET_ACTIVE, false);

                stack.set(ModDataComponentTypes.MAGNET_ACTIVE, !enabled);
            }
        });
    }
}
