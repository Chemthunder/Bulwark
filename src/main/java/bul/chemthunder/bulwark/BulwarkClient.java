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
    }
}
