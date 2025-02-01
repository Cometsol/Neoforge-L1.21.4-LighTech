package com.cometsol.lighttech.Common.Blocks.Base;

import com.cometsol.lighttech.Common.BlockEnities.BaseBE.MachineEntityBase;
import com.cometsol.lighttech.Common.Items.DataComponents.LightTechDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class MachineBase extends Block implements EntityBlock {
    public MachineBase(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public static boolean never(BlockState state, BlockGetter getter, BlockPos pos){
        return false;
    }

    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        super.setPlacedBy(level, pos, state, entity, stack);
        if (!level.isClientSide && entity instanceof Player player) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof MachineEntityBase machineEntityBase) {
                if(stack.has(LightTechDataComponents.CUSTOM_DATA_1)) {
                    CompoundTag compound = ((CustomData)stack.get(LightTechDataComponents.CUSTOM_DATA_1)).copyTag();
                    if(!compound.isEmpty()) {
                    blockEntity.loadCustomOnly(compound, level.registryAccess());
                    }
                }
                machineEntityBase.setPlaceBy(player.getUUID());
            }
        }
    }
}
