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

public class shortGrassModifier extends LootModifier {

    public static Supplier<MapCodec<shortGrassModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.mapCodec((ntLootModifierInstance -> shortGrassModifier.codecStart(ntLootModifierInstance)
            .and(BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(shortGrassModifierInstance1 -> shortGrassModifierInstance1.item))
            .and(Codec.BOOL.fieldOf("remove").forGetter(shortGrassModifierInstance1 -> shortGrassModifierInstance1.isRemove))
            .and(BuiltInRegistries.ITEM.byNameCodec().fieldOf("target").forGetter(shortGrassModifierInstance1 -> shortGrassModifierInstance1.target))
            .apply(ntLootModifierInstance, shortGrassModifier::new))));

    private final Item item;
    private final Boolean isRemove;
    private final Item target;

    public shortGrassModifier(LootItemCondition[] conditions, Item item, Boolean isRemove, Item target) {
        super(conditions);
        this.item = item;
        this.isRemove = isRemove;
        this.target = target;
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


        generatedLoot.add(new ItemStack(this.item));
        if(isRemove) {
            generatedLoot.removeIf(x -> (x.getItem() == target));
        }

        return generatedLoot;
    }

}
