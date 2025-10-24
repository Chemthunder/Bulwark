package bul.chemthunder.bulwark.index;

import bul.chemthunder.bulwark.Bulwark;
import bul.chemthunder.bulwark.block.*;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;

import java.util.function.Function;

public interface BulwarkBlocks {
    Block DISRUPTER = createWithItem("disrupter", DisrupterBlock::new, AbstractBlock.Settings.copy(Blocks.OBSIDIAN)
            .sounds(BlockSoundGroup.TRIAL_SPAWNER)
            .luminance((state) -> 10)
    );

    Block RESTRICTOR = createWithItem("restrictor", RestrictorBlock::new, AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)
            .sounds(BlockSoundGroup.TRIAL_SPAWNER)
            .luminance((state) -> 10)
    );

    Block ILL_SUBSTANCE = createWithItem("ill_substance", IllSubstanceBlock::new, AbstractBlock.Settings.copy(Blocks.SCULK)
            .sounds(BlockSoundGroup.SCULK)
            .nonOpaque()
            .noCollision()
    );

    Block BUDDING_KLAPROTH = createWithItem("budding_klaproth", BuddingKlaprothBlock::new, AbstractBlock.Settings.copy(Blocks.BUDDING_AMETHYST)
            .sounds(BlockSoundGroup.AMETHYST_BLOCK)
    );

    Block OMINOUS_CATALYST = createWithItem("ominous_catalyst", Block::new, AbstractBlock.Settings.copy(Blocks.SCULK_CATALYST));

    Block TAINTED_GLASS = createWithItem("tainted_glass", TaintedGlassBlock::new, AbstractBlock.Settings.copy(Blocks.TINTED_GLASS)
            .sounds(BlockSoundGroup.GLASS)
    );

    Block CRYSTALLIZED_TAINTED_GLASS = createWithItem("crystallized_tainted_glass", CrystallizedTaintedGlass::new, AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK)
            .sounds(BlockSoundGroup.GLASS)
    );

    Block METALLIC_TAINTED_GLASS = createWithItem("metallic_tainted_glass", MetallicTaintedGlassBlock::new, AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK)
            .sounds(BlockSoundGroup.GLASS)
    );

//    Block HARM_CHEST = createWithItem("harm_chest", HarmChestBlock::new, AbstractBlock.Settings.copy(Blocks.CHEST));
//
//    Block GAZER = createWithItem("gazer", GazerBlock::new, AbstractBlock.Settings.copy(Blocks.COPPER_BULB)
//            .sounds(BlockSoundGroup.TRIAL_SPAWNER)
//            .luminance((state) -> 10)
//    );

    // budding
    Block SMALL_KLAPROTH_BUD = createWithItem("small_klaproth_bud", KlaprothClusterBlock::new, AbstractBlock.Settings.copy(Blocks.SMALL_AMETHYST_BUD));

    Block MEDIUM_KLAPROTH_BUD = createWithItem("medium_klaproth_bud", KlaprothClusterBlock::new, AbstractBlock.Settings.copy(Blocks.MEDIUM_AMETHYST_BUD));

    Block LARGE_KLAPROTH_BUD = createWithItem("large_klaproth_bud", KlaprothClusterBlock::new, AbstractBlock.Settings.copy(Blocks.LARGE_AMETHYST_BUD));

    Block KLAPROTH_CLUSTER = createWithItem("klaproth_cluster", KlaprothClusterBlock::new, AbstractBlock.Settings.copy(Blocks.AMETHYST_CLUSTER));


    // tube
    Block KLAPROTH_TUBE = createWithItem("klaproth_tube", KlaprothTubeBlock::new, AbstractBlock.Settings.copy(Blocks.LANTERN).strength(3.5F));

    Block SCULK_KLAPROTH_TUBE = createWithItem("sculk_klaproth_tube", KlaprothTubeBlock::new, AbstractBlock.Settings.copy(Blocks.LANTERN).strength(3.5F));

    Block LUMINANT_KLAPROTH_TUBE = createWithItem("luminant_klaproth_tube", KlaprothTubeBlock::new, AbstractBlock.Settings.copy(Blocks.LANTERN).strength(3.5F));

    Block SILLY_KLAPROTH_TUBE = createWithItem("silly_klaproth_tube", KlaprothTubeBlock::new, AbstractBlock.Settings.copy(Blocks.LANTERN).strength(3.5F));

    Block RED_KLAPROTH_TUBE = createWithItem("red_klaproth_tube", KlaprothTubeBlock::new, AbstractBlock.Settings.copy(Blocks.LANTERN).strength(3.5F));

//    Block EXOTIC_KLAPROTH_TUBE = createWithItem("exotic_klaproth_tube", KlaprothTubeBlock::new, AbstractBlock.Settings.copy(Blocks.LANTERN).strength(3.5f));
//
//    Block ORANGE_KLAPROTH_TUBE = createWithItem("orange_klaproth_tube", KlaprothTubeBlock::new, AbstractBlock.Settings.copy(Blocks.LANTERN).strength(3.5f));
//
//    Block GLOWING_KLAPROTH_TUBE = createWithItem("glowing_klaproth_tube", KlaprothTubeBlock::new, AbstractBlock.Settings.copy(Blocks.LANTERN).strength(3.5f));

    // klaproth variants
    Block KLAPROTH_BLOCK = createWithItem("klaproth_block", Block::new, AbstractBlock.Settings.copy(Blocks.QUARTZ_BLOCK));

    Block CUT_KLAPROTH_BLOCK = createWithItem("cut_klaproth_block", Block::new, AbstractBlock.Settings.copy(Blocks.QUARTZ_BLOCK));

    Block KLAPROTH_PILLAR = createWithItem("klaproth_pillar", PillarBlock::new, AbstractBlock.Settings.copy(Blocks.QUARTZ_PILLAR));
//
//    Block FORG_STATUE = createWithItem("forg_statue", ForgStatueBlock::new, AbstractBlock.Settings.copy(Blocks.STONE)
//            .sounds(BlockSoundGroup.STONE)
//            .nonOpaque()
//    );

    static Block create(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        return Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Bulwark.id(name)), factory, settings);
    }

    // Create and Register with an item, always
    static Block createWithItem(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        Block block = create(name, factory, settings);
        BulwarkItems.create(name, itemSettings -> new BlockItem(block, itemSettings), new Item.Settings().useBlockPrefixedTranslationKey());

        return block;
    }

    static void init() {
        // a
    }

    static void clientInit() {
        BlockRenderLayerMap.INSTANCE.putBlock(TAINTED_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                ILL_SUBSTANCE,
                KLAPROTH_CLUSTER,
                KLAPROTH_CLUSTER,
                SMALL_KLAPROTH_BUD,
                LARGE_KLAPROTH_BUD,
                MEDIUM_KLAPROTH_BUD,
                KLAPROTH_TUBE,
                RED_KLAPROTH_TUBE,
                SILLY_KLAPROTH_TUBE,
                SCULK_KLAPROTH_TUBE,
                LUMINANT_KLAPROTH_TUBE
        );
    }
}