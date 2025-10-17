package bul.chemthunder.bulwark.mixin;

import bul.chemthunder.bulwark.index.BulwarkBlocks;
import bul.chemthunder.bulwark.index.BulwarkStatusEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BrushItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BrushItem.class)
public abstract class BrushItemMixin extends Item {
    public BrushItemMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "useOnBlock", at = @At("HEAD"))
    private void bulwark$klaprothDustcloud(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        PlayerEntity player = context.getPlayer();
        if (player != null) {
            if (context.getWorld().getBlockState(context.getBlockPos()).isOf(BulwarkBlocks.KLAPROTH_BLOCK)) {
                player.addStatusEffect(new StatusEffectInstance(BulwarkStatusEffects.ACTINISM, 600));
                player.playSound(SoundEvents.BLOCK_AMETHYST_CLUSTER_BREAK);
                player.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE);
                player.getItemCooldownManager().set(player.getStackInHand(context.getHand()), 60);
            }
        }
    }
}