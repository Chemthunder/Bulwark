package bul.chemthunder.bulwark.block;

import bul.chemthunder.bulwark.Bulwark;
import bul.chemthunder.bulwark.index.BulwarkBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RodBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class KlaprothTubeBlock extends RodBlock {
    public static final MapCodec<KlaprothTubeBlock> CODEC = createCodec(KlaprothTubeBlock::new);

    public MapCodec<KlaprothTubeBlock> getCodec() {
        return CODEC;
    }


    public KlaprothTubeBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.UP));
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction = ctx.getSide();
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos().offset(direction.getOpposite()));
        return blockState.isOf(this) && blockState.get(FACING) == direction ? (BlockState)this.getDefaultState().with(FACING, direction.getOpposite()) : (BlockState)this.getDefaultState().with(FACING, direction);
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        int chance = random.nextInt(2);

        Direction direction = (Direction)state.get(FACING);
        double d = (double)pos.getX() + 0.55 - (double)(random.nextFloat() * 0.1F);
        double e = (double)pos.getY() + 0.55 - (double)(random.nextFloat() * 0.1F);
        double f = (double)pos.getZ() + 0.55 - (double)(random.nextFloat() * 0.1F);
        double g = (double)(0.4F - (random.nextFloat() + random.nextFloat()) * 0.4F);
        if (random.nextInt(5) == 0) {
            if (state.isOf(BulwarkBlocks.KLAPROTH_TUBE)) {
                world.addParticleClient(Bulwark.SPARKLE_BASE, d + (double) direction.getOffsetX() * g, e + (double) direction.getOffsetY() * g, f + (double) direction.getOffsetZ() * g, random.nextGaussian() * 0.005, random.nextGaussian() * 0.005, random.nextGaussian() * 0.005);
            }
            if (state.isOf(BulwarkBlocks.LUMINANT_KLAPROTH_TUBE)) {
                world.addParticleClient(Bulwark.SPARKLE_LUMI, d + (double) direction.getOffsetX() * g, e + (double) direction.getOffsetY() * g, f + (double) direction.getOffsetZ() * g, random.nextGaussian() * 0.005, random.nextGaussian() * 0.005, random.nextGaussian() * 0.005);
            }
            if (state.isOf(BulwarkBlocks.SCULK_KLAPROTH_TUBE)) {
                world.addParticleClient(Bulwark.SPARKLE_SCULK, d + (double) direction.getOffsetX() * g, e + (double) direction.getOffsetY() * g, f + (double) direction.getOffsetZ() * g, random.nextGaussian() * 0.005, random.nextGaussian() * 0.005, random.nextGaussian() * 0.005);
            }
            if (state.isOf(BulwarkBlocks.SILLY_KLAPROTH_TUBE)) {
                if (chance == 0) {
                    world.addParticleClient(Bulwark.SPARKLE_SILLY_1, d + (double) direction.getOffsetX() * g, e + (double) direction.getOffsetY() * g, f + (double) direction.getOffsetZ() * g, random.nextGaussian() * 0.005, random.nextGaussian() * 0.005, random.nextGaussian() * 0.005);
                } else {
                    world.addParticleClient(Bulwark.SPARKLE_SILLY_2, d + (double) direction.getOffsetX() * g, e + (double) direction.getOffsetY() * g, f + (double) direction.getOffsetZ() * g, random.nextGaussian() * 0.005, random.nextGaussian() * 0.005, random.nextGaussian() * 0.005);
                }
            }
            if (state.isOf(BulwarkBlocks.RED_KLAPROTH_TUBE)) {
                world.addParticleClient(Bulwark.SPARKLE_RED, d + (double) direction.getOffsetX() * g, e + (double) direction.getOffsetY() * g, f + (double) direction.getOffsetZ() * g, random.nextGaussian() * 0.005, random.nextGaussian() * 0.005, random.nextGaussian() * 0.005);
            }
            if (state.isOf(BulwarkBlocks.GLOWING_KLAPROTH_TUBE)) {
                world.addParticleClient(Bulwark.SPARKLE_GLOWING, d + (double) direction.getOffsetX() * g, e + (double) direction.getOffsetY() * g, f + (double) direction.getOffsetZ() * g, random.nextGaussian() * 0.005, random.nextGaussian() * 0.005, random.nextGaussian() * 0.005);
            }
        }
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
