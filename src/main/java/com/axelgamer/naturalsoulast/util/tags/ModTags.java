package com.axelgamer.naturalsoulast.util.tags;

import com.axelgamer.naturalsoulast.NaturalSoulast;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static class Items {

        public static final TagKey<Item> LIVING_SOUL_FOOD = createTag("living_soul_food");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(NaturalSoulast.MODID, name));
        }
    }
}
