package com.cometsol.lighttech.Util;

import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.capabilities.Capabilities.ItemHandler;
import net.neoforged.neoforge.items.IItemHandler;

public class MiscHelpers {
    private static final Random rand = new Random();

    public MiscHelpers() {
    }

    public static double nextDouble(double min, double max) {
        return min + (max - min) * rand.nextDouble();
    }

    public static IItemHandler getAttachedInventory(Level level, BlockPos blockPos, Direction side) {
        if (level == null) {
            return null;
        } else {
            BlockEntity be = level.getBlockEntity(blockPos);
            if (be != null) {
                IItemHandler handler = (IItemHandler)level.getCapability(ItemHandler.BLOCK, blockPos, side);
                return handler;
            } else {
                return null;
            }
        }
    }

    public static Direction getPrimaryDirection(Vec3 vec) {
        double absX = Math.abs(vec.x);
        double absY = Math.abs(vec.y);
        double absZ = Math.abs(vec.z);
        if (absX > absY && absX > absZ) {
            return vec.x > (double)0.0F ? Direction.EAST : Direction.WEST;
        } else if (absY > absX && absY > absZ) {
            return vec.y > (double)0.0F ? Direction.UP : Direction.DOWN;
        } else {
            return vec.z > (double)0.0F ? Direction.SOUTH : Direction.NORTH;
        }
    }

    public static Direction getFacingDirection(Player player) {
        float yaw = player.getYRot();
        float pitch = player.getXRot();
        Direction horizontalDirection = Direction.fromYRot((double)yaw);
        if (pitch < -45.0F) {
            return Direction.UP;
        } else {
            return pitch > 45.0F ? Direction.DOWN : horizontalDirection;
        }
    }
}
