package bul.chemthunder.bulwark.block.entity;

import bul.chemthunder.bulwark.index.BulwarkBlockEntities;
import bul.chemthunder.bulwark.index.BulwarkBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RestrictorBlockEntity extends BlockEntity {
    public Type type = Type.DEFAULT;
    public int pushTicks = 30;
    public boolean use = true;

    public RestrictorBlockEntity(BlockPos pos, BlockState state) {
        super(BulwarkBlockEntities.RESTRICTOR, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, @NotNull RestrictorBlockEntity restrictor) {
        if (restrictor.pushTicks > 0) {
            restrictor.pushTicks--;
            if (restrictor.pushTicks == 0) {
                restrictor.pushTicks = 5;
                restrictor.use = true;
                world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
                restrictor.markDirty();
            }
        }

        BlockState aboveState = world.getBlockState(pos.up());

        if (world.getBlockEntity(pos.up()) instanceof RestrictorBlockEntity blockEntity) {
            if (restrictor.type != blockEntity.type) {
                updateType(world, pos, state, restrictor, blockEntity.type);
            }
        }

        // Checks what state it is, it'll check the if statement, but won't run it unless the type doesn't match. The two if statements not being combined is to avoid it swapping forever
        if (aboveState.isOf(Blocks.LODESTONE)) {
            if (restrictor.type != Type.LODESTONE) updateType(world, pos, state, restrictor, Type.LODESTONE);
        } else if (aboveState.isOf(Blocks.IRON_BLOCK)) {
            if (restrictor.type != Type.IRON_BLOCK) updateType(world, pos, state, restrictor, Type.IRON_BLOCK);
        } else if (aboveState.isOf(Blocks.SCULK_CATALYST)) {
            if (restrictor.type != Type.SCULK_CATALYST) updateType(world, pos, state, restrictor, Type.SCULK_CATALYST);
        } else if (restrictor.type != Type.DEFAULT) {
            updateType(world, pos, state, restrictor, Type.DEFAULT);
        }

        if (aboveState.isOf(BulwarkBlocks.RESTRICTOR) || aboveState.isIn(BlockTags.AIR) || aboveState.isOf(Blocks.LODESTONE) || aboveState.isOf(Blocks.IRON_BLOCK) || aboveState.isOf(Blocks.SCULK_CATALYST)) {
            Box area = new Box(pos).expand(5, 0, 5);
            List<Entity> entities = world.getEntitiesByClass(Entity.class, area, entity -> true);

            for (Entity entity : entities) {
                if (restrictor.use) {
                    if (aboveState.isOf(Blocks.LODESTONE)) {
                        if (!entity.isSneaking()) {
                            entity.onLanding();
                            entity.setVelocity(entity.getVelocity().x, 0, entity.getVelocity().z);
                            entity.addVelocity(entity.getRotationVector().x * 0.3, 1, entity.getRotationVector().z * 0.3);
                            entity.velocityModified = true;
                        }
                    } else if (aboveState.isOf(Blocks.IRON_BLOCK)) {
                        if (!entity.isSneaking()) {
                            entity.onLanding();
                            entity.setVelocity(entity.getVelocity().x, 0, entity.getVelocity().z);
                            entity.addVelocity(entity.getRotationVector().x * 0.3, -1, entity.getRotationVector().z * 0.3);
                            entity.velocityModified = true;
                        }
                    } else if (aboveState.isOf(Blocks.SCULK_CATALYST) && entity instanceof LivingEntity living) {
                        if (!entity.isSneaking()) {
                            living.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 40, 1));
                        }
                    } else if (aboveState.isIn(BlockTags.AIR)) {
                        if (!entity.isSneaking()) {
                            entity.setVelocity(area.getCenter().subtract(entity.getPos()).multiply(-0.1));
                            entity.velocityModified = true;
                        }
                    }
                }

                restrictor.use = false;
                world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
                restrictor.markDirty();
            }
        }
    }

    private static void updateType(World world, BlockPos pos, BlockState state, @NotNull RestrictorBlockEntity restrictor, Type type) {
        restrictor.type = type;
        world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
        restrictor.markDirty();
    }

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        this.type = Type.fromString(nbt.getString("type", "default"));
        this.pushTicks = nbt.getInt("pushTicks", 0);
        this.use = nbt.getBoolean("use", true);
    }

    @Override
    public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        nbt.putString("type", this.type.name);
        nbt.putInt("pushTicks", this.pushTicks);
        nbt.putBoolean("use", this.use);
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registries) {
        return createNbt(registries);
    }

    public enum Type {
        DEFAULT("default"),
        LODESTONE("lodestone"),
        IRON_BLOCK("iron_block"),
        SCULK_CATALYST("sculk_catalyst");

        public final String name;

        Type(String name) {
            this.name = name;
        }

        public static Type fromString(String name) {
            for (Type type : Type.values()) {
                if (type.name.equals(name)) {
                    return type;
                }
            }

            return Type.DEFAULT;
        }
    }
}