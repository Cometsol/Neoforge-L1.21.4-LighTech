package com.cometsol.lighttech.Items.utilita_tools;

import com.cometsol.lighttech.Blocks.ModBlocks;
import com.cometsol.lighttech.Items.ModItems;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import java.util.Map;

import static com.cometsol.lighttech.Blocks.ModBlocks.FACING;

public class CrystalResonator extends Item {

    private static final Map<Block, Block>  CRYSTAL_MAP =
            Map.of(
                    ModBlocks.CLEARCRYSTALCLUSTER.get(), ModBlocks.CLEARCRYSTALCLUSTER_STAGE_4.get(),
                    ModBlocks.CLEARCRYSTALCLUSTER_STAGE_4.get(), ModBlocks.CLEARCRYSTALCLUSTER_STAGE_3.get(),
                    ModBlocks.CLEARCRYSTALCLUSTER_STAGE_3.get(), ModBlocks.CLEARCRYSTALCLUSTER_STAGE_2.get(),
                    ModBlocks.CLEARCRYSTALCLUSTER_STAGE_2.get(), ModBlocks.CLEARCRYSTALCLUSTER_STAGE_1.get(),
                    ModBlocks.CLEARCRYSTALCLUSTER_STAGE_1.get(), ModBlocks.CLEARCRYSTALCLUSTER_STAGE_0.get()
            );

    public CrystalResonator(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickBlock = level.getBlockState(context.getClickedPos()).getBlock();

        if(CRYSTAL_MAP.containsKey(clickBlock)) {
            if(!level.isClientSide()){
                Direction face = level.getBlockState(context.getClickedPos()).getValue(AmethystClusterBlock.FACING);
                level.setBlockAndUpdate(context.getClickedPos(), CRYSTAL_MAP.get(clickBlock).defaultBlockState().setValue(FACING, face));
                if(context.getItemInHand().nextDamageWillBreak()){
                    context.getPlayer().getInventory().add(new ItemStack(ModItems.RESONATOR.get()));
                }
                context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), context.getPlayer(),
                        item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
                context.getPlayer().getInventory().add(new ItemStack(ModItems.CLEARCRYSTALSHARD.get()));
            }
        }
        return InteractionResult.SUCCESS;
    }
}
