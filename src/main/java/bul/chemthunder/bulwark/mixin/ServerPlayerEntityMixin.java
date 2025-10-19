package bul.chemthunder.bulwark.mixin;

import bul.chemthunder.bulwark.index.BulwarkItems;
import bul.chemthunder.bulwark.index.BulwarkMasks;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {
    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Inject(method = "getPlayerListName", at = @At("HEAD"), cancellable = true)
    private void replaceNameOnTabList(CallbackInfoReturnable<Text> cir) {
        ServerPlayerEntity player = (ServerPlayerEntity)(Object)this;
        if (player.getEquippedStack(EquipmentSlot.HEAD).isOf(BulwarkItems.ECLIPSE_MASK)) {
                cir.setReturnValue(Text.translatable("name.mask").withColor(0x481b52).formatted(Formatting.ITALIC).formatted(Formatting.OBFUSCATED));
        }
        if (player.getEquippedStack(EquipmentSlot.HEAD).isOf(BulwarkMasks.RED_SHADE_MASK)) {
            cir.setReturnValue(Text.translatable("name.shade").withColor(0xff003c).formatted(Formatting.ITALIC).formatted(Formatting.OBFUSCATED));
        }
        if (player.getEquippedStack(EquipmentSlot.HEAD).isOf(BulwarkMasks.FAKER_MASK)) {
            cir.setReturnValue(Text.translatable("name.faker").withColor(0xFF9F4A).formatted(Formatting.ITALIC).formatted(Formatting.OBFUSCATED));
        }
        if (player.getEquippedStack(EquipmentSlot.HEAD).isOf(BulwarkMasks.SILLY_MASK)) {
            cir.setReturnValue(Text.translatable("name.silly").withColor(0xff64fd).formatted(Formatting.ITALIC).formatted(Formatting.OBFUSCATED));
        }
        if (player.getEquippedStack(EquipmentSlot.HEAD).isOf(BulwarkMasks.WARDEN_MASK)) {
            cir.setReturnValue(Text.translatable("name.warden").withColor(0x29dfeb).formatted(Formatting.ITALIC));
        }
        if (player.getEquippedStack(EquipmentSlot.HEAD).isOf(BulwarkMasks.JUDGES_VEIL)) {
            cir.setReturnValue(Text.translatable("name.judge").withColor(0x036d85).formatted(Formatting.ITALIC));
        }
        if (player.getEquippedStack(EquipmentSlot.HEAD).isOf(BulwarkMasks.GNARP_MASK)) {
            cir.setReturnValue(Text.translatable("name.gnarp").withColor(0x87c967).formatted(Formatting.ITALIC));
        }
        if (player.getEquippedStack(EquipmentSlot.HEAD).isOf(BulwarkMasks.KITSUNE_MASK)) {
            cir.setReturnValue(Text.translatable("name.kitsune").withColor(0xD13328).formatted(Formatting.OBFUSCATED).formatted(Formatting.ITALIC));
        }
        if (player.getEquippedStack(EquipmentSlot.HEAD).isOf(BulwarkMasks.BENEFACTOR_MASK)) {
            cir.setReturnValue(Text.translatable("name.benefactor").withColor(0x545E80));
        }
        if (player.getEquippedStack(EquipmentSlot.HEAD).isOf(BulwarkMasks.HUMAN_MASK)) {
            cir.setReturnValue(Text.translatable("name.human").withColor(0xa81f1f));
        }
        if (player.getEquippedStack(EquipmentSlot.HEAD).isOf(BulwarkMasks.GORED_MASK)) {
            cir.setReturnValue(Text.translatable("name.gored").withColor(0xef3e8b).formatted(Formatting.OBFUSCATED));
        }
    }
}