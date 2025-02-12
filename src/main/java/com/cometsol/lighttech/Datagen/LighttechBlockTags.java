package com.cometsol.lighttech.Datagen;

import com.cometsol.lighttech.Common.Blocks.ModBlocks;
import com.cometsol.lighttech.LightTech;
import com.cometsol.lighttech.Util.intergration.justdirethings.justdirethingsintergration;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;


public class LighttechBlockTags extends BlockTagsProvider {


    public static final TagKey<Block> ECLISEGATEDENY = BlockTags.create(ResourceLocation.fromNamespaceAndPath("justdirethings", "eclipsegate_deny"));
    public static final TagKey<Block> ATTUNED = BlockTags.create(ResourceLocation.fromNamespaceAndPath("lighttech", "attuned"));

    public LighttechBlockTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider){
        super(packOutput, lookupProvider, LightTech.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.CLEARCRYSTAL_BLOCK.get())
                .add(ModBlocks.FIERYCRYSTAL_BLOCK.get())
                .add(ModBlocks.VOIDCRYSTAL_BLOCK.get())
                .add(ModBlocks.CLEARCRYSTALCLUSTER.get())
                .add(ModBlocks.FIERYCRYSTALCLUSTER.get())
                .add(ModBlocks.VOIDCRYSTALCLUSTER.get())
                .add(ModBlocks.CLEARCRYSTALCLUSTER_NODE.get())
                .add(ModBlocks.FIERYCRYSTALCLUSTER_NODE.get())
                .add(ModBlocks.VOIDCRYSTALCLUSTER_NODE.get())
                .add(ModBlocks.CLEARCRYSTALCLUSTER_STAGE_0.get())
                .add(ModBlocks.CLEARCRYSTALCLUSTER_STAGE_1.get())
                .add(ModBlocks.CLEARCRYSTALCLUSTER_STAGE_2.get())
                .add(ModBlocks.CLEARCRYSTALCLUSTER_STAGE_3.get())
                .add(ModBlocks.CLEARCRYSTALCLUSTER_STAGE_4.get())
                .add(ModBlocks.FIERYCRYSTALCLUSTER_STAGE_0.get())
                .add(ModBlocks.FIERYCRYSTALCLUSTER_STAGE_1.get())
                .add(ModBlocks.FIERYCRYSTALCLUSTER_STAGE_2.get())
                .add(ModBlocks.FIERYCRYSTALCLUSTER_STAGE_3.get())
                .add(ModBlocks.FIERYCRYSTALCLUSTER_STAGE_4.get())
                .add(ModBlocks.VOIDCRYSTALCLUSTER_STAGE_0.get())
                .add(ModBlocks.VOIDCRYSTALCLUSTER_STAGE_1.get())
                .add(ModBlocks.VOIDCRYSTALCLUSTER_STAGE_2.get())
                .add(ModBlocks.VOIDCRYSTALCLUSTER_STAGE_3.get())
                .add(ModBlocks.VOIDCRYSTALCLUSTER_STAGE_4.get())
                .add(ModBlocks.CLEARCRYSTALGLASS.get())
                .add(ModBlocks.FIERYCRYSTALGLASS.get())
                .add(ModBlocks.VOIDCRYSTALGLASS.get())
                .replace(false);

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.CLEARCRYSTAL_BLOCK.get())
                .add(ModBlocks.FIERYCRYSTAL_BLOCK.get())
                .add(ModBlocks.VOIDCRYSTAL_BLOCK.get())
                .add(ModBlocks.CLEARCRYSTALCLUSTER.get())
                .add(ModBlocks.FIERYCRYSTALCLUSTER.get())
                .add(ModBlocks.VOIDCRYSTALCLUSTER.get())
                .add(ModBlocks.CLEARCRYSTALCLUSTER_NODE.get())
                .add(ModBlocks.FIERYCRYSTALCLUSTER_NODE.get())
                .add(ModBlocks.VOIDCRYSTALCLUSTER_NODE.get())
                .add(ModBlocks.CLEARCRYSTALCLUSTER_STAGE_0.get())
                .add(ModBlocks.CLEARCRYSTALCLUSTER_STAGE_1.get())
                .add(ModBlocks.CLEARCRYSTALCLUSTER_STAGE_2.get())
                .add(ModBlocks.CLEARCRYSTALCLUSTER_STAGE_3.get())
                .add(ModBlocks.CLEARCRYSTALCLUSTER_STAGE_4.get())
                .add(ModBlocks.FIERYCRYSTALCLUSTER_STAGE_0.get())
                .add(ModBlocks.FIERYCRYSTALCLUSTER_STAGE_1.get())
                .add(ModBlocks.FIERYCRYSTALCLUSTER_STAGE_2.get())
                .add(ModBlocks.FIERYCRYSTALCLUSTER_STAGE_3.get())
                .add(ModBlocks.FIERYCRYSTALCLUSTER_STAGE_4.get())
                .add(ModBlocks.VOIDCRYSTALCLUSTER_STAGE_0.get())
                .add(ModBlocks.VOIDCRYSTALCLUSTER_STAGE_1.get())
                .add(ModBlocks.VOIDCRYSTALCLUSTER_STAGE_2.get())
                .add(ModBlocks.VOIDCRYSTALCLUSTER_STAGE_3.get())
                .add(ModBlocks.VOIDCRYSTALCLUSTER_STAGE_4.get())
                .add(ModBlocks.CLEARCRYSTALGLASS.get())
                .add(ModBlocks.FIERYCRYSTALGLASS.get())
                .add(ModBlocks.VOIDCRYSTALGLASS.get())
                .replace(false);

        tag(ATTUNED)
                .add(ModBlocks.CLEARCRYSTALCLUSTER_NODE.get())
                .add(ModBlocks.FIERYCRYSTALCLUSTER_NODE.get())
                .add(ModBlocks.VOIDCRYSTALCLUSTER_NODE.get());

        if(justdirethingsintergration.isLoaded()){
            tag(ECLISEGATEDENY)
                    .add(ModBlocks.CLEARCRYSTAL_BLOCK.get())
                    .add(ModBlocks.FIERYCRYSTAL_BLOCK.get())
                    .add(ModBlocks.VOIDCRYSTAL_BLOCK.get())
                    .add(ModBlocks.CLEARCRYSTALCLUSTER.get())
                    .add(ModBlocks.FIERYCRYSTALCLUSTER.get())
                    .add(ModBlocks.VOIDCRYSTALCLUSTER.get())
                    .add(ModBlocks.CLEARCRYSTALCLUSTER_NODE.get())
                    .add(ModBlocks.FIERYCRYSTALCLUSTER_NODE.get())
                    .add(ModBlocks.VOIDCRYSTALCLUSTER_NODE.get())
                    .add(ModBlocks.CLEARCRYSTALCLUSTER_STAGE_0.get())
                    .add(ModBlocks.CLEARCRYSTALCLUSTER_STAGE_1.get())
                    .add(ModBlocks.CLEARCRYSTALCLUSTER_STAGE_2.get())
                    .add(ModBlocks.CLEARCRYSTALCLUSTER_STAGE_3.get())
                    .add(ModBlocks.CLEARCRYSTALCLUSTER_STAGE_4.get())
                    .add(ModBlocks.FIERYCRYSTALCLUSTER_STAGE_0.get())
                    .add(ModBlocks.FIERYCRYSTALCLUSTER_STAGE_1.get())
                    .add(ModBlocks.FIERYCRYSTALCLUSTER_STAGE_2.get())
                    .add(ModBlocks.FIERYCRYSTALCLUSTER_STAGE_3.get())
                    .add(ModBlocks.FIERYCRYSTALCLUSTER_STAGE_4.get())
                    .add(ModBlocks.VOIDCRYSTALCLUSTER_STAGE_0.get())
                    .add(ModBlocks.VOIDCRYSTALCLUSTER_STAGE_1.get())
                    .add(ModBlocks.VOIDCRYSTALCLUSTER_STAGE_2.get())
                    .add(ModBlocks.VOIDCRYSTALCLUSTER_STAGE_3.get())
                    .add(ModBlocks.VOIDCRYSTALCLUSTER_STAGE_4.get())
                    .add(ModBlocks.CLEARCRYSTALGLASS.get())
                    .add(ModBlocks.FIERYCRYSTALGLASS.get())
                    .add(ModBlocks.VOIDCRYSTALGLASS.get())
                    .replace(false);
        }
    }
}
