package com.cometsol.lighttech.Common.Items.Special;

import com.cometsol.lighttech.Common.Items.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import net.minecraft.network.chat.Component;



public class FieryCrystal extends Item {
    public FieryCrystal(Properties properties) {
        super(properties);
    }
    public void inventoryTick(@NotNull ItemStack itemStack, @NotNull Level world, @NotNull Entity entity, int itemSlot, boolean isSelected) {
        if (!world.isClientSide) {
            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity)entity;
                if (world.random.nextFloat() < 0.005F) {
                    if (this.fieryProtection(entity, world)) {
                        if(livingEntity.hasEffect(MobEffects.HARM)) {
                            livingEntity.removeEffect(MobEffects.HARM);
                        }
                    } else {
                        if(!livingEntity.hasEffect(MobEffects.HARM)) {
                            livingEntity.addEffect(new MobEffectInstance(MobEffects.HARM, 100, 5, false, false));
                        }
                    }
                }
            }

        }
    }
    public boolean fieryProtection(Entity entity, Level level) {
        if (entity instanceof Player player) {
            if (player.hasEffect(MobEffects.FIRE_RESISTANCE)) {
                return true;
            }else if(player.getInventory().contains(new ItemStack((ItemLike) ModItems.LEATHERGLOVES))) {
                int slot = player.getInventory().findSlotMatchingItem(new ItemStack((ItemLike) ModItems.LEATHERGLOVES));
                player.getInventory().getItem(slot).hurtAndBreak(1, ((ServerLevel) level), player,
                        _ -> player.getInventory().getItem(slot));
                return true;
            } else {
                return false;
            }

        }
        return false;
    }

    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, context, tooltip, flagIn);
        Level level = context.level();
        if(level!= null) {
            tooltip.add(Component.translatable("lighttech.fierycrystaltooltip").withStyle(ChatFormatting.RED));
        }
    }
}
