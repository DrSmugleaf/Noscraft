package drsmugleaf.noscraft.common.container.skill;

import drsmugleaf.noscraft.common.skills.ISkill;
import drsmugleaf.noscraft.common.skills.ModSkills;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DrSmugleaf on 22/02/2019
 */
public class SkillsLearned implements INBTSerializable<NBTTagCompound> {

    @Nonnull
    private final Map<Integer, ISkill> SKILLS = new HashMap<>();

    public SkillsLearned() {
        for (ISkill skill : ModSkills.SKILLS) {
            SKILLS.put(skill.getID(), skill);
        }
    }

    public void addSkill(@Nonnull ISkill skill) {
        SKILLS.put(skill.getID(), skill);
    }

    @Nonnull
    public Map<Integer, ISkill> getSkills() {
        return new HashMap<>(SKILLS);
    }

    public void removeSkill(int id) {
        SKILLS.remove(id);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        for (ISkill skill : getSkills().values()) {
            String skillName = skill.getRegistryName().toString();
            nbt.setBoolean(skillName, true);
        }

        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        SKILLS.clear();

        for (ISkill skill : ModSkills.SKILLS) {
            if (nbt.getBoolean(skill.getRegistryName().toString())) {
                addSkill(skill);
            }
        }
    }

}
