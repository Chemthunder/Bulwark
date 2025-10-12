package bul.chemthunder.bulwark.item;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class OperatorKey extends Item {
    public OperatorKey(Settings settings) {
        super(settings);
    }

    @SuppressWarnings("deprecation")
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.bulwark.operator_key.desc").styled(style -> style.withColor(0xff005d)).formatted(Formatting.ITALIC));
    }


    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        if (entity instanceof PlayerEntity player) {
            if (player.getOffHandStack().isOf(this)) {
                //player.playSoundToPlayer(BulwarkSoundEvents.CLOWN, SoundCategory.NEUTRAL, 5, 5);
            }
        }
        super.inventoryTick(stack, world, entity, slot);
    }
}
