package com.blakebr0.pickletweaks.init;

import com.blakebr0.pickletweaks.PickleTweaks;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModDataComponents {
    public static final DeferredRegister<DataComponentType<?>> REGISTRY = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, PickleTweaks.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> MAGNET_ACTIVE = REGISTRY.register("magnet_active", () -> DataComponentType.<Boolean>builder().persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL).build());
}
