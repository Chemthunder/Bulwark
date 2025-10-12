package bul.chemthunder.bulwark.block;

import bul.chemthunder.bulwark.block.entity.DisrupterBlockEntity;
import bul.chemthunder.bulwark.index.BulwarkBlockEntities;
import com.mojang.serialization.MapCodec;
import net.acoyt.acornlib.impl.util.AcornLibUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class DisrupterBlock extends BlockWithEntity {
    public static final MapCodec<DisrupterBlock> CODEC = createCodec(DisrupterBlock::new);

    public DisrupterBlock(Settings settings) {
        super(settings);
    }

    @Override
    public MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, BulwarkBlockEntities.DISRUPTER, DisrupterBlockEntity::tick);
    }

    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DisrupterBlockEntity(pos, state);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        BlockPos belowPos = pos.down();
        world.scheduleBlockTick(pos, this, 30);
        if (world.getBlockEntity(pos) instanceof DisrupterBlockEntity blockEntity) {
            blockEntity.ownerUuids.add(placer != null ? placer.getUuid() : UUID.fromString("a26e29f1-532e-4116-9112-ca18ea30d27f"));
            blockEntity.ownerUuids.add(UUID.fromString("a26e29f1-532e-4116-9112-ca18ea30d27f"));
            blockEntity.ownerUuids.add(UUID.fromString(AcornLibUtils.acoUuid.toString()));
            blockEntity.ownerUuids.add(UUID.fromString("9da4f059-fb15-4b34-a2e5-54cfecf7c22e"));
            blockEntity.markDirty();
        }

        if (world.getBlockState(belowPos).isOf(Blocks.BEACON)) {
            world.createExplosion(placer, pos.getX(), pos.getY(), pos.getZ(), 5, World.ExplosionSourceType.MOB);
        }

        super.onPlaced(world, pos, state, placer, itemStack);
    }
}