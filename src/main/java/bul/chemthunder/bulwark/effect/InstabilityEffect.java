package bul.chemthunder.bulwark.effect;

import net.acoyt.acornlib.api.effect.UnclearableEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class InstabilityEffect extends StatusEffect implements UnclearableEffect {
    public InstabilityEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }


}
