package bul.chemthunder.bulwark.item;

import bul.chemthunder.bulwark.index.BulwarkItems;
import bul.chemthunder.bulwark.index.BulwarkStatusEffects;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class KlaprothItem extends Item {
    public KlaprothItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos();
        BlockState state = context.getWorld().getBlockState(pos);
        ItemStack offStack = context.getPlayer().getOffHandStack();
        ItemStack mainStack = context.getPlayer().getMainHandStack();
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();

        if (state.isOf(Blocks.AMETHYST_BLOCK)) {
            //block check
            if (offStack.isOf(BulwarkItems.KLAPROTH) && mainStack.isOf(Items.GOLDEN_PICKAXE)) {
                //item check

                world.setBlockState(pos, Blocks.BUDDING_AMETHYST.getDefaultState());
                player.playSound(SoundEvents.BLOCK_ANVIL_USE, 0.25f, 5);
                player.playSound(SoundEvents.ITEM_HONEYCOMB_WAX_ON);
            }
        }

        return super.useOnBlock(context);
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
    if (entity instanceof PlayerEntity player) {
        ItemStack main = player.getMainHandStack();
        ItemStack offMain = player.getOffHandStack();

        if (main.isOf(BulwarkItems.KLAPROTH) || offMain.isOf(BulwarkItems.KLAPROTH) || main.isOf(BulwarkItems.IMPURE_KLAPROTH) || offMain.isOf(BulwarkItems.IMPURE_KLAPROTH))
            player.addStatusEffect(new StatusEffectInstance(BulwarkStatusEffects.ACTINISM, 20, 0), player);
    }

        super.inventoryTick(stack, world, entity, slot);
    }
}
