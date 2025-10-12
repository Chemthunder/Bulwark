package bul.chemthunder.bulwark.block;

import bul.chemthunder.bulwark.index.BulwarkBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MetallicTaintedGlassBlock extends Block {
    public MetallicTaintedGlassBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (player.isSneaking()) {
            world.setBlockState(pos, BulwarkBlocks.TAINTED_GLASS.getDefaultState());
            player.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE);

            BlockPos north = pos.north();
            BlockPos up = pos.up();
            BlockPos down = pos.down();
            BlockPos east = pos.east();
            BlockPos south = pos.south();
            BlockPos west = pos.west();


            if (world.getBlockState(north).isOf(this)) {
                world.setBlockState(north, BulwarkBlocks.TAINTED_GLASS.getDefaultState());
            }
            if (world.getBlockState(up).isOf(this)) {
                world.setBlockState(up, BulwarkBlocks.TAINTED_GLASS.getDefaultState());
            }
            if (world.getBlockState(down).isOf(this)) {
                world.setBlockState(down, BulwarkBlocks.TAINTED_GLASS.getDefaultState());
            }
            if (world.getBlockState(east).isOf(this)) {
                world.setBlockState(east, BulwarkBlocks.TAINTED_GLASS.getDefaultState());
            }
            if (world.getBlockState(south).isOf(this)) {
                world.setBlockState(south, BulwarkBlocks.TAINTED_GLASS.getDefaultState());
            }
            if (world.getBlockState(west).isOf(this)) {
                world.setBlockState(west, BulwarkBlocks.TAINTED_GLASS.getDefaultState());
            }
        }
        return super.onUse(state, world, pos, player, hit);
    }
}
