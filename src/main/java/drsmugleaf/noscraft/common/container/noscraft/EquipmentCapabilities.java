package drsmugleaf.noscraft.common.container.noscraft;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

import javax.annotation.Nullable;

/**
 * Created by DrSmugleaf on 05/03/2019
 */
public class EquipmentCapabilities {

    @CapabilityInject(Equipment.class)
    public static final Capability<Equipment> CAPABILITY_EQUIPMENT = null;

    public static class CapabilityEquipment implements Capability.IStorage<Equipment> {

        @Nullable
        @Override
        public NBTBase writeNBT(Capability<Equipment> capability, Equipment instance, EnumFacing side) {
            return null;
        }

        @Override
        public void readNBT(Capability<Equipment> capability, Equipment instance, EnumFacing side, NBTBase nbt) {}

    }

}
