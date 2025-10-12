package bul.chemthunder.bulwark.item;

import bul.chemthunder.bulwark.index.BulwarkItems;
import bul.chemthunder.bulwark.index.BulwarkMasks;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

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
    }
}
