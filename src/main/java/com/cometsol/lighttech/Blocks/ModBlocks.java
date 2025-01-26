package com.cometsol.lighttech.Blocks;


import com.cometsol.lighttech.Items.ModItems;
import com.cometsol.lighttech.LightTech;
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
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(LightTech.MODID);

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name,toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, block.getId()))));
    }

    public static final DeferredBlock<Block> CLEARCRYSTAL_BLOCK = registerBlock("clearcrystal_block", () -> new Block(BlockBehaviour.Properties.of()
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("lighttech", "clearcrystal_block")))
            .strength(2f)
            .noOcclusion()
            .sound(SoundType.AMETHYST)
            .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> CLEARCRYSTALGLASS = registerBlock("clearcrystalglass", () -> new TransparentBlock(BlockBehaviour.Properties.of()
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("lighttech","clearcrystalglass")))
            .strength(0.6f)
            .noOcclusion()
            .sound(SoundType.GLASS)
            .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> CLEARCRYSTAL_Cluster = registerBlock("clearcrystal_cluster", () -> new Block(BlockBehaviour.Properties.of()
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("lighttech","clearcrystal_cluster")))
            .strength(0.5f)
            .noOcclusion()
            .sound(SoundType.AMETHYST_CLUSTER)
            .forceSolidOn()
            .pushReaction(PushReaction.DESTROY)));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}

