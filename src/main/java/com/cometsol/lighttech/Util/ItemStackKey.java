package com.cometsol.lighttech.Util;

import java.util.Objects;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemStackKey {
    public final Holder<Item> item;
    public final DataComponentPatch dataComponents;
    private final int hash;

    public ItemStackKey(ItemStack stack, boolean compareNBT) {
        this.item = stack.getItemHolder();
        this.dataComponents = compareNBT ? stack.getComponentsPatch() : DataComponentPatch.EMPTY;
        this.hash = Objects.hash(new Object[]{this.item, this.dataComponents});
    }

    public ItemStack getStack() {
        return new ItemStack(this.item, 1, this.dataComponents);
    }

    public ItemStack getStack(int amt) {
        return new ItemStack(this.item, amt, this.dataComponents);
    }

    public int hashCode() {
        return this.hash;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ItemStackKey)) {
            return false;
        } else {
            return ((ItemStackKey)obj).item == this.item && Objects.equals(((ItemStackKey)obj).dataComponents, this.dataComponents);
        }
    }
}
