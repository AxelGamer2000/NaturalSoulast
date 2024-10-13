package com.axelgamer.naturalsoulast.entities.projectiles;

import com.axelgamer.naturalsoulast.entities.ModEntities;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class PebbleProjectile extends Arrow implements ItemSupplier {

    public static final ItemStack PROJECTILE_ITEM = new ItemStack(Items.COBBLESTONE);
    private int knockback = 0;

    public PebbleProjectile(EntityType<? extends Arrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public PebbleProjectile(EntityType<? extends Arrow> pEntityType, double pX, double pY, double pZ, Level pLevel, ItemStack pPickupItemStack, @Nullable ItemStack pFiredFromWeapon) {
        super(pEntityType, pX, pY, pZ, pLevel, PROJECTILE_ITEM, pFiredFromWeapon);
    }

    public PebbleProjectile(EntityType<? extends Arrow> pEntityType, LivingEntity pOwner, Level pLevel, ItemStack pPickupItemStack, @Nullable ItemStack pFiredFromWeapon) {
        super(pEntityType, pOwner, pLevel, PROJECTILE_ITEM, pFiredFromWeapon);
    }

    @Override
    public ItemStack getItem() {
        return PROJECTILE_ITEM;
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return PROJECTILE_ITEM;
    }

    public void setKnockback(int knockback) {
        this.knockback = knockback;
    }

    @Override
    protected void doPostHurtEffects(LivingEntity pTarget) {
        super.doPostHurtEffects(pTarget);
    }

    @Override
    protected void doKnockback(LivingEntity livingEntity, DamageSource damageSource) {
        if (knockback > 0.0) {
            double d1 = Math.max(0.0, 1.0 - livingEntity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
            Vec3 vec3 = this.getDeltaMovement().multiply(1.0, 0.0, 1.0).normalize().scale(knockback * 0.6 * d1);
            if (vec3.lengthSqr() > 0.0) {
                livingEntity.push(vec3.x, 0.1, vec3.z);
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        if(this.inGround) {
            this.discard();
        }
    }

    public static PebbleProjectile shoot(Level world, LivingEntity entity, RandomSource source) {
        return shoot(world, entity, source, 1f, 5, 5);
    }

    public static PebbleProjectile shoot(Level world, LivingEntity entity, RandomSource source, float pullingPower) {
        return shoot(world, entity, source, pullingPower * 1f, 5, 5);
    }

    public static PebbleProjectile shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
        PebbleProjectile entityarrow = new PebbleProjectile(ModEntities.PEBBLE_PROJECTILE.get(), world);
        entityarrow.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 2, 0);
        entityarrow.setSilent(true);
        entityarrow.setCritArrow(false);
        entityarrow.setBaseDamage(damage);
        entityarrow.setKnockback(knockback);
        world.addFreshEntity(entityarrow);
        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.arrow.shoot")), SoundSource.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
        return entityarrow;
    }

    public static PebbleProjectile shoot(LivingEntity entity, LivingEntity target) {
        PebbleProjectile entityarrow = new PebbleProjectile(ModEntities.PEBBLE_PROJECTILE.get(), entity.level());
        double dx = target.getX() - entity.getX();
        double dy = target.getY() + target.getEyeHeight() - 1.1;
        double dz = target.getZ() - entity.getZ();
        entityarrow.shoot(dx, dy - entityarrow.getY() + Math.hypot(dx, dz) * 0.2F, dz, 1f * 2, 12.0F);
        entityarrow.setSilent(true);
        entityarrow.setBaseDamage(5);
        entityarrow.setKnockback(5);
        entityarrow.setCritArrow(false);
        entity.level().addFreshEntity(entityarrow);
        entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.arrow.shoot")), SoundSource.PLAYERS, 1, 1f / (RandomSource.create().nextFloat() * 0.5f + 1));
        return entityarrow;
    }

}
