package com.axelgamer.naturalsoulast.item;

import com.axelgamer.naturalsoulast.sound.ModSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class GrassCutterItem extends Item {

    public GrassCutterItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        Level level = pContext.getLevel();
        Block clickedBlock = level.getBlockState(pContext.getClickedPos()).getBlock();
        Player player = pContext.getPlayer();

        if(!level.isClientSide()) {
            if (clickedBlock.defaultBlockState() == Blocks.SHORT_GRASS.defaultBlockState()) {
                assert player != null;

                player.swing(InteractionHand.MAIN_HAND);
                player.addItem(new ItemStack(Items.WHEAT_SEEDS));

                level.playSeededSound(null,
                        pContext.getClickedPos().getX(),
                        pContext.getClickedPos().getY(),
                        pContext.getClickedPos().getZ(),
                        ModSounds.GRASS_CUTTER_CUT_SOUND.get(),
                        SoundSource.BLOCKS,
                        1f,
                        1f,
                        0);

                level.setBlockAndUpdate(pContext.getClickedPos(), Blocks.AIR.defaultBlockState());

            }
        }

        return InteractionResult.SUCCESS;
    }
}
