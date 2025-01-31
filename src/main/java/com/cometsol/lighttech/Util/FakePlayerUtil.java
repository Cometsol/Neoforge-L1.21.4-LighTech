//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.cometsol.lighttech.Util;

import java.util.List;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket.Action;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ClipContext.Block;
import net.minecraft.world.level.ClipContext.Fluid;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.HitResult.Type;
import net.neoforged.neoforge.common.CommonHooks;
import org.jetbrains.annotations.Nullable;

public class FakePlayerUtil {
    public FakePlayerUtil() {
    }

    protected static void addAttributes(UsefulFakePlayer player, ItemStack itemStack, EquipmentSlot equipmentSlot) {
        AttributeMap attributemap = player.getAttributes();
        if (!itemStack.isEmpty()) {
            itemStack.forEachModifier(equipmentSlot, (p_332602_, p_332603_) -> {
                AttributeInstance attributeinstance = attributemap.getInstance(p_332602_);
                if (attributeinstance != null) {
                    attributeinstance.removeModifier(p_332603_.id());
                    attributeinstance.addTransientModifier(p_332603_);
                }

            });
        }

    }

    protected static void removeAttributes(UsefulFakePlayer player, ItemStack itemStack, EquipmentSlot equipmentSlot) {
        AttributeMap attributemap = player.getAttributes();
        if (!itemStack.isEmpty()) {
            itemStack.forEachModifier(equipmentSlot, (p_330002_, p_330003_) -> {
                AttributeInstance attributeinstance = attributemap.getInstance(p_330002_);
                if (attributeinstance != null) {
                    attributeinstance.removeModifier(p_330003_);
                }

            });
        }

    }

    public static void setupFakePlayerForUse(UsefulFakePlayer player, Vec3 pos, Direction direction, ItemStack toHold, boolean sneaking) {
        player.getInventory().items.set(player.getInventory().selected, toHold);
        float xRot = direction == Direction.DOWN ? 90.0F : (direction == Direction.UP ? -90.0F : 0.0F);
        player.setXRot(xRot);
        player.setYRot(direction.toYRot());
        player.setYHeadRot(direction.toYRot());
        Direction.Axis a = direction.getAxis();
        Direction.AxisDirection ad = direction.getAxisDirection();
        double x = a == Axis.X ? (ad == AxisDirection.NEGATIVE ? 0.95 : 0.05) : (double)0.5F;
        double y = a == Axis.Y ? (ad == AxisDirection.NEGATIVE ? 0.95 : 0.05) : (double)0.5F;
        double z = a == Axis.Z ? (ad == AxisDirection.NEGATIVE ? 0.95 : 0.05) : (double)0.5F;
        player.setPos(pos.x() + x, pos.y() + y - (double)player.getEyeHeight(), pos.z() + z);
        if (!toHold.isEmpty()) {
            addAttributes(player, toHold, EquipmentSlot.MAINHAND);
        }

        player.setShiftKeyDown(sneaking);
    }

    public static void setupFakePlayerForUse(UsefulFakePlayer player, BlockPos pos, Direction direction, ItemStack toHold, boolean sneaking) {
        player.getInventory().items.set(player.getInventory().selected, toHold);
        float xRot = direction == Direction.DOWN ? 90.0F : (direction == Direction.UP ? -90.0F : 0.0F);
        player.absRotateTo(direction.toYRot(), xRot);
        player.setYHeadRot(direction.toYRot());
        player.yHeadRotO = direction.toYRot();
        Direction.Axis a = direction.getAxis();
        Direction.AxisDirection ad = direction.getAxisDirection();
        double x = a == Axis.X ? (ad == AxisDirection.NEGATIVE ? 0.95 : 0.05) : (double)0.5F;
        double y = a == Axis.Y ? (ad == AxisDirection.NEGATIVE ? 0.95 : 0.05) : (double)0.5F;
        double z = a == Axis.Z ? (ad == AxisDirection.NEGATIVE ? 0.95 : 0.05) : (double)0.5F;
        player.absMoveTo((double)pos.getX() + x, (double)pos.getY() + y - (double)player.getEyeHeight(), (double)pos.getZ() + z);
        if (!toHold.isEmpty()) {
            addAttributes(player, toHold, EquipmentSlot.MAINHAND);
        }

        player.setShiftKeyDown(sneaking);
    }

    public static void setupFakePlayerForUse(UsefulFakePlayer player, BlockPos pos, Direction direction, Vec3 entityPosition, ItemStack toHold, boolean sneaking) {
        player.getInventory().items.set(player.getInventory().selected, toHold);
        float xRot = direction == Direction.DOWN ? 90.0F : (direction == Direction.UP ? -90.0F : 0.0F);
        player.setXRot(xRot);
        player.setYRot(direction.toYRot());
        player.setYHeadRot(direction.toYRot());
        Direction.Axis a = direction.getAxis();
        Direction.AxisDirection ad = direction.getAxisDirection();
        double x = a == Axis.X ? (ad == AxisDirection.NEGATIVE ? 0.95 : 0.05) : (double)0.5F;
        double y = a == Axis.Y ? (ad == AxisDirection.NEGATIVE ? 0.95 : 0.05) : (double)0.5F;
        double z = a == Axis.Z ? (ad == AxisDirection.NEGATIVE ? 0.95 : 0.05) : (double)0.5F;
        player.setPos((double)pos.getX() + x, (double)pos.getY() + y - (double)player.getEyeHeight(), (double)pos.getZ() + z);
        if (!toHold.isEmpty()) {
            addAttributes(player, toHold, EquipmentSlot.MAINHAND);
        }

        player.setShiftKeyDown(sneaking);
        Vec3 playerEyePos = new Vec3(player.getX(), player.getY() + (double)player.getEyeHeight(), player.getZ());
        Vec3 toEntity = entityPosition.subtract(playerEyePos).normalize();
        float yaw = (float)Math.toDegrees(Math.atan2(toEntity.z, toEntity.x)) - 90.0F;
        float pitch = (float)Math.toDegrees(-Math.atan2(toEntity.y, Math.sqrt(toEntity.x * toEntity.x + toEntity.z * toEntity.z)));
        player.setYRot(yaw);
        player.setYHeadRot(yaw);
        player.setXRot(pitch);
    }

    public static void cleanupFakePlayerFromUse(UsefulFakePlayer player, ItemStack oldStack) {
        if (!oldStack.isEmpty()) {
            removeAttributes(player, oldStack, EquipmentSlot.MAINHAND);
        }

        player.getInventory().items.set(player.getInventory().selected, ItemStack.EMPTY);
        if (!player.getInventory().isEmpty()) {
            player.getInventory().dropAll();
        }

        player.setShiftKeyDown(false);
        player.setReach(player.getAttributeValue(Attributes.BLOCK_INTERACTION_RANGE));
    }

    public static FakePlayerResult clickEntityInDirection(UsefulFakePlayer player, Level world, LivingEntity entity, int clickType, int maxHold) {
        HitResult toUse = rayTraceEntity(player, world, player.getReach());
        if (toUse == null) {
            return new FakePlayerResult(InteractionResult.FAIL, player.getMainHandItem());
        } else if (clickType == 2) {
            ItemStack itemstack = player.getMainHandItem();
            if (itemstack.isEmpty()) {
                player.stopUsingItem();
                return new FakePlayerResult(InteractionResult.FAIL, player.getMainHandItem());
            } else {
                if (!player.isUsingItem()) {
                    player.startUsingItem(InteractionHand.MAIN_HAND);
                }

                player.fakeupdateUsingItem(itemstack);
                int holdingFor = player.getTicksUsingItem();
                if (holdingFor >= maxHold) {
                    player.releaseUsingItem();
                    return new FakePlayerResult(InteractionResult.SUCCESS, player.getMainHandItem());
                } else {
                    return new FakePlayerResult(InteractionResult.SUCCESS, player.getMainHandItem());
                }
            }
        } else {
            if (clickType == 0) {
                if (processUseEntity(player, world, entity, toUse, com.cometsol.lighttech.Util.InteractionType.INTERACT_AT)) {
                    return new FakePlayerResult(InteractionResult.SUCCESS, player.getMainHandItem());
                }

                if (processUseEntity(player, world, entity, (HitResult)null, com.cometsol.lighttech.Util.InteractionType.INTERACT)) {
                    return new FakePlayerResult(InteractionResult.SUCCESS, player.getMainHandItem());
                }
            } else if (processUseEntity(player, world, ((EntityHitResult)toUse).getEntity(), (HitResult)null, com.cometsol.lighttech.Util.InteractionType.ATTACK)) {
                return new FakePlayerResult(InteractionResult.SUCCESS, player.getMainHandItem());
            }

            return new FakePlayerResult(InteractionResult.FAIL, player.getMainHandItem());
        }
    }

    public static FakePlayerResult clickBlockInDirection(UsefulFakePlayer player, Level world, int clickType, int maxHold) {
        HitResult toUse = rayTraceBlock(player, world, player.getReach());
        if (toUse == null) {
            return new FakePlayerResult(InteractionResult.FAIL, player.getMainHandItem());
        } else {
            ItemStack itemstack = player.getMainHandItem();
            if (clickType == 2) {
                if (itemstack.isEmpty()) {
                    player.stopUsingItem();
                    return new FakePlayerResult(InteractionResult.FAIL, player.getMainHandItem());
                } else {
                    if (!player.isUsingItem()) {
                        player.startUsingItem(InteractionHand.MAIN_HAND);
                    }

                    player.fakeupdateUsingItem(itemstack);
                    int holdingFor = player.getTicksUsingItem();
                    if (holdingFor >= maxHold) {
                        player.releaseUsingItem();
                        return new FakePlayerResult(InteractionResult.SUCCESS, player.getMainHandItem());
                    } else {
                        return new FakePlayerResult(InteractionResult.SUCCESS, player.getMainHandItem());
                    }
                }
            } else {
                if (toUse.getType() == Type.BLOCK) {
                    BlockPos blockpos = ((BlockHitResult)toUse).getBlockPos();
                    BlockState state = world.getBlockState(blockpos);
                    if (!state.isAir()) {
                        if (clickType != 0) {
                            player.gameMode.handleBlockBreakAction(blockpos, Action.START_DESTROY_BLOCK, ((BlockHitResult)toUse).getDirection(), player.level().getHeight(), 0);
                            return new FakePlayerResult(InteractionResult.SUCCESS, player.getMainHandItem());
                        }

                        InteractionResult type = player.gameMode.useItemOn(player, world, itemstack, InteractionHand.MAIN_HAND, (BlockHitResult)toUse);
                        if (type == InteractionResult.SUCCESS || type == InteractionResult.CONSUME) {
                            return new FakePlayerResult(InteractionResult.SUCCESS, player.getMainHandItem());
                        }
                    }
                }

                if (toUse.getType() == Type.MISS && clickType == 0) {
                    InteractionResult type = player.gameMode.useItemOn(player, world, itemstack, InteractionHand.MAIN_HAND, (BlockHitResult)toUse);
                    if (type == InteractionResult.SUCCESS || type == InteractionResult.CONSUME) {
                        return new FakePlayerResult(InteractionResult.SUCCESS, player.getMainHandItem());
                    }
                }

                if (!itemstack.isEmpty()) {
                    InteractionResult type = player.gameMode.useItem(player, world, itemstack, InteractionHand.MAIN_HAND);
                    if (type == InteractionResult.SUCCESS || type == InteractionResult.CONSUME) {
                        return new FakePlayerResult(InteractionResult.SUCCESS, player.getMainHandItem());
                    }
                }

                return new FakePlayerResult(InteractionResult.FAIL, player.getMainHandItem());
            }
        }
    }

    public static FakePlayerResult rightClickAirInDirection(UsefulFakePlayer player, Level world, int clickType, int maxHold) {
        HitResult toUse = rayTraceBlock(player, world, player.getReach());
        if (toUse == null) {
            new FakePlayerResult(InteractionResult.FAIL, player.getMainHandItem());
        }

        ItemStack itemstack = player.getMainHandItem();
        if (clickType == 2) {
            if (itemstack.isEmpty()) {
                player.stopUsingItem();
                return new FakePlayerResult(InteractionResult.FAIL, player.getMainHandItem());
            } else {
                if (!player.isUsingItem()) {
                    player.startUsingItem(InteractionHand.MAIN_HAND);
                }

                player.fakeupdateUsingItem(itemstack);
                int holdingFor = player.getTicksUsingItem();
                if (holdingFor >= maxHold) {
                    player.releaseUsingItem();
                    return new FakePlayerResult(InteractionResult.SUCCESS, player.getMainHandItem());
                } else {
                    return new FakePlayerResult(InteractionResult.SUCCESS, player.getMainHandItem());
                }
            }
        } else {
            if (toUse.getType() == Type.BLOCK) {
                BlockPos blockpos = ((BlockHitResult)toUse).getBlockPos();
                BlockState state = world.getBlockState(blockpos);
                if (!state.isAir()) {
                    InteractionResult type = player.gameMode.useItemOn(player, world, itemstack, InteractionHand.MAIN_HAND, (BlockHitResult)toUse);
                    if (type == InteractionResult.SUCCESS || type == InteractionResult.CONSUME) {
                        return new FakePlayerResult(InteractionResult.SUCCESS, player.getMainHandItem());
                    }
                }
            }

            if (toUse.getType() == Type.MISS) {
            }

            if (!itemstack.isEmpty()) {
                InteractionResult type = player.gameMode.useItem(player, world, itemstack, InteractionHand.MAIN_HAND);
                if (type == InteractionResult.SUCCESS || type == InteractionResult.CONSUME) {
                    return new FakePlayerResult(InteractionResult.SUCCESS, player.getMainHandItem());
                }
            }

            return new FakePlayerResult(InteractionResult.FAIL, player.getMainHandItem());
        }
    }

    public static ItemStack leftClickInDirection(UsefulFakePlayer player, Level world, BlockPos pos, Direction side, BlockState sourceState) {
        HitResult toUse = rayTrace(player, world, player.getReach());
        if (toUse == null) {
            return player.getMainHandItem();
        } else {
            if (toUse.getType() == Type.ENTITY) {
                if (processUseEntity(player, world, ((EntityHitResult)toUse).getEntity(), (HitResult)null, InteractionType.ATTACK)) {
                    return player.getMainHandItem();
                }
            } else if (toUse.getType() == Type.BLOCK) {
                BlockPos blockpos = ((BlockHitResult)toUse).getBlockPos();
                BlockState state = world.getBlockState(blockpos);
                if (state != sourceState && !state.isAir()) {
                    player.gameMode.handleBlockBreakAction(blockpos, Action.START_DESTROY_BLOCK, ((BlockHitResult)toUse).getDirection(), player.level().getHeight(), 0);
                    return player.getMainHandItem();
                }
            }

            if (toUse == null || toUse.getType() == Type.MISS) {
                for(int i = 1; i <= 5; ++i) {
                    BlockState state = world.getBlockState(pos.relative(side, i));
                    if (state != sourceState && !state.isAir()) {
                        player.gameMode.handleBlockBreakAction(pos.relative(side, i), Action.START_DESTROY_BLOCK, side.getOpposite(), player.level().getHeight(), 0);
                        return player.getMainHandItem();
                    }
                }
            }

            return player.getMainHandItem();
        }
    }

    public static HitResult traceEntities(UsefulFakePlayer player, Vec3 base, Vec3 target, Level world) {
        Entity pointedEntity = null;
        HitResult result = null;
        Vec3 vec3d3 = null;
        AABB search = (new AABB(base.x, base.y, base.z, target.x, target.y, target.z)).inflate((double)0.5F, (double)0.5F, (double)0.5F);
        List<Entity> list = world.getEntities(player, search, (entity) -> EntitySelector.NO_SPECTATORS.test(entity) && entity != null && entity.isPickable());
        double d2 = (double)5.0F;

        for(int j = 0; j < list.size(); ++j) {
            Entity entity1 = (Entity)list.get(j);
            AABB aabb = entity1.getBoundingBox().inflate((double)entity1.getPickRadius());
            Optional<Vec3> optVec = aabb.clip(base, target);
            if (aabb.contains(base)) {
                if (d2 >= (double)0.0F) {
                    pointedEntity = entity1;
                    vec3d3 = (Vec3)optVec.orElse(base);
                    d2 = (double)0.0F;
                }
            } else if (optVec.isPresent()) {
                double d3 = base.distanceTo((Vec3)optVec.get());
                if (d3 < d2 || d2 == (double)0.0F) {
                    if (entity1.getRootVehicle() == player.getRootVehicle() && !entity1.canRiderInteract()) {
                        if (d2 == (double)0.0F) {
                            pointedEntity = entity1;
                            vec3d3 = (Vec3)optVec.get();
                        }
                    } else {
                        pointedEntity = entity1;
                        vec3d3 = (Vec3)optVec.get();
                        d2 = d3;
                    }
                }
            }
        }

        if (pointedEntity != null && base.distanceTo(vec3d3) > (double)5.0F) {
            pointedEntity = null;
            result = BlockHitResult.miss(vec3d3, (Direction)null, BlockPos.containing(vec3d3));
        }

        if (pointedEntity != null) {
            result = new EntityHitResult(pointedEntity, vec3d3);
        }

        return result;
    }

    public static boolean processUseEntity(UsefulFakePlayer player, Level world, Entity entity, @Nullable HitResult result, InteractionType action) {
        if (entity != null && player.distanceToSqr(entity) < (double)36.0F) {
            if (action == com.cometsol.lighttech.Util.InteractionType.INTERACT) {
                return player.interactOn(entity, InteractionHand.MAIN_HAND) == InteractionResult.SUCCESS;
            }

            if (action == com.cometsol.lighttech.Util.InteractionType.INTERACT_AT) {
                if (CommonHooks.onInteractEntityAt(player, entity, result.getLocation(), InteractionHand.MAIN_HAND) != null) {
                    return false;
                }

                return entity.interactAt(player, result.getLocation(), InteractionHand.MAIN_HAND) == InteractionResult.SUCCESS;
            }

            if (action == com.cometsol.lighttech.Util.InteractionType.ATTACK) {
                if (!(entity instanceof ItemEntity) && !(entity instanceof ExperienceOrb) && !(entity instanceof Arrow) && entity != player) {
                    player.attack(entity);
                    return true;
                }

                return false;
            }
        }

        return false;
    }

    public static HitResult rayTrace(UsefulFakePlayer player, Level level, double reachDist) {
        Vec3 base = new Vec3(player.getX(), player.getEyeY(), player.getZ());
        Vec3 look = player.getLookAngle();
        Vec3 target = base.add(look.x * reachDist, look.y * reachDist, look.z * reachDist);
        HitResult trace = level.clip(new ClipContext(base, target, Block.OUTLINE, Fluid.SOURCE_ONLY, player));
        HitResult traceEntity = traceEntities(player, base, target, level);
        HitResult toUse = trace == null ? traceEntity : trace;
        if (trace != null && traceEntity != null) {
            double d1 = trace.getLocation().distanceTo(base);
            double d2 = traceEntity.getLocation().distanceTo(base);
            toUse = traceEntity.getType() == Type.ENTITY && d1 > d2 ? traceEntity : trace;
        }

        return toUse;
    }

    public static HitResult rayTraceBlock(UsefulFakePlayer player, Level level, double reachDist) {
        Vec3 base = new Vec3(player.getX(), player.getEyeY(), player.getZ());
        Vec3 look = player.getLookAngle();
        Vec3 target = base.add(look.x * reachDist, look.y * reachDist, look.z * reachDist);
        HitResult trace = level.clip(new ClipContext(base, target, Block.OUTLINE, Fluid.SOURCE_ONLY, player));
        return trace;
    }

    public static HitResult rayTraceEntity(UsefulFakePlayer player, Level level, double reachDist) {
        Vec3 base = new Vec3(player.getX(), player.getEyeY(), player.getZ());
        Vec3 look = player.getLookAngle();
        Vec3 target = base.add(look.x * reachDist, look.y * reachDist, look.z * reachDist);
        HitResult traceEntity = traceEntities(player, base, target, level);
        return traceEntity;
    }

}
