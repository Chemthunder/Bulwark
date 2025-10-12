package bul.chemthunder.bulwark.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class ComicallyLargeLollipopItem extends Item {
    public ComicallyLargeLollipopItem(Settings settings) {
        super(settings);
    }


    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 1;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof PlayerEntity player) {
            player.sendMessage(Text.translatable("text.lollipop_use").formatted(Formatting.ITALIC).withColor(0x481b52), false);
        }
        return super.finishUsing(stack, world, user);
    }
}
