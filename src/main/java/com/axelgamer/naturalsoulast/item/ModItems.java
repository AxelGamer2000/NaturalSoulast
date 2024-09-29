package com.axelgamer.naturalsoulast.item;

import com.axelgamer.naturalsoulast.NaturalSoulast;
import com.axelgamer.naturalsoulast.block.ModBlocks;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    // Create a Deferred Register to hold Items which will all be registered under the "naturalsoulast" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NaturalSoulast.MODID);

    // Creates a new BlockItem with the id "naturalsoulast:example_block", combining the namespace and path
    public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("example_block", ModBlocks.EXAMPLE_BLOCK);

    // Creates a new food item with the id "naturalsoulast:example_id", nutrition 1 and saturation 2
    public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item", new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(12).saturationModifier(21f).build()));

    public static final DeferredItem<Item> GRASS_STALK = ITEMS.registerSimpleItem(
      "grass_stalk",
      new Item.Properties()
    );

    public static final DeferredItem<Item> SCISSORS_BLADE = ITEMS.registerSimpleItem(
            "scissors_blade",
            new Item.Properties()
    );

    public static final DeferredItem<Item> WOODEN_FINGER_RING = ITEMS.registerSimpleItem(
            "wooden_finger_ring",
            new Item.Properties()
    );

    public static final DeferredItem<Item> GRASS_CUTTER = ITEMS.register(
            "grass_cutter",
            () -> new GrassCutterItem(new Item.Properties().stacksTo(1))
    );
}
