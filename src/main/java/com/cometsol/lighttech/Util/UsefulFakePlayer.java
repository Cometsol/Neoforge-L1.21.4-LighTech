//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.cometsol.lighttech.Util;

import com.mojang.authlib.GameProfile;
import java.util.OptionalInt;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.TeleportTransition;
import net.neoforged.neoforge.common.util.FakePlayer;

public class UsefulFakePlayer extends FakePlayer {
    private double reach;

    public UsefulFakePlayer(Level world, GameProfile name) {
        super((ServerLevel)world, name);
        this.setReach(this.getAttributeValue(Attributes.BLOCK_INTERACTION_RANGE));
    }

    public OptionalInt openMenu(MenuProvider p_9033_) {
        return OptionalInt.empty();
    }

    public OptionalInt openMenu(@Nullable MenuProvider menu, @Nullable Consumer<RegistryFriendlyByteBuf> extraDataWriter) {
        return OptionalInt.empty();
    }

    public float getAttackStrengthScale(float adjustTicks) {
        return 1.0F;
    }

    public ItemCooldowns getCooldowns() {
        return new ItemCooldowns();
    }

    public boolean canBeSeenByAnyone() {
        return false;
    }

    public Entity changeDimension(TeleportTransition dimensionTransition) {
        return createPlayer(dimensionTransition.newLevel(), this.getGameProfile());
    }

    public void fakeupdateUsingItem(ItemStack itemStack) {
        this.updateUsingItem(itemStack);
    }

    public double getReach() {
        return this.reach;
    }

    public void setReach(double reach) {
        this.reach = reach;
    }

    public static UsefulFakePlayer createPlayer(Level world, GameProfile profile) {
        return new UsefulFakePlayer(world, profile);
    }

}
