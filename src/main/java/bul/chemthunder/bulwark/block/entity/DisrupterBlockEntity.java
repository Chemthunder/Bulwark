package bul.chemthunder.bulwark.block.entity;

import bul.chemthunder.bulwark.Bulwark;
import bul.chemthunder.bulwark.index.BulwarkBlockEntities;
import bul.chemthunder.bulwark.index.BulwarkDamageSources;
import bul.chemthunder.bulwark.index.BulwarkItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Uuids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DisrupterBlockEntity extends BlockEntity {
    public List<UUID> ownerUuids = new ArrayList<>();
    public int pushTicks = 30;
    public boolean use = true;

    public DisrupterBlockEntity(BlockPos pos, BlockState state) {
        super(BulwarkBlockEntities.DISRUPTER, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, @NotNull DisrupterBlockEntity disrupter) {
        BlockState aboveState = world.getBlockState(pos.up());

        if (disrupter.pushTicks > 0) {
            disrupter.pushTicks--;
            if (disrupter.pushTicks == 0) {
                disrupter.pushTicks = aboveState.isOf(Blocks.LODESTONE) ? 10 : 20;
                disrupter.use = true;
                world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
                disrupter.markDirty();
            }
        }

        if (aboveState.isIn(BlockTags.AIR) || aboveState.isOf(Blocks.SCULK_SHRIEKER) || aboveState.isOf(Blocks.LODESTONE)) {
            Box area = new Box(pos).expand(aboveState.isIn(BlockTags.AIR) ? 7 : aboveState.isOf(Blocks.SCULK_SHRIEKER) ? 3 : 5);
            List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, area, entity -> true);

            for (LivingEntity entity : entities) {
                if (!entity.getOffHandStack().isOf(BulwarkItems.OPERATOR_KEY) && !disrupter.ownerUuids.contains(entity.getUuid()) && world instanceof ServerWorld serverWorld && !entity.isInvulnerableTo(serverWorld, BulwarkDamageSources.radiation(entity))) {
                    if (disrupter.use) {
                        if (aboveState.isOf(Blocks.SCULK_SHRIEKER)) {
                            entity.damage(serverWorld, BulwarkDamageSources.radiation(entity), 6);
                            serverWorld.spawnParticles(
                                    ParticleTypes.SCULK_SOUL,
                                    entity.getX(),
                                    entity.getY() + 1,
                                    entity.getZ(),
                                    15,
                                    0,
                                    0,
                                    0,
                                    0.05
                            );

                            entity.playSound(SoundEvents.BLOCK_SCULK_CATALYST_BREAK);
                        } else if (aboveState.isOf(Blocks.LODESTONE)) {
                            entity.setVelocity(area.getCenter().subtract(entity.getPos()).multiply(entity instanceof PlayerEntity ? 0.006 : 0.019));
                            entity.velocityModified = true;

                            serverWorld.spawnParticles(
                                    ParticleTypes.END_ROD,
                                    entity.getX(),
                                    entity.getY() + 1,
                                    entity.getZ(),
                                    1,
                                    0,
                                    0,
                                    0,
                                    0
                            );

                        } else if (aboveState.isIn(BlockTags.AIR)) {
                            entity.damage(serverWorld, BulwarkDamageSources.radiation(entity), 1.5f);
                            entity.setVelocity(area.getCenter().subtract(entity.getPos()).multiply(-0.1));
                            serverWorld.spawnParticles(
                                    Bulwark.DISRUPTER_HIT,
                                    entity.getX(),
                                    entity.getY() + 1,
                                    entity.getZ(),
                                    15,
                                    0,
                                    0,
                                    0,
                                    0.05
                            );


                            entity.playSound(SoundEvents.ITEM_LODESTONE_COMPASS_LOCK);
                        }

                        disrupter.use = false;
                        world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
                        disrupter.markDirty();
                    }
                }
            }
        }
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        this.ownerUuids.clear();
        this.ownerUuids.addAll(nbt.get("ownerUuids", Uuids.CODEC.listOf()).orElse(List.of()));
        this.pushTicks = nbt.getInt("pushTicks", 0);
        this.use = nbt.getBoolean("use", true);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        nbt.put("ownerUuids", Uuids.CODEC.listOf(), this.ownerUuids);
        nbt.putInt("pushTicks", this.pushTicks);
        nbt.putBoolean("use", this.use);
    }
}
