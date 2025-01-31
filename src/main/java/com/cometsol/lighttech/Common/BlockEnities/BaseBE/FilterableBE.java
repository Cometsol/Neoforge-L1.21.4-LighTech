package com.cometsol.lighttech.Common.BlockEnities.BaseBE;

import com.cometsol.lighttech.Common.Handlers.FilterBasicHandler;
import com.cometsol.lighttech.Util.ItemStackKey;
import com.cometsol.lighttech.Util.Interfacehelpers.FilterData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;

public interface FilterableBE {
    FilterBasicHandler getFilterHandler();

    FilterData getFilterData();

    default void setFilterData(FilterData filterData) {
        FilterData existingData = this.getFilterData();
    }

    BlockEntity getBlockEntity();

    default void saveFilterSettings(CompoundTag tag) {
        tag.putBoolean("allowlist", this.getFilterData().allowlist);
        tag.putBoolean("compareNBT", this.getFilterData().compareNBT);
        tag.putInt("blockitemfilter", this.getFilterData().blockItemFilter);
    }

    default void loadFilterSettings(CompoundTag tag) {
        this.getFilterData().allowlist = tag.getBoolean("allowlist");
        this.getFilterData().compareNBT = tag.getBoolean("compareNBT");
        int blockItemFilter = tag.getInt("blockitemfilter");
        if (blockItemFilter != -1 && this.getFilterData().blockItemFilter != -1) {
            this.getFilterData().blockItemFilter = blockItemFilter;
        }

    }

    default void setFilterSettings(FilterData filterData) {
        this.getFilterData().allowlist = filterData.allowlist;
        this.getFilterData().compareNBT = filterData.compareNBT;
        this.getFilterData().blockItemFilter = filterData.blockItemFilter;
        BlockEntity var3 = this.getBlockEntity();
        if (var3 instanceof MachineEntityBase machineEntityBase) {
            machineEntityBase.markDirtyClient();
        }

    }

    default boolean isStackValidFilter(ItemStack testStack) {
        ItemStackKey key = new ItemStackKey(testStack, this.getFilterData().compareNBT);
        if (this.getFilterData().filterCache.containsKey(key)) {
            return (Boolean)this.getFilterData().filterCache.get(key);
        } else {
            FilterBasicHandler filteredItems = this.getFilterHandler();

            for(int i = 0; i < filteredItems.getSlots(); ++i) {
                ItemStack stack = filteredItems.getStackInSlot(i);
                if (!stack.isEmpty() && key.equals(new ItemStackKey(stack, this.getFilterData().compareNBT))) {
                    this.getFilterData().filterCache.put(key, this.getFilterData().allowlist);
                    return this.getFilterData().allowlist;
                }
            }

            this.getFilterData().filterCache.put(key, !this.getFilterData().allowlist);
            return !this.getFilterData().allowlist;
        }
    }

    /*default boolean isEntityValidFilter(Entity entity, Level level) {
        if (this.getFilterData().entityCache.containsKey(entity)) {
            return (Boolean)this.getFilterData().entityCache.get(entity);
        } else {
            FilterBasicHandler filteredItems = this.getFilterHandler();

            for(int i = 0; i < filteredItems.getSlots(); ++i) {
                ItemStack stack = filteredItems.getStackInSlot(i);
                if (!stack.isEmpty()) {
                    if (stack.getItem() instanceof SpawnEggItem) {
                        Item entityEgg = SpawnEggItem.byId(entity.getType());
                        if (entityEgg != null && stack.is(entityEgg)) {
                            this.getFilterData().entityCache.put(entity, this.getFilterData().allowlist);
                            return this.getFilterData().allowlist;
                        }
                    } else {
                        Item var10 = stack.getItem();
                        if (var10 instanceof CreatureCatcher) {
                            CreatureCatcher creatureCatcher = (CreatureCatcher)var10;
                            Mob mob = CreatureCatcherEntity.getEntityFromItemStack(stack, level);
                            if (mob != null && entity.getType().equals(mob.getType())) {
                                if (!this.getFilterData().compareNBT) {
                                    this.getFilterData().entityCache.put(entity, this.getFilterData().allowlist);
                                    return this.getFilterData().allowlist;
                                }

                                CompoundTag filterTag = this.getNormalizedTag(mob);
                                CompoundTag targetTag = this.getNormalizedTag(entity);
                                if (filterTag.equals(targetTag)) {
                                    this.getFilterData().entityCache.put(entity, this.getFilterData().allowlist);
                                    return this.getFilterData().allowlist;
                                }
                            }
                        }
                    }
                }
            }

            this.getFilterData().entityCache.put(entity, !this.getFilterData().allowlist);
            return !this.getFilterData().allowlist;
        }
    }*/

    default CompoundTag getNormalizedTag(Entity entity) {
        CompoundTag tag = new CompoundTag();
        entity.save(tag);
        tag.remove("AbsorptionAmount");
        tag.remove("Age");
        tag.remove("Air");
        tag.remove("ArmorDropChances");
        tag.remove("ArmorItems");
        tag.remove("Brain");
        tag.remove("CanPickUpLoot");
        tag.remove("DeathTime");
        tag.remove("FallDistance");
        tag.remove("FallFlying");
        tag.remove("Fire");
        tag.remove("ForcedAge");
        tag.remove("HandDropChances");
        tag.remove("HandItems");
        tag.remove("HurtByTimestamp");
        tag.remove("HurtTime");
        tag.remove("InLove");
        tag.remove("Invulnerable");
        tag.remove("LeftHanded");
        tag.remove("Motion");
        tag.remove("OnGround");
        tag.remove("PersistenceRequired");
        tag.remove("PortalCooldown");
        tag.remove("Pos");
        tag.remove("Rotation");
        tag.remove("UUID");
        tag.remove("attributes");
        tag.remove("id");
        tag.remove("NoAI");
        tag.remove("Silent");
        tag.remove("Glowing");
        tag.remove("Tags");
        tag.remove("Passengers");
        tag.remove("Leashed");
        tag.remove("Leash");
        tag.remove("CustomName");
        tag.remove("FireTicks");
        tag.remove("Dimension");
        tag.remove("HasVisualFire");
        tag.remove("ActiveEffects");
        tag.remove("FromBucket");
        tag.remove("neoforge:spawn_type");
        return tag;
    }

    default boolean isStackValidFilter(LiquidBlock liquidBlock) {
        ItemStack testStack = new ItemStack(liquidBlock.fluid.getBucket());
        ItemStackKey key = new ItemStackKey(testStack, this.getFilterData().compareNBT);
        if (this.getFilterData().filterCache.containsKey(key)) {
            return (Boolean)this.getFilterData().filterCache.get(key);
        } else {
            FilterBasicHandler filteredItems = this.getFilterHandler();

            for(int i = 0; i < filteredItems.getSlots(); ++i) {
                ItemStack stack = filteredItems.getStackInSlot(i);
                if (!stack.isEmpty() && key.equals(new ItemStackKey(stack, this.getFilterData().compareNBT))) {
                    this.getFilterData().filterCache.put(key, this.getFilterData().allowlist);
                    return this.getFilterData().allowlist;
                }
            }

            this.getFilterData().filterCache.put(key, !this.getFilterData().allowlist);
            return !this.getFilterData().allowlist;
        }
    }
}
