package com.blakebr0.pickletweaks.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public final class BlacklistUtils {
    public static boolean contains(Item item, List<String> blacklist) {
        var id = ForgeRegistries.ITEMS.getKey(item);
        if (id == null)
            return false;

        return blacklist.stream().anyMatch(s -> {
            var parts = s.split(":");

            // tag case
            if (parts.length == 3 && s.startsWith("tag:")) {
                var tag = ItemTags.create(new ResourceLocation(parts[1], parts[2]));

                return item.builtInRegistryHolder().is(tag);
            }

            if (parts.length != 2)
                return false;

            if ("*".equals(parts[1])) {
                return id.getNamespace().equals(parts[0]);
            }

            return id.toString().equals(s);
        });
    }
}
