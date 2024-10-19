package com.axelgamer.naturalsoulast.entities.projectiles;

import com.axelgamer.naturalsoulast.entities.ModEntities;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

public class Pebble extends ThrowableItemProjectile {

    private static final Item DEFAULT_ITEM = Items.APPLE;
    private static final EntityType<Pebble> ENTITY_TYPE = ModEntities.PEBBLE.get();

    public Pebble(EntityType<? extends Pebble> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public Pebble(Level pLevel, LivingEntity pShooter) {
        super(ENTITY_TYPE, pShooter, pLevel);
    }

    public Pebble(Level pLevel, double pX, double pY, double pZ) {
        super(ENTITY_TYPE, pX, pY, pZ, pLevel);
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return DEFAULT_ITEM;
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), 4);

        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 5, 1, false, false, false));
        }

    }

}
