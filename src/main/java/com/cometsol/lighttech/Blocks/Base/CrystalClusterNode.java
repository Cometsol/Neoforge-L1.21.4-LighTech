package com.cometsol.lighttech.Blocks.Base;

import com.cometsol.lighttech.Blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BuddingAmethystBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class CrystalClusterNode extends BuddingAmethystBlock {
    private static final Random rand = new Random();
    private static final Direction[] DIRECTIONS = Direction.values();

    public CrystalClusterNode(){ super(Properties.of().sound(SoundType.AMETHYST).randomTicks().strength(1.5f));}

    protected void randomTick(BlockState state , ServerLevel level, BlockPos pos, RandomSource random) {

        if (random.nextInt(5) == 0) {
            Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
            BlockPos blockpos = pos.relative(direction);
            BlockState blockstate = level.getBlockState(blockpos);
            Block block = null;
            if (canClusterGrowAtState(blockstate)) {
                block = (Block) ModBlocks.CLEARCRYSTALCLUSTER_STAGE_0.get();
            } else if (blockstate.is((Block) ModBlocks.CLEARCRYSTALCLUSTER_STAGE_0.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = (Block) ModBlocks.CLEARCRYSTALCLUSTER_STAGE_1.get();
            } else if (blockstate.is((Block) ModBlocks.CLEARCRYSTALCLUSTER_STAGE_1.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = (Block) ModBlocks.CLEARCRYSTALCLUSTER_STAGE_2.get();
            } else if (blockstate.is((Block) ModBlocks.CLEARCRYSTALCLUSTER_STAGE_2.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = (Block) ModBlocks.CLEARCRYSTALCLUSTER_STAGE_3.get();
            } else if (blockstate.is((Block) ModBlocks.CLEARCRYSTALCLUSTER_STAGE_3.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = (Block) ModBlocks.CLEARCRYSTALCLUSTER_STAGE_4.get();
            } else if (blockstate.is((Block) ModBlocks.CLEARCRYSTALCLUSTER_STAGE_4.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = (Block) ModBlocks.CLEARCRYSTALCLUSTER.get();
            }
            if (block != null) {
                level.setBlockAndUpdate(blockpos, blockstate);
            }
        }
    }
}
