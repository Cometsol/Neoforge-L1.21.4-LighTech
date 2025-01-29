package com.cometsol.lighttech.Items;

import com.cometsol.lighttech.Items.utilita_tools.CrystalResonator;
import com.cometsol.lighttech.LightTech;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(LightTech.MODID);

    public static final DeferredItem<Item> CLEARCRYSTALDUST = ITEMS.registerItem("clearcrystaldust",Item::new, new Properties()
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("lighttech","clearcrystaldust"))));
    public static final DeferredItem<Item> CLEARCRYSTALSHARD = ITEMS.registerItem("clearcrystalshard",Item::new, new Properties()
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("lighttech","clearcrystalshard"))));
    public static final DeferredItem<Item> RESONATOR = ITEMS.registerItem("resonator",Item::new, new Properties()
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("lighttech","resonator")))
            .stacksTo(1));

    public static final DeferredItem<Item> CRYSTALRESONATOR_CLEAR = ITEMS.registerItem("crystalresonator_clear", CrystalResonator::new, new Properties()
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("lighttech_r","crystalresonator_clear")))
            .durability(2)
            .stacksTo(1)
            .setNoCombineRepair());
    public static final DeferredItem<Item> CRYSTALRESONATOR_FIERY = ITEMS.registerItem("crystalresonator_fiery", CrystalResonator::new, new Properties()
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("lighttech_","crystalresonator_fiery")))
            .durability(400)
            .stacksTo(1)
            .setNoCombineRepair());
    public static final DeferredItem<Item> CRYSTALRESONATOR_VOID = ITEMS.registerItem("crystalresonator_void", CrystalResonator::new, new Properties()
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("lighttech_","crystalresonator_void")))
            .durability(600)
            .stacksTo(1)
            .setNoCombineRepair());

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
