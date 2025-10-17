package bul.chemthunder.bulwark.effect;

import bul.chemthunder.bulwark.Bulwark;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

public class WarningEffect extends StatusEffect {
    public WarningEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity player) {
            world.spawnParticles(Bulwark.WARNING_PARTICLE, entity.getX(), entity.getY(), entity.getZ(), 1, 0.5, 0.9, 0.5, 0.04);
            player.sendMessage(Text.translatable("text.disrupter.hostess.fuckyou"), true);
        }
        return super.applyUpdateEffect(world, entity, amplifier);
    }

    public ParticleEffect createParticle(StatusEffectInstance effect) {
        return new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.AIR.getDefaultState());
    }

    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 15;
        return duration % i == 0;
    }
}
