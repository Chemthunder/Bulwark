package bul.chemthunder.bulwark.datagen;

import bul.chemthunder.bulwark.Bulwark;
import bul.chemthunder.bulwark.index.BulwarkItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class BulwarkItemTagProvider extends FabricTagProvider<Item> {

    public static final TagKey<Item> DENTISTRY = TagKey.of(RegistryKeys.ITEM, Identifier.of(Bulwark.MOD_ID, "dentistry"));

    public BulwarkItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ITEM, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        getOrCreateTagBuilder(DENTISTRY)
                .add(BulwarkItems.KLAPROTH)
                .add(BulwarkItems.IMPURE_KLAPROTH)
                .setReplace(true);
    }

}
