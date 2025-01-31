package com.cometsol.lighttech.Common.BlockEnities.BaseBE;

import com.cometsol.lighttech.Util.MiscHelpersRedstoneMode;
import com.cometsol.lighttech.Util.Interfacehelpers.RedstoneControlData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public interface RedstoneControlledBE {
    RedstoneControlData getRedstoneControlData();

    BlockEntity getBlockEntity();

    default void setRedstoneSettings(int redstoneMode) {
        this.getRedstoneControlData().redstoneMode = MiscHelpersRedstoneMode.values()[redstoneMode];
        BlockEntity var3 = this.getBlockEntity();
        if (var3 instanceof MachineEntityBase machineEntityBase) {
            machineEntityBase.markDirtyClient();
        }

        BlockState blockState = this.getBlockEntity().getBlockState();
        /*if (blockState.hasProperty(BlockBreakerT1.ACTIVE)) {
            this.getBlockEntity().getLevel().setBlockAndUpdate(this.getBlockEntity().getBlockPos(), (BlockState)blockState.setValue(BlockBreakerT1.ACTIVE, this.isActiveRedstoneTestOnly()));
        }*/

    }

    default void evaluateRedstone() {
        if (!this.getRedstoneControlData().checkedRedstone) {
            boolean newRedstoneSignal = this.getBlockEntity().getLevel().hasNeighborSignal(this.getBlockEntity().getBlockPos());
            if (this.getRedstoneControlData().redstoneMode.equals(MiscHelpersRedstoneMode.PULSE) && !this.getRedstoneControlData().receivingRedstone && newRedstoneSignal) {
                this.getRedstoneControlData().pulsed = true;
            }

            this.getRedstoneControlData().receivingRedstone = newRedstoneSignal;
            this.getRedstoneControlData().checkedRedstone = true;
            BlockState blockState = this.getBlockEntity().getBlockState();
            /*if (blockState.hasProperty(BlockBreakerT1.ACTIVE)) {
                this.getBlockEntity().getLevel().setBlockAndUpdate(this.getBlockEntity().getBlockPos(), (BlockState)blockState.setValue(BlockBreakerT1.ACTIVE, this.isActiveRedstoneTestOnly()));
            }*/
        }

    }

    default boolean isActiveRedstoneTestOnly() {
        if (this.getRedstoneControlData().redstoneMode.equals(MiscHelpersRedstoneMode.IGNORED)) {
            return true;
        } else if (this.getRedstoneControlData().redstoneMode.equals(MiscHelpersRedstoneMode.LOW)) {
            return !this.getRedstoneControlData().receivingRedstone;
        } else if (this.getRedstoneControlData().redstoneMode.equals(MiscHelpersRedstoneMode.HIGH)) {
            return this.getRedstoneControlData().receivingRedstone;
        } else {
            return this.getRedstoneControlData().redstoneMode.equals(MiscHelpersRedstoneMode.PULSE) && this.getRedstoneControlData().pulsed;
        }
    }

    default boolean isActiveRedstone() {
        if (this.getRedstoneControlData().redstoneMode.equals(MiscHelpersRedstoneMode.IGNORED)) {
            return true;
        } else if (this.getRedstoneControlData().redstoneMode.equals(MiscHelpersRedstoneMode.LOW)) {
            return !this.getRedstoneControlData().receivingRedstone;
        } else if (this.getRedstoneControlData().redstoneMode.equals(MiscHelpersRedstoneMode.HIGH)) {
            return this.getRedstoneControlData().receivingRedstone;
        } else if (this.getRedstoneControlData().redstoneMode.equals(MiscHelpersRedstoneMode.PULSE) && this.getRedstoneControlData().pulsed) {
            this.getRedstoneControlData().pulsed = false;
            return true;
        } else {
            return false;
        }
    }

    default void saveRedstoneSettings(CompoundTag tag) {
        tag.putInt("redstoneMode", this.getRedstoneControlData().redstoneMode.ordinal());
        tag.putBoolean("pulsed", this.getRedstoneControlData().pulsed);
        tag.putBoolean("receivingRedstone", this.getRedstoneControlData().receivingRedstone);
    }

    default void loadRedstoneSettings(CompoundTag tag) {
        if (tag.contains("redstoneMode")) {
            this.getRedstoneControlData().redstoneMode = MiscHelpersRedstoneMode.values()[tag.getInt("redstoneMode")];
            this.getRedstoneControlData().pulsed = tag.getBoolean("pulsed");
            this.getRedstoneControlData().receivingRedstone = tag.getBoolean("receivingRedstone");
        }

    }
}
