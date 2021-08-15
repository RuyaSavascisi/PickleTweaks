package com.blakebr0.pickletweaks.feature.handler;

import com.blakebr0.pickletweaks.feature.item.NightVisionGogglesItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public final class NightVisionGogglesHandler {
    public static final List<String> WEARERS = new ArrayList<>();

    public static String getPlayerKey(Player player) {
        return player.getGameProfile().getName() + ":" + player.getCommandSenderWorld().isClientSide;
    }

    public static boolean hasGoggles(Player entity) {
        var stack = entity.getItemBySlot(EquipmentSlot.HEAD);

        return !stack.isEmpty() && stack.getItem() instanceof NightVisionGogglesItem;
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        var entity = event.getEntityLiving();

        if (entity instanceof Player player) {
            var key = getPlayerKey(player);
            var hasGoggles = hasGoggles(player);

            if (WEARERS.contains(key)) {
                if (hasGoggles) {
                    player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 246, 0, true, false));
                } else {
                    player.removeEffectNoUpdate(MobEffects.NIGHT_VISION);
                    WEARERS.remove(key);
                }
            } else if (hasGoggles) {
                WEARERS.add(key);
            }
        }
    }
}
