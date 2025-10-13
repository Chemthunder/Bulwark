package bul.chemthunder.bulwark.mixin.client;

import bul.chemthunder.bulwark.Bulwark;
import bul.chemthunder.bulwark.index.BulwarkStatusEffects;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Function;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Unique private static final Identifier INSTABILITY_HEART = Bulwark.id("hud/heart/instability");

    @Inject(method = "renderHealthBar", at = @At("HEAD"), cancellable = true)
    private void renderHealthBar(DrawContext context, PlayerEntity player, int x, int y, int lines, int regeneratingHeartIndex, float maxHealth, int lastHealth, int health, int absorption, boolean blinking, CallbackInfo ci) {
        if (MinecraftClient.getInstance().getCameraEntity() instanceof LivingEntity living && living.hasStatusEffect(BulwarkStatusEffects.ACTINISM)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderFood", at = @At("HEAD"), cancellable = true)
    private void renderFood(DrawContext context, PlayerEntity player, int top, int right, CallbackInfo ci) {
        if (MinecraftClient.getInstance().getCameraEntity() instanceof LivingEntity living && living.hasStatusEffect(BulwarkStatusEffects.ACTINISM)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderHotbar", at = @At("HEAD"), cancellable = true)
    private void renderHotbar(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (MinecraftClient.getInstance().getCameraEntity() instanceof LivingEntity living && living.hasStatusEffect(BulwarkStatusEffects.ACTINISM)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderArmor", at = @At("HEAD"), cancellable = true)
    private static void renderArmor(DrawContext context, PlayerEntity player, int i, int j, int k, int x, CallbackInfo ci) {
        if (MinecraftClient.getInstance().getCameraEntity() instanceof LivingEntity living && living.hasStatusEffect(BulwarkStatusEffects.ACTINISM)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderExperienceBar", at = @At("HEAD"), cancellable = true)
    private static void renderExpBar(DrawContext context, int x, CallbackInfo ci) {
        if (MinecraftClient.getInstance().getCameraEntity() instanceof LivingEntity living && living.hasStatusEffect(BulwarkStatusEffects.ACTINISM)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderExperienceLevel", at = @At("HEAD"), cancellable = true)
    private static void renderExpLvl(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (MinecraftClient.getInstance().getCameraEntity() instanceof LivingEntity living && living.hasStatusEffect(BulwarkStatusEffects.ACTINISM)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderOverlay", at = @At("HEAD"), cancellable = true)
    private static void renderOverlay(DrawContext context, Identifier texture, float opacity, CallbackInfo ci) {
        if (MinecraftClient.getInstance().getCameraEntity() instanceof LivingEntity living && living.hasStatusEffect(BulwarkStatusEffects.ACTINISM)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderCrosshair", at = @At("HEAD"), cancellable = true)
    private static void renderCrosshair(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (MinecraftClient.getInstance().getCameraEntity() instanceof LivingEntity living && living.hasStatusEffect(BulwarkStatusEffects.ACTINISM)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderStatusEffectOverlay", at = @At("HEAD"), cancellable = true)
    private static void renderStatusEffectOverlay(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (MinecraftClient.getInstance().getCameraEntity() instanceof LivingEntity living && living.hasStatusEffect(BulwarkStatusEffects.ACTINISM)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderMainHud", at = @At("HEAD"), cancellable = true)
    private static void renderMainHudOverlay(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (MinecraftClient.getInstance().getCameraEntity() instanceof LivingEntity living && living.hasStatusEffect(BulwarkStatusEffects.ACTINISM)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderHotbarItem", at = @At("HEAD"), cancellable = true)
    private static void renderHotBarItem(DrawContext context, int x, int y, RenderTickCounter tickCounter, PlayerEntity player, ItemStack stack, int seed, CallbackInfo ci) {
        if (MinecraftClient.getInstance().getCameraEntity() instanceof LivingEntity living && living.hasStatusEffect(BulwarkStatusEffects.ACTINISM)) {
            ci.cancel();
        }
    }
    @WrapOperation(
            method = {"drawHeart"},
            at = {@At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Ljava/util/function/Function;Lnet/minecraft/util/Identifier;IIII)V"
            )}
    )
    private void bulwark$instability(DrawContext instance, Function<Identifier, RenderLayer> renderLayers, Identifier sprite, int x, int y, int width, int height, Operation<Void> original) {
        Entity camera = MinecraftClient.getInstance().getCameraEntity();
        if (camera instanceof LivingEntity living && living.hasStatusEffect(BulwarkStatusEffects.INSTABILITY)) {
            original.call(instance, renderLayers, INSTABILITY_HEART, x, y, width, height);
            return;
        }

        original.call(instance, renderLayers, sprite, x, y, width, height);
    }
}
