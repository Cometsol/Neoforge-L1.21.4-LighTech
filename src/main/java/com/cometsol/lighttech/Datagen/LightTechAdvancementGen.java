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

import java.util.List;
import java.util.function.Consumer;

public class LightTechAdvancementGen implements AdvancementSubProvider {
    @Override
    public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> saver) {
        Advancement.Builder builder = Advancement.Builder.advancement();
        builder.parent(AdvancementSubProvider.createPlaceholder("lighttech:story/root")).display(
                new ItemStack((ItemLike) ModItems.CLEARCRYSTALSHARD),
                Component.translatable("advancement.lighttech.first_step.title"),
                Component.translatable("advancement.lighttech.first_step.description"),
                null,
                AdvancementType.GOAL,
                true,
                false,
                true

        );
        builder.rewards(AdvancementRewards.Builder.recipe(ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(LightTech.MODID,"clearcrystal_block"))));
        builder.rewards(AdvancementRewards.Builder.recipe(ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "clearcrystalshard"))));
        builder.rewards(AdvancementRewards.Builder.recipe(ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "clearcrystaldust"))));
        builder.rewards(AdvancementRewards.Builder.recipe(ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "clearcrystalglass"))));
        builder.rewards(AdvancementRewards.Builder.recipe(ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "clearcrystalshard_smelt"))));
        builder.rewards(AdvancementRewards.Builder.recipe(ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "crystalresonator_clear"))));
        builder.rewards(AdvancementRewards.Builder.recipe(ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "crystalresonator_fiery"))));
        builder.rewards(AdvancementRewards.Builder.recipe(ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "crystalresonator_void"))));
        builder.addCriterion("collect_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CLEARCRYSTALSHARD.get()));
        builder.requirements(AdvancementRequirements.allOf(List.of("collect_crystal")));
        builder.save(saver,ResourceLocation.fromNamespaceAndPath(LightTech.MODID, "begninges"));
    }
}

