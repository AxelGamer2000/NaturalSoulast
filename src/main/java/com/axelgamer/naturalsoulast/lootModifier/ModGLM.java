package com.axelgamer.naturalsoulast.lootModifier;

import com.axelgamer.naturalsoulast.NaturalSoulast;
import com.mojang.serialization.MapCodec;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModGLM {
    public static DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIER = DeferredRegister.create(NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS, NaturalSoulast.MODID);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<shortGrassModifier>> SHORT_GRASS_MODIFIER = LOOT_MODIFIER.register("add_item", shortGrassModifier.CODEC);
    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<removeModifier>> REMOVED_MODIFIER = LOOT_MODIFIER.register("remove_item", removeModifier.CODEC);
}
