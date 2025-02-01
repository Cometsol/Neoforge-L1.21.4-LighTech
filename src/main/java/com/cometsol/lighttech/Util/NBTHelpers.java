package com.cometsol.lighttech.Util;

import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class NBTHelpers {
    public static StreamCodec<ByteBuf, Vec3> VEC3_STREAM_CODEC;

    public NBTHelpers() {
    }

    public static CompoundTag globalPosToNBT(GlobalPos globalPos) {
        CompoundTag tag = new CompoundTag();
        tag.putString("dimension", globalPos.dimension().location().toString());
        tag.put("blockpos", NbtUtils.writeBlockPos(globalPos.pos()));
        return tag;
    }

    public static GlobalPos nbtToGlobalPos(CompoundTag tag) {
        if (tag.contains("dimension")) {
            ResourceKey<Level> levelKey = ResourceKey.create(Registries.DIMENSION, ResourceLocation.parse(tag.getString("dimension")));
            BlockPos blockPos = (BlockPos)NbtUtils.readBlockPos(tag, "blockpos").orElse(BlockPos.ZERO);
            return blockPos.equals(BlockPos.ZERO) ? null : GlobalPos.of(levelKey, blockPos);
        } else {
            return null;
        }
    }

    public static CompoundTag globalVec3ToNBT(ResourceKey<Level> level, Vec3 position) {
        CompoundTag tag = new CompoundTag();
        tag.putString("dimension", level.location().toString());
        tag.putDouble("vec3x", position.x);
        tag.putDouble("vec3y", position.y);
        tag.putDouble("vec3z", position.z);
        return tag;
    }

    public static CompoundTag globalVec3ToNBT(GlobalVec3 globalVec3) {
        CompoundTag tag = new CompoundTag();
        tag.putString("dimension", globalVec3.dimension().location().toString());
        tag.putDouble("vec3x", globalVec3.position().x);
        tag.putDouble("vec3y", globalVec3.position().y);
        tag.putDouble("vec3z", globalVec3.position().z);
        return tag;
    }

    public static GlobalVec3 nbtToGlobalVec3(CompoundTag tag) {
        if (!tag.contains("dimension")) {
            return null;
        } else {
            ResourceKey<Level> levelKey = ResourceKey.create(Registries.DIMENSION, ResourceLocation.parse(tag.getString("dimension")));
            double x = tag.getDouble("vec3x");
            double y = tag.getDouble("vec3y");
            double z = tag.getDouble("vec3z");
            return new GlobalVec3(levelKey, new Vec3(x, y, z));
        }
    }

    public static CompoundTag vec3ToNBT(Vec3 vec3) {
        CompoundTag tag = new CompoundTag();
        tag.putDouble("vec3x", vec3.x);
        tag.putDouble("vec3y", vec3.y);
        tag.putDouble("vec3z", vec3.z);
        return tag;
    }

    public static Vec3 nbtToVec3(CompoundTag tag) {
        double x = tag.getDouble("vec3x");
        double y = tag.getDouble("vec3y");
        double z = tag.getDouble("vec3z");
        return new Vec3(x, y, z);
    }

    static {
        VEC3_STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.DOUBLE, Vec3::x, ByteBufCodecs.DOUBLE, Vec3::y, ByteBufCodecs.DOUBLE, Vec3::z, Vec3::new);
    }
}
