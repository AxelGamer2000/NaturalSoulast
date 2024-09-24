package com.axelgamer.naturalsoulast.lootModifier;

import com.axelgamer.naturalsoulast.NaturalSoulast;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class glmDatagen extends GlobalLootModifierProvider {

    public glmDatagen(PackOutput output, CompletableFuture<HolderLookup.Provider> providerCompletableFuture) {
        super(output, providerCompletableFuture, NaturalSoulast.MODID);
    }

    @Override
    protected void start() {
        add(
                "short_grass_modifier_test",

                new ntLootModifier(new LootItemCondition[]{

                }),

                List.of(new ModLoadedCondition("minecraft"))
        );
    }
}

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = "naturalsoulast")
class glmDatagenHandler {
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        event.getGenerator().addProvider(event.includeServer(), new glmDatagen(event.getGenerator().getPackOutput(), event.getLookupProvider()));
    }
}