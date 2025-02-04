package com.cometsol.lighttech.Common.Items;

import com.cometsol.lighttech.Common.Items.Special.FieryCrystal;
import com.cometsol.lighttech.Common.Items.tools.CrystalResonator;
import com.cometsol.lighttech.LightTech;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    private static final DeferredRegister.Items BLOCKITEMS = DeferredRegister.createItems(LightTech.MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(LightTech.MODID);
    //items
    public static final DeferredItem<Item> CLEARCRYSTALDUST = ITEMS.registerItem("clearcrystaldust",Item::new, new Properties()
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"clearcrystaldust"))));
    public static final DeferredItem<Item> FIERYCRYSTALDUST = ITEMS.registerItem("fierycrystaldust",Item::new, new Properties()
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"fierycrystaldust"))));
    public static final DeferredItem<Item> VOIDCRYSTALDUST = ITEMS.registerItem("voidcrystaldust",Item::new, new Properties()
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"voidcrystaldust"))));
    public static final DeferredItem<Item> CLEARCRYSTALSHARD = ITEMS.registerItem("clearcrystalshard",Item::new, new Properties()
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"clearcrystalshard"))));
    public static final DeferredItem<Item> FIERYCRYSTALSHARD = ITEMS.registerItem("fierycrystalshard", FieryCrystal::new, new Properties()
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"fierycrystalshard"))));
    public static final DeferredItem<Item> VOIDCRYSTALSHARD = ITEMS.registerItem("voidcrystalshard",Item::new, new Properties()
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"voidcrystalshard"))));

    //tools
    public static final DeferredItem<Item> RESONATOR = ITEMS.registerItem("resonator",Item::new, new Properties()
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"resonator")))
            .stacksTo(1));
    public static final DeferredItem<Item> CRYSTALRESONATOR_CLEAR = ITEMS.registerItem("crystalresonator_clear", CrystalResonator::new, new Properties()
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"crystalresonator_clear")))
            .durability(2).stacksTo(1).setNoCombineRepair());
    public static final DeferredItem<Item> CRYSTALRESONATOR_FIERY = ITEMS.registerItem("crystalresonator_fiery", CrystalResonator::new, new Properties()
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"crystalresonator_fiery")))
            .durability(400).stacksTo(1).setNoCombineRepair());
    public static final DeferredItem<Item> CRYSTALRESONATOR_VOID = ITEMS.registerItem("crystalresonator_void", CrystalResonator::new, new Properties()
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"crystalresonator_void")))
            .durability(600).stacksTo(1).setNoCombineRepair());

    //equipment
    public static final DeferredItem<Item> LEATHERGLOVES = ITEMS.registerItem("leathergloves", Item::new, new Properties()
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "leathergloves")))
            .durability(1000)
            .stacksTo(1));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
