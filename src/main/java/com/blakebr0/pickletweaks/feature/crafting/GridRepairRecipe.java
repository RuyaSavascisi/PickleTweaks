package com.blakebr0.pickletweaks.feature.crafting;

import com.blakebr0.cucumber.helper.StackHelper;
import com.blakebr0.pickletweaks.config.ModConfigs;
import com.blakebr0.pickletweaks.init.ModRecipeSerializers;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;

public class GridRepairRecipe extends ShapelessRecipe {
	public GridRepairRecipe() {
		super("", CraftingBookCategory.EQUIPMENT, ItemStack.EMPTY, NonNullList.create());
	}

	@Override
	public ItemStack assemble(CraftingInput inventory, HolderLookup.Provider lookup) {
		if (!ModConfigs.GRID_REPAIR_ENABLED.get())
			return ItemStack.EMPTY;

		var tool = ItemStack.EMPTY;
		NonNullList<ItemStack> inputs = NonNullList.create();

		for (int i = 0; i < inventory.size(); i++) {
			var slotStack = inventory.getItem(i);

			if (slotStack.isEmpty())
				continue;

			if (tool.isEmpty() && slotStack.isDamageableItem()) {
				tool = slotStack;
			} else {
				inputs.add(slotStack);
			}
		}

		if (tool.isEmpty() || !tool.isDamaged() || inputs.isEmpty()) {
			return ItemStack.EMPTY;
		}

		if (GridRepairHelper.isBlacklisted(tool.getItem())) {
			return ItemStack.EMPTY;
		}

		int repairCost = ModConfigs.GRID_REPAIR_COST.get();
		int enchantmentCost = ModConfigs.GRID_REPAIR_ENCHANTMENT_COST.get();
		boolean cheaperShovel = ModConfigs.GRID_REPAIR_CHEAP_SHOVEL.get();
		boolean cheaperShears = ModConfigs.GRID_REPAIR_CHEAP_SHEARS.get();

		if ((cheaperShovel && tool.getItem() instanceof ShovelItem) || (cheaperShears && tool.getItem() instanceof ShearsItem)) {
			repairCost = Math.max(1, repairCost / 2);
			enchantmentCost = Math.max(0, enchantmentCost / 2);
		}

		int damage = tool.getMaxDamage() / (repairCost + (tool.isEnchanted() ? enchantmentCost : 0));

		double matCount = 0;
		boolean maxed = false;

		for (var mat : inputs) {
			if (maxed) return ItemStack.EMPTY;

			if (!mat.hasCraftingRemainingItem()) {
				double matValue = GridRepairHelper.getMaterialValue(tool, mat);
				if (matValue == 0) return ItemStack.EMPTY;

				matCount += matValue;

				if (tool.getDamageValue() - (damage * matCount) <= 0) {
					maxed = true;
				}
			} else {
				return ItemStack.EMPTY;
			}
		}

		tool = StackHelper.withSize(tool, 1, false);

		tool.setDamageValue(tool.getDamageValue() - (int) (damage * matCount));

		if (ModConfigs.GRID_REPAIR_STRIP_ENCHANTMENTS.get()) {
			var enchantments = new ItemEnchantments.Mutable(tool.getTagEnchantments());

			enchantments.removeIf(e -> !isCurse(e.value()));

			EnchantmentHelper.setEnchantments(tool, enchantments.toImmutable());
		}

		return tool;
	}

	@Override
	public boolean matches(CraftingInput inventory, Level level) {
		return !this.assemble(inventory, level.registryAccess()).isEmpty();
	}

	@Override
	public ItemStack getResultItem(HolderLookup.Provider lookup) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean isSpecial() {
		return true;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return ModRecipeSerializers.CRAFTING_GRID_REPAIR.get();
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(CraftingInput inventory) {
		return NonNullList.withSize(inventory.size(), ItemStack.EMPTY);
	}

	private static boolean isCurse(Enchantment enchantment) {
		return enchantment.effects().has(EnchantmentEffectComponents.PREVENT_EQUIPMENT_DROP)
				|| enchantment.effects().has(EnchantmentEffectComponents.PREVENT_ARMOR_CHANGE);
	}

	public static class Serializer implements RecipeSerializer<GridRepairRecipe> {
		private static final GridRepairRecipe INSTANCE = new GridRepairRecipe();

		public static final MapCodec<GridRepairRecipe> CODEC = MapCodec.unit(INSTANCE).stable();
		public static final StreamCodec<RegistryFriendlyByteBuf, GridRepairRecipe> STREAM_CODEC = StreamCodec.of(
				GridRepairRecipe.Serializer::toNetwork, GridRepairRecipe.Serializer::fromNetwork
		);

		@Override
		public MapCodec<GridRepairRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, GridRepairRecipe> streamCodec() {
			return STREAM_CODEC;
		}

		private static GridRepairRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
			return new GridRepairRecipe();
		}

		private static void toNetwork(RegistryFriendlyByteBuf buffer, GridRepairRecipe recipe) { }
	}
}

