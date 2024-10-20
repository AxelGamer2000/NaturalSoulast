package com.axelgamer.naturalsoulast.entities;

import com.axelgamer.naturalsoulast.NaturalSoulast;
import com.axelgamer.naturalsoulast.entities.projectiles.Pebble;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, NaturalSoulast.MODID);
    public static final DeferredHolder<EntityType<?>, EntityType<Pebble>> PEBBLE = ENTITY_TYPES.register(
            "pebble", () -> EntityType.Builder.<Pebble>of(Pebble::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("pebble"));
}
