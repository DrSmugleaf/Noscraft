package drsmugleaf.noscraft.common.container.noscraft;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by DrSmugleaf on 05/02/2019
 */
public class FairyCapabilityProvider implements ICapabilityProvider, INBTSerializable<NBTTagCompound> {

    private final @Nonnull FairyContainer CONTAINER;

    public FairyCapabilityProvider(@Nonnull FairyContainer container) {
        CONTAINER = container;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == FairyCapabilities.CAPABILITY_FAIRIES;
    }

    @Override
    @SuppressWarnings("unchecked")
    public @Nullable <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == FairyCapabilities.CAPABILITY_FAIRIES ? FairyCapabilities.CAPABILITY_FAIRIES.cast(CONTAINER) : null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return CONTAINER.serializeNBT();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        CONTAINER.deserializeNBT(nbt);
    }

}
