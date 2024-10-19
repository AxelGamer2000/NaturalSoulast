package com.axelgamer.naturalsoulast.entities;

import com.axelgamer.naturalsoulast.NaturalSoulast;
import com.axelgamer.naturalsoulast.entities.projectiles.Pebble;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE = DeferredRegister.create(Registries.ENTITY_TYPE, NaturalSoulast.MODID);
    public static final DeferredHolder<EntityType<?>, EntityType<Pebble>> PEBBLE = ENTITY_TYPE.register(
            "pebble", () -> EntityType.Builder.<Pebble>of(Pebble::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("pebble"));


    private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
        return ENTITY_TYPE.register(registryname, () -> entityTypeBuilder.build(registryname));
    }
}
