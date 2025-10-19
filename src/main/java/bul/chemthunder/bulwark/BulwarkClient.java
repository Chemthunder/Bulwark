package bul.chemthunder.bulwark;

import bul.chemthunder.bulwark.index.BulwarkBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.EndRodParticle;

public class BulwarkClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BulwarkBlocks.clientInit();

        ParticleFactoryRegistry.getInstance().register(Bulwark.ACTINISM, EndRodParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(Bulwark.DISRUPTER_HIT, EndRodParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(Bulwark.WARNING_PARTICLE, EndRodParticle.Factory::new);

        ParticleFactoryRegistry.getInstance().register(Bulwark.SPARKLE_LUMI, EndRodParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(Bulwark.SPARKLE_BASE, EndRodParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(Bulwark.SPARKLE_RED, EndRodParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(Bulwark.SPARKLE_SCULK, EndRodParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(Bulwark.SPARKLE_SILLY_1, EndRodParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(Bulwark.SPARKLE_SILLY_2, EndRodParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(Bulwark.SPARKLE_GLOWING, EndRodParticle.Factory::new);
    }
}
