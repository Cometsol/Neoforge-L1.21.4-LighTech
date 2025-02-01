package com.cometsol.lighttech.Common.BlockEnities.BaseBE;

import com.cometsol.lighttech.Common.Handlers.FilterBasicHandler;
import com.cometsol.lighttech.Registration;
import com.cometsol.lighttech.Util.Interfacehelpers.AreaAffectingData;
import com.cometsol.lighttech.Util.Interfacehelpers.FilterData;
import com.cometsol.lighttech.Util.Interfacehelpers.RedstoneControlData;
import com.cometsol.lighttech.Util.MiscHelpersRedstoneMode;
import com.cometsol.lighttech.Util.UsefulFakePlayer;
import com.mojang.authlib.GameProfile;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.common.util.BlockSnapshot;
import net.neoforged.neoforge.common.util.FakePlayer;
import net.neoforged.neoforge.common.util.FakePlayerFactory;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.items.ItemStackHandler;

import java.util.Map;
import java.util.UUID;

public class MachineEntityBase extends BlockEntity {
    public int MACHINE_SLOTS = 0;
    public int ANYSIZE_FILTER_SLOTS = 0;
    public static final UUID defaultFakePlayerUUID = UUID.fromString("20354d7a-e4fe-47af-8ff6-187bca92f3f9");
    public static final GameProfile defaultFakePlayerProfile;
    public UUID placedByUUID;
    protected int direction = 0;
    protected int tickSpeed = 20;
    protected int operationTicks = -1;
    protected UsefulFakePlayer usefulFakePlayer;
    protected final Map<ChunkPos, Boolean> chunkTestCache = new Object2BooleanOpenHashMap();

    public MachineEntityBase(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    public void tickClient() {
    }

    public void tickServer() {
        this.handleTicks();
        this.clearProtectionCache();
        if (this instanceof RedstoneControlledBE redstoneControlledBE) {
            redstoneControlledBE.evaluateRedstone();
        }
    }

    public void clearProtectionCache() { this.chunkTestCache.clear();}

    public void  handleTicks() {
        if(this.operationTicks <= 0) {
            this.operationTicks = this.tickSpeed;
        }
        --this.operationTicks;
    }

    public int getTickSpeed() {return this.tickSpeed;}

    public void setTickSpeed(int newTickSpeed) {
        this.tickSpeed = newTickSpeed;
        if (this.operationTicks > this.tickSpeed) {
            this.operationTicks = this.tickSpeed;
        }
        this.markDirtyClient();
    }

    public boolean canRun() {
        if(!(this instanceof  RedstoneControlledBE redstoneControlledBE)) {
            return this.operationTicks == 0;
        } else {
            return this.operationTicks == 0 || redstoneControlledBE.getRedstoneControlData().redstoneMode.equals(MiscHelpersRedstoneMode.PULSE);
        }
    }

    public int getDirection() {return this.direction;}

    public Direction getDirectionValue() { return Direction.values()[this.direction];}

    public void setDirection(int direction){ this.direction = direction; }

    protected GameProfile getPlaceByProfile() {
        return this.placedByUUID == null ? defaultFakePlayerProfile : new GameProfile(this.placedByUUID, "[LightTechFakePlayer]");
    }

    protected boolean canPlaceAt(Level level, BlockPos pos, FakePlayer fakePlayer) {
        ChunkPos chunkPos = new ChunkPos(pos);
        if(this.chunkTestCache.containsKey(chunkPos)) {
            return (boolean) this.chunkTestCache.get(chunkPos);
        } else {
            boolean canPlace = !EventHooks.onBlockPlace(fakePlayer, BlockSnapshot.create(level.dimension(), level, pos.below()), Direction.UP);
            this.chunkTestCache.put(chunkPos, canPlace);
            return canPlace;
        }
    }

    protected boolean canBreakAt(Level level, BlockPos pos, FakePlayer fakePlayer) { return true; }

    protected boolean canBreakAndPlaceAt(Level level, BlockPos pos, FakePlayer fakePlayer) {
        ChunkPos chunkPos = new ChunkPos(pos);
        if(this.chunkTestCache.containsKey(chunkPos)) {
            return (boolean) this.chunkTestCache.get(chunkPos);
        } else {
            boolean canBreak = this.canBreakAt(level, pos, fakePlayer) && this.canPlaceAt(level, pos, fakePlayer);
            this.chunkTestCache .put(chunkPos, canBreak);
            return canBreak;
        }
    }

    protected FakePlayer getFakePlayer(ServerLevel level){
        return FakePlayerFactory.get(level, this.getPlaceByProfile());
    }

    protected FakePlayer getFakePlayer(ServerLevel level, UUID uuid) {
        GameProfile gameProfile = new GameProfile(uuid, "[LightTechFakePlayer]");
        return FakePlayerFactory.get(level, gameProfile);
    }

    protected UsefulFakePlayer getUsefulFakePlayer(ServerLevel level) {
        if(this.usefulFakePlayer == null) {
            this.usefulFakePlayer = UsefulFakePlayer.createPlayer(level, this.getPlaceByProfile());
        }
        return this.usefulFakePlayer;
    }

    public void setFakePlayerData(ItemStack itemStack, FakePlayer fakePlayer, BlockPos pos, Direction direction) {
        fakePlayer.setPos((double) pos.below().relative(direction).getX() + (double)0.5f, (double) pos.below().relative(direction).getY(), (double) pos.below().relative(direction).getZ() + (double) 0.5f);
        float xRot = direction == Direction.DOWN ? 90.0f : (direction == Direction.UP ? -90.0f : 0.0f);
        fakePlayer.setXRot(xRot);
        fakePlayer.setYRot(direction.toYRot());
        fakePlayer.setYHeadRot(direction.toYRot());
        fakePlayer.setItemInHand(InteractionHand.MAIN_HAND, itemStack);
    }

    public void setPlaceBy(UUID placeBy){ this.placedByUUID = placeBy; }

    public ClientboundBlockEntityDataPacket getUpdatePacket() { return  ClientboundBlockEntityDataPacket.create(this); }

    public ItemStackHandler getMachineHandler() { return (ItemStackHandler)this.getData(Registration.MACHINE_HANDLER); }

    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        this.loadAdditional(tag, lookupProvider);
    }
    public CompoundTag getUpdateTag(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        this.saveAdditional(tag, provider);
        return tag;
    }

    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt, HolderLookup.Provider lookupProvider) {
        super.onDataPacket(net, pkt, lookupProvider);
        if(this instanceof  AreaAffectingBE areaAffectingBE) {
            areaAffectingBE.getAreaAffectingData().area = null;
        }
    }

    public void markDirtyClient() {
        this.setChanged();
        if(this.level != null) {
            BlockState state = this.level.getBlockState(this.getBlockPos());
            this.level.sendBlockUpdated(this.getBlockPos(), state, state, 3);
        }
    }

    public void setChanged() {
        super.setChanged();
        if(this instanceof FilterableBE filterableBE) {
            filterableBE.getFilterData().filterCache.clear();
        }
    }

    public FilterData getDefaultFilterData() { return new FilterData(); }

    public RedstoneControlData getDefaultRedstoneData() { return new RedstoneControlData(); }

    public AreaAffectingData getDefaultAreaData(AreaAffectingBE areaAffectingBE) {
        return areaAffectingBE.getDefaultAreaData((Direction)this.getBlockState().getValue(BlockStateProperties.FACING));
    }

    public boolean isDefualtSetting() {
        if(this.tickSpeed != 20) {
            return false;
        } else if (this.direction != 0) {
            return false;
        } else {
            if (this instanceof AreaAffectingBE) {
                AreaAffectingBE areaAffectingBE = (AreaAffectingBE)this;
                if (!areaAffectingBE.getAreaAffectingData().equals(this.getDefaultAreaData(areaAffectingBE))) {
                    return false;
                }
            }

            if(this instanceof FilterableBE) {
                FilterableBE filterableBE = (FilterableBE)this;
                if(!filterableBE.getFilterData().equals(this.getDefaultFilterData())) {
                    return false;
                }
            }

            if(this instanceof FilterableBE) {
                FilterableBE filterableBe = (FilterableBE)this;
                FilterBasicHandler filteredItems = filterableBe.getFilterHandler();

                for(int i = 0; i < filteredItems.getSlots(); ++i) {
                    if(!filteredItems.getStackInSlot(i).isEmpty()) {
                        return false;
                    }
                }
            }

            if(this instanceof PoweredMachineBE) {
                PoweredMachineBE poweredMachineBE = (PoweredMachineBE)this;
                if(poweredMachineBE.getEnergyStored() > 0) {
                    return false;
                }
            }

            if(this instanceof RedstoneControlledBE) {
                RedstoneControlledBE redstoneControlledBE = (RedstoneControlledBE)this;
                if(!redstoneControlledBE.getRedstoneControlData().equals(this.getDefaultRedstoneData())) {
                    return false;
                }
            }

            return true;
        }
    }

    public void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.saveAdditional(tag, provider);
        tag.putInt("ticksped", this.tickSpeed);
        if (this.placedByUUID != null) {
            tag.putUUID("placeBy", this.placedByUUID);
        }
        tag.putInt("direction", this.direction);
        if(this instanceof  AreaAffectingBE areaAffectingBE) {
            areaAffectingBE.saveAreaSettings(tag);
        }
        if (this instanceof FilterableBE filterableBE) {
            filterableBE.saveFilterSettings(tag);
        }
        if (this instanceof RedstoneControlledBE redstoneControlledBE) {
            redstoneControlledBE.saveRedstoneSettings(tag);
        }
    }

    public void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        if (tag.contains("direction")) {
            this.direction = tag.getInt("direction");
        }
        if (tag.contains("tickspeed")) {
            this.tickSpeed = tag.getInt("tickspeed");
        }
        if (tag.contains("placedBy")) {
            this.placedByUUID = tag.getUUID("placedBy");
        }
        if (this instanceof AreaAffectingBE areaAffectingBE) {
            areaAffectingBE.loadAreaSettings(tag);
        }
        if (this instanceof FilterableBE filterableBE) {
            filterableBE.loadFilterSettings(tag);
        }
        if (this instanceof RedstoneControlledBE redstoneControlledBE) {
            redstoneControlledBE.loadRedstoneSettings(tag);
        }
        super.loadAdditional(tag, provider);
    }

    static {
        defaultFakePlayerProfile = new GameProfile(defaultFakePlayerUUID, "[LightTechFakePlayer]");
    }
}
