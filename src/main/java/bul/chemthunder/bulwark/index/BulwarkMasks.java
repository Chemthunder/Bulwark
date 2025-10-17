package bul.chemthunder.bulwark.index;

import bul.chemthunder.bulwark.Bulwark;
import bul.chemthunder.bulwark.item.EclipseMaskItem;
import net.acoyt.acornlib.api.items.AcornItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Function;

public interface BulwarkMasks {

    Item RED_SHADE_MASK = create("red_shade_mask", EclipseMaskItem::new, new AcornItemSettings()
            .enchantable(5)
            .fireproof()
            .maxCount(1)
            .equippableUnswappable(EquipmentSlot.HEAD)
            .attributeModifiers(EclipseMaskItem.createAttributeModifiers())
    );

    Item FAKER_MASK = create("faker_mask", EclipseMaskItem::new, new AcornItemSettings()
            .enchantable(5)
            .fireproof()
            .maxCount(1)
            .equippableUnswappable(EquipmentSlot.HEAD)
            .attributeModifiers(EclipseMaskItem.createAttributeModifiers())
    );

    Item SILLY_MASK = create("silly_mask", EclipseMaskItem::new, new AcornItemSettings()
            .enchantable(5)
            .fireproof()
            .maxCount(1)
            .equippableUnswappable(EquipmentSlot.HEAD)
            .attributeModifiers(EclipseMaskItem.createAttributeModifiers())
    );

    Item WARDEN_MASK = create("warden_mask", EclipseMaskItem::new, new AcornItemSettings()
            .enchantable(5)
            .fireproof()
            .maxCount(1)
            .equippableUnswappable(EquipmentSlot.HEAD)
            .attributeModifiers(EclipseMaskItem.createAttributeModifiers())
    );

    Item JUDGES_VEIL = create("judges_veil", EclipseMaskItem::new, new AcornItemSettings()
            .enchantable(5)
            .fireproof()
            .maxCount(1)
            .equippableUnswappable(EquipmentSlot.HEAD)
            .attributeModifiers(EclipseMaskItem.createAttributeModifiers())
    );

    Item GNARP_MASK = create("gnarp_mask", EclipseMaskItem::new, new AcornItemSettings()
            .enchantable(5)
            .fireproof()
            .maxCount(1)
            .equippableUnswappable(EquipmentSlot.HEAD)
            .attributeModifiers(EclipseMaskItem.createAttributeModifiers())
    );

    Item KITSUNE_MASK = create("kitsune_mask", EclipseMaskItem::new, new AcornItemSettings()
            .enchantable(5)
            .fireproof()
            .maxCount(1)
            .equippableUnswappable(EquipmentSlot.HEAD)
            .attributeModifiers(EclipseMaskItem.createAttributeModifiers())
    );

    Item BENEFACTOR_MASK = create("benefactor_mask", EclipseMaskItem::new, new AcornItemSettings()
            .enchantable(5)
            .fireproof()
            .maxCount(1)
            .equippableUnswappable(EquipmentSlot.HEAD)
            .attributeModifiers(EclipseMaskItem.createAttributeModifiers())
    );

    Item BLOODIED_MASK = create("bloodied_mask", EclipseMaskItem::new, new AcornItemSettings()
            .enchantable(5)
            .fireproof()
            .maxCount(1)
            .equippableUnswappable(EquipmentSlot.HEAD)
            .attributeModifiers(EclipseMaskItem.createAttributeModifiers())
    );

    Item HUMAN_MASK = create("human_mask", EclipseMaskItem::new, new AcornItemSettings()
            .enchantable(5)
            .fireproof()
            .maxCount(1)
            .equippableUnswappable(EquipmentSlot.HEAD)
            .attributeModifiers(EclipseMaskItem.createAttributeModifiers())
    );

    Item TAB_ICON = create("tab_icon", Item::new, new AcornItemSettings()
            .maxCount(1)
    );

    static Item create(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        return Items.register(RegistryKey.of(RegistryKeys.ITEM, Bulwark.id(name)), factory, settings);
    }

    static void init() {
    }
}
