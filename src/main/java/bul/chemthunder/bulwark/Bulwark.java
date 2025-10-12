package bul.chemthunder.bulwark;

import bul.chemthunder.bulwark.datagen.BulwarkItemTagProvider;
import bul.chemthunder.bulwark.index.*;
import com.google.common.collect.ImmutableSet;
import net.acoyt.acornlib.api.ALib;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.poi.PointOfInterest;
import net.minecraft.world.poi.PointOfInterestType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bulwark implements ModInitializer {
    public static final String MOD_ID = "bulwark";

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final SimpleParticleType ACTINISM = FabricParticleTypes.simple();
    public static final SimpleParticleType DISRUPTER_HIT = FabricParticleTypes.simple();
    public static final SimpleParticleType SPARKLE_BASE = FabricParticleTypes.simple();
    public static final SimpleParticleType SPARKLE_LUMI = FabricParticleTypes.simple();
    public static final SimpleParticleType SPARKLE_RED = FabricParticleTypes.simple();
    public static final SimpleParticleType SPARKLE_SCULK = FabricParticleTypes.simple();
    public static final SimpleParticleType SPARKLE_SILLY_1 = FabricParticleTypes.simple();
    public static final SimpleParticleType SPARKLE_SILLY_2 = FabricParticleTypes.simple();

    public static final Potion TATER_POTION =
            Registry.register(
                    Registries.POTION,
                    Identifier.of(Bulwark.MOD_ID, "actinism"),
                    new Potion("actinism",
                            new StatusEffectInstance(
                                    BulwarkStatusEffects.ACTINISM,
                                    3600,
                                    0)));

    public static final ToolMaterial DENTISTRY_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            4550,
            25.5F,
            0F,
            22,
            BulwarkItemTagProvider.DENTISTRY
    );

    @Override
    public void onInitialize() {
        BulwarkItems.init();
        BulwarkItemGroups.init();
        BulwarkEnchantments.init();
        BulwarkBlocks.init();
        BulwarkStatusEffects.init();
        BulwarkBlockEntities.init();
        MasksItemGroup.init();
        BulwarkMasks.init();

        ServerLivingEntityEvents.AFTER_DAMAGE.register((living, source, baseDamageTaken, damageTaken, blocked) -> {
            // Tracing
            if (living.getEquippedStack(EquipmentSlot.HEAD).isOf(BulwarkItems.ECLIPSE_MASK) || living.getEquippedStack(EquipmentSlot.HEAD).isOf(BulwarkMasks.FAKER_MASK) || living.getEquippedStack(EquipmentSlot.HEAD).isOf(BulwarkMasks.RED_SHADE_MASK) || living.getEquippedStack(EquipmentSlot.HEAD).isOf(BulwarkMasks.SILLY_MASK) || living.getEquippedStack(EquipmentSlot.HEAD).isOf(BulwarkMasks.WARDEN_MASK)) {
                if (EnchantmentHelper.hasAnyEnchantmentsWith(living.getEquippedStack(EquipmentSlot.HEAD), BulwarkEnchantments.TRACING)) {

                    LivingEntity attacker = living.getAttacker();
                    World world = living.getWorld();
                    if (attacker instanceof LivingEntity) {
                        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 600, 0, false, false, false));
                        if (world instanceof ServerWorld serverWorld) {
                            serverWorld.spawnParticles(ParticleTypes.CHERRY_LEAVES, attacker.getX(), attacker.getY() + 1, attacker.getZ(), 15, 0, 0, 0, 0.05);
                        }

                    }
                }
            }
        });

        FuelRegistryEvents.BUILD.register(((builder, context) -> {
            builder.add(BulwarkItems.KLAPROTH, 432000);
        }));

        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(MOD_ID, "actinism"), ACTINISM);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(MOD_ID, "disrupter_hit"), DISRUPTER_HIT);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(MOD_ID, "tube_sparkle_base"), SPARKLE_BASE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(MOD_ID, "tube_sparkle_lumi"), SPARKLE_LUMI);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(MOD_ID, "tube_sparkle_red"), SPARKLE_RED);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(MOD_ID, "tube_sparkle_sculk"), SPARKLE_SCULK);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(MOD_ID, "tube_sparkle_silly_1"), SPARKLE_SILLY_1);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(MOD_ID, "tube_sparkle_silly_2"), SPARKLE_SILLY_2);

        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(
                    // Input potion.
                    Potions.WATER,
                    // Ingredient
                    BulwarkItems.KLAPROTH,
                    // Output potion.
                    Registries.POTION.getEntry(TATER_POTION)
            );
        });
//        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
//            builder.registerItemRecipe(Items.POTION.asItem(), BulwarkItems.ILL_BOTTLE, Items.OMINOUS_BOTTLE);
//        });

        ALib.registerModIcon(MOD_ID, Bulwark.id("lil_guy_but_he_sparkles.png"));
        ALib.registerModMenu(MOD_ID, 0xbd00d8);

        LOGGER.info("I'll be waiting, friend.");
    }
}