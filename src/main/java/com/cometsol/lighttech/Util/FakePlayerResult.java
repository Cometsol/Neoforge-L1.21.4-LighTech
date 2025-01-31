//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.cometsol.lighttech.Util;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;

public record FakePlayerResult(InteractionResult interactionResult, ItemStack returnStack) {
    public FakePlayerResult(InteractionResult interactionResult, ItemStack returnStack) {
        this.interactionResult = interactionResult;
        this.returnStack = returnStack;
    }

    public InteractionResult interactionResult() {
        return this.interactionResult;
    }

    public ItemStack returnStack() {
        return this.returnStack;
    }
}
