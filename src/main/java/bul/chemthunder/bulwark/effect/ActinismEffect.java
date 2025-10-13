package bul.chemthunder.bulwark.effect;

import bul.chemthunder.bulwark.Bulwark;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import org.jetbrains.annotations.Nullable;

public class ActinismEffect extends StatusEffect {
    public ActinismEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        world.spawnParticles(Bulwark.ACTINISM, entity.getX(), entity.getY(), entity.getZ(), 1, 0.5, 0.7, 0.5, 0.04);
        return super.applyUpdateEffect(world, entity, amplifier);
    }

    public ParticleEffect createParticle(StatusEffectInstance effect) {
        return new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.AIR.getDefaultState());
    }

    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 5;
        return duration % i == 0;
    }

    @Override
    public void applyInstantEffect(ServerWorld world, @Nullable Entity effectEntity, @Nullable Entity attacker, LivingEntity entity, int amplifier, double proximity) {
        entity.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE);
        entity.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK);
        entity.playSound(SoundEvents.BLOCK_BEACON_ACTIVATE);
        super.applyInstantEffect(world, effectEntity, attacker, entity, amplifier, proximity);
    }
}
