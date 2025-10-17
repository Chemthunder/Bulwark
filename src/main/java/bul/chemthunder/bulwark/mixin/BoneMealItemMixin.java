package bul.chemthunder.bulwark.mixin;

import bul.chemthunder.bulwark.index.BulwarkBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoneMealItem.class)
public abstract class BoneMealItemMixin extends Item {
    public BoneMealItemMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "useOnBlock", at = @At("HEAD"))
    private void bulwark$overrideUseOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        PlayerEntity player = context.getPlayer();
        BlockPos belowPos = context.getBlockPos().down();
        World world = context.getWorld();

        if (world.getBlockState(belowPos).isOf(Blocks.AIR) || world.getBlockState(belowPos).isOf(BulwarkBlocks.ILL_SUBSTANCE)) {
            world.setBlockState(belowPos, BulwarkBlocks.ILL_SUBSTANCE.getDefaultState());
        }

        if (player != null) player.playSound(SoundEvents.ITEM_BONE_MEAL_USE);
    }
}
