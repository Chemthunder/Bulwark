package bul.chemthunder.bulwark.block;

import bul.chemthunder.bulwark.block.entity.DisrupterBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class GazerBlock extends Block {
    public static final MapCodec<GazerBlock> CODEC = createCodec(GazerBlock::new);

    public GazerBlock(Settings settings) {
        super(settings);
    }


//    @Override
//    public MapCodec<? extends BlockWithEntity> getCodec() {
//        return CODEC;
//    }

//    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
//        return new GazerBlockEntity(pos, state);
//    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        world.scheduleBlockTick(pos, this, 30);
        if (world.getBlockEntity(pos) instanceof DisrupterBlockEntity blockEntity) {
            blockEntity.ownerUuids.add(placer != null ? placer.getUuid() : UUID.fromString("a26e29f1-532e-4116-9112-ca18ea30d27f"));
            blockEntity.ownerUuids.add(UUID.fromString("a26e29f1-532e-4116-9112-ca18ea30d27f"));
            blockEntity.markDirty();
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        Box area = new Box(pos).expand(7);
        List<LivingEntity> entities = world.getEntitiesByClass(
                LivingEntity.class, area,
                entity -> true
        );

        for (LivingEntity entity : entities) {
            if (!entity.isSneaking()) {
                if (!(world.getBlockEntity(pos) instanceof DisrupterBlockEntity blockEntity && blockEntity.ownerUuids.contains(entity.getUuid()))) {
                    entity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 600));
                }
            }
        }
        world.scheduleBlockTick(pos, this, 60); // 1 second later
        super.scheduledTick(state, world, pos, random);
    }
}
