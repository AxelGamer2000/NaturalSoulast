package com.axelgamer.naturalsoulast.lootModifier;

import com.axelgamer.naturalsoulast.NaturalSoulast;
import com.axelgamer.naturalsoulast.item.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

public class glmDatagen extends GlobalLootModifierProvider {

    public glmDatagen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, NaturalSoulast.MODID);
    }

    @Override
    protected void start() {
        add("short_grass_modifier", new ntLootModifier(new LootItemCondition[] {
                LootTableIdCondition.builder(ResourceLocation.parse("minecraft:blocks/short_grass")).build(),
                MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.IRON_SWORD)).build()
                },
                ModItems.GRASS_STALK.get()));
    }
}

//return error

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = "naturalsoulast")
class glmDatagenHandler {
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        event.getGenerator().addProvider(event.includeServer(), new glmDatagen(event.getGenerator().getPackOutput(), event.getLookupProvider()));
    }
}