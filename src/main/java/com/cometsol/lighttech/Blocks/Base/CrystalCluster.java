package com.cometsol.lighttech.Blocks.Base;

import com.cometsol.lighttech.Items.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class CrystalCluster extends AmethystClusterBlock {
    public CrystalCluster(float height, float aabbOffset, BlockBehaviour.Properties properties) {
        super(height, aabbOffset, properties);
    }
}
