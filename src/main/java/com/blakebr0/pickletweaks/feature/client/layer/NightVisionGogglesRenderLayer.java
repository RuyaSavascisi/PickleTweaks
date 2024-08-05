package com.blakebr0.pickletweaks.feature.client.layer;

import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.compat.curios.CuriosCompat;
import com.blakebr0.pickletweaks.init.ModItems;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

public class NightVisionGogglesRenderLayer<T extends LivingEntity, M extends HumanoidModel<T>> extends RenderLayer<T, M> {
    private static final ResourceLocation TEXTURE_BASIC = PickleTweaks.resource("textures/models/armor/night_vision_goggles_layer_1.png");
    private static final ResourceLocation TEXTURE_REINFORCED = PickleTweaks.resource("textures/models/armor/reinforced_night_vision_goggles_layer_1.png");

    public NightVisionGogglesRenderLayer(RenderLayerParent<T, M> parent) {
        super(parent);
    }

    @Override
    public void render(PoseStack matrix, MultiBufferSource buffer, int lightness, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        CuriosCompat.findNightVisionGogglesCurio(entity).ifPresent(curio -> {
            var model = IClientItemExtensions.of(curio).getHumanoidArmorModel(entity, curio, EquipmentSlot.CHEST, null);
            var texture = curio.is(ModItems.NIGHT_VISION_GOGGLES.get()) ? TEXTURE_BASIC : TEXTURE_REINFORCED;

            this.getParentModel().copyPropertiesTo((HumanoidModel<T>) model);
            this.renderModel(matrix, buffer, lightness, curio.hasFoil(), model, texture);
        });
    }

    private void renderModel(PoseStack matrix, MultiBufferSource buffer, int lightness, boolean foil, Model model, ResourceLocation armorResource) {
        var vertex = ItemRenderer.getArmorFoilBuffer(buffer, RenderType.armorCutoutNoCull(armorResource), foil);
        model.renderToBuffer(matrix, vertex, lightness, OverlayTexture.NO_OVERLAY, FastColor.ARGB32.color(255, 255, 255, 255));
    }
}
