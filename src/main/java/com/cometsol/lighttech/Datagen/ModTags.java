package com.cometsol.lighttech.Datagen;

import com.cometsol.lighttech.LightTech;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;


public class ModTags {

    public static class Blocks {

        public static final TagKey<Block> ATTUNED = createTag("attuned");

        public static final TagKey<Block> ECLIPSEGATE_DENY = addTag("eclipsegate_deny");

        public static TagKey<Block> createTag(String name){
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(LightTech.MODID, name));
        }
        public static TagKey<Block> addTag(String name){
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath("justdirethings", name));
        }
    }

    public static class Items {

        public static final TagKey<Item> AUTO_SMELT_DENY = addTag("auto_smelt_deny");

        public static TagKey<Item> addTag(String name){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath("justdirethings", name));
        }
    }
}
