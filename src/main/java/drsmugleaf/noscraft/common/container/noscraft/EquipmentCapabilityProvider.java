package drsmugleaf.noscraft.common.container.noscraft;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by DrSmugleaf on 05/03/2019
 */
public class EquipmentCapabilityProvider implements ICapabilityProvider, INBTSerializable<NBTTagCompound> {

    private final @Nonnull Equipment EQUIPMENT;

    public EquipmentCapabilityProvider(@Nonnull Equipment equipment) {
        EQUIPMENT = equipment;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == EquipmentCapabilities.CAPABILITY_EQUIPMENT;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return hasCapability(capability, facing) ? EquipmentCapabilities.CAPABILITY_EQUIPMENT.cast(EQUIPMENT) : null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return EQUIPMENT.serializeNBT();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        EQUIPMENT.deserializeNBT(nbt);
    }

}
