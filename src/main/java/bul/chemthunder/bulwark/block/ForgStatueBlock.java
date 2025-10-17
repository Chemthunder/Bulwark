package bul.chemthunder.bulwark.block;

import bul.chemthunder.bulwark.index.BulwarkItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import static net.minecraft.block.BarrelBlock.FACING;

public class ForgStatueBlock extends Block {
    public ForgStatueBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.UP));
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (stack.isOf(Items.MAGMA_CREAM)) {
            if (player.getOffHandStack().isOf(Items.PURPLE_DYE)) {
                player.giveItemStack(Blocks.PEARLESCENT_FROGLIGHT.asItem().getDefaultStack());
            }
            if (player.getOffHandStack().isOf(Items.LIME_DYE)) {
                player.giveItemStack(Blocks.VERDANT_FROGLIGHT.asItem().getDefaultStack());
            }
            if (player.getOffHandStack().isOf(Items.YELLOW_DYE)) {
                player.giveItemStack(Blocks.OCHRE_FROGLIGHT.asItem().getDefaultStack());
            }
            if (player.getOffHandStack().isOf(BulwarkItems.KLAPROTH)) {
                player.giveItemStack(Blocks.OCHRE_FROGLIGHT.asItem().getDefaultStack());
            }
        }
        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
