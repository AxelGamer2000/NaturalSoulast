package com.axelgamer.naturalsoulast.entities;

import com.axelgamer.naturalsoulast.NaturalSoulast;
import com.axelgamer.naturalsoulast.entities.projectiles.PebbleProjectile;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE = DeferredRegister.create(Registries.ENTITY_TYPE, NaturalSoulast.MODID);
    public static final DeferredHolder<EntityType<?>, EntityType<PebbleProjectile>> PEBBLE_PROJECTILE = register("test",
            EntityType.Builder.<PebbleProjectile>of(PebbleProjectile::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));

    private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
        return ENTITY_TYPE.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
    }

}
