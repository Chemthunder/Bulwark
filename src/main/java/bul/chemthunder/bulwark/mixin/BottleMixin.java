package bul.chemthunder.bulwark.mixin;

import bul.chemthunder.bulwark.index.BulwarkBlocks;
import bul.chemthunder.bulwark.index.BulwarkItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GlassBottleItem.class)
public abstract class BottleMixin extends Item {
    public BottleMixin(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        PlayerEntity player = context.getPlayer();
        Hand hand = player.getActiveHand();
        ItemStack stack = player.getStackInHand(hand);

        if (world.getBlockState(pos).isOf(BulwarkBlocks.ILL_SUBSTANCE)) {
            stack.decrement(1);
            player.giveItemStack(BulwarkItems.ILL_BOTTLE.getDefaultStack());
            player.playSound(SoundEvents.ITEM_HONEYCOMB_WAX_ON);
            player.playSound(SoundEvents.BLOCK_CONDUIT_AMBIENT_SHORT);
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
        }

        return super.useOnBlock(context);
    }
}
