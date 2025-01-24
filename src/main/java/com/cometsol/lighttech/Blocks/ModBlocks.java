package com.cometsol.lighttech.Blocks;


import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.cometsol.lighttech.Items.ModItems.ITEMS;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks("lighttech");

    public static final DeferredBlock<Block> CLEARCRYSTAL_BLOCK = BLOCKS.register("clearcrystal_block", () -> new Block(BlockBehaviour.Properties.of()
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("lighttech", "clearcrystal_block")))
            .strength(2f)
            .noOcclusion()
            .sound(SoundType.AMETHYST)
            .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> CLEARCRYSTALGLASS = BLOCKS.register("clearcrystalglass", () -> new TransparentBlock(BlockBehaviour.Properties.of()
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("lighttech","clearcrystalglass")))
            .strength(1f)
            .noOcclusion()
            .sound(SoundType.GLASS)
            .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> CLEARCRYSTAL_Cluster = BLOCKS.register("clearcrystal_cluster", () -> new Block(BlockBehaviour.Properties.of()
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("lighttech","clearcrystal_cluster")))
            .strength(0.5f)
            .noOcclusion()
            .sound(SoundType.AMETHYST_CLUSTER)
            .forceSolidOn()
            .pushReaction(PushReaction.DESTROY)));

    public static DeferredItem<Item> CLEARCRYSTAL_BLOCK_ITEM = ITEMS.register("clearcrystal_block",
            ()-> new BlockItem((Block) CLEARCRYSTAL_BLOCK.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("lighttech","clearcrystal_block")))
                    .useBlockDescriptionPrefix()));
    public static DeferredItem<Item> CLEARCRYSTALGLASS_ITEM = ITEMS.register("clearcrystalglass",
            ()-> new BlockItem((Block) CLEARCRYSTALGLASS.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("lighttech","clearcrystalglass")))
                    .useBlockDescriptionPrefix()));
}

