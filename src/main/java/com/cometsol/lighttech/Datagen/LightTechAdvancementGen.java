package com.cometsol.lighttech.Datagen;

import com.cometsol.lighttech.Common.Items.ModItems;
import com.cometsol.lighttech.LightTech;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class LightTechAdvancementGen implements AdvancementSubProvider {
    @Override
    public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> saver) {
        Advancement.Builder clearcrystal = Advancement.Builder.advancement();
        clearcrystal.parent(AdvancementSubProvider.createPlaceholder("minecraft:story/root")).display(
                new ItemStack((ItemLike) ModItems.CLEARCRYSTALSHARD),
                Component.translatable("advancement.lighttech.first_step.title"),
                Component.translatable("advancement.lighttech.first_step.description"),
                null,
                AdvancementType.GOAL,
                true,
                false,
                true

        ).rewards(AdvancementRewards.Builder.recipe(ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"clearcrystal_block"))))
        .rewards(AdvancementRewards.Builder.recipe(ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "clearcrystalshard"))))
        .rewards(AdvancementRewards.Builder.recipe(ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "clearcrystaldust"))))
        .rewards(AdvancementRewards.Builder.recipe(ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "clearcrystalglass"))))
        .rewards(AdvancementRewards.Builder.recipe(ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "clearcrystalshard_smelt"))))
        .rewards(AdvancementRewards.Builder.recipe(ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "crystalresonator_clear"))))
        .addCriterion("collect_clearcrystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CLEARCRYSTALSHARD.get()))
        .requirements(AdvancementRequirements.allOf(Collections.singleton("collect_clearcrystal")))
        .save(saver,ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "clearbegninges"));

        Advancement.Builder fierycrystal = Advancement.Builder.advancement();
        fierycrystal.parent(AdvancementSubProvider.createPlaceholder("minecraft:story/root")).display(
                new ItemStack((ItemLike) ModItems.FIERYCRYSTALSHARD),
                Component.translatable("advancement.lighttech.second_step.title"),
                Component.translatable("advancement.lighttech.second_step.description"),
                null,
                AdvancementType.GOAL,
                true,
                false,
                true

        )
        .rewards(AdvancementRewards.Builder.recipe(ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"fierycrystal_block"))))
        .rewards(AdvancementRewards.Builder.recipe(ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "fierycrystalshard"))))
        .rewards(AdvancementRewards.Builder.recipe(ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "fierycrystaldust"))))
        .rewards(AdvancementRewards.Builder.recipe(ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "fierycrystalglass"))))
        .rewards(AdvancementRewards.Builder.recipe(ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "fierycrystalshard_smelt"))))
        .rewards(AdvancementRewards.Builder.recipe(ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "crystalresonator_fiery"))))
        .addCriterion("collect_fierycrystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FIERYCRYSTALSHARD.get()))
        .requirements(AdvancementRequirements.allOf(Collections.singleton("collect_fierycrystal")))
        .save(saver,ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "fieryprogresion"));

        Advancement.Builder voidcrystal = Advancement.Builder.advancement();
        voidcrystal.parent(AdvancementSubProvider.createPlaceholder("minecraft:story/root")).display(
                new ItemStack((ItemLike) ModItems.VOIDCRYSTALSHARD),
                Component.translatable("advancement.lighttech.final_step.title"),
                Component.translatable("advancement.lighttech.final_step.description"),
                null,
                AdvancementType.GOAL,
                true,
                false,
                true

        )
        .rewards(AdvancementRewards.Builder.recipe(ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"voidcrystal_block"))))
        .rewards(AdvancementRewards.Builder.recipe(ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "voidcrystalshard"))))
        .rewards(AdvancementRewards.Builder.recipe(ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "voidcrystaldust"))))
        .rewards(AdvancementRewards.Builder.recipe(ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "voidcrystalglass"))))
        .rewards(AdvancementRewards.Builder.recipe(ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "voidcrystalshard_smelt"))))
        .rewards(AdvancementRewards.Builder.recipe(ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "crystalresonator_void"))))
        .addCriterion("collect_voidcrystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.VOIDCRYSTALSHARD.get()))
        .requirements(AdvancementRequirements.allOf(Collections.singleton("collect_voidcrystal")))
        .save(saver,ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "voidends"));
    }
}

