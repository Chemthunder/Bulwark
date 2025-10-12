package bul.chemthunder.bulwark.item;

import bul.chemthunder.bulwark.index.BulwarkBlocks;
import bul.chemthunder.bulwark.index.BulwarkItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CandyItem extends Item {
    public CandyItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        BlockPos pos = context.getBlockPos();
        ItemStack offStack = player.getOffHandStack();
        ItemStack mainStack = player.getMainHandStack();

        if (world.getBlockState(pos).isOf(BulwarkBlocks.KLAPROTH_CLUSTER)) {
            if (offStack.isOf(this) && mainStack.isOf(Items.GOLDEN_AXE)) {
                offStack.decrement(1);
                mainStack.decrement(1);
                player.giveItemStack(BulwarkItems.DENTIST.getDefaultStack());

                player.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK);
                player.playSound(SoundEvents.BLOCK_END_PORTAL_SPAWN);
            }
        }

        return super.useOnBlock(context);
    }
}
