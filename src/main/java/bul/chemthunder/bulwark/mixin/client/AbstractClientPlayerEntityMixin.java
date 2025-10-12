package bul.chemthunder.bulwark.mixin.client;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin extends PlayerEntity {
    public AbstractClientPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

//    @Inject(method = "getSkinTextures", at = @At("RETURN"), cancellable = true)
//    private void bulwark$cloaked(CallbackInfoReturnable<SkinTextures> cir) {
//        SkinTextures defaultTextures = cir.getReturnValue();
//        MinecraftClient client = MinecraftClient.getInstance();
//        if (defaultTextures != null && client.player.getEquippedStack(EquipmentSlot.HEAD).isOf(BulwarkItems.ECLIPSE_MASK)) {
//            cir.setReturnValue(new SkinTextures(
//                    Bulwark.id("textures/entity/cloaked_wide.png"),
//                    defaultTextures.textureUrl(),
//                    null,
//                    null,
//                    SkinTextures.Model.WIDE,
//                    defaultTextures.secure()
//            ));
//        }
//
//        if (defaultTextures != null && client.player.getEquippedStack(EquipmentSlot.HEAD).isOf(BulwarkMasks.RED_SHADE_MASK)) {
//            cir.setReturnValue(new SkinTextures(
//                    Bulwark.id("textures/entity/shade_thin.png"),
//                    defaultTextures.textureUrl(),
//                    null,
//                    null,
//                    SkinTextures.Model.WIDE,
//                    defaultTextures.secure()
//            ));
//        }
//
//        if (defaultTextures != null && client.player.getEquippedStack(EquipmentSlot.HEAD).isOf(BulwarkMasks.FAKER_MASK)) {
//            cir.setReturnValue(new SkinTextures(
//                    Bulwark.id("textures/entity/faker_thin.png"),
//                    defaultTextures.textureUrl(),
//                    null,
//                    null,
//                    SkinTextures.Model.WIDE,
//                    defaultTextures.secure()
//            ));
//        }
//    }
}
