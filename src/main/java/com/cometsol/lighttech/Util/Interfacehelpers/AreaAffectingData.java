package com.cometsol.lighttech.Util.Interfacehelpers;

import java.util.Objects;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.AABB;

public class AreaAffectingData {
    public double xRadius = (double)0.0F;
    public double yRadius = (double)0.0F;
    public double zRadius = (double)0.0F;
    public int xOffset = 0;
    public int yOffset = 1;
    public int zOffset = 0;
    public boolean renderArea = false;
    public AABB area;

    public AreaAffectingData() {
    }

    public AreaAffectingData(Direction facing) {
        this.xOffset = facing.getStepX();
        this.yOffset = facing.getStepY();
        this.zOffset = facing.getStepZ();
    }

    public AreaAffectingData(double xRadius, double yRadius, double zRadius, int xOffset, int yOffset, int zOffset) {
        this.xRadius = xRadius;
        this.yRadius = yRadius;
        this.zRadius = zRadius;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.zOffset = zOffset;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            AreaAffectingData that = (AreaAffectingData)o;
            return this.xRadius == that.xRadius && this.yRadius == that.yRadius && this.zRadius == that.zRadius && this.xOffset == that.xOffset && this.yOffset == that.yOffset && this.zOffset == that.zOffset && this.renderArea == that.renderArea;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.xRadius, this.yRadius, this.zRadius, this.xOffset, this.yOffset, this.zOffset, this.renderArea});
    }
}
