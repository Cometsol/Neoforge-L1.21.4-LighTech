package com.cometsol.lighttech;

import com.cometsol.lighttech.Common.BlockEnities.BaseBE.MachineEntityBase;
import com.cometsol.lighttech.Common.BlockEnities.BaseBE.PoweredMachineBE;
import com.cometsol.lighttech.Common.Capabilities.MachineEnergyStorage;
import com.cometsol.lighttech.Common.Handlers.FilterBasicHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.common.world.chunk.LoadingValidationCallback;
import net.neoforged.neoforge.common.world.chunk.TicketController;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static net.neoforged.neoforge.registries.NeoForgeRegistries.Keys;

public class Registration {
    public static final TicketController TICKET_CONTROLLER = new TicketController(ResourceLocation.fromNamespaceAndPath("lighttech", "chunk_loader"), (LoadingValidationCallback)null);
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES;
    public static final Supplier<AttachmentType<ItemStackHandler>> HANDLER;
    public static final Supplier<AttachmentType<ItemStackHandler>> MACHINE_HANDLER;
    //public static final Supplier<AttachmentType<GeneratorFluidItemHandler>> GENERATOR_FLUID_ITEM_HANDLER;
    public static final Supplier<AttachmentType<FilterBasicHandler>> HANDLER_BASIC_FILTER;
    public static final Supplier<AttachmentType<FilterBasicHandler>> HANDLER_BASIC_FILTER_ANYSIZE;
    public static final Supplier<AttachmentType<MachineEnergyStorage>> ENERGYSTORAGE_MACHINES;
    //public static final Supplier<AttachmentType<TransmitterEnergyStorage>> ENERGYSTORAGE_TRANSMITTERS;
    //public static final Supplier<AttachmentType<EnergyStorageNoReceive>> ENERGYSTORAGE_GENERATORS;
    public static final Supplier<AttachmentType<CompoundTag>> DEATH_DATA;

    public static void init(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }
    
    static {
        ATTACHMENT_TYPES = DeferredRegister.create(Keys.ATTACHMENT_TYPES, "lighttech");
        HANDLER = ATTACHMENT_TYPES.register("handler", () -> AttachmentType.serializable(() -> new ItemStackHandler(1)).build());
        MACHINE_HANDLER = ATTACHMENT_TYPES.register("machine_handler", () -> AttachmentType.serializable((holder) -> {
            if (holder instanceof MachineEntityBase machineEntityBase) {
                return new ItemStackHandler( machineEntityBase.MACHINE_SLOTS);
            } else {
                return new ItemStackHandler(1);
            }
        }).build());
        /*GENERATOR_ITEM_HANDLER = ATTACHMENT_TYPES.register("generator_item_handler", () -> AttachmentType.serializable((holder) -> {
            if (holder instanceof  MachineEntityBase machineEntityBase) {
                return new GeneratorItemHandler( machineEntityBase.MACHINE_SLOTS);
            } else {
                return new GeneratorItemHandler(1);
            }
        }).build());
        GENERATOR_FLUID_ITEM_HANDLER = ATTACHMENT_TYPES.register("generator_fluid_item_handler", () -> AttachmentType.serializable((holder) -> {
            if (holder instanceof  MachineEntityBase machineEntityBase) {
                return new GeneratorFluidItemHandler( machineEntityBase.MACHINE_SLOTS);
            } else {
                return new GeneratorFluidItemHandler(1);
            }
        }).build());*/
        HANDLER_BASIC_FILTER = ATTACHMENT_TYPES.register("handler_item_collector", () -> AttachmentType.serializable(() -> new FilterBasicHandler(9)).build());
        HANDLER_BASIC_FILTER_ANYSIZE = ATTACHMENT_TYPES.register("anysize_filter_handler", () -> AttachmentType.serializable((holder) -> {
            if (holder instanceof  MachineEntityBase machineEntityBase) {
                return new FilterBasicHandler( machineEntityBase.ANYSIZE_FILTER_SLOTS);
            } else {
                return new FilterBasicHandler(0);
            }
        }).build());
        ENERGYSTORAGE_MACHINES = ATTACHMENT_TYPES.register("energystorage_machines", () -> AttachmentType.serializable((holder) -> {
            if (holder instanceof PoweredMachineBE feMachineBE) {
                int capacity = feMachineBE.getMaxEnergy();
                return new MachineEnergyStorage(capacity);
            } else {
                throw new IllegalStateException("Cannot attach energy handler item to a non-PoweredMachine.");
            }
        }).build());
        /*ENERGYSTORAGE_TRANSMITTERS = ATTACHMENT_TYPES.register("energystorage_transmitters", () -> AttachmentType.serializable((holder) -> {
            if (holder instanceof EnergyTransmitterBE energyTransmitterBE) {
                int capacity = energyTransmitterBE.getMaxEnergy();
                return new TransmitterEnergyStorage(capacity, energyTransmitterBE);
            } else {
                throw new IllegalStateException("Cannot attach energy handler item to a non-EnergyTransmitter.");
            }
        }).build());
        ENERGYSTORAGE_GENERATORS = ATTACHMENT_TYPES.register("energystorage_generators", () -> AttachmentType.serializable((holder) -> {
            if (holder instanceof PoweredMachineBE feMachineBE) {
                int capacity = feMachineBE.getMaxEnergy();
                return new EnergyStorageNoReceive(capacity);
            } else {
                throw new IllegalStateException("Cannot attach energy handler item to a non-PoweredMachine.");
            }
        }).build());*/
        DEATH_DATA = ATTACHMENT_TYPES.register("death_data", () -> AttachmentType.builder(CompoundTag::new).serialize(CompoundTag.CODEC).build());
    }

}
