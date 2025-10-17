package bul.chemthunder.bulwark.index;

import bul.chemthunder.bulwark.Bulwark;
import bul.chemthunder.bulwark.item.*;
import net.acoyt.acornlib.api.items.AcornItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Function;

import static net.acoyt.acornlib.api.util.ItemUtils.modifyItemNameColor;

@SuppressWarnings("deprecation")
public interface BulwarkItems {
    Item ECLIPSE_MASK = create("eclipse_mask", EclipseMaskItem::new, new AcornItemSettings()
            .maxCount(1)

            .equippableUnswappable(EquipmentSlot.HEAD)
            .enchantable(5)
            .attributeModifiers(EclipseMaskItem.createAttributeModifiers())
    );

    Item OPERATOR_KEY = create("operator_key", OperatorKey::new, new AcornItemSettings()
            .showHand()
            .maxCount(1)
            .fireproof()
    );

    Item KLAPROTH = create("klaproth", KlaprothItem::new, new AcornItemSettings()
            .maxCount(16)
            .fireproof()
    );
    
    Item IMPURE_KLAPROTH = create("impure_klaproth", KlaprothItem::new, new AcornItemSettings()
            .fireproof()
    );

    Item ILL_BOTTLE = create("ill_bottle", IllBottleItem::new, new AcornItemSettings()
            .maxCount(16)
            .fireproof()
    );
    
    Item DENTIST = create("dentist", DentistItem::new, new AcornItemSettings()
            .maxCount(1)
            .pickaxe(Bulwark.DENTISTRY_MATERIAL, 1.5f, -2.5f)
            .fireproof()
            .enchantable(5)
            .attributeModifiers(DentistItem.createAttributeModifiers())
    );

    Item KLAPROTH_CANDY = create("klaproth_candy", CandyItem::new, new AcornItemSettings()
            .food(new FoodComponent(5, 4, false))
            .maxCount(16)
            .useRemainder(Items.PAPER)
    );

    Item POISONED_FISH = create("poisoned_fish", Item::new, new AcornItemSettings()
            .maxCount(1)
            .food(new FoodComponent(4, 1, true)).useRemainder(Items.BUCKET)
    );

    Item COMICALLY_LARGE_LOLLIPOP = create("comically_large_lollipop", ComicallyLargeLollipopItem::new, new AcornItemSettings()
            .maxCount(1)
            .food(new FoodComponent(0, 0, true)).useRemainder(Items.STICK)
    );

    Item KLAPMALLOW = create("klapmallow", KlapmallowItem::new, new AcornItemSettings()
            .maxCount(16)
            .food(new FoodComponent(1, 0.5f, true))
    );

    Item KLAPMALLOW_STICK = create("klapmallow_stick", KlapmallowItem::new, new AcornItemSettings()
            .maxCount(1)
            .food(new FoodComponent(3, 2, true)).useRemainder(Items.STICK)
    );

    Item ROASTED_KLAPMALLOW = create("roasted_klapmallow", KlapmallowItem::new, new AcornItemSettings()
            .maxCount(16)
            .food(new FoodComponent(2, 1, true))
    );

    Item ROASTED_KLAPMALLOW_STICK = create("roasted_klapmallow_stick", KlapmallowItem::new, new AcornItemSettings()
            .maxCount(1)
            .food(new FoodComponent(5, 2.5f, true)).useRemainder(Items.STICK)
    );

    Item KLAPROTH_CHUNK = create("klaproth_chunk", KlaprothItem::new, new AcornItemSettings()
            .fireproof()
    );

    Item KLAPROTH_PLATING = create("klaproth_plating", KlaprothItem::new, new AcornItemSettings()
            .fireproof()
    );

    static Item create(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        return Items.register(RegistryKey.of(RegistryKeys.ITEM, Bulwark.id(name)), factory, settings);
    }

    static void init() {
        //   modifyItemNameColor(AMARANTHINE_CLEAVER, 0x90403e);

        modifyItemNameColor(Item.fromBlock(BulwarkBlocks.DISRUPTER), 0xb02065);
        modifyItemNameColor(Item.fromBlock(BulwarkBlocks.RESTRICTOR), 0xb02065);
        modifyItemNameColor(KLAPROTH, 0x992d74);
        modifyItemNameColor(Item.fromBlock(BulwarkBlocks.TAINTED_GLASS), 0xc394e1);

        modifyItemNameColor(DENTIST, 0xff64fd);

        modifyItemNameColor(Item.fromBlock(BulwarkBlocks.LUMINANT_KLAPROTH_TUBE), 0xffbc5e);
        modifyItemNameColor(Item.fromBlock(BulwarkBlocks.RED_KLAPROTH_TUBE), 0xfd4b57);
        modifyItemNameColor(Item.fromBlock(BulwarkBlocks.SCULK_KLAPROTH_TUBE), 0x009295);
        modifyItemNameColor(Item.fromBlock(BulwarkBlocks.SILLY_KLAPROTH_TUBE), 0xffa1fe);
        modifyItemNameColor(Item.fromBlock(BulwarkBlocks.KLAPROTH_TUBE), 0xd5c3f5);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> {
            entries.addAfter(Blocks.TEST_BLOCK, OPERATOR_KEY);
        });
    }


}
