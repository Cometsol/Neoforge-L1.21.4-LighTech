package com.cometsol.lighttech.Blocks.Base;

import com.cometsol.lighttech.Blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BuddingAmethystBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.NotNull;

public class CrystalClusterNode extends BuddingAmethystBlock {

    public CrystalClusterNode(BlockBehaviour.Properties properties) {
        super(properties);
    }
    private static final Direction[] DIRECTIONS = Direction.values();
    public static final IntegerProperty GROWTHFACE = IntegerProperty.create("growface", 0, 5);

    protected void randomTick(BlockState state , ServerLevel level, BlockPos pos, RandomSource random) {

        if (random.nextInt(5) == 0) {
            Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
            BlockPos blockpos = pos.relative(direction);
            BlockState blockstate = level.getBlockState(blockpos);
            Block block = null;
            if (canClusterGrowAtState(blockstate)) {
                block = ModBlocks.CLEARCRYSTALCLUSTER_STAGE_0.get();
            } else if (blockstate.is(ModBlocks.CLEARCRYSTALCLUSTER_STAGE_0.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = ModBlocks.CLEARCRYSTALCLUSTER_STAGE_1.get();
            } else if (blockstate.is(ModBlocks.CLEARCRYSTALCLUSTER_STAGE_1.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = ModBlocks.CLEARCRYSTALCLUSTER_STAGE_2.get();
            } else if (blockstate.is(ModBlocks.CLEARCRYSTALCLUSTER_STAGE_2.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block =  ModBlocks.CLEARCRYSTALCLUSTER_STAGE_3.get();
            } else if (blockstate.is(ModBlocks.CLEARCRYSTALCLUSTER_STAGE_3.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block =  ModBlocks.CLEARCRYSTALCLUSTER_STAGE_4.get();
            } else if (blockstate.is(ModBlocks.CLEARCRYSTALCLUSTER_STAGE_4.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block =  ModBlocks.CLEARCRYSTALCLUSTER.get();
            }
            if (block != null) {
                BlockState blockstate1 = block.defaultBlockState().setValue(AmethystClusterBlock.FACING, direction);
                level.setBlockAndUpdate(blockpos, blockstate1);
            }
            if (direction == Direction.DOWN) {
                level.setBlockAndUpdate(pos, state.setValue(GROWTHFACE, 0));
            } else if (direction == Direction.UP) {
                level.setBlockAndUpdate(pos, state.setValue(GROWTHFACE, 1));
            } else if (direction == Direction.NORTH) {
                level.setBlockAndUpdate(pos, state.setValue(GROWTHFACE, 2));
            } else if (direction == Direction.SOUTH) {
                level.setBlockAndUpdate(pos, state.setValue(GROWTHFACE, 3));
            } else if (direction == Direction.WEST) {
                level.setBlockAndUpdate(pos, state.setValue(GROWTHFACE, 4));
            } else if (direction == Direction.EAST) {
                level.setBlockAndUpdate(pos, state.setValue(GROWTHFACE, 5));
            }
        }
    }

    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        return this.defaultBlockState().setValue(GROWTHFACE, 0);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(GROWTHFACE);
    }

}
