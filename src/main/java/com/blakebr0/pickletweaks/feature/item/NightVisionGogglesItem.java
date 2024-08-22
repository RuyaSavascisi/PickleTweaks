package com.blakebr0.pickletweaks.feature.item;

import com.blakebr0.cucumber.item.BaseArmorItem;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorMaterial;

public class NightVisionGogglesItem extends BaseArmorItem {
	public NightVisionGogglesItem(Holder<ArmorMaterial> material, int maxDamageFactor) {
		super(material, Type.HELMET, maxDamageFactor);
	}
}
