package com.blakebr0.pickletweaks.feature.client.extensions;

import com.blakebr0.pickletweaks.feature.client.ModelHandler;
import com.blakebr0.pickletweaks.feature.client.model.NightVisionGogglesModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

public class NightVisionGogglesClientExtensions implements IClientItemExtensions {
    public static final NightVisionGogglesClientExtensions INSTANCE = new NightVisionGogglesClientExtensions();

    private NightVisionGogglesModel model;

    @Override
    public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> _default) {
        if (this.model == null) {
            var layer = Minecraft.getInstance().getEntityModels().bakeLayer(ModelHandler.NIGHT_VISION_GOGGLES_LAYER);

            this.model = new NightVisionGogglesModel(layer);
        }

        return this.model;
    }
}
