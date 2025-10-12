package bul.chemthunder.bulwark.block;

import bul.chemthunder.bulwark.index.BulwarkBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class BuddingKlaprothBlock extends Block {
    public BuddingKlaprothBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockState aboveState = world.getBlockState(pos.up());

        if (!world.getBlockState(pos.down(1)).isOf(Blocks.MAGMA_BLOCK) && !world.getBlockState(pos.down(2)).isOf(Blocks.MAGMA_BLOCK) && !world.getBlockState(pos.down(3)).isOf(Blocks.MAGMA_BLOCK) && !world.getBlockState(pos.down(4)).isOf(Blocks.MAGMA_BLOCK)) {
            if (aboveState.isOf(Blocks.AIR)) {
                world.setBlockState(pos.up(), BulwarkBlocks.SMALL_KLAPROTH_BUD.getDefaultState());
            } else if (aboveState.isOf(BulwarkBlocks.SMALL_KLAPROTH_BUD)) {
                world.setBlockState(pos.up(), BulwarkBlocks.MEDIUM_KLAPROTH_BUD.getDefaultState());
            } else if (aboveState.isOf(BulwarkBlocks.MEDIUM_KLAPROTH_BUD)) {
                world.setBlockState(pos.up(), BulwarkBlocks.LARGE_KLAPROTH_BUD.getDefaultState());
            } else if (aboveState.isOf(BulwarkBlocks.LARGE_KLAPROTH_BUD)) {
                world.setBlockState(pos.up(), BulwarkBlocks.KLAPROTH_CLUSTER.getDefaultState());
            }
        }

        super.randomTick(state, world, pos, random);
    }
}
