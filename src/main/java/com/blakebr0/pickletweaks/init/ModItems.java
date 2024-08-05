package com.blakebr0.pickletweaks.init;

import com.blakebr0.cucumber.item.BaseArmorItem;
import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.cucumber.item.BaseReusableItem;
import com.blakebr0.cucumber.item.tool.BaseAxeItem;
import com.blakebr0.cucumber.item.tool.BaseHoeItem;
import com.blakebr0.cucumber.item.tool.BasePaxelItem;
import com.blakebr0.cucumber.item.tool.BasePickaxeItem;
import com.blakebr0.cucumber.item.tool.BaseScytheItem;
import com.blakebr0.cucumber.item.tool.BaseShearsItem;
import com.blakebr0.cucumber.item.tool.BaseShovelItem;
import com.blakebr0.cucumber.item.tool.BaseSickleItem;
import com.blakebr0.cucumber.item.tool.BaseSwordItem;
import com.blakebr0.pickletweaks.PickleTweaks;
import com.blakebr0.pickletweaks.feature.item.CoalPieceItem;
import com.blakebr0.pickletweaks.feature.item.DiamondAppleItem;
import com.blakebr0.pickletweaks.feature.item.EmeraldAppleItem;
import com.blakebr0.pickletweaks.feature.item.MagnetItem;
import com.blakebr0.pickletweaks.feature.item.NightVisionGogglesItem;
import com.blakebr0.pickletweaks.feature.item.WateringCanItem;
import com.blakebr0.pickletweaks.lib.ModItemTier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(Registries.ITEM, PickleTweaks.MOD_ID);

	// register block items here for class load order purposes
	static {
		ModBlocks.BLOCK_ITEMS.forEach(REGISTRY::register);
	}

	public static final DeferredHolder<Item, Item> COAL_PIECE = REGISTRY.register("coal_piece", CoalPieceItem::new);
	public static final DeferredHolder<Item, Item> CHARCOAL_PIECE = REGISTRY.register("charcoal_piece", CoalPieceItem::new);
	public static final DeferredHolder<Item, Item> DIAMOND_APPLE = REGISTRY.register("diamond_apple", DiamondAppleItem::new);
	public static final DeferredHolder<Item, Item> EMERALD_APPLE = REGISTRY.register("emerald_apple", EmeraldAppleItem::new);
	public static final DeferredHolder<Item, Item> WATERING_CAN = REGISTRY.register("watering_can", () -> new WateringCanItem(3, 0.25));
	public static final DeferredHolder<Item, Item> REINFORCED_WATERING_CAN = REGISTRY.register("reinforced_watering_can", () -> new WateringCanItem(7, 0.35));
	public static final DeferredHolder<Item, Item> GRASS_FIBER = REGISTRY.register("grass_fiber", () -> new BaseItem());
	public static final DeferredHolder<Item, Item> GRASS_FIBER_MESH = REGISTRY.register("grass_fiber_mesh", () -> new BaseReusableItem(20));
	public static final DeferredHolder<Item, Item> MESH = REGISTRY.register("mesh", () -> new BaseReusableItem(64));
	public static final DeferredHolder<Item, Item> REINFORCED_MESH = REGISTRY.register("reinforced_mesh", () -> new BaseReusableItem(512));
	public static final DeferredHolder<Item, Item> MAGNET = REGISTRY.register("magnet", MagnetItem::new);
	public static final DeferredHolder<Item, Item> NIGHT_VISION_GOGGLES = REGISTRY.register("night_vision_goggles", () -> new NightVisionGogglesItem(ModArmorMaterials.NIGHT_VISION_GOGGLES));
	public static final DeferredHolder<Item, Item> REINFORCED_NIGHT_VISION_GOGGLES = REGISTRY.register("reinforced_night_vision_goggles", () -> new NightVisionGogglesItem(ModArmorMaterials.REINFORCED_NIGHT_VISION_GOGGLES));

	public static final DeferredHolder<Item, Item> FLINT_SWORD = REGISTRY.register("flint_sword", () -> new BaseSwordItem(ModItemTier.FLINT));
	public static final DeferredHolder<Item, Item> FLINT_PICKAXE = REGISTRY.register("flint_pickaxe", () -> new BasePickaxeItem(ModItemTier.FLINT));
	public static final DeferredHolder<Item, Item> FLINT_SHOVEL = REGISTRY.register("flint_shovel", () -> new BaseShovelItem(ModItemTier.FLINT));
	public static final DeferredHolder<Item, Item> FLINT_AXE = REGISTRY.register("flint_axe", () -> new BaseAxeItem(ModItemTier.FLINT));
	public static final DeferredHolder<Item, Item> FLINT_HOE = REGISTRY.register("flint_hoe", () -> new BaseHoeItem(ModItemTier.FLINT));
	public static final DeferredHolder<Item, Item> FLINT_SHEARS = REGISTRY.register("flint_shears", () -> new BaseShearsItem(p -> p.durability(100)));

	public static final DeferredHolder<Item, Item> EMERALD_SWORD = REGISTRY.register("emerald_sword", () -> new BaseSwordItem(ModItemTier.EMERALD));
	public static final DeferredHolder<Item, Item> EMERALD_PICKAXE = REGISTRY.register("emerald_pickaxe", () -> new BasePickaxeItem(ModItemTier.EMERALD));
	public static final DeferredHolder<Item, Item> EMERALD_SHOVEL = REGISTRY.register("emerald_shovel", () -> new BaseShovelItem(ModItemTier.EMERALD));
	public static final DeferredHolder<Item, Item> EMERALD_AXE = REGISTRY.register("emerald_axe", () -> new BaseAxeItem(ModItemTier.EMERALD));
	public static final DeferredHolder<Item, Item> EMERALD_HOE = REGISTRY.register("emerald_hoe", () -> new BaseHoeItem(ModItemTier.EMERALD));

	public static final DeferredHolder<Item, Item> FLINT_HELMET = REGISTRY.register("flint_helmet", () -> new BaseArmorItem(ModArmorMaterials.FLINT, ArmorItem.Type.HELMET));
	public static final DeferredHolder<Item, Item> FLINT_CHESTPLATE = REGISTRY.register("flint_chestplate", () -> new BaseArmorItem(ModArmorMaterials.FLINT, ArmorItem.Type.CHESTPLATE));
	public static final DeferredHolder<Item, Item> FLINT_LEGGINGS = REGISTRY.register("flint_leggings", () -> new BaseArmorItem(ModArmorMaterials.FLINT, ArmorItem.Type.LEGGINGS));
	public static final DeferredHolder<Item, Item> FLINT_BOOTS = REGISTRY.register("flint_boots", () -> new BaseArmorItem(ModArmorMaterials.FLINT, ArmorItem.Type.BOOTS));
	public static final DeferredHolder<Item, Item> EMERALD_HELMET = REGISTRY.register("emerald_helmet", () -> new BaseArmorItem(ModArmorMaterials.EMERALD, ArmorItem.Type.HELMET));
	public static final DeferredHolder<Item, Item> EMERALD_CHESTPLATE = REGISTRY.register("emerald_chestplate", () -> new BaseArmorItem(ModArmorMaterials.EMERALD, ArmorItem.Type.CHESTPLATE));
	public static final DeferredHolder<Item, Item> EMERALD_LEGGINGS = REGISTRY.register("emerald_leggings", () -> new BaseArmorItem(ModArmorMaterials.EMERALD, ArmorItem.Type.LEGGINGS));
	public static final DeferredHolder<Item, Item> EMERALD_BOOTS = REGISTRY.register("emerald_boots", () -> new BaseArmorItem(ModArmorMaterials.EMERALD, ArmorItem.Type.BOOTS));

	public static final DeferredHolder<Item, Item> WOODEN_PAXEL = REGISTRY.register("wooden_paxel", () -> new BasePaxelItem(Tiers.WOOD));
	public static final DeferredHolder<Item, Item> STONE_PAXEL = REGISTRY.register("stone_paxel", () -> new BasePaxelItem(Tiers.STONE));
	public static final DeferredHolder<Item, Item> IRON_PAXEL = REGISTRY.register("iron_paxel", () -> new BasePaxelItem(Tiers.IRON));
	public static final DeferredHolder<Item, Item> GOLDEN_PAXEL = REGISTRY.register("golden_paxel", () -> new BasePaxelItem(Tiers.GOLD));
	public static final DeferredHolder<Item, Item> DIAMOND_PAXEL = REGISTRY.register("diamond_paxel", () -> new BasePaxelItem(Tiers.DIAMOND));
	public static final DeferredHolder<Item, Item> FLINT_PAXEL = REGISTRY.register("flint_paxel", () -> new BasePaxelItem(ModItemTier.FLINT));
	public static final DeferredHolder<Item, Item> EMERALD_PAXEL = REGISTRY.register("emerald_paxel", () -> new BasePaxelItem(ModItemTier.EMERALD));
	public static final DeferredHolder<Item, Item> NETHERITE_PAXEL = REGISTRY.register("netherite_paxel", () -> new BasePaxelItem(Tiers.NETHERITE));

	public static final DeferredHolder<Item, Item> WOODEN_SICKLE = REGISTRY.register("wooden_sickle", () -> new BaseSickleItem(Tiers.WOOD, 1));
	public static final DeferredHolder<Item, Item> STONE_SICKLE = REGISTRY.register("stone_sickle", () -> new BaseSickleItem(Tiers.STONE, 1));
	public static final DeferredHolder<Item, Item> IRON_SICKLE = REGISTRY.register("iron_sickle", () -> new BaseSickleItem(Tiers.IRON, 2));
	public static final DeferredHolder<Item, Item> GOLDEN_SICKLE = REGISTRY.register("golden_sickle", () -> new BaseSickleItem(Tiers.GOLD, 2));
	public static final DeferredHolder<Item, Item> DIAMOND_SICKLE = REGISTRY.register("diamond_sickle", () -> new BaseSickleItem(Tiers.DIAMOND, 3));
	public static final DeferredHolder<Item, Item> FLINT_SICKLE = REGISTRY.register("flint_sickle", () -> new BaseSickleItem(ModItemTier.FLINT, 1));
	public static final DeferredHolder<Item, Item> EMERALD_SICKLE = REGISTRY.register("emerald_sickle", () ->  new BaseSickleItem(ModItemTier.EMERALD, 3));
	public static final DeferredHolder<Item, Item> NETHERITE_SICKLE = REGISTRY.register("netherite_sickle", () -> new BaseSickleItem(Tiers.NETHERITE, 3));

	public static final DeferredHolder<Item, Item> WOODEN_SCYTHE = REGISTRY.register("wooden_scythe", () -> new BaseScytheItem(Tiers.WOOD, 1));
	public static final DeferredHolder<Item, Item> STONE_SCYTHE = REGISTRY.register("stone_scythe", () -> new BaseScytheItem(Tiers.STONE, 1));
	public static final DeferredHolder<Item, Item> IRON_SCYTHE = REGISTRY.register("iron_scythe", () -> new BaseScytheItem(Tiers.IRON, 2));
	public static final DeferredHolder<Item, Item> GOLDEN_SCYTHE = REGISTRY.register("golden_scythe", () -> new BaseScytheItem(Tiers.GOLD, 2));
	public static final DeferredHolder<Item, Item> DIAMOND_SCYTHE = REGISTRY.register("diamond_scythe", () -> new BaseScytheItem(Tiers.DIAMOND, 3));
	public static final DeferredHolder<Item, Item> FLINT_SCYTHE = REGISTRY.register("flint_scythe", () -> new BaseScytheItem(ModItemTier.FLINT, 1));
	public static final DeferredHolder<Item, Item> EMERALD_SCYTHE = REGISTRY.register("emerald_scythe", () -> new BaseScytheItem(ModItemTier.EMERALD, 3));
	public static final DeferredHolder<Item, Item> NETHERITE_SCYTHE = REGISTRY.register("netherite_scythe", () -> new BaseScytheItem(Tiers.NETHERITE, 3));
}
