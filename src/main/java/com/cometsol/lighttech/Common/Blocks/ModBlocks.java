package com.cometsol.lighttech.Common.Blocks;


import com.cometsol.lighttech.Common.Blocks.Base.CrystalCluster;
import com.cometsol.lighttech.Common.Blocks.Base.CrystalClusterNode;
import com.cometsol.lighttech.Common.Items.ModItems;
import com.cometsol.lighttech.LightTech;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class ModBlocks {

    public static final EnumProperty<Direction> FACING = BlockStateProperties.FACING;
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(LightTech.MODID);

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name,toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.BLOCKITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, block.getId()))));
    }
    //storage
    public static final DeferredBlock<Block> CLEARCRYSTAL_BLOCK = registerBlock("clearcrystal_block", () -> new Block(BlockBehaviour.Properties.of()
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "clearcrystal_block")))
            .strength(2f).noOcclusion().sound(SoundType.AMETHYST).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> FIERYCRYSTAL_BLOCK = registerBlock("fierycrystal_block", () -> new Block(BlockBehaviour.Properties.of()
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "fierycrystal_block")))
            .strength(4f).noOcclusion().sound(SoundType.AMETHYST).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> VOIDCRYSTAL_BLOCK = registerBlock("voidcrystal_block", () -> new Block(BlockBehaviour.Properties.of()
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "voidcrystal_block")))
            .strength(8f).noOcclusion().sound(SoundType.AMETHYST).requiresCorrectToolForDrops()));

    //building
    public static final DeferredBlock<Block> CLEARCRYSTALGLASS = registerBlock("clearcrystalglass", () -> new TransparentBlock(BlockBehaviour.Properties.of()
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"clearcrystalglass")))
            .strength(0.6f).noOcclusion().sound(SoundType.GLASS).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> FIERYCRYSTALGLASS = registerBlock("fierycrystalglass", () -> new TransparentBlock(BlockBehaviour.Properties.of()
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"fierycrystalglass")))
            .strength(1.2f).noOcclusion().sound(SoundType.GLASS).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> VOIDCRYSTALGLASS = registerBlock("voidcrystalglass", () -> new TransparentBlock(BlockBehaviour.Properties.of()
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"voidcrystalglass")))
            .strength(2.4f).noOcclusion().sound(SoundType.GLASS).requiresCorrectToolForDrops()));

    //cluster
    public static final DeferredBlock<Block> CLEARCRYSTALCLUSTER = registerBlock("clearcrystalcluster", () -> new CrystalCluster(3.0F, 4.0F, BlockBehaviour.Properties.of()
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"clearcrystalcluster")))
            .strength(0.5f).noOcclusion().sound(SoundType.AMETHYST_CLUSTER).forceSolidOn().pushReaction(PushReaction.DESTROY).forceSolidOn()));
    public static final DeferredBlock<Block> FIERYCRYSTALCLUSTER = registerBlock("fierycrystalcluster", () -> new CrystalCluster(3.0F, 4.0F, BlockBehaviour.Properties.of()
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"fierycrystalcluster")))
            .strength(1f).noOcclusion().sound(SoundType.AMETHYST_CLUSTER).forceSolidOn().pushReaction(PushReaction.DESTROY).forceSolidOn()));
    public static final DeferredBlock<Block> VOIDCRYSTALCLUSTER = registerBlock("voidcrystalcluster", () -> new CrystalCluster(3.0F, 4.0F, BlockBehaviour.Properties.of()
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"voidcrystalcluster")))
            .strength(2f).noOcclusion().sound(SoundType.AMETHYST_CLUSTER).forceSolidOn().pushReaction(PushReaction.DESTROY).forceSolidOn()));
    public static final DeferredBlock<Block> CLEARCRYSTALCLUSTER_STAGE_0 = registerBlock("clearcrystalcluster_stage_0", () -> new CrystalCluster(3.0F, 4.0F, BlockBehaviour.Properties.ofFullCopy((BlockBehaviour)CLEARCRYSTALCLUSTER.get())
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"clearcrystalcluster_stage_0")))));
    public static final DeferredBlock<Block> CLEARCRYSTALCLUSTER_STAGE_1 = registerBlock("clearcrystalcluster_stage_1", () -> new CrystalCluster(3.0F, 4.0F, BlockBehaviour.Properties.ofFullCopy((BlockBehaviour)CLEARCRYSTALCLUSTER.get())
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"clearcrystalcluster_stage_1")))));
    public static final DeferredBlock<Block> CLEARCRYSTALCLUSTER_STAGE_2 = registerBlock("clearcrystalcluster_stage_2", () -> new CrystalCluster(3.0F, 4.0F, BlockBehaviour.Properties.ofFullCopy((BlockBehaviour)CLEARCRYSTALCLUSTER.get())
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"clearcrystalcluster_stage_2")))));
    public static final DeferredBlock<Block> CLEARCRYSTALCLUSTER_STAGE_3 = registerBlock("clearcrystalcluster_stage_3", () -> new CrystalCluster(3.0F, 4.0F, BlockBehaviour.Properties.ofFullCopy((BlockBehaviour)CLEARCRYSTALCLUSTER.get())
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"clearcrystalcluster_stage_3")))));
    public static final DeferredBlock<Block> CLEARCRYSTALCLUSTER_STAGE_4 = registerBlock("clearcrystalcluster_stage_4", () -> new CrystalCluster(3.0F, 4.0F, BlockBehaviour.Properties.ofFullCopy((BlockBehaviour)CLEARCRYSTALCLUSTER.get())
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"clearcrystalcluster_stage_4")))));
    public static final DeferredBlock<Block> FIERYCRYSTALCLUSTER_STAGE_0 = registerBlock("fierycrystalcluster_stage_0", () -> new CrystalCluster(3.0F, 4.0F, BlockBehaviour.Properties.ofFullCopy((BlockBehaviour)FIERYCRYSTALCLUSTER.get())
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"fierycrystalcluster_stage_0")))));
    public static final DeferredBlock<Block> FIERYCRYSTALCLUSTER_STAGE_1 = registerBlock("fierycrystalcluster_stage_1", () -> new CrystalCluster(3.0F, 4.0F, BlockBehaviour.Properties.ofFullCopy((BlockBehaviour)FIERYCRYSTALCLUSTER.get())
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"fierycrystalcluster_stage_1")))));
    public static final DeferredBlock<Block> FIERYCRYSTALCLUSTER_STAGE_2 = registerBlock("fierycrystalcluster_stage_2", () -> new CrystalCluster(3.0F, 4.0F, BlockBehaviour.Properties.ofFullCopy((BlockBehaviour)FIERYCRYSTALCLUSTER.get())
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"fierycrystalcluster_stage_2")))));
    public static final DeferredBlock<Block> FIERYCRYSTALCLUSTER_STAGE_3 = registerBlock("fierycrystalcluster_stage_3", () -> new CrystalCluster(3.0F, 4.0F, BlockBehaviour.Properties.ofFullCopy((BlockBehaviour)FIERYCRYSTALCLUSTER.get())
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"fierycrystalcluster_stage_3")))));
    public static final DeferredBlock<Block> FIERYCRYSTALCLUSTER_STAGE_4 = registerBlock("fierycrystalcluster_stage_4", () -> new CrystalCluster(3.0F, 4.0F, BlockBehaviour.Properties.ofFullCopy((BlockBehaviour)FIERYCRYSTALCLUSTER.get())
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"fierycrystalcluster_stage_4")))));
    public static final DeferredBlock<Block> VOIDCRYSTALCLUSTER_STAGE_0 = registerBlock("voidcrystalcluster_stage_0", () -> new CrystalCluster(3.0F, 4.0F, BlockBehaviour.Properties.ofFullCopy((BlockBehaviour)VOIDCRYSTALCLUSTER.get())
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"voidcrystalcluster_stage_0")))));
    public static final DeferredBlock<Block> VOIDCRYSTALCLUSTER_STAGE_1 = registerBlock("voidcrystalcluster_stage_1", () -> new CrystalCluster(3.0F, 4.0F, BlockBehaviour.Properties.ofFullCopy((BlockBehaviour)VOIDCRYSTALCLUSTER.get())
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"voidcrystalcluster_stage_1")))));
    public static final DeferredBlock<Block> VOIDCRYSTALCLUSTER_STAGE_2 = registerBlock("voidcrystalcluster_stage_2", () -> new CrystalCluster(3.0F, 4.0F, BlockBehaviour.Properties.ofFullCopy((BlockBehaviour)VOIDCRYSTALCLUSTER.get())
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"voidcrystalcluster_stage_2")))));
    public static final DeferredBlock<Block> VOIDCRYSTALCLUSTER_STAGE_3 = registerBlock("voidcrystalcluster_stage_3", () -> new CrystalCluster(3.0F, 4.0F, BlockBehaviour.Properties.ofFullCopy((BlockBehaviour)VOIDCRYSTALCLUSTER.get())
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"voidcrystalcluster_stage_3")))));
    public static final DeferredBlock<Block> VOIDCRYSTALCLUSTER_STAGE_4 = registerBlock("voidcrystalcluster_stage_4", () -> new CrystalCluster(3.0F, 4.0F, BlockBehaviour.Properties.ofFullCopy((BlockBehaviour)VOIDCRYSTALCLUSTER.get())
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"voidcrystalcluster_stage_4")))));

    //cluster budding
    public static final DeferredBlock<Block> CLEARCRYSTALCLUSTER_NODE = registerBlock("clearcrystalcluster_node", () -> new CrystalClusterNode(BlockBehaviour.Properties.of()
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"clearcrystalcluster_node")))
            .strength(1.5f).noOcclusion().sound(SoundType.AMETHYST).randomTicks().requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> FIERYCRYSTALCLUSTER_NODE = registerBlock("fierycrystalcluster_node", () -> new CrystalClusterNode(BlockBehaviour.Properties.of()
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"fierycrystalcluster_node")))
            .strength(3f).noOcclusion().sound(SoundType.AMETHYST).randomTicks().requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> VOIDCRYSTALCLUSTER_NODE = registerBlock("voidcrystalcluster_node", () -> new CrystalClusterNode(BlockBehaviour.Properties.of()
            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"voidcrystalcluster_node")))
            .strength(6f).noOcclusion().sound(SoundType.AMETHYST).randomTicks().requiresCorrectToolForDrops()));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}

