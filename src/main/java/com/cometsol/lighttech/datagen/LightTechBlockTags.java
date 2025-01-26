package com.cometsol.lighttech.datagen;

import com.cometsol.lighttech.Blocks.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class LightTechBlockTags extends BlockTagsProvider {

    public LightTechBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, "lighttech");
    }

    protected void addTags(HolderLookup.Provider provider) {
        this.tag(net.neoforged.neoforge.common.Tags.Blocks.ORES).add((Block) ModBlocks.CLEARCRYSTALCluster.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL).add((Block) ModBlocks.CLEARCRYSTALCluster.get()).add((Block) ModBlocks.CLEARCRYSTAL_BLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add((Block) ModBlocks.CLEARCRYSTALCluster.get()).add((Block) ModBlocks.CLEARCRYSTAL_BLOCK.get()).add((Block) ModBlocks.CLEARCRYSTALGLASS.get());
        this.tag(Tags.Blocks.CLUSTERS).add((Block) ModBlocks.CLEARCRYSTALCluster.get());
    }
}
