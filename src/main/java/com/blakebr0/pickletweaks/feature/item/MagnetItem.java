package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.init.ModDataComponentTypes;
import com.blakebr0.pickletweaks.lib.ModTooltips;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class MagnetItem extends BaseItem {
	public MagnetItem() {
		super(p -> p
				.stacksTo(1)
				.component(ModDataComponentTypes.MAGNET_ACTIVE, false)
		);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		var stack = player.getItemInHand(hand);
		var enabled = stack.getOrDefault(ModDataComponentTypes.MAGNET_ACTIVE, false);

		player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 0.5F, enabled ? 0.5F : 1.0F);
		stack.set(ModDataComponentTypes.MAGNET_ACTIVE, !enabled);

		return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return stack.getOrDefault(ModDataComponentTypes.MAGNET_ACTIVE, false);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag advanced) {
		if (stack.getOrDefault(ModDataComponentTypes.MAGNET_ACTIVE, false)) {
			tooltip.add(ModTooltips.ENABLED.build());
		} else {
			tooltip.add(ModTooltips.DISABLED.build());
		}
	}

	@Override
	public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean isSelected) {
		var enabled = stack.getOrDefault(ModDataComponentTypes.MAGNET_ACTIVE, false);
		if (entity instanceof Player && enabled) {
			double range = ModConfigs.MAGNET_RANGE.get();
			var items = level.getEntitiesOfClass(ItemEntity.class, entity.getBoundingBox().inflate(range));

			for (var item : items) {
				if (!item.isAlive() || item.getPersistentData().contains("PreventRemoteMovement"))
					continue;

				var thrower = item.getOwner();
				if (thrower != null && thrower.getUUID().equals(entity.getUUID()) && item.hasPickUpDelay())
					continue;

				if (!level.isClientSide()) {
					item.setNoPickUpDelay();
					item.setPos(entity.getX(), entity.getY(), entity.getZ());
				}
			}

			var orbs = level.getEntitiesOfClass(ExperienceOrb.class, entity.getBoundingBox().inflate(range));

			for (var orb : orbs) {
				if (!level.isClientSide()) {
					orb.setPos(entity.getX(), entity.getY(), entity.getZ());
				}
			}
		}
	}
}
