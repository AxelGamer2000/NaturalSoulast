package com.axelgamer.naturalsoulast.creativeTab;

import com.axelgamer.naturalsoulast.NaturalSoulast;
import com.axelgamer.naturalsoulast.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {

    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "naturalsoulast" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NaturalSoulast.MODID);

    // Creates a creative tab with the id "naturalsoulast:example_tab" for the example item, that is placed after the combat tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.naturalsoulast")) //The language key for the title of your CreativeModeTab
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ModItems.SOUL.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ModItems.GRASS_STALK.get());
                output.accept(ModItems.SCISSORS_BLADE.get());
                output.accept(ModItems.WOODEN_FINGER_RING.get());
                output.accept(ModItems.GRASS_CUTTER.get());
                output.accept(ModItems.SOUL.get());
                output.accept(ModItems.EYE_OF_NATURAL_SOUL.get());
                output.accept(ModItems.AMULET_OF_NATURAL_SOUL.get());
            }).build());

}
