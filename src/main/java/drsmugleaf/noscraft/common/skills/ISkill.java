package drsmugleaf.noscraft.common.skills;

import drsmugleaf.noscraft.common.IModellable;
import drsmugleaf.noscraft.common.classes.ITransformation;
import drsmugleaf.noscraft.common.element.Elements;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by DrSmugleaf on 15/02/2019
 */
public interface ISkill extends IForgeRegistryEntry<ISkill>, IModellable {

    int getID();

    @Nonnull
    String getName();

    int getJobLevel();

    @Nonnull
    Elements getElement();

    @Nonnull
    String getDescription();

    @Nonnull
    String getSkillEffects();

    int getCP();

    double getCastTime();

    int getRange();

    double getCooldown();

    @Nonnull
    Target getTarget();

    int getMPCost();

    int getDamageBonus();

    int getElementBonus();

    @Nullable
    ITransformation getTransformation();

    @Nonnull
    ResourceLocation getTexture();

    boolean isOnCooldown();

    void cast();

    // TODO: 15/02/2019 Effects

}
