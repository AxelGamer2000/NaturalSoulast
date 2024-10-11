package com.axelgamer.naturalsoulast.item.amulets;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class AmuletOfNaturalSoulItem extends Item implements ICurioItem {
    public AmuletOfNaturalSoulItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        slotContext.entity().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 3, 1, false, false, false));
        slotContext.entity().addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3, 0, false, false, false));
        slotContext.entity().addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 3, 0, false, false, false));
        ICurioItem.super.curioTick(slotContext, stack);
    }
}
