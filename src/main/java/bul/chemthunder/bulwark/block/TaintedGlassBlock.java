package bul.chemthunder.bulwark.block;

import bul.chemthunder.bulwark.index.BulwarkBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.TransparentBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;

public class TaintedGlassBlock extends TransparentBlock {
    public TaintedGlassBlock(Settings settings) {
        super(settings);
    }

    protected boolean isTransparent(BlockState state) {
        return true;
    }

    protected int getOpacity(BlockState state) {
        return 50;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (player.isSneaking()) {
            world.setBlockState(pos, BulwarkBlocks.CRYSTALLIZED_TAINTED_GLASS.getDefaultState());
            player.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE);

            BlockPos north = pos.north();
            BlockPos up = pos.up();
            BlockPos down = pos.down();
            BlockPos east = pos.east();
            BlockPos south = pos.south();
            BlockPos west = pos.west();

            if (world.getBlockState(north).isOf(this)) {
                world.setBlockState(north, BulwarkBlocks.CRYSTALLIZED_TAINTED_GLASS.getDefaultState());
            }
            if (world.getBlockState(up).isOf(this)) {
                world.setBlockState(up, BulwarkBlocks.CRYSTALLIZED_TAINTED_GLASS.getDefaultState());
            }
            if (world.getBlockState(down).isOf(this)) {
                world.setBlockState(down, BulwarkBlocks.CRYSTALLIZED_TAINTED_GLASS.getDefaultState());
            }
            if (world.getBlockState(east).isOf(this)) {
                world.setBlockState(east, BulwarkBlocks.CRYSTALLIZED_TAINTED_GLASS.getDefaultState());
            }
            if (world.getBlockState(south).isOf(this)) {
                world.setBlockState(south, BulwarkBlocks.CRYSTALLIZED_TAINTED_GLASS.getDefaultState());
            }
            if (world.getBlockState(west).isOf(this)) {
                world.setBlockState(west, BulwarkBlocks.CRYSTALLIZED_TAINTED_GLASS.getDefaultState());
            }
            player.swingHand(Hand.MAIN_HAND);
        }

        if (player.isSneaking() && player.getMainHandStack().isOf(Items.IRON_NUGGET)) {
            world.setBlockState(pos, BulwarkBlocks.METALLIC_TAINTED_GLASS.getDefaultState());
            player.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE);

            BlockPos north = pos.north();
            BlockPos up = pos.up();
            BlockPos down = pos.down();
            BlockPos east = pos.east();
            BlockPos south = pos.south();
            BlockPos west = pos.west();

            if (world.getBlockState(north).isOf(this)) {
                world.setBlockState(north, BulwarkBlocks.METALLIC_TAINTED_GLASS.getDefaultState());
            }
            if (world.getBlockState(up).isOf(this)) {
                world.setBlockState(up, BulwarkBlocks.METALLIC_TAINTED_GLASS.getDefaultState());
            }
            if (world.getBlockState(down).isOf(this)) {
                world.setBlockState(down, BulwarkBlocks.METALLIC_TAINTED_GLASS.getDefaultState());
            }
            if (world.getBlockState(east).isOf(this)) {
                world.setBlockState(east, BulwarkBlocks.METALLIC_TAINTED_GLASS.getDefaultState());
            }
            if (world.getBlockState(south).isOf(this)) {
                world.setBlockState(south, BulwarkBlocks.METALLIC_TAINTED_GLASS.getDefaultState());
            }
            if (world.getBlockState(west).isOf(this)) {
                world.setBlockState(west, BulwarkBlocks.METALLIC_TAINTED_GLASS.getDefaultState());
            }
            player.swingHand(Hand.MAIN_HAND);
        }
        return super.onUse(state, world, pos, player, hit);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }


}
