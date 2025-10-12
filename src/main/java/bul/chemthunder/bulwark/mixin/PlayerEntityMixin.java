package bul.chemthunder.bulwark.mixin;

import bul.chemthunder.bulwark.index.BulwarkItems;
import bul.chemthunder.bulwark.index.BulwarkMasks;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

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

        return original;
    }
}
