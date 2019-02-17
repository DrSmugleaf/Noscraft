package drsmugleaf.noscraft.common.abilities;

import drsmugleaf.noscraft.common.element.Elements;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 15/02/2019
 */
public interface IAbility {

    int getJobLevel();

    @Nonnull
    Elements getElement();

    @Nonnull String getDescription();

    @Nonnull String getSkillEffects();

    int getCP();

    double getCastTime();

    int getDistance();

    double getCooldown();

    @Nonnull Target getTarget();

    int getMPCost();

    int getDamageBonus();

    int getElementBonus();

    // TODO: 15/02/2019 Effects

}
