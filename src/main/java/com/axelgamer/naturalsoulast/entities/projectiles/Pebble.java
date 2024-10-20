package com.axelgamer.naturalsoulast.entities.projectiles;

import com.axelgamer.naturalsoulast.entities.ModEntities;
import com.axelgamer.naturalsoulast.item.ModItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SnowballItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

public class Pebble extends ThrowableItemProjectile {

    private static final Item DEFAULT_ITEM = ModItems.PEBBLE.get();
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
    public @NotNull ItemStack getItem() {
        return new ItemStack(DEFAULT_ITEM);
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), 1);

        if (entity instanceof LivingEntity livingEntity && !entity.level().isClientSide()) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 1, false, true, true));
        }

    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }

    private ParticleOptions getParticle() {
        ItemStack itemstack = this.getItem();
        return (ParticleOptions)(new ItemParticleOption(ParticleTypes.ITEM, itemstack));
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 3) {
            ParticleOptions particleoptions = this.getParticle();

            for (int i = 0; i < 8; i++) {
                this.level().addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }
}
