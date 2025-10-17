package bul.chemthunder.bulwark.index;

import bul.chemthunder.bulwark.Bulwark;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public interface BulwarkDamageSources {
    RegistryKey<DamageType> RADIATION = of("radiation");
    RegistryKey<DamageType> GOOPY = of("goopy");
    //RegistryKey<DamageType> STEAMED = of("steamed");

    //static DamageSource steamed(LivingEntity entity) {
    //     return entity.getDamageSources().create(STEAMED);
    //}

    static DamageSource radiation(LivingEntity entity) {
        return entity.getDamageSources().create(RADIATION);
    }
    static DamageSource goopy(LivingEntity entity) {
        return entity.getDamageSources().create(GOOPY);
    }

    private static RegistryKey<DamageType> of(String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Bulwark.id(name));
    }
}