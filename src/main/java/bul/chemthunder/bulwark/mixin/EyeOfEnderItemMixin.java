package bul.chemthunder.bulwark.mixin;

import bul.chemthunder.bulwark.index.BulwarkBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderEyeItem.class)
public abstract class EyeOfEnderItemMixin extends Item {
    public EyeOfEnderItemMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "useOnBlock", at = @At("HEAD"))
    private void useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();

        if (player != null) {
            ItemStack stack = player.getStackInHand(context.getHand());
            if (player.isSneaking() && context.getWorld().getBlockState(context.getBlockPos()).isOf(Blocks.SCULK_CATALYST) && stack.isOf(Items.ENDER_EYE)) {
                if (player instanceof PlayerEntity) {
                    stack.decrement(1);
                    world.setBlockState(context.getBlockPos(), BulwarkBlocks.OMINOUS_CATALYST.getDefaultState());
                    player.playSound(SoundEvents.BLOCK_CONDUIT_ACTIVATE);
                    player.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE);
                }

                if (world.getBlockState(context.getBlockPos().down()).isOf(Blocks.AIR)) {
                    world.setBlockState(context.getBlockPos().down(), BulwarkBlocks.ILL_SUBSTANCE.getDefaultState());
                }
            }
        }
    }
}
