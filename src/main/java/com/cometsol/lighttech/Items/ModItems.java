package com.cometsol.lighttech.Items;

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

    public static final DeferredItem<Item> CLEARCRYSTALDUST = ITEMS.registerItem("clearcrystaldust",Item::new,
            new Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("lighttech","clearcrystaldust"))));
    public static final DeferredItem<Item> CLEARCRYSTALSHARD = ITEMS.registerItem("clearcrystalshard",Item::new,
            new Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("lighttech","clearcrystalshard"))));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
