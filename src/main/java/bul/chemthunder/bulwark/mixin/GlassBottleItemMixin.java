package bul.chemthunder.bulwark.mixin;

import bul.chemthunder.bulwark.index.BulwarkBlocks;
import bul.chemthunder.bulwark.index.BulwarkItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GlassBottleItem.class)
public abstract class GlassBottleItemMixin extends Item {
    public GlassBottleItemMixin(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();

        if (player != null) {
            ItemStack stack = player.getStackInHand(context.getHand());
            if (player.isSneaking() && context.getWorld().getBlockState(pos).isOf(BulwarkBlocks.ILL_SUBSTANCE) && stack.isOf(Items.GLASS_BOTTLE)) {
                stack.decrement(1);
                player.swingHand(context.getHand(), !world.isClient);
                player.giveItemStack(BulwarkItems.ILL_BOTTLE.getDefaultStack());
                player.playSound(SoundEvents.ITEM_HONEYCOMB_WAX_ON);
                player.playSound(SoundEvents.BLOCK_CONDUIT_AMBIENT_SHORT);
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }

        return ActionResult.PASS;
    }
}
