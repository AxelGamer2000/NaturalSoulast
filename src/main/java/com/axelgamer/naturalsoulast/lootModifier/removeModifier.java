package com.axelgamer.naturalsoulast.lootModifier;

import com.axelgamer.naturalsoulast.NaturalSoulast;
import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class removeModifier extends LootModifier {

    public static Supplier<MapCodec<removeModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.mapCodec((ntLootModifierInstance -> removeModifier.codecStart(ntLootModifierInstance)
            .and(BuiltInRegistries.ITEM.byNameCodec().fieldOf("removedItem").forGetter(shortGrassModifierInstance1 -> shortGrassModifierInstance1.removedItem))
            .apply(ntLootModifierInstance, removeModifier::new))));

    private final Item removedItem;

    public removeModifier(LootItemCondition[] conditions, Item removedItem) {
        super(conditions);
        this.removedItem = removedItem;
    }

    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, NaturalSoulast.MODID);

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {

        for (LootItemCondition condition: this.conditions) {
            if (!condition.test(context)) {
                return generatedLoot;
            }
        }

        generatedLoot.removeIf(x -> (x.getItem() == removedItem));

        return generatedLoot;
    }

}
