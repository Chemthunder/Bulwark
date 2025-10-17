package bul.chemthunder.bulwark.item;

import bul.chemthunder.bulwark.index.BulwarkBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class IllBottleItem extends Item {
    public IllBottleItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos();
        BlockState state = context.getWorld().getBlockState(pos);
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        Hand hand = player.getActiveHand();
        ItemStack stack = player.getStackInHand(hand);

        if (state.isOf(Blocks.BUDDING_AMETHYST)) {
            world.setBlockState(pos, BulwarkBlocks.BUDDING_KLAPROTH.getDefaultState());
            player.playSound(SoundEvents.BLOCK_ANVIL_USE, 0.25f, 3);
            player.playSound(SoundEvents.BLOCK_SIGN_WAXED_INTERACT_FAIL);
            stack.decrement(1);
        }
        return super.useOnBlock(context);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        World world = user.getWorld();
        BlockPos pos = entity.getBlockPos();
        if (entity instanceof FrogEntity forg) {
            if (world instanceof ServerWorld serverWorld) {
                forg.kill(serverWorld);
                world.setBlockState(pos, BulwarkBlocks.FORG_STATUE.getDefaultState());
            }
        }
        return super.useOnEntity(stack, user, entity, hand);
    }
}
