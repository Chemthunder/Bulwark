package bul.chemthunder.bulwark.index;

import bul.chemthunder.bulwark.Bulwark;
import bul.chemthunder.bulwark.effect.ActinismEffect;
import bul.chemthunder.bulwark.effect.InstabilityEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

public interface BulwarkStatusEffects {
  //  RegistryEntry<StatusEffect> INSANITY = create("insanity", new InsanityEffect());

    RegistryEntry<StatusEffect> ACTINISM = create("actinism", new ActinismEffect(StatusEffectCategory.NEUTRAL, 0xbd00d8));

    RegistryEntry<StatusEffect> INSTABILITY = create("instability", new InstabilityEffect(StatusEffectCategory.NEUTRAL, 0xff64fd));

    private static RegistryEntry<StatusEffect> create(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Bulwark.id(name), statusEffect);
    }

    static void init() {
    }
}
