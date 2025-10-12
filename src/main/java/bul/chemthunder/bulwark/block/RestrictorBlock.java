package bul.chemthunder.bulwark.block;

import bul.chemthunder.bulwark.block.entity.RestrictorBlockEntity;
import bul.chemthunder.bulwark.index.BulwarkBlockEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RestrictorBlock extends BlockWithEntity {
    public static final MapCodec<RestrictorBlock> CODEC = createCodec(RestrictorBlock::new);

    public RestrictorBlock(Settings settings) {
        super(settings);
    }

    @Override
    public MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, BulwarkBlockEntities.RESTRICTOR, RestrictorBlockEntity::tick);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RestrictorBlockEntity(pos, state);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (world.getBlockEntity(pos) instanceof RestrictorBlockEntity restrictor) {
            restrictor.pushTicks = 30;
            world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
            restrictor.markDirty();
        }
    }
}