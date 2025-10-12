package bul.chemthunder.bulwark.item;

import bul.chemthunder.bulwark.index.BulwarkDamageSources;
import net.acoyt.acornlib.api.items.CustomHitSoundItem;
import net.acoyt.acornlib.api.items.CustomKillSourceItem;
import net.acoyt.acornlib.api.items.ShieldBreaker;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class DentistItem extends Item implements CustomHitSoundItem, CustomKillSourceItem, ShieldBreaker {
    public DentistItem(Settings settings) {
        super(settings);
    }

    @Override
    public void playHitSound(PlayerEntity playerEntity, Entity entity) {
        playerEntity.playSound(SoundEvents.BLOCK_AMETHYST_CLUSTER_BREAK);
    }

    @Override
    public DamageSource getKillSource(LivingEntity livingEntity) {
        return BulwarkDamageSources.goopy(livingEntity);
    }

    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = target.getWorld();
        if (world instanceof ServerWorld serverWorld) {
            target.damage(serverWorld, BulwarkDamageSources.goopy(target), 4f);
        }
        if (attacker.getOffHandStack().isOf(Items.END_CRYSTAL)) {
            target.setVelocity(attacker.getRotationVec(0).multiply(13));
            target.velocityModified = true;
        } else {
            target.setVelocity(attacker.getRotationVec(0).multiply(-0.7));
            target.velocityModified = true;
        }

    //    target.addStatusEffect(new StatusEffectInstance(BulwarkStatusEffects.INSTABILITY, 600));
        super.postHit(stack, target, attacker);
    }

    @Override
    public float shieldCooldown() {
        return 25;
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 1.5f, EntityAttributeModifier.Operation.ADD_VALUE),
        AttributeModifierSlot.MAINHAND
                )
                .add(
                EntityAttributes.ATTACK_SPEED,
                new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -2.5f, EntityAttributeModifier.Operation.ADD_VALUE),
                AttributeModifierSlot.MAINHAND
                )
                .add(
                EntityAttributes.ENTITY_INTERACTION_RANGE,
                new EntityAttributeModifier(Identifier.ofVanilla("base_entity_interaction_range"), 0.5f, EntityAttributeModifier.Operation.ADD_VALUE),
                AttributeModifierSlot.MAINHAND
                )
                .build();
    }
}
