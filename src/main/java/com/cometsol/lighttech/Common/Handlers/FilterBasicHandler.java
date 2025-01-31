package com.cometsol.lighttech.Common.Handlers;

import javax.annotation.Nonnull;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;

public class FilterBasicHandler extends ItemStackHandler {
    public FilterBasicHandler(int size) {
        super(size);
    }

    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return super.isItemValid(slot, stack);
    }

    public int getSlotLimit(int slot) {
        return 1;
    }
}
