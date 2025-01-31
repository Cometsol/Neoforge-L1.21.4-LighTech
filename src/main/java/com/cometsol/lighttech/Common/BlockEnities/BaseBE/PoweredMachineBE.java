package com.cometsol.lighttech.Common.BlockEnities.BaseBE;

import net.minecraft.world.inventory.ContainerData;
import com.cometsol.lighttech.Common.Capabilities.MachineEnergyStorage;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.capabilities.Capabilities.EnergyStorage;
import net.neoforged.neoforge.energy.IEnergyStorage;

public interface PoweredMachineBE {
    default int getMaxEnergy() {
        return 100000;
    }

    ContainerData getContainerData();

    MachineEnergyStorage getEnergyStorage();

    default int getEnergyStored() {
        return this.getEnergyStorage().getEnergyStored();
    }

    default void setEnergyStored(int value) {
        this.getEnergyStorage().setEnergy(value);
    }

    int getStandardEnergyCost();

    default boolean hasEnoughPower(int power) {
        return this.getEnergyStorage().extractEnergy(power, true) >= power;
    }

    default int insertEnergy(int power, boolean simulate) {
        return this.getEnergyStorage().receiveEnergy(power, simulate);
    }

    default int extractEnergy(int power, boolean simulate) {
        return this.getEnergyStorage().extractEnergy(power, simulate);
    }

    default void chargeItemStack(ItemStack itemStack) {
        IEnergyStorage slotEnergy = (IEnergyStorage)itemStack.getCapability(EnergyStorage.ITEM);
        if (slotEnergy != null) {
            int acceptedEnergy = slotEnergy.receiveEnergy(5000, true);
            if (acceptedEnergy > 0) {
                int extractedEnergy = this.getEnergyStorage().extractEnergy(acceptedEnergy, false);
                slotEnergy.receiveEnergy(extractedEnergy, false);
            }
        }

    }
}

