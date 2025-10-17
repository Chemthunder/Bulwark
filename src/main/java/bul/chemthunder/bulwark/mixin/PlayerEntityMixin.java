package bul.chemthunder.bulwark.mixin;

import bul.chemthunder.bulwark.index.BulwarkItems;
import bul.chemthunder.bulwark.index.BulwarkMasks;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @ModifyReturnValue(method = "getDisplayName", at = @At("RETURN"))
    private Text bulwark$maskName(Text original) {
        PlayerEntity player = (PlayerEntity)(Object)this;
        if (player.getEquippedStack(EquipmentSlot.HEAD).isOf(BulwarkItems.ECLIPSE_MASK)) {
                return Text.translatable("name.mask").withColor(0x481b52).formatted(Formatting.ITALIC).formatted(Formatting.OBFUSCATED);
        }
        if (player.getEquippedStack(EquipmentSlot.HEAD).isOf(BulwarkMasks.RED_SHADE_MASK)) {
            return Text.translatable("name.shade").withColor(0xff003c).formatted(Formatting.ITALIC).formatted(Formatting.OBFUSCATED);
        }
        if (player.getEquippedStack(EquipmentSlot.HEAD).isOf(BulwarkMasks.SILLY_MASK)) {
            return Text.translatable("name.silly").withColor(0xff64fd).formatted(Formatting.ITALIC);
        }
        if (player.getEquippedStack(EquipmentSlot.HEAD).isOf(BulwarkMasks.FAKER_MASK)) {
            return Text.translatable("name.faker").withColor(0xFF9F4A).formatted(Formatting.ITALIC).formatted(Formatting.OBFUSCATED);
        }
        if (player.getEquippedStack(EquipmentSlot.HEAD).isOf(BulwarkMasks.WARDEN_MASK)) {
            return Text.translatable("name.warden").withColor(0x29dfeb).formatted(Formatting.ITALIC);
        }
        if (player.getEquippedStack(EquipmentSlot.HEAD).isOf(BulwarkMasks.JUDGES_VEIL)) {
            return Text.translatable("name.judge").withColor(0x036d85).formatted(Formatting.ITALIC);
        }
        if (player.getEquippedStack(EquipmentSlot.HEAD).isOf(BulwarkMasks.GNARP_MASK)) {
            return Text.translatable("name.gnarp").withColor(0x87c967).formatted(Formatting.ITALIC);
        }
        if (player.getEquippedStack(EquipmentSlot.HEAD).isOf(BulwarkMasks.KITSUNE_MASK)) {
            return Text.translatable("name.kitsune").withColor(0xD13328).formatted(Formatting.ITALIC).formatted(Formatting.OBFUSCATED);
        }
        if (player.getEquippedStack(EquipmentSlot.HEAD).isOf(BulwarkMasks.BENEFACTOR_MASK)) {
            return Text.translatable("name.benefactor").withColor(0x545E80);
        }
        if (player.getEquippedStack(EquipmentSlot.HEAD).isOf(BulwarkMasks.HUMAN_MASK)) {
            return Text.translatable("name.human").withColor(0xa81f1f);
        }
        return original;
    }

    @Inject(method = "onKilledOther", at = @At("HEAD"))
    private void bulwark$bloodiedMaskForKioBecauseImBasedLikeThat(ServerWorld world, LivingEntity other, CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity player = (PlayerEntity)(Object)this;
        if (other instanceof PlayerEntity) {

            ItemStack headStack = player.getEquippedStack(EquipmentSlot.HEAD);
            if (headStack.isOf(BulwarkMasks.BENEFACTOR_MASK)) {
                player.equipStack(EquipmentSlot.HEAD, BulwarkMasks.BLOODIED_MASK.getDefaultStack());
                headStack.decrement(1);
            }
        }
    }
}
