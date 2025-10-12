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

public interface MasksItemGroup {
    RegistryKey<ItemGroup> GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Bulwark.id("masks"));
    ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(BulwarkItems.ECLIPSE_MASK))
            .displayName(Text.translatable("itemGroup.masks").styled(style -> style.withColor(0xbd00d8)))
            .build();

    static void init() {
        Registry.register(Registries.ITEM_GROUP, GROUP_KEY, ITEM_GROUP);

        ItemGroupEvents.modifyEntriesEvent(GROUP_KEY).register(MasksItemGroup::addEntries);

    }

    private static void addEntries(FabricItemGroupEntries itemGroup) {
        itemGroup.add(BulwarkItems.ECLIPSE_MASK);
        itemGroup.add(BulwarkMasks.FAKER_MASK);
        itemGroup.add(BulwarkMasks.RED_SHADE_MASK);
        itemGroup.add(BulwarkMasks.SILLY_MASK);
        itemGroup.add(BulwarkMasks.WARDEN_MASK);
    }
}
