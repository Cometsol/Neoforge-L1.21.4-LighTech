package com.cometsol.lighttech.Common.BlockEnities.BaseBE;

import com.cometsol.lighttech.Util.Interfacehelpers.AreaAffectingData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;

public interface AreaAffectingBE {
    double maxRadius = (double)9.0f;
    int maxOffset = 12;

    BlockEntity getBlockEntity();

    AreaAffectingData getAreaAffectingData();

    default AABB getAABBOffsetOnly(BlockPos relativePos) {
        double xOffset = (double)this.getAreaAffectingData().xOffset;
        double yOffset = (double)this.getAreaAffectingData().yOffset;
        double zOffset = (double)this.getAreaAffectingData().zOffset;
        if (this.getAreaAffectingData().xRadius != Math.floor(this.getAreaAffectingData().xRadius)) {
            xOffset += (double)0.75F;
        }

        if (this.getAreaAffectingData().yRadius != Math.floor(this.getAreaAffectingData().yRadius)) {
            yOffset += (double)0.75F;
        }

        if (this.getAreaAffectingData().zRadius != Math.floor(this.getAreaAffectingData().zRadius)) {
            zOffset += (double)0.75F;
        }

        return new AABB((double)relativePos.getX() + xOffset, (double)relativePos.getY() + yOffset, (double)relativePos.getZ() + zOffset, (double)relativePos.getX() + xOffset + (this.getAreaAffectingData().xRadius != Math.floor(this.getAreaAffectingData().xRadius) ? (double)0.5F : (double)1.0F), (double)relativePos.getY() + yOffset + (this.getAreaAffectingData().yRadius != Math.floor(this.getAreaAffectingData().yRadius) ? (double)0.5F : (double)1.0F), (double)relativePos.getZ() + zOffset + (this.getAreaAffectingData().zRadius != Math.floor(this.getAreaAffectingData().zRadius) ? (double)0.5F : (double)1.0F));
    }

    default AreaAffectingData getDefaultAreaData() {
        return new AreaAffectingData();
    }

    default AreaAffectingData getDefaultAreaData(Direction facing) {
        return new AreaAffectingData(facing);
    }

    default void handleRotate(Direction oldDirection, Direction newDirection) {
        if (oldDirection != newDirection) {
            AreaAffectingData areaAffectingData = this.getAreaAffectingData();
            AreaAffectingData defaultData = this.getDefaultAreaData(oldDirection);
            defaultData.renderArea = areaAffectingData.renderArea;
            if (areaAffectingData.equals(defaultData)) {
                AreaAffectingData newAreaData = new AreaAffectingData(newDirection);
                this.setAreaSettings(newAreaData.xRadius, newAreaData.yRadius, newAreaData.zRadius, newAreaData.xOffset, newAreaData.yOffset, newAreaData.zOffset, areaAffectingData.renderArea);
            }
        }
    }

    default AABB getAABB(BlockPos relativePos) {
        if (this.getAreaAffectingData().area == null) {
            double xOffset = (double)this.getAreaAffectingData().xOffset;
            double yOffset = (double)this.getAreaAffectingData().yOffset;
            double zOffset = (double)this.getAreaAffectingData().zOffset;
            if (this.getAreaAffectingData().xRadius != Math.floor(this.getAreaAffectingData().xRadius)) {
                xOffset += (double)0.5F;
            }

            if (this.getAreaAffectingData().yRadius != Math.floor(this.getAreaAffectingData().yRadius)) {
                yOffset += (double)0.5F;
            }

            if (this.getAreaAffectingData().zRadius != Math.floor(this.getAreaAffectingData().zRadius)) {
                zOffset += (double)0.5F;
            }

            this.getAreaAffectingData().area = (new AABB((double)relativePos.getX() + xOffset, (double)relativePos.getY() + yOffset, (double)relativePos.getZ() + zOffset, (double)relativePos.getX() + xOffset + (double)1.0F, (double)relativePos.getY() + yOffset + (double)1.0F, (double)relativePos.getZ() + zOffset + (double)1.0F)).inflate(this.getAreaAffectingData().xRadius, this.getAreaAffectingData().yRadius, this.getAreaAffectingData().zRadius);
        }

        return this.getAreaAffectingData().area;
    }

    default void setAreaSettings(double x, double y, double z, int xo, int yo, int zo, boolean renderArea) {
        this.getAreaAffectingData().xRadius = Math.max((double)0.0F, Math.min(x, (double)5.0F));
        this.getAreaAffectingData().yRadius = Math.max((double)0.0F, Math.min(y, (double)5.0F));
        this.getAreaAffectingData().zRadius = Math.max((double)0.0F, Math.min(z, (double)5.0F));
        this.getAreaAffectingData().xOffset = Math.max(-9, Math.min(xo, 9));
        this.getAreaAffectingData().yOffset = Math.max(-9, Math.min(yo, 9));
        this.getAreaAffectingData().zOffset = Math.max(-9, Math.min(zo, 9));
        this.getAreaAffectingData().renderArea = renderArea;
        this.getAreaAffectingData().area = null;
        BlockEntity var12 = this.getBlockEntity();
        if (var12 instanceof MachineEntityBase machineEntityBase) {
            machineEntityBase.markDirtyClient();
        }

    }

    default void saveAreaSettings(CompoundTag tag) {
        tag.putDouble("xRadiusDouble", this.getAreaAffectingData().xRadius);
        tag.putDouble("yRadiusDouble", this.getAreaAffectingData().yRadius);
        tag.putDouble("zRadiusDouble", this.getAreaAffectingData().zRadius);
        tag.putInt("xOffset", this.getAreaAffectingData().xOffset);
        tag.putInt("yOffset", this.getAreaAffectingData().yOffset);
        tag.putInt("zOffset", this.getAreaAffectingData().zOffset);
        tag.putBoolean("renderArea", this.getAreaAffectingData().renderArea);
    }

    default void saveAreaOnly(CompoundTag tag) {
        tag.putDouble("xRadiusDouble", this.getAreaAffectingData().xRadius);
        tag.putDouble("yRadiusDouble", this.getAreaAffectingData().yRadius);
        tag.putDouble("zRadiusDouble", this.getAreaAffectingData().zRadius);
    }

    default void saveOffsetOnly(CompoundTag tag) {
        tag.putInt("xOffset", this.getAreaAffectingData().xOffset);
        tag.putInt("yOffset", this.getAreaAffectingData().yOffset);
        tag.putInt("zOffset", this.getAreaAffectingData().zOffset);
    }

    default void loadAreaSettings(CompoundTag tag) {
        if (tag.contains("xRadiusDouble")) {
            this.getAreaAffectingData().xRadius = tag.getDouble("xRadiusDouble");
            this.getAreaAffectingData().yRadius = tag.getDouble("yRadiusDouble");
            this.getAreaAffectingData().zRadius = tag.getDouble("zRadiusDouble");
            this.getAreaAffectingData().xOffset = tag.getInt("xOffset");
            this.getAreaAffectingData().yOffset = tag.getInt("yOffset");
            this.getAreaAffectingData().zOffset = tag.getInt("zOffset");
            this.getAreaAffectingData().renderArea = tag.getBoolean("renderArea");
        }

    }

    default void loadAreaOnly(CompoundTag tag) {
        if (tag.contains("xRadiusDouble")) {
            this.getAreaAffectingData().xRadius = tag.getDouble("xRadiusDouble");
            this.getAreaAffectingData().yRadius = tag.getDouble("yRadiusDouble");
            this.getAreaAffectingData().zRadius = tag.getDouble("zRadiusDouble");
        }

    }

    default void loadOffsetOnly(CompoundTag tag) {
        if (tag.contains("xOffset")) {
            this.getAreaAffectingData().xOffset = tag.getInt("xOffset");
            this.getAreaAffectingData().yOffset = tag.getInt("yOffset");
            this.getAreaAffectingData().zOffset = tag.getInt("zOffset");
        }

    }
}
