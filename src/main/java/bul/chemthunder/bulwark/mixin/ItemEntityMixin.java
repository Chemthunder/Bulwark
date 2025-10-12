package bul.chemthunder.bulwark.mixin;

import bul.chemthunder.bulwark.index.BulwarkItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.Ownable;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity implements Ownable {
    @Shadow public abstract ItemStack getStack();

    public ItemEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    protected void onBlockCollision(BlockState state) {
        if (state.isOf(Blocks.LAVA) && this.getStack().getItem().equals(BulwarkItems.IMPURE_KLAPROTH)) {
            if (getWorld() instanceof ServerWorld serverWorld) {
                this.dropStack(serverWorld, new ItemStack(BulwarkItems.KLAPROTH, this.getStack().getCount()));
                this.discard();
                this.playSound(SoundEvents.BLOCK_LAVA_EXTINGUISH, 1, 1);
                this.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE, 1, 1);
            }
        }
    }
}
