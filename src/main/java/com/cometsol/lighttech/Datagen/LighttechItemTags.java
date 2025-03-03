package com.cometsol.lighttech.Datagen;

import com.cometsol.lighttech.Common.Items.ModItems;
import com.cometsol.lighttech.LightTech;
import com.cometsol.lighttech.Util.intergration.justdirethings.justdirethingsintergration;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;

public class LighttechItemTags extends ItemTagsProvider {
    public static final TagKey<Item> WRENCHES = forgeTag("wrenches");
    public static final TagKey<Item> GEMS = forgeTag("gems");
    public static final TagKey<Item> AUTO_SMELT_DENY = ItemTags.create(ResourceLocation.fromNamespaceAndPath("justdirethings", "auto_smelt_deny"));
    public static final TagKey<Item> ATTUNED = ItemTags.create(ResourceLocation.fromNamespaceAndPath("lighttech", "attuned"));


    private static TagKey<Item> forgeTag(String name) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
    }

    public LighttechItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, LighttechBlockTags blockTags) {
        super(output, lookupProvider, blockTags.contentsGetter(), LightTech.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(GEMS)
                .add(ModItems.CLEARCRYSTALSHARD.get())
                .add(ModItems.FIERYCRYSTALSHARD.get())
                .add(ModItems.VOIDCRYSTALSHARD.get())
                .replace(false);

        if(justdirethingsintergration.isLoaded()){
            tag(AUTO_SMELT_DENY)
                    .add(ModItems.CLEARCRYSTALSHARD.get())
                    .add(ModItems.FIERYCRYSTALSHARD.get())
                    .add(ModItems.VOIDCRYSTALSHARD.get())
                    .replace(false);
        }
    }
}
