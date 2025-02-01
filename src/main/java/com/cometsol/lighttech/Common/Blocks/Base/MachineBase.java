package com.cometsol.lighttech.Common.Blocks.Base;

import com.cometsol.lighttech.Common.BlockEnities.BaseBE.AreaAffectingBE;
import com.cometsol.lighttech.Common.BlockEnities.BaseBE.MachineEntityBase;
import com.cometsol.lighttech.Common.BlockEnities.BaseBE.RedstoneControlledBE;
import com.cometsol.lighttech.Common.Items.DataComponents.LightTechDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class MachineBase extends Block implements EntityBlock {
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

/*
    public InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult hit) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            ItemStack itemStack = player.getMainHandItem();
            if (!(itemStack.getItem() instanceof MachineSettingsCopier) && !(itemStack.getItem() instanceof FerricoreWrench)) {
                BlockEntity te = level.getBlockEntity(blockPos);
                if (!this.isValidBE(te)) {
                    return InteractionResult.FAIL;
                } else {
                    this.openMenu(player, blockPos);
                    return InteractionResult.SUCCESS;
                }
            } else {
                return InteractionResult.PASS;
            }
        }
    }
*/
    public abstract void openMenu(Player var1, BlockPos var2);

    public abstract boolean isValidBE(BlockEntity var1);

    public <T extends BlockEntity> @Nullable BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide() ? (lvl, pos, blockState, t) -> {
            if (t instanceof MachineEntityBase tile) {
                tile.tickClient();
            }

        } : (lvl, pos, blockState, t) -> {
            if (t instanceof MachineEntityBase tile) {
                tile.tickServer();
            }

        };
    }

    /*public void neighborChanged(BlockState blockState, Level level, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(blockState, level, pos, blockIn, fromPos, isMoving);
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof RedstoneControlledBE redstoneControlledBE) {
            redstoneControlledBE.getRedstoneControlData().checkedRedstone = false;
        }

    }*/

    public boolean canConnectRedstone(BlockState state, BlockGetter level, BlockPos pos, @javax.annotation.Nullable Direction direction) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        return blockEntity instanceof RedstoneControlledBE ? true : super.canConnectRedstone(state, level, pos, direction);
    }

    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (newState.getBlock() != this) {
            BlockEntity blockEntity = worldIn.getBlockEntity(pos);
            if (blockEntity instanceof MachineEntityBase) {
                MachineEntityBase machineEntityBase = (MachineEntityBase)blockEntity;
                IItemHandler iItemHandler = machineEntityBase.getMachineHandler();

                for(int i = 0; i < iItemHandler.getSlots(); ++i) {
                    Containers.dropItemStack(worldIn, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), iItemHandler.getStackInSlot(i));
                }
            }
        }

        super.onRemove(state, worldIn, pos, newState, isMoving);
    }

    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        List<ItemStack> drops = super.getDrops(state, builder);
        BlockEntity blockEntity = (BlockEntity)builder.getParameter(LootContextParams.BLOCK_ENTITY);
        if (blockEntity instanceof MachineEntityBase machineEntityBase) {
            if (!machineEntityBase.isDefaultSettings()) {
                ItemStack itemStack = new ItemStack(Item.byBlock(this));
                CompoundTag compoundTag = new CompoundTag();
                ((MachineEntityBase)blockEntity).saveAdditional(compoundTag, builder.getLevel().registryAccess());
                if (!compoundTag.isEmpty()) {
                    itemStack.set(LightTechDataComponents.CUSTOM_DATA_1, CustomData.of(compoundTag));
                }

                drops.clear();
                drops.add(itemStack);
            }
        }

        return drops;
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return (BlockState)this.defaultBlockState().setValue(BlockStateProperties.FACING, context.getNearestLookingDirection().getOpposite());
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{BlockStateProperties.FACING});
    }

    public BlockState rotate(BlockState state, Rotation rotation) {
        return (BlockState)state.setValue(BlockStateProperties.FACING, rotation.rotate((Direction)state.getValue(BlockStateProperties.FACING)));
    }

    public BlockState direRotate(BlockState blockState, LevelAccessor level, BlockPos pos, Rotation direction) {
        BlockState newState = this.direRotate(blockState, direction);
        BlockEntity var7 = level.getBlockEntity(pos);
        if (var7 instanceof AreaAffectingBE areaAffectingBE) {
            areaAffectingBE.handleRotate((Direction)blockState.getValue(BlockStateProperties.FACING), (Direction)newState.getValue(BlockStateProperties.FACING));
        }

        return newState;
    }

    public BlockState direRotate(BlockState state, Rotation rotation) {
        Property<Direction> prop = BlockStateProperties.FACING;
        Comparable<?> currentValue = state.getValue(prop);
        List<Direction> directions = prop.getPossibleValues().stream().toList();
        int currentDirectionIndex = directions.indexOf(currentValue);
        int nextDirectionIndex = (currentDirectionIndex + 1) % directions.size();
        Direction nextDirection = (Direction)directions.get(nextDirectionIndex);
        return (BlockState)state.setValue(prop, nextDirection);
    }
}
