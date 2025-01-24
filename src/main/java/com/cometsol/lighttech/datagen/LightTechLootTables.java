package com.cometsol.lighttech.datagen;

import com.cometsol.lighttech.Blocks.ModBlocks;
import com.cometsol.lighttech.Items.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class LightTechLootTables extends VanillaBlockLoot {
    public LightTechLootTables(HolderLookup.Provider p_344962_) {
        super(p_344962_);
    }

    protected void generate() {
        this.dropSelf((Block) ModBlocks.CLEARCRYSTAL_BLOCK.get());
        this.dropOther((Block) ModBlocks.CLEARCRYSTALGLASS.get(), (ItemLike) this.createSilkTouchDispatchTable((Block)ModBlocks.CLEARCRYSTALGLASS.get(), this.applyExplosionDecay((ItemLike)ModBlocks.CLEARCRYSTALGLASS.get(), LootItem.lootTableItem((ItemLike)ModItems.CLEARCRYSTALDUST.get()))));
        this.dropOther((Block) ModBlocks.CLEARCRYSTAL_Cluster.get(), (ItemLike) LootItem.lootTableItem((ItemLike)ModItems.CLEARCRYSTALSHARD.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F))));


    }
}
