package com.cometsol.lighttech.Items;

import com.cometsol.lighttech.Blocks.ModBlocks;
import com.cometsol.lighttech.LightTech;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LightTech.MODID);

    public static final Supplier<CreativeModeTab> LIGHTTECH_ITEMS = CREATIVE_MODE_TAB.register("lighttech_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.CLEARCRYSTALSHARD.get()))
                    .title(Component.translatable("creativetab.lighttech.lighttech_items"))
                    .displayItems((itemDisplayParameters, output) ->{
                      output.accept(ModItems.CLEARCRYSTALSHARD);
                      output.accept(ModItems.CLEARCRYSTALDUST);
                    }).build());

    public static final Supplier<CreativeModeTab> LIGHTTECH_BLOCKS = CREATIVE_MODE_TAB.register("lighttech_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.CLEARCRYSTAL_BLOCK))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "lighttech_items_tab"))
                    .title(Component.translatable("creativetab.lighttech.lighttech_blocks"))
                    .displayItems((itemDisplayParameters, output) ->{
                        output.accept(ModBlocks.CLEARCRYSTAL_BLOCK);
                        output.accept(ModBlocks.CLEARCRYSTALGLASS);
                        output.accept(ModBlocks.CLEARCRYSTALCluster);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }

}
