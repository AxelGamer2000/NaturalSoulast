package com.axelgamer.naturalsoulast.sound;

import com.axelgamer.naturalsoulast.NaturalSoulast;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, NaturalSoulast.MODID);

    public static final DeferredHolder<SoundEvent, SoundEvent> GRASS_CUTTER_CUT_SOUND = SOUND_EVENTS.register(
            "grass_cutter_cut_sound",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(NaturalSoulast.MODID, "grass_cutter_cut"))
    );
}
