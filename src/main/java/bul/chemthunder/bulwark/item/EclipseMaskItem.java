package bul.chemthunder.bulwark.item;

import bul.chemthunder.bulwark.index.BulwarkItems;
import bul.chemthunder.bulwark.index.BulwarkMasks;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemStackSet;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class EclipseMaskItem extends Item {
    public EclipseMaskItem(Settings settings) {
        super(settings);
    }

    public Text getName(ItemStack stack) {
        return Text.translatable("item.bulwark.mask");
    }

    @SuppressWarnings("deprecation")
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        if (stack.isOf(BulwarkItems.ECLIPSE_MASK)) {
            textConsumer.accept(Text.translatable("item.bulwark.mask.eclipse").styled(style -> style.withColor(0xbd00d8)));
        }
        if (stack.isOf(BulwarkMasks.FAKER_MASK)) {
            textConsumer.accept(Text.translatable("item.bulwark.mask.faker").styled(style -> style.withColor(0xf5ad42)));
        }
        if (stack.isOf(BulwarkMasks.RED_SHADE_MASK)) {
            textConsumer.accept(Text.translatable("item.bulwark.mask.red").styled(style -> style.withColor(0xff003c)));
        }
        if (stack.isOf(BulwarkMasks.SILLY_MASK)) {
            textConsumer.accept(Text.translatable("item.bulwark.mask.silly").styled(style -> style.withColor(0xff64fd)));
        }
        if (stack.isOf(BulwarkMasks.WARDEN_MASK)) {
            textConsumer.accept(Text.translatable("item.bulwark.mask.warden").styled(style -> style.withColor(0x29dfeb)));
        }
        if (stack.isOf(BulwarkMasks.JUDGES_VEIL)) {
            textConsumer.accept(Text.translatable("item.bulwark.mask.judge").styled(style -> style.withColor(0x036d85)));
        }
        if (stack.isOf(BulwarkMasks.GNARP_MASK)) {
            textConsumer.accept(Text.translatable("item.bulwark.mask.gnarp").styled(style -> style.withColor(0x87c967)));
        }
        if (stack.isOf(BulwarkMasks.KITSUNE_MASK)) {
            textConsumer.accept(Text.translatable("item.bulwark.mask.kitsune").styled(style -> style.withColor(0xD13328)));
        }
        if (stack.isOf(BulwarkMasks.BENEFACTOR_MASK)) {
            textConsumer.accept(Text.translatable("item.bulwark.mask.benefactor").styled(style -> style.withColor(0x545E80)));
        }
        if (stack.isOf(BulwarkMasks.BLOODIED_MASK)) {
            textConsumer.accept(Text.translatable("item.bulwark.mask.bloodied_1").styled(style -> style.withColor(0x3f131c)));
            textConsumer.accept(Text.translatable("item.bulwark.mask.bloodied_2").styled(style -> style.withColor(0x3f131c)));
        }
        if (stack.isOf(BulwarkMasks.HUMAN_MASK)) {
            textConsumer.accept(Text.translatable("item.bulwark.mask.human").styled(style -> style.withColor(0xa81f1f)));
        }
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.ARMOR,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 2f, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.HEAD
                )
                .add(
                        EntityAttributes.ARMOR_TOUGHNESS,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -1.5f, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.HEAD
                )
                .build();
    }
}
