package bul.chemthunder.bulwark.index;

import bul.chemthunder.bulwark.Bulwark;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

public interface BulwarkItemGroups {
    RegistryKey<ItemGroup> GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Bulwark.id("bulwark"));
    ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(BulwarkItems.KLAPROTH))
            .displayName(Text.translatable("itemGroup.bulwark").styled(style -> style.withColor(0xbd00d8)))
            .build();

    static void init() {
        Registry.register(Registries.ITEM_GROUP, GROUP_KEY, ITEM_GROUP);

        ItemGroupEvents.modifyEntriesEvent(GROUP_KEY).register(BulwarkItemGroups::addEntries);

    }

    private static void addEntries(FabricItemGroupEntries itemGroup) {
        itemGroup.add(BulwarkBlocks.DISRUPTER);
        itemGroup.add(BulwarkBlocks.RESTRICTOR);
        itemGroup.add(BulwarkItems.ILL_BOTTLE);
        itemGroup.add(BulwarkBlocks.ILL_SUBSTANCE);
        itemGroup.add(BulwarkItems.KLAPROTH);
        itemGroup.add(BulwarkBlocks.TAINTED_GLASS);
        itemGroup.add(BulwarkBlocks.SMALL_KLAPROTH_BUD);
        itemGroup.add(BulwarkBlocks.MEDIUM_KLAPROTH_BUD);
        itemGroup.add(BulwarkBlocks.LARGE_KLAPROTH_BUD);
        itemGroup.add(BulwarkBlocks.KLAPROTH_CLUSTER);
        itemGroup.add(BulwarkItems.IMPURE_KLAPROTH);
        itemGroup.add(BulwarkBlocks.KLAPROTH_TUBE);
        itemGroup.add(BulwarkBlocks.SILLY_KLAPROTH_TUBE);
        itemGroup.add(BulwarkBlocks.RED_KLAPROTH_TUBE);
        itemGroup.add(BulwarkBlocks.SCULK_KLAPROTH_TUBE);
        itemGroup.add(BulwarkBlocks.LUMINANT_KLAPROTH_TUBE);
        itemGroup.add(BulwarkBlocks.KLAPROTH_BLOCK);
        itemGroup.add(BulwarkBlocks.CUT_KLAPROTH_BLOCK);
        itemGroup.add(BulwarkBlocks.KLAPROTH_PILLAR);
        itemGroup.add(BulwarkItems.KLAPROTH_CANDY);
        itemGroup.add(BulwarkItems.KLAPMALLOW);
        itemGroup.add(BulwarkItems.KLAPMALLOW_STICK);
        itemGroup.add(BulwarkItems.ROASTED_KLAPMALLOW);
        itemGroup.add(BulwarkItems.ROASTED_KLAPMALLOW_STICK);

        //testing
//        itemGroup.add(BulwarkBlocks.BUDDING_KLAPROTH);
//        itemGroup.add(BulwarkBlocks.OMINOUS_CATALYST);
//        itemGroup.add(BulwarkItems.DENTIST);
    }
}
