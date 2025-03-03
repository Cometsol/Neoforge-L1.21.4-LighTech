package com.cometsol.lighttech.Common.Items.tools;

import com.cometsol.lighttech.Common.Blocks.ModBlocks;
import com.cometsol.lighttech.Common.Items.ModItems;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;

import java.util.Map;

import static com.cometsol.lighttech.Common.Blocks.ModBlocks.FACING;

public class CrystalResonator extends Item {

    private static final Map<Block, Block>  CLEARCRYSTAL_MAP =
            Map.of(
                    ModBlocks.CLEARCRYSTALCLUSTER.get(), ModBlocks.CLEARCRYSTALCLUSTER_STAGE_4.get(),
                    ModBlocks.CLEARCRYSTALCLUSTER_STAGE_4.get(), ModBlocks.CLEARCRYSTALCLUSTER_STAGE_3.get(),
                    ModBlocks.CLEARCRYSTALCLUSTER_STAGE_3.get(), ModBlocks.CLEARCRYSTALCLUSTER_STAGE_2.get(),
                    ModBlocks.CLEARCRYSTALCLUSTER_STAGE_2.get(), ModBlocks.CLEARCRYSTALCLUSTER_STAGE_1.get(),
                    ModBlocks.CLEARCRYSTALCLUSTER_STAGE_1.get(), ModBlocks.CLEARCRYSTALCLUSTER_STAGE_0.get()
            );
    private static final Map<Block, Block>  FIERYCRYSTAL_MAP =
            Map.of(
                    ModBlocks.FIERYCRYSTALCLUSTER.get(), ModBlocks.FIERYCRYSTALCLUSTER_STAGE_4.get(),
                    ModBlocks.FIERYCRYSTALCLUSTER_STAGE_4.get(), ModBlocks.FIERYCRYSTALCLUSTER_STAGE_3.get(),
                    ModBlocks.FIERYCRYSTALCLUSTER_STAGE_3.get(), ModBlocks.FIERYCRYSTALCLUSTER_STAGE_2.get(),
                    ModBlocks.FIERYCRYSTALCLUSTER_STAGE_2.get(), ModBlocks.FIERYCRYSTALCLUSTER_STAGE_1.get(),
                    ModBlocks.FIERYCRYSTALCLUSTER_STAGE_1.get(), ModBlocks.FIERYCRYSTALCLUSTER_STAGE_0.get()
            );
    private static final Map<Block, Block>  VOIDCRYSTAL_MAP =
            Map.of(
                    ModBlocks.VOIDCRYSTALCLUSTER.get(), ModBlocks.VOIDCRYSTALCLUSTER_STAGE_4.get(),
                    ModBlocks.VOIDCRYSTALCLUSTER_STAGE_4.get(), ModBlocks.VOIDCRYSTALCLUSTER_STAGE_3.get(),
                    ModBlocks.VOIDCRYSTALCLUSTER_STAGE_3.get(), ModBlocks.VOIDCRYSTALCLUSTER_STAGE_2.get(),
                    ModBlocks.VOIDCRYSTALCLUSTER_STAGE_2.get(), ModBlocks.VOIDCRYSTALCLUSTER_STAGE_1.get(),
                    ModBlocks.VOIDCRYSTALCLUSTER_STAGE_1.get(), ModBlocks.VOIDCRYSTALCLUSTER_STAGE_0.get()
            );

    public CrystalResonator(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickBlock = level.getBlockState(context.getClickedPos()).getBlock();

        if(CLEARCRYSTAL_MAP.containsKey(clickBlock)) {
            if (!level.isClientSide()) {
                Direction face = level.getBlockState(context.getClickedPos()).getValue(AmethystClusterBlock.FACING);
                level.setBlockAndUpdate(context.getClickedPos(), CLEARCRYSTAL_MAP.get(clickBlock).defaultBlockState().setValue(FACING, face));
                if (context.getItemInHand().nextDamageWillBreak()) {
                    context.getPlayer().getInventory().add(new ItemStack(ModItems.RESONATOR.get()));
                }
                context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), context.getPlayer(),
                        item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
                context.getPlayer().getInventory().add(new ItemStack(ModItems.CLEARCRYSTALSHARD.get()));
            }
        } else if (FIERYCRYSTAL_MAP.containsKey(clickBlock)) {
            if (!level.isClientSide()) {
                Direction face = level.getBlockState(context.getClickedPos()).getValue(AmethystClusterBlock.FACING);
                level.setBlockAndUpdate(context.getClickedPos(), FIERYCRYSTAL_MAP.get(clickBlock).defaultBlockState().setValue(FACING, face));
                if (context.getItemInHand().nextDamageWillBreak()) {
                    context.getPlayer().getInventory().add(new ItemStack(ModItems.RESONATOR.get()));
                }
                context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), context.getPlayer(),
                        item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
                context.getPlayer().getInventory().add(new ItemStack(ModItems.FIERYCRYSTALSHARD.get()));
            }
        } else if (VOIDCRYSTAL_MAP.containsKey(clickBlock)) {
            if (!level.isClientSide()) {
                Direction face = level.getBlockState(context.getClickedPos()).getValue(AmethystClusterBlock.FACING);
                level.setBlockAndUpdate(context.getClickedPos(), VOIDCRYSTAL_MAP.get(clickBlock).defaultBlockState().setValue(FACING, face));
                if (context.getItemInHand().nextDamageWillBreak()) {
                    context.getPlayer().getInventory().add(new ItemStack(ModItems.RESONATOR.get()));
                }
                context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), context.getPlayer(),
                        item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
                context.getPlayer().getInventory().add(new ItemStack(ModItems.VOIDCRYSTALSHARD.get()));
            }
        }
        return InteractionResult.SUCCESS;
    }
}
