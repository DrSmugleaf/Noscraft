package drsmugleaf.noscraft.common.abilities.archer.destroyer;

import drsmugleaf.noscraft.common.abilities.Target;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 15/02/2019
 */
public class FireTheShotgun extends AbilityDestroyer {

    @Override
    public int getJobLevel() {
        return 0;
    }

    @Override
    public @Nonnull String getDescription() {
        return "Basic skill for destroyers";
    }

    @Override
    public @Nonnull String getSkillEffects() {
        return "Ranged attack +150, fire element +180";
    }

    @Override
    public double getCastTime() {
        return 0.2;
    }

    @Override
    public double getCooldown() {
        return 0.8;
    }

    @Override
    public @Nonnull Target getTarget() {
        return Target.SELECTED;
    }

    @Override
    public int getMPCost() {
        return 0;
    }

    @Override
    public int getDamageBonus() {
        return 150;
    }

    @Override
    public int getElementBonus() {
        return 180;
    }

}
