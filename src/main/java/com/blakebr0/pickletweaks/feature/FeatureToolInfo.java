package com.blakebr0.pickletweaks.feature;

import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.lib.ModTooltips;
import com.blakebr0.pickletweaks.tweaks.TweakToolUselessifier;
import com.blakebr0.pickletweaks.util.BlacklistUtils;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

public final class FeatureToolInfo {
	@SubscribeEvent
	public void onItemTooltip(ItemTooltipEvent event) {
		if (!ModConfigs.ENABLE_TOOL_INFO_TOOLTIP.get())
			return;

		var tooltip = event.getToolTip().listIterator();
		var item = event.getItemStack().getItem();

		if (item instanceof DiggerItem tool) {
			if (isBlacklisted(item))
				return;

			tooltip.next();

			tooltip.add(ModTooltips.MINING_SPEED.args(getMiningSpeed(tool)).build());
		}
	}

	private static float getMiningSpeed(DiggerItem item) {
		if (TweakToolUselessifier.isUselessTool(item))
			return 0F;

		return item.getTier().getSpeed();
	}

	private static boolean isBlacklisted(Item item) {
		return BlacklistUtils.contains(item, ModConfigs.TOOL_INFO_TOOLTIP_BLACKLIST.get());
	}
}
