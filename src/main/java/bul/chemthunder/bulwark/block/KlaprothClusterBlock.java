package bul.chemthunder.bulwark.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class KlaprothClusterBlock extends Block {
    public KlaprothClusterBlock(Settings settings) {
        super(settings);
    }
    private static final VoxelShape SHAPE;
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE.offset(state.getModelOffset(pos));
    }

    static  {
        SHAPE = Block.createColumnShape((double)6.0F, (double)0.0F, (double)10.0F);
    }
}
