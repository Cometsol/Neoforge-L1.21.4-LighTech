package com.cometsol.lighttech.datagen;

import com.cometsol.lighttech.Blocks.ModBlocks;
import com.cometsol.lighttech.Items.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class LightTechItemTags extends ItemTagsProvider {
    public static final TagKey<Item> Clear_Crystal = forgeTag("Gem/Clear_Crystal");
    public static final TagKey<Item> STORAGEBLOCKS = forgeTag("storage_blocks");

    public LightTechItemTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, BlockTagsProvider blockTags) {
        super(packOutput, lookupProvider, blockTags.contentsGetter());
    }

    private static TagKey<Item> forgeTag(String name) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
    }

    protected void addTags(HolderLookup.Provider provider) {
        this.tag(Tags.Items.GEMS).add((Item) ModItems.CLEARCRYSTALSHARD.get());
        this.tag(Clear_Crystal).add((Item) ModItems.CLEARCRYSTALSHARD.get());
        this.tag(STORAGEBLOCKS).add((Item) ModBlocks.CLEARCRYSTAL_BLOCK_ITEM.get());
    }
}
