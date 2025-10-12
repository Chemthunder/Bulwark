package bul.chemthunder.bulwark.index;

import bul.chemthunder.bulwark.Bulwark;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public interface BulwarkDamageSources {
    RegistryKey<DamageType> RADIATION = of("radiation");
    RegistryKey<DamageType> MIRROR_USE = of("mirror_use");
    RegistryKey<DamageType> CAVITY = of("cavity");
    RegistryKey<DamageType> GOOPY = of("goopy");
    RegistryKey<DamageType> FISH = of("fish");
    //RegistryKey<DamageType> STEAMED = of("steamed");

    //static DamageSource steamed(LivingEntity entity) {
    //     return entity.getDamageSources().create(STEAMED);
    //}

    static DamageSource radiation(LivingEntity entity) {
        return entity.getDamageSources().create(RADIATION);
    }
    static DamageSource mirror_use(LivingEntity entity) {
        return entity.getDamageSources().create(MIRROR_USE);
    }
    static DamageSource cavity(LivingEntity entity) {
        return entity.getDamageSources().create(CAVITY);
    }
    static DamageSource goopy(LivingEntity entity) {
        return entity.getDamageSources().create(GOOPY);
    }
    static DamageSource fish(LivingEntity entity) {
        return entity.getDamageSources().create(FISH);
    }

    private static RegistryKey<DamageType> of(String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Bulwark.id(name));
    }
}