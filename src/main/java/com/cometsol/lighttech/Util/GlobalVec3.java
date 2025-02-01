package com.cometsol.lighttech.Util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public record GlobalVec3(ResourceKey<Level> dimension, Vec3 position) {
    public static final Codec<GlobalVec3> CODEC = RecordCodecBuilder.create((cooldownInstance) -> cooldownInstance.group(Level.RESOURCE_KEY_CODEC.fieldOf("dimension").forGetter(GlobalVec3::dimension), Vec3.CODEC.fieldOf("direction").forGetter(GlobalVec3::position)).apply(cooldownInstance, GlobalVec3::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, GlobalVec3> STREAM_CODEC;

    public GlobalVec3(ResourceKey<Level> dimension, Vec3 position) {
        this.dimension = dimension;
        this.position = position;
    }

    public String toVec3ShortString() {
        return String.format("%.2f, %.2f, %.2f", this.position.x(), this.position.y(), this.position.z());
    }

    public ResourceKey<Level> dimension() {
        return this.dimension;
    }

    public Vec3 position() {
        return this.position;
    }

    static {
        STREAM_CODEC = StreamCodec.composite(ResourceKey.streamCodec(Registries.DIMENSION), GlobalVec3::dimension, NBTHelpers.VEC3_STREAM_CODEC, GlobalVec3::position, GlobalVec3::new);
    }
}

