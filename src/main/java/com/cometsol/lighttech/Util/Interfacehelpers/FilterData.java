package com.cometsol.lighttech.Util.Interfacehelpers;

import com.cometsol.lighttech.Util.ItemStackKey;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import net.minecraft.world.entity.Entity;

public class FilterData {
    public boolean allowlist = false;
    public boolean compareNBT = false;
    public int blockItemFilter = -1;
    public final Map<ItemStackKey, Boolean> filterCache = new Object2BooleanOpenHashMap();
    public final WeakHashMap<Entity, Boolean> entityCache = new WeakHashMap();

    public FilterData() {
    }

    public FilterData(boolean allowlist, boolean compareNBT, int blockItemFilter) {
        this.allowlist = allowlist;
        this.compareNBT = compareNBT;
        this.blockItemFilter = blockItemFilter;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.allowlist, this.compareNBT, this.blockItemFilter});
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            FilterData that = (FilterData)o;
            return this.allowlist == that.allowlist && this.compareNBT == that.compareNBT && this.blockItemFilter == that.blockItemFilter;
        } else {
            return false;
        }
    }
}
