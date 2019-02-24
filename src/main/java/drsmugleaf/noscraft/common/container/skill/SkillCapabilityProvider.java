package drsmugleaf.noscraft.common.container.skill;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by DrSmugleaf on 22/02/2019
 */
public class SkillCapabilityProvider implements ICapabilityProvider, INBTSerializable<NBTTagCompound> {

    private final @Nonnull SkillsLearned SKILLS_LEARNED;

    public SkillCapabilityProvider(@Nonnull SkillsLearned skills) {
        SKILLS_LEARNED = skills;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == SkillCapabilities.CAPABILITY_SKILLS;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == SkillCapabilities.CAPABILITY_SKILLS ? SkillCapabilities.CAPABILITY_SKILLS.cast(SKILLS_LEARNED) : null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return SKILLS_LEARNED.serializeNBT();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        SKILLS_LEARNED.deserializeNBT(nbt);
    }

}
