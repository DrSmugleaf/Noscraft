package drsmugleaf.noscraft.common.container.skill;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

import javax.annotation.Nullable;

/**
 * Created by DrSmugleaf on 22/02/2019
 */
public class SkillCapabilities {

    @CapabilityInject(SkillsLearned.class)
    public static final Capability<SkillsLearned> CAPABILITY_SKILLS = null;

    public static class CapabilitySkills implements Capability.IStorage<SkillsLearned> {

        @Nullable
        @Override
        public NBTBase writeNBT(Capability<SkillsLearned> capability, SkillsLearned instance, EnumFacing side) {
            return instance.serializeNBT();
        }

        @Override
        public void readNBT(Capability<SkillsLearned> capability, SkillsLearned instance, EnumFacing side, NBTBase nbt) {
            instance.deserializeNBT((NBTTagCompound) nbt);
        }

    }

}
