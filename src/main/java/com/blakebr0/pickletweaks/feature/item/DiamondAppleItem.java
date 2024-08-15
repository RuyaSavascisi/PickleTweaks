package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.cucumber.lib.Tooltips;
import com.blakebr0.pickletweaks.lib.ModTooltips;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class DiamondAppleItem extends BaseItem {
	public static final FoodProperties FOOD = new FoodProperties.Builder().nutrition(6).saturationModifier(1.5F).alwaysEdible().build();

	public DiamondAppleItem() {
		super(p -> p.food(FOOD).rarity(Rarity.EPIC));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
		MobEffectInstance potion;
		int duration = 0;

		potion = entity.getEffect(MobEffects.REGENERATION);
		if (potion != null && potion.getAmplifier() >= 1)
			duration = potion.getDuration();
		entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, duration + 400, 1));

		potion = entity.getEffect(MobEffects.ABSORPTION);
		if (potion != null && potion.getAmplifier() >= 2)
			duration = potion.getDuration();
		entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, duration + 4800, 2));

		potion = entity.getEffect(MobEffects.FIRE_RESISTANCE);
		if (potion != null && potion.getAmplifier() >= 0)
			duration = potion.getDuration();
		entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, duration + 4800, 0));

		potion = entity.getEffect(MobEffects.DAMAGE_RESISTANCE);
		if (potion != null && potion.getAmplifier() >= 0)
			duration = potion.getDuration();
		entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, duration + 4800, 0));

		return entity.eat(level, stack);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		if (Screen.hasShiftDown()) {
			tooltip.add(ModTooltips.GIVES_BUFFS.build());
			tooltip.add(ModTooltips.createMobEffectLine(MobEffects.REGENERATION, "II", getDuration(400)));
			tooltip.add(ModTooltips.createMobEffectLine(MobEffects.ABSORPTION, "III", getDuration(4800)));
			tooltip.add(ModTooltips.createMobEffectLine(MobEffects.FIRE_RESISTANCE, "I", getDuration(4800)));
			tooltip.add(ModTooltips.createMobEffectLine(MobEffects.DAMAGE_RESISTANCE, "I", getDuration(4800)));
		} else {
			tooltip.add(Tooltips.HOLD_SHIFT_FOR_INFO.build());
		}
	}

	private static String getDuration(int ticks) {
		int duration = ticks / 20;
		var minutes = Math.floorDiv(duration, 60);
		var seconds = String.format("%02d", duration % 60);

		return minutes + ":" + seconds;
	}
}
