package drsmugleaf.noscraft.common.container.noscraft;

import drsmugleaf.noscraft.common.item.equipment.fairy.IFairy;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

import javax.annotation.Nullable;

/**
 * Created by DrSmugleaf on 05/02/2019
 */
public class FairyCapabilities {

    @CapabilityInject(FairyContainer.class)
    public static final Capability<FairyContainer> CAPABILITY_FAIRIES = null;

    @CapabilityInject(IFairy.class)
    public static final Capability<IFairy> CAPABILITY_ITEM_FAIRY = null;

    public static class CapabilityFairy implements Capability.IStorage<FairyContainer> {

        @Override
        public @Nullable NBTBase writeNBT(Capability<FairyContainer> capability, FairyContainer instance, EnumFacing side) {
            return null;
        }

        @Override
        public void readNBT(Capability<FairyContainer> capability, FairyContainer instance, EnumFacing side, NBTBase nbt) {}

    }

    public static class CapabilityItemFairyStorage implements Capability.IStorage<IFairy> {

        @Override
        public @Nullable NBTBase writeNBT(Capability<IFairy> capability, IFairy instance, EnumFacing side) {
            return null;
        }

        @Override
        public void readNBT(Capability<IFairy> capability, IFairy instance, EnumFacing side, NBTBase nbt) {}

    }

}
