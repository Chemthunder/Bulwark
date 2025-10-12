package bul.chemthunder.bulwark.index;

import bul.chemthunder.bulwark.Bulwark;
import bul.chemthunder.bulwark.block.entity.DisrupterBlockEntity;
import bul.chemthunder.bulwark.block.entity.RestrictorBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public interface BulwarkBlockEntities {
    Map<BlockEntityType<?>, Identifier> BLOCK_ENTITIES = new LinkedHashMap<>();

    BlockEntityType<DisrupterBlockEntity> DISRUPTER = create("disrupter", FabricBlockEntityTypeBuilder
            .create(DisrupterBlockEntity::new)
            .addBlocks(BulwarkBlocks.DISRUPTER)
            .build());

    BlockEntityType<RestrictorBlockEntity> RESTRICTOR = create("restrictor", FabricBlockEntityTypeBuilder
            .create(RestrictorBlockEntity::new)
            .addBlocks(BulwarkBlocks.RESTRICTOR)
            .build());

//    BlockEntityType<GazerBlockEntity> GAZER = create("gazer", FabricBlockEntityTypeBuilder
//            .create(GazerBlockEntity::new)
//            .addBlocks(BulwarkBlocks.GAZER)
//            .build());

    private static <T extends BlockEntity> BlockEntityType<T> create(String name, BlockEntityType<T> blockEntity) {
        BLOCK_ENTITIES.put(blockEntity, Bulwark.id(name));
        return blockEntity;
    }

    static void init() {
        BLOCK_ENTITIES.keySet().forEach(blockEntity -> {
            Registry.register(Registries.BLOCK_ENTITY_TYPE, BLOCK_ENTITIES.get(blockEntity), blockEntity);
        });
    }
}
